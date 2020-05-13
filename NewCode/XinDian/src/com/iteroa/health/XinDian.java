package com.iteroa.health;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.iteroa.util.ApiURL;
import com.iteroa.util.OSSManagement;
import com.iteroa.util.ShowInfo;
import com.iteroa.util.XMLRead;
import com.iteroa.util.XinDianDao;

/**
 * 心电信息获取
 * 
 * @author Daniel Duan
 * @date: 24/04/2019
 *
 */
public class XinDian {
	// 图片上传工具类
	private static OSSManagement oss;

	private static String[] judgeCondition(String zds) {
		String[] strs = zds.split("\n");
		String xdResult = "";
		String others = "";
		for (String str : strs) {
			if (str.indexOf("T波改变") != -1) {
				xdResult += "2,";
			} else if (str.indexOf("心肌梗塞") != -1) {
				xdResult += "3,";
			} else if (str.indexOf("心动过速") != -1) {
				xdResult += "4,";
			} else if (str.indexOf("心动过缓") != -1) {
				xdResult += "5,";
			} else if (str.indexOf("早搏") != -1) {
				xdResult += "6,";
			} else if (str.indexOf("房颤") != -1) {
				xdResult += "7,";
			} else if (str.indexOf("传导阻滞") != -1) {
				xdResult += "8,";
			} else {
				if (!str.startsWith("***")) {
					if (xdResult.indexOf("9,") == -1) {
						xdResult += "9,";
					}
					others += str.split(" ")[1] + ";";
				}
			}
		}
		if (!"".equals(xdResult)) {
			xdResult = xdResult.substring(0, xdResult.length() - 1);
		}
		if (!"".equals(others)) {
			others = others.substring(0, others.length() - 1);
		}
		strs[0] = xdResult;
		strs[1] = others;
		return strs;
	}

	private static String uploadImg(String imgName) throws Throwable {
		String imgUrl = "";
		String localUrl = ApiURL.getFileURL() + "/" + imgName;
		String url = oss.getOSSURL(new File(localUrl), "XinDian");
		if (url != null && !"".equals(url)) {
			imgUrl = url;
		}
		return imgUrl;
	}

	@SuppressWarnings("static-access")
	public static void init() throws Exception {
		List<Map<String, String>> list = XMLRead.getResult();
		// 初始化图片上传工具
		oss = new OSSManagement();
		try {
			for (Map<String, String> m : list) {
				Map<String, String> params = new HashMap<String, String>();
				params.put("field128", "");
				// 心率
				if ("1".equals(m.get("isOk"))) {
					params.put("field127", "1");
				} else {
					String[] result = judgeCondition(m.get("result"));
					params.put("field127", result[0]);
					params.put("field128", result[1]);
				}
				params.put("field77", m.get("xinlv"));
				params.put("code", m.get("code"));
				String imgUrl = uploadImg(m.get("code") + ".JPG");
				if ("".equals(imgUrl)) {
					ShowInfo.getInstance(true, "心电信息").setContext("更新信息失败:Code:" + params.get("code") + "，上传图片失败!");
				} else {
					params.put("field246", imgUrl);
					new XinDianDao(params);
				}
			}
		} catch (Throwable e) {
			throw new Exception(e);
		} finally {
			if (oss != null) {
				oss.Shutdown();
			}
		}
	}
}
