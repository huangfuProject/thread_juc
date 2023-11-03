package com.six;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @author huangfukexing
 * @date 2023/11/3 16:09
 */
public class AtomicReferenceTest {
    public static void main(String[] args) {
        AtomicReference<String> atomicReference = new AtomicReference<>();
        //设置一个值
        atomicReference.set("abcd");
        //获取一个值
        System.out.println(atomicReference.get());
        //比较后更新
        System.out.println(atomicReference.compareAndSet("abcd", "hf"));
        //获取值
        System.out.println(atomicReference.get());
    }
}
