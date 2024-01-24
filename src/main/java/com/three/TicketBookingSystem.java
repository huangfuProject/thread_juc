package com.three;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangfu
 * @date 2024/01/24 08:01
 */
public class TicketBookingSystem {
    public static void main(String[] args) {
        TicketCounter ticketCounter = new TicketCounter(20);

        // 创建多个线程进行购票操作
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticketCounter.purchaseTicket(1);
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticketCounter.purchaseTicket(1);
            }
        });

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                ticketCounter.purchaseTicket(1);
            }
        });

        thread1.start();
        thread2.start();
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 打印最终剩余车票数量
        System.out.println("最后可售门票: " + ticketCounter.getAvailableTickets());
    }
}


class TicketCounter {
    /**
     * 车票的剩余数量
     */
    private int availableTickets;
    /**
     * 锁
     */
    private final Lock lock = new ReentrantLock();

    public TicketCounter(int totalTickets) {
        this.availableTickets = totalTickets;
    }

    /**
     * 购票动作
     * @param quantity 购票的数目
     */
    public void purchaseTicket(int quantity) {
        //加锁
        lock.lock();
        try {
            if (availableTickets >= quantity) {
                //模拟购票所需时间
                Thread.sleep(500);
                availableTickets -= quantity;
                System.out.println(Thread.currentThread().getName() + " 购买 " + quantity + " 票； 剩余的票: " + availableTickets);
            } else {
                System.out.println(Thread.currentThread().getName() + " 购买失败，车票不足");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            //解锁
            lock.unlock();
        }
    }

    /**
     * 返回余票的数目
     * @return 余票数目
     */
    public int getAvailableTickets() {
        return availableTickets;
    }
}