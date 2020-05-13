package com.dao;

import java.util.List;
import java.util.Map;

import com.form.DataReviewForm;
import com.form.FormModel;

/**
 * 数据库查询BaseMapper
 * 
 * @author Daniel Duan
 * 
 */
public interface DataManageMapper {

	// 获取查询信息
	List<DataReviewForm> getDateList(Map<String, Object> map);

	// 查询体检异常信息
	List<FormModel> getALData(Map<String, Object> map);

	// 生化数据更新
	int updShenHua(Map<String, Object> map);

	// 血球数据更新
	int updXueQiu(Map<String, Object> map);

	// 身高体重数据更新
	int updSGTZ(Map<String, Object> map);

	// 血压数据更新
	int updXueYa(Map<String, Object> map);

	// 尿液数据更新
	int updNiaoYe(Map<String, Object> map);

	// 心电数据更新
	int updXinDian(Map<String, Object> map);

	// 本年体检人数
	FormModel getYearNum();

	// 本季度体检人数
	FormModel getSeasonNum();

	// 本月体检人数
	FormModel getMonthNum();

	// 当天体检人数
	FormModel getDayNum();

	// 省年体检数据量
	List<FormModel> getProvinceNum();

	// 查询省体检异常信息 -->
	List<FormModel> getProvinceData(Map<String, Object> map);

}
