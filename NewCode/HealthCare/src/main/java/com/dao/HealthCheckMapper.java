package com.dao;

import java.util.Map;

import com.dto.HealthCheckDto;
import com.dto.SelfCareDto;
import com.dto.TCMDto;

/**
 * 数据库查询
 * 
 * @author Daniel Duan
 * 
 */
public interface HealthCheckMapper {
	// 体检信息
	HealthCheckDto getHealthCheck(int pid);

	// 修改体检信息
	int updGenerally(HealthCheckDto healthCheckDto);

	// 修改生活方式
	int updLifestyle(HealthCheckDto healthCheckDto);

	// 修改脏器功能
	int updOrgan(HealthCheckDto healthCheckDto);

	// 修改查体
	int updExam(HealthCheckDto healthCheckDto);

	// 修改辅助检查
	int updAssistantExam(HealthCheckDto healthCheckDto);

	// 修改现存健康问题
	int updHealthyProblem(HealthCheckDto healthCheckDto);

	// 修改住院用药
	int updHospitalDrug(HealthCheckDto healthCheckDto);

	// 修改健康评价
	int updHealthyComment(HealthCheckDto healthCheckDto);

	// 根据病人id获取自理能力信息
	SelfCareDto getSelfcare(int pid);

	// 修自理能力
	int updSelfcare(SelfCareDto selfCare);

	// 获取中医体质信息
	TCMDto getTCM(int pid);

	// 修改中医体质
	int updTCM(TCMDto tCMDto);

	// 修改健康评价结果
	int updHealthyResult(HealthCheckDto healthCheckDto);

	// 修改检查员
	int updCP(HealthCheckDto healthCheckDto);

	// 同步脉率心率
	int syndata(Map<String, Object> map);

}
