package com.seven;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延时队列
 *
 * @author huangfukexing
 * @date 2023/11/7 11:43
 */
public class DelayQueueTest {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<DelayedTask> delayQueue = new DelayQueue<DelayedTask>();
        delayQueue.add(new DelayedTask("a1", 3000));
        delayQueue.add(new DelayedTask("11", 3000));
        delayQueue.add(new DelayedTask("2", 4000));
        delayQueue.add(new DelayedTask("3", 5000));

        System.out.println(delayQueue.take().taskName);
        System.out.println(delayQueue.take().taskName);
        System.out.println(delayQueue.take().taskName);
        System.out.println(delayQueue.take().taskName);
    }

    private static class DelayedTask implements Delayed {
        private long delayTime;  // 延迟时间，单位为纳秒
        private String taskName;

        public DelayedTask(String taskName, long delayTime) {
            this.taskName = taskName;
            this.delayTime = System.currentTimeMillis() + delayTime;
        }

        @Override
        public long getDelay(TimeUnit unit) {
            long diff = delayTime - System.currentTimeMillis();
            return unit.convert(diff, TimeUnit.MILLISECONDS);
        }

        @Override
        public int compareTo(Delayed o) {
            long diff = this.delayTime - ((DelayedTask) o).delayTime;
            return Long.compare(diff, 0);
        }

        public String getTaskName() {
            return taskName;
        }
    }

}
