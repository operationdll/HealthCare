package com.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 卫生所信息
 * 
 * @author Daniel Duan
 * 
 */
public class HealthCenterDto implements Serializable {

	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 名称
	private String name;
	// 产品代码
	private String productcode;
	// 机构代码
	private String orgid;
	// 省份代码
	private String province;
	// 城市代码
	private String city;
	// 区/县代码
	private String district;
	// 创建日期
	private Date createDate;
	// 修改日期
	private Date updateDate;
	// 区域名称
	private String pname;

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProductcode() {
		return productcode;
	}

	public void setProductcode(String productcode) {
		this.productcode = productcode;
	}

	public String getOrgid() {
		return orgid;
	}

	public void setOrgid(String orgid) {
		this.orgid = orgid;
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
