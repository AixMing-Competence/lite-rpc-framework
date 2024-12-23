package com.aixming.rpc.proxy;

import com.aixming.common.model.User;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Mock 服务代理（JDK 动态代理）
 *
 * @author AixMing
 * @since 2024-12-22 19:10:16
 */
@Slf4j
public class MockServiceProxy implements InvocationHandler {

    /**
     * 调用代理
     *
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 根据方法的返回值类型，生成特定的默认值对象
        Class<?> methodReturnType = method.getReturnType();
        log.info("mock invoke {}", method.getName());
        return getDefaultObject(methodReturnType);
    }

    /**
     * 生成特定类型的默认值对象（可自行完善默认值逻辑）
     *
     * @param type
     * @return
     */
    public Object getDefaultObject(Class<?> type) {
        // 返回基本数据类型默认值
        if (type.isPrimitive()) {
            if (type == boolean.class) {
                return false;
            } else if (type == int.class) {
                return 0;
            } else if (type == short.class) {
                return (short) 0;
            } else if (type == long.class) {
                return 0;
            }
        }
        // 返回对象类型默认值
        if (User.class.isAssignableFrom(type)) {
            User user = new User();
            user.setName("aixminghhhhhhh");
            return user;
        }
        return null;
    }
}
