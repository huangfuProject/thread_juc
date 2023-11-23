package com.four;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch简单使用
 *
 * @author huangfukexing
 * @date 2023/11/23 15:41
 */
public class CountDownLatchTest {

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 10; i++) {
            new Thread(() ->{

                try {
                    System.out.println("线程：" +Thread.currentThread().getName()  + "开始执行。");
                    Thread.sleep((long) (Math.random() + 10000));
                    System.out.println("线程：" +Thread.currentThread().getName()  + "执行完成.");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }


            }).start();
        }
        System.out.println("开始等待10个线程都完成任务...");
        countDownLatch.await();
        System.out.println("线程全部执行完毕");
    }
}
