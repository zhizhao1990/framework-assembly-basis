package com.cmc.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 日期工具类
 * <p>
 * 主要使用了SimpleDateFormat常用方法：format(Date date)和parse(String date).
 * </p>
 * @author Thomas Lee
 * @version 2017年3月31日 下午2:23:56
 */
public class DateUtils {

    private static final Logger LOG = LoggerFactory.getLogger(DateUtils.class);

    /** 日期格式定义 */
    private final static SimpleDateFormat SDF = new SimpleDateFormat("yyyy/MM/dd");
    private final static SimpleDateFormat YYYYMMDD = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat YYYY_MM_DD = new SimpleDateFormat("yyyy-MM-dd");
    private final static SimpleDateFormat YYYYMMDDHHMM = new SimpleDateFormat("yyyyMMddHHmm");
    private final static SimpleDateFormat HHMM = new SimpleDateFormat("HHmm");
    private final static SimpleDateFormat MMDD = new SimpleDateFormat("MM月dd日");
    private final static SimpleDateFormat SDF1 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private final static SimpleDateFormat SDF2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private final static SimpleDateFormat SDF3 = new SimpleDateFormat("HH:mm");
    private final static SimpleDateFormat SDF4 = new SimpleDateFormat("yyyyMMdd");
    private final static SimpleDateFormat SDF5 = new SimpleDateFormat("HH:mm:ss");
    private final static SimpleDateFormat SDF6 = new SimpleDateFormat("yyyy.MM.dd");
    private final static SimpleDateFormat SDF7 = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
    private final static SimpleDateFormat SDF8 = new SimpleDateFormat("yyyy年MM月dd日     HH:mm");

    /******************* SimpleDateFormat常用方法：format(Date date)和parse(String date) ***********************/

    /**
     * SimpleDateFormat常用方法：public final String format(Date date)
     * @param date 需要格式化的日期
     * @return 格式化后的日期字符串
     * @author Thomas Lee
     * @version 2017年3月31日 下午2:30:29
     */
    public String format(Date date) {
        return YYYY_MM_DD.format(date);
    }

    /**
     * SimpleDateFormat常用方法：public Date parse(String source) throws ParseException
     * @param date 需要解析的日期字符串
     * @return 解析后的日期
     * @author Thomas Lee
     * @version 2017年3月31日 下午2:30:53
     */
    public Date parse(String date) {
        try {
            return YYYY_MM_DD.parse(date);
        } catch (ParseException e) {
            LOG.error(e.getMessage(), e);
            return null;
        }
    }

    /******************* SimpleDateFormat常用方法 ***********************/

    public static String HHMM(Date date) {
        if (date == null)
            return "";
        return HHMM.format(date);
    }

    public static String LongToString(Long time) {
        String s = "";
        if (time != null) {
            s = SDF2.format(time);
        }
        return s;
    }

    private final static SimpleDateFormat yyyyMMddhhmmssSSS = new SimpleDateFormat("yyyyMMddhhmmssSSS");

    public static String simpleDate(Date date) {
        if (date == null)
            return "";
        return SDF.format(date);
    }

    public static String mmddDate(Date date) {
        if (date == null)
            return "";
        return MMDD.format(date);
    }

    public static String simpleDate1(String date1) {
        if (date1 == null)
            return "";
        return SDF.format(date1);
    }

    public static String simpleDate2(Date date) {
        if (date == null)
            return "";
        return yyyyMMddhhmmssSSS.format(date);

    }

    public static String simpleDate3(Date date) {
        if (date == null)
            return "";
        return HHMM.format(date);

    }

    public static String formatDate1(Date date) {
        if (date == null)
            return "";
        return YYYY_MM_DD.format(date);
    }

