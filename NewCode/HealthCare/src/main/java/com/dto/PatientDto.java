package com.dto;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 * 病人信息
 * 
 * @author Daniel Duan
 * 
 */
public class PatientDto implements Serializable {

	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 档案编号
	private String code;
	// 姓名
	private String name;
	// 现住址
	private String address;
	// 户籍地址
	private String address1;
	// 乡镇(街道)名称
	private String countyname;
	// 村(居)委会名称
	private String vcname;
	// 建档单位
	private String fileunit;
	// 建档人
	private String fileuser;
	// 责任医生
	private String doctor;
	// 建档日期
	private Date filedate;
	// 性别(1男2女3未说明的性别4未知的性别)
	private Integer sex;
	// 出生日期
	private Date birthdate;
	// 身份证
	private String idcard;
	// 身份证图片
	private String idimg;
	// 工作单位
	private String company;
	// 本人电话
	private String phoneno;
	// 联系人姓名
	private String cname;
	// 联系人电话
	private String cphoneno;
	// 常驻类型(1户籍2非户籍)
	private Integer rtype;
	// 民族
	private String nationality;
	// 血型1A型2B型3O型4AB型5不详
	private Integer blood;
	// RH1阴性2阳性3不详
	private Integer RH;
	// 文化程度1研究生2大学本科3大学专科和专科学校4中等专业学校5技工学校6高中7初中8小学9文盲或半文盲10不详
	private Integer elevel;
	// 职业1国家机关、党群组织、企业、事业单位负责人2专业技术人员3办事人员和有关人员4商业、服务业人员5农、林、牧、渔、水利业生产人员6生产、运输设备操作人员及有关人员7军人8不便分类的其他从业人员9无职业
	private Integer career;
	// 婚姻状况1未婚2已婚3丧偶4离婚5未说明的婚姻状况
	private Integer marital;
	// 医疗费用支付方式1城镇职工基本医疗保险2城镇居民基本医疗保险3新型农村合作医疗4贫困救助5商业医疗保险6全公费7全自费8其他
	private String paymentway;
	// 药物过敏史1无2青霉素3磺胺4链霉素5其他
	private String allergy;
	// 暴露史1无2化学品3毒物4射线
	private String exposure;
	// 疾病1无2高血压3糖料病4冠心病5慢性阻塞性肺疾病6恶性肿瘤7脑卒中8严重精神障碍9结核病10肝炎11其他法定传染病12职业病13其他
	private String disease;
	// 确诊时间1
	private Date ctime1;
	// 确诊时间2
	private Date ctime2;
	// 确诊时间3
	private Date ctime3;
	// 确诊时间4
	private Date ctime4;
	// 手术1无2有
	private Integer surgery;
	// 手术名称1
	private String sname1;
	// 手术时间1
	private Date stime1;
	// 手术名称2
	private String sname2;
	// 手术时间2
	private Date stime2;
	// 外伤1无2有
	private Integer trauma;
	// 外伤名称1
	private String tname1;
	// 外伤时间1
	private Date ttime1;
	// 外伤名称2
	private String tname2;
	// 外伤时间2
	private Date ttime2;
	// 输血1无2有
	private Integer btran;
	// 输血原因1
	private String breason1;
	// 输血时间1
	private Date btime1;
	// 输血原因2
	private String breason2;
	// 输血时间2
	private Date btime2;
	// 父亲1无2高血压3糖尿病4冠心病5慢性阻塞性肺疾病6恶性肿瘤7脑卒中8严重精神障碍9结核病10肝炎11先天畸形12其他
	private String father;
	// 母亲
	private String mother;
	// 兄弟姐妹
	private String bsrelative;
	// 子女
	private String children;
	// 遗传病史1无2有
	private Integer genetic;
	// 疾病名称
	private String dname;
	// 残疾情况1无残疾2视力残疾3听力残疾4语言残疾5肢体残疾6智力残疾7精神残疾8其他残疾
	private String disability;
	// 厨房排风设施1无2油烟机3换气扇4烟囱
	private Integer kfacility;
	// 燃料类型1液化气2煤3天然气4沼气5柴火6其他
	private Integer ftype;
	// 饮水1自来水2经净化过滤的水3井水4河湖水5塘水6其他
	private Integer dwater;
	// 厕所1卫生厕所2一格或二格粪池式3马桶4露天粪坑5简易棚厕
	private Integer toilet;
	// 禽畜栏1无2单设3室内4室外
	private Integer poultry;
	// 卫生所
	private Integer hcid;
	// 创建日期
	private Date createDate;
	// 修改日期
	private Date updateDate;
	// 年龄
	private Integer age;
	// 医保卡号(城镇保险)
	private String field1;
	// 医保卡号(居民基本保险)
	private String field2;
	// 卡号
	private String field3;
	// 医疗费用支付方式其他
	private String field4;
	// 户主姓名
	private String field5;
	// 户主身份证
	private String field6;
	// 家庭人口数
	private String field7;
	// 家庭结构
	private String field8;
	// 居住情况1与成年子女同住2与子孙三代(四代)同住3夫妻二人同住4独居5计划生育特殊家庭
	private Integer field9;
	// 疾病其他
	private String field10;
	// 体检主表ID
	private String mtid;
	// 居民ID
	private String personid;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMarital() {
		return marital;
	}

