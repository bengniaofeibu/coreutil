package com.jiujiuwisdom.utils;


import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpUtil {

    private static CloseableHttpClient HTTP_CLIENT;

    private static final String CHARSET = "UTF-8";

    static {

        RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(3000).build();
        HTTP_CLIENT = HttpClientBuilder.create().setDefaultRequestConfig(config).build();

    }


    /**
     * GET 请求
     *
     * @param url
     * @return
     */
    public static String doGet(String url) {

        HttpGet httpGet = new HttpGet(url);
        try {

            return getResponseEntity(HTTP_CLIENT.execute(httpGet));

        } catch (IOException e) {
            e.printStackTrace();

        } finally {

            closeHttpClient();
        }
        return null;
    }


    public static String doGet(String url, Map<String, Object> params) {
        try {
            if (params != null && params.size() > 0) {

                StringBuilder urlStr = new StringBuilder(url).append("?");

                urlStr =urlStr.append(EntityUtils.toString(new UrlEncodedFormEntity(getNameValuePairList(params)), CHARSET));

                HttpGet httpGet = new HttpGet(urlStr.toString());

                return getResponseEntity(HTTP_CLIENT.execute(httpGet));

            }
        } catch (IOException e) {

            e.printStackTrace();

        } finally {

            closeHttpClient();
        }
        return null;
    }


    public static String doPost(String url, Map<String, Object> params) {

        try {
            if (params != null && !params.isEmpty()) {

                HttpPost httpPost = new HttpPost(url);

                httpPost.setEntity(new UrlEncodedFormEntity(getNameValuePairList(params), CHARSET));

                return getResponseEntity(HTTP_CLIENT.execute(httpPost));
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            closeHttpClient();

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
        if (response.getStatusLine().getStatusCode() == 200) {
            HttpEntity entity = response.getEntity();
            return entity != null ? EntityUtils.toString(entity, CHARSET) : null;
        }
        return null;
    }

    private static void closeHttpClient() {

        if (HTTP_CLIENT != null) {
            try {
                HTTP_CLIENT.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
