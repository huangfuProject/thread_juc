package com.seven;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * *************************************************<br/>
 * 优先级队列<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/11/6 21:42
 */
public class PriorityQueueTest {

    private final static PriorityBlockingQueue<Integer> priorityQueue = new PriorityBlockingQueue<>(2, new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            //倒序排列
            return o2 - o1;
        }
    });

    public static void main(String[] args) throws InterruptedException {
        priorityQueue.add(1);
        priorityQueue.add(2);
        priorityQueue.add(3);
        priorityQueue.add(4);

        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());
        System.out.println(priorityQueue.take());
    }
}
