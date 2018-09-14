package com.jiujiuwisdom.utils;


import com.jiujiuwisdom.constant.BaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class HttpUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(HttpUtil.class);

    private static CloseableHttpClient HTTP_CLIENT;

    static {

        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(3000).build();
        HTTP_CLIENT = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

    }


    /**
     * GET 请求
     *
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url) {

        CloseableHttpResponse response = null;

        try {
            HttpGet httpGet = new HttpGet(url);
            response = HTTP_CLIENT.execute(httpGet);
            return getResponseEntity(response);

        } catch (IOException e) {
            LOGGER.error("doPost doGet!!! {}, url {} ",e.getMessage(),url);
        } finally {
            closeHttpClient(response);
        }
        return null;
    }


    /**
     *  带参数的GET请求
     * @param url  请求地址
     * @param params 请求参数
     * @return
     */
    public static String doGet(String url, Map<String, Object> params) {

        CloseableHttpResponse response = null;
        try {
            if (params != null && params.size() > 0) {

                StringBuilder urlStr = new StringBuilder(url).append("?");

                urlStr =urlStr.append(EntityUtils.toString(new UrlEncodedFormEntity(getNameValuePairList(params)), BaseConstant.CHARSET));

                HttpGet httpGet = new HttpGet(urlStr.toString());

                response = HTTP_CLIENT.execute(httpGet);
                return getResponseEntity(response);

            }
        } catch (IOException e) {
            LOGGER.error("doPost doGet!!! {}, url {} ",e.getMessage(),url);
        } finally {
            closeHttpClient(response);
        }
        return null;
    }


    /**
     *  POST 请求
     * @param url 请求地址
     * @param params 请求参数
     * @return
     */
    public static String doPost(String url, Map<String, Object> params) {

        CloseableHttpResponse response = null;
        try {
            if (params != null && !params.isEmpty()) {

                HttpPost httpPost = new HttpPost(url);

                httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePairList(params), BaseConstant.CHARSET));

                response = HTTP_CLIENT.execute(httpPost);
                return getResponseEntity(response);
            }

        } catch (Exception e) {
            LOGGER.error("doPost error!!! {}, url {} ",e.getMessage(),url);
        } finally {
            closeHttpClient(response);
        }
        return null;
    }

    public static String doPost(String url, String json){

        CloseableHttpResponse response = null;
        try {

                HttpPost httpPost = new HttpPost(url);
                StringEntity se = new StringEntity(json, Charset.forName("UTF-8"));
                httpPost.addHeader(HTTP.CONTENT_TYPE, "application/json");
                se.setContentEncoding("UTF-8");
                httpPost.setEntity(se);
                response = HTTP_CLIENT.execute(httpPost);
            return getResponseEntity(response);

        } catch (Exception e) {
            LOGGER.error("doPost error!!! {}, url {} ",e.getMessage(),url);
        } finally {
            closeHttpClient(response);
        }
        return null;
    }

    private static List<NameValuePair> getNameValuePairList(Map<String, Object> params){
        List<NameValuePair> pairs  = new ArrayList<>(params.size());
        for (String key : params.keySet()) {
            pairs.add(new BasicNameValuePair(key, params.get(key).toString()));
        }
        return pairs;
    }

    private static String getResponseEntity(CloseableHttpResponse response) throws IOException {

        int statusCode = response.getStatusLine().getStatusCode();

        if (statusCode == 200) {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity, BaseConstant.CHARSET) : null;
        }
        LOGGER.error("response statusCode {}",statusCode);
        return null;
    }

    private static void closeHttpClient(CloseableHttpResponse response) {

        if (response != null) {
            try {
                response.close();
            } catch (IOException e) {
                LOGGER.error("http_client close {}",e.getMessage());
            }
        }
    }
}
