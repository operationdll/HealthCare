package com.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 自理能力表
 * 
 * @author Daniel Duan
 * 
 */
public class SelfCareDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	// 进餐：使用餐具将饭菜送人口、咀嚼、吞咽等活动:1独立完成2-----3需要协助，如切碎、搅拌食物等4完全需要帮助
	private Integer field1;
	// 梳洗：梳头、洗脸、刷牙、剃须洗澡等活动:1独立完成2能独立地洗头、梳头、洗脸、刷牙、剃须等；洗澡需要协助3在协助下和适当的时间内，能完成部分梳洗活动4完全需要帮助
	private Integer field2;
	// 穿衣：穿衣裤、袜子、鞋子等活动:1独立完成2-----3需要协助,在适当的时间内，完成部分穿衣4完全需要帮助
	private Integer field3;
	// 如厕：小便、大便等活动及自控1不需协助，可自控2偶尔失禁，但基本上能如厕或使用便具3经常失禁，在很多提示和协助下尚能如厕或使用便具4完全失禁，完全需要帮助
	private Integer field4;
	// 活动：站立、室内行走、上下楼梯、户外活动:1独立完成所有活动2借助较小的外力或辅助装置能完成站立、行走、上下楼梯等3借助较大的外力才能完成站立、行走，不能上下楼梯4借助较大的外力才能完成站立、行走，不能上下楼梯
	private Integer field5;
	// 总分
	private String field6;
	// 下次随访目标
	private String field7;
	// 下次随访日期
	private Date field8;
	// 评估结论
	private String field9;
	// 创建日期
	private Date createDate;
	// 修改日期
	private Date updateDate;
	// 病人
	private Integer pid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getField1() {
		return field1;
	}

	public void setField1(Integer field1) {
		this.field1 = field1;
	}

	public Integer getField2() {
		return field2;
	}

	public void setField2(Integer field2) {
		this.field2 = field2;
	}

	public Integer getField3() {
		return field3;
	}

	public void setField3(Integer field3) {
		this.field3 = field3;
	}

	public Integer getField4() {
		return field4;
	}

	public void setField4(Integer field4) {
		this.field4 = field4;
	}

	public Integer getField5() {
		return field5;
	}

	public void setField5(Integer field5) {
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

	public Date getField8() {
		return field8;
	}

	public void setField8(Date field8) {
		this.field8 = field8;
	}

	public String getField9() {
		return field9;
	}

	public void setField9(String field9) {
		this.field9 = field9;
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

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

}
