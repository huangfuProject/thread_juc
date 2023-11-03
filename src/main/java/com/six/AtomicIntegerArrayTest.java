package com.six;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * @author huangfukexing
 * @date 2023/11/3 15:44
 */
public class AtomicIntegerArrayTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerArray atomicIntegerArray = new AtomicIntegerArray(1000);

        List<Thread> threadList = new ArrayList<>(40);
        IncrementTask task1 = new IncrementTask(atomicIntegerArray);
        DecrementTask task2 = new DecrementTask(atomicIntegerArray);
        for (int i = 0; i < 20; i++) {
            Thread thread1 = new Thread(task1);
            Thread thread2 = new Thread(task2);
            thread1.start();
            thread2.start();
            threadList.add(thread1);
            threadList.add(thread2);
        }

        //等到线程结束
        for (Thread thread : threadList) {
            thread.join();
        }
        System.out.println("线程执行完毕");
        //获取当前原子数组中的数据
        for (int i = 0; i < atomicIntegerArray.length(); i++) {
            System.out.println(atomicIntegerArray.get(i));

        }

    }


    /**
     * 进行累加操作
     */
    private static class IncrementTask implements Runnable {
        private final AtomicIntegerArray atomicIntegerArray;

        private IncrementTask(AtomicIntegerArray atomicIntegerArray) {
            this.atomicIntegerArray = atomicIntegerArray;
        }

        @Override
        public void run() {
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                //对i位置进行+1操作
                atomicIntegerArray.incrementAndGet(i);
            }
        }
    }

    /**
     * 进行递减操作
     */
    private static class DecrementTask implements Runnable {
        private final AtomicIntegerArray atomicIntegerArray;

        private DecrementTask(AtomicIntegerArray atomicIntegerArray) {
            this.atomicIntegerArray = atomicIntegerArray;
        }

        @Override
        public void run() {
            for (int i = 0; i < atomicIntegerArray.length(); i++) {
                //对i位置进行-1操作
                atomicIntegerArray.decrementAndGet(i);
            }
        }
    }
}
