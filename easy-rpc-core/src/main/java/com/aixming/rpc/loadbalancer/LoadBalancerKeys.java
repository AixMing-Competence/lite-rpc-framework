package com.aixming.rpc.loadbalancer;

/**
 * 负载均衡器键名
 *
 * @author AixMing
 * @since 2025-01-10 16:42:38
 */
public interface LoadBalancerKeys {

    /**
     * 轮询
     */
    String ROUND_ROBIN = "roundRobin";

    /**
     * 随机
     */
    String RANDOM = "random";

    /**
     * 一致性哈希
     */
    String CONSISTENT_HASH = "consistentHash";

}
