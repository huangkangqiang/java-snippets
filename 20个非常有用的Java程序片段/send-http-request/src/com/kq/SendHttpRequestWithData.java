package com.kq;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 发送HTTP请求
 * 
 * @author huang
 *
 */
public class SendHttpRequestWithData {

	public static void main(String[] args) throws IOException {

		URL url = new URL("https://www.baidu.com/");
		sendHttpRequest(url);
	}

	public static void sendHttpRequest(URL url) throws IOException {
		BufferedReader bReader = new BufferedReader(new InputStreamReader(url.openStream()));
		String string = "";
		while (null != (string = bReader.readLine())) {
			System.out.println(string);
		}
	}

}
