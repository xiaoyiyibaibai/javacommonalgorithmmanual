package com.algorithm.completablefuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @ClassName BasicFuture
 * @Description TODO
 * @Author renhao
 * @Date 2019/12/20 11:20
 **/
public class BasicFuture {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService es = Executors.newFixedThreadPool(10);
        Future<Integer> f = es.submit(() ->{
            // 长时间的异步计算
            // ……
            // 然后返回结果
            return 100;
        });
//        while(!f.isDone())
//            ;
        f.get();
    }
}
