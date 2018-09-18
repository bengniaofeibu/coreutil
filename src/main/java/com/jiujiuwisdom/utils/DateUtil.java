package com.jiujiuwisdom.utils;

import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;
import java.util.Date;


/**
 * 日期工具类
 */
public final class DateUtil {


    /**
     * 获取当前日期
     *
     * @return yyyy-mm-dd
     */
    public static LocalDateTime getLocalDateNow() {
        return LocalDateTime.now();
    }

    /**
     * 获取日期的毫秒值
     * @param localDateTime
     * @return
     */
    public static Long getMilli(LocalDateTime localDateTime){
        return localDateTime.toInstant(ZoneOffset.of("+8")).toEpochMilli();
    }


    /**
     * 获取待定时期是几号
     *
     * @return
     */
    public static int getDayOfMonth(LocalDateTime localDateTime) {
        return localDateTime.getDayOfMonth();
    }

    /**
     * 获取待定日期的第几天
     *
     * @return
     */
    public static LocalDateTime getDayOfMonth(LocalDateTime localDateTime,int day) {
        return localDateTime.withDayOfMonth(day);
    }


    /**
     * 获取待定日期是第几周
     *
     * @return
     */
    public static int getDayOfWeek(LocalDateTime localDateTime) {
        return localDateTime.getDayOfWeek().getValue();
    }

    /**
     * 获取待定日期是几月
     *
     * @return
     */
    public static int getMonth(LocalDateTime localDateTime) {
        return localDateTime.getMonthValue();
    }

    /**
     * 获取待定日期是哪年
     *
     * @return
     */
    public static int getYear(LocalDateTime localDateTime) {
        return localDateTime.getYear();
    }


    /**
     * 比较两个日期的日期差
     *
     * @param localDateStart 开始时间
     * @param localDateEnd   结束时间
     * @param unit 天，月，年
     * @return 时间差
     */
    public static long betweenDate(LocalDateTime localDateStart, LocalDateTime localDateEnd, ChronoUnit unit) {

        final Period between = Period.between(LocalDate.from(localDateStart), LocalDate.from(localDateEnd));

        long betweenNum = 0;
        switch (unit) {
            case DAYS:
                betweenNum = unit.between(localDateStart, localDateEnd);
                break;
            case MONTHS:
                betweenNum = between.getMonths();
                break;
            case YEARS:
                betweenNum = between.getYears();
                break;
        }
        return betweenNum;
    }


    /**
     * 比较两个日期的日期差
     *
     * @param dateStart 开始时间
     * @param dateEnd   结束时间
     * @param unit 天，月，年
     * @return 时间差
     */
    public static long betweenDate(Date dateStart, Date dateEnd, ChronoUnit unit) {

        LocalDateTime localDateStart = dateToLocalDateTime(dateStart);

        LocalDateTime localDateEnd = dateToLocalDateTime(dateEnd);

        return betweenDate(localDateStart,localDateEnd,unit);
    }


    /**
     * 判定localDateStart是否在localDateEnd之后
     *
     * @param localDateStart 开始时间
     * @param localDateEnd   结束时间
     * @return
     */
    public static boolean isAfter(LocalDateTime localDateStart, LocalDateTime localDateEnd) {
        return localDateStart.isAfter(localDateEnd);
    }


    /**
     * 判定localDateStart是否在localDateEnd之前
     *
     * @param localDateStart 开始时间
     * @param localDateEnd   结束时间
     * @return
     */
    public static boolean isBefore(LocalDateTime localDateStart, LocalDateTime localDateEnd) {
        return localDateStart.isBefore(localDateEnd);
    }


    /**
     * 获取一天的开始时间
     *
     * @param localDateTime 当前时间
     * @return
     */
    public static LocalDateTime getLocalDateTimeStart(LocalDateTime localDateTime) {
        return localDateTime.withHour(0).withMinute(0).withSecond(0);
    }

    /**
     * 获取一天的结束时间
     *
     * @param localDateTime 当前时间
     * @return
     */
    public static LocalDateTime getLocalDateTimeEnd(LocalDateTime localDateTime) {
        return localDateTime.withHour(23).withMinute(59).withSecond(59);
    }

    /**
     * 增加年/月/周/天/小时/分/秒数
     * @param localDateTime
     * @param unit
     * @param num
     * @return
     */
    public static LocalDateTime addTime(LocalDateTime localDateTime,ChronoUnit unit,int num){
         return localDateTime.plus(num,unit);
    }

    /**
     * 获取当前月的最后一天
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getLastDayOfMonth(LocalDateTime localDateTime){
        return localDateTime.with(TemporalAdjusters.lastDayOfMonth());
    }

    /**
     * 获取当前月的第一天
     * @param localDateTime
     * @return
     */
    public static LocalDateTime getFirstDayOfMonth(LocalDateTime localDateTime){
        return localDateTime.with(TemporalAdjusters.firstDayOfMonth());
    }


    /**
     * 日期格式化
     *
     * @param localDateTime
     * @param pattern
     * @return
     */
    public static String format(LocalDateTime localDateTime, String pattern) {
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 日期格式化
     *
     * @param date
     * @param pattern
     * @return
     */
    public static String format(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(date);
    }

    /**
     *  date 转换成 LocalDateTime
     * @param date
     * @return
     */
    public static LocalDateTime dateToLocalDateTime(Date date){
           Instant instant = date.toInstant();
           ZoneId zoneId = ZoneId.of("GMT+8");
           return LocalDateTime.ofInstant(instant, zoneId);
    }
}
