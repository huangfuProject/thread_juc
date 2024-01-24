package com.three;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁购票模拟
 *
 * @author huangfukexing
 * @date 2023/10/31 17:59
 */
public class BuyTicketReadWriteLock {
    private final static ReentrantReadWriteLock REENTRANT_READ_WRITE_LOCK = new ReentrantReadWriteLock(true);
    /**
     * 获取读锁
     */
    private final static ReentrantReadWriteLock.ReadLock READ_LOCK = REENTRANT_READ_WRITE_LOCK.readLock();
    /**
     * 获取写锁
     */
    private final static ReentrantReadWriteLock.WriteLock WRITE_LOCK = REENTRANT_READ_WRITE_LOCK.writeLock();

    private static class Task {
        /**
         * 余票信息
         */
        private int remainingVotes;

        public Task(int remainingVotes) {
            this.remainingVotes = remainingVotes;
        }

        /**
         * 购票
         */
        public void buyTicket(){
            System.out.println(Thread.currentThread().getName() + "尝试获取写锁，准备购票.");
            WRITE_LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到写锁，开始购票.");
                if(remainingVotes >0) {
                    remainingVotes--;
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "购票成功，余票减少.");
                }else {
                    System.out.println("剩余票数为:" + remainingVotes + "， 购买失败.");
                }

            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                WRITE_LOCK.unlock();
            }
        }

        /**
         * 查询票
         */
        public void selectTicket(){
            System.out.println(Thread.currentThread().getName() + "尝试获取读锁，准备查询票.");
            READ_LOCK.lock();
            try {
                System.out.println(Thread.currentThread().getName() + "获取到读锁，当前余票为: " + remainingVotes);
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + "查询成功，释放读锁.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                READ_LOCK.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Task task = new Task(4);
        new Thread(task::selectTicket, "线程1").start();
        new Thread(task::selectTicket, "线程2").start();
        new Thread(task::buyTicket, "线程3").start();
        new Thread(task::selectTicket, "线程4").start();
    }
}
