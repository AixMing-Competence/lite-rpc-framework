package com.aixming.rpc.fault.tolerant;

import com.aixming.rpc.model.RpcResponse;

import java.util.Map;

/**
 * 故障恢复 - 容错策略（降级到其他服务）
 *
 * @author AixMing
 * @since 2025-01-14 20:37:09
 */
public class FailBackTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // 获取降级服务进行调用
        return null;
    }

}
