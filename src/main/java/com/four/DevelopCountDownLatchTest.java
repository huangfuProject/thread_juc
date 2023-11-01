package com.four;

import java.util.Random;
import java.util.concurrent.*;

/**
 * 出租车司机
 * @author huangfukexing
 * @date 2023/11/1 11:30
 */
public class DevelopCountDownLatchTest {

    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


    /**
     * 等待到来的车辆计数器
     */
    protected final static CountDownLatch WAIT_CAR_COUNT = new CountDownLatch(1);
    /**
     * 乘客下车计数器
     */
    protected final static CountDownLatch PASSENGER_DOWN_COUNT = new CountDownLatch(4);

    public static void main(String[] args) throws Exception {
        EXECUTOR.execute(new ProjectDevelop("小红"));
        EXECUTOR.execute(new ProjectDevelop("小绿"));
        EXECUTOR.execute(new ProjectDevelop("小蓝"));
        EXECUTOR.execute(new ProjectDevelop("小紫"));
        System.out.println("产品经理此时正在紧张的设计原型和PRD.....");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("原型设计完毕.");
        WAIT_CAR_COUNT.countDown();
        //运维开始等待项目开发完毕上线
        PASSENGER_DOWN_COUNT.await();
        System.out.println("项目开发完毕，运维开始上线.");
        System.out.println("上线成功.");
    }

    private static class ProjectDevelop implements Runnable {

        private final String name;

        private ProjectDevelop(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            try {
                System.out.println(name + "正在等待产品经理的原型和PRD...");
                WAIT_CAR_COUNT.await();
                System.out.println(name + "获取到了原型和PRD，开始开发.");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(name + "开发完毕.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                PASSENGER_DOWN_COUNT.countDown();
            }
        }
    }

}
