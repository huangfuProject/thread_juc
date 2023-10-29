package com.one;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * *************************************************<br/>
 * Callable测试<br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/10/29 15:25
 */
public class ThreadCallableTest {

    public static void main(String[] args) {
        //构建一个具有返回结果的任务对象  包装实际的任务对象
        FutureTask<String> stringFutureTask = new FutureTask<>(new TaskReturn());
        Thread thread = new Thread(stringFutureTask);
        thread.setName("测试线程");
        thread.setPriority(Thread.MAX_PRIORITY);
        thread.start();
        try {
            System.out.println(stringFutureTask.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    private static class TaskReturn implements Callable<String> {

        @Override
        public String call() throws Exception {
            return String.format("我被线程【%s】执行了", Thread.currentThread().getName());
        }
    }
}
