package com.iteroa.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 血球数据库存储类
 * 
 * @author Daniel Duan
 *
 */
public class ShengHuaDao {

	// 获取日之类
	private Logger log = Logger.getLogger(ShengHuaDao.class);

	private Map<String, String> params = new HashMap<String, String>();

	public ShengHuaDao(Map<String, String> params) {
		this.params = params;
		run();
	}

	@SuppressWarnings("static-access")
	private void run() {
		try {
			String sql = "update healthcheckup set field114=?,field115=?,field117=?,"
					+ "field120=?,field119=?,field106=?,field123=?,"
					+ "field124=?,field126=?,field125=?,updateDate=NOW()"
					+ " where pid = (select id from patients where code=?)";
			// 上传数据
			int rnum = DBUtil.excuteUpdate(sql,
					new Object[] { params.get("field114"), params.get("field115"), params.get("field117"),
							params.get("field120"), params.get("field119"), params.get("field106"),
							params.get("field123"), params.get("field124"), params.get("field126"),
							params.get("field125"), params.get("code") });
			if (rnum == 0) {
				ShowInfo.getInstance(true, "生化信息").setContext("更新信息失败:Code:" + params.get("code") + "，上传失败请确认条码号");
			} else {
				ShowInfo.getInstance(true, "生化信息").setContext("更新信息成功:Code:" + params.get("code"));
			}
		} catch (Exception e) {
			log.error("生化信息错误Code:" + params.get("code") + "，" + e.toString());
			ShowInfo.getInstance(true, "生化信息").setContext("系统异常Code:" + params.get("code") + "，" + e.toString());
		}
	}

}
