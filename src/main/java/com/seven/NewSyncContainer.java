package com.seven;

import java.util.concurrent.ConcurrentHashMap;

/**
 * *************************************************<br/>
 * <br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/11/5 13:16
 */
public class NewSyncContainer {
    public static void main(String[] args) {
        ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<>();
        concurrentHashMap.put("1","a");
    }
}
