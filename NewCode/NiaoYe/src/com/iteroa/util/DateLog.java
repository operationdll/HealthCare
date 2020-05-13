package com.iteroa.util;

import java.io.File;
import java.io.FileWriter;

/**
 * 数据输出工具类
 * 
 * @author Daniel Duan
 *
 */
public class DateLog {

	public static String FILEURL = "";

	public static void createFile() throws Exception {
		String path = FILEURL;
		File file = new File(path);
		if (file.exists() == true) {
			file.delete();
		}
		file.createNewFile();
	}

	// 写入测试数据
	public static void writeDate(String content) throws Exception {
		FileWriter writer = null;
		try {
			writer = new FileWriter(FILEURL, true);
			writer.write(content);
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}
}
