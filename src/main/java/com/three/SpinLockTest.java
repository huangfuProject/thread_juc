package com.three;

/**
 * *************************************************<br/>
 * 自旋锁<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/10/31 20:39
 */
import java.util.concurrent.atomic.AtomicBoolean;

public class SpinLockTest {
    private AtomicBoolean locked = new AtomicBoolean(false);

    /**
     * 自旋锁加锁操作
     */
    public void lock() {
        while (!locked.compareAndSet(false, true)) {
            // 自旋等待锁释放
        }
    }

    /**
     * 自旋锁 解锁操作
     */
    public void unlock() {
        locked.set(false);
    }

    public static void main(String[] args) {
        SpinLockTest spinLock = new SpinLockTest();
        Task task = new Task(spinLock);

        Thread thread1 = new Thread(task, "线程1");
        Thread thread2 = new Thread(task, "线程2");

        thread1.start();
        thread2.start();
    }

    private static class Task implements Runnable {
        private final SpinLockTest spinLock;

        private Task(SpinLockTest spinLock) {
            this.spinLock = spinLock;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "尝试获取锁.");
            spinLock.lock();
            try {
                System.out.println(Thread.currentThread().getName() + " 获取锁成功.");
                // 模拟一些临界区代码
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                spinLock.unlock();
                System.out.println(Thread.currentThread().getName() + " 释放锁.");
            }
        }
    }
}

