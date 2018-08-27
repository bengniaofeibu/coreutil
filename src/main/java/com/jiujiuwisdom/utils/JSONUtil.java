package com.jiujiuwisdom.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/*
   json工具类
 */
public final class JSONUtil {

    private static final Logger LOGGER  = LoggerFactory.getLogger(JSONUtil.class);


    /**
     *  实体bean转换成json字符串
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {

            LOGGER.error("Object 转换为 json error {}",e.getMessage());

            return null;
        }
    }

    public static <T> T parseObject(String text, Class<T> clazz) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(text, clazz);
        } catch (Exception e) {
            LOGGER.error("json 转换为 Object error {}",e.getMessage());
            return null;
        }
    }

}
