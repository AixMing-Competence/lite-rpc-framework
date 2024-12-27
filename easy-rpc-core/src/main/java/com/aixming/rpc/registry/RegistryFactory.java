package com.aixming.rpc.registry;

import com.aixming.rpc.spi.SpiLoader;

/**
 * 注册中心工厂（用户获取注册中心对象）
 *
 * @author AixMing
 * @since 2024-12-26 21:15:43
 */
public class RegistryFactory {

    static {
        SpiLoader.load(Registry.class);
    }

    /**
     * 默认注册中心
     */
    private static final Registry DEFAULT_REGISTRY = new EtcdRegistry();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Registry getInstance(String key) {
        return SpiLoader.getInstance(Registry.class, key);
    }
    
}
