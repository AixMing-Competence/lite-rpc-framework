package com.aixming.rpc.server.tcp;

import com.aixming.rpc.server.HttpServer;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetServer;

/**
 * Vertx TCP 服务器
 *
 * @author AixMing
 * @since 2025-01-07 15:26:04
 */
public class VertxTcpServer implements HttpServer {
    @Override
    public void doStart(int port) {
        Vertx vertx = Vertx.vertx();
        NetServer server = vertx.createNetServer();
        // 处理请求
        server.connectHandler((socket) -> {
            // 处理连接
            socket.handler(buffer -> {
                byte[] requestBytes = buffer.getBytes();
                // 处理请求数据
                System.out.println("Server received data from client: " + requestBytes.toString());
                byte[] responseBytes = handleRequest(requestBytes);
                // 发送响应
                socket.write(Buffer.buffer(responseBytes));
            });
        });

        // 监听端口，启动 TCP 服务器
        server.listen(port, result -> {
            if (result.succeeded()) {
                System.out.println("TCP server started on port " + port);
            } else {
                System.out.println("Failed to start TCP server: " + result.cause());
            }
        });

    }

    private byte[] handleRequest(byte[] requestBytes) {
        return "Hello, client!".getBytes();
    }

    public static void main(String[] args) {
        new VertxTcpServer().doStart(8888);
    }
}
