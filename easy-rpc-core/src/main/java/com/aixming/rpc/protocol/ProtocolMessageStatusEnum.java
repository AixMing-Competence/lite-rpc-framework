package com.aixming.rpc.protocol;

import lombok.Getter;

/**
 * 协议消息状态枚举
 *
 * @author AixMing
 * @since 2025-01-05 21:48:34
 */
@Getter
public enum ProtocolMessageStatusEnum {

    /**
     * 成功
     */
    ok("ok", 20),

    /**
     * 请求失败
     */
    bad_request("bad_request", 40),

    /**
     * 响应失败
     */
    bad_response("bad_response", 50);

    private final String text;

    private final int value;

    ProtocolMessageStatusEnum(String text, int value) {
        this.text = text;
        this.value = value;
    }

    /**
     * 根据 value 获取枚举
     *
     * @param value
     * @return
     */
    public static ProtocolMessageStatusEnum getEnumByValue(int value) {
        for (ProtocolMessageStatusEnum anEnum : values()) {
            if (anEnum.value == value) {
                return anEnum;
            }
        }
        return null;
    }
}
