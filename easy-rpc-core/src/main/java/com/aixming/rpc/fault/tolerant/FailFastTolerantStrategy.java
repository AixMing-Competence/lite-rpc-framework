package com.aixming.rpc.fault.tolerant;

import com.aixming.rpc.model.RpcResponse;

import java.util.Map;

/**
 * 快速失败 - 容错策略（立即通知外层调用方）
 *
 * @author AixMing
 * @since 2025-01-14 20:37:09
 */
public class FailFastTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        throw new RuntimeException("服务报错", e);
    }

}
