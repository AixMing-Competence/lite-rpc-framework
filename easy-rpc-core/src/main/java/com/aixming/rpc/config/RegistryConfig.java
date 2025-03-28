package com.aixming.rpc.config;

import lombok.Data;

/**
 * RPC 框架注册中心配置
 * 让用户配置连接注册中心所需信息
 *
 * @author AixMing
 * @since 2024-12-26 19:55:48
 */
@Data
public class RegistryConfig {

    /**
     * 注册中心类别
     */
    private String registry = "etcd";

    /**
     * 注册中心地址
     */
    private String address = "http://localhost:2380";

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 超时时间（单位毫秒）
     */
    private Long timeout = 10000L;

}
