package com.seven;

import java.util.concurrent.ConcurrentLinkedQueue;

public class ProducerConsumerExample {
    private static final int MAX_CAPACITY = 5;
    private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<>();

    public static void main(String[] args) {
        Thread producer = new Thread(new Producer());
        Thread consumer = new Thread(new Consumer());

        producer.start();
        consumer.start();
    }

    static class Producer implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (queue.size() == MAX_CAPACITY) {
                    System.out.println("队列已满，等待消费者消费...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    int number = (int) (Math.random() * 100);
                    queue.offer(number);
                    System.out.println("生产者生产的消息为: " + number);
                }
            }
        }
    }

    static class Consumer implements Runnable {
        @Override
        public void run() {
            while (true) {
                if (queue.isEmpty()) {
                    System.out.println("队列为空，等待生产者生产...");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    int number = queue.poll();
                    System.out.println("消费者消费到数据: " + number);
                }
            }
        }
    }
}
