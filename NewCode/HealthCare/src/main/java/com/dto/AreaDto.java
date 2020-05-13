package com.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 区域信息
 * 
 * @author Daniel Duan
 * 
 */
public class AreaDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 区域代码
	private String code;
	// 区域名称
	private String name;
	// 全区域名称
	private String pname;
	// 上级区域代码
	private String pcode;
	// 接口区域代码
	private String hcode;
	// 层级
	private Integer level;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public String getPcode() {
		return pcode;
	}

	public void setPcode(String pcode) {
		this.pcode = pcode;
	}

	public String getHcode() {
		return hcode;
	}

	public void setHcode(String hcode) {
		this.hcode = hcode;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
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
