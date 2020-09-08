package com.kingstar.kafka.hutool.regex;

import cn.hutool.core.util.RandomUtil;
import org.junit.Test;

import java.awt.*;

/**
 * @author xiayc
 * @date 2020/9/4 15:44
 */
public class ReTest {
    @Test
    public void test1() {
        for (int i = 0; i < 100; i++) {
            int j = RandomUtil.randomInt(10000);
            Color color = RandomUtil.randomColor();
            System.out.println(color);
        }

    }
}
