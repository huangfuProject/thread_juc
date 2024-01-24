package com.three;

import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockExample {

    /**
     * 创建一个可重入锁
     */
    private final ReentrantLock lock = new ReentrantLock();

    public static void main(String[] args) {
        ReentrantLockExample example = new ReentrantLockExample();
        example.outerMethod();
    }

    // 外层方法
    public void outerMethod() {
        lock.lock(); // 获取锁
        try {
            System.out.println("线程："+Thread.currentThread().getName() + "外层加锁成功; 此时加锁次数为:" + lock.getHoldCount());
            System.out.println("线程："+Thread.currentThread().getName() + "; 外层方法开始执行");
            innerMethod(); // 调用内层方法
            System.out.println("线程："+Thread.currentThread().getName() + "; 外层方法执行结束");
        } finally {
            lock.unlock(); // 释放锁
            System.out.println("线程："+Thread.currentThread().getName() + "外层方法释放锁成功; 此时加锁次数为:" + lock.getHoldCount());
        }
    }

    // 内层方法
    public void innerMethod() {
        //获取锁不会被阻塞
        lock.lock();
        try {
            System.out.println("线程："+Thread.currentThread().getName() + "内层加锁成功; 此时加锁次数为:" + lock.getHoldCount());
            System.out.println("线程："+Thread.currentThread().getName() + "; 内层方法执行");
            System.out.println("线程："+Thread.currentThread().getName() + "; 内层方法结束");
        } finally {
            lock.unlock(); // 释放锁
            System.out.println("线程："+Thread.currentThread().getName() + "内层方法释放锁成功; 此时加锁次数为:" + lock.getHoldCount());
        }
    }
}
