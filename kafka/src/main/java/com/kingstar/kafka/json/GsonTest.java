package com.kingstar.kafka.json;

import org.junit.Test;

/**
 * @author xiayc
 * @date 2020/10/20 17:43
 */
public class GsonTest {
    @Test
    public void test1() {
        String var = "{\n" +
                "  \"name\": null\n" +
                "}";
        System.out.println(GsonFormatUtil.prettyFormat(var));
    }
}
