package com.algorithm.controller;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * @ClassName CompletableFutureDemo2
 * @Description https://www.jianshu.com/p/73aaec23009d
 * @Author renhao
 * @Date 2019/12/20 9:30
 **/
public class CompletableFutureDemo2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        doCompletableFuture();
    }

    public static void doCompletableFuture() throws ExecutionException, InterruptedException {
        CompletableFuture future = new CompletableFuture();
        // 在 Java8 中，推荐使用 Lambda 来替代匿名 Runnable 实现类
        new Thread(
                () -> {
                    try {
                        // 模拟一段耗时的操作
                        Thread.sleep(2000);

                        future.complete("I have completed");
                    } catch (Exception e) {
                    }
                }
        ).start();

        System.out.println(future.get());
    }

}
