package com.dao;

import java.util.List;
import java.util.Map;

import com.dto.PatientDto;

/**
 * 数据库查询BaseMapper
 * 
 * @author Daniel Duan
 * 
 */
public interface PatientMapper {

	// 所有用户信息
	List<PatientDto> getList(Map<String, Object> map);

	// 用户信息
	PatientDto getDetail(int id);

	// 修改居民健康档案
	int updPatientHeath(PatientDto patientDto);

	// 修改个人档案
	int updHealthCase(PatientDto patientDto);

	// 根据uuid获取用户信息
	List<PatientDto> getPatientByUUID(Map<String, Object> map);

	// 根据id删除病人信息1
	public int delPatient1(int id);

	// 根据id删除病人信息2
	public int delPatient2(int id);

	// 根据id删除病人信息3
	public int delPatient3(int id);

	// 根据id删除病人信息4
	public int delPatient4(int id);
	
	//修改省平台体检ID
	public int updPatientMt(Map<String, Object> map);

}
