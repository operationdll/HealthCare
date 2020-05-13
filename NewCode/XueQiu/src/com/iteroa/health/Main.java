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
				// 创建数据输出文件
				DateLog.createFile();
				new XueQiu().init(ps[0], Integer.parseInt(ps[1]));
			}
		} catch (Exception e) {
			log.error("血球信息报错:", e);
			ShowInfo.getInstance(false,"血球信息").setContext("血球信息系统错误:" + e.toString());
		}
	}

}
