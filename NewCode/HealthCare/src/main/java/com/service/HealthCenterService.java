package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BaseMapper;
import com.dto.HealthCenterDto;
import com.form.FormModel;

/**
 * 卫生所逻辑类
 * 
 * @author Daniel Duan
 * 
 */
@Service
@Transactional // 会把所有public方法加上事务
public class HealthCenterService {

	@Autowired
	private BaseMapper baseMapper;

	public BaseMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(BaseMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public List<HealthCenterDto> getAreaList(Map<String, Object> map) {
		return baseMapper.getAreaList(map);
	}

	public List<HealthCenterDto> getAllAreaList() {
		return baseMapper.getAllAreaList();
	}

	public int getAreaListCount(Map<String, Object> map) {
		return baseMapper.getAreaListCount(map);
	}

	public int insertHealthCenter(HealthCenterDto healthCenterDto) {
		return baseMapper.insertHealthCenter(healthCenterDto);
	}

	public int updHealthCenter(HealthCenterDto healthCenterDto) {
		return baseMapper.updHealthCenter(healthCenterDto);
	}

	public HealthCenterDto getHealthById(int id) {
		return baseMapper.getHealthById(id);
	}

	public List<HealthCenterDto> getHealthByDistrict(String district) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("district", district);
		return baseMapper.getHealthByDistrict(map);
	}

	public FormModel getCenterByid(int id) {
		return baseMapper.getCenterByid(id);
	}
}
