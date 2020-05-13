package com.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.BaseMapper;
import com.dto.HislogDto;
import com.dto.UserDto;
import com.form.FormModel;

/**
 * 用户逻辑类
 * 
 * @author Daniel Duan
 * 
 */
@Service
@Transactional // 会把所有public方法加上事务
public class UserService {

	@Autowired
	private BaseMapper baseMapper;

	public BaseMapper getBaseMapper() {
		return baseMapper;
	}

	public void setBaseMapper(BaseMapper baseMapper) {
		this.baseMapper = baseMapper;
	}

	public UserDto getUser(UserDto userDto) {
		return baseMapper.getUser(userDto);
	}

	public int updatePWD(UserDto userDto) {
		return baseMapper.updatePWD(userDto);
	}

	public List<UserDto> getUserList(Map<String, Object> map) {
		return baseMapper.getUserList(map);
	}

	public int getUserListCount(Map<String, Object> map) {
		return baseMapper.getUserListCount(map);
	}

	public int insertUser(UserDto userDto) {
		return baseMapper.insertUser(userDto);
	}

	public int updUser(UserDto userDto) {
		return baseMapper.updUser(userDto);
	}

	public int getUserCount(Map<String, Object> map) {
		return baseMapper.getUserCount(map);
	}

	public List<FormModel> getSynData(Map<String, Object> map) {
		return baseMapper.getSynData(map);
	}

	public List<FormModel> uploadSynData(Map<String, Object> map) {
		return baseMapper.uploadSynData(map);
	}

	public int insertHislog(HislogDto hislogDto) {
		return baseMapper.insertHislog(hislogDto);
	}

	public List<HislogDto> getHislog(Map<String, Object> map) {
		return baseMapper.getHislog(map);
	}

	public int updateHislog(HislogDto hislogDto) {
		return baseMapper.updateHislog(hislogDto);
	}

	public List<FormModel> getCData(Map<String, Object> map) {
		return baseMapper.getCData(map);
	}

}
