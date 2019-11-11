package com.okflow.common.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.lang.time.DateFormatUtils;

import com.google.common.base.Strings;

/**
 * 日期工具类
 * 
 * @author xiaofanglin
 * @version 
 */
public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	private static String[] parsePatterns = { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" };

	/** 得到当前日期字符串 格式(yyyy-MM-dd) */
	public static String getDate() {
		return getDate("yyyy-MM-dd");
	}

	/** 得到当前日期字符串 格式(yyyy-MM-dd) pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E" */
	public static String getDate(String pattern) {
		return DateFormatUtils.format(new Date(), pattern);
	}

	/** 得到日期字符串 默认格式(yyyy-MM-dd) pattern可以为："yyyy-MM-dd" "HH:mm:ss" "E" */
	public static String formatDate(Date date, Object... pattern) {
		String formatDate = null;
		if (pattern != null && pattern.length > 0) {
			formatDate = DateFormatUtils.format(date, pattern[0].toString());
		}
		else {
			formatDate = DateFormatUtils.format(date, "yyyy-MM-dd");
		}
		return formatDate;
	}

	/** 得到日期时间字符串，转换格式(yyyy-MM-dd HH:mm:ss) */
	public static String formatDateTime(Date date) {
		return formatDate(date, "yyyy-MM-dd HH:mm:ss");
	}

	/** 得到日期时间字符串，转换格式(yyyy-MM-dd HH:mm:ss) */
	public static String formatDateTime_(Date date) {
		return formatDate(date, "yyyyMMddHHmmss");
	}

	/** 得到当前时间字符串 格式(HH:mm:ss) */
	public static String getTime() {
		return formatDate(new Date(), "HH:mm:ss");
	}

	/** 得到当前日期和时间字符串 格式(yyyy-MM-dd HH:mm:ss) */
	public static String getDateTime() {
		return formatDate(new Date(), "yyyy-MM-dd HH:mm:ss");
	}

	/** 得到当前日期和时间字符串 格式(yyyy年MM月dd日) */
	public static String getDateTime2() {
		return formatDate(new Date(), "yyyy年MM月dd日");
	}

	/** 得到当前日期和时间字符串 格式(yyyy年MM月) */
	public static String getDateTime3() {
		return formatDate(new Date(), "yyyy年MM月");
	}

	/** 得到当前年份字符串 格式(yyyy) */
	public static String getYear() {
		return formatDate(new Date(), "yyyy");
	}

	/** 得到当前月份字符串 格式(MM) */
	public static String getMonth() {
		return formatDate(new Date(), "MM");
	}

	/** 得到当天字符串 格式(dd) */
	public static String getDay() {
		return formatDate(new Date(), "dd");
	}

	/** 得到当前星期字符串 格式(E)星期几 */
	public static String getWeek() {
		return formatDate(new Date(), "E");
	}

	/** 日期格式的转换 yyyy-MM-dd HH:mm:ss */
	public static Date getStrDate(Object date) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date myDate;
		try {
			myDate = dateFormat.parse(date.toString());
			return myDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/** Fri May 06 16:19:17 CST 2016 转换成EEE MMM dd HH:mm:ss zzz yyyy */
	public static Date getStr2Date(Object date) {
		String pattern = "EEE MMM dd HH:mm:ss zzz yyyy";
		SimpleDateFormat df = new SimpleDateFormat(pattern, Locale.US);
		try {
			Date rdate = df.parse(date.toString());
			return rdate;
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		return null;
	}

	/**
	 * 日期型字符串转化为日期 格式
	 * { "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm",
	 * "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm" }
	 */
	public static Date parseDate(Object str) {
		if (str == null) {
			return null;
		}
		try {
			return parseDate(str.toString(), parsePatterns);
		} catch (ParseException e) {
			return null;
		}
	}

	public static Date getDateStart(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 00:00:00");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	public static Date getDateEnd(Date date) {
		if (date == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			date = sdf.parse(formatDate(date, "yyyy-MM-dd") + " 23:59:59");
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 年份加减
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String addYears(String date, int years) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		try {
			Date d = format.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.YEAR, years);
			return format.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 年份加减
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String getDate(int months) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		Date d = new Date();
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		c.add(c.MONTH, months);
		return format.format(c.getTime());
	}

	/**
	 * 年份加减
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String addYearMonth(String date, int years, int months) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		try {
			Date d = format.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.YEAR, years);
			c.add(c.MONTH, months);
			return format.format(c.getTime()) + "-01";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 年份加减
	 * 
	 * @param date
	 * @param years
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String addYearMonth6(String date, int years, int months) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		try {
			Date d = format.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.YEAR, years);
			c.add(c.MONTH, months);
			return format.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 月份加减
	 * 
	 * @param date 日期
	 * @param months
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String addMonths(String date, int months) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = format.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.MONTH, months);
			return format.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 月份加减
	 * 
	 * @param date 日期
	 * @param months
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String addMonths6(String date, int months) {
		DateFormat format = new SimpleDateFormat("yyyy-MM");
		try {
			Date d = format.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.MONTH, months);
			return format.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 日期加减
	 * 
	 * @param date 日期
	 * @param months
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String addDates(String date, int dates) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = format.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.DATE, dates);
			return format.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 当前日期月份加减
	 * 
	 * @param months
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String addMonths(int months) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = format.parse(getDate());
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.MONTH, months);
			return format.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 当前日期月份加减
	 * 
	 * @param months
	 * @return
	 */
	@SuppressWarnings("static-access")
	public static String addMonths6(int months) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = format.parse(getDate());
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.MONTH, months);
			return format.format(c.getTime()).substring(0, 7);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 第一天
	 * 
	 * @param date
	 * @return
	 */
	public static String dateFirst(String date) {
		return date + "-01";
	}

	/**
	 * 年月
	 * 
	 * @param date
	 * @return
	 */
	public static String date6(String date) {
		String tmp = null;
		if ((!Strings.isNullOrEmpty(date)) && (date.length() >= 7)) {
			return date.substring(0, 7);
		}
		return tmp;
	}

	public static int monthsDiff(String beginDate, String endDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		Calendar bef = Calendar.getInstance();
		Calendar aft = Calendar.getInstance();
		try {
			bef.setTime(sdf.parse(endDate));
		} catch (ParseException e) {
		}
		try {
			aft.setTime(sdf.parse(beginDate));
		} catch (ParseException e) {
		}
		int result = aft.get(Calendar.MONTH) - bef.get(Calendar.MONTH);
		int month = (aft.get(Calendar.YEAR) - bef.get(Calendar.YEAR)) * 12;
		return Math.abs(month + result);
	}

	/**
	 * 获取两个字符串年月日期的月数
	 * 
	 * @param startDate 开始年月 YYYY-MM
	 * @param endDate 结束年月 YYYY-MM
	 * @return
	 */
	public static int getMonths(String startDate, String endDate) {
		int months = 0;
		if (!Strings.isNullOrEmpty(startDate) && !Strings.isNullOrEmpty(endDate)) {
			int startYear = Integer.parseInt(startDate.split("-")[0]);
			int startMonth = Integer.parseInt(startDate.split("-")[1]);
			int endYear = Integer.parseInt(endDate.split("-")[0]);
			int endMonth = Integer.parseInt(endDate.split("-")[1]);
			if (endMonth >= startMonth) {
				months = (endYear - startYear) * 12 + (endMonth - startMonth);
			}
			else {
				months = (endYear - startYear) * 12 - (startMonth - endMonth);
			}
		}
		return months;
	}

	/** 取标准执行时间 */
	public static String minStr(String computeTime, String usedStandardDate) {
		if (!Strings.isNullOrEmpty(computeTime) && !Strings.isNullOrEmpty(usedStandardDate)) {
			return computeTime.compareTo(usedStandardDate) <= 0 ? computeTime : usedStandardDate;
		}
		if (!Strings.isNullOrEmpty(computeTime) && Strings.isNullOrEmpty(usedStandardDate)) {
			return computeTime;
		}
		else {
			return usedStandardDate;
		}
	}

	public static String maxStr(String computeTime, String tzqxsj) {
		if (computeTime.compareTo(tzqxsj) > 0) {
			return date6(computeTime);
		}
		else {
			return date6(tzqxsj);
		}
	}

	/**
	 * 把月数转成 x年x月
	 * 
	 * @param months
	 * @return
	 */
	public static String month2Year(int months) {
		int year = 0;
		int month = 0;
		if (months < 12) {
			return months + "月";
		}
		else if (months % 12 == 0) {
			year = months / 12;
			return year + "年";
		}
		else {
			year = months / 12;
			month = months % 12;
			return year + "年" + month + "月";
		}
	}

	@SuppressWarnings("static-access")
	public static String addYearMonthDay(String date, int years, int months, int days) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date d = format.parse(date);
			Calendar c = Calendar.getInstance();
			c.setTime(d);
			c.add(c.YEAR, years);
			c.add(c.MONTH, months);
			c.add(c.DAY_OF_MONTH, days);
			return format.format(c.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
}
