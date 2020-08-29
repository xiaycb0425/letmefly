package com.kingstar.kafka.util;

import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

/**
 * @author xiayc
 * @date 2020/8/29 11:10
 */
public class ReadTextTest {
    @Test
    public void test(){

        BufferedReader br = null;
        try {
            String line = "";
            String path = Objects.requireNonNull(this.getClass().getClassLoader().getResource("DrtpDefConfig.txt")).getPath();
            File file = new File(path);
            br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8));

            List<Map<String, String>> list = new ArrayList<>();
            Map<String, String> map = new HashMap<>(16);

            while ((line = br.readLine()) != null) {
                if ("DrtpDefStart".equals(line)) {
                    continue;
                }

                if (line.contains(":")) {
                    String[] split = line.split(":");
                    if (split.length == 1) {
                        map.put(split[0], "");
                    } else {
                        map.put(split[0], split[1]);
                    }
                }

                if ("DrtpDefEnd".equals(line)) {
                    Map<String, String> newMap = new HashMap<>(16);
                    newMap.putAll(map);
                    list.add(newMap);
                    map.clear();
                }
            }

            System.out.println(list);
        } catch (Exception e) {

            System.out.println(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                if (null != br) {
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
