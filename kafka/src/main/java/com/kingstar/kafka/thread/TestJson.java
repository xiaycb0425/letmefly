package com.kingstar.kafka.thread;

import com.alibaba.fastjson.JSONArray;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiayc
 * @date 2020/9/9 11:26
 */
public class TestJson {
    @Test
    public void test1() {
        JsonCompareUtil jsonCompareUtil = new JsonCompareUtil();
        String json1 = "[{\n" +
                "    \"userInfo\": {\n" +
                "        \"clientId\": \"1234ljq\",\n" +
                "        \"realName\": \"\",\n" +
                "        \"idNum\": \"\"\n" +
                "    },\n" +
                "    \"data\": {\n" +
                "        \"tagCode\": \"\",\n" +
                "        \"productQuality\": \"\",\n" +
                "        \"supplierCode\": \"\",\n" +
                "        \"priceUp\": \"\",\n" +
                "        \"priceDown\": \"\",\n" +
                "        \"tradeChannel\": 0,\n" +
                "        \"brandName\": \"\",\n" +
                "        \"productCategory\": \"\",\n" +
                "        \"orderBy\": \"0\",\n" +
                "        \"weightUp\": \"\",\n" +
                "        \"weightDown\": \"\",\n" +
                "        \"pageSize\": 100,\n" +
                "        \"pageNum\": \"1\"\n" +
                "    }\n" +
                "}]";
        String json2 = "[{}]";
        JSONArray jsonArray = jsonCompareUtil.compareTwoJsonMethodEntry(json1, json2);

        System.out.println(jsonArray.toJSONString());
    }

    @Test
    public void test2() {
        Map<Integer, String> data = new HashMap<>();
        data.put(1, "");
        data.put(2, "");
        data.put(3, null);
        data.put(4, "");
        data.put(0, "");
        String caseName = getCaseName(data, 4);
        System.out.println(caseName);
    }

    private String getCaseName(Map<Integer, String> data, int startRow) {
        String caseName = data.get(startRow);
        if (StringUtils.isEmpty(caseName)) {
            if (startRow >= 0) {
                caseName = getCaseName(data, startRow - 1);
                return caseName;
            }
        }else {
            if (caseName.length() > 50) {
                caseName = caseName.substring(0, 50);
            }
        }
        return caseName;
    }
}
