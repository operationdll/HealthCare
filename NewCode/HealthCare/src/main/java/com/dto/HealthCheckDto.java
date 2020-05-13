package com.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 健康体检信息
 * 
 * @author Daniel Duan
 * 
 */
public class HealthCheckDto implements Serializable {

	private static final long serialVersionUID = 1L;
	// 主键
	private int id;
	// 体检日期
	private Date field1;
	// 症状1无症状2头痛3头晕4心悸5胸闷6胸痛7慢性咳嗽8咳痰9呼吸困难10多饮11多尿12体重下降13乏力14关节肿痛15视力模糊16手脚麻木17尿急
	// 18尿痛19便秘20腹泻21恶心呕吐22眼花23耳鸣24乳房胀痛25其他
	private String field2;
	private String field3;// 症状其他
	private String field4;// 体温
	private String field5;// 脉率
	private String field6;// 呼吸频率
	private String field7;// 血压左侧1
	private String field8;// 血压左侧2
	private String field9;// 血压右侧1 收缩压
	private String field10;// 血压右侧2 舒张压
	private String field11;// 身高
	private String field12;// 体重
	private String field13;// 腰围
	private Integer field15;// 老年人健康状态评估1满意2基本满意3说不清楚4不太满意5不满意
	private Integer field16;// 老年人自理能力评估1可自理2轻度依赖3中度依赖4不能自理
	private Integer field17;// 老年人认知功能1粗筛阴性2粗筛阳性
	private Integer field18;// 老年人情感状态1粗筛阴性2粗筛阳性
	private Integer field19;// 锻炼频率1每天2每周一次以上3偶尔4不锻炼
	private String field20;// 每次锻炼时间
	private String field21;// 坚持锻炼时间
	private String field22;// 锻炼方式
	private String field23;// 饮食习惯1荤素均衡2荤食为主3素食为主4嗜盐5嗜油6嗜糖
	private Integer field24;// 吸烟状况1从不吸烟2已戒烟3吸烟
	private String field25;// 日吸烟量
	private String field26;// 开始吸烟年龄
	private String field27;// 戒烟年龄
	private Integer field28;// 饮酒频率1从不2偶尔3经常4每天
	private String field29;// 日饮酒量
	private Integer field30;// 是否戒酒1未戒酒2已戒酒
	private String field31;// 戒酒年龄
	private String field32;// 开始饮酒年龄
	private Integer field33;// 近一年内是否曾醉酒1是2否
	private String field34;// 饮酒种类1白酒2啤酒3红酒4黄酒5其他
	private String field35;// 饮酒种类其他
	private Integer field36;// 职业病危害1无2有
	private String field37;// 工种
	private String field38;// 年限
	private String field39;// 粉尘
	private Integer field40;// 粉尘防护措施1有2无
	private String field41;// 放射物质
	private Integer field42;// 放射物质防护措施1有2无
	private String field43;// 物理因素
	private Integer field44;// 物理因素防护措施1有2无
	private String field45;// 化学物质
	private Integer field46;// 化学物质防护措施1有2无
	private String field47;// 其他
	private Integer field48;// 其他防护措施1有2无
	private Integer field49;// 口唇1红润2苍白3发绀4皲裂5疱疹
	private String field50;// 齿列1正常2缺齿3龋齿4义齿(假牙)
	private String field51;// 缺齿1
	private String field52;// 缺齿2
	private String field53;// 缺齿3
	private String field54;// 缺齿4
	private String field55;// 龋齿1
	private String field56;// 龋齿2
	private String field57;// 龋齿3
	private String field58;// 龋齿4
	private String field59;// 义齿1
	private String field60;// 义齿2
	private String field61;// 义齿3
	private String field62;// 义齿4
	private Integer field63;// 咽部1无充血2充血3淋巴滤泡增生
	private String field64;// 左眼视力
	private String field65;// 右眼视力
	private String field66;// 左眼矫正视力
	private String field67;// 右眼矫正视力
	private Integer field68;// 听力1听见2听不清或无法听见
	private Integer field69;// 运动功能1可顺利完成2无法独立完成其中任何一个动作
	private Integer field70;// 眼底1正常2异常
	private Integer field71;// 皮肤1正常2潮红3苍白4发绀5黄染6色素沉着7其他
	private Integer field72;// 巩膜1正常2黄染3充血4其他
	private Integer field73;// 淋巴结1未触及2锁骨上3腋窝4其他
	private Integer field74;// 桶状胸1否2是
	private Integer field75;// 呼吸音1正常2异常
	private Integer field76;// 罗音1无2干罗音3湿罗音4其他
	private String field77;// 心率
	private Integer field78;// 心律1齐2不齐3绝对不齐
	private Integer field79;// 杂音1无2有
	private Integer field80;// 压痛1无2有
	private Integer field81;// 包块1无2有
	private Integer field82;// 肝大1无2有
	private Integer field83;// 脾大1无2有
	private Integer field84;// 移动性浊音1无2有
	private Integer field85;// 下肢水肿1无2单侧3双侧不对称4双侧对称
	private Integer field86;// 足背动脉搏动1未触及2触及双侧对称3触及左侧弱或消失4触及右侧弱或消失
	private Integer field87;// 肛门指诊1未见异常2触痛3包块4前列腺异常5其他
	private Integer field88;// 乳腺1未见异常2乳房切除3异常泌乳4乳腺包块5其他
	private Integer field89;// 妇科外阴1未见异常2异常
	private Integer field90;// 妇科阴道1未见异常2异常
	private Integer field91;// 妇科宫颈1未见异常2异常
	private Integer field92;// 妇科宫体1未见异常2异常
	private Integer field93;// 妇科附件1未见异常2异常
	private String field94;// 妇科其他
	private Integer field95;// ABO1A型2B型3O型4AB型
	private Integer field96;// RH1是2否3不详
	private String field97;// 血红蛋白
	private String field98;// 白细胞
	private String field99;// 血小板
	private String field100;// 血常规其他
	private String field101;// 尿蛋白
	private String field102;// 尿糖
	private String field103;// 尿酮体
	private String field104;// 尿潜血
	private String field105;// 尿其他
	private String field106;// 空腹血糖mmol/L
	private String field107;// 空腹血糖mg/dL
	private String field108;// 同型半胱氨酸
	private String field109;// 尿微量蛋白
	private Integer field110;// 大便潜血1阴性2阳性
	private String field111;// 糖化血红蛋白
	private Integer field112;// 乙型肝炎表面抗原1阴性2阳性
	private String field113;// 乙肝mg/dL
	private String field114;// 血清谷丙转氨酶
	private String field115;// 血清谷草转氨酶
	private String field116;// 白蛋白
	private String field117;// 总胆红素
	private String field118;// 结合胆红素
	private String field119;// 血清肌酐
	private String field120;// 血尿素氮
	private String field121;// 血钾浓度1
	private String field122;// 血钾浓度2
	private String field123;// 总胆固醇
	private String field124;// 甘油三酯
	private String field125;// 血清低密度
	private String field126;// 血清高密度
	private String field127;// 心电图1正常2ST段改变3陈旧性心肌梗塞4窦性心动过速5窦性心动过缓6早搏7房颤8房室传导阻滞9其他
	private String field128;// 心电图其他
	private Integer field129;// 胸部X片1正常2异常
	private Integer field130;// 腹部B超1正常2异常
	private Integer field132;// 其他1正常2异常
	private String field133;// 腹部其他
	private Integer field134;// 宫颈涂片1正常2异常
	private String field135;// 宫颈涂片
	private String field136;// 其他2
	private String field137;// 脑血管疾病1未发现2缺血性卒中3脑出血4蛛网膜下腔出血5短暂性脑缺血6其他
	private String field138;// 脑血管疾病其他
	private String field139;// 肾脏疾病1未发现2糖尿病肾病3肾功能衰竭4急性肾炎5慢性肾炎6其他
	private String field140;// 肾脏疾病其他
	private String field141;// 心脏疾病1 未发现 2 心肌梗死 3 心绞痛 4 冠状动脉血运重建 5 充血性心力衰竭 6 心前区疼痛 7 其他
	private String field142;// 心脏疾病其他
	private String field143;// 眼部疾病1未发现2视网膜出血或渗出3视乳头水肿4白内障5其他
	private String field144;// 眼部疾病其他
	private String field145;// 神经系统疾病1未发现2阿尔茨海默症(老年痴呆症)3帕金森病4其他
	private String field146;// 神经系统疾病其他
	private String field147;// 其他系统疾病1未发现2糖尿病3慢性支气管炎4慢性阻塞性肺气肿5恶性肿瘤6老年性骨关节病7其他
	private String field148;// 其他系统疾病其他
	private Date field149;// 入出院日期1
	private Date field150;// 入出院日期2
	private Date field151;// 入出院日期3
	private Date field152;// 入出院日期4
	private String field153;// 原因1
	private String field154;// 原因2
	private String field155;// 医疗机构名称1
	private String field156;// 医疗机构名称2
	private String field157;// 病案号1
	private String field158;// 病案号2
	private Date field159;// 建/撤床日期1
	private Date field160;// 建/撤床日期2
	private Date field161;// 建/撤床日期3
	private Date field162;// 建/撤床日期4
	private String field163;// 家庭病床史原因1
	private String field164;// 家庭病床史原因2
	private String field165;// 家庭病床史医疗机构名称1
	private String field166;// 家庭病床史医疗机构名称2
	private String field167;// 家庭病床史病案号1
	private String field168;// 家庭病床史病案号2
	// 药物名称1
	private String field169;
	private Integer field170;// 用法(1)1口服2皮下注射3静脉注射4肌肉注射
	private String field171;// 用量1
	// 用药时间(1)1 1周 2 2周 3 3周 4 1个月 5 2个月 6 3个月 7 4个月 8 5个月 9 6个月 10 7个月 11 8个月 12
	// 9个月 13 10个月 14 11个月 15 1年 16 2年 17 3年 18 4年 19 5年
	private Integer field172;
	private Integer field173;// 服药依从性(1)1规律2间断3不服药
	// 药物名称2
	private String field174;
	private Integer field175;// 用法(2)1口服2皮下注射3静脉注射4肌肉注射
	private String field176;// 用量2
	// 用药时间(2)1 1周 2 2周 3 3周 4 1个月 5 2个月 6 3个月 7 4个月 8 5个月 9 6个月 10 7个月 11 8个月 12
	// 9个月 13 10个月 14 11个月 15 1年 16 2年 17 3年 18 4年 19 5年
	private Integer field177;
	private Integer field178;// 服药依从性(2)1规律2间断3不服药
	// 药物名称3
	private String field179;
	private Integer field180;// 用法(3)1口服2皮下注射3静脉注射4肌肉注射
	private String field181;// 用量3
	// 用药时间(3)1 1周 2 2周 3 3周 4 1个月 5 2个月 6 3个月 7 4个月 8 5个月 9 6个月 10 7个月 11 8个月 12
	// 9个月 13 10个月 14 11个月 15 1年 16 2年 17 3年 18 4年 19 5年
	private Integer field182;
	private Integer field183;// 服药依从性(3)1规律2间断3不服药
	// 药物名称4
	private String field184;
	private Integer field185;// 用法(4)1口服2皮下注射3静脉注射4肌肉注射
	private String field186;// 用量4
	// 用药时间(4)1 1周 2 2周 3 3周 4 1个月 5 2个月 6 3个月 7 4个月 8 5个月 9 6个月 10 7个月 11 8个月 12
	// 9个月 13 10个月 14 11个月 15 1年 16 2年 17 3年 18 4年 19 5年
	private Integer field187;
	private Integer field188;// 服药依从性(4)1规律2间断3不服药
	// 药物名称5
	private String field189;
	private Integer field190;// 用法(5)1口服2皮下注射3静脉注射4肌肉注射
	private String field191;// 用量5
	// 用药时间(5)1 1周 2 2周 3 3周 4 1个月 5 2个月 6 3个月 7 4个月 8 5个月 9 6个月 10 7个月 11 8个月 12
	// 9个月 13 10个月 14 11个月 15 1年 16 2年 17 3年 18 4年 19 5年
	private Integer field192;
	private Integer field193;// 服药依从性(5)1规律2间断3不服药
	// 药物名称6
	private String field194;
	private Integer field195;// 用法(6)1口服2皮下注射3静脉注射4肌肉注射
	private String field196;// 用量6
	// 用药时间(6)1 1周 2 2周 3 3周 4 1个月 5 2个月 6 3个月 7 4个月 8 5个月 9 6个月 10 7个月 11 8个月 12
	// 9个月 13 10个月 14 11个月 15 1年 16 2年 17 3年 18 4年 19 5年
	private Integer field197;
	private Integer field198;// 服药依从性(6)1规律2间断3不服药
	private String field199;// 非免疫名称1
	private Date field200;// 接种日期1
	private String field201;// 接种机构1
	private String field202;// 非免疫名称2
	private Date field203;// 接种日期2
	private String field204;// 接种机构2
	private String field205;// 非免疫名称3
	private Date field206;// 接种日期3
	private String field207;// 接种机构3
	private Integer field208;// 健康评价1体检无异常2有异常
	private String field209;// 健康评价异常1
	private String field210;// B超图片1
	private String field211;// 健康评价异常2
	private String field212;// 健康评价异常3
	private String field213;// 健康评价异常4
	private String field214;// 健康指导1定期随访2纳入慢性病患者健康管理3建议复查4建议转诊
	private String field215;// 危险因素控制1戒烟2健康饮酒3饮食4锻炼5减体重6建议接种疫苗7其他
	private String field216;// 目标
	private String field217;// 建议接种疫苗
	private String field218;// 健康体检其他
	private String field219;// BMI
	private String field222;// 红细胞计数
	private String field223;// 红细胞压积
	private String field224;// 淋巴细胞百分比
	private String field225;// 中间细胞百分比
	private String field226;// 中性粒细胞百分比
	private String field227;// 平均红细胞血红蛋白浓度
	private String field228;// 平均红细胞体积
	private String field229;// 平均红细胞血红蛋白含量
	private String field230;// 血小板分布宽度
	private String field231;// 红细胞分布宽度变异系数
	private String field232;// 平均血小板体积
	private String field233;// 血小板压积
	private String field234;// 淋巴细胞绝对值
	private String field235;// 中间细胞绝对值
	private String field236;// 中性粒细胞绝对值
	private String field237;// 尿胆红素
	private String field238;// 尿胆原
	private String field239;// 亚硝酸盐
	private String field240;// 尿白细胞
	private String field241;// 尿比重
	private String field242;// 尿酸碱值
	private String field243;// 红细胞分布宽度标准差
	private String field244;// 超声所见
	private String field245;// 超声提示 腹部
	private String field246;// 心电图图片
	private Date createDate;// 创建日期
	private Date updateDate;// 修改日期
	private Integer pid;// 病人
	private String field14;// 血常规检查员
	private String field247;// 尿常规检查员
	private String field248;// 生化检查员
	private String field249;// 心电图检查员
	private String field250;// B超检查员
	private String field251;// B超图片2
	private String field252;// B超图片3
	private String field253;// B超图片4
	private String field254;// B超图片5
	private String field255;// B超图片6
	private String field131;// 妇科异常1
	private String field220;// 妇科异常2
	private String field221;// 妇科异常3
	private String field256;// 妇科异常4
	private String field257;// 妇科异常5
	private String field258;// 尿蛋白单位
	private String field259;// 尿糖单位
	private String field260;// 尿胆红素单位
	private String field261;// 尿胆原单位
	private String field262;// 尿潜血单位
	private String field263;// 尿酮体单位
	private String field264;// 亚硝酸盐单位
	private String field265;// 尿白细胞单位
	private String field266;// 尿比重单位
	private String field267;// 尿酸碱值单位
	private String field268;// 血管疾病1 未发现 2 夹层动脉瘤 3 动脉闭塞性疾病 4 其他
	private String field269;// 血管疾病其他

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getField1() {
		return field1;
	}

