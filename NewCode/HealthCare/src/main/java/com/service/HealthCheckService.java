package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.HealthCheckMapper;
import com.dao.PatientMapper;
import com.dto.HealthCheckDto;
import com.dto.PatientDto;
import com.dto.SelfCareDto;
import com.dto.TCMDto;

/**
 * 健康体检逻辑类
 * 
 * @author Daniel Duan
 * 
 */
@Service
@Transactional // 会把所有public方法加上事务
public class HealthCheckService {

	@Autowired
	private HealthCheckMapper baseMapper;

	@Autowired
	private PatientMapper patientMapper;

	public PatientMapper getPatientMapper() {
		return patientMapper;
	}

	public void setPatientMapper(PatientMapper patientMapper) {
		this.patientMapper = patientMapper;
	}

	public HealthCheckMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(HealthCheckMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public HealthCheckDto getHealthCheck(int pid) {
		return baseMapper.getHealthCheck(pid);
	}

	public int updGenerally(HealthCheckDto healthCheckDto) {
		return baseMapper.updGenerally(healthCheckDto);
	}

	public int updLifestyle(HealthCheckDto healthCheckDto) {
		return baseMapper.updLifestyle(healthCheckDto);
	}

	public int updOrgan(HealthCheckDto healthCheckDto) {
		return baseMapper.updOrgan(healthCheckDto);
	}

	public int updExam(HealthCheckDto healthCheckDto) {
		return baseMapper.updExam(healthCheckDto);
	}

	public int updAssistantExam(HealthCheckDto healthCheckDto) {
		return baseMapper.updAssistantExam(healthCheckDto);
	}

	public int updHealthyProblem(HealthCheckDto healthCheckDto) {
		return baseMapper.updHealthyProblem(healthCheckDto);
	}

	public int updHospitalDrug(HealthCheckDto healthCheckDto) {
		return baseMapper.updHospitalDrug(healthCheckDto);
	}

	public int updHealthyComment(HealthCheckDto healthCheckDto) {
		return baseMapper.updHealthyComment(healthCheckDto);
	}

	public SelfCareDto getSelfcare(int pid) {
		return baseMapper.getSelfcare(pid);
	}

	public int updSelfcare(SelfCareDto selfCare) {
		return baseMapper.updSelfcare(selfCare);
	}

	public TCMDto getTCM(int pid) {
		return baseMapper.getTCM(pid);
	}

	public int updTCM(TCMDto tCMDto) {
		return baseMapper.updTCM(tCMDto);
	}

	public int updHealthyResult(HealthCheckDto healthCheckDto) {
		return baseMapper.updHealthyResult(healthCheckDto);
	}

	public int updCP(HealthCheckDto healthCheckDto, PatientDto patientDto) {
		int num = 0;
		num = num + baseMapper.updCP(healthCheckDto);
		num = num + patientMapper.updPatientHeath(patientDto);
		return num;
	}

	public int updAllCP(HealthCheckDto healthCheckDto, String ids, String fileuser, String doctor) {
		int i = 0;
		String[] idarr = ids.split(",");
		for (String id : idarr) {
			healthCheckDto.setPid(Integer.parseInt(id));
			i = i + baseMapper.updCP(healthCheckDto);
			PatientDto patientDto = patientMapper.getDetail(healthCheckDto.getPid());
			patientDto.setFileuser(fileuser);
			patientDto.setDoctor(doctor);
			i = i + patientMapper.updPatientHeath(patientDto);
		}
		return i;
	}

	public int syndata(List<PatientDto> patientDtos, Integer synnum) {
		int i = 0;
		if (patientDtos != null && patientDtos.size() > 0) {
			String ids = "";
			for (PatientDto p : patientDtos) {
				ids = ids + p.getId() + ",";
			}
			ids = ids.substring(0, ids.length() - 1);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ids", ids);
			map.put("synnum", synnum);
			i = baseMapper.syndata(map);
		} else {
			i = 1;
		}
		return i;
	}

}
