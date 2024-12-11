package com.aixming.rpc.registry;

import java.util.concurrent.ConcurrentHashMap;

/**
 * 本地注册中心
 *
 * @author AixMing
 * @since 2024-12-11 19:19:20
 */
public class LocalRegistry {

    /**
     * 注册信息存储
     */
    private static final ConcurrentHashMap<String, Class<?>> map = new ConcurrentHashMap<>();

    /**
     * 注册服务
     *
     * @param serviceName
     * @param implClass
     */
    public static void register(String serviceName, Class<?> implClass) {
        map.put(serviceName, implClass);
    }

    /**
     * 获取服务
     *
     * @param serviceName
     * @return
     */
    public static Class<?> get(String serviceName) {
        return map.get(serviceName);
    }

    /**
     * 删除服务
     *
     * @param serviceName
     */
    public static void remove(String serviceName) {
        map.remove(serviceName);
    }
}
