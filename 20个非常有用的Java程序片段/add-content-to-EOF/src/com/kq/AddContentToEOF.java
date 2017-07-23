package com.kq;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 向文件末尾添加内容
 * 
 * @author huang
 *
 */
public class AddContentToEOF {

	public static void main(String[] args) {

		String filename = "src/com/kq/test.txt";
		String content = "Hello World\n";

		addContentToEOF(filename, content);
	}

	private static void addContentToEOF(String filename, String content) {

		BufferedWriter out = null;
		try {
			out = new BufferedWriter(new FileWriter(filename, true));
			out.write(content);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
