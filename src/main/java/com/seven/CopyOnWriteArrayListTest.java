package com.seven;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * *************************************************<br/>
 * <br/>
 * ************************************************<br/>
 *
 * @author huangfu
 * @date 2023/11/6 20:11
 */
public class CopyOnWriteArrayListTest {
    public static void main(String[] args) {
        CopyOnWriteArrayList<String> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        copyOnWriteArrayList.add("a");
        copyOnWriteArrayList.add("b");
        for (String s : copyOnWriteArrayList) {
            System.out.println(s);
        }

    }
}
