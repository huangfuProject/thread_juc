package com.four;

import java.util.concurrent.*;

/**
 * 信号量
 * @author huangfukexing
 * @date 2023/11/1 16:44
 */
public class SemaphoreTest {

    private static final Semaphore SEMAPHORE = new Semaphore(2, true);
    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws Exception {

        for (int i = 0; i < 50; i++) {
            EXECUTOR.execute(new Task());
        }

    }

    private static class Task implements Runnable {
        @Override
        public void run() {

            try {
                //申请许可证
                SEMAPHORE.acquire();
                System.out.println(Thread.currentThread().getName() + "获取到了许可证，开始运行. ");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(Thread.currentThread().getName() + "运行结束. ");


            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                //释放许可证
                SEMAPHORE.release();
            }
        }
    }
}
