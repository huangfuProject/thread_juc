package com.six;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * @author huangfukexing
 * @date 2023/11/3 17:23
 */
public class AtomicIntegerFieldUpdaterTest {
    public static void main(String[] args) throws InterruptedException {
        AtomicIntegerFieldUpdater<Count> atomicIntegerFieldUpdater = AtomicIntegerFieldUpdater.newUpdater(Count.class, "count");
        Count count = new Count();

        Task task = new Task(count, atomicIntegerFieldUpdater);
        Thread thread = new Thread(task);
        Thread thread1 = new Thread(task);

        thread.start();
        thread1.start();

        thread.join();
        thread1.join();

        System.out.println(count.count);
    }

    private static class Task implements Runnable {

        private final Count count;
        private final AtomicIntegerFieldUpdater atomicIntegerFieldUpdater;

        private Task(Count count, AtomicIntegerFieldUpdater atomicIntegerFieldUpdater) {
            this.count = count;
            this.atomicIntegerFieldUpdater = atomicIntegerFieldUpdater;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {
                //对对象内的数据进行累加操作
                atomicIntegerFieldUpdater.incrementAndGet(count);
            }
        }
    }



    private static class Count {
        volatile int count;
    }
}
