package com.aixming.rpc.proxy;

import cn.hutool.core.collection.CollUtil;
import com.aixming.rpc.RpcApplication;
import com.aixming.rpc.config.RegistryConfig;
import com.aixming.rpc.config.RpcConfig;
import com.aixming.rpc.constant.RpcConstant;
import com.aixming.rpc.loadbalancer.LoadBalancer;
import com.aixming.rpc.loadbalancer.LoadBalancerFactory;
import com.aixming.rpc.model.RpcRequest;
import com.aixming.rpc.model.RpcResponse;
import com.aixming.rpc.model.ServiceMetaInfo;
import com.aixming.rpc.registry.Registry;
import com.aixming.rpc.registry.RegistryFactory;
import com.aixming.rpc.serializer.Serializer;
import com.aixming.rpc.serializer.SerializerFactory;
import com.aixming.rpc.server.tcp.VertxTCPClient;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;

/**
 * 服务代理（jdk 动态代理）
 *
 * @author AixMing
 * @since 2024-12-12 14:43:56
 */
public class ServiceProxy implements InvocationHandler {
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 序列化器
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        Serializer serializer = SerializerFactory.getInstance(rpcConfig.getSerializer());
        // 构建请求对象
        String serviceName = method.getDeclaringClass().getName();
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(serviceName)
                .methodName(method.getName())
                .parameterTypes(method.getParameterTypes())
                .args(args)
                .build();
        try {
            // 序列化
            byte[] bytes = serializer.serialize(rpcRequest);
            // 从注册中心获取服务请求地址
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            String serviceKey = serviceMetaInfo.getServiceKey();
            List<ServiceMetaInfo> serviceMetaInfoList = registry.serviceDiscovery(serviceKey);
            if (CollUtil.isEmpty(serviceMetaInfoList)) {
                throw new RuntimeException("暂无服务地址");
            }

            // 负载均衡
            LoadBalancer loadBalancer = LoadBalancerFactory.getInstance(rpcConfig.getLoadBalancer());
            // 将调用方法名作为负载均衡参数
            HashMap<String, Object> requestParams = new HashMap<>();
            requestParams.put("methodName",rpcRequest.getMethodName());
            ServiceMetaInfo selectedServiceMetaInfo = loadBalancer.select(requestParams, serviceMetaInfoList);

            // // 发http请求
            // try (HttpResponse httpResponse = HttpRequest.post(selectedServiceMetaInfo.getServiceAddress())
            //         .body(bytes)
            //         .execute()) {
            //     byte[] result = httpResponse.bodyBytes();
            //     // 反序列化
            //     RpcResponse rpcResponse = serializer.deSerialize(result, RpcResponse.class);
            //     return rpcResponse.getData();
            // }

            // 发送 TCP 请求
            RpcResponse rpcResponse = VertxTCPClient.doRequest(rpcRequest, selectedServiceMetaInfo);
            return rpcResponse.getData();
        } catch (Exception e) {
            throw new RuntimeException("远程调用失败");
        }
    }
}
