package com.jiujiuwisdom.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.ResourceBundle;

/**
 * 获取 properties工具类
 */
public class PropertiesUtil {

    private  PropertiesUtil propertiesUtil;

    private final static Properties properties = new Properties();

    private String name;

    public PropertiesUtil(String name){

            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream(name);
            try {
                properties.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
    }



    public String getValue(String key){
         return properties.getProperty(key);
    }

    public static void main(String[] args) {

        PropertiesUtil propertiesUtil = new PropertiesUtil("redis-test.properties");

        String value = propertiesUtil.getValue("redis.host");
        System.out.println(value);
    }

}
