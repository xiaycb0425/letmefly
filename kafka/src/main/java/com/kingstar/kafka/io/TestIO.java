package com.kingstar.kafka.io;

import org.springframework.util.StringUtils;

import java.io.File;
import java.util.Arrays;

/**
 * @author xiayc
 * @date 2020/8/29 15:49
 */
public class TestIO {
    public static void main(String[] args) {
        String param = "";
        int i = 0;
        if (!StringUtils.isEmpty(param)) {
            i = Integer.parseInt(param);
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(i);
        File path = new File("kafka");
        String[] list;
        if (args.length == 0) {
            list = path.list();
        } else {
            list = path.list();
        }

        Arrays.sort(list, String.CASE_INSENSITIVE_ORDER);
        for (String s : list) {
            System.out.println(s);
        }
    }
}
