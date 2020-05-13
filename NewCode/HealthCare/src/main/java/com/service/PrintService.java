package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.PrintMapper;
import com.form.PrintForm;

/**
 * 用户逻辑类
 * 
 * @author Daniel Duan
 * 
 */
@Service
@Transactional // 会把所有public方法加上事务
public class PrintService {

	@Autowired
	private PrintMapper baseMapper;

	public PrintMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(PrintMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public List<PrintForm> getPrintList(Map<String, Object> map) {
		return baseMapper.getPrintList(map);
	}

	public List<PrintForm> gxtyData(Map<String, Object> map) {
		return baseMapper.gxtyData(map);
	}

}
