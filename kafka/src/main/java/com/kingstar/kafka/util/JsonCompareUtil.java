package com.kingstar.kafka.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiayc
 * @date 2020/7/23 8:33
 * @des 比较两个json的工具类
 */
public class JsonCompareUtil {

    private JSONArray validationResList;

    public JsonCompareUtil() {
        this.validationResList = new JSONArray();
    }

    public JSONArray getCompareResult() {
        return this.validationResList;
    }

    /**
     * 判断字符串是否为json
     *
     * @param str  需要
     * @param type 0 jsonObject  1 jsonArray
     * @return
     */
    public static boolean isJson(String str, int type) {
        if (str == null || "".equals(str)) {
            return false;
        }
        try {
            if (type == 0) {
                JSONObject.parseObject(str);
            } else {
                JSONObject.parseArray(str);
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 比较jsonObject对象
     *
     * @param jsonObject1
     * @param jsonObject2
     */
    public void compareTwoJsonObj(JSONObject jsonObject1, JSONObject jsonObject2) {
        if (!jsonObject1.isEmpty()) {
            for (String key : jsonObject1.keySet()) {
                String newKey = key;
                if (key.startsWith("@")) {
                    newKey = key.substring(1);
                }
                // 校验key
                if (!jsonObject2.containsKey(newKey)) {
                    // 不包含newk
                    StringBuilder stringBuilder = new StringBuilder();
                    String compareResult = stringBuilder.append("预期字段[").append(newKey).append("]在响应结果中不存在").toString();
                    setContrastResult(compareResult);
                } else {
                    // 校验value
                    if (!key.startsWith("@")) {
                        String str1 = jsonObject1.getString(key);
                        String str2 = jsonObject2.getString(newKey);
                        compareTwoJsonMethodEntry(str1, str2);
                    }
                }
            }
        }
    }

    /**
     * 比较两个jsonArray对象
     *
     * @param jsonArray1
     * @param jsonArray2
     */
    public void compareTwoJsonArr(JSONArray jsonArray1, JSONArray jsonArray2) {
        if (!jsonArray1.isEmpty()) {
            if (jsonArray1.size() > jsonArray2.size()) {
                String compareResult = "预期结果集合与响应结果中返回集合长度不符合";
                setContrastResult(compareResult);
            } else {
                // 预期size 小于等于 实际size
                for (int i = 0; i < jsonArray1.size(); i++) {
                    String str1 = jsonArray1.getString(i);
                    String str2 = jsonArray2.getString(i);
                    compareTwoJsonMethodEntry(str1, str2);
                }
            }
        }
    }

    /**
     * 根据不同的string json类型调用不同方法 比较两个Json string的方法入口
     *
     * @param str1
     * @param str2
     */
    public JSONArray compareTwoJsonMethodEntry(String str1, String str2) {
        boolean jsonObj1 = isJson(str1, 0);
        boolean jsonObj2 = isJson(str2, 0);

        boolean jsonArr1 = isJson(str1, 1);
        boolean jsonArr2 = isJson(str2, 1);

        if (jsonObj1 && jsonObj2) {
            // value 是 obj 递归调用
            compareTwoJsonObj(JSONObject.parseObject(str1), JSONObject.parseObject(str2));
        } else if (jsonArr1 && jsonArr2) {
            // value 是array
            compareTwoJsonArr(JSONArray.parseArray(str1), JSONArray.parseArray(str2));
        } else if (!jsonObj1 && !jsonObj2 && !jsonArr1 && !jsonArr2) {
            // value 比较
            compareTwoStringValue(str1, str2);
        }
        return this.validationResList;
    }

    public void compareTwoStringValue(String value1, String value2) {
        StringBuilder stringBuilder = new StringBuilder();
        String compareResult = stringBuilder.append("预估请求结果[").append(value1).append("]与响应结果值[").append(value2).append("]对比失败").toString();

        // 值1非null 非忽略字段 值2为null
        if (value1 != null && !value1.startsWith("@") && value2 == null) {
            setContrastResult(compareResult);
        }
        // 值1为null 值2非null
        if (value2 != null && value1 == null) {
            setContrastResult(compareResult);
        }
        // 值1非null 值2非null
        if (value2 != null && value1 != null && !value1.startsWith("@") && !value1.equals(value2)) {
            setContrastResult(compareResult);
        }
    }

    // 设置校验结果
    private void setContrastResult(String value) {
        Map<String, Object> map = new HashMap<>();
        map.put("compare_result", value);
        validationResList.add(map);
    }
}
