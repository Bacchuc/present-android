package com.larry.present.common.util;

import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {

    public static String TAG = DateUtil.class.toString();

    /**
     * DATE_FORMAT_FOR_YMD_CHINESE
     */
    public static final String DATE_FORMAT_FOR_YMD_CHINESE = "yyyy年MM月dd日";

    /**
     * DATE_FORMAT_YYYYMM
     */
    public static final String DATE_FORMAT_YYYYMM = "yyyy年MM月";

    /**
     * DATE_FORMAT_YYYYMM
     */
    public static final String DATE_FORMAT_YYYYMM_M = "yyyyMM";

    /**
     * DATE_FORMAT_YYYYMMDD
     */
    public static final String DATE_FORMAT_YYYYMMDD = "yyyy年MM月dd日";

    /**
     * DATE_FORMAT_DD
     */
    public static final String DATE_FORMAT_DD = "dd日";

    /**
     * DATE_FORMAT_MMDD
     */
    public static final String DATE_FORMAT_MMDD = "MM月dd日";

    /**
     * DATE_FORMAT_MMDD
     */
    public static final String DATE_FORMAT_MMDDSTR = "MMdd";

    /**
     * DATE_FORMAT_YYYYMDE
     */
    public static final String DATE_FORMAT_YYYYMDE = "yyyy年M月d日(E)";

    /**
     * DATE_FORMAT_MDD_S
     */
    public static final String DATE_FORMAT_MDD_S = "M/dd";

    /**
     * DATE_FORMAT_MMDDHHMM_S
     */
    public static final String DATE_FORMAT_MMDDHHMM_S = "MM/dd HH:mm";

    /**
     * DATE_FORMAT_YYYYMD_S
     */
    public static final String DATE_FORMAT_YYYYMD_S = "yyyy/M/d";

    /**
     * DATE_FORMAT_YYYYMMDD_S
     */
    public static final String DATE_FORMAT_YYYYMMDD_S = "yyyy/MM/dd";

    /**
     * DATE_FORMAT_YYYYMMDD_H
     */
    public static final String DATE_FORMAT_YYYYMMDD_H = "yyyy-MM-dd";

    /**
     * DATE_FORMAT_YYYYMMDDE_H
     */
    public static final String DATE_FORMAT_YYYYMMDDE_H = "yyyy-MM-dd(E)";

    /**
     * DATE_FORMAT_YYYYMMDDHHMME_S
     */
    public static final String DATE_FORMAT_YYYYMMDDHHMME_S = "yyyy/MM/dd HH:mm(E)";

    /**
     * DATE_FORMAT_YYYYMMDDHHMM_S
     */
    public static final String DATE_FORMAT_YYYYMMDDHHMM_S = "yyyy/MM/dd HH:mm";

    /**
     * DATE_FORMAT_YYYYMMDDHHMMSS
     */
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS = "yyyy-MM-dd HH:mm:ss";

    /**
     * DATE_FORMAT_HHMMSS
     */
    public static final String DATE_FORMAT_HHMMSS = "HH:mm:ss";

    /**
     * DATE_FORMAT_HHMM
     */
    public static final String DATE_FORMAT_HHMM = "HH:mm";

    /**
     * DATE_FORMAT_YYYYMMDDHHMM
     */
    public static final String DATE_FORMAT_YYYYMMDDHHMM = "yyyy-MM-dd HH:mm";

    /**
     * DATE_FORMAT_YYYYMMDDHHMMSSSSS
     */
    public static final String DATE_FORMAT_YYMMDDHHMMSSSSS = "yyMMddHHmmssSSS";

    /**
     * DATE_FORMAT_YYYYMMDDHHMMSS_M
     */
    public static final String DATE_FORMAT_YYYYMMDDHHMMSS_M = "yyyyMMddHHmmss";

    /**
     * DATE_TYPE 日
     */
    public static final int DATE_TYPE_DATE = Calendar.DATE;

    /**
     * DATE_TYPE 月
     */
    public static final int DATE_TYPE_MONTH = Calendar.MONTH;

    /**
     * DATE_TYPE 年
     */
    public static final int DATE_TYPE_YEAR = Calendar.YEAR;

	/*private static SerializeConfig secondsFormatConfig;
    static
	{
		secondsFormatConfig = new SerializeConfig();
		secondsFormatConfig.put(Date.class, new SecondsFormatSerializer());
		secondsFormatConfig.put(java.sql.Timestamp.class, new TimestampSecondsFormatSerializer()); 
	}*/

    /**
     * 将日期型转换成字符串类型
     *
     * @param date 日期
     * @return 日期格式化后的字符串
     */
    public static final String getDate(Date date) {
        SimpleDateFormat simpleDateFormat = null;
        String returnValue = "";
        if (date != null) {
            simpleDateFormat = new SimpleDateFormat(DATE_FORMAT_FOR_YMD_CHINESE);
            returnValue = simpleDateFormat.format(date);
        }
        return (returnValue);
    }

    /**
     * 取得今天的日期
     *
     * @return 返回Calendar类型的日期
     * @throws ParseException 解析异常
     */
    public static Calendar getToday() throws ParseException {
        Date today = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(today);
        return cal;
    }

    /**
     * 根据格式由字符串类型转换成日期型
     *
     * @param pattern 日期格式
     * @param strDate 字符串型的日期
     * @return 日期型对象
     * @throws ParseException 解析异常
     */
    public static Date convertStringToDate(String pattern, String strDate) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            return simpleDateFormat.parse(strDate);
        } catch (Exception e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }

    /**
     * 秒字符串转换成Date型
     *
     * @param strDate
     * @return 日期型对象
     */
    public static Date convertSecondsStringToDate(String strDate) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(strDate) * 1000);
        return c.getTime();
    }

    /**
     * 秒字符串转换成当天的零点
     *
     * @param strDate
     * @return 当天零点的日期型对象
     */
    public static Date convertSecondsStringToDayStart(String strDate) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(Long.parseLong(strDate) * 1000);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
        return c.getTime();
    }

    /**
     * 根据格式由日期型转换成字符串类型
     *
     * @param date    日期
     * @param pattern 日期格式
     * @return 字符串类型日期
     * @throws ParseException
     */
    public static String convertDateToString(Date date, String pattern) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        // df.setTimeZone(timeZone);
        return simpleDateFormat.format(date);
    }

    /**
     * 取得日期中的年和月
     *
     * @param date 日期
     * @return 返回yyyyMM型的字符串
     * @throws ParseException 解析异常
     */
    public static String getDateyyyyMM(Date date) throws ParseException {
        return convertDateToString(date, DATE_FORMAT_YYYYMM_M);
    }

    /**
     * 取得日期中的完整字符串 年月日时分秒毫秒
     *
     * @param date 日期
     * @return 返回yyMMddHHmmssSSS型的字符串
     * @throws ParseException 解析异常
     */
    public static String getDateyyMMddHHmmssSSS(Date date) throws ParseException {
        return convertDateToString(date, DATE_FORMAT_YYMMDDHHMMSSSSS);
    }

    /**
     * 取得日期中的 年月日时分秒
     *
     * @param date 日期
     * @return 返回yyyyMMddHHmmss型的字符串
     * @throws ParseException 解析异常
     */
    public static String getDateyyyyMMddHHmmss(Date date) {
        return convertDateToString(date, DATE_FORMAT_YYYYMMDDHHMMSS_M);
    }

    /**
     * 取得日期中的小时和分钟
     *
     * @param date 日期
     * @return 返回HH:mm型的字符串
     * @throws ParseException 解析异常
     */
    public static String getDateHHmm(Date date) throws ParseException {
        return convertDateToString(date, DATE_FORMAT_HHMM);
    }

    /**
     * 取得日期中的小时和分钟和秒
     *
     * @param date 日期
     * @return 返回指定日期的HH:mm:ss字符串
     * @throws ParseException 解析异常
     */
    public static String getDateHHmmss(Date date) throws ParseException {
        return convertDateToString(date, DATE_FORMAT_HHMMSS);
    }

    /**
     * @return String 日期时间 yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTimeStr() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat(DATE_FORMAT_YYYYMMDDHHMMSS);
        String time = (f.format(c.getTime()));
        return time;
    }

    /**
     * @return String 日期 yyyy-MM-dd
     */
    public static String getDateStr() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        String date = (f.format(c.getTime()));
        return date;
    }

    /**
     * @return String 日期 yyyy-MM-dd HH:mm:ss
     */
    public static String getCurrentDateStr() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = (f.format(c.getTime()));
        return date;
    }

    /**
     * 获取当前日期(简单格式)
     *
     * @return
     * @throws ParseException
     */
    public static Date getSimpleDate() throws ParseException {
        String format = "yyyy-MM-dd";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date date = sdf.parse(getDateStr());
        return date;
    }

    /**
     * 获取当前日期(简单格式)
     *
     * @return
     * @throws ParseException
     */
    public static Date getCurrentDate() throws ParseException {
        String format = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sdf = new SimpleDateFormat(format);

        Date date = sdf.parse(getCurrentDateStr());
        return date;
    }

    /**
     * @return Date
     */
    public static Date getDate() {
        Calendar c = Calendar.getInstance();
        return c.getTime();
    }

    /**
     * 取得指定日期的前一天
     *
     * @return Date
     */
    public static Date getPrevioustDate(Date date) {
        return new Date(date.getTime() - 24 * 60 * 60 * 1000);
    }

    /**
     * 取得指定日期的下一天
     *
     * @return Date
     */
    public static Date getNextDate(Date date) {
        return new Date(date.getTime() + 24 * 60 * 60 * 1000);
    }

    /**
     * @return Date 年份 yyyy
     */
    public static String getYear() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat f = new SimpleDateFormat("yyyy");
        String date = (f.format(c.getTime()));
        return date;
    }

    /**
     * 格式化日期类型
     *
     * @param date
     * @return
     */
    public static String formatDate(Date date) {
        if (date == null)
            return null;
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return f.format(date);
    }

    /**
     * 格式化日期类型
     *
     * @param date
     * @return
     */
    public static String formatMMDDSTR(Date date) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat f = new SimpleDateFormat("M月d日");
        return f.format(date);
    }

    /**
     * 字符串转化为时间
     *
     * @param strDate
     * @return
     */
    public static Date getYYYYMMDDHHMMSSDate(String strDate) {
        if (strDate == null) {
            return null;
        }

        return convertStringToDate(DATE_FORMAT_YYYYMMDDHHMMSS, strDate);
    }

    /**
     * 指定日期加上指定年、月、天后的日期，默认指定日期为当前日期
     *
     * @param date
     * @param type 年、月、天
     * @param num
     * @return
     */
    public static Date getDateAddDate(Date date, int type, int num) {
        Calendar calendar = Calendar.getInstance();
        if (date != null) {
            calendar.setTime(date);
        }
        calendar.add(type, num);
        return calendar.getTime();
    }

    /**
     * 指定日期加上年后的日期，默认指定日期为当前日期
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getDateAddYear(Date date, int num) {
        return getDateAddDate(date, Calendar.YEAR, num);
    }

    /**
     * 指定日期加上月后的日期，默认指定日期为当前日期
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getDateAddMonth(Date date, int num) {
        return getDateAddDate(date, Calendar.MONTH, num);
    }

    /**
     * 指定日期加上日后的日期，默认指定日期为当前日期
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getDateAddDay(Date date, int num) {
        return getDateAddDate(date, Calendar.DATE, num);
    }

    /**
     * 指定日期加上小时后的日期，默认指定日期为当前日期
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getDateAddHours(Date date, int num) {
        return getDateAddDate(date, Calendar.HOUR_OF_DAY, num);
    }

    /**
     * 指定日期加上小时后的日期，默认指定日期为当前日期
     *
     * @param date
     * @param num
     * @return
     */
    public static Date getDateAddSecond(Date date, int num) {
        return getDateAddDate(date, Calendar.SECOND, num);
    }

    /**
     * 返回2个日期之差，默认第一个日期为当前日期
     *
     * @param startDate
     * @param endDate
     * @return
     * @throws ParseException
     */
    public static long getDaysBetweenTwoDates(Date startDate, Date endDate)
            throws ParseException {
        if (startDate == null) {
            startDate = DateUtil.getCurrentDate();
        }
        if (endDate == null) {
            return 0;
        }
        long quot = endDate.getTime() - startDate.getTime();
        return quot / 1000 / 60 / 60 / 24;
    }

    /**
     * 获取当前月的天数
     *
     * @return
     */
    public static int getDayCountOfMonth() {
        return Calendar.getInstance().getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    /**
     * 获取当前的时间戳
     *
     * @return secondsFormatConfig
     */
    public static long getDateTimeStap() {
        Date date = new Date();
        return date.getTime();
    }
}
