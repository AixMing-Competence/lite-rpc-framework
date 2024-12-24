package com.aixming.rpc.serializer;

import java.util.HashMap;
import java.util.Map;

/**
 * 序列化器工厂（用于获取序列化器对象）
 * 工厂 + 单例模式
 *
 * @author AixMing
 * @since 2024-12-24 17:04:29
 */
public class SerializerFactory {

    /**
     * 序列化器映射（用于实现单例）
     */
    private static final Map<String, Serializer> KEY_SERIALIZER_MAP;

    static {
        KEY_SERIALIZER_MAP = new HashMap<>();
        KEY_SERIALIZER_MAP.put(SerializerKeys.JDK, new JdkSerializer());
        KEY_SERIALIZER_MAP.put(SerializerKeys.JSON, new JsonSerializer());
        KEY_SERIALIZER_MAP.put(SerializerKeys.KRYO, new KryoSerializer());
        KEY_SERIALIZER_MAP.put(SerializerKeys.HESSIAN, new HessianSerializer());
    }

    /**
     * 默认序列化器
     */
    private static final Serializer DEFAULT_SERIALIZER = KEY_SERIALIZER_MAP.get(SerializerKeys.JDK);

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static Serializer getInstance(String key) {
        return KEY_SERIALIZER_MAP.getOrDefault(key, DEFAULT_SERIALIZER);
    }

}
