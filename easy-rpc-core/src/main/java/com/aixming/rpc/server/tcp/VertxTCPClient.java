package com.aixming.rpc.server.tcp;

import io.vertx.core.Vertx;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

/**
 * vertx TCP 客户端
 *
 * @author AixMing
 * @since 2025-01-07 16:02:58
 */
public class VertxTCPClient {

    public void start() {
        Vertx vertx = Vertx.vertx();
        NetClient client = vertx.createNetClient();
        client.connect(8888, "localhost", result -> {
            if (result.succeeded()) {
                System.out.println("Connected to TCP server");
                NetSocket socket = result.result();
                // 发送数据
                socket.write("Hello server!");
                // 接受响应
                socket.handler(buffer -> {
                    System.out.println("Received response from server: " + buffer.toString());
                });
            } else {
                System.out.println("Failed to TCP server: " + result.cause().getMessage());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTCPClient().start();
    }

}
