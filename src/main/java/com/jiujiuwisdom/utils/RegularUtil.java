package com.jiujiuwisdom.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegularUtil {

    private static final String PHONE_FORMAT = "^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\\d{8}$";

    public static boolean verifyPhoneFormat(String  phone){

        Pattern pattern = Pattern.compile(PHONE_FORMAT);

        Matcher matcher = pattern.matcher(phone);

        return matcher.matches();
    }
}
