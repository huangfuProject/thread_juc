package com.five;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author huangfukexing
 * @date 2023/11/2 12:35
 */
public class SimpleDateFormatSuccessTest {
    private final static ThreadPoolExecutor EXECUTOR = new ThreadPoolExecutor(10, 20, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1000), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    public static void main(String[] args) {

        for (int i = 0; i < 1000; i++) {
            int finalI = i;
            EXECUTOR.execute(() ->{
                //从当前的线程中获取一个simpleDateFormat
                SimpleDateFormat simpleDateFormat = SimpleDataFormatCache.getSimpleDateFormat();
                System.out.println(simpleDateFormat.format(new Date(finalI * 1000)));
            });
        }
    }
}

class SimpleDataFormatCache {
    /**
     * 构建一个ThreadLocal 并在每次调用get方法返回为空的时候调用创建SimpleDateFormat的初始化方法
     */
    private static final ThreadLocal<SimpleDateFormat> SIMPLE_DATA_FORMAT_CACHE = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static SimpleDateFormat getSimpleDateFormat(){
        return SIMPLE_DATA_FORMAT_CACHE.get();
    }
    public static void setSimpleDateFormat(SimpleDateFormat simpleDateFormat){
        SIMPLE_DATA_FORMAT_CACHE.set(simpleDateFormat);
    }
    public static void removeSimpleDateFormat(){
        SIMPLE_DATA_FORMAT_CACHE.remove();
    }
}
