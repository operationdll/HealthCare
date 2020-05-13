package com.dao;

import com.dto.DoctorSignDto;

/**
 * 数据库查询
 * 
 * @author Daniel Duan
 * 
 */
public interface DoctorMapper {

	/**
	 * 根据hicd查询家医签约数据
	 * 
	 * @param hcid
	 * @return
	 */
	DoctorSignDto getDoctorByhcid(int hcid);

	/**
	 * 新增家医签约信息
	 * 
	 * @param doctorSignDto
	 * @return
	 */
	int insertDoctorSign(DoctorSignDto doctorSignDto);

	/**
	 * 修改家医签约信息
	 * 
	 * @param doctorSignDto
	 * @return
	 */
	int updDoctorSign(DoctorSignDto doctorSignDto);

}
