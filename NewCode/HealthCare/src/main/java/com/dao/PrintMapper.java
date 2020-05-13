package com.dao;

import java.util.List;
import java.util.Map;

import com.form.PrintForm;

/**
 * 数据库查询BaseMapper
 * 
 * @author Daniel Duan
 * 
 */
public interface PrintMapper {

	// 获取查询信息
	List<PrintForm> getPrintList(Map<String, Object> map);

	// 高血糖高血压数据
	List<PrintForm> gxtyData(Map<String, Object> map);
}
