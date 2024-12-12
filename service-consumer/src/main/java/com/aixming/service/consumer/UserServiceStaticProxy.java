package com.aixming.service.consumer;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.aixming.common.model.User;
import com.aixming.common.service.UserService;
import com.aixming.rpc.model.RpcRequest;
import com.aixming.rpc.model.RpcResponse;
import com.aixming.rpc.serializer.JdkSerializer;
import com.aixming.rpc.serializer.Serializer;

import java.io.IOException;

/**
 * UserService 静态代理
 *
 * @author AixMing
 * @since 2024-12-11 21:06:46
 */
public class UserServiceStaticProxy implements UserService {

    public User getUser(User user) {
        // 序列化器
        final Serializer serializer = new JdkSerializer();

        // 发请求
        RpcRequest rpcRequest = RpcRequest.builder()
                .serviceName(UserService.class.getName())
                .methodName("getUser")
                .parameterTypes(new Class[]{User.class})
                .args(new Object[]{user})
                .build();

        try {
            byte[] bodyBytes = serializer.serialize(rpcRequest);
            byte[] resultBytes;
            try (HttpResponse httpResponse = HttpRequest.post("http://localhost:8080")
                    .body(bodyBytes)
                    .execute()) {
                resultBytes = httpResponse.bodyBytes();
            }
            RpcResponse rpcResponse = serializer.deSerialize(resultBytes, RpcResponse.class);
            return (User) rpcResponse.getData();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
