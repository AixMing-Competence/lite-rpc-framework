package com.aixming.service.consumer;

import com.aixming.common.model.User;
import com.aixming.common.service.UserService;
import com.aixming.rpc.proxy.ServiceProxyFactory;

/**
 * @author AixMing
 * @since 2024-12-11 17:58:00
 */
public class EasyServiceConsumer {
    public static void main(String[] args) {
        UserService userService = ServiceProxyFactory.getProxy(UserService.class);
        User user = new User();
        user.setName("aixming");
        User newUser = userService.getUser(user);
        System.out.println(newUser);
    }
}
