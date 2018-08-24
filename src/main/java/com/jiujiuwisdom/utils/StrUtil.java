package com.jiujiuwisdom.utils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public final class StrUtil {


    /**
     * map 转换成 kay=value&kay=value
     *
     * @param mapParam
     * @return
     */
    public static String mapToString(Map<String, Object> mapParam) {

        if (mapParam == null) return null;

        StringBuilder strBuilder = new StringBuilder();

        for (Iterator<Map.Entry<String, Object>> iterator = mapParam.entrySet().iterator(); iterator.hasNext(); ) {

            Map.Entry<String, Object> next = iterator.next();
            strBuilder.append(next.getKey()).append("=").append(next.getValue());

            if (iterator.hasNext()) {
                strBuilder.append("&");
            }
        }

        return strBuilder.toString();
    }

    public static void main(String[] args) {

        Map<String, Object> map = new HashMap<>();
        map.put("aaa", "bbb");
        map.put("cccc", "dddd");

        System.out.println(mapToString(map));
    }
}
