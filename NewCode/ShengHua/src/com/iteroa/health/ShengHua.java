package com.iteroa.health;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;

import com.iteroa.util.ApiURL;
import com.iteroa.util.ShengHuaDao;

/**
 * 生化信息获取
 * 
 * @author Daniel Duan
 * @date: 24/04/2019
 *
 */
public class ShengHua {

	@SuppressWarnings("resource")
	public static void init() throws Exception {
		BufferedReader reader = new BufferedReader(new FileReader(ApiURL.getFileURL()));// 换成你的文件名
		reader.readLine();// 第一行信息，为标题信息，不用，如果需要，注释掉
		String line = null;
		int num = 0;
		Map<String, String> params = new HashMap<String, String>();
		while ((line = reader.readLine()) != null) {
			String item[] = line.split(",");
			String code = item[2];
			String itemName = item[3].trim();
			String value = item[4].trim();
			// 血清谷丙转氨酶
			if ("ALT".equals(itemName)) {
				params.put("field114", value);
			}
			// 血清谷草转氨酶
			if ("AST".equals(itemName)) {
				params.put("field115", value);
			}
			// 总胆红素
			if ("TBIL".equals(itemName)) {
				params.put("field117", value);
			}
			// 血尿素氮
			if ("BUN".equals(itemName)) {
				params.put("field120", value);
			}
			// 血清肌酐
			if ("Crea".equals(itemName)) {
				params.put("field119", value);
			}
			// 血糖
			if ("GLU".equals(itemName)) {
				params.put("field106", value);
			}
			// 总胆固醇
			if ("CHO".equals(itemName)) {
				params.put("field123", value);
			}
			// 甘油三酯
			if ("TG".equals(itemName)) {
				params.put("field124", value);
			}
			// 血清高密度
			if ("HDL".equals(itemName)) {
				params.put("field126", value);
			}
			// 血清低密度
			if ("LDL".equals(itemName)) {
				params.put("field125", value);
			}
			if (num < 9) {
				num++;
			} else {
				code = code.replaceAll("\\t", "").replaceAll("\"", "");
				params.put("code", code);
				new ShengHuaDao(params);
				num = 0;
				params = new HashMap<String, String>();
			}
		}
	}
}
