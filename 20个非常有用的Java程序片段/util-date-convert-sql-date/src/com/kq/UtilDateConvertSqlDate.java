package com.kq;


/**
 * 将util.Date转换成sql.Date
 * 
 * @author huang
 *
 */
public class UtilDateConvertSqlDate {

	public static void main(String[] args) {
		
		UtilDateConvertSqlDate utilDateConvertSqlDate=new UtilDateConvertSqlDate();

		java.util.Date utilDate=new java.util.Date();
		System.out.println(utilDateConvertSqlDate.utilDateConvertSqlDate(utilDate));
	}
	
	public java.sql.Date utilDateConvertSqlDate(java.util.Date utilDate){
		return new java.sql.Date(utilDate.getTime());
	}

}
