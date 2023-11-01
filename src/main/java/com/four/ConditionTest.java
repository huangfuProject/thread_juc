package com.four;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author huangfukexing
 * @date 2023/11/1 18:08
 */
public class ConditionTest {

    private final static ReentrantLock LOCK = new ReentrantLock();
    /**
     * 创建等待条件
     */
    private final static Condition CONDITION = LOCK.newCondition();
    public static void main(String[] args) {
        //CONDITION.
    }
}
