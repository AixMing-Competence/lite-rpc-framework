package com.aixming.common.service;

import com.aixming.common.model.User;

/**
 * 用户服务
 *
 * @author AixMing
 * @since 2024-12-11 17:39:56
 */
public interface UserService {

    /**
     * 获取用户
     *
     * @param user
     * @return
     */
    User getUser(User user);
}
