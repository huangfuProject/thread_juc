package com.six;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * 自旋锁
 *
 * @author huangfukexing
 * @date 2023/11/3 16:20
 */
public class SpinLockDemo {
    private AtomicReference<Thread> atomicReference = new AtomicReference<>();

    /**
     * 加锁操作
     */
    public void lock(){

        //获取当前线程
        Thread thread = Thread.currentThread();
        //判断 是不是有线程持有锁，如果锁为空，则将当前线程分配锁！否则自旋
        while (!atomicReference.compareAndSet(null, thread)) {
            System.out.println(Thread.currentThread().getName() + "尝试重新获取锁");
        }
    }

    /**
     * 解锁操作
     */
    public void unLock(){
        //获取当前线程
        Thread thread = Thread.currentThread();
        //如果是当前线程 就将当前线程设为null  解锁
        atomicReference.compareAndSet(thread, null);
    }

    public static void main(String[] args) throws InterruptedException {
        Task task = new Task(new SpinLockDemo());
        Thread thread1 = new Thread(task,"线程1");
        Thread thread2 = new Thread(task,"线程2");
        Thread thread3 = new Thread(task,"线程3");
        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("此时值为:" + task.i);
    }

    private static class Task implements Runnable{
        int i = 0;
        private final SpinLockDemo spinLockDemo;

        private Task(SpinLockDemo spinLockDemo) {
            this.spinLockDemo = spinLockDemo;
        }


        @Override
        public void run() {
            spinLockDemo.lock();
            try {
                for (int j = 0; j < 100000; j++) {
                    i++;
                }
            }finally {
                spinLockDemo.unLock();
            }

        }
    }
}
