package com.one;

import java.util.concurrent.TimeUnit;

/**
 * *************************************************<br/>
 * 守护线程测试
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/10/29 16:18
 */
public class DaemonThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        thread.setName("测试守护线程");
        thread.setDaemon(true);
        thread.start();
    }

    private static class Task implements Runnable{

        @Override
        public void run() {
            try {
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("线程运行，线程名称为:" + Thread.currentThread().getName());
        }
    }
}