    public static Date parseDate2(String date) {
        try {
            return SDF4.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Date StringToDate6(String date) {
        try {
            return YYYY_MM_DD.parse(date);
        } catch (Exception e) {
        }
        return new Date();
    }

    public static String stringDate10(String date) {
        return DateUtils.formatDate1(parseDate2(date));
    }

    /**
     * 取当前时间
     * @return
     */
    public static String getCurrentTime() {

        Calendar calendar = Calendar.getInstance();

        int i = calendar.get(1);
        int j = calendar.get(2) + 1;
        int k = calendar.get(5);
        return "" + i + (j >= 10 ? "" + j : "0" + j) + (k >= 10 ? "" + k : "0" + k);
    }

    /**
     * @Description 计算和指定日期偏差指定天数的日期, 如果指定的日期为 null，则返回和当前日期偏差指定天数的日期.
     * @param startDay
     *            指定的基准日期.
     * @param days
     *            偏差的天数.
     * @return 偏差后的日期 .
     */
    public static Date calculateDate(Date startDay, int days) {
        if (null == startDay) {
            startDay = new Date();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDay);
        cal.add(Calendar.DAY_OF_YEAR, days);
        return cal.getTime();
    }

    public static Date calculateMonth(Date startDay, int months) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(startDay);
        cal.add(Calendar.MONTH, months);
        return cal.getTime();
    }

    /**
     * String 获取当前时间yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static String getCurrentDateTime() {
        return dateToString4(getCurrentDate());
    }

    public static Date getCurrentDate() {
        return new Date(System.currentTimeMillis());
    }

    /**
     * String 获取今天日期yyyyMMdd
     * @return
     */
    public static String getToday() {
        Calendar calendar = Calendar.getInstance();
        return DateUtils.getyyyyMMdd(calendar.getTime());
    }

    /**
     * String 获取明天日期yyyyMMdd
     * @return
     */
    public static String getTomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        return DateUtils.getyyyyMMdd(calendar.getTime());
    }

    /**
     * String 获取几天后日期yyyyMMdd
     * @return
     */
    public static String getDateByDays(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return DateUtils.getyyyyMMdd(calendar.getTime());
    }

