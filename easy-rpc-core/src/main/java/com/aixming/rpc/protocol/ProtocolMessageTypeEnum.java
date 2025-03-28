package com.aixming.rpc.protocol;

import lombok.Getter;

/**
 * 协议消息类型枚举
 *
 * @author AixMing
 * @since 2025-01-05 21:54:00
 */
@Getter
public enum ProtocolMessageTypeEnum {

    /**
     * 请求
     */
    REQUEST(0),

    /**
     * 响应
     */
    RESPONSE(1),

    /**
     * 心跳检测
     */
    HEART_BEAT(2),

    /**
     * 其他类型
     */
    OTHERS(3);

    private final int key;

    ProtocolMessageTypeEnum(int key) {
        this.key = key;
    }

    /**
     * 根据 key 获取枚举
     *
     * @param key
     * @return
     */
    public static ProtocolMessageTypeEnum getEnumByKey(int key) {
        for (ProtocolMessageTypeEnum anEnum : values()) {
            if (anEnum.key == key) {
                return anEnum;
            }
        }
        return null;
    }

}
