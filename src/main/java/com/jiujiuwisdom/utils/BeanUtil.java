package com.jiujiuwisdom.utils;


import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class BeanUtil {

    /**
     * map转实体bean
     * @param map
     * @param object
     * @return
     */
    public static Object MapTobean(Map<String, Object> map, Object object) {

          if (map == null || object == null) return null;

        try {
            BeanUtils.populate(object, map);
            return  object;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 实体转换成map
     * @param object
     * @return
     */
    public static Map<String,Object> beanToMap(Object object){

        if (object == null) return null;

        return JsonUtil.parseObject(JsonUtil.toJSONString(object),Map.class);
    }

}
