package com.eight;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeoutException;

/**
 *
 *
 * @author huangfukexing
 * @date 2023/11/10 21:43
 */
public class StopTest2 {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> stringFutureTask = new FutureTask<String>(new Task());
        new Thread(stringFutureTask).start();
        //获取线程的返回结果
        Thread.sleep(1000);
        System.out.println(stringFutureTask.cancel(true));
        System.out.println("任务被停止");
        System.out.println(stringFutureTask.get());
    }

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            while (true) {
                System.out.println("线程正在运行");
                Thread.sleep(500);
            }
        }
    }
}
