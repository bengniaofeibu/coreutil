package com.jiujiuwisdom.config;

import lombok.Getter;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 获取 properties工具类
 */
@Getter
@Setter
public class PropertiesConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(PropertiesConfig.class);

    private final static Properties properties = new Properties();

    private String name;

    public PropertiesConfig(String name){

        try {

            InputStream in = PropertiesConfig.class.getClassLoader().getResourceAsStream(name);

            if (in == null){
                throw  new FileNotFoundException("file not found  ("+name+")");
            }
             properties.load(in);

            } catch (IOException e) {
                LOGGER.error("properties 加载文件失败 {}",e.getMessage());
            }
    }

    public String getValue(String key){
         return properties.getProperty(key);
    }

}
