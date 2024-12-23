package com.aixming.service.consumer;

import com.aixming.rpc.config.RpcConfig;
import com.aixming.rpc.utils.ConfigUtil;

/**
 * @author AixMing
 * @since 2024-12-20 19:46:25
 */
public class ConsumerExample {

    public static void main(String[] args) {
        RpcConfig rpcConfig = ConfigUtil.loadConfig(RpcConfig.class, "rpc");
        System.out.println(rpcConfig);
    }

}
