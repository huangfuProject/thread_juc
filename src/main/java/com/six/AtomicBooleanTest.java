package com.six;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author huangfukexing
 * @date 2023/11/3 15:25
 */
public class AtomicBooleanTest {
    protected static AtomicBoolean atomicBoolean = new AtomicBoolean(false);

    public static void main(String[] args) {
        new Thread(new Task()).start();
        new Thread(new Task()).start();
        new Thread(new Task()).start();
        new Thread(new Task()).start();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            if (atomicBoolean.compareAndSet(false, true)) {
                System.out.println(Thread.currentThread().getName() + "开始启动服务");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "启动服务占用8080端口");
            }else {
                System.out.println(Thread.currentThread().getName() + "服务已经被启动了，无须在再次启动");
            }

        }
    }
}
