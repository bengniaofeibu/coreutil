package com.jiujiuwisdom.utils;

import lombok.Data;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

@Data
public final class BaiduUtil {

    private String name;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaiduUtil.class);

    private static String ak = "IK5AlGXoZ23tDAGjldRalicbhdpsrKwE";

    private static String GEOCODER_V2_URL = "http://api.map.baidu.com/geocoder/v2/";

    /**
     * 调用百度api获取城市名称
     *
     * @param lat
     * @param lng
     * @return
     */
    public static String getCityName(String lat, String lng) {

        Map<String,Object> params = new HashMap<>();
        try {
            params.put("callback","renderReverse");
            params.put("ak", ak);
            params.put("location", new StringBuilder(lat).append(",").append(lng).toString());
            params.put("output", "json");
            params.put("pois",1);
            String json = HttpUtil.doGet(GEOCODER_V2_URL, params);
            return new JSONObject(json.substring(29, json.length() - 1)).getJSONObject("result").getJSONObject("addressComponent").getString("city");
        }catch (Exception e){

            LOGGER.error(e.getMessage());

        }finally {
            params = null;
        }
        return null;
    }

    public static void main(String[] args){
        String city = getCityName("31.266302","121.557401");
        System.out.println(city);
    }
}
