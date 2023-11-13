package com.eight;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 线程池使用callable
 *
 * @author huangfukexing
 * @date 2023/11/10 21:49
 */
public class ThreadPoolCallableCase1 {
    private final static AtomicInteger IDX = new AtomicInteger(0);

    private final static ThreadPoolExecutor THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), r -> new Thread(r, "open-api-" + IDX.getAndIncrement()), new ThreadPoolExecutor.AbortPolicy());


    public static void main(String[] args) {

        ThreadPoolCallableCase1 threadPoolCallableCase1 = new ThreadPoolCallableCase1();
        System.out.println(threadPoolCallableCase1.getData());
        System.out.println(threadPoolCallableCase1.getData());
        System.out.println(threadPoolCallableCase1.getData());
        System.out.println(threadPoolCallableCase1.getData());
        System.out.println(threadPoolCallableCase1.getData());
        System.out.println(threadPoolCallableCase1.getData());
        System.out.println(threadPoolCallableCase1.getData());
    }

    public String getData(){
        Future<String> submit = THREAD_POOL_EXECUTOR.submit(new Task());
        try {
            return submit.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return "手动中断任务";
        } catch (ExecutionException e) {
            return "第三方异常";
        } catch (TimeoutException e) {
            //超时了就取消任务
            System.out.println(submit.cancel(true));
            return "第三方接口网络异常";
        }
    }

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            try {
                Thread.sleep((long) (Math.random() * 7000));
            }catch (Exception e) {
                System.out.println("任务被主动中断");
            }
            return "第三方数据返回成功";
        }
    }
}
