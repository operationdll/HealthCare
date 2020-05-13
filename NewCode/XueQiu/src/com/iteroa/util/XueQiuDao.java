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
public class XueQiuDao {

	// 获取日之类
	private Logger log = Logger.getLogger(XueQiuDao.class);

	private Map<String, String> params = new HashMap<String, String>();

	public XueQiuDao(Map<String, String> params) {
		this.params = params;
		run();
	}

	@SuppressWarnings("static-access")
	private void run() {
		try {
			String sql = "update healthcheckup set field98=?,field234 = ?,field235=?,"
					+ "field236=?,field224=?,field225=?,field226=?," 
					+ "field222=?,field97=?,field227=?,field229=?,"
					+ "field228=?,field243=?,field231=?,field223=?," 
					+ "field99=?,field232=?,field230=?,field233=?,"
					+ " updateDate=NOW()  where pid = (select id from patients where code=?)";
			int rnum = DBUtil.excuteUpdate(sql, new Object[] { params.get("field98"), params.get("field234"),
					params.get("field235"), params.get("field236"), params.get("field224"), params.get("field225"),
					params.get("field226"), params.get("field222"), params.get("field97"), params.get("field227"),
					params.get("field229"), params.get("field228"), params.get("field243"), params.get("field231"),
					params.get("field223"), params.get("field99"), params.get("field232"), params.get("field230"),
					params.get("field233"), params.get("code") });
			if (rnum == 0) {
				ShowInfo.getInstance(false, "血球信息")
						.setContext("更新信息失败:Code:" + params.get("code") + " No:" + params.get("No") + "，上传失败请确认条码号");
			} else {
				ShowInfo.getInstance(false, "血球信息")
						.setContext("更新信息成功:Code:" + params.get("code") + " No:" + params.get("No"));
			}
		} catch (Exception e) {
			log.error("血球信息错误Code:" + params.get("code") + "，" + e.toString());
			ShowInfo.getInstance(false, "血球信息").setContext("系统异常Code:" + params.get("code") + "，" + e.toString());
		}
	}

}
