package com.four;

import java.util.concurrent.*;

/**
 * 开发完成后需要测试
 *
 * @author huangfukexing
 * @date 2023/11/1 15:49
 */
public class DevelopAndTestCountDownLatchTest {

    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

    /**
     * 产品经理
     */
    private static final CyclicBarrier PRD_COUNT = new CyclicBarrier(1, new StartDevelop());
    /**
     * 开发人员
     */
    private static final CyclicBarrier DEVELOP_COUNT = new CyclicBarrier(4, new TestCode());

    /**
     * 测试人员
     */
    private static final CyclicBarrier TEST_COUNT = new CyclicBarrier(1, new OperationTopLineCode());



    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        System.out.println("产品经理此时正在紧张的设计原型和PRD.....");
        TimeUnit.SECONDS.sleep(3);
        System.out.println("原型设计完毕.");
        PRD_COUNT.await();
    }


    /**
     * 产品经理资料准备齐全后的回调
     */
    private static class StartDevelop implements Runnable {

        @Override
        public void run() {
            EXECUTOR.execute(new DevelopCode("小红"));
            EXECUTOR.execute(new DevelopCode("小绿"));
            EXECUTOR.execute(new DevelopCode("小蓝"));
            EXECUTOR.execute(new DevelopCode("小紫"));
        }
    }


    private static class DevelopCode implements Runnable {
        private final String name;

        private DevelopCode(String name) {
            this.name = name;
        }

        @Override
        public void run() {

            try {
                System.out.println(name + "开始开发代码.");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println(name + "完成代码开发.");
                //等待其他人完成开发
                DEVELOP_COUNT.await();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * 测试人员的测试任务
     */
    private static class TestCode implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("开发人员开发完成，测试人员开始测试.");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("测试人员完成测试");
                TEST_COUNT.await();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 运维上线代码
     */
    private static class OperationTopLineCode implements Runnable{

        @Override
        public void run() {

            try {
                System.out.println("检测到测试完成，运维开始上线代码");
                Thread.sleep((long) (Math.random() * 10000));
                System.out.println("上线完成");
                //上线完成后关闭线程池
                EXECUTOR.shutdown();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
