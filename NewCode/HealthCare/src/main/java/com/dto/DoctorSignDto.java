package com.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 家医签约
 * 
 * @author Daniel Duan
 * 
 */
public class DoctorSignDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 团队长名称
	private String field1;
	// 团队长地址
	private String field2;
	// 团队长电话
	private String field3;
	// 团队医生名称
	private String field4;
	// 团队医生地址
	private String field5;
	// 团队医生电话
	private String field6;
	// 护士名称
	private String field7;
	// 护士地址
	private String field8;
	// 护士电话
	private String field9;
	// 公务人员名称
	private String field10;
	// 公务人员地址
	private String field11;
	// 公务人员电话
	private String field12;
	// 其他人员名称
	private String field13;
	// 其他人员地址
	private String field14;
	// 其他人员电话
	private String field15;
	// 其他人员2名称
	private String field16;
	// 其他人员2地址
	private String field17;
	// 其他人员2电话
	private String field18;
	// 其他人员3名称
	private String field19;
	// 其他人员3地址
	private String field20;
	// 其他人员3电话
	private String field21;
	// 监督单位
	private String field22;
	// 监督电话
	private String field23;
	// 服务对象类型:1孕妇2⼉童3⾼⾎压4糖尿病5⽼年⼈6慢阻肺7计⽣特殊家庭8贫困⼈⼝9其它
	private String field24;
	// 服务类型
	private String field25;
	// 费用
	private String field26;
	// 签字盖章
	private String field27;
	// 卫生所
	private Integer hcid;
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

	public String getField9() {
		return field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
	}

	public String getField10() {
		return field10;
	}

	public void setField10(String field10) {
		this.field10 = field10;
	}

	public String getField11() {
		return field11;
	}

	public void setField11(String field11) {
		this.field11 = field11;
	}

	public String getField12() {
		return field12;
	}

	public void setField12(String field12) {
		this.field12 = field12;
	}

	public String getField13() {
		return field13;
	}

	public void setField13(String field13) {
		this.field13 = field13;
	}

	public String getField14() {
		return field14;
	}

	public void setField14(String field14) {
		this.field14 = field14;
	}

	public String getField15() {
		return field15;
	}

	public void setField15(String field15) {
		this.field15 = field15;
	}

	public String getField16() {
		return field16;
	}

	public void setField16(String field16) {
		this.field16 = field16;
	}

	public String getField17() {
		return field17;
	}

	public void setField17(String field17) {
		this.field17 = field17;
	}

	public String getField18() {
		return field18;
	}

	public void setField18(String field18) {
		this.field18 = field18;
	}

	public String getField19() {
		return field19;
	}

	public void setField19(String field19) {
		this.field19 = field19;
	}

	public String getField20() {
		return field20;
	}

	public void setField20(String field20) {
		this.field20 = field20;
	}

	public String getField21() {
		return field21;
	}

	public void setField21(String field21) {
		this.field21 = field21;
	}

	public String getField22() {
		return field22;
	}

	public void setField22(String field22) {
		this.field22 = field22;
	}

	public String getField23() {
		return field23;
	}

	public void setField23(String field23) {
		this.field23 = field23;
	}

	public String getField24() {
		return field24;
	}

	public void setField24(String field24) {
		this.field24 = field24;
	}

	public String getField25() {
		return field25;
	}

	public void setField25(String field25) {
		this.field25 = field25;
	}

	public String getField26() {
		return field26;
	}

	public void setField26(String field26) {
		this.field26 = field26;
	}

	public String getField27() {
		return field27;
	}

	public void setField27(String field27) {
		this.field27 = field27;
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

}
