package com.iteroa.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

/**
 * Api请求类
 * 
 * @author Daniel Duan
 *
 */
public class ApiUtil {

	/**
	 * 发送请求
	 * 
	 * @param urlStr 请求数据的url
	 * @param method POST/GET
	 * @return 返回的JSON数据
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, Object> doSend(String urlStr, String method) throws Exception {
		HttpURLConnection connection = null;
		Map<String, Object> retMap = new HashMap<String, Object>();
		try {
			// 创建连接
			URL url = new URL(urlStr);// 请求地址
			connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setRequestMethod(method);
			connection.setUseCaches(false);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestProperty("Content-Type", "application/json");// 以json的方式传递参数
			connection.setRequestProperty("Accept-Charset", "UTF-8");
			connection.connect();
			// 读取响应
			BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String retStr = ConvertStream2Json(connection.getInputStream());
			if (retStr != null && !"".equals(retStr)) {
				retMap = (Map<String, Object>) JSON.parse(retStr);
			}
			reader.close();
		} finally {
			// 断开连接
			if (connection != null) {
				connection.disconnect();
			}
		}
		return retMap;
	}

	/***
	 * 将流转换成String
	 * 
	 * @param inputStream
	 * @return
	 * @throws Exception
	 */
	private static String ConvertStream2Json(InputStream inputStream) throws Exception {
		String jsonStr = "";
		// ByteArrayOutputStream相当于内存输出流
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		// 将输入流转移到内存输出流中
		while ((len = inputStream.read(buffer, 0, buffer.length)) != -1) {
			out.write(buffer, 0, len);
		}
		// 将内存流转换为字符串
		jsonStr = new String(out.toByteArray());
		return jsonStr;
	}

	public static void main(String[] args) {
		try {
			System.out.println(
					ApiUtil.doSend("http://localhost:8080/system/login.do?userName=admin&password=admin123", "POST"));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
