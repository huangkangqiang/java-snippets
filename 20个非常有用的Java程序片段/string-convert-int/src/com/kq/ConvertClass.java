package com.kq;

/**
 * 字符串-整型的相互转换
 * 
 * @author huang
 *
 */
public class ConvertClass {
	public static void main(String[] args) {

		String str = String.valueOf(123);// 整型转换为字符串

		int i = Integer.parseInt(str);// 字符串转换为整型

		System.out.println("str=" + str + "\ni=" + i);
	}
}
