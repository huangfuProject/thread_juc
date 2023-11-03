package com.six;

import java.util.concurrent.atomic.LongAccumulator;

/**
 * @author huangfukexing
 * @date 2023/11/3 18:24
 */
public class LongAccumulatorTest {
    public static void main(String[] args) {
        LongAccumulator longAccumulator = new LongAccumulator((x,y)-> x * y, 1);
        longAccumulator.accumulate(1);
        longAccumulator.accumulate(2);
        longAccumulator.accumulate(3);
        System.out.println(longAccumulator.getThenReset());
    }
}
