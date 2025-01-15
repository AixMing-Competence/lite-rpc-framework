package com.aixming.rpc.bootstrap;

import com.aixming.rpc.RpcApplication;
import com.aixming.rpc.config.RegistryConfig;
import com.aixming.rpc.config.RpcConfig;
import com.aixming.rpc.constant.RpcConstant;
import com.aixming.rpc.model.ServiceMetaInfo;
import com.aixming.rpc.registry.LocalRegistry;
import com.aixming.rpc.registry.Registry;
import com.aixming.rpc.registry.RegistryFactory;
import com.aixming.rpc.server.tcp.VertxTcpServer;

import java.util.List;

/**
 * 服务提供者启动类
 *
 * @author AixMing
 * @since 2025-01-14 21:40:30
 */
public class ProviderBootstrap {

    public static void init(List<ServiceRegisterInfo<?>> serviceRegisterInfoList) {
        // 加载配置文件
        RpcApplication.init();
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();

        // 遍历所有要注册的服务
        for (ServiceRegisterInfo<?> serviceRegisterInfo : serviceRegisterInfoList) {
            String serviceName = serviceRegisterInfo.getServiceName();
            // 本地注册
            LocalRegistry.register(serviceName, serviceRegisterInfo.getImplClass());
            
            // 获取注册中心
            RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
            Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());

            ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();

            serviceMetaInfo.setServiceName(serviceName);
            serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
            serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
            serviceMetaInfo.setServicePort(rpcConfig.getServerPort());

            try {
                // 注册到注册中心
                registry.register(serviceMetaInfo);
            } catch (Exception e) {
                throw new RuntimeException(serviceName + " 服务注册失败", e);
            }

        }

        // 启动 web 服务器
        VertxTcpServer vertxTcpServer = new VertxTcpServer();
        vertxTcpServer.doStart(rpcConfig.getServerPort());
    }

}
