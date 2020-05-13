package com.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BaseMapper;
import com.dao.PatientMapper;
import com.dto.HealthCenterDto;
import com.dto.PatientDto;

/**
 * 病人逻辑类
 * 
 * @author Daniel Duan
 * 
 */
@Service
@Transactional // 会把所有public方法加上事务
public class PatientService {

	@Autowired
	private PatientMapper baseMapper;

	@Autowired
	private BaseMapper healthCenterMapper;

	public BaseMapper getHealthCenterMapper() {
		return healthCenterMapper;
	}

	public void setHealthCenterMapper(BaseMapper healthCenterMapper) {
		this.healthCenterMapper = healthCenterMapper;
	}

	public void setBaseMapper(PatientMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public List<PatientDto> getList(Map<String, Object> map) {
		return baseMapper.getList(map);
	}

	public PatientDto getDetail(int id) {
		PatientDto patientDto = baseMapper.getDetail(id);
		if (patientDto != null) {
			// 建档单位直接同步卫生院名称
			String fileunit = patientDto.getFileunit();
			if (StringUtils.isEmpty(fileunit)) {
				HealthCenterDto healthCenterDto = healthCenterMapper.getHealthById(patientDto.getHcid());
				patientDto.setFileunit(healthCenterDto.getName());
			}
		}
		return patientDto;
	}

	public int updPatientHeath(PatientDto patientDto) {
		return baseMapper.updPatientHeath(patientDto);
	}

	public int updHealthCase(PatientDto patientDto) {
		return baseMapper.updHealthCase(patientDto);
	}

	public List<PatientDto> getPatientByUUID(Map<String, Object> map) {
		return baseMapper.getPatientByUUID(map);
	}

	public int delPatient(int id) {
		baseMapper.delPatient1(id);
		baseMapper.delPatient2(id);
		baseMapper.delPatient3(id);
		return baseMapper.delPatient4(id);
	}

	public int updPatientMt(Map<String, Object> map) {
		return baseMapper.updPatientMt(map);
	}

}
