package com.four;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * 循环栅栏
 * @author huangfukexing
 * @date 2023/11/1 14:30
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(2, new Runnable() {
            @Override
            public void run() {
                System.out.println("adsdadas");
            }
        });

        Thread thread1 = new Thread(() -> {
            try {
                // 模拟线程1在等待时被中断
                //Thread.currentThread().interrupt(); // 中断线程1
                System.out.println("线程1阻塞");
                barrier.await(3, TimeUnit.SECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
                // 线程2等待
                barrier.await();
                System.out.println("ewqeq");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        thread1.start();
        thread2.start();

        try {

            // 主线程等待线程1和线程2完成
            thread1.join();
            barrier.reset();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (barrier.isBroken()) {
            System.out.println("1111CyclicBarrier is broken.");
        } else {
            System.out.println("CyclicBarrier is not broken.");
        }
    }
}
