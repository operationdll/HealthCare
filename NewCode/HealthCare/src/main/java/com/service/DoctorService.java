package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.DoctorMapper;
import com.dto.DoctorSignDto;

/**
 * 区域逻辑类
 * 
 * @author Daniel Duan
 * 
 */
@Service
@Transactional // 会把所有public方法加上事务
public class DoctorService {

	@Autowired
	private DoctorMapper DoctorMapper;

	public DoctorSignDto getDoctorByhcid(int hcid) {
		return DoctorMapper.getDoctorByhcid(hcid);
	}

	public int insertDoctorSign(DoctorSignDto doctorSignDto) {
		return DoctorMapper.insertDoctorSign(doctorSignDto);
	}

	public int updDoctorSign(DoctorSignDto doctorSignDto) {
		return DoctorMapper.updDoctorSign(doctorSignDto);
	}

	public DoctorMapper getDoctorMapper() {
		return DoctorMapper;
	}

	public void setDoctorMapper(DoctorMapper doctorMapper) {
		DoctorMapper = doctorMapper;
	}

}
