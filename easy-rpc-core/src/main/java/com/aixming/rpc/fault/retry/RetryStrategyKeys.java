package com.aixming.rpc.fault.retry;

/**
 * 重试策略键名
 *
 * @author AixMing
 * @since 2025-01-14 19:50:43
 */
public interface RetryStrategyKeys {

    /**
     * 不重试
     */
    String NO = "no";

    /**
     * 固定间隔重试
     */
    String FIXED_INTERVAL = "fixedInterval";

}
