package com.iteroa.util;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 血球数据库存储类
 * 
 * @author Daniel Duan
 *
 */
public class SGTZDao {

	// 获取日之类
	private Logger log = Logger.getLogger(SGTZDao.class);

	private Map<String, String> params = new HashMap<String, String>();

	public SGTZDao(Map<String, String> params) {
		this.params = params;
		run();
	}

	@SuppressWarnings("static-access")
	private void run() {
		try {
			String sql = "update healthcheckup set field11=?,field12 = ?,field219 = ?,updateDate=NOW()"
					+ " where pid = (select id from patients where code=?)";
			// 身高
			String highStr = params.get("field11");
			// 体重
			String weightStr = params.get("field12");
			// 计算体质数
			String bmi = "";
			if (highStr != null && !"".equals(highStr) && weightStr != null && !"".equals(weightStr)) {
				Double h = Double.parseDouble(highStr);
				Double w = Double.parseDouble(weightStr);
				DecimalFormat df = new DecimalFormat("0.00");
				h = h / 100;
				h = h * h;
				bmi = df.format(w / h);
			}
			// 上传数据
			int rnum = DBUtil.excuteUpdate(sql, new Object[] { highStr, weightStr, bmi, params.get("code") });
			if (rnum == 0) {
				ShowInfo.getInstance(true, "身高体重信息").setContext("更新信息失败:Code:" + params.get("code") + "，上传失败请确认条码号");
			} else {
				ShowInfo.getInstance(true, "身高体重信息").setContext("更新信息成功:Code:" + params.get("code"));
			}
		} catch (Exception e) {
			log.error("身高体重信息错误Code:" + params.get("code") + "，" + e.toString());
			ShowInfo.getInstance(true, "身高体重信息").setContext("系统异常Code:" + params.get("code") + "，" + e.toString());
		}
	}

}
