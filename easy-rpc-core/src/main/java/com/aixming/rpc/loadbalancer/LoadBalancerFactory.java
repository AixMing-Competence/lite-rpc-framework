package com.aixming.rpc.loadbalancer;

import com.aixming.rpc.spi.SpiLoader;

/**
 * 负载均衡器工厂
 *
 * @author AixMing
 * @since 2025-01-10 16:45:39
 */
public class LoadBalancerFactory {
    
    static {
        // SPI 加载负载均衡器
        SpiLoader.load(LoadBalancer.class);
    }

    /**
     * 默认负载均衡器
     */
    private static final LoadBalancer DEFAULT_LOAD_BALANCER = new RoundRobinLoadBalancer();

    public static LoadBalancer getInstance(String key) {
        return SpiLoader.getInstance(LoadBalancer.class, key);
    }

}
