package com.jiujiuwisdom.utils;

import java.math.BigDecimal;

public final class BigDecimalUtil {


    /**
     *  BigDecimal 加法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal add(BigDecimal var1,BigDecimal var2){
       return var1.add(var2);
    }


    /**
     *  Integer 加法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal add(Integer var1,Integer var2){

        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        return bigDecimal1.add(bigDecimal2);
    }

    /**
     *  Double 加法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal add(Double var1,Double var2){

        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        return bigDecimal1.add(bigDecimal2);
    }


    /**
     *  BigDecimal 减法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal subtract(BigDecimal var1,BigDecimal var2){
        return var1.subtract(var2);
    }


    /**
     *  Integer 减法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal subtract(Integer var1,Integer var2){

        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        return bigDecimal1.subtract(bigDecimal2);
    }

    /**
     *  Double 减法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal subtract(Double var1,Double var2){

        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        return bigDecimal1.subtract(bigDecimal2);
    }

    /**
     *  BigDecimal 乘法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal multiply(BigDecimal var1,BigDecimal var2){
        return var1.multiply(var2);
    }


    /**
     *  Integer 乘法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal multiply(Integer var1,Integer var2){

        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        return bigDecimal1.multiply(bigDecimal2);
    }

    /**
     *  Double 乘法
     * @param var1
     * @param var2
     * @return
     */
    public static BigDecimal multiply(Double var1,Double var2){

        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        return bigDecimal1.multiply(bigDecimal2);
    }


    /**
     *  BigDecimal 除法
     * @param var1
     * @param var2
     * @param scale 保留的位数
     * @return
     */
    public static BigDecimal divide(BigDecimal var1,BigDecimal var2,int scale){
        checkScale(scale);
        return var1.divide(var2,scale,BigDecimal.ROUND_HALF_UP);
    }


    /**
     *  Integer 除法
     * @param var1
     * @param var2
     * @param scale 保留的位数
     * @return
     */
    public static BigDecimal divide(Integer var1,Integer var2,int scale){
        checkScale(scale);
        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        return bigDecimal1.divide(bigDecimal2,scale,BigDecimal.ROUND_HALF_UP);
    }

    /**
     *  Double 除法
     * @param var1
     * @param var2
     * @param scale 保留的位数
     * @return
     */
    public static BigDecimal divide(Double var1,Double var2,int scale){
        checkScale(scale);
        BigDecimal bigDecimal1 = new BigDecimal(var1);
        BigDecimal bigDecimal2 = new BigDecimal(var2);
        return bigDecimal1.divide(bigDecimal2,scale,BigDecimal.ROUND_HALF_UP);
    }


    private static void checkScale(int scale){
        if (scale < 0) throw new IllegalArgumentException(" scale 必须大于0或等于0");
    }
}
