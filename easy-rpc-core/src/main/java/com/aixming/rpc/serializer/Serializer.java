package com.aixming.rpc.serializer;

import java.io.IOException;

/**
 * 序列化器接口
 *
 * @author AixMing
 * @since 2024-12-11 19:33:49
 */
public interface Serializer {

    /**
     * 序列化
     *
     * @param object
     * @param <T>
     * @return
     */
    <T> byte[] serialize(T object) throws IOException;

    /**
     * 反序列化
     *
     * @param bytes
     * @param type
     * @param <T>
     * @return
     */
    <T> T deSerialize(byte[] bytes, Class<T> type) throws IOException;
}
