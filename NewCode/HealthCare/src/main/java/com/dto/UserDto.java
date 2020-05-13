package com.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户信息
 * 
 * @author Daniel Duan
 * 
 */
public class UserDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 用户名
	private String userName;
	// 密码
	private String userPassword;
	// 省份代码
	private String province;
	// 城市代码
	private String city;
	// 区/县代码
	private String district;
	// 卫生所
	private Integer hcid;
	// 卫生所名称
	private String healthName;
	// 创建日期
	private Date createDate;
	// 修改日期
	private Date updateDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public Integer getHcid() {
		return hcid;
	}

	public void setHcid(Integer hcid) {
		this.hcid = hcid;
	}

	public String getHealthName() {
		return healthName;
	}

	public void setHealthName(String healthName) {
		this.healthName = healthName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

}
