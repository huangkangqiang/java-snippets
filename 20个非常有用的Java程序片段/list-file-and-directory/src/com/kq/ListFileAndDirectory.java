package com.kq;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 列出文件和目录
 * 
 * @author huang
 *
 */
public class ListFileAndDirectory {

	private static FileOutputStream out;

	public static void main(String[] args) throws IOException {

		out = new FileOutputStream("src/com/kq/file.txt");

		File srcDirectory = new File("D:" + File.separator);
		listFileAndDirectory(srcDirectory);
	}

	public static void listFileAndDirectory(File srcDirectory) throws IOException {
		String string = srcDirectory + "\n";
		out.write(string.getBytes());
		if (srcDirectory.isDirectory()) {
			File[] lists = srcDirectory.listFiles();
			if (lists != null) {
				for (int i = 0; i < lists.length; i++) {
					listFileAndDirectory(lists[i]);
				}
			}
		}
	}

}