	public void setField1(Date field1) {
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

	public Integer getField15() {
		return field15;
	}

	public void setField15(Integer field15) {
		this.field15 = field15;
	}

	public Integer getField16() {
		return field16;
	}

	public void setField16(Integer field16) {
		this.field16 = field16;
	}

	public Integer getField17() {
		return field17;
	}

	public void setField17(Integer field17) {
		this.field17 = field17;
	}

	public Integer getField18() {
		return field18;
	}

	public void setField18(Integer field18) {
		this.field18 = field18;
	}

	public Integer getField19() {
		return field19;
	}

	public void setField19(Integer field19) {
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

	public Integer getField24() {
		return field24;
	}

	public void setField24(Integer field24) {
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

	public Integer getField28() {
		return field28;
	}

	public void setField28(Integer field28) {
		this.field28 = field28;
	}

	public String getField29() {
		return field29;
	}

	public void setField29(String field29) {
		this.field29 = field29;
	}

	public Integer getField30() {
		return field30;
	}

	public void setField30(Integer field30) {
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

	public Integer getField33() {
		return field33;
	}

	public void setField33(Integer field33) {
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

	public Integer getField36() {
		return field36;
	}

	public void setField36(Integer field36) {
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

	public Integer getField40() {
		return field40;
	}

	public void setField40(Integer field40) {
		this.field40 = field40;
	}

	public String getField41() {
		return field41;
	}

	public void setField41(String field41) {
		this.field41 = field41;
	}

	public Integer getField42() {
		return field42;
	}

	public void setField42(Integer field42) {
		this.field42 = field42;
	}

	public String getField43() {
		return field43;
	}

	public void setField43(String field43) {
		this.field43 = field43;
	}

	public Integer getField44() {
		return field44;
	}

	public void setField44(Integer field44) {
		this.field44 = field44;
	}

	public String getField45() {
		return field45;
	}

	public void setField45(String field45) {
		this.field45 = field45;
	}

	public Integer getField46() {
		return field46;
	}

	public void setField46(Integer field46) {
		this.field46 = field46;
	}

	public String getField47() {
		return field47;
	}

	public void setField47(String field47) {
		this.field47 = field47;
	}

	public Integer getField48() {
		return field48;
	}

	public void setField48(Integer field48) {
		this.field48 = field48;
	}

	public Integer getField49() {
		return field49;
	}

	public void setField49(Integer field49) {
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

	public Integer getField63() {
		return field63;
	}

	public void setField63(Integer field63) {
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

	public Integer getField68() {
		return field68;
	}

	public void setField68(Integer field68) {
		this.field68 = field68;
	}

	public Integer getField69() {
		return field69;
	}

	public void setField69(Integer field69) {
		this.field69 = field69;
	}

	public Integer getField70() {
		return field70;
	}

	public void setField70(Integer field70) {
		this.field70 = field70;
	}

	public Integer getField71() {
		return field71;
	}

	public void setField71(Integer field71) {
		this.field71 = field71;
	}

	public Integer getField72() {
		return field72;
	}

	public void setField72(Integer field72) {
		this.field72 = field72;
	}

	public Integer getField73() {
		return field73;
	}

	public void setField73(Integer field73) {
		this.field73 = field73;
	}

	public Integer getField74() {
		return field74;
	}

	public void setField74(Integer field74) {
		this.field74 = field74;
	}

	public Integer getField75() {
		return field75;
	}

	public void setField75(Integer field75) {
		this.field75 = field75;
	}

	public Integer getField76() {
		return field76;
	}

	public void setField76(Integer field76) {
		this.field76 = field76;
	}

	public String getField77() {
		return field77;
	}

	public void setField77(String field77) {
		this.field77 = field77;
	}

	public Integer getField78() {
		return field78;
	}

	public void setField78(Integer field78) {
		this.field78 = field78;
	}

	public Integer getField79() {
		return field79;
	}

	public void setField79(Integer field79) {
		this.field79 = field79;
	}

	public Integer getField80() {
		return field80;
	}

	public void setField80(Integer field80) {
		this.field80 = field80;
	}

	public Integer getField81() {
		return field81;
	}

	public void setField81(Integer field81) {
		this.field81 = field81;
	}

	public Integer getField82() {
		return field82;
	}

	public void setField82(Integer field82) {
		this.field82 = field82;
	}

	public Integer getField83() {
		return field83;
	}

	public void setField83(Integer field83) {
		this.field83 = field83;
	}

	public Integer getField84() {
		return field84;
	}

	public void setField84(Integer field84) {
		this.field84 = field84;
	}

	public Integer getField85() {
		return field85;
	}

	public void setField85(Integer field85) {
		this.field85 = field85;
	}

	public Integer getField86() {
		return field86;
	}

	public void setField86(Integer field86) {
		this.field86 = field86;
	}

	public Integer getField87() {
		return field87;
	}

	public void setField87(Integer field87) {
		this.field87 = field87;
	}

	public Integer getField88() {
		return field88;
	}

	public void setField88(Integer field88) {
		this.field88 = field88;
	}

	public Integer getField89() {
		return field89;
	}

	public void setField89(Integer field89) {
		this.field89 = field89;
	}

	public Integer getField90() {
		return field90;
	}

	public void setField90(Integer field90) {
		this.field90 = field90;
	}

	public Integer getField91() {
		return field91;
	}

	public void setField91(Integer field91) {
		this.field91 = field91;
	}

	public Integer getField92() {
		return field92;
	}

	public void setField92(Integer field92) {
		this.field92 = field92;
	}

	public Integer getField93() {
		return field93;
	}

	public void setField93(Integer field93) {
		this.field93 = field93;
	}

	public String getField94() {
		return field94;
	}

	public void setField94(String field94) {
		this.field94 = field94;
	}

	public Integer getField95() {
		return field95;
	}

	public void setField95(Integer field95) {
		this.field95 = field95;
	}

	public Integer getField96() {
		return field96;
	}

	public void setField96(Integer field96) {
		this.field96 = field96;
	}

	public String getField97() {
		return field97;
	}

	public void setField97(String field97) {
		this.field97 = field97;
	}

	public String getField98() {
		return field98;
	}

	public void setField98(String field98) {
		this.field98 = field98;
	}

	public String getField99() {
		return field99;
	}

	public void setField99(String field99) {
		this.field99 = field99;
	}

	public String getField100() {
		return field100;
	}

	public void setField100(String field100) {
		this.field100 = field100;
	}

	public String getField101() {
		return field101;
	}

	public void setField101(String field101) {
		this.field101 = field101;
	}

	public String getField102() {
		return field102;
	}

	public void setField102(String field102) {
		this.field102 = field102;
	}

	public String getField103() {
		return field103;
	}

	public void setField103(String field103) {
		this.field103 = field103;
	}

	public String getField104() {
		return field104;
	}

	public void setField104(String field104) {
		this.field104 = field104;
	}

	public String getField105() {
		return field105;
	}

	public void setField105(String field105) {
		this.field105 = field105;
	}

	public String getField106() {
		return field106;
	}

	public void setField106(String field106) {
		this.field106 = field106;
	}

	public String getField107() {
		return field107;
	}

	public void setField107(String field107) {
		this.field107 = field107;
	}

	public String getField108() {
		return field108;
	}

	public void setField108(String field108) {
		this.field108 = field108;
	}

	public String getField109() {
		return field109;
	}

	public void setField109(String field109) {
		this.field109 = field109;
	}

	public Integer getField110() {
		return field110;
	}

	public void setField110(Integer field110) {
		this.field110 = field110;
	}

	public String getField111() {
		return field111;
	}

	public void setField111(String field111) {
		this.field111 = field111;
	}

	public Integer getField112() {
		return field112;
	}

	public void setField112(Integer field112) {
		this.field112 = field112;
	}

	public String getField113() {
		return field113;
	}

	public void setField113(String field113) {
		this.field113 = field113;
	}

	public String getField114() {
		return field114;
	}

	public void setField114(String field114) {
		this.field114 = field114;
	}

	public String getField115() {
		return field115;
	}

	public void setField115(String field115) {
		this.field115 = field115;
	}

	public String getField116() {
		return field116;
	}

	public void setField116(String field116) {
		this.field116 = field116;
	}

	public String getField117() {
		return field117;
	}

	public void setField117(String field117) {
		this.field117 = field117;
	}

	public String getField118() {
		return field118;
	}

	public void setField118(String field118) {
		this.field118 = field118;
	}

	public String getField119() {
		return field119;
	}

	public void setField119(String field119) {
		this.field119 = field119;
	}

	public String getField120() {
		return field120;
	}

	public void setField120(String field120) {
		this.field120 = field120;
	}

	public String getField121() {
		return field121;
	}

	public void setField121(String field121) {
		this.field121 = field121;
	}

	public String getField122() {
		return field122;
	}

	public void setField122(String field122) {
		this.field122 = field122;
	}

	public String getField123() {
		return field123;
	}

	public void setField123(String field123) {
		this.field123 = field123;
	}

	public String getField124() {
		return field124;
	}

	public void setField124(String field124) {
		this.field124 = field124;
	}

	public String getField125() {
		return field125;
	}

	public void setField125(String field125) {
		this.field125 = field125;
	}

	public String getField126() {
		return field126;
	}

	public void setField126(String field126) {
		this.field126 = field126;
	}

	public String getField127() {
		return field127;
	}

	public void setField127(String field127) {
		this.field127 = field127;
	}

	public String getField128() {
		return field128;
	}

	public void setField128(String field128) {
		this.field128 = field128;
	}

	public Integer getField129() {
		return field129;
	}

	public void setField129(Integer field129) {
		this.field129 = field129;
	}

	public Integer getField130() {
		return field130;
	}

	public void setField130(Integer field130) {
		this.field130 = field130;
	}

	public String getField131() {
		return field131;
	}

	public void setField131(String field131) {
		this.field131 = field131;
	}

	public Integer getField132() {
		return field132;
	}

	public void setField132(Integer field132) {
		this.field132 = field132;
	}

	public String getField133() {
		return field133;
	}

	public void setField133(String field133) {
		this.field133 = field133;
	}

	public Integer getField134() {
		return field134;
	}

	public void setField134(Integer field134) {
		this.field134 = field134;
	}

	public String getField135() {
		return field135;
	}

	public void setField135(String field135) {
		this.field135 = field135;
	}

	public String getField136() {
		return field136;
	}

	public void setField136(String field136) {
		this.field136 = field136;
	}

	public String getField137() {
		return field137;
	}

	public void setField137(String field137) {
		this.field137 = field137;
	}

	public String getField138() {
		return field138;
	}

	public void setField138(String field138) {
		this.field138 = field138;
	}

	public String getField139() {
		return field139;
	}

	public void setField139(String field139) {
		this.field139 = field139;
	}

	public String getField140() {
		return field140;
	}

	public void setField140(String field140) {
		this.field140 = field140;
	}

	public String getField141() {
		return field141;
	}

	public void setField141(String field141) {
		this.field141 = field141;
	}

	public String getField142() {
		return field142;
	}

	public void setField142(String field142) {
		this.field142 = field142;
	}

	public String getField143() {
		return field143;
	}

	public void setField143(String field143) {
		this.field143 = field143;
	}

	public String getField144() {
		return field144;
	}

	public void setField144(String field144) {
		this.field144 = field144;
	}

	public String getField145() {
		return field145;
	}

	public void setField145(String field145) {
		this.field145 = field145;
	}

	public String getField146() {
		return field146;
	}

	public void setField146(String field146) {
		this.field146 = field146;
	}

	public String getField147() {
		return field147;
	}

	public void setField147(String field147) {
		this.field147 = field147;
	}

	public String getField148() {
		return field148;
	}

	public void setField148(String field148) {
		this.field148 = field148;
	}

	public Date getField149() {
		return field149;
	}

	public void setField149(Date field149) {
		this.field149 = field149;
	}

	public Date getField150() {
		return field150;
	}

	public void setField150(Date field150) {
		this.field150 = field150;
	}

	public Date getField151() {
		return field151;
	}

	public void setField151(Date field151) {
		this.field151 = field151;
	}

	public Date getField152() {
		return field152;
	}

	public void setField152(Date field152) {
		this.field152 = field152;
	}

	public String getField153() {
		return field153;
	}

	public void setField153(String field153) {
		this.field153 = field153;
	}

	public String getField154() {
		return field154;
	}

	public void setField154(String field154) {
		this.field154 = field154;
	}

	public String getField155() {
		return field155;
	}

	public void setField155(String field155) {
		this.field155 = field155;
	}

	public String getField156() {
		return field156;
	}

	public void setField156(String field156) {
		this.field156 = field156;
	}

	public String getField157() {
		return field157;
	}

	public void setField157(String field157) {
		this.field157 = field157;
	}

	public String getField158() {
		return field158;
	}

	public void setField158(String field158) {
		this.field158 = field158;
	}

	public Date getField159() {
		return field159;
	}

	public void setField159(Date field159) {
		this.field159 = field159;
	}

	public Date getField160() {
		return field160;
	}

	public void setField160(Date field160) {
		this.field160 = field160;
	}

	public Date getField161() {
		return field161;
	}

	public void setField161(Date field161) {
		this.field161 = field161;
	}

	public Date getField162() {
		return field162;
	}

	public void setField162(Date field162) {
		this.field162 = field162;
	}

	public String getField163() {
		return field163;
	}

	public void setField163(String field163) {
		this.field163 = field163;
	}

	public String getField164() {
		return field164;
	}

	public void setField164(String field164) {
		this.field164 = field164;
	}

	public String getField165() {
		return field165;
	}

	public void setField165(String field165) {
		this.field165 = field165;
	}

	public String getField166() {
		return field166;
	}

	public void setField166(String field166) {
		this.field166 = field166;
	}

	public String getField167() {
		return field167;
	}

	public void setField167(String field167) {
		this.field167 = field167;
	}

	public String getField168() {
		return field168;
	}

	public void setField168(String field168) {
		this.field168 = field168;
	}

	public String getField169() {
		return field169;
	}

	public void setField169(String field169) {
		this.field169 = field169;
	}

	public Integer getField170() {
		return field170;
	}

	public void setField170(Integer field170) {
		this.field170 = field170;
	}

	public String getField171() {
		return field171;
	}

	public void setField171(String field171) {
		this.field171 = field171;
	}

	public Integer getField172() {
		return field172;
	}

	public void setField172(Integer field172) {
		this.field172 = field172;
	}

	public Integer getField173() {
		return field173;
	}

	public void setField173(Integer field173) {
		this.field173 = field173;
	}

	public String getField174() {
		return field174;
	}

	public void setField174(String field174) {
		this.field174 = field174;
	}

	public Integer getField175() {
		return field175;
	}

	public void setField175(Integer field175) {
		this.field175 = field175;
	}

	public String getField176() {
		return field176;
	}

	public void setField176(String field176) {
		this.field176 = field176;
	}

	public Integer getField177() {
		return field177;
	}

	public void setField177(Integer field177) {
		this.field177 = field177;
	}

	public Integer getField178() {
		return field178;
	}

	public void setField178(Integer field178) {
		this.field178 = field178;
	}

	public String getField179() {
		return field179;
	}

	public void setField179(String field179) {
		this.field179 = field179;
	}

	public Integer getField180() {
		return field180;
	}

	public void setField180(Integer field180) {
		this.field180 = field180;
	}

	public String getField181() {
		return field181;
	}

	public void setField181(String field181) {
		this.field181 = field181;
	}

	public Integer getField182() {
		return field182;
	}

	public void setField182(Integer field182) {
		this.field182 = field182;
	}

	public Integer getField183() {
		return field183;
	}

	public void setField183(Integer field183) {
		this.field183 = field183;
	}

	public String getField184() {
		return field184;
	}

	public void setField184(String field184) {
		this.field184 = field184;
	}

	public Integer getField185() {
		return field185;
	}

	public void setField185(Integer field185) {
		this.field185 = field185;
	}

	public String getField186() {
		return field186;
	}

	public void setField186(String field186) {
		this.field186 = field186;
	}

	public Integer getField187() {
		return field187;
	}

	public void setField187(Integer field187) {
		this.field187 = field187;
	}

	public Integer getField188() {
		return field188;
	}

	public void setField188(Integer field188) {
		this.field188 = field188;
	}

	public String getField189() {
		return field189;
	}

	public void setField189(String field189) {
		this.field189 = field189;
	}

	public Integer getField190() {
		return field190;
	}

	public void setField190(Integer field190) {
		this.field190 = field190;
	}

	public String getField191() {
		return field191;
	}

	public void setField191(String field191) {
		this.field191 = field191;
	}

	public Integer getField192() {
		return field192;
	}

	public void setField192(Integer field192) {
		this.field192 = field192;
	}

	public Integer getField193() {
		return field193;
	}

	public void setField193(Integer field193) {
		this.field193 = field193;
	}

	public String getField194() {
		return field194;
	}

	public void setField194(String field194) {
		this.field194 = field194;
	}

	public Integer getField195() {
		return field195;
	}

	public void setField195(Integer field195) {
		this.field195 = field195;
	}

	public String getField196() {
		return field196;
	}

	public void setField196(String field196) {
		this.field196 = field196;
	}

	public Integer getField197() {
		return field197;
	}

	public void setField197(Integer field197) {
		this.field197 = field197;
	}

	public Integer getField198() {
		return field198;
	}

	public void setField198(Integer field198) {
		this.field198 = field198;
	}

	public String getField199() {
		return field199;
	}

	public void setField199(String field199) {
		this.field199 = field199;
	}

	public Date getField200() {
		return field200;
	}

	public void setField200(Date field200) {
		this.field200 = field200;
	}

	public String getField201() {
		return field201;
	}

	public void setField201(String field201) {
		this.field201 = field201;
	}

	public String getField202() {
		return field202;
	}

	public void setField202(String field202) {
		this.field202 = field202;
	}

	public Date getField203() {
		return field203;
	}

	public void setField203(Date field203) {
		this.field203 = field203;
	}

	public String getField204() {
		return field204;
	}

	public void setField204(String field204) {
		this.field204 = field204;
	}

	public String getField205() {
		return field205;
	}

	public void setField205(String field205) {
		this.field205 = field205;
	}

	public Date getField206() {
		return field206;
	}

	public void setField206(Date field206) {
		this.field206 = field206;
	}

	public String getField207() {
		return field207;
	}

	public void setField207(String field207) {
		this.field207 = field207;
	}

	public Integer getField208() {
		return field208;
	}

	public void setField208(Integer field208) {
		this.field208 = field208;
	}

	public String getField209() {
		return field209;
	}

	public void setField209(String field209) {
		this.field209 = field209;
	}

	public String getField210() {
		return field210;
	}

	public void setField210(String field210) {
		this.field210 = field210;
	}

	public String getField211() {
		return field211;
	}

	public void setField211(String field211) {
		this.field211 = field211;
	}

	public String getField212() {
		return field212;
	}

	public void setField212(String field212) {
		this.field212 = field212;
	}

	public String getField213() {
		return field213;
	}

	public void setField213(String field213) {
		this.field213 = field213;
	}

	public String getField214() {
		return field214;
	}

	public void setField214(String field214) {
		this.field214 = field214;
	}

	public String getField215() {
		return field215;
	}

	public void setField215(String field215) {
		this.field215 = field215;
	}

	public String getField216() {
		return field216;
	}

	public void setField216(String field216) {
		this.field216 = field216;
	}

	public String getField217() {
		return field217;
	}

	public void setField217(String field217) {
		this.field217 = field217;
	}

	public String getField218() {
		return field218;
	}

	public void setField218(String field218) {
		this.field218 = field218;
	}

	public String getField219() {
		return field219;
	}

	public void setField219(String field219) {
		this.field219 = field219;
	}

	public String getField220() {
		return field220;
	}

	public void setField220(String field220) {
		this.field220 = field220;
	}

	public String getField221() {
		return field221;
	}

	public void setField221(String field221) {
		this.field221 = field221;
	}

	public String getField222() {
		return field222;
	}

	public void setField222(String field222) {
		this.field222 = field222;
	}

	public String getField223() {
		return field223;
	}

	public void setField223(String field223) {
		this.field223 = field223;
	}

	public String getField224() {
		return field224;
	}

	public void setField224(String field224) {
		this.field224 = field224;
	}

	public String getField225() {
		return field225;
	}

	public void setField225(String field225) {
		this.field225 = field225;
	}

	public String getField226() {
		return field226;
	}

	public void setField226(String field226) {
		this.field226 = field226;
	}

	public String getField227() {
		return field227;
	}

	public void setField227(String field227) {
		this.field227 = field227;
	}

	public String getField228() {
		return field228;
	}

	public void setField228(String field228) {
		this.field228 = field228;
	}

	public String getField229() {
		return field229;
	}

	public void setField229(String field229) {
		this.field229 = field229;
	}

	public String getField230() {
		return field230;
	}

	public void setField230(String field230) {
		this.field230 = field230;
	}

	public String getField231() {
		return field231;
	}

	public void setField231(String field231) {
		this.field231 = field231;
	}

	public String getField232() {
		return field232;
	}

	public void setField232(String field232) {
		this.field232 = field232;
	}

	public String getField233() {
		return field233;
	}

	public void setField233(String field233) {
		this.field233 = field233;
	}

	public String getField234() {
		return field234;
	}

	public void setField234(String field234) {
		this.field234 = field234;
	}

	public String getField235() {
		return field235;
	}

	public void setField235(String field235) {
		this.field235 = field235;
	}

	public String getField236() {
		return field236;
	}

	public void setField236(String field236) {
		this.field236 = field236;
	}

	public String getField237() {
		return field237;
	}

	public void setField237(String field237) {
		this.field237 = field237;
	}

	public String getField238() {
		return field238;
	}

	public void setField238(String field238) {
		this.field238 = field238;
	}

	public String getField239() {
		return field239;
	}

	public void setField239(String field239) {
		this.field239 = field239;
	}

	public String getField240() {
		return field240;
	}

	public void setField240(String field240) {
		this.field240 = field240;
	}

	public String getField241() {
		return field241;
	}

	public void setField241(String field241) {
		this.field241 = field241;
	}

	public String getField242() {
		return field242;
	}

	public void setField242(String field242) {
		this.field242 = field242;
	}

	public String getField243() {
		return field243;
	}

	public void setField243(String field243) {
		this.field243 = field243;
	}

	public String getField244() {
		return field244;
	}

	public void setField244(String field244) {
		this.field244 = field244;
	}

	public String getField245() {
		return field245;
	}

	public void setField245(String field245) {
		this.field245 = field245;
	}

	public String getField246() {
		return field246;
	}

	public void setField246(String field246) {
		this.field246 = field246;
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

	public String getField14() {
		return field14;
	}

	public void setField14(String field14) {
		this.field14 = field14;
	}

	public String getField247() {
		return field247;
	}

	public void setField247(String field247) {
		this.field247 = field247;
	}

	public String getField248() {
		return field248;
	}

	public void setField248(String field248) {
		this.field248 = field248;
	}

	public String getField249() {
		return field249;
	}

	public void setField249(String field249) {
		this.field249 = field249;
	}

	public String getField250() {
		return field250;
	}

	public void setField250(String field250) {
		this.field250 = field250;
	}

	public String getField251() {
		return field251;
	}

	public void setField251(String field251) {
		this.field251 = field251;
	}

	public String getField252() {
		return field252;
	}

	public void setField252(String field252) {
		this.field252 = field252;
	}

	public String getField253() {
		return field253;
	}

	public void setField253(String field253) {
		this.field253 = field253;
	}

	public String getField254() {
		return field254;
	}

	public void setField254(String field254) {
		this.field254 = field254;
	}

	public String getField255() {
		return field255;
	}

	public void setField255(String field255) {
		this.field255 = field255;
	}

	public String getField256() {
		return field256;
	}

	public void setField256(String field256) {
		this.field256 = field256;
	}

	public String getField257() {
		return field257;
	}

	public void setField257(String field257) {
		this.field257 = field257;
	}

	public String getField258() {
		return field258;
	}

	public void setField258(String field258) {
		this.field258 = field258;
	}

	public String getField259() {
		return field259;
	}

	public void setField259(String field259) {
		this.field259 = field259;
	}

	public String getField260() {
		return field260;
	}

	public void setField260(String field260) {
		this.field260 = field260;
	}

	public String getField261() {
		return field261;
	}

	public void setField261(String field261) {
		this.field261 = field261;
	}

	public String getField262() {
		return field262;
	}

	public void setField262(String field262) {
		this.field262 = field262;
	}

	public String getField263() {
		return field263;
	}

	public void setField263(String field263) {
		this.field263 = field263;
	}

	public String getField264() {
		return field264;
	}

	public void setField264(String field264) {
		this.field264 = field264;
	}

	public String getField265() {
		return field265;
	}

	public void setField265(String field265) {
		this.field265 = field265;
	}

	public String getField266() {
		return field266;
	}

	public void setField266(String field266) {
		this.field266 = field266;
	}

	public String getField267() {
		return field267;
	}

	public void setField267(String field267) {
		this.field267 = field267;
	}

	public String getField268() {
		return field268;
	}

	public void setField268(String field268) {
		this.field268 = field268;
	}

	public String getField269() {
		return field269;
	}

	public void setField269(String field269) {
		this.field269 = field269;
	}

}
