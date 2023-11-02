package com.five;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author huangfukexing
 * @date 2023/11/2 17:06
 */
public class ThreadLocalOom {
    private final static ThreadLocal<Object> threadLocal = new ThreadLocal<>();
    public static void main(String[] args) {
        while (true) {
            Object data = new Object();
            // 将对象存储在ThreadLocal中
            threadLocal.set(data);
        }
    }
}
