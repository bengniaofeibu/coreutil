package com.jiujiuwisdom.utils;


import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.BeanUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

@Slf4j
public final class BeanUtil<E> {

    /**
     * map转实体bean
     * @param map
     * @param e
     * @return
     */
    public static <E> E mapTobean(Map<String, Object> map, E e) {

          if (map == null || e == null) return null;

        try {
            BeanUtils.populate(e, map);
            return e;
        } catch (Exception ex) {
            log.error("map转实体bean error {}",ex.getMessage());
        }
        return null;
    }


    /**
     * 实体转换成map
     * @return
     */
    public static <E> Map<String,Object> beanToMap(E e){

        if (e == null) return null;

        return JSONUtil.parseObject(JSONUtil.toJSONString(e),Map.class);
    }
}
