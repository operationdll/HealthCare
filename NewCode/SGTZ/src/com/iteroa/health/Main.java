package com.iteroa.health;

import org.apache.log4j.Logger;

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
			SGTZ.init();
		} catch (Exception e) {
			log.error("身高体重信息报错:", e);
			ShowInfo.getInstance(true, "身高体重信息").setContext("身高体重信息系统错误:" + e.toString());
		}
	}

}
