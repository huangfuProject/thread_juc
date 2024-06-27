package com.nine;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangfukexing
 * @date 2024/6/26 17:35
 */
public class TestLock {
    private final ReentrantLock LOCK = new ReentrantLock();
    private final Condition CONDITION = LOCK.newCondition();

    private String cId;

    public void lock(String id) throws InterruptedException{
        LOCK.lock();
        try {
            while (cId!=null && !cId.equals(id)) {
                CONDITION.await();
            }
            cId = id;
        }finally {
            LOCK.unlock();
        }

    }

    public void unlock(String id) throws InterruptedException{
        LOCK.lock();
        try {
            while (cId!=null && cId.equals(id)) {
                cId = null;
                CONDITION.signal();
            }
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            LOCK.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        TestLock testLock = new TestLock();
        new Thread(() ->{
            try {
                testLock.lock("1");
                System.out.println("111111111");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() ->{
            try {
                testLock.lock("2");
                System.out.println("22222222");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();


        new Thread(() ->{
            try {
                testLock.lock("2");
                System.out.println("333333");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() ->{
            try {
                Thread.sleep(10000);
                testLock.unlock("1");
                System.out.println("========");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() ->{
            try {
                Thread.sleep(10000);
                testLock.unlock("1");
                System.out.println("========");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
