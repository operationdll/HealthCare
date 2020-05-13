package com.iteroa.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ReadTxt {
	/**
	 * 获取系统配置信息
	 * 
	 * @return
	 */
	public static String getSystem(String fileName) throws Exception {
		InputStream is = null;
		BufferedReader reader = null;
		StringBuffer buffer = new StringBuffer();
		try {
			is = new FileInputStream(System.getProperty("user.dir") + "/" + fileName);
			reader = new BufferedReader(new InputStreamReader(is));
			String line = reader.readLine(); // 读取第一行
			while (line != null) { // 如果 line 为空说明读完了
				buffer.append(line);
				line = reader.readLine(); // 读取下一行
			}
		} finally {
			if (reader != null) {
				reader.close();
			}
			if (is != null) {
				is.close();
			}
		}
		return buffer.toString();
	}
}
