package com.aixming.service.provider;

import com.aixming.common.service.UserService;
import com.aixming.rpc.RpcApplication;
import com.aixming.rpc.config.RegistryConfig;
import com.aixming.rpc.config.RpcConfig;
import com.aixming.rpc.constant.RpcConstant;
import com.aixming.rpc.model.ServiceMetaInfo;
import com.aixming.rpc.registry.LocalRegistry;
import com.aixming.rpc.registry.Registry;
import com.aixming.rpc.registry.RegistryFactory;
import com.aixming.rpc.server.tcp.VertxTcpServer;

/**
 * 服务提供者示例
 *
 * @author AixMing
 * @since 2024-12-27 18:43:54
 */
public class ProviderExample {

    public static void main(String[] args) {
        // 加载配置文件
        RpcApplication.init();
        // 注册服务
        String serviceName = UserService.class.getName();
        LocalRegistry.register(serviceName, UserServiceImpl.class);
        // 注册服务到注册中心
        RpcConfig rpcConfig = RpcApplication.getRpcConfig();
        RegistryConfig registryConfig = rpcConfig.getRegistryConfig();
        Registry registry = RegistryFactory.getInstance(registryConfig.getRegistry());
        ServiceMetaInfo serviceMetaInfo = new ServiceMetaInfo();
        serviceMetaInfo.setServiceName(serviceName);
        serviceMetaInfo.setServiceHost(rpcConfig.getServerHost());
        serviceMetaInfo.setServicePort(rpcConfig.getServerPort());
        serviceMetaInfo.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        try {
            registry.register(serviceMetaInfo);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        // 启动 web 服务
        VertxTcpServer server = new VertxTcpServer();
        server.doStart(rpcConfig.getServerPort());
    }

}
