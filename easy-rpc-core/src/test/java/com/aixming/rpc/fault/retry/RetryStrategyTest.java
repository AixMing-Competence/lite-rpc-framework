package com.aixming.rpc.fault.retry;

import com.aixming.rpc.model.RpcResponse;
import org.junit.Test;

/**
 * @author AixMing
 * @since 2025-01-14 19:44:08
 */
public class RetryStrategyTest {

    private RetryStrategy retryStrategy = new FixedIntervalRetryStrategy();

    @Test
    public void retryStrategyTest() {
        try {
            RpcResponse rpcResponse = retryStrategy.doRetry(() -> {
                System.out.println("测试重试");
                throw new RuntimeException("模拟重试失败");
            });
        } catch (Exception e) {
            System.out.println("重试多次失败");
            e.printStackTrace();
        }
    }

}