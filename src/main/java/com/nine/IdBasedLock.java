package com.nine;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class IdBasedLock {

    /**
     * 使用map存储一个阻塞锁
     */
    private static final ConcurrentHashMap<String, LockConditionPair> lockMap = new ConcurrentHashMap<String, LockConditionPair>();

    /**
     * 加锁操作  每次传递一个id都存储一个加锁的实现用于解锁的操作
     * @param id 传递的标记
     */
    public void lock(String id) {
        LockConditionPair lockConditionPair = lockMap.get(id);
        // 若加锁实现不存在，就向map增加一个缓存
        if (lockConditionPair == null) {
            lockConditionPair = new LockConditionPair();
            LockConditionPair existing = lockMap.putIfAbsent(id, lockConditionPair);
            if (existing != null) {
                lockConditionPair = existing;
            }
        }
        // 加锁操作
        lockConditionPair.lock.lock();
        try {
            // 循环是否解锁
            while (!lockConditionPair.isUnlocked) {
                // 没解锁就阻塞
                lockConditionPair.condition.await();
            }
            // 解锁后标记为未解锁
            lockConditionPair.isUnlocked = false;
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } finally {
            // 解锁操作
            lockConditionPair.lock.unlock();
        }
    }

    /**
     * 根据传递的id 进行条件解锁
     * @param id 解锁标记
     */
    public void unlock(String id) {
        // 先获取锁标记
        LockConditionPair lockConditionPair = lockMap.get(id);
        if (lockConditionPair != null) {
            // 加锁
            lockConditionPair.lock.lock();
            try {
                // 将数据标记为解锁
                lockConditionPair.isUnlocked = true;
                // 释放阻塞
                lockConditionPair.condition.signal();
            } finally {
                // 解锁操作
                lockConditionPair.lock.unlock();
            }
        }
    }

    private static class LockConditionPair {
        final ReentrantLock lock = new ReentrantLock();
        final Condition condition = lock.newCondition();
        volatile boolean isUnlocked = false;
    }

    public static void main(String[] args) {
        IdBasedLock idBasedLock = new IdBasedLock();
        

        
        // 创建一个线程进行锁定
        new Thread(new Runnable() {
            public void run() {
                System.out.println("1开始加锁阻塞");
                idBasedLock.lock("1");
                System.out.println("1解除阻塞");
            }
        }).start();
        // 创建一个线程进行锁定
        new Thread(new Runnable() {
            public void run() {
                System.out.println("2开始加锁阻塞");
                idBasedLock.lock("2");
                System.out.println("2解除阻塞");
            }
        }).start();
        // 创建一个线程进行锁定
        new Thread(new Runnable() {
            public void run() {
                System.out.println("3开始加锁阻塞");
                idBasedLock.lock("3");
                System.out.println("3解除阻塞");
            }
        }).start();
        // 创建一个线程进行解锁
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(7000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("解锁1");
                idBasedLock.unlock("1");
            }
        }).start();

        // 创建一个线程进行解锁
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("解锁2");
                idBasedLock.unlock("2");
            }
        }).start();

        // 创建一个线程进行解锁
        new Thread(new Runnable() {
            public void run() {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("解锁3");
                idBasedLock.unlock("3");
            }
        }).start();

    }
}
