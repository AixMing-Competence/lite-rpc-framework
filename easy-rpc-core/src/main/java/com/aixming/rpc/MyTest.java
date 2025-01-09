package com.aixming.rpc;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 测试类
 *
 * @author AixMing
 * @since 2024-12-23 18:26:56
 */
public class MyTest {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        CompletableFuture<String> future1 = new CompletableFuture<>();
        future1.complete("aksdjksa");
    }

}
