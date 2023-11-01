package com.four;

import java.util.concurrent.*;

/**
 * @author huangfukexing
 * @date 2023/11/1 16:11
 */
public class DineTest {
    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    /**
     * 小伙伴
     */
    private static final CyclicBarrier BUDDY_CUNT = new CyclicBarrier(4, new Runnable() {
        @Override
        public void run() {
            System.out.println("人都到齐了，出发去团建;每一个人都很开心，脸上挂着幸福的笑容.");
        }
    });

    public static void main(String[] args) {
        EXECUTOR.execute(new EmployeeAct("打工人1号"));
        EXECUTOR.execute(new EmployeeAct("打工人2号"));
        EXECUTOR.execute(new EmployeeAct("打工人3号"));
        EXECUTOR.execute(new EmployeeAct("打工人4号"));
    }

    /**
     * 员工出发去公司的任务任务
     */
    private static class EmployeeAct implements Runnable {
        private final String name;

        private EmployeeAct(String name) {
            this.name = name;
        }


        @Override
        public void run() {
            try {
                System.out.println(name + "出发前往公司.");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(name + "到达公司");
                BUDDY_CUNT.await();
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(name + "经过2个小时的车程，到达了目的地");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
