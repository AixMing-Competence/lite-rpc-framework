package com.aixming.service.provider;

import com.aixming.common.service.UserService;
import com.aixming.rpc.RpcApplication;
import com.aixming.rpc.config.RpcConfig;
import com.aixming.rpc.registry.LocalRegistry;
import com.aixming.rpc.server.VertxHttpServer;
import com.aixming.rpc.utils.ConfigUtil;

/**
 * 简易服务提供者示例
 *
 * @author AixMing
 * @since 2024-12-11 17:54:20
 */
public class EasyServiceProvider {
    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtil.loadConfig(RpcConfig.class, "rpc");
        RpcApplication.init(rpcConfig);
        // 注册服务
        LocalRegistry.register(UserService.class.getName(), UserServiceImpl.class);

        // 启动 web 服务
        VertxHttpServer server = new VertxHttpServer();
        server.doStart(RpcApplication.getRpcConfig().getServerPort());
    }
}
