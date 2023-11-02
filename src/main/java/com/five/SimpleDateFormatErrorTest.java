package com.five;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;

/**
 * @author huangfukexing
 * @date 2023/11/2 12:35
 */
public class SimpleDateFormatErrorTest {
    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(40, 40, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());
    static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            EXECUTOR.execute(() ->{
                System.out.println(simpleDateFormat.format(new Date(finalI * 1000)));
            });
        }
    }
}
