package com.kq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

public class GeneratePDF {

	public static void main(String[] args) {
		
		try {
			OutputStream out=new FileOutputStream(new File("src/com/kq/test.pdf"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