	public void setMarital(Integer marital) {
		this.marital = marital;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCountyname() {
		return countyname;
	}

	public void setCountyname(String countyname) {
		this.countyname = countyname;
	}

	public String getVcname() {
		return vcname;
	}

	public void setVcname(String vcname) {
		this.vcname = vcname;
	}

	public String getFileunit() {
		return fileunit;
	}

	public void setFileunit(String fileunit) {
		this.fileunit = fileunit;
	}

	public String getFileuser() {
		return fileuser;
	}

	public void setFileuser(String fileuser) {
		this.fileuser = fileuser;
	}

	public String getDoctor() {
		return doctor;
	}

	public void setDoctor(String doctor) {
		this.doctor = doctor;
	}

	public Date getFiledate() {
		return filedate;
	}

	public void setFiledate(Date filedate) {
		this.filedate = filedate;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public String getIdcard() {
		return idcard;
	}

	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}

	public String getIdimg() {
		return idimg;
	}

	public void setIdimg(String idimg) {
		this.idimg = idimg;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getPhoneno() {
		return phoneno;
	}

	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCphoneno() {
		return cphoneno;
	}

	public void setCphoneno(String cphoneno) {
		this.cphoneno = cphoneno;
	}

	public Integer getRtype() {
		return rtype;
	}

	public void setRtype(Integer rtype) {
		this.rtype = rtype;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public Integer getBlood() {
		return blood;
	}

	public void setBlood(Integer blood) {
		this.blood = blood;
	}

	public Integer getRH() {
		return RH;
	}

	public void setRH(Integer rH) {
		RH = rH;
	}

	public Integer getElevel() {
		return elevel;
	}

	public void setElevel(Integer elevel) {
		this.elevel = elevel;
	}

	public Integer getCareer() {
		return career;
	}

	public void setCareer(Integer career) {
		this.career = career;
	}

	public String getPaymentway() {
		return paymentway;
	}

	public void setPaymentway(String paymentway) {
		this.paymentway = paymentway;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}

	public String getExposure() {
		return exposure;
	}

	public void setExposure(String exposure) {
		this.exposure = exposure;
	}

	public String getDisease() {
		return disease;
	}

	public void setDisease(String disease) {
		this.disease = disease;
	}

	public Date getCtime1() {
		return ctime1;
	}

	public void setCtime1(Date ctime1) {
		this.ctime1 = ctime1;
	}

	public Date getCtime2() {
		return ctime2;
	}

	public void setCtime2(Date ctime2) {
		this.ctime2 = ctime2;
	}

	public Date getCtime3() {
		return ctime3;
	}

	public void setCtime3(Date ctime3) {
		this.ctime3 = ctime3;
	}

	public Date getCtime4() {
		return ctime4;
	}

	public void setCtime4(Date ctime4) {
		this.ctime4 = ctime4;
	}

	public Integer getSurgery() {
		return surgery;
	}

	public void setSurgery(Integer surgery) {
		this.surgery = surgery;
	}

	public String getSname1() {
		return sname1;
	}

	public void setSname1(String sname1) {
		this.sname1 = sname1;
	}

	public Date getStime1() {
		return stime1;
	}

	public void setStime1(Date stime1) {
		this.stime1 = stime1;
	}

	public String getSname2() {
		return sname2;
	}

	public void setSname2(String sname2) {
		this.sname2 = sname2;
	}

	public Date getStime2() {
		return stime2;
	}

	public void setStime2(Date stime2) {
		this.stime2 = stime2;
	}

	public Integer getTrauma() {
		return trauma;
	}

	public void setTrauma(Integer trauma) {
		this.trauma = trauma;
	}

	public String getTname1() {
		return tname1;
	}

	public void setTname1(String tname1) {
		this.tname1 = tname1;
	}

	public Date getTtime1() {
		return ttime1;
	}

	public void setTtime1(Date ttime1) {
		this.ttime1 = ttime1;
	}

	public String getTname2() {
		return tname2;
	}

	public void setTname2(String tname2) {
		this.tname2 = tname2;
	}

	public Date getTtime2() {
		return ttime2;
	}

	public void setTtime2(Date ttime2) {
		this.ttime2 = ttime2;
	}

	public Integer getBtran() {
		return btran;
	}

	public void setBtran(Integer btran) {
		this.btran = btran;
	}

	public String getBreason1() {
		return breason1;
	}

	public void setBreason1(String breason1) {
		this.breason1 = breason1;
	}

	public Date getBtime1() {
		return btime1;
	}

	public void setBtime1(Date btime1) {
		this.btime1 = btime1;
	}

	public String getBreason2() {
		return breason2;
	}

	public void setBreason2(String breason2) {
		this.breason2 = breason2;
	}

	public Date getBtime2() {
		return btime2;
	}

	public void setBtime2(Date btime2) {
		this.btime2 = btime2;
	}

	public String getFather() {
		return father;
	}

	public void setFather(String father) {
		this.father = father;
	}

	public String getMother() {
		return mother;
	}

	public void setMother(String mother) {
		this.mother = mother;
	}

	public String getBsrelative() {
		return bsrelative;
	}

	public void setBsrelative(String bsrelative) {
		this.bsrelative = bsrelative;
	}

	public String getChildren() {
		return children;
	}

	public void setChildren(String children) {
		this.children = children;
	}

	public Integer getGenetic() {
		return genetic;
	}

	public void setGenetic(Integer genetic) {
		this.genetic = genetic;
	}

	public String getDname() {
		return dname;
	}

	public void setDname(String dname) {
		this.dname = dname;
	}

	public String getDisability() {
		return disability;
	}

	public void setDisability(String disability) {
		this.disability = disability;
	}

	public Integer getKfacility() {
		return kfacility;
	}

	public void setKfacility(Integer kfacility) {
		this.kfacility = kfacility;
	}

	public Integer getFtype() {
		return ftype;
	}

	public void setFtype(Integer ftype) {
		this.ftype = ftype;
	}

	public Integer getDwater() {
		return dwater;
	}

	public void setDwater(Integer dwater) {
		this.dwater = dwater;
	}

	public Integer getToilet() {
		return toilet;
	}

	public void setToilet(Integer toilet) {
		this.toilet = toilet;
	}

	public Integer getPoultry() {
		return poultry;
	}

	public void setPoultry(Integer poultry) {
		this.poultry = poultry;
	}

	public Integer getHcid() {
		return hcid;
	}

	public void setHcid(Integer hcid) {
		this.hcid = hcid;
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

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public String getField3() {
		return field3;
	}

	public void setField3(String field3) {
		this.field3 = field3;
	}

	public String getField4() {
		return field4;
	}

	public void setField4(String field4) {
		this.field4 = field4;
	}

	public String getField5() {
		return field5;
	}

	public void setField5(String field5) {
		this.field5 = field5;
	}

	public String getField6() {
		return field6;
	}

	public void setField6(String field6) {
		this.field6 = field6;
	}

	public String getField7() {
		return field7;
	}

	public void setField7(String field7) {
		this.field7 = field7;
	}

	public String getField8() {
		return field8;
	}

	public void setField8(String field8) {
		this.field8 = field8;
	}

	public Integer getField9() {
		return field9;
	}

	public void setField9(Integer field9) {
		this.field9 = field9;
	}

	public String getField10() {
		return field10;
	}

	public void setField10(String field10) {
		this.field10 = field10;
	}

	public String getMtid() {
		return mtid;
	}

	public void setMtid(String mtid) {
		this.mtid = mtid;
	}

	public String getPersonid() {
		return personid;
	}

	public void setPersonid(String personid) {
		this.personid = personid;
	}

}
