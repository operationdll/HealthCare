package com.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dao.AreaMapper;
import com.dto.AreaDto;

/**
 * 区域逻辑类
 * 
 * @author Daniel Duan
 * 
 */
@Service
@Transactional // 会把所有public方法加上事务
public class AreaService {

	@Autowired
	private AreaMapper areaMapper;

	public AreaMapper getAreaMapper() {
		return areaMapper;
	}

	public void setAreaMapper(AreaMapper areaMapper) {
		this.areaMapper = areaMapper;
	}

	public List<AreaDto> getAreaList(Map<String, Object> map) {
		return areaMapper.getAreaList(map);
	}

	public int insertArea(AreaDto areaDto) {
		return areaMapper.insertArea(areaDto);
	}

	public int updArea(AreaDto areaDto, String oldCode) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("oldCode", oldCode);
		int num = 0;
		if (!StringUtils.isEmpty(oldCode) && !areaDto.getCode().equals(oldCode)) {
			if (3 != areaDto.getLevel()) {
				map.put("level", "3");
				areaMapper.delArea(map);
				map.put("level", "2");
				areaMapper.delArea(map);
				num = areaMapper.updArea(areaDto);
			} else {
				num = areaMapper.updArea(areaDto);
			}
		} else {
			num = areaMapper.updArea(areaDto);
		}
		return num;
	}

}
