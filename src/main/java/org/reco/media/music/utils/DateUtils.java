package org.reco.media.music.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 工具类：日期工具
 * @author zhangsl
 */
public class DateUtils {
	
	/**
	 * 是否超过一周
	 * 
	 * @param addtime
	 * @param now
	 * @return
	 */
	public static boolean moreWeek(Date addtime, Date now) {
		if (now == null)
			now = new Date();
	    Calendar calendar = Calendar.getInstance();  //得到日历  
	    calendar.setTime(addtime);//把当前时间赋给日历  
	    calendar.add(Calendar.DAY_OF_MONTH, 7);  //设置为7天前  
	    Date after7days = calendar.getTime();   //得到7天后的时间  
	    return after7days.getTime() < now.getTime();
	}  
	
	/**
	 * Date转换成String类型，格式为"yyyy-MM-dd"
	 * @param date
	 * @return
	 */
	public static String date2Str(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(date != null){
			String str = sdf.format(date);
			return str;
		}
		return null;
	}
	
	/**
	 * Date转换成String类型，格式为"yyyyMMdd"
	 * @param date
	 * @return
	 */
	public static String date2Str2(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		if(date != null){
			String str = sdf.format(date);
			return str;
		}
		return null;
	}
	
	/**
	 * String转换成Date，格式为"yyyy-MM-dd".
	 * @param str"yyyy-MM-dd"
	 * @return
	 */
	public static Date str2Date(String str){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return date;
	}
	
	/**
	 * 将返回的字符串"/Date(1460908800000)/"，转成Date类型
	 * @param str
	 * @return
	 */
	public static Date formatDate(String  str){
		
		String str2 = "";
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(str)){
			str = str.trim();
			if (str != null && !"".equals(str)) {
				for (int i = 0; i < str.length(); i++) {
					if (str.charAt(i) >= 48 && str.charAt(i) <= 57) {
						str2 += str.charAt(i);
					}
				}
			}
		}
		
		if(org.apache.commons.lang3.StringUtils.isNotEmpty(str2)){
			Long l = Long.valueOf(str2);
			Date d = new Date(l);
			return d;
		}
		
		return null;
	}
	/**
	 * 将返回的字符串"/Date(1460908800000)/"，转成String类型
	 * @param str
	 * @return Sring类型的日期
	 */
	public static String formatLongDate2Str(String str){
		String strDate = "";
		try {
			if(StringUtils.isNoneEmpty(str)){
				Pattern p = Pattern.compile("[^0-9]");
				Matcher longDate=p.matcher(str);
				strDate = date2Str(new Date(Long.valueOf(longDate.replaceAll(""))));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strDate;
	}
	
	public static String strT2Str(String date){
		if(StringUtils.isBlank(date)){
			return null;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
			format.parse(date);
			return	format1.format(format.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
			return date.substring(0,date.indexOf("T"));
		}
		
	}
	
	public static String strT2Str(String date,String format2){
		if(StringUtils.isBlank(date)){
			return null;
		}
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			SimpleDateFormat format1 = new SimpleDateFormat(format2);
			format.parse(date);
			return	format1.format(format.parse(date));
		} catch (Exception e) {
			e.printStackTrace();
			return date.substring(0,date.indexOf("T"));
		}
		
	}
	
	/**
	 * 根据传入格式格式化日期
	 * @param str
	 * @return
	 */
	public static Date str2Date(String str,String fomat){
		SimpleDateFormat sdf = new SimpleDateFormat(fomat);  
		Date date = null;
		try {
			date = sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}  
		return date;
	}
	
	/**
	 * 毫秒转Date
	 * @return
	 */
	public static Date formatLongToDate(Long msec, String format) {
		 SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
	     String d = simpleDateFormat.format(msec);
	     Date date = null;
	     try {
	    	 date = simpleDateFormat.parse(d); 
		} catch (ParseException e) {
			e.printStackTrace();
		}
	     return date;
	}
	
	/**
	 * 将yyyy-MM-dd格式的字符串日期转化成/Date(long)/类型
	 * @param str
	 * @return
	 */
	public static String formatDateToLong(String date){
		String temp = "/Date(temp)/";
		try {
			DateFormat dateFormat1 = new SimpleDateFormat("yyyy-MM-dd");
			Date date2 = dateFormat1.parse(date);
			String longDate = new Long(date2.getTime()).toString();
			String re = temp.replace("temp", longDate);
			return re;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) {
//		System.err.println(formatLongDate2Str("/Date(1490112000000)/"));
//		System.err.println(str2Date("2017-07-03","yyyy-MM-dd HH:mm:ss"));
//		System.out.println(DateUtils.date2Str(new Date(DateUtils.str2Date("2017-9-13","yyyy-MM-dd").getTime()+(24*60*60*1000l))));
	}
	
    /**
     * 被比较时间是否是 当前时间的n天内 （此处时间为n - n+1天） 
     * @param date 当前时间
     * @param parse 被比较时间的时间戳
     * @param i n天
     * @return
     */
	public static boolean isNDaysAgo(Date date, long parse, int n) {
		Date now =  str2Date(DateUtils.date2Str(date),"yyyy-MM-dd");
		long nDayAgosmils = org.apache.commons.lang3.time.DateUtils.addDays(now, -n).getTime();
		return parse>=nDayAgosmils;
	}
	
	/**
	 * 获取几天后的日期
	 * @param d
	 * @param day
	 * @return
	 */
	public static Date addDays(Date date, int days) {
		Calendar now = Calendar.getInstance();
		now.setTime(date);
		now.set(Calendar.DATE, now.get(Calendar.DATE) + days);
		return now.getTime();
	}

	/**
	 * 获取几个月后的日期
	 * @param create_date
	 * @param monthes 几个月
	 * @return
	 */
    public static Date addMonths(Date date, int monthes) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.MONTH, monthes);
		return calendar.getTime();
    }
    
    /**
     * 获取几年后的日期
     * @param date
     * @param years
     * @return
     */
    public static Date addYears(Date date, int years) {
    	Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.YEAR, years);
		return calendar.getTime();
    }
}
