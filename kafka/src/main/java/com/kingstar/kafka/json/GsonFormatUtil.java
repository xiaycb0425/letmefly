package com.kingstar.kafka.json;

import com.google.common.reflect.TypeToken;
import com.google.gson.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiayc
 * @date 2020/9/17 14:35
 */
public class GsonFormatUtil {

    private static final Gson gson3 = new GsonBuilder().disableHtmlEscaping().serializeNulls().setPrettyPrinting().create();

    private static final Gson gson = new GsonBuilder().serializeNulls().disableHtmlEscaping().create();

    /**
     * 将json字符串格式化输出
     *
     * @param obj
     * @return
     */
    public static String prettyFormat(String obj) {
            JsonElement jsonElement = JsonParser.parseString(obj);
            return gson3.toJson(jsonElement);
    }

    public static String toJsonString(Object object) {
        return object == null ? null : gson.toJson(object);
    }

    /**
     * 转成json
     */
    public static String beanToString(Object object) {
        String gsonString = null;
        if (gson != null) {
            gsonString = gson.toJson(object);
        }
        return gsonString;
    }

    /**
     * 转成bean
     */
    public static <T> T stringToBean(String gsonString, Class<T> cls) {
        T t = null;
        if (gson != null) {
            t = gson.fromJson(gsonString, cls);
        }
        return t;
    }

    /**
     * 转成list
     */
    public static <T> List<T> stringToList(String gsonString, Class<T> cls) {
        List<T> list = new ArrayList<>();
        if (gson != null) {
            JsonArray array = new JsonParser().parse(gsonString).getAsJsonArray();
            for (final JsonElement elem : array) {
                list.add(gson.fromJson(elem, cls));
            }
        }
        return list;
    }

    /**
     * 转成list, 有可能造成类型擦除
     */
    public static <T> ArrayList<T> stringToList(String gsonString) {
        ArrayList<T> list = null;
        if (gson != null) {
            list = gson.fromJson(gsonString, new TypeToken<ArrayList<T>>() {
            }.getType());
        }
        return list;
    }

    /**
     * 转成map的
     */
    public static <T> Map<String, T> stringToMaps(String gsonString, Class<T> cls) {
        Map<String, T> map = null;
        if (gson != null) {
            map = gson.fromJson(gsonString, new TypeToken<Map<String, T>>() {
            }.getType());
        }
        return map;
    }

}

