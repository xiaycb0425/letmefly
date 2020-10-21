package com.kingstar.kafka.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * @author xiayc
 * @date 2020/10/14 9:05
 */
public class JSONUtil {

    public static JSONObject changeJsonObj(JSONObject jsonObj,JSONObject keyMap) {
        JSONObject resJson = new JSONObject();
        Set<String> keySet = jsonObj.keySet();
        for (String key : keySet) {
            String resKey = keyMap.get(key) == null ? key : keyMap.getString(key);
            try {
                JSONObject jsonobj1 = jsonObj.getJSONObject(key);
                JSONObject keyMapNext = keyMap.getJSONObject("_next") == null ? keyMap : keyMap.getJSONObject("_next");

                resJson.put(resKey, changeJsonObj(jsonobj1, keyMapNext));
            } catch (Exception e) {
                try {
                    JSONArray jsonArr = jsonObj.getJSONArray(key);
                    JSONObject keyMapNext = keyMap.getJSONObject("_next") == null ? keyMap : keyMap.getJSONObject("_next");
                    resJson.put(resKey, changeJsonArr(jsonArr, keyMapNext));
                } catch (Exception x) {
                    resJson.put(resKey, jsonObj.get(key));
                }
            }
        }
        return resJson;
    }

    public static JSONArray changeJsonArr(JSONArray jsonArr,JSONObject keyMap) {
        JSONArray resJson = new JSONArray();
        for (int i = 0; i < jsonArr.size(); i++) {
            JSONObject jsonObj = jsonArr.getJSONObject(i);
            resJson.add(changeJsonObj(jsonObj, keyMap));
        }
        return resJson;
    }

    public static void main(String[] args) {
        String jsonStr = "{\"user\":{\"name\":\"张三\",\"sex\":\"男\",\"hobby\":[{\"name\":\"足球\",\"desc\":\"任性\"},{\"game\":\"英雄联盟\",\"desc\":\"就是这么任性\"}]}}";

        //使用json作为映射关系的配置
        String jsonKeyMap = "{'_next':{'name':'xingming','_next':{'name':'hobbyname','game':'hobbyname'}}}";
        JSONObject jsonKeyObject = JSONObject.parseObject(jsonKeyMap);

        JSONObject jsonObj = JSONUtil.changeJsonObj(JSONObject.parseObject(jsonStr),jsonKeyObject);
        System.out.println("换值结果 》》 " + jsonObj.toString());
    }

}
