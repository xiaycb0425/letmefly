package com.kingstar.kafka.bigdecimal;

import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author xiayc
 * @date 2020/8/31 14:53
 */
public class BigDecimalTest {
    @Test
    public void test1(){
        double douleAdd = ArithmeticUtils.add(0.01, 0.11);
        System.out.println(douleAdd);
        BigDecimal add = ArithmeticUtils.add("0.01", "0.11");
        System.out.println(add);
    }
}
