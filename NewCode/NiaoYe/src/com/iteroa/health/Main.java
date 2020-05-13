package com.iteroa.health;

import org.apache.log4j.Logger;

import com.iteroa.util.DateLog;
import com.iteroa.util.ReadTxt;
import com.iteroa.util.ShowInfo;

/**
 * 系统主要入口类
 * 
 * @author Daniel Duan
 *
 */
public class Main {

	@SuppressWarnings("static-access")
	public static void main(String[] args) {
		Logger log = Logger.getLogger(Main.class);
		try {
			// 获取系统参数
			String system = ReadTxt.getSystem("system.txt");
			String[] ps = system.split(";");
			if (ps != null && ps.length > 0) {
				// 创建数据输出文件
				DateLog.FILEURL = ps[2];
				DateLog.createFile();
				new NiaoYe().init(ps[0], Integer.parseInt(ps[1]));
			}
			ShowInfo.getInstance(true, "尿液信息").setContext("尿液信息上传程序已启动");
		} catch (Exception e) {
			log.error("尿液信息报错:", e);
			ShowInfo.getInstance(true, "尿液信息").setContext("尿液信息系统错误:" + e.toString());
		}
	}

}
