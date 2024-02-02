package com.four;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * *************************************************<br/>
 * 模拟停车场业务<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/11/1 20:11
 */
public class ParkingLot {
    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    private final static ReentrantLock LOCK = new ReentrantLock();
    private final static Condition CONDITION = LOCK.newCondition();

    /**
     * 停车场总位置数量
     */
    private final int totalParkingSpaces;
    /**
     * 已经停了多少量
     */
    private int occupiedSpaces = 0;

    public ParkingLot(int totalParkingSpaces) {
        this.totalParkingSpaces = totalParkingSpaces;
    }

    public static void main(String[] args) {
        ParkingLot parkingLot = new ParkingLot(4);
        for (int i = 0; i < 6; i++) {
            EXECUTOR.execute(new CarActive(parkingLot, "车辆"+i));
        }
    }

    /**
     * 尝试进入停车场
     */
    public void park(String name){
        LOCK.lock();
        try {
            if(occupiedSpaces >= totalParkingSpaces){
                // 如果停车场已满，等待
                System.out.println(name + ": 车辆等待停车位...");
                // 开始等待车位
                CONDITION.await();
            }
            // 有停车位，抢到了 将已经占用的数量+1
            occupiedSpaces++;
            System.out.println(name + ": 车辆成功停车，剩余的停车位:" + (totalParkingSpaces - occupiedSpaces));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    /**
     * 驶离停车场
     */
    public void bearOff(String name){
        LOCK.lock();
        try {
            // 离开停车场  将已占用的数量-1
            occupiedSpaces--;
            System.out.println(name + ": 车辆离开停车场，剩余停车位: " + (totalParkingSpaces - occupiedSpaces));
            // 通知等待的车辆有空位了
            CONDITION.signal();
        }finally {
            LOCK.unlock();
        }
    }

    private static class CarActive implements Runnable {

        private final ParkingLot parkingLot;
        private final  String name;

        private CarActive(ParkingLot parkingLot, String name) {
            this.parkingLot = parkingLot;
            this.name = name;
        }

        @Override
        public void run() {
            try {
                parkingLot.park(name);
                Thread.sleep((long) (Math.random() * 10000));
                parkingLot.bearOff(name);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
