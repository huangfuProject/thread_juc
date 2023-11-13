package com.eight;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author huangfukexing
 * @date 2023/11/13 20:29
 */
public class ThreadDbTest {
    private final static AtomicInteger IDX = new AtomicInteger(0);

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), r -> new Thread(r, "open-api-" + IDX.getAndIncrement()), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {

        List<Future<String>> futures = new ArrayList<>();

        Future<String> submit1 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(1, 10000, true));
        Future<String> submit2 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(10001, 20000, true));
        Future<String> submit3 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(20001, 30000, true));
        Future<String> submit4 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(30001, 40000, true));
        Future<String> submit5 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(40001, 50000, false));
        Future<String> submit6 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(50001, 60000, true));
        Future<String> submit7 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(70001, 80000, true));
        Future<String> submit8 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(80001, 90000, true));
        Future<String> submit9 = THREAD_POOL_EXECUTOR.submit(new BatchWriteDbTask(90001, 100000, true));

        futures.add(submit1);
        futures.add(submit2);
        futures.add(submit3);
        futures.add(submit4);
        futures.add(submit5);
        futures.add(submit6);
        futures.add(submit7);
        futures.add(submit8);
        futures.add(submit9);

        for (Future<String> future : futures) {
            try {
                System.out.println(future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }

    }

    public static class BatchWriteDbTask implements Callable<String> {


        private final Integer minIndex;
        private final Integer maxIndex;

        /**
         * 模拟使用， 当为true的时候就写入成功 当为false就写入失败
         */
        private final boolean isSuccess;

        public BatchWriteDbTask(Integer minIndex, Integer maxIndex, boolean isSuccess) {
            this.minIndex = minIndex;
            this.maxIndex = maxIndex;
            this.isSuccess = isSuccess;
        }


        @Override
        public String call() throws Exception {
            System.out.println("开始批量写入数据 " + minIndex + "至" + maxIndex);
            if(!isSuccess) {
                throw new Exception("数据 " + minIndex + "至" + maxIndex + "写入失败，请手动处理。");
            }
            Thread.sleep(5000);
            return "数据"  + minIndex  + "至" + maxIndex + "写入成功";
        }
    }
}
