package com.aixming.rpc.protocol;

import cn.hutool.core.util.IdUtil;
import com.aixming.rpc.constant.RpcConstant;
import com.aixming.rpc.model.RpcRequest;
import io.vertx.core.buffer.Buffer;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author AixMing
 * @since 2025-01-07 17:12:35
 */
public class ProtocolMessageTest {

    @Test
    public void ProtocolMessageEncodeAndDecodeTest() throws IOException {
        ProtocolMessage<RpcRequest> protocolMessage = new ProtocolMessage<>();
        ProtocolMessage.Header header = new ProtocolMessage.Header();
        header.setMagic(ProtocolConstant.MESSAGE_MAGIC);
        header.setVersion(ProtocolConstant.PROTOCOL_VERSION);
        header.setSerializer((byte) ProtocolMessageSerializerEnum.JDK.getKey());
        header.setType((byte) ProtocolMessageTypeEnum.REQUEST.getKey());
        header.setStatus((byte) ProtocolMessageStatusEnum.ok.getValue());
        header.setRequestId(IdUtil.getSnowflakeNextId());
        header.setBodyLength(0);

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setServiceName("myService");
        rpcRequest.setMethodName("myMethod");
        rpcRequest.setServiceVersion(RpcConstant.DEFAULT_SERVICE_VERSION);
        rpcRequest.setParameterTypes(new Class[]{String.class});
        rpcRequest.setArgs(new Object[]{"aaa", "bbb"});
        
        protocolMessage.setHeader(header);
        protocolMessage.setBody(rpcRequest);

        Buffer buffer = ProtocolMessageEncoder.encode(protocolMessage);
        ProtocolMessage<?> decodeProtocolMessage = ProtocolMessageDecoder.decode(buffer);
        Assert.assertNotNull(decodeProtocolMessage);
    }

}
