package com.one;

import java.util.concurrent.TimeUnit;

/**
 * *************************************************<br/>
 * 创建线程 runable<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/10/29 15:17
 */
public class ThreadRunnableTest {

    public static void main(String[] args) {
        Thread thread = new Thread(new Task());
        thread.setName("测试线程");
        thread.start();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            System.out.println("线程运行，线程名称为:" + Thread.currentThread().getName());
        }
    }
}

