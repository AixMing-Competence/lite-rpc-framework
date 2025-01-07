package com.aixming.rpc.protocol;

/**
 * 协议常量
 *
 * @author AixMing
 * @since 2025-01-05 21:45:04
 */
public interface ProtocolConstant {

    /**
     * 消息头长度
     */
    int MESSAGE_HEAD_LENGTH = 17;

    /**
     * 消息魔数
     */
    byte MESSAGE_MAGIC = 0x1;

    /**
     * 协议版本号
     */
    byte PROTOCOL_VERSION = 0x1;

}
