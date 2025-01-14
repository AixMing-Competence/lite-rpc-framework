package com.aixming.rpc.fault.tolerant;

import com.aixming.rpc.model.RpcResponse;

import java.util.Map;

/**
 * 故障转移 - 容错策略（切换其他节点调用）
 *
 * @author AixMing
 * @since 2025-01-14 20:37:09
 */
public class FailOverTolerantStrategy implements TolerantStrategy {

    @Override
    public RpcResponse doTolerant(Map<String, Object> context, Exception e) {
        // 获取其他服务节点进行调用
        return null;
    }

}
