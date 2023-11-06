package com.seven;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * *************************************************<br/>
 * <br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/11/5 12:11
 */
public class OldSyncContainer {
    public static void main(String[] args) {
        Vector<String> vector = new Vector<>();
        vector.add("1");

        Hashtable<String,String> hashtable = new Hashtable<>();
        hashtable.put("a","1");

        List<String> list = Collections.synchronizedList(new ArrayList<>());
        list.add("Ad");


    }
}
