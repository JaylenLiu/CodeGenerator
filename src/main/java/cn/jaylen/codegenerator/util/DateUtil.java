package cn.jaylen.codegenerator.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author ljl
 * @create 2018-08-24 11:13
 * @desc 时间工具类
 **/
public final class DateUtil {
    /**
     * 年(yyyy)
     */
    public static final String YEAR = "yyyy";

    /**
     * 年-月(yyyy-MM)
     */
    public static final String YEAR_MONTH = "yyyy-MM";

    /**
     * 年-月-日(yyyy-MM-dd)
     */
    public static final String YEAR_MONTH_DAY = "yyyy-MM-dd";

    /**
     * 年月日(yyyyMMdd)
     */
    public static final String YEAR_MONTH_DAY_SIMPLE = "yyyyMMdd";

    /**
     * 年-月-日 小时(yyyy-MM-dd HH)
     */
    public static final String YEAR_MONTH_DAY_HOUR = "yyyy-MM-dd HH";

    /**
     * 年-月-日 小时(yyyy-MM-dd HH)中文输出
     */
    public static final String YEAR_MONTH_DAY_HOUR_CN = "yyyy年MM月dd日HH时";

    /**
     * 年-月-日 小时:分钟(yyyy-MM-dd HH:mm)中文输出
     */
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE_CN = "yyyy年MM月dd日 HH:mm";

    /**
     * 年-月-日 小时:分钟(yyyy-MM-dd HH:mm)
     */
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE = "yyyy-MM-dd HH:mm";

    /**
     * 年-月-日 小时:分钟:秒钟(yyyy-MM-dd HH:mm:ss)
     */
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND = "yyyy-MM-dd HH:mm:ss";

    /**
     * 年月日小时分钟秒钟(yyyyMMddHHmmss)
     */
    public static final String YEAR_MONTH_DAY_HOUR_MINUTE_SECOND_SIMPLE = "yyyyMMddHHmmss";

    /**
     * 小时:分钟:秒钟(HH:mm:ss)
     */
    public static final String HOUR_MINUTE_SECOND = "HH:mm:ss";

    /**
     * 小时:分钟(HH:mm)
     */
    public static final String HOUR_MINUTE = "HH:mm";

    /**
     * 月.日(M.d)
     */
    public static final String MONTH_DAY = "M.d";

    /**
     * 一天的秒数
     */
    private static final int DAY_SECOND = 24 * 60 * 60;

    /**
     * 一小时的秒数
     */
    private static final int HOUR_SECOND = 60 * 60;

    /**
     * 一分钟的秒数
     */
    private static final int MINUTE_SECOND = 60;


    public static String format (Date date, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        if (date == null) {
            return sdf.format(new Date());
        } else {
            return sdf.format(date);
        }
    }

    public static String formatToString(String date, String pattern) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(sdf.parse(date));
    }
}
