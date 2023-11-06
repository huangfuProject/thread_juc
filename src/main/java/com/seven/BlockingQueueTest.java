package com.seven;

import java.util.PriorityQueue;
import java.util.concurrent.*;

/**
 * *************************************************<br/>
 * 阻塞队列<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/11/6 20:41
 */
public class BlockingQueueTest {
    private final static ArrayBlockingQueue<String> ARRAY_BLOCKING_QUEUE = new ArrayBlockingQueue<String>(5);
    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(4, 8, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) {
        //触发排队操作  这里实际生产环境不推荐两种任务共同使用一个线程池 演示使用
        THREAD_POOL_EXECUTOR.execute(new QueueUp());

        //三个服务员开始接待客人吃饭
        for (int i = 0; i < 3; i++) {
            THREAD_POOL_EXECUTOR.execute(new EatingTask());
        }

    }

    private static class QueueUp implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    String customerName = "顾客" + i;
                    System.out.println("顾客" + customerName + "开始排队");
                    ARRAY_BLOCKING_QUEUE.put(customerName);

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static class EatingTask implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    String thisCustomerName = ARRAY_BLOCKING_QUEUE.take();
                    System.out.println("顾客" + thisCustomerName + "排队成功，进入餐厅开始吃饭");
                    Thread.sleep((long) (Math.random() * 10000));
                    System.out.println("顾客" + thisCustomerName + "吃完离开了.");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
