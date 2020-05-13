package com.dao;

import java.util.List;
import java.util.Map;

import com.dto.HealthCenterDto;
import com.dto.HislogDto;
import com.dto.UserDto;
import com.form.FormModel;

/**
 * 数据库查询BaseMapper
 * 
 * @author Daniel Duan
 * 
 */
public interface BaseMapper {

	/** 用户管理 */
	// 用户
	UserDto getUser(UserDto userDto);

	// 修改密码
	int updatePWD(UserDto userDto);

	// 用户信息
	List<UserDto> getUserList(Map<String, Object> map);

	// 用户信息总数
	int getUserListCount(Map<String, Object> map);

	// 新增用户信息
	int insertUser(UserDto userDto);

	// 修改用户信息
	int updUser(UserDto userDto);

	// 获取该用户名是否存在
	int getUserCount(Map<String, Object> map);

	/** 卫生所管理 */
	// 条件查询卫生所信息
	List<HealthCenterDto> getAreaList(Map<String, Object> map);

	// 卫生所所有信息
	List<HealthCenterDto> getAllAreaList();

	// 卫生所信息总数
	int getAreaListCount(Map<String, Object> map);

	// 新增卫生所
	int insertHealthCenter(HealthCenterDto healthCenterDto);

	// 修改卫生所
	int updHealthCenter(HealthCenterDto healthCenterDto);

	// 根据id获取卫生所信息
	HealthCenterDto getHealthById(int id);

	// 根据district获取卫生所信息
	List<HealthCenterDto> getHealthByDistrict(Map<String, Object> map);

	// 获取同步数据
	List<FormModel> getSynData(Map<String, Object> map);

	// 根据文件获取同步数据
	List<FormModel> uploadSynData(Map<String, Object> map);

	// 新增省平台上传日志信息
	int insertHislog(HislogDto hislogDto);

	// 获取省平台错误信息
	List<HislogDto> getHislog(Map<String, Object> map);

	// 修改省平台上传日志信息
	int updateHislog(HislogDto hislogDto);

	// 根据id获取卫生所信息
	FormModel getCenterByid(int id);

	// 获取各个卫生院体检数据
	List<FormModel> getCData(Map<String, Object> map);
}