    /**
     * String 获取几天后日期yyyy-MM-dd
     * @return
     */
    public static String getDateByDays2(Integer days) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_YEAR, days);
        return DateUtils.getyyyy_MM_dd(calendar.getTime());
    }

    /**
     * 获取几天(days)前的时间
     * @param days
     * @return
     */
    public static Date getDateByBeforeDays(Integer days) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, days);
        return c.getTime();
    }

    /**
     * 将日期格式化为字符串
     * @param date
     * @return
     */
    @Deprecated
    public static String dateToString(Date date, String pattern) {
        if (date == null) {
            return "";
        }
        return new SimpleDateFormat(pattern).format(date);
    }

    public static String getyyyyMMdd(Date date) {
        if (date == null)
            return "";
        return YYYYMMDD.format(date);
    }

    public static String getyyyy_MM_dd(Date date) {
        if (date == null)
            return "";
        return YYYY_MM_DD.format(date);
    }

    public static Date StringToDate(String date) {
        try {
            return YYYYMMDD.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * String 获取时间 yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static String format1(Date date) {
        if (date == null)
            date = new Date();
        return SDF1.format(date);
    }

    /**
     * String 获取时间 yyyy-MM-dd HH:mm
     * @param date
     * @return
     */
    public static String format1NoChangNull(Date date) {
        return null == date ? "" : SDF1.format(date);
    }

    public static String dateToString1(Date date) {
        try {
            return SDF1.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * Date 获取时间 yyyy-MM-dd HH:mm
     * @param String
     * @return Date
     */
    public static Date StringToDate1(String date) {
        try {
            return SDF1.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * Date 获取时间 yyyy/MM/dd
     * @param String
     * @return Date
     */
    public static Date StringToDate5(String date) {
        try {
            return SDF.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Date StringToDateHHmmss(String date) {
        try {
            return SDF5.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * Date 获取时间yyyyMMddHHmm
     * @param String
     *            yyyyMMddHHmm
     * @return Date
     */
    public static Date StringToDate2(String date) {
        try {
            return YYYYMMDDHHMM.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    /**
     * Date 获取时间yyyyMMdd
     * @param String
     *            yyyyMMdd
     * @return Date
     */
    public static Date StringToDate3(String date) {
        try {
            return YYYYMMDD.parse(date);
        } catch (ParseException e) {
        }
        return new Date();
    }

    public static Date StringToDate4(String date) {
        try {
            return SDF2.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 传入date为空时，返回空
     * @param date
     *            Date
     * @return yyyy-MM-dd HH:mm:ss
     */
    public static Date StringToDate7(String date) {
        try {
            return SDF2.parse(date);
        } catch (ParseException e) {
        }
        return null;
    }

    /**
     * 传入date为空时，返回空
     * @param date
     *            Date
     * @return yyyy-MM-dd HH:mm:ss String
     */
    public static String dateToString4(Date date) {
        try {
            return SDF2.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static String dateToString7(Date date) {
        try {
            return SDF7.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static String dateToString8(Date date) {
        try {
            return SDF8.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static String getHHmm(Date date) {
        try {
            return SDF3.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static String getHHmmss(Date date) {
        try {
            return SDF5.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    public static Integer getWeekId(String date) {
        Date d = new Date();
        try {
            d = YYYYMMDD.parse(date);
        } catch (ParseException e) {
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        Integer i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        return i;
    }

    /**
     * 获取周几
     * @param date
     *            yyyyMMdd
     * @return
     */
    public static String getWeek(String date) {
        String week = "";
        switch (getWeekId(date)) {
        case 1:
            week = "星期一";
            break;
        case 2:
            week = "星期二";
            break;
        case 3:
            week = "星期三";
            break;
        case 4:
            week = "星期四";
            break;
        case 5:
            week = "星期五";
            break;
        case 6:
            week = "星期六";
            break;
        case 0:
            week = "星期日";
            break;

        default:
            break;
        }
        return week;
    }

    public static String getAP(String ap) {
        String amPm = "";
        if ("0".equals(ap)) {
            amPm = "上午";
        } else if ("1".equals(ap)) {
            amPm = "下午";
        } else if ("2".equals(ap)) {
            amPm = "全天";
        }
        return amPm;
    }

    /**
     * 时间计算（日）
     * @param date
     * @param n
     * @return
     */
    public static Date getDate(Date date, int n) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(Calendar.DAY_OF_MONTH, n);
        return gc.getTime();
    }

    public static Date getDate(Date date, int field, int n) {
        GregorianCalendar gc = new GregorianCalendar();
        gc.setTime(date);
        gc.add(field, n);
        return gc.getTime();
    }

    public static Date weekToDate(Number n) {
        Integer w = n.intValue();
        Calendar calendar = Calendar.getInstance();
        Integer t = calendar.get(Calendar.DAY_OF_WEEK);
        t = t - 1 == 0 ? 7 : t - 1;
        Integer i = w - t < 0 ? w - t + 7 : w - t;
        calendar.add(Calendar.DAY_OF_MONTH, i);
        return calendar.getTime();
    }

    public static boolean isEqual(Date one, Date another) {
        if (one == null || another == null)
            return false;
        return YYYYMMDD.format(one).equals(YYYYMMDD.format(another));
    }

    // 当前时间是上午还是下午（0：上午；1：下午）
    public static int AM_PM() {
        GregorianCalendar ca = new GregorianCalendar();
        return ca.get(GregorianCalendar.AM_PM);
    }

    public static String getDatePlusTime(String time) {
        String[] ss = time.split(":");
        String hh = ss[0];
        String mm = ss[1];
        if (hh.length() == 1) {
            hh = "0" + hh;
        }
        if (mm.length() == 1) {
            mm = "0" + mm;
        }
        time = hh + ":" + mm + ":00";
        return time;
    }

    public static String format6(Date date) {
        try {
            return SDF6.format(date);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取当前月份的开始时间
     * @author Li Chuanbin
     * @param month
     * @return
     */
    public static Date getBeginTimeOfCurrentMonth(Integer month) {
        Date beginTime = null;
        try {
            beginTime = new SimpleDateFormat("yyyy-MM-dd").parse(Calendar.getInstance().get(Calendar.YEAR) + "-" + month + "-1");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return beginTime;
    }

    /**
     * 获取要查询月份的开始时间
     * @author Li Chuanbin
     * @param year
     *            当前年份
     * @param currentMonth
     *            当前月份
     * @param queriedMonth
     *            要查询月份
     * @return
     */
    public static Date getBeginDateViaYearAndMonth(Integer year, Integer currentMonth, Integer queriedMonth) {
        Date beginDate = null;
        try {
            if (currentMonth < 6) {
                if (queriedMonth >= currentMonth + 7 && queriedMonth <= 12) {
                    beginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((year - 1) + "-" + queriedMonth + "-1 00:00:00");
                } else {
                    beginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year + "-" + queriedMonth + "-1 00:00:00");
                }
            } else {
                beginDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year + "-" + queriedMonth + "-1 00:00:00");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return beginDate;
    }

    /**
     * 获取要查询月份的结束时间
     * @author Li Chuanbin
     * @param year
     *            当前年份
     * @param currentMonth
     *            当前月份
     * @param queriedMonth
     *            要查询月份
     * @return
     */
    public static Date getEndDateViaYearAndMonth(Integer year, Integer currentMonth, Integer queriedMonth) {
        Date endDate = null;
        try {
            if (currentMonth < 6) {
                if (queriedMonth >= currentMonth + 7 && queriedMonth < 12) {
                    endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse((year - 1) + "-" + (queriedMonth + 1) + "-1 00:00:00");
                } else if (12 == queriedMonth) {
                    endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year + "-12-30 23:59:59");
                } else {
                    endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year + "-" + (queriedMonth + 1) + "-1 00:00:00");
                }
            } else if (12 == queriedMonth) {
                endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year + "-12-30 23:59:59");
            } else {
                endDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(year + "-" + (queriedMonth + 1) + "-1 00:00:00");
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return endDate;
    }

    /**
     * 格式化日期.
     * @param date
     *            日期对象.
     * @param formatter
     *            日期格式模板.
     * @return 日期指定格式的字符串表示.
     */
    public static String format(Date date, String formatter) {
        if (null == date) {
            return null;
        }
        SimpleDateFormat dateFormater = new SimpleDateFormat(formatter);
        return dateFormater.format(date);
    }

    public static String showTime(Date createTime) {
        long now = new Date().getTime();
        long time = createTime.getTime();
        String showTime = "";
        int time1 = (int) ((now - time) / 60000); // 相差时间的分钟数
        int day = 24 * 60; // 一天的分钟数
        int hour = 60; // 一小时的分钟数
        if (DateUtils.simpleDate(new Date()).equals(DateUtils.simpleDate(createTime))) {
            if (time1 <= 0) {
                showTime = "刚刚";
            } else if (time1 < hour) {
                showTime = time1 + "分钟前";
            } else if ((time1 / day) < day) {
                showTime = time1 / hour + "小时前";
            }
        } else {
            showTime = DateUtils.mmddDate(createTime);
        }
        return showTime;
    }

    /*
     * 获取当前时间的前一天
     */
    public static String getDayBefore(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, -1);// 把日期往前减一天
        date = calendar.getTime(); // 这个时间就是日期往后推一天的结果
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf1.format(date);
        return dateStr;
    }

    /**
     * @Description 获取几个月前的时间，时间在对应月份的1日凌晨
     * @param date
     *            比较的时间
     * @param diffMonth
     *            差距月数，为1时指当月
     * @return 传入时间对应月数前的时间，1日凌晨
     */
    public static Date getBeforeMonth(Date date, Integer diffMonth) {
        Integer before = 1 - diffMonth;
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MONTH, before); // 对应月份
        String time = cal.get(Calendar.YEAR) + "-" + (cal.get(Calendar.MONTH) + 1) + "-01 00:00:00";
        return StringToDate4(time);
    }

    /**
     * 当天的开始时间
     * @author Li Chuanbin
     * @return
     */
    public static long startOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        Date date = calendar.getTime();
        return date.getTime();
    }

    /**
     * 当天的结束时间
     * @author Li Chuanbin
     * @return
     */
    public static long endOfTodDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 59);
        calendar.set(Calendar.MILLISECOND, 999);
        Date date = calendar.getTime();
        return date.getTime();
    }

}