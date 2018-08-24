package com.jiujiuwisdom.utils;

import com.jiujiuwisdom.constant.BaseConstant;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class Base64Util {

    private static final String CHARSET = "UTF-8";

    /*编码
    * */
    public static String encode(String text) {
        final BASE64Encoder encoder = new BASE64Encoder();
        final byte[] textByte;
        try {
            textByte = text.getBytes(BaseConstant.CHARSET);
            //编码
            final String encodedText = encoder.encode(textByte);

            return encodedText;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /*解码
    * */
    public static String decode(String encodedText) {
        final BASE64Decoder decoder = new BASE64Decoder();
        try {
            String result = new String(decoder.decodeBuffer(encodedText),BaseConstant.CHARSET);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
