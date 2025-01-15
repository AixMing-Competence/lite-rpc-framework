package com.aixming.example.provider.examplespringbootprovider.service;

import com.aixming.common.model.User;
import com.aixming.common.service.UserService;
import com.aixming.literpcspringbootstarter.annotation.RpcService;
import org.springframework.stereotype.Service;

/**
 * @author AixMing
 * @since 2025-01-15 16:47:45
 */
@Service
@RpcService
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
