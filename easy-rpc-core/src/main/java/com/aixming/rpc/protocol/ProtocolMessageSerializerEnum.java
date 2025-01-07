package com.aixming.rpc.protocol;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 协议消息序列化器枚举
 *
 * @author AixMing
 * @since 2025-01-05 22:01:15
 */
@Getter
public enum ProtocolMessageSerializerEnum {

    /**
     * jdk 序列化器
     */
    JDK(0, "jdk"),
    /**
     * json 序列化器
     */
    JSON(1, "json"),
    /**
     * Kryo 序列化器
     */
    KRYO(2, "kryo"),
    /**
     * Hessian 序列化器
     */
    HESSIAN(3, "hessian");

    private final int key;

    private final String value;

    ProtocolMessageSerializerEnum(int key, String value) {
        this.key = key;
        this.value = value;
    }

    /**
     * 获取 value 列表
     *
     * @return
     */
    public static List<String> getValues() {
        return Arrays.stream(values()).map(ProtocolMessageSerializerEnum::getValue).collect(Collectors.toList());
    }

    /**
     * 根据 key 获取枚举
     *
     * @param key
     * @return
     */
    public static ProtocolMessageSerializerEnum getEnumByKey(int key) {
        for (ProtocolMessageSerializerEnum anEnum : values()) {
            if (anEnum.key == key) {
                return anEnum;
            }
        }
        return null;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static ProtocolMessageSerializerEnum getEnumByValue(String value) {
        for (ProtocolMessageSerializerEnum anEnum : values()) {
            if (anEnum.value.equals(value)) {
                return anEnum;
            }
        }
        return null;
    }

}
