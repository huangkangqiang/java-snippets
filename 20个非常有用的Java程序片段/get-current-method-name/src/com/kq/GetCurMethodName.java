package com.kq;

/**
 * 得到当前方法的名字
 * 
 * @author huang
 *
 */
public class GetCurMethodName {

	public static void main(String[] args) {
	
		String methodName=Thread.currentThread().getStackTrace()[1].getMethodName();
		
		System.out.println(methodName);
	}
}
