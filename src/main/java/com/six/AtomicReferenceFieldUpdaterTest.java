package com.six;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;

/**
 * @author huangfukexing
 * @date 2023/11/3 17:33
 */
public class AtomicReferenceFieldUpdaterTest {

    public static void main(String[] args) throws InterruptedException {
        AtomicReferenceFieldUpdater<Log, String> atomicReferenceFieldUpdater = AtomicReferenceFieldUpdater.newUpdater(Log.class, String.class, "logMessage");
        Log log = new Log("a");

        if (atomicReferenceFieldUpdater.compareAndSet(log,"a", "b")) {
            System.out.println("原子更新成功");
        }
        System.out.println(log.logMessage);
    }



    private static class Log {
        volatile String logMessage;

        public Log(String logMessage) {
            this.logMessage = logMessage;
        }
    }
}
