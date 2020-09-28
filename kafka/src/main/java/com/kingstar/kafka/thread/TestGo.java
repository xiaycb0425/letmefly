package com.kingstar.kafka.thread;

import org.junit.Test;

/**
 * @author xiayc
 * @date 2020/9/10 15:03
 */
public class TestGo {
    @Test
    public void  test1(){
        String test1= "错误[1]：无数据, SQLCODE=100,LINE=[ignore],FILE=p200110.sqc";

        String[] res = test1.split("：");
        String errCode = res[0].trim().substring(3, res[0].lastIndexOf("]"));
        String errMsg =
                res[1].substring(0,  res[1].indexOf("SQLCODE"));
        System.out.println(errCode);
        System.out.println(errMsg);

    }
}
