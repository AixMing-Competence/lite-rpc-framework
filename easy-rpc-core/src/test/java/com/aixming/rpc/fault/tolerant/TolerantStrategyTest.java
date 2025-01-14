package com.aixming.rpc.fault.tolerant;

import com.aixming.rpc.model.RpcResponse;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author AixMing
 * @since 2025-01-14 20:58:52
 */
public class TolerantStrategyTest {

    private TolerantStrategy tolerantStrategy = new FailSafeTolerantStrategy();

    @Test
    public void doTolerant() {
        RpcResponse rpcResponse = new RpcResponse("sjdskjdks", null, null, null);
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            rpcResponse = tolerantStrategy.doTolerant(null, e);
        }
        System.out.println(rpcResponse);
    }

}