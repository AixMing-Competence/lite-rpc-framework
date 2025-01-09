package com.aixming.rpc.server.tcp;

import com.aixming.rpc.protocol.ProtocolConstant;
import io.vertx.core.Handler;
import io.vertx.core.buffer.Buffer;
import io.vertx.core.parsetools.RecordParser;

/**
 * 装饰者模式（使用 recordBuffer 对原有的 buffer 处理能力增强）
 *
 * @author AixMing
 * @since 2025-01-07 21:18:35
 */
public class TcpBufferHandlerWrapper implements Handler<Buffer> {

    private final RecordParser recordParser;

    public TcpBufferHandlerWrapper(Handler<Buffer> bufferHandler) {
        recordParser = initRecordParser(bufferHandler);
    }

    @Override
    public void handle(Buffer buffer) {
        recordParser.handle(buffer);
    }

    private RecordParser initRecordParser(Handler<Buffer> bufferHandler) {
        // 构造 parser
        RecordParser parser = RecordParser.newFixed(ProtocolConstant.MESSAGE_HEAD_LENGTH);

        parser.setOutput(new Handler<>() {
            int size = -1;
            // 一次完整的读取（头 + 体）
            Buffer resultBuffer = Buffer.buffer();

            @Override
            public void handle(Buffer buffer) {
                if (size == -1) {
                    size = buffer.getInt(13);
                    // 修改读取的长度
                    parser.fixedSizeMode(size);
                    // 写入头信息
                    resultBuffer.appendBuffer(buffer);
                } else {
                    // 写入体信息
                    resultBuffer.appendBuffer(buffer);
                    // 已拼接为完整的 buffer，执行处理
                    bufferHandler.handle(resultBuffer);
                    // 重置下一轮读取状态
                    parser.fixedSizeMode(ProtocolConstant.MESSAGE_HEAD_LENGTH);
                    size = -1;
                    resultBuffer = Buffer.buffer();
                }
            }
        });

        return parser;
    }
}
