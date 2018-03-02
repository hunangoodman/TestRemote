package com.treasure.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtils {
	
	public static String getCurrentTime(String format){
		
		Date nowTime=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat(format); 
		return sdf.format(nowTime);
	}
	
	public static String getCurrentTime(){
		return getCurrentTime("yyyy-MM-dd HH:mm:ss");
	}
	
}
