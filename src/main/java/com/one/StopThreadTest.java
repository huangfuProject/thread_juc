package com.one;

import java.util.concurrent.TimeUnit;

/**
 * *************************************************<br/>
 * 停止线程
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/10/29 16:48
 */
public class StopThreadTest {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new Task());
        thread.setName("测试线程");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        //发出一个停止信号
        thread.interrupt();
    }

    private static class Task implements Runnable {

        @Override
        public void run() {
            //验证停止信号是否已经停止
            while (!Thread.currentThread().isInterrupted()) {
                System.out.println("我执行了");
            }
        }
    }
}
