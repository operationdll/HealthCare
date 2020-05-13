package com.iteroa.util;

import java.sql.*;

public class DBUtil {
	static String JDBC_DRIVER = "";
	static String DB_URL = "";
	static String USER = "";
	static String PASS = "";

	private static Connection conn;
	private static PreparedStatement ps;

	/**
	 * 获取连接
	 * 
	 * @return
	 */
	private static Connection getConnection() throws Exception {
		if ("".equals(JDBC_DRIVER)) {
			String database = ApiURL.getDataBase();
			String[] ps = database.split(";");
			JDBC_DRIVER = ps[0];
			DB_URL = ps[1];
			USER = ps[2];
			PASS = ps[3];
		}
		// 注册 JDBC 驱动
		Class.forName(JDBC_DRIVER);
		// 打开链接
		conn = DriverManager.getConnection(DB_URL, USER, PASS);
		return conn;
	}

	/**
	 * 更新操作
	 * 
	 * @param sql
	 * @param obj
	 * @return
	 */
	public static int excuteUpdate(String sql, Object[] obj) throws Exception {
		int result = -1;
		try {
			if (getConnection() == null) {
				return 0;
			}
			ps = conn.prepareStatement(sql);
			for (int i = 0; i < obj.length; i++) {
				ps.setObject(i + 1, obj[i]);
			}
			result = ps.executeUpdate();
		} catch (Exception e) {
			throw new Exception(e);
		} finally {
			closeConn();
		}
		return result;
	}

	/**
	 * 关闭连接
	 */
	private static void closeConn() throws Exception {
		if (ps != null) {
			ps.close();
		}
		if (conn != null) {
			conn.close();
		}
	}

}