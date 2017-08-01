package com.kq;

public class ResizeArray {

	public static void main(String[] args) {
		String[] oldArray=new String[3];
		int newSize=5;
		
		String[] newArray=(String[]) resizeArray(oldArray, newSize);
		
		System.out.println("the length of old array:"+oldArray.length);
		System.out.println("the length of new array:"+newArray.length);
	}

	public static Object resizeArray(Object oldArray,int newSize){
		int oldSize=java.lang.reflect.Array.getLength(oldArray);
		Class<?> elementType=oldArray.getClass().getComponentType();
		Object newArray=java.lang.reflect.Array.newInstance(elementType, newSize);
		
		int preserveLength=Math.min(oldSize,newSize);
		if (preserveLength>0) {
			System.arraycopy(oldArray, 0, newArray, 0, preserveLength);
		}
		return newArray;
	}
}
