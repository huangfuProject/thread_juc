package com.eight;

import java.util.concurrent.*;

/**
 * @author huangfukexing
 * @date 2023/11/10 19:38
 */
public class CallableTest {
    public static void main(String[] args) throws ExecutionException, InterruptedException, TimeoutException {
        FutureTask<String> stringFutureTask = new FutureTask<>(new Task());
        new Thread(stringFutureTask).start();
        //获取线程的返回结果
        Thread.sleep(1000);
        System.out.println(stringFutureTask.cancel(true));
        System.out.println("Ad");
        System.out.println(stringFutureTask.get(1000, TimeUnit.MILLISECONDS));
    }

    private static class Task implements Callable<String> {

        @Override
        public String call() throws Exception {
            Thread.sleep(3000);
            System.out.println("xsxsx");
            return "我是执行结果";
        }
    }
}
