package com.kingstar.kafka.bigdecimal;

import org.junit.Test;

/**
 * @author xiayc
 * @date 2020/10/20 15:34
 */
public class StringValueTest {
    @Test
    public void test1() {
        double v = Double.parseDouble("1.0000");
        System.out.println(v);

        String format = String.format("%.6f", 1.0);
        System.out.println(format);

    }
}
