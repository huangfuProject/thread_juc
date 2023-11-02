package com.five;

/**
 * @author huangfukexing
 * @date 2023/11/2 17:47
 */
public class ChildThreadLocal {
    private final static ThreadLocal<Integer> THREAD_LOCAL = new ThreadLocal<>();

    public static void main(String[] args) {
        THREAD_LOCAL.set(1);
        System.out.println("线程：" + Thread.currentThread().getName() + "获取到数据为:" + THREAD_LOCAL.get());
        //获取主线程的值
        Integer integer = THREAD_LOCAL.get();
        new Thread(()->{
            //设置到子线程
            THREAD_LOCAL.set(integer);
            System.out.println("线程：" + Thread.currentThread().getName() + "获取到数据为:" + THREAD_LOCAL.get());
        }, "子线程").start();
    }
}
