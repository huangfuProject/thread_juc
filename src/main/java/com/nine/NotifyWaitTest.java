package com.nine;

/**
 * @author huangfukexing
 * @date 2023/11/17 17:43
 */
public class NotifyWaitTest {

    public static void main(String[] args) {
        Object o = new Object();

        new Thread(new WaitTask(o)).start();
        new Thread(new NotifyTask(o)).start();
    }

    private static class NotifyTask implements Runnable {

        private final Object moniter;

        private NotifyTask(Object moniter) {
            this.moniter = moniter;
        }

        @Override
        public void run() {
            synchronized (moniter) {
                System.out.println(Thread.currentThread() + "开始运行");
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "开始通知解除阻塞");
                moniter.notify();
            }
        }
    }

    private static class WaitTask implements Runnable {
        private final Object moniter;

        private WaitTask(Object moniter) {
            this.moniter = moniter;
        }

        @Override
        public void run() {
            synchronized (moniter) {
                System.out.println(Thread.currentThread() + "开始进入阻塞状态");
                try {
                    moniter.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread() + "阻塞被解开");
            }
        }
    }
}
