package com.algorithm.controller;

import jdk.internal.org.objectweb.asm.commons.TryCatchBlockSorter;

import java.sql.Driver;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @ClassName CompletableFutureDemo
 * @Description TODO
 * @Author renhao
 * @Date 2019/12/19 15:35
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        doFuture8();

    }
    public static void doFuture1(){
        CompletableFuture<String> f = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().submit(()->{
            f.complete("hello");
            //f.completeExceptionally(new RuntimeException("error"));
        });

        try {
            String result = f.get();
            System.out.println(result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void doFuture2(){
        CompletableFuture<String> f = new CompletableFuture<>();

        new Thread(() -> {
            try {
                System.out.println("thread1:" + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                System.out.println("thread2:" + f.get());
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }).start();
       // complete和completeExceptionally方法可以控制结果的结束：
        f.complete("hello");
    }
/**
 * @Author xiaodongohong
 * @Description 创建异步操作的方法主要是：
 *
 * public static CompletableFuture<Void> runAsync(Runnable runnable)
 * public static CompletableFuture<Void> runAsync(Runnable runnable,Executor executor)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier)
 * public static <U> CompletableFuture<U> supplyAsync(Supplier<U> supplier,Executor executor)
 *
 * @Date 9:02 2019/12/20
 * @Param []
 * @return void
 **/
    public static void doFuture3() throws Exception{
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(14000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "hello";
        });

        String result = f.get();

        System.out.println(result);
    }
    /**
     * @Author xiaodongohong
     * @Description 连续异步操作
     * public CompletableFuture<Void> thenRun(Runnable action)
     * public CompletableFuture<Void> thenRunAsync(Runnable action)
     * public CompletableFuture<Void> thenRunAsync(Runnable action,Executor executor)
     * public <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
     * public <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     * public CompletableFuture<Void> thenAccept(Consumer<? super T> action)
     * public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action)
     * public CompletableFuture<Void> thenAcceptAsync(Consumer<? super T> action,Executor executor)
     *
     * @Date 9:01 2019/12/20
     * @Param []
     * @return void
     **/
    public static void doFuture4() throws Exception{
        CompletableFuture<Void> f = CompletableFuture
                .supplyAsync(() -> {

                    try {
                        Thread.sleep(14000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return  "hello";})
                .thenApplyAsync(res -> res + " world!")
                .thenAcceptAsync(System.out::println);

        f.get();

        int sum =0;
        for (int i=0;i<100;i++){
            sum = sum+i;
        }
        System.out.println( "sum=" + sum );
    }

    /**
     * 等待操作完成
     * public CompletableFuture<T> whenComplete(BiConsumer<? super T, ? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action)
     * public CompletableFuture<T> whenCompleteAsync(BiConsumer<? super T, ? super Throwable> action, Executor executor)
     **/
    public static void doFuture5() throws Exception{
        CompletableFuture<String> f = CompletableFuture
                .supplyAsync(() -> {
                    try {
                        Thread.sleep(14000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    return  "hello";})
                .thenApplyAsync(res -> res + " world!")
                .whenComplete((res, err) -> {
                    if (err != null) {
                        err.printStackTrace();
                    } else {
                        System.out.println(res+"结果");
                    }
                });

        f.get();
    }
    // 组合
    public static void doFuture6() throws Exception{
        CompletableFuture<String> f = CompletableFuture.supplyAsync(() -> "Hello")
                .thenCompose(res -> CompletableFuture.supplyAsync(() -> res + " World,"))
                .thenCombine(CompletableFuture.supplyAsync(() -> "CompletableFuture!"), (a, b) -> a + b);

        String result = f.get();

        System.out.println(result);//Hello World,CompletableFuture!
    }
    // 结果&异常处理
    public static void doFuture7() throws Exception{
      // 异常处理
        CompletableFuture<Object> f = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApplyAsync(res -> res + "World")
                .thenApplyAsync(res -> {
                    throw new RuntimeException("error");
                })
                .exceptionally(e -> {
                    //handle exception here
                  //  e.printStackTrace();
                    return "异常信息"+e.getMessage();
                });

        Object result2 = f.get();
        System.out.println(result2);

       // 执行结果处理
        CompletableFuture<Object> f2 = CompletableFuture.supplyAsync(() -> "Hello")
                .thenApplyAsync(res -> res + "World")
                .thenApplyAsync(res -> {
                    throw new RuntimeException("error");
                })
                .handleAsync((res, err) -> {
                    if (err != null) {
                        //handle exception here
                        return null;
                    } else {
                        return res;
                    }
                });

        Object result = f2.get();
        System.out.println(result);
    }

      // 并行执行异步操作并统一处理结果
    public static void doFuture8() throws Exception{
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> "hello");
        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> "world");
        CompletableFuture<String> f3 = CompletableFuture.supplyAsync(() -> "!");

       // 使用allOf方法
        CompletableFuture<Void> all = CompletableFuture.allOf(f1, f2, f3);
        Object object=  all.get();
        System.out.println("object === "+object);
        System.out.println("f1 === "+f1.get());
        System.out.println("f2 === "+f2.get());
        System.out.println("f3 === "+f3.get());

        // 结合StreamAPI
        List<String> result = Stream.of(f1, f2, f3)
                .map(CompletableFuture::join)
                .collect( Collectors.toList());

        System.out.println("Stream = "+result);
    }
}
