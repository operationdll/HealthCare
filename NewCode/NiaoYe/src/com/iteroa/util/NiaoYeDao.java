package com.iteroa.util;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

/**
 * 尿液数据库存储类
 * 
 * @author Daniel Duan
 *
 */
public class NiaoYeDao {

	// 获取日之类
	private Logger log = Logger.getLogger(NiaoYeDao.class);

	private Map<String, String> params = new HashMap<String, String>();

	public NiaoYeDao(Map<String, String> params) {
		this.params = params;
		run();
	}

	@SuppressWarnings("static-access")
	private void run() {
		try {
			String sql = "update healthcheckup set field240=?,field239=?,field101=?,"
					+ "field238=?,field242=?,field104=?,field241=?," + "field237=?,field102=?,field103=?,field265=?,"
					+ "field264=?,field261=?,field258=?,field267=?," + "field262=?,field266=?,field260=?,field259=?,"
					+ "field263=?,updateDate=NOW()" + " where pid = (select id from patients where code=?)";
			// 上传数据
			int rnum = DBUtil.excuteUpdate(sql, new Object[] { params.get("field240"), params.get("field239"),
					params.get("field101"), params.get("field238"), params.get("field242"), params.get("field104"),
					params.get("field241"), params.get("field237"), params.get("field102"), params.get("field103"),
					params.get("field265"), params.get("field264"), params.get("field261"), params.get("field258"),
					params.get("field267"), params.get("field262"), params.get("field266"), params.get("field260"),
					params.get("field259"), params.get("field263"), params.get("code") });
			if (rnum == 0) {
				ShowInfo.getInstance(true, "尿液信息").setContext("更新信息失败:Code:" + params.get("code") + "，上传失败请确认条码号");
			} else {
				ShowInfo.getInstance(true, "尿液信息").setContext("更新信息成功:Code:" + params.get("code"));
			}
		} catch (Exception e) {
			log.error("尿液信息错误Code:" + params.get("code") + "，" + e.toString());
			ShowInfo.getInstance(true, "尿液信息").setContext("系统异常Code:" + params.get("code") + "，" + e.toString());
		}
	}

}
