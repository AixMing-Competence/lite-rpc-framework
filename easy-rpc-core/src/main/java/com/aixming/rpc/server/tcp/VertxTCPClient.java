package com.aixming.rpc.server.tcp;

import cn.hutool.core.util.IdUtil;
import com.aixming.rpc.RpcApplication;
import com.aixming.rpc.model.RpcRequest;
import com.aixming.rpc.model.RpcResponse;
import com.aixming.rpc.model.ServiceMetaInfo;
import com.aixming.rpc.protocol.*;
import io.vertx.core.Vertx;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.net.NetClient;
import io.vertx.core.net.NetSocket;

import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * vertx TCP 请求客户端
 *
 * @author AixMing
 * @since 2025-01-07 16:02:58
 */
public class VertxTCPClient {

    /**
     * 发送请求
     *
     * @param rpcRequest
     * @param serviceMetaInfo
     * @return
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static RpcResponse doRequest(RpcRequest rpcRequest, ServiceMetaInfo serviceMetaInfo) throws ExecutionException, InterruptedException {
        Vertx vertx = Vertx.vertx();
        NetClient client = vertx.createNetClient();
        CompletableFuture<RpcResponse> completableFuture = new CompletableFuture<>();
        client.connect(serviceMetaInfo.getServicePort(), serviceMetaInfo.getServiceHost(),
                result -> {
                    // 没连接成功
                    if (!result.succeeded()) {
                        System.err.println("Failed to connect to tcp server");
                        return;
                    }
                    NetSocket netSocket = result.result();
                    // 构建请求消息
                    ProtocolMessage<RpcRequest> requestProtocolMessage = new ProtocolMessage<>();
                    ProtocolMessage.Header header = new ProtocolMessage.Header();
                    header.setMagic(ProtocolConstant.MESSAGE_MAGIC);
                    header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
                    header.setSerializer((byte) ProtocolMessageSerializerEnum.getEnumByValue(RpcApplication.getRpcConfig().getSerializer()).getKey());
                    header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
                    header.setRequestId(IdUtil.getSnowflakeNextId());
                    header.setBodyLength(0);
                    requestProtocolMessage.setHeader(header);
                    requestProtocolMessage.setBody(rpcRequest);

                    try {
                        // 编码
                        Buffer buffer = ProtocolMessageEncoder.encode(requestProtocolMessage);
                        netSocket.write(buffer);
                    } catch (IOException e) {
                        throw new RuntimeException("请求消息编码失败");
                    }

                    TcpBufferHandlerWrapper tcpBufferHandlerWrapper = new TcpBufferHandlerWrapper(buffer -> {
                        try {
                            ProtocolMessage<RpcResponse> responseProtocolMessage = 
                                    (ProtocolMessage<RpcResponse>) ProtocolMessageDecoder.decode(buffer);
                            completableFuture.complete(responseProtocolMessage.getBody());
                        } catch (IOException e) {
                            throw new RuntimeException("响应消息解码失败");
                        }
                    });
                    // 处理响应
                    netSocket.handler(tcpBufferHandlerWrapper);
                });
        RpcResponse rpcResponse = completableFuture.get();
        // 关闭连接
        client.close();
        return rpcResponse;
    }

    public void start() {
        Vertx vertx = Vertx.vertx();
        NetClient client = vertx.createNetClient();
        client.connect(8888, "localhost", result -> {
            if (result.succeeded()) {
                System.out.println("Connected to TCP server");
                NetSocket socket = result.result();
                // 发送数据
                for (int i = 0; i < 1000; i++) {
                    Buffer buffer = Buffer.buffer();
                    buffer.appendInt(0);
                    String str = "Hello server!Hello server!Hello server!";
                    buffer.appendInt(str.getBytes().length);
                    buffer.appendBytes(str.getBytes());
                    socket.write(buffer);
                }
                // 接受响应
                socket.handler(buffer -> System.out.println("Received response from server: " + buffer.toString()));
            } else {
                System.out.println("Failed to TCP server: " + result.cause().getMessage());
            }
        });
    }

    public static void main(String[] args) {
        new VertxTCPClient().start();
    }

}
