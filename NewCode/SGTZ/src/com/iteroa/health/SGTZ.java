package com.iteroa.health;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.iteroa.util.ApiURL;
import com.iteroa.util.SGTZDao;

/**
 * 身高体重信息获取
 * 
 * @author Daniel Duan
 * @date: 24/04/2019
 *
 */
public class SGTZ {

	@SuppressWarnings("resource")
	public static void init() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(ApiURL.getFileURL()));// 换成你的文件名
		String line = null;
		while ((line = reader.readLine()) != null) {
			if (!"".equals(line)) {
				Map<String, String> params = new HashMap<String, String>();
				String[] data = line.split(":");
				String barcode = data[0];
				String tz = data[1];
				String sg = data[2];
				if (tz.indexOf("0") == 0) {
					tz = tz.substring(1, tz.length());
				}
				if (sg.indexOf("0") == 0) {
					sg = sg.substring(1, sg.length());
				}
				params.put("code", barcode);
				params.put("field11", sg);
				params.put("field12", tz);
				new SGTZDao(params);
			}
		}
	}
}
