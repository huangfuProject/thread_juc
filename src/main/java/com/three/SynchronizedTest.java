package com.three;

/**
 * @author huangfukexing
 * @date 2023/10/31 12:56
 */
public class SynchronizedTest {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(task, "线程1");
        Thread thread2 = new Thread(task, "线程2");

        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("最终的计算结果"+task.getCount());
    }

    private static class Task implements Runnable {
        int count = 0;
        @Override
        public synchronized void run() {
            for (int j = 0; j < 100000; j++) {
                count++;
            }
        }

        public int getCount() {
            return count;
        }
    }
}
