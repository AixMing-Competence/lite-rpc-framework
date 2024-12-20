package com.aixming.rpc.server;

/**
 * HTTP 服务器接口
 *
 * @author AixMing
 * @since 2024-12-11 18:59:13
 */
public interface HttpServer {

    /**
     * 启动服务器
     *
     * @param port
     */
    void doStart(int port);
}
