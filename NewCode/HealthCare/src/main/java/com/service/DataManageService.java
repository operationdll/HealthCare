package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DataManageMapper;
import com.form.DataReviewForm;
import com.form.FormModel;

/**
 * 用户逻辑类
 * 
 * @author Daniel Duan
 * 
 */
@Service
@Transactional // 会把所有public方法加上事务
public class DataManageService {

	@Autowired
	private DataManageMapper dataManageMapper;

	public DataManageMapper getDataManageMapper() {
		return dataManageMapper;
	}

	public void setDataManageMapper(DataManageMapper dataManageMapper) {
		this.dataManageMapper = dataManageMapper;
	}

	public List<DataReviewForm> getDateList(Map<String, Object> map) {
		return dataManageMapper.getDateList(map);
	}

	public List<FormModel> getALData(Map<String, Object> map) {
		return dataManageMapper.getALData(map);
	}

	public int updShenHua(Map<String, Object> map) {
		return dataManageMapper.updShenHua(map);
	}

	public int updXueQiu(Map<String, Object> map) {
		return dataManageMapper.updXueQiu(map);
	}

	public int updSGTZ(Map<String, Object> map) {
		return dataManageMapper.updSGTZ(map);
	}

	public int updXueYa(Map<String, Object> map) {
		return dataManageMapper.updXueYa(map);
	}

	public int updNiaoYe(Map<String, Object> map) {
		return dataManageMapper.updNiaoYe(map);
	}

	public int updXinDian(Map<String, Object> map) {
		return dataManageMapper.updXinDian(map);
	}

	public FormModel getYearNum() {
		return dataManageMapper.getYearNum();
	}

	public FormModel getSeasonNum() {
		return dataManageMapper.getSeasonNum();
	}

	public FormModel getMonthNum() {
		return dataManageMapper.getMonthNum();
	}

	public FormModel getDayNum() {
		return dataManageMapper.getDayNum();
	}

	public List<FormModel> getProvinceNum() {
		return dataManageMapper.getProvinceNum();
	}

	public List<FormModel> getProvinceData(Map<String, Object> map) {
		return dataManageMapper.getProvinceData(map);
	}

}
