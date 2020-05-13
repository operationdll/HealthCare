package com.form;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

/**
 * 数据审核信息
 * 
 * @author Daniel Duan
 * 
 */
public class DataReviewForm implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 病人id
	private Integer id;
	// 姓名
	private String field1;
	// 性别(1男2女3未说明的性别4未知的性别)
	private String field2;
	// 生日
	private Date field3;
	// 年龄
	private Integer age;
	// 体检日期
	private Date field4;
	// 体检编码
	private String field5;
	// 有异常|无异常 健康评价1体检无异常2有异常
	private String field6;
	// 自理能力 已做
	private String field7;
	// 中医体质 已做
	private String field8;
	// 添加时间
	private Date field9;
	// 更新时间
	private Date field10;
	// 症状1无症状2头痛3头晕4心悸5胸闷6胸痛7慢性咳嗽8咳痰9呼吸困难10多饮11多尿12体重下降13乏力14关节肿痛15视力模糊16手脚麻木17尿急
	// 18尿痛19便秘20腹泻21恶心呕吐22眼花23耳鸣24乳房胀痛25其他
	private String field11;
	// 呼吸频率
	private String field12;
	// 腰围
	private String field13;
	// 健康状态评估;// 老年人健康状态评估1满意2基本满意3说不清楚4不太满意5不满意
	private String field14;
	// 老年人自理能力评估1可自理2轻度依赖3中度依赖4不能自理
	private String field15;
	// 老年人认知功能1粗筛阴性2粗筛阳性
	private String field16;
	// 老年人情感状态1粗筛阴性2粗筛阳性
	private String field17;
	// 锻炼频率1每天2每周一次以上3偶尔4不锻炼
	private String field18;
	// 饮食习惯1荤素均衡2荤食为主3素食为主4嗜盐5嗜油6嗜糖
	private String field19;
	private String field20;// 吸烟状况1从不吸烟2已戒烟3吸烟
	private String field21;// 饮酒频率1从不2偶尔3经常4每天
	private String field22;// 齿列1正常2缺齿3龋齿4义齿(假牙)
	private String field23;// 左眼视力
	private String field24;// 右眼视力
	private String field25;// 听力1听见2听不清或无法听见
	private String field26;// 运动功能1可顺利完成2无法独立完成其中任何一个动作
	private String field27;// 脑血管疾病1未发现2缺血性卒中3脑出血4蛛网膜下腔出血5短暂性脑缺血6其他
	private String field28;// 肾脏疾病1未发现2糖尿病肾病3肾功能衰竭4急性肾炎5慢性肾炎6其他
	private String field29;// 心血管疾病1未发现2心肌梗死3心绞痛4冠状动脉血运重建5充血性心力衰竭6心前区疼痛7高血压8夹层动脉瘤9动脉闭塞性疾病10其他
	private String field30;// 眼部疾病1未发现2视网膜出血或渗出3视乳头水肿4白内障5其他
	private String field31;// 神经系统疾病1未发现2阿尔茨海默症(老年痴呆症)3帕金森病4其他
	private String field32;// 其他系统疾病1未发现2糖尿病3慢性支气管炎4慢性阻塞性肺气肿5恶性肿瘤6老年性骨关节病7其他
	private String field33;// 身高
	private String field34;// 体重
	private String field35;// BMI 体质指数
	private String field36;// 左侧高压// 血压左侧1
	private String field37;// 左侧低压// 血压左侧2
	private String field38;// 右侧高压;// 血压右侧1 收缩压
	private String field39;// 右侧低压;// 血压右侧2 舒张压
	private String field40;// 脉率 脉搏
	private String field41;// 血红蛋白
	private String field42;// 白细胞
	private String field43;// 血小板
	private String field44;// 血常规其他
	private String field45;// 尿酮体
	private String field46;// 尿潜血
	private String field47;// 尿蛋白
	private String field48;// 尿糖
	private String field49;// 尿常规其他// 尿其他
	private String field50;// 血清谷丙;// 血清谷丙转氨酶
	private String field51;// 血清谷草;// 血清谷草转氨酶
	private String field52;// 总胆红素
	private String field53;// 血清肌酐
	private String field54;// 血尿素氮
	private String field55;// 总胆固醇
	private String field56;// 甘油三酯
	private String field57;// 血清低密度
	private String field58;// 血清高密度
	private String field59;// 空腹血糖mmol/L
	private String field60;// 心电图1正常2ST段改变3陈旧性心肌梗塞4窦性心动过速5窦性心动过缓6早搏7房颤8房室传导阻滞9其他
	private String field61;// 心电图其他
	private String field62;// 心率
	private String field63;// 腹部B超1正常2异常
	private String field64;// b超异常// 腹部其他
	private String field65;// 健康指导1定期随访2纳入慢性病患者健康管理3建议复查4建议转诊
	private String field66;// 危险因素控制1戒烟2健康饮酒3饮食4锻炼5减体重6建议接种疫苗7其他
	private String field67;// 进餐：使用餐具将饭菜送人口、咀嚼、吞咽等活动:1独立完成2-----3需要协助，如切碎、搅拌食物等4完全需要帮助
	private String field68;// 梳洗：梳头、洗脸、刷牙、剃须洗澡等活动:1独立完成2能独立地洗头、梳头、洗脸、刷牙、剃须等；洗澡需要协助3在协助下和适当的时间内，能完成部分梳洗活动4完全需要帮助
	private String field69;// 穿衣：穿衣裤、袜子、鞋子等活动:1独立完成2-----3需要协助,在适当的时间内，完成部分穿衣4完全需要帮助
	private String field70;// 如厕：小便、大便等活动及自控1不需协助，可自控2偶尔失禁，但基本上能如厕或使用便具3经常失禁，在很多提示和协助下尚能如厕或使用便具4完全失禁，完全需要帮助
	private String field71;// 活动：站立、室内行走、上下楼梯、户外活动:1独立完成所有活动2借助较小的外力或辅助装置能完成站立、行走、上下楼梯等3借助较大的外力才能完成站立、行走，不能上下楼梯4借助较大的外力才能完成站立、行走，不能上下楼梯
	private String field72;// 评估结论
	private String field73;// 平和质:1是2倾向是
	private String field74;// 气虚质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field75;// 阳虚质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field76;// 阴虚质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field77;// 痰湿质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field78;// 湿热质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field79;// 血瘀质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field80;// 气郁质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field81;// 特禀质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field82;// 随访医生

	public Integer getAge() {
		age = null;
		if (field3 != null) {
			Calendar cal = Calendar.getInstance();
			if (cal.before(field3)) {
				return age;
			}
			int yearNow = cal.get(Calendar.YEAR);
			int monthNow = cal.get(Calendar.MONTH);
			int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
			cal.setTime(field3);
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

	public String getField1() {
		return field1;
	}

	public void setField1(String field1) {
		this.field1 = field1;
	}

	public String getField2() {
		// 性别(1男2女3未说明的性别4未知的性别)
		if (!StringUtils.isEmpty(field2)) {
			if ("1".equals(field2)) {
				field2 = "男";
			} else if ("2".equals(field2)) {
				field2 = "女";
			} else if ("3".equals(field2)) {
				field2 = "未说明的性别";
			} else if ("4".equals(field2)) {
				field2 = "未知的性别";
			}
		}
		return field2;
	}

	public void setField2(String field2) {
		this.field2 = field2;
	}

	public Date getField3() {
		return field3;
	}

	public void setField3(Date field3) {
		this.field3 = field3;
	}

	public Date getField4() {
		return field4;
	}

	public void setField4(Date field4) {
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

	public Date getField9() {
		return field9;
	}

	public void setField9(Date field9) {
		this.field9 = field9;
	}

	public Date getField10() {
		return field10;
	}

	public void setField10(Date field10) {
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

	public String getField28() {
		return field28;
	}

	public void setField28(String field28) {
		this.field28 = field28;
	}

	public String getField29() {
		return field29;
	}

	public void setField29(String field29) {
		this.field29 = field29;
	}

	public String getField30() {
		return field30;
	}

	public void setField30(String field30) {
		this.field30 = field30;
	}

	public String getField31() {
		return field31;
	}

	public void setField31(String field31) {
		this.field31 = field31;
	}

	public String getField32() {
		return field32;
	}

	public void setField32(String field32) {
		this.field32 = field32;
	}

	public String getField33() {
		return field33;
	}

	public void setField33(String field33) {
		this.field33 = field33;
	}

	public String getField34() {
		return field34;
	}

	public void setField34(String field34) {
		this.field34 = field34;
	}

	public String getField35() {
		return field35;
	}

	public void setField35(String field35) {
		this.field35 = field35;
	}

	public String getField36() {
		return field36;
	}

	public void setField36(String field36) {
		this.field36 = field36;
	}

	public String getField37() {
		return field37;
	}

	public void setField37(String field37) {
		this.field37 = field37;
	}

	public String getField38() {
		return field38;
	}

	public void setField38(String field38) {
		this.field38 = field38;
	}

	public String getField39() {
		return field39;
	}

	public void setField39(String field39) {
		this.field39 = field39;
	}

	public String getField40() {
		return field40;
	}

	public void setField40(String field40) {
		this.field40 = field40;
	}

	public String getField41() {
		return field41;
	}

	public void setField41(String field41) {
		this.field41 = field41;
	}

	public String getField42() {
		return field42;
	}

	public void setField42(String field42) {
		this.field42 = field42;
	}

	public String getField43() {
		return field43;
	}

	public void setField43(String field43) {
		this.field43 = field43;
	}

	public String getField44() {
		return field44;
	}

	public void setField44(String field44) {
		this.field44 = field44;
	}

	public String getField45() {
		return field45;
	}

	public void setField45(String field45) {
		this.field45 = field45;
	}

	public String getField46() {
		return field46;
	}

	public void setField46(String field46) {
		this.field46 = field46;
	}

	public String getField47() {
		return field47;
	}

	public void setField47(String field47) {
		this.field47 = field47;
	}

	public String getField48() {
		return field48;
	}

	public void setField48(String field48) {
		this.field48 = field48;
	}

	public String getField49() {
		return field49;
	}

	public void setField49(String field49) {
		this.field49 = field49;
	}

	public String getField50() {
		return field50;
	}

	public void setField50(String field50) {
		this.field50 = field50;
	}

	public String getField51() {
		return field51;
	}

	public void setField51(String field51) {
		this.field51 = field51;
	}

	public String getField52() {
		return field52;
	}

	public void setField52(String field52) {
		this.field52 = field52;
	}

	public String getField53() {
		return field53;
	}

	public void setField53(String field53) {
		this.field53 = field53;
	}

	public String getField54() {
		return field54;
	}

	public void setField54(String field54) {
		this.field54 = field54;
	}

	public String getField55() {
		return field55;
	}

	public void setField55(String field55) {
		this.field55 = field55;
	}

	public String getField56() {
		return field56;
	}

	public void setField56(String field56) {
		this.field56 = field56;
	}

	public String getField57() {
		return field57;
	}

	public void setField57(String field57) {
		this.field57 = field57;
	}

	public String getField58() {
		return field58;
	}

	public void setField58(String field58) {
		this.field58 = field58;
	}

	public String getField59() {
		return field59;
	}

	public void setField59(String field59) {
		this.field59 = field59;
	}

	public String getField60() {
		return field60;
	}

	public void setField60(String field60) {
		this.field60 = field60;
	}

	public String getField61() {
		return field61;
	}

	public void setField61(String field61) {
		this.field61 = field61;
	}

	public String getField62() {
		return field62;
	}

	public void setField62(String field62) {
		this.field62 = field62;
	}

	public String getField63() {
		return field63;
	}

	public void setField63(String field63) {
		this.field63 = field63;
	}

	public String getField64() {
		return field64;
	}

	public void setField64(String field64) {
		this.field64 = field64;
	}

	public String getField65() {
		return field65;
	}

	public void setField65(String field65) {
		this.field65 = field65;
	}

	public String getField66() {
		return field66;
	}

	public void setField66(String field66) {
		this.field66 = field66;
	}

	public String getField67() {
		return field67;
	}

	public void setField67(String field67) {
		this.field67 = field67;
	}

	public String getField68() {
		return field68;
	}

	public void setField68(String field68) {
		this.field68 = field68;
	}

	public String getField69() {
		return field69;
	}

	public void setField69(String field69) {
		this.field69 = field69;
	}

	public String getField70() {
		return field70;
	}

	public void setField70(String field70) {
		this.field70 = field70;
	}

	public String getField71() {
		return field71;
	}

	public void setField71(String field71) {
		this.field71 = field71;
	}

	public String getField72() {
		return field72;
	}

	public void setField72(String field72) {
		this.field72 = field72;
	}

	public String getField73() {
		return field73;
	}

	public void setField73(String field73) {
		this.field73 = field73;
	}

	public String getField74() {
		return field74;
	}

	public void setField74(String field74) {
		this.field74 = field74;
	}

	public String getField75() {
		return field75;
	}

	public void setField75(String field75) {
		this.field75 = field75;
	}

	public String getField76() {
		return field76;
	}

	public void setField76(String field76) {
		this.field76 = field76;
	}

	public String getField77() {
		return field77;
	}

	public void setField77(String field77) {
		this.field77 = field77;
	}

	public String getField78() {
		return field78;
	}

	public void setField78(String field78) {
		this.field78 = field78;
	}

	public String getField79() {
		return field79;
	}

	public void setField79(String field79) {
		this.field79 = field79;
	}

	public String getField80() {
		return field80;
	}

	public void setField80(String field80) {
		this.field80 = field80;
	}

	public String getField81() {
		return field81;
	}

	public void setField81(String field81) {
		this.field81 = field81;
	}

	public String getField82() {
		return field82;
	}

	public void setField82(String field82) {
		this.field82 = field82;
	}

}
