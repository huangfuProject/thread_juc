package com.six;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huangfukexing
 * @date 2023/11/3 15:10
 */
public class AtomicIntegerTest {
    protected static AtomicInteger atomicInteger = new AtomicInteger();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        Thread thread1 = new Thread(new Task());

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        System.out.println(atomicInteger.get());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                //累加并返回
                atomicInteger.incrementAndGet();
            }
        }
    }
}
