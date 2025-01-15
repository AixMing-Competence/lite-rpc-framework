package com.aixming.service.consumer;

import com.aixming.common.model.User;
import com.aixming.common.service.UserService;
import com.aixming.rpc.bootstrap.ConsumerBootstrap;
import com.aixming.rpc.proxy.ServiceProxyFactory;

/**
 * @author AixMing
 * @since 2024-12-11 17:58:00
 */
public class EasyServiceConsumer {
    public static void main(String[] args) {
        // 服务提供者初始化
        ConsumerBootstrap.init();

        // 现在要调用远程服务
        // 获取代理
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("aixming1111");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("newUser is null");
        }
        short number = userService.getNumber();
        System.out.println(number);
    }
}
