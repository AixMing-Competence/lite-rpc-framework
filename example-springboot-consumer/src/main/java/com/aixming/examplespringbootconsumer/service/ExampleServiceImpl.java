package com.aixming.examplespringbootconsumer.service;

import com.aixming.common.model.User;
import com.aixming.common.service.UserService;
import com.aixming.literpcspringbootstarter.annotation.RpcReference;
import org.springframework.stereotype.Service;

/**
 * @author AixMing
 * @since 2025-01-15 16:51:09
 */
@Service
public class ExampleServiceImpl {

    @RpcReference
    private UserService userService;

    public void test() {
        User user = new User();
        user.setName("AixMing");
        User newUser = userService.getUser(user);
        System.out.println(newUser.getName());
    }

}
