package com.aixming.rpc.fault.tolerant;

import com.aixming.rpc.spi.SpiLoader;

/**
 * 容错策略对象工厂
 *
 * @author AixMing
 * @since 2025-01-14 20:49:19
 */
public class TolerantStrategyFactory {

    static {
        SpiLoader.load(TolerantStrategy.class);
    }

    /**
     * 默认容错策略
     */
    private static final TolerantStrategy DEFAULT_TOLERANT_STRATEGY = new FailFastTolerantStrategy();

    /**
     * 获取实例
     *
     * @param key
     * @return
     */
    public static TolerantStrategy getInstance(String key) {
        return SpiLoader.getInstance(TolerantStrategy.class, key);
    }

}
