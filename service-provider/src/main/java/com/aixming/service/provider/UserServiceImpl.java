package com.aixming.service.provider;

import com.aixming.common.model.User;
import com.aixming.common.service.UserService;

/**
 * 用户服务实现类
 *
 * @author AixMing
 * @since 2024-12-11 17:52:52
 */
public class UserServiceImpl implements UserService {
    @Override
    public User getUser(User user) {
        System.out.println("用户名：" + user.getName());
        return user;
    }
}
