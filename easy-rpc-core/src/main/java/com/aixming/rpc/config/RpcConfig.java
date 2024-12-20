package com.aixming.rpc.config;

import lombok.Data;

/**
 * RPC 框架配置
 *
 * @author AixMing
 * @since 2024-12-20 19:15:28
 */
@Data
public class RpcConfig {

    /**
     * 名称
     */
    private String name;

    /**
     * 版本号
     */
    private String version;

    /**
     * 服务器主机名
     */
    private String serverHost = "localhost";

    /**
     * 服务器端口号
     */
    private Integer serverPort = 8080;

}
