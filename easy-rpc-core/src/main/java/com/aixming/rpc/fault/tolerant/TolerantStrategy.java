package com.aixming.rpc.fault.tolerant;

import com.aixming.rpc.model.RpcResponse;

import java.util.Map;

/**
 * 容错策略接口
 *
 * @author AixMing
 * @since 2025-01-14 20:34:17
 */
public interface TolerantStrategy {

    /**
     * 容错
     *
     * @param context 上下文，用于传递数据
     * @param e       异常
     * @return
     */
    RpcResponse doTolerant(Map<String, Object> context, Exception e);

}
