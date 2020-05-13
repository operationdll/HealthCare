package com.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 省平台上传记录信息
 * 
 * @author Daniel Duan
 * 
 */
public class HislogDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 卫生所
	private Integer hcid;
	// 区域ID
	private String regionid;
	// 区域CODE
	private String regioncode;
	// 医生编码
	private String dcode;
	// 医生姓名
	private String dname;
	// 成功条数
	private Integer sucnum;
	// 失败条数
	private Integer errnum;
	// 失败信息
	private String erromsg;
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

	public Integer getHcid() {
		return hcid;
	}

	public void setHcid(Integer hcid) {
		this.hcid = hcid;
	}

	public String getRegionid() {
		return regionid;
	}

	public void setRegionid(String regionid) {
		this.regionid = regionid;
	}

	public String getRegioncode() {
		return regioncode;
	}

	public void setRegioncode(String regioncode) {
		this.regioncode = regioncode;
	}

	public String getDcode() {
		return dcode;
	}

	public void setDcode(String dcode) {
		this.dcode = dcode;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public Integer getSucnum() {
		return sucnum;
	}

	public void setSucnum(Integer sucnum) {
		this.sucnum = sucnum;
	}

	public Integer getErrnum() {
		return errnum;
	}

	public void setErrnum(Integer errnum) {
		this.errnum = errnum;
	}

	public String getErromsg() {
		return erromsg;
	}

	public void setErromsg(String erromsg) {
		this.erromsg = erromsg;
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
