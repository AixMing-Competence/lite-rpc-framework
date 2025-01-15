package com.aixming.literpcspringbootstarter.annotation;

import com.aixming.rpc.constant.RpcConstant;
import com.aixming.rpc.fault.retry.RetryStrategyKeys;
import com.aixming.rpc.fault.tolerant.TolerantStrategyKeys;
import com.aixming.rpc.loadbalancer.LoadBalancerKeys;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 服务消费者注解（用于注入服务）
 *
 * @author AixMing
 * @since 2025-01-15 14:26:38
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface RpcReference {

    /**
     * 服务接口类
     *
     * @return
     */
    Class<?> interfaceClass() default void.class;

    /**
     * 版本
     *
     * @return
     */
    String serviceVersion() default RpcConstant.DEFAULT_SERVICE_VERSION;

    /**
     * 负载均衡器
     *
     * @return
     */
    String loadBalancer() default LoadBalancerKeys.ROUND_ROBIN;

    /**
     * 重试策略
     *
     * @return
     */
    String retryStrategy() default RetryStrategyKeys.NO;

    /**
     * 容错策略
     *
     * @return
     */
    String tolerantStrategy() default TolerantStrategyKeys.FAIL_FAST;

    /**
     * 模拟调用
     *
     * @return
     */
    boolean mock() default false;

}
