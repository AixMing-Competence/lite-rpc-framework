package com.aixming.rpc;

import com.aixming.rpc.serializer.Serializer;

import java.util.ServiceLoader;

/**
 * 测试类
 *
 * @author AixMing
 * @since 2024-12-23 18:26:56
 */
public class MyTest {

    public static void main(String[] args) {
        Serializer serializer = null;
        ServiceLoader<Serializer> serializerServiceLoader = ServiceLoader.load(Serializer.class);
        for (Serializer service : serializerServiceLoader) {
            serializer = service;
        }
        System.out.println(serializer);
    }

}
