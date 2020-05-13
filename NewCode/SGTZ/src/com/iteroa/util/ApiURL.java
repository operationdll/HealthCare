package com.iteroa.util;

/**
 * 获取API地址信息
 * 
 * @author Daniel Duan
 *
 */
public class ApiURL {

	private static String fileURL = "";
	private static String dataBase = "";

	/**
	 * 获取文件存放路径
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getFileURL() throws Exception {
		if ("".equals(fileURL)) {
			fileURL = ReadTxt.getSystem("system.txt");
		}
		return fileURL;
	}

	/**
	 * 获取数据库配置
	 * 
	 * @return
	 * @throws Exception
	 */
	public static String getDataBase() throws Exception {
		if ("".equals(dataBase)) {
			dataBase = ReadTxt.getSystem("database.txt");
		}
		return dataBase;
	}
	
	public static void main(String[] args) {
		try {
			System.out.println(getDataBase());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}