package com.form;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 打印查询信息
 * 
 * @author Daniel Duan
 * 
 */
public class PrintForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 病人ID
	private Integer pid;
	// 身份证
	private String idCard;
	// 姓名
	private String name;
	// 条码号
	private String code;
	// 年龄
	private Integer age;
	// 生日
	private Date birthdate;
	// 体检日期
	private Date examDate;
	// 责任医生
	private String resDoctor;
	// 机构名称
	private String insName;
	// 性别(1男2女3未说明的性别4未知的性别)
	private Integer sex;
	// 联系电话
	private String phoneno;
	// 家庭住址
	private String address;
	// 户籍地址
	private String address1;
	// 参数1
	private String filed1;
	// 参数2
	private String filed2;
	// 参数3
	private String filed3;

	public Integer getAge() {
		age = null;
		if (birthdate != null) {
			Calendar cal = Calendar.getInstance();
			if (cal.before(birthdate)) {
				return age;
			}
			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH);
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(birthdate);
			int yearBirth = cal.get(Calendar.YEAR);
			int monthBirth = cal.get(Calendar.MONTH);
			int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
			age = yearNow - yearBirth;
			if (monthNow <= monthBirth) {
				if (monthNow == monthBirth) {
					if (dayOfMonthNow < dayOfMonthBirth)
						age--;
				} else {
					age--;
				}
			}
		}
		return age;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getExamDate() {
		return examDate;
	}

	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}

	public String getResDoctor() {
		return resDoctor;
	}

	public void setResDoctor(String resDoctor) {
		this.resDoctor = resDoctor;
	}

	public String getInsName() {
		return insName;
	}

	public void setInsName(String insName) {
		this.insName = insName;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getFiled1() {
		return filed1;
	}

	public void setFiled1(String filed1) {
		this.filed1 = filed1;
	}

	public String getFiled2() {
		return filed2;
	}

	public void setFiled2(String filed2) {
		this.filed2 = filed2;
	}

	public String getFiled3() {
		return filed3;
	}

	public void setFiled3(String filed3) {
		this.filed3 = filed3;
	}
}
