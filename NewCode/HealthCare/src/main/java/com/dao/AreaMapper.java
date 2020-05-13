package com.dao;

import java.util.List;
import java.util.Map;

import com.dto.AreaDto;

/**
 * 数据库查询BaseMapper
 * 
 * @author Daniel Duan
 * 
 */
public interface AreaMapper {

	// 获取区域信息
	List<AreaDto> getAreaList(Map<String, Object> map);

	// 新增区域信息
	int insertArea(AreaDto areaDto);

	// 修改区域信息
	int updArea(AreaDto areaDto);
	
	// 删除区域信息
	int delArea(Map<String, Object> map);

}
