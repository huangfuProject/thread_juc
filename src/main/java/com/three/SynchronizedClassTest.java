package com.three;

/**
 * @author huangfukexing
 * @date 2023/10/31 12:56
 */
public class SynchronizedClassTest {

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task();
        Thread thread1 = new Thread(task, "线程1");
        Thread thread2 = new Thread(task, "线程2");
        long currentTimeMillis = System.currentTimeMillis();
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println("总耗时:" + (System.currentTimeMillis() - currentTimeMillis));

    }

    private static class Task implements Runnable {
        @Override
        public  void run() {
            try {
                Task.simulate();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public static  void simulate() throws InterruptedException {
            System.out.println(Thread.currentThread().getName() + ": 开始查询数据库");
            //模拟耗时
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + ": 查询数据库结束");
            synchronized (SynchronizedClassTest.class) {
                System.out.println(Thread.currentThread().getName() + ": 开始修改临界区数据");
                //修改临界区数据操作 模拟耗时
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + ": 修改临界区数据结束");
            }
            System.out.println(Thread.currentThread().getName() + ": 开始执行剩余的逻辑");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + ": 执行剩余的逻辑成功");
        }
    }
}
