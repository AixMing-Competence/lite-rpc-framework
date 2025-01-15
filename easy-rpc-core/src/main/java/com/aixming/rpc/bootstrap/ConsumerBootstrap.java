package com.aixming.rpc.bootstrap;

import com.aixming.rpc.RpcApplication;

/**
 * 服务消费者启动类（初始化）
 *
 * @author AixMing
 * @since 2025-01-15 13:42:21
 */
public class ConsumerBootstrap {

    /**
     * 初始化
     */
    public static void init() {
        // RPC 框架初始化（配置和注册中心）
        RpcApplication.init();
    }
    
}
