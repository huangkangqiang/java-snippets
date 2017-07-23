package com.kq;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 字符串转日期
 * 
 * @author huang
 *
 */
public class StringConvertDate {

	public static void main(String[] args) {
		
		String dateString="2012-01-13 19:25:16";
		
		System.out.println(stringConvertDate1(dateString));
		System.out.println(stringConvertDate2(dateString));
	}
	
	private static Date stringConvertDate1(String dateString){
		try {
			return DateFormat.getDateInstance().parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static Date stringConvertDate2(String dateString){
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:SS");
		try {
			return format.parse(dateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
}
