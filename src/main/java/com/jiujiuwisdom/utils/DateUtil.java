package com.jiujiuwisdom.utils;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjusters;


/**
 * 日期工具类
 */
public class DateUtil {

    public static final String TIME_PATTERN_LONG = "dd/MMM/yyyy:HH:mm:ss +0900";

    /**
     * date format dd/MM/yyyy:HH:mm:ss +0900
     */
    public static final String TIME_PATTERN_LONG2 = "dd/MM/yyyy:HH:mm:ss +0900";

    /**
     * date format YYYY-MM-DD HH24:MI:SS
     */
    public static final String DB_TIME_PATTERN = "YYYY-MM-DD HH24:MI:SS";

    /**
     * date format YYYYMMDDHH24MISS
     */
    public static final String DB_TIME_PATTERN_1 = "YYYYMMDDHH24MISS";

    /**
     * date format dd/MM/yy HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT = "dd/MM/yy HH:mm:ss";

    /**
     * date format dd/MM/yy HH24:mm
     */
    public static final String TIME_PATTERN_SHORT_1 = "yyyy/MM/dd HH:mm";

    /**
     * date format yyyy年MM月dd日 HH:mm:ss
     */
    public static final String TIME_PATTERN_SHORT_2 = "yyyy年MM月dd日 HH:mm:ss";
    /**
     * date format yyyyMMddHHmmss
     */
    public static final String TIME_PATTERN_SESSION = "yyyyMMddHHmmss";

    /**
     * date format yyyyMMddHHmmssSSS
     */
    public static final String TIME_PATTERN_MILLISECOND = "yyyyMMddHHmmssSSS";

    /**
     * date format yyyyMMdd
     */
    public static final String DATE_FMT_0 = "yyyyMMdd";

    /**
     * date format yyyy/MM/dd
     */
    public static final String DATE_FMT_1 = "yyyy/MM/dd";

    /**
     * date format yyyy/MM/dd hh:mm:ss
     */
    public static final String DATE_FMT_2 = "yyyy/MM/dd hh:mm:ss";

    /**
     * date format yyyy-MM-dd
     */
    public static final String DATE_FMT_3 = "yyyy-MM-dd";

    /**
     * date format yyyy年MM月dd日
     */
    public static final String DATE_FMT_4 = "yyyy年MM月dd日";

    /**
     * date format yyyy-MM-dd HH
     */
    public static final String DATE_FMT_5 = "yyyy-MM-dd HH";

    /**
     * date format yyyy-MM
     */
    public static final String DATE_FMT_6 = "yyyy-MM";

    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_7 = "MM月dd日 HH:mm";

    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_8 = "HH:mm:ss";

    /**
     * date format MM月dd日 HH:mm
     */
    public static final String DATE_FMT_9 = "yyyy.MM.dd";
    public static final String DATE_FMT_10 = "HH:mm";
    public static final String DATE_FMT_11 = "yyyy.MM.dd HH:mm:ss";

    /**
     * date format yyyy年MM月dd日
     */
    public static final String DATE_FMT_12 = "MM月dd日";
    public static final String DATE_FMT_13 = "yyyy年MM月dd日HH时mm分";
    public static final String DATE_FMT_14 = "yyyyMM";
    public static final String DATE_FMT_15 = "MM-dd HH:mm:ss";
    public static final String DATE_FMT_16 = "yyyyMMddHHmm";
    public static final String DATE_FMT_17 = "HHmmss";
    public static final String DATE_FMT_18 = "yyyy";

    /**
     * date format yyyy-MM-dd HH:mm:ss
     */
    public static final String TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";


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
}
