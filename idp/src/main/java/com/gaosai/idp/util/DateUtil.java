package com.gaosai.idp.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
	public static String getCurTime(){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	    Long time = Long.valueOf( getCurTimeStamp() + "000" );  
	    String d = format.format(time);  
	    return d;
	}
	public static String getCurTime1(){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	    Long time = Long.valueOf( getCurTimeStamp() + "000" );  
	    String d = format.format(time);  
	    return d;
	}
	public static int getCurTimeStamp(){
		return (int)( System.currentTimeMillis() / 1000 );
	}
	public static String getTimeFromTimeStamp( int l ){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");  
	    Long time = Long.valueOf( l + "000" );  
	    String d = format.format(time);  
	    return d;
	}
	public static String getDateFromTimeStamp( int l ){
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd");  
	    Long time = Long.valueOf( l + "000" );  
	    String d = format.format(time);  
	    return d;
	}
	public static int getTimeStampFromTime( String time ) throws ParseException {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    Date date=simpleDateFormat .parse( time );
	    int timeStemp = (int)( date.getTime() / 1000 );
	    return timeStemp;
	}
	public static int getTimeStampFromDate( String time ) throws ParseException {
		SimpleDateFormat simpleDateFormat =new SimpleDateFormat("yyyy-MM-dd");
	    Date date=simpleDateFormat .parse( time );
	    int timeStemp = (int)( date.getTime() / 1000 );
	    return timeStemp;
	}

	public static String formatData( String all ){
		String ans = "";

		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date date = null;
		try {
			date = format.parse(all);
		}catch ( Exception e ){
			return all;
		}
		Calendar calendar = Calendar.getInstance();
		calendar.setTime( date );

		int oldY = calendar.get( Calendar.YEAR ),
				oldM = calendar.get( Calendar.MONTH ),
				oldDay = calendar.get( Calendar.DAY_OF_MONTH );

		Calendar newCa = Calendar.getInstance();
		int newY = newCa.get( Calendar.YEAR ),
				newM = newCa.get( Calendar.MONTH ),
				newD = newCa.get( Calendar.DAY_OF_MONTH );
		if( oldY == newY &&
				oldM == newM &&
				oldDay == newD ){
			format = new SimpleDateFormat( "HH:mm" );
			ans = format.format( date );
		}else{
			format = new SimpleDateFormat( "MM-dd" );
			ans = format.format( date );
		}

		return  ans;
	}

	public static String _format( String all ){
		SimpleDateFormat format = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss" );
		Date date = null;
		try {
			date = format.parse(all);
		}catch ( Exception e ){
			return all;
		}
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		String dateString = formatter.format(date);
		return  dateString;
	}
	
	// 获得某天0点时间
	public static int getTimesmorning( int timestamp ) {
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis( timestamp );
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}
	
	// 获得当天0点时间
	public static int getTimesmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得当天24点时间
	public static int getTimesnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR_OF_DAY, 24);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得本周一0点时间
	public static int getTimesWeekmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得本周日24点时间
	public static int getTimesWeeknight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return (int) ((cal.getTime().getTime() + (7 * 24 * 60 * 60 * 1000)) / 1000);
	}

	// 获得本月第一天0点时间
	public static int getTimesMonthmorning() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMinimum(Calendar.DAY_OF_MONTH));
		return (int) (cal.getTimeInMillis() / 1000);
	}

	// 获得本月最后一天24点时间
	public static int getTimesMonthnight() {
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
		cal.set(Calendar.HOUR_OF_DAY, 24);
		return (int) (cal.getTimeInMillis() / 1000);
	}
	
	public static int getMonth(){
		Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.MONTH) + 1;
	    return month;
	}
	public static int getYear(){
		Calendar cal = Calendar.getInstance();
	    int month = cal.get(Calendar.YEAR);
	    return month;
	}
	public static int getMonthDay(){
		Calendar cal = Calendar.getInstance();
		int dom = cal.get(Calendar.DAY_OF_MONTH);
		return dom;
	}
	public static int getMonthLastDay( int m ){
		if( m == 2 ){
			return 28;
		}
		if( m == 1 || m == 3 || m== 5 || m == 7|| m== 8 || m== 10|| m== 12 ){
			return 31;
		}
		return 30;
	}
	

}
