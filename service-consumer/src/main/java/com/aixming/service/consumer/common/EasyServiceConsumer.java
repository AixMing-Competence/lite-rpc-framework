package com.aixming.service.consumer.common;

import com.aixming.common.model.User;
import com.aixming.common.service.UserService;

/**
 * @author AixMing
 * @since 2024-12-11 17:58:00
 */
public class EasyServiceConsumer {
    public static void main(String[] args) {
        UserService userService = null;
        User user = new User();
        user.setName("aixming");
        User newUser = userService.getUser(user);
        if (newUser != null) {
            System.out.println(newUser.getName());
        } else {
            System.out.println("user == null");
        }
    }
}
