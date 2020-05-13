package com.iteroa.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 数据库存储类
 * 
 * @author Daniel Duan
 *
 */
public class XinDianDao {

	// 获取日之类
	private Logger log = Logger.getLogger(XinDianDao.class);

	private Map<String, String> params = new HashMap<String, String>();

	public XinDianDao(Map<String, String> params) {
		this.params = params;
		run();
	}

	@SuppressWarnings("static-access")
	private void run() {
		try {
			String sql = "update healthcheckup set field128=?,field127 = ?,field77 = ?,"
					+ "field246 = ?,updateDate=NOW()  where pid = (select id from patients where code=?)";
			// 上传数据
			int rnum = DBUtil.excuteUpdate(sql, new Object[] { params.get("field128"), params.get("field127"),
					params.get("field77"), params.get("field246"), params.get("code") });
			if (rnum == 0) {
				ShowInfo.getInstance(true, "心电信息").setContext("更新信息失败:Code:" + params.get("code") + "，上传失败请确认条码号");
			} else {
				ShowInfo.getInstance(true, "心电信息").setContext("更新信息成功:Code:" + params.get("code"));
			}
		} catch (Exception e) {
			log.error("心电信息错误Code:" + params.get("code") + "，" + e.toString());
			ShowInfo.getInstance(true, "心电信息").setContext("系统异常Code:" + params.get("code") + "，" + e.toString());
		}
	}

}
