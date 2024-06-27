package com.seven;

import java.util.concurrent.SynchronousQueue;

/**
 * @author huangfukexing
 * @date 2024/3/1 13:50
 */
public class SynchronousQueueTest {

    public static void main(String[] args) throws InterruptedException {
        SynchronousQueue<String> synchronousQueue = new SynchronousQueue<>();

        //模拟生产者
        new Thread(() ->{
            while (true) {
                try {
                    synchronousQueue.put("你好");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();


        //模拟消费者
        new Thread(() ->{
            while (true) {
                try {
                    Thread.sleep(1000);
                    System.out.println(synchronousQueue.take());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }
}
