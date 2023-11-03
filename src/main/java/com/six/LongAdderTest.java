package com.six;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.LongAdder;

/**
 * @author huangfukexing
 * @date 2023/11/3 17:47
 */
public class LongAdderTest {
    protected static LongAdder longAdder = new LongAdder();

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        Thread thread1 = new Thread(new Task());

        thread.start();
        thread1.start();
        thread.join();
        thread1.join();

        System.out.println(longAdder.sum());
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                //累加并返回
                longAdder.increment();
            }
        }
    }
}
