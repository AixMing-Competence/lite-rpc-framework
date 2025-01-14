package com.aixming.rpc.fault.tolerant;

import com.aixming.rpc.model.RpcResponse;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 快速静默 - 容错策略
 *
 * @author AixMing
 * @since 2025-01-14 20:37:09
 */
@Slf4j
public class FailSafeTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        log.error("服务异常静默处理", e);
        return new RpcResponse();
    }

}
