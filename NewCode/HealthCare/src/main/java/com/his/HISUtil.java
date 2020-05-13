package com.his;

import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.binary.Base64;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;

public class HISUtil {

	private static final String URL = "http://171.221.252.195:10095/webservice.asmx";
	private static final String APPID = "RGgLHljxb39uzo";
	private static final String APPSECRET = "3ctG9y8P";
	private static final String RANDOMKEY = "hello";

	public static String digest(byte[] data, String algorithm) throws Exception {
		MessageDigest messageDigest = MessageDigest.getInstance(algorithm);
		return Base64.encodeBase64URLSafeString(messageDigest.digest(data));
	}

	/**
	 * 
	 * @param TradeCode
	 * @param InputParameter
	 * @param code
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private static Map<String, Object> send(String TradeCode, String InputParameter, String regionCode)
			throws Exception {
		Map<String, Object> rmap = new HashMap<String, Object>();
		Map<String, String> map = new HashMap<String, String>();
		String appId = APPID;
		String appSecret = APPSECRET;
		String randomKey = RANDOMKEY;
		// 在消息体中放入认证信息
		map.put("o_app_id", appId);
		map.put("o_random_key", randomKey);
		map.put("o_digest_str", digest((appId + appSecret + randomKey).getBytes("utf-8"), "SHA-256"));
		map.put("r_region_code", regionCode);
		String p1 = JSON.toJSONString(map);
		p1 = p1.substring(0, p1.length() - 1);
		InputParameter = InputParameter.substring(1);
		InputParameter = p1 + "," + InputParameter;
		WebServiceSoapProxy p = new WebServiceSoapProxy();
		p.setWebServiceSoap_address(URL);
		String restultStr = p.HIS_Interface(TradeCode, InputParameter);
		rmap = (Map<String, Object>) JSON.parse(restultStr);
		return rmap;
	}

	/**
	 * 根据身份证查询人员id
	 * 
	 * @param idCard
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String getPersonId(String idCard, String PRODUCTCODE, String regionCode) throws Exception {
		String resultStr = "";
		Map<String, Object> map = new HashMap<String, Object>();
		String p1 = "{\"ProductCode\": \"" + PRODUCTCODE + "\",\"IDCARD\":\"" + idCard
				+ "\",\"RegionCode\":\"51\",\"Status\":-1}";
		map = send("55-11", p1, regionCode);
		Object result = map.get("Result");
		if (result != null && "1".equals(result.toString())) {
			List<Object> l = (List<Object>) map.get("Msg");
			if (l.size() > 0) {
				map = (Map<String, Object>) l.get(0);
				resultStr = map.get("ID").toString();
			}
		}
		return resultStr;
	}

	/**
	 * 新增居民信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static String addPerson(String BuildDate, String MasterName, String RegionID, String CardID,
			String GenderCode, String MarryStatus, String JobCode, String HukouInd, String EducationCode,
			String ContactPerson, String BirthDay, String RegionCode, String PersonTel, String ContaceTel,
			String BloodType, String RhBlood, String PaymentWaystring, String OtherPaymentWaystring,
			String DrugAllergyHistory, String ExposureHistory, String Disability, String KitchenExhaust,
			String FuelType, String Drinkingwater, String Toilet, String LivestockColumn, String PRODUCTCODE,
			String regionCode) throws Exception {
		String resultStr = "";
		Map<String, Object> map = new HashMap<String, Object>();
		// 增加家庭信息
		String p1 = "{\r\n" + "  \"ProductCode\": \"" + PRODUCTCODE + "\",\r\n" + " \"FamilyList\": [\r\n" + "    {\r\n"
				+ "      \"BuildDate\": \"" + BuildDate + "\",\r\n" + // 建档日期
				"      \"MasterName\": \"" + MasterName + "\",\r\n" + // 户主姓名
				"      \"RegionID\": \"" + RegionID + "\"\r\n" + // 区划
				"    }\r\n" + "  ]\r\n" + "}";
		map = send("54-1", p1, regionCode);
		Object FamilyID = "";
		Object result = map.get("Result");
		if (result != null && "1".equals(result.toString())) {
			map = (Map<String, Object>) map.get("Msg");
			FamilyID = map.get("ID");
		}
		if (!StringUtils.isEmpty(FamilyID)) {
			// 增加居民信息
			String p2 = "{\r\n" + "  \"ProductCode\": \"" + PRODUCTCODE + "\",\r\n" + "  \"PersonList\": [\r\n"
					+ "    {      \r\n" + "      \"CardID\": \"" + CardID + "\",\r\n" + // 身份证号码
					"      \"NationCode\":1,\r\n" + // 民族代码 01汉族
					"      \"GenderCode\": \"" + GenderCode + "\",\r\n" + // 性别 0未知的性别,1男,2女,9未说明的性别
					"      \"MarryStatus\": \"" + MarryStatus + "\",\r\n" + // 婚姻状况 1未婚,2已婚,3丧偶,4离婚,5未说明的婚姻状况
					"      \"JobCode\": \"" + JobCode + "\",\r\n" + // 职业代码 1国家机关 党群组织 企业 事业单位负责人,2专业技术人员,3办事人员和有关人员,4商业
																	// 服务业人员,5农 林 牧 渔 水利业生产人员,6生产
																	// 运输设备操作人员及有关人员,7军人,8不便分类的其他从业人员,9无职业(选择8后面加上选择的身份的拼音小写，例如:8_xs，表示学生)
					"      \"HukouInd\": \"" + HukouInd + "\",\r\n" + // 常住类型 （1 户籍，0 非户籍）
					"      \"EducationCode\": \"" + EducationCode + "\",\r\n" + // 文化程度
																				// 11研究生,12大学本科,13大学专科和专科学校,14中等专业学校,15技工学校,16高中，3初中，2小学，1文盲和半文盲，6不详
					"      \"ContactPerson\": \"" + ContactPerson + "\",\r\n" + // 联系人姓名
					"      \"NationalityCode\": \"01\",\r\n" + // 国籍代码 01中国 CHN
					"      \"Name\": \"" + MasterName + "\",\r\n" + // 名称
					"      \"BirthDay\": \"" + BirthDay + "\",\r\n" + // 生日
					"      \"BuildDate\": \"" + BuildDate + "\",\r\n" + // 建档时间
					"      \"FamilyID\": \"" + FamilyID + "\",\r\n" + // 家庭ID
					"      \"RegionCode\": \"" + RegionCode + "\",\r\n" + // 档案所在区划代码
					"      \"PersonTel\": \"" + PersonTel + "\",\r\n" + // 本人电话
					"      \"ContaceTel\": \"" + ContaceTel + "\",\r\n" + // 联系人电话
					"      \"BloodType\": \"" + BloodType + "\",\r\n" + // 血型 1 A型,2 B型,3 o型,4 AB型, 5 不详
					"      \"RhBlood\": \"" + RhBlood + "\",\r\n" + // RH阴性 1 阳性,2 阴性,3 不详
					"      \"PaymentWaystring\": \"" + PaymentWaystring + "\",\r\n" + // 医疗费用支付方式 1 城镇职工基本医疗保险,2
																						// 城镇居民基本医疗保险,4 新型农村合作医疗8贫困救助，16
																						// 商业医疗保险 ，32 全公费 ，64
																						// 全自费(选了64就不能选择其它选项了) ，128 其他
					"      \"OtherPaymentWaystring\": \"" + OtherPaymentWaystring + "\",\r\n" + // 医疗费用其他
					"      \"DrugAllergyHistory\": \"" + DrugAllergyHistory + "\",\r\n" + // 药物过敏史 1 无(选1就不能选择其它选项), 2
																							// 青霉素, 4 磺胺, 8 链霉素, 16 其他
					"      \"ExposureHistory\":\"" + ExposureHistory + "\",\r\n" + // 暴露史 1 无，2 化学品，4 毒物，8 射线
					"      \"Disability\": \"" + Disability + "\",\r\n" + // 残疾情况 1 无(选1就不能选择其它选项),2 视力残疾,4 听力残疾,8 语言残疾,
																			// 16 肢体残疾,32 智力残疾,64 精神残疾,128 其他残疾
					"      \"KitchenExhaust\": " + KitchenExhaust + ",\r\n" + // 厨房排风设施 1 无(选1就不能选择其它选项), 2 油烟机, 4 换气扇,8
																				// 烟囱
					"      \"FuelType\": \"" + FuelType + "\",\r\n" + // 燃料类型 1 液化气,2 煤,4 天然气,8 沼气,16 柴火,32 其他
					"      \"Drinkingwater\": \"" + Drinkingwater + "\",\r\n" + // 饮水 1 自来水, 2经净化过滤的水, 4井水, 8河湖水,16
																				// 塘水,32 其他
					"      \"Toilet\": \"" + Toilet + "\",\r\n" + // 厕所 1卫生厕所, 2一格或二格粪池式,3马桶,4露天粪坑,5 简易棚厕
					"      \"LivestockColumn\": \"" + LivestockColumn + "\",\r\n" + // 禽畜栏 0 无，1 单设,2 室内,3 室外
					"      \"HouseholderRelationship\": 1\r\n" + // 与户主关系 1本人
					"    }\r\n" + "  ]\r\n" + "}";
			map = send("55-2", p2, regionCode);
			Object result2 = map.get("Result");
			if (result2 != null && "1".equals(result2.toString())) {
				map = (Map<String, Object>) map.get("Msg");
				if (!StringUtils.isEmpty(map.get("ID"))) {
					resultStr = map.get("ID").toString();
				}
			}
		}
		return resultStr;
	}

	/**
	 * 增加或修改个人健康体检记录
	 * 
	 * @param areaCode 0开江1西充县
	 * @return
	 * @throws Exception
	 */
	public static Map<String, Object> upPersonData(String PersonID, String ExamDate, String Symptom,
			String AssessmentAbnormal, String Guidance, String RiskCrtl, String RiskCrtlWeight, String RiskCrtlVaccine,
			String RiskCrtlOther, String DoctorID, String DoctorName, String BodyTemperature, String PulseRate,
			String HeartRate, String RespiratoryRate, String LeftSbp, String LeftDbp, String RightSbp, String RightDbp,
			String Height, String Weight, String Waistline, String Bmi, String DorsalisPedisArteryPulse, String Lips,
			String Dentition, String MissingTeeth, String Caries, String Denture, String Throat, String LeftEye,
			String RightEye, String LeftEyeVc, String RightEyeVc, String Hearing, String MotorFunction, String Fundus,
			String Skin, String Sclera, String LymphNodes, String BarrelChest, String BreathSounds, String Rale,
			String Rhythm, String HeartMurmur, String AbdominalTenderness, String AbdominalMass, String TheAbdomenLiver,
			String Splenomegaly, String ShiftingDullness, String LowerExtremityEdema, String Dre, String Breast,
			String FastingBloodGlucose, String Hemoglobin, String Leukocyte, String Platelet, String OtherBlood,
			String UrineProtein, String Urine, String Ketone, String OccultBloodInUrine, String OtherUrine,
			String UrinaryAlbumin, String FecalOccultBlood, String SerumAla, String SerumAa, String Albumin,
			String TotalBilirubin, String Bilirubin, String SerumCreatinine, String Bun, String PotassiumConcentration,
			String TotalCholesterol, String Triglycerides, String LdlCholesterol, String HdlCholesterol,
			String GlycatedHemoglobin, String Ecg, String ChestXRay, String BUltrasonicOther, String Erythrocytes,
			String DifferentialCount, String Sg, String Ph, String Drug, String Hospt, String ExerciseFrequency,
			String EachExerciseTime, String ExerciseTime, String ExerciseMethod, String Diet, String SmokingStatus,
			String Smoking, String SmokingAge, String AgeQuit, String DrinkingFrequency, String DailyAlcoholIntake,
			String IsAlcohol, String AlcoholAge, String AgeStartedDrinking, String IsDrunkLastYear, String AlcoholType,
			String IsOe, String Occupation, String WorkingTime, String Cerebrovascular, String Kidney, String Heart,
			String Vascular, String Eyes, String Nervoussystems, String Others, String Health, String LifeSkills,
			String LifeSkillsScore, String CognitiveFunction, String EmotionalState, String ModerateQuality,
			String QualityDeficiency, String YangQuality, String YinQuality, String Phlegm, String DampHeat,
			String BloodQuality, String QiQuality, String TeBingQuality, String A01, String A02, String A03, String A04,
			String A05, String A06, String A07, String A08, String A09, String A10, String A11, String A12, String A13,
			String A14, String A15, String A16, String A17, String A18, String A19, String A20, String A21, String A22,
			String A23, String A24, String A25, String A26, String A27, String A28, String A29, String A30, String A31,
			String A32, String A33, String Other, String TcHealthGuides, String PRODUCTCODE, String regionCode,
			int TcHealthGuide, String BUltrasonicWave, String Vulva, String Vaginal, String Cervix, String Palace,
			String UterineAdnexa, String WOther,String MtID) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		boolean bol = false;
		String p1 = "{\r\n" + "    \"ProductCode\": \"" + PRODUCTCODE + "\",\r\n" + "    \"DataSouceCode\": \"\",\r\n" + // 数据来源编码
				"    \"MtID\": \"" + MtID + "\",\r\n" + // 体检主表ID
				"    \"ans1\": \"[]\",\r\n" + // 自理能力自我评估
				"    \"ans2\": \"[" + CognitiveFunction + "]\",\r\n" + // 认知功能
				"    \"ans3\": \"[" + EmotionalState + "]\",\r\n" + // 情感状态
				"    \"Master\": {\r\n" + "        \"PersonID\": \"" + PersonID + "\",\r\n" + // 居民ID
				"        \"ExamDate\": \"" + ExamDate + "\",\r\n" + // 体检日期
				"        \"Symptom\": \"" + Symptom + "\",\r\n" + // 症状（1 无症状、2 头痛、4 头晕、8 心悸、 16 胸闷、32 胸痛、
				// 64 慢性咳嗽、128 咳痰、256 呼吸困难、512多饮、1024 多尿、
				// 2048 体重下降、4096 乏力、8192 关节肿痛、
				// 16384 视力模糊、32768 手脚麻木、65536 尿急、
				// 131072 尿痛、262144 便秘、524288 腹泻、
				// 1048576 恶心呕吐、2097152 眼花、4194304 耳鸣、
				// 8388608 乳房胀痛、16777216 其他）
				"        \"Assessment\": \"\",\r\n" + // 健康评价（如果存在异常，则保存异常信息，多个异常以\u0001分隔）
				"        \"AssessmentAbnormal\": \"" + AssessmentAbnormal + "\",\r\n" + // 健康评价异常情况
				"        \"Guidance\": \"" + Guidance + "\",\r\n" + // 健康指导（1纳入慢性病患者健康管理、2建议复查、4建议转诊）
				"        \"RiskCrtl\": \"" + RiskCrtl + "\",\r\n" + // 危险因素控制（1 戒烟 2 健康饮酒 4 饮食 8 锻炼 16 减体重 32 建议接种疫苗 64
																	// 其他）
				"        \"RiskCrtlWeight\": \"" + RiskCrtlWeight + "\",\r\n" + // 危险因素控制减重目标KG
				"        \"RiskCrtlVaccine\": \"" + RiskCrtlVaccine + "\",\r\n" + // 危险因素控制建议疫苗
				"        \"RiskCrtlOther\": \"" + RiskCrtlOther + "\",\r\n" + // 危险因素控制其他
				"        \"HealthSummary\": \"\",\r\n" + // 健康摘要
				"        \"DoctorID\": \"" + DoctorID + "\",\r\n" + // 责任医生ID
				"        \"DoctorName\": \"" + DoctorName + "\",\r\n" + // 责任医生
				"        \"UserID\": \"\",\r\n" + // 操作用户ID
				"        \"UserName\": \"\",\r\n" + // 操作用户姓名
				"        \"IsStandard\": \"\",\r\n" + // 是否完善
				"        \"Remark\": \"\" \r\n" + // 备注
				"    },\r\n" + "    \"Body\": {\r\n" + "        \"BodyTemperature\": \"" + BodyTemperature + "\",\r\n" + // 体温
				"        \"PulseRate\": \"" + PulseRate + "\",\r\n" + // 脉率（次/分钟，范围为10-200）
				"        \"HeartRate\": \"" + HeartRate + "\",\r\n" + // 心率（次/分钟,范围为10-200）
				"        \"RespiratoryRate\": \"" + RespiratoryRate + "\",\r\n" + // 呼吸频率（次/分钟,范围为5-99）
				"        \"LeftSbp\": \"" + LeftSbp + "\",\r\n" + // 左侧收缩压
				"        \"LeftDbp\": \"" + LeftDbp + "\",\r\n" + // 左侧舒张压
				"        \"RightSbp\": \"" + RightSbp + "\",\r\n" + // 右侧收缩压
				"        \"RightDbp\": \"" + RightDbp + "\",\r\n" + // 右侧舒张压
				"        \"Height\": \"" + Height + "\",\r\n" + // 身高
				"        \"Weight\": \"" + Weight + "\",\r\n" + // 体重
				"        \"Waistline\": \"" + Waistline + "\",\r\n" + // 腰围
				"        \"Hip\": \"\",\r\n" + // 臀围（CM）
				"        \"Bmi\": \"" + Bmi + "\",\r\n" + // 体质指数
				"        \"Whr\": \"\",\r\n" + // 腰臀围比
				"        \"DorsalisPedisArteryPulse\": \"" + DorsalisPedisArteryPulse + "\",\r\n" + // 足背动脉搏动（1 未触及、
				// 2 触及双侧对称、4 触及左侧弱或消失、 8 触及右侧弱或消失 ）
				"        \"DorsalisPulseResult\": \"\" \r\n" + // 足背动脉搏动侧位
				"    },\r\n" + "    \"Organ\": {\r\n" + "        \"Lips\": \"" + Lips + "\",\r\n" + // 口唇（1 红润、2
																									// 苍白、4发绀、8 皲裂、16
																									// 疱疹）
				"        \"Dentition\": \"" + Dentition + "\",\r\n" + // 齿列（1 正常、2 缺齿、4 龋齿、8 义齿(假牙)）
				"        \"MissingTeeth\": \"" + MissingTeeth + "\",\r\n" + // 齿列缺齿
				"        \"Caries\": \"" + Caries + "\",\r\n" + // 齿列龋齿
				"        \"Denture\": \"" + Denture + "\",\r\n" + // 齿列义齿
				"        \"Throat\": \"" + Throat + "\",\r\n" + // 咽部（1 无充血、2 充血、4 淋巴滤泡增生）
				"        \"LeftEye\": \"" + LeftEye + "\",\r\n" + // 左眼视力
				"        \"RightEye\": \"" + RightEye + "\",\r\n" + // 右眼视力
				"        \"LeftEyeVc\": \"" + LeftEyeVc + "\",\r\n" + // 左眼矫正视力
				"        \"RightEyeVc\": \"" + RightEyeVc + "\",\r\n" + // 右眼矫正视力
				"        \"Hearing\": \"" + Hearing + "\",\r\n" + // 听力（1 听见、2 听不清或无法听见）
				"        \"MotorFunction\": \"" + MotorFunction + "\",\r\n" + // 运动功能（1 可顺利完成、2 无法独立完成任何一个动作）
				"        \"Fundus\": \"" + Fundus + "\",\r\n" + // 眼底（1 正常、2 异常）
				"        \"Skin\": \"" + Skin + "\",\r\n" + // 皮肤（1 正常、2 潮红、4 苍白、8 发绀、16 黄染、32 色素沉着、64 其他）
				"        \"Sclera\": \"" + Sclera + "\",\r\n" + // 巩膜（1 正常、2 黄染、4 充血、8 其他）
				"        \"LymphNodes\": \"" + LymphNodes + "\",\r\n" + // 淋巴结（1 未触及、2 锁骨上、4 腋窝、8 其他
				"        \"BarrelChest\": \"" + BarrelChest + "\",\r\n" + // 肺桶状胸（1 否、2 是）
				"        \"BreathSounds\": \"" + BreathSounds + "\",\r\n" + // 肺呼吸音（1 正常、2 异常）
				"        \"Rale\": \"" + Rale + "\",\r\n" + // 肺罗音（1 无、2 干罗音、4 湿罗音、8 其他）
				"        \"Rhythm\": \"" + Rhythm + "\",\r\n" + // 心脏心律（1 齐、2 不齐、4 绝对不齐）
				"        \"HeartMurmur\": \"" + HeartMurmur + "\",\r\n" + // 心脏杂音（1 无、2 有）
				"        \"AbdominalTenderness\": \"" + AbdominalTenderness + "\",\r\n" + // 腹部压痛（1 无、2 有）
				"        \"AbdominalMass\": \"" + AbdominalMass + "\",\r\n" + // 腹部包块（1 无、2 有）
				"        \"TheAbdomenLiver\": \"" + TheAbdomenLiver + "\",\r\n" + // 腹部肝大（1 无、2 有）
				"        \"Splenomegaly\": \"" + Splenomegaly + "\",\r\n" + // 腹部脾大（1 无、2 有）
				"        \"ShiftingDullness\": \"" + ShiftingDullness + "\",\r\n" + // 移动性浊音（1 无、2 有）
				"        \"LowerExtremityEdema\": \"" + LowerExtremityEdema + "\",\r\n" + // 下肢水肿（1 无、2 单侧、4
																							// 双侧不对称、8双侧对称）
				"        \"Dre\": \"" + Dre + "\",\r\n" + // 肛门指诊（1 未及异常、2 触痛、4 包块、8 前列腺异常 、16 其他）
				"        \"Breast\": \"" + Breast + "\",\r\n" + // 乳腺（1 未见异常、2 乳房切除、4 异常泌乳、8 乳腺包块、16 其他）
				"        \"OrganOther\": \"\"\r\n" + // 脏器功能其他
				"    },\r\n" + "    \"Labor\": {\r\n" + "        \"FastingBloodGlucose\": \"" + FastingBloodGlucose
				+ "\",\r\n" + // 空腹血糖，保存单位mmol/L
				"        \"PostprandialBloodGlucose\": \"\",\r\n" + // 餐后血糖mmol/L
				"        \"RandomBloodGlucose\": \"\",\r\n" + // 随机血糖mmol/L
				"        \"Hemoglobin\": \"" + Hemoglobin + "\",\r\n" + // 血红蛋白g/L
				"        \"Leukocyte\": \"" + Leukocyte + "\",\r\n" + // 白细胞×10^9/L
				"        \"Platelet\": \"" + Platelet + "\",\r\n" + // 血小板×10^9/L
				"        \"OtherBlood\": \"" + OtherBlood + "\",\r\n" + // 血常规其他
				"        \"UrineProtein\": \"" + UrineProtein + "\",\r\n" + // 尿蛋白（选项值分别为：-、+-、+、++、+++、++++）
				"        \"Urine\": \"" + Urine + "\",\r\n" + // 尿糖
				"        \"Ketone\": \"" + Ketone + "\",\r\n" + // 尿酮体
				"        \"OccultBloodInUrine\": \"" + OccultBloodInUrine + "\",\r\n" + // 尿潜血
				"        \"OtherUrine\": \"" + OtherUrine + "\",\r\n" + // 尿常规其他
				"        \"UrinaryAlbumin\": \"" + UrinaryAlbumin + "\",\r\n" + // 尿微量白蛋白
				"        \"FecalOccultBlood\": \"" + FecalOccultBlood + "\",\r\n" + // 大便潜血（1 阴性、2 阳性）
				"        \"SerumAla\": \"" + SerumAla + "\",\r\n" + // 血清谷丙转氨酶U/L
				"        \"SerumAa\": \"" + SerumAa + "\",\r\n" + // 血清谷草转氨酶U/L
				"        \"Albumin\": \"" + Albumin + "\",\r\n" + // 白蛋白g/L
				"        \"TotalBilirubin\": \"" + TotalBilirubin + "\",\r\n" + // 总胆红素μmol/L
				"        \"Bilirubin\": \"" + Bilirubin + "\",\r\n" + // 结合胆红素μmol/L
				"        \"SerumCreatinine\": \"" + SerumCreatinine + "\",\r\n" + // 血清肌酐μmol/L
				"        \"Bun\": \"" + Bun + "\",\r\n" + // 血尿素氮mmol/L
				"        \"PotassiumConcentration\": \"" + PotassiumConcentration + "\",\r\n" + // 血钾浓度mmol/L
				"        \"SodiumConcentration\": \"\",\r\n" + // 血钠浓度mmol/L
				"        \"TotalCholesterol\": \"" + TotalCholesterol + "\",\r\n" + // 总胆固醇mmol/L
				"        \"Triglycerides\": \"" + Triglycerides + "\",\r\n" + // 甘油三酯mmol/L
				"        \"GPT\": \"\",\r\n" + // 肝功GPT
				"        \"LdlCholesterol\": \"" + LdlCholesterol + "\",\r\n" + // 血清低密度脂蛋白胆固醇mmol/L
				"        \"HdlCholesterol\": \"" + HdlCholesterol + "\",\r\n" + // 血清高密度脂蛋白胆固醇mmol/L
				"        \"GlycatedHemoglobin\": \"" + GlycatedHemoglobin + "\",\r\n" + // 糖化血红蛋白%
				"        \"HepatitisBSurfaceAntigen\": \"\",\r\n" + // 乙型肝炎表面抗原（1 阴性、2 阳性）
				"        \"Ecg\": \"" + Ecg + "\",\r\n" + // 心电图（1 正常 、2 异常）如果存在异常，则保存异常信息，值与信息以\u0001分隔;如（2\u0001血丝）
				"        \"ChestXRay\": \"" + ChestXRay + "\",\r\n" + // 胸部X线片（1 正常 、2 异常）
				"        \"BUltrasonicOther\": \"" + BUltrasonicWave + "\",\r\n" + // 腹部B超（1 正常 、2
																					// 异常）如果存在异常，则保存异常信息，值与信息以\u0001分隔;如（2\u0001血丝）
				"        \"BUltrasonicWave\": \"" + BUltrasonicOther + "\",\r\n" + // "B超（1 正常 、2 异常）
																					// 如果存在异常，则保存异常信息，值与信息以\u0001分隔;如（2\u0001血丝）
				"        \"CervicalSmear\": \"\",\r\n" + // 宫颈涂片（1 正常 、2 异常）如果存在异常，则保存异常信息，值与信息以\u0001分隔;如（2\u0001血丝）
				"        \"OtherLaboratory\": \"\",\r\n" + // 辅助检查其他
				"        \"Erythrocytes\": \"" + Erythrocytes + "\",\r\n" + // 红细胞×10^9/L
				"        \"DifferentialCount\": \"" + DifferentialCount + "\",\r\n" + // 白细胞分类计数
				"        \"BloodTransaminase\": \"\",\r\n" + // 血转氨酶
				"        \"Sg\": \"" + Sg + "\",\r\n" + // 尿比重
				"        \"Ph\": \"" + Ph + "\",\r\n" + // 尿酸碱度
				"        \"Ng\": \"\" \r\n" + // 淋球菌
				"    },\r\n" + "    \"Woman\": "// 妇女检查
				+ "{ \"Vulva\": \"" + Vulva + "\",\r\n" + // 外阴（1 未见异常、2 异常）
															// 如果存在异常，则保存异常信息，值与信息以\\u0001分隔;如（2\\u0001不懂1）
				"        \"Vaginal\": \"" + Vaginal + "\",\r\n" + // 阴道（1 未见异常、2 异常）
				"        \"Cervix\": \"" + Cervix + "\",\r\n" + // 宫颈（1 未见异常、2 异常）
				"        \"Palace\": \"" + Palace + "\",\r\n" + // 宫体（1 未见异常、2 异常）
				"        \"UterineAdnexa\": \"" + UterineAdnexa + "\",\r\n" + // 附件（1 未见异常、2 异常）
				"        \"VaginalSecretions\": null,\r\n" + // 阴道分泌物
				"        \"Vdrl\": null,\r\n" + // 梅毒血清学试验
				"        \"VaginalCleanness\": null,\r\n" + // 阴道清洁度
				"        \"Other\": \"" + WOther + "\",\r\n" + // 其他
				"        \"Trichomonas\": null,\r\n" + // 滴虫
				"        \"Albicans\": null},\r\n" + // 念珠菌
				"    \"OePostion\": [],\r\n" + // 毒物种类
				"    \"Vacc\": [],\r\n" + // 体检非免疫规划预防接种史
				"    \"Drug\": [" + Drug + "],\r\n" +
//				"    \"Drug\": [{\r\n" + 
//				"        \"DrugName\": \"999\",\r\n" + //药物名称
//				"        \"Usage\": \"口服\",\r\n" + //用法
//				"        \"Amount\": \"每日三包\",\r\n" + //用量
//				"        \"MedicationTime\": 15,\r\n" + //用药时长
//				"        \"MedicationUnit\": 3,\r\n" + //用药时长单位（1年、2月、3天）
//				"        \"MedicationCompliance\": 1\r\n" + //服药依从性（1规律、2间断、3不服药）
//				"    }],\r\n" + 
				"    \"Hospt\": [" + Hospt + "],\r\n" +
//				"    \"Hospt\": [{\r\n" + 
//				"        \"HistoryType\": 1,\r\n" + //历史类型（1住院史、2病床史）
//				"        \"InDate\": \"2018-01-01\",\r\n" + //入院日期
//				"        \"OutDate\": \"2018-01-10\",\r\n" + //出院日期
//				"        \"Reason\": \"呼吸不畅\",\r\n" + //原因
//				"        \"OrgName\": \"中心医院\",\r\n" + //医疗机构名称
//				"        \"MedicalRecordNumber\": \"0001\"\r\n" + //病案号
//				"    }],\r\n" + 
				"    \"ScaleScore\": {\r\n" + "        \"Health\":\"" + Health + "\",\r\n" + // 健康状态（1 满意、2 基本满意、4说不清楚、8
																								// 不太满意、16 不满意）
				"        \"LifeSkills\": \"" + LifeSkills + "\",\r\n" + // 生活能力选项（1 可自理（0～3分）、2 轻度依赖（4～8分）、4
																		// 中度依赖（9～18分)、8 不能自理（≥19分））
				"        \"LifeSkillsScore\": \"" + LifeSkillsScore + "\",\r\n" + // 生活能力得分
				"        \"CognitiveFunction\": \"" + CognitiveFunction + "\",\r\n" + // 认知功能选项（1 粗筛阴性、2 粗筛阳性，简易智力状态检查）
				"        \"CognitiveFunctionScore\": \"\",\r\n" + // 认知功能得分（如果得分为空，表示认知粗筛阴性）
				"        \"EmotionalState\": \"" + EmotionalState + "\",\r\n" + // 情感状态选项（1 粗筛阴性、2 粗筛阳性，老年人抑郁评分检查）
				"        \"EmotionalStateScore\": \"\" \r\n " + // 情感状态得分（如果情感状态为空，表示粗筛阴性）
				"    },\r\n" + "    \"LifeStyle\": {\r\n" + "        \"ExerciseFrequency\": \"" + ExerciseFrequency
				+ "\",\r\n" + // 锻炼频率（1 每天、2 每周一次以上、4 偶尔、8 不锻炼）
				"        \"EachExerciseTime\": \"" + EachExerciseTime + "\",\r\n" + // 每次锻炼时间（分钟）
				"        \"ExerciseTime\": \"" + ExerciseTime + "\",\r\n" + // 坚持锻炼时间（年）
				"        \"ExerciseMethod\": \"" + ExerciseMethod + "\",\r\n" + // 锻炼方式
				"        \"ExerciseWeekTimes\": \"\",\r\n" + // 每周锻炼次数
				"        \"Diet\": \"" + Diet + "\",\r\n" + // 饮食习惯（1 荤素均衡、2 荤食为主、4 素食为主、8 嗜盐、16 嗜油、32 嗜糖）
				"        \"SmokingStatus\": \"" + SmokingStatus + "\",\r\n" + // 吸烟状况（1 从不吸烟、2 已戒烟、4 吸烟）
				"        \"Smoking\": \"" + Smoking + "\",\r\n" + // 日吸烟量（平均支数）
				"        \"SmokingAge\": \"" + SmokingAge + "\",\r\n" + // 开始吸烟年龄
				"        \"AgeQuit\": \"" + AgeQuit + "\",\r\n" + // 戒烟年龄
				"        \"DrinkingFrequency\": \"" + DrinkingFrequency + "\",\r\n" + // 饮酒频率（1 从不、2 偶尔、4 经常、8 每天）
				"        \"DailyAlcoholIntake\": \"" + DailyAlcoholIntake + "\",\r\n" + // 日饮酒量（平均两数）
				"        \"IsAlcohol\": \"" + IsAlcohol + "\",\r\n" + // 是否戒酒（1 未戒酒、2 已戒酒）
				"        \"AlcoholAge\": \"" + AlcoholAge + "\",\r\n" + // 戒酒年龄
				"        \"AgeStartedDrinking\": \"" + AgeStartedDrinking + "\",\r\n" + // 开始饮酒年龄
				"        \"IsDrunkLastYear\": \"" + IsDrunkLastYear + "\",\r\n" + // 近一年内是否曾醉酒（1 是、2 否）
				"        \"AlcoholType\": \"" + AlcoholType + "\",\r\n" + // 饮酒种类（1 白酒、2 啤酒、4 红酒、8 黄酒、16 其他）
				"        \"IsOe\": \"" + IsOe + "\",\r\n" + // 是否职业暴露（1 无、2 有）
				"        \"Occupation\": \"" + Occupation + "\",\r\n" + // 工种
				"        \"WorkingTime\": \"" + WorkingTime + "\"\r\n" + // 从业时间（年数）
				"    },\r\n" + "    \"Problems\": {\r\n" + "        \"Cerebrovascular\": \"" + Cerebrovascular
				+ "\",\r\n" + // 脑血管疾病（1 未发现、2 缺血性卒中、4 脑出血、8 蛛网膜下腔出血、16 短暂性脑缺血发作、32 其他）
				"        \"Kidney\": \"" + Kidney + "\",\r\n" + // 肾脏疾病（1 未发现、2 糖尿病肾病、4 肾功能衰竭、8 急性肾炎、16 慢性肾炎、32 其他）
				"        \"Heart\": \"" + Heart + "\",\r\n" + // 心脏疾病（1 未发现、2 心肌梗死、4 心绞痛、8 冠状动脉血运重建、16
																// 充血性心力衰竭、32心前区疼痛、64 其他）
				"        \"Vascular\": \"" + Vascular + "\",\r\n" + // 血管疾病（1 未发现、2 夹层动脉瘤、4 动脉闭塞性疾病、8 其他）
				"        \"Eyes\": \"" + Eyes + "\",\r\n" + // 眼部疾病（1 未发现、2 视网膜出血或渗出、4 视乳头水肿、8 白内障、16 其他）
				"        \"Nervoussystems\": \"" + Nervoussystems + "\",\r\n" + // 神经系统疾病（1 未发现、2 有）
				"        \"Others\": \"" + Others + "\"\r\n" + // 其他系统疾病（1 未发现、2 有）
				"    },\r\n" + "    \"ExamHiv\": {},\r\n" + // HIV
				"    \"Other\": [" + Other + "],\r\n" + // 其他
//				"\"Other\": [{\r\n" + 
//				"        \"AttrName\": \"AlcoholType\",\r\n" + 
//				"        \"OtherText\": \"米酒\"\r\n" + 
//				"    }],"
				"    \"ChsCons\": {}" + // 中医辨识（现在没有用了，接口没改之前，还需要像一下这样传）
				"}";
		map = HISUtil.send("56-3", p1, regionCode);
		Object result2 = map.get("Result");
		Map<String, Object> rmap = new HashMap<String, Object>();
		if (result2 != null && "1".equals(result2.toString())) {
			bol = true;
			rmap.put("personid", PersonID);
			rmap.put("mtid", map.get("ID"));
		}
		// 增加中医药随访记录
		if (bol && !StringUtils.isEmpty(A01)) {
			bol = updateTCM(DoctorName, DoctorID, PersonID, ModerateQuality, QualityDeficiency, YangQuality, YinQuality,
					Phlegm, DampHeat, BloodQuality, QiQuality, TeBingQuality, ExamDate, A01, A02, A03, A04, A05, A06,
					A07, A08, A09, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22, A23, A24, A25, A26,
					A27, A28, A29, A30, A31, A32, A33, TcHealthGuides, PRODUCTCODE, regionCode, TcHealthGuide);
		}
		rmap.put("result", bol);
		return rmap;
	}

	/**
	 * 新增或更新中医药随访记录
	 * 
	 * @return
	 * @throws Exception
	 */
	public static boolean updateTCM(String DoctorName, String DoctorID, String Resident, String ModerateQuality,
			String QualityDeficiency, String YangQuality, String YinQuality, String Phlegm, String DampHeat,
			String BloodQuality, String QiQuality, String TeBingQuality, String FollowUpDate, String A01, String A02,
			String A03, String A04, String A05, String A06, String A07, String A08, String A09, String A10, String A11,
			String A12, String A13, String A14, String A15, String A16, String A17, String A18, String A19, String A20,
			String A21, String A22, String A23, String A24, String A25, String A26, String A27, String A28, String A29,
			String A30, String A31, String A32, String A33, String TcHealthGuides, String PRODUCTCODE,
			String regionCode, int THG) throws Exception {
		boolean bol = false;
		String NextFollowUpDate = "";
		if (!StringUtils.isEmpty(FollowUpDate)) {
			String[] s = FollowUpDate.split("-");
			if (s.length == 3) {
				int year = Integer.parseInt(s[0]) + 1;
				NextFollowUpDate = year + "-" + s[1] + "-" + s[2];
			}
		}
		String TcHealthGuide = "";
		if (THG > 0) {
			TcHealthGuide = THG + "";
		}
		Map<String, Object> map = new HashMap<String, Object>();
		String p1 = "{\r\n" + "      \"ID\":\"\",\r\n" + // 随访记录ID，ID为空表示新增，ID不为空表示更新。
				"      \"ProductCode\": \"" + PRODUCTCODE + "\",\r\n" + "      \"OldPeopleTcHealtItem\": {\r\n"
				+ "        \"Resident\": \"" + Resident + "\",\r\n" + // 居民ID
				"        \"TcHealthGuide\": \"" + TcHealthGuide + "\",\r\n" + // 中医保健指导代码（V1.90+弃用）
				"        \"TcHealthGuideOther\": \"\",\r\n" + // 宜稳定情绪，尽量避免烦恼，可选择不同形式的兴趣爱好。宜欣赏曲调悠扬的乐曲，如古筝《高山流水》等。
				"        \"TcHealthGuides\":" + TcHealthGuides + ",\r\n" + // 中医药保健指导代码
				"        \"TcHealthHighScore\":\"\",\r\n" + // 气郁质
				"        \"FollowUpDate\": \"" + FollowUpDate + "\",\r\n" + // 随访时间
				"        \"NextFollowUpDate\": \"" + NextFollowUpDate + "\",\r\n" + // 下次随访时间
				"        \"DoctorName\": \"" + DoctorName + "\",\r\n" + // 责任医生名字
				"        \"DoctorID\": \"" + DoctorID + "\",\r\n" + // 责任医生ID
				"        \"A01\": \"" + A01 + "\",\r\n" + "        \"A02\": \"" + A02 + "\",\r\n"
				+ "        \"A03\": \"" + A03 + "\",\r\n" + "        \"A04\": \"" + A04 + "\",\r\n"
				+ "        \"A05\": \"" + A05 + "\",\r\n" + "        \"A06\": \"" + A06 + "\",\r\n"
				+ "        \"A07\": \"" + A07 + "\",\r\n" + "        \"A08\": \"" + A08 + "\",\r\n"
				+ "        \"A09\": \"" + A09 + "\",\r\n" + "        \"A10\": \"" + A10 + "\",\r\n"
				+ "        \"A11\": \"" + A11 + "\",\r\n" + "        \"A12\": \"" + A12 + "\",\r\n"
				+ "        \"A13\": \"" + A13 + "\",\r\n" + "        \"A14\": \"" + A14 + "\",\r\n"
				+ "        \"A15\": \"" + A15 + "\",\r\n" + "        \"A16\": \"" + A16 + "\",\r\n"
				+ "        \"A17\": \"" + A17 + "\",\r\n" + "        \"A18\": \"" + A18 + "\",\r\n"
				+ "        \"A19\": \"" + A19 + "\",\r\n" + "        \"A20\": \"" + A20 + "\",\r\n"
				+ "        \"A21\": \"" + A21 + "\",\r\n" + "        \"A22\": \"" + A22 + "\",\r\n"
				+ "        \"A23\": \"" + A23 + "\",\r\n" + "        \"A24\": \"" + A24 + "\",\r\n"
				+ "        \"A25\": \"" + A25 + "\",\r\n" + "        \"A26\": \"" + A26 + "\",\r\n"
				+ "        \"A27\": \"" + A27 + "\",\r\n" + "        \"A28\": \"" + A28 + "\",\r\n"
				+ "        \"A29\": \"" + A29 + "\",\r\n" + "        \"A30\": \"" + A30 + "\",\r\n"
				+ "        \"A31\": \"" + A31 + "\",\r\n" + "        \"A32\": \"" + A32 + "\",\r\n"
				+ "        \"A33\": \"" + A33 + "\"" + "      },\r\n" + "      \"ExamChsConstitutionItem\": {\r\n"
				+ "        \"ID\":\"\",\r\n" + "        \"ModerateQuality\": \"" + ModerateQuality + "\",\r\n" + // 平和质（是：1，基本是：2，否则为0）
				"        \"QualityDeficiency\": \"" + QualityDeficiency + "\",\r\n" + // 气虚质（是：1，倾向是：2，否则为0）
				"        \"YangQuality\": \"" + YangQuality + "\",\r\n" + // 阳虚质（是：1，倾向是：2，否则为0）
				"        \"YinQuality\": \"" + YinQuality + "\",\r\n" + // 阴虚质（是：1，倾向是：2，否则为0）
				"        \"Phlegm\": \"" + Phlegm + "\",\r\n" + // 痰湿质（是：1，倾向是：2，否则为0）
				"        \"DampHeat\": \"" + DampHeat + "\",\r\n" + // 湿热质（是：1，倾向是：2，否则为0）
				"        \"BloodQuality\": \"" + BloodQuality + "\",\r\n" + // 血瘀质（是：1，倾向是：2，否则为0）
				"        \"QiQuality\": \"" + QiQuality + "\",\r\n" + // 气郁质（是：1，倾向是：2，否则为0）
				"        \"TeBingQuality\": \"" + TeBingQuality + "\"\r\n" + // 特秉质（是：1，倾向是：2，否则为0）
				"      }\r\n" + "    }";
		map = send("71-4", p1, regionCode);
		Object result = map.get("Result");
		if (result != null && "1".equals(result.toString())) {
			bol = true;
		}
		return bol;
	}

	/**
	 * 获取医生信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getDocters(String ORGID, String PRODUCTCODE, String regionCode)
			throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String p1 = "{\r\n" + "  \"ProductCode\": \"" + PRODUCTCODE + "\",\r\n" + "  \"OrgID\": \"" + ORGID + "\","
				+ "  \"PageSize\": \"100\"," + "  \"PageIndex\": \"0\"\r\n" + "}\r\n";
		map = send("53-3", p1, regionCode);
		Object result = map.get("Result");
		if (result != null && "1".equals(result.toString())) {
			list = (List<Map<String, Object>>) map.get("Msg");
		}
		return list;
	}

	/**
	 * 获取医疗机构信息
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static List<Map<String, Object>> getMedicalIn(String RegionCode, String regionCode) throws Exception {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String, Object> map = new HashMap<String, Object>();
		String p1 = "{\r\n" + "  \"RegionCode\": \"" + RegionCode + "\"" + "}\r\n";
		map = send("30", p1, regionCode);
		Object result = map.get("Result");
		if (result != null && "1".equals(result.toString())) {
			list = (List<Map<String, Object>>) map.get("Msg");
		}
		return list;
	}

	/**
	 * 获取注册产品信息
	 * 
	 * @return
	 * @throws Exception
	 */
	public static void registerProduct(String areaCode, String regionCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String p1 = "{\r\n" + "  \"CompanyCode\": \"" + areaCode + "\",\r\n" + "  \"ProductName\": \"骥世健康APP\",\r\n"
				+ "  \"ProductType\": \"04\",\r\n" + "  \"ExtInfo\":\"40-F0-2F-3D-40-D3\",\r\n"
				+ "  \"UserName\":\"jsjk\",\r\n" + "  \"Password\":\"123\"\r\n" + "}";
		map = send("48-1", p1, regionCode);
		Object result = map.get("Result");
		if (result != null && "1".equals(result.toString())) {
			System.out.println(result.toString());
		}
	}

	/**
	 * 登录
	 * 
	 * @param areaCode
	 * @throws Exception
	 */
	public static void login(String PRODUCTCODE, String regionCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String p1 = "{\r\n" + "    \"ProductCode\": \"" + PRODUCTCODE + "\",\r\n"
				+ "    \"UserName\":\"703051324\",\r\n" + "    \"Password\":\"123123\"\r\n" + "}";
		map = send("01", p1, regionCode);
		Object result = map.get("Result");
		if (result != null && "1".equals(result.toString())) {
			System.out.println(map.toString());
		}
	}

	/**
	 * 批量获取病人信息
	 * 
	 * @param areaCode
	 * @throws Exception
	 */
	public static void getPatients(String PRODUCTCODE, String regionCode) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		String p1 = "{\r\n" + "  \"ProductCode\": \"" + PRODUCTCODE + "\",\r\n" + "  \"RegionCode\": \"511723105\",\r\n"
				+ "  \"AgeStart\": \"开始年龄\"" + "}";
		map = send("55-12", p1, regionCode);
		Object result = map.get("Result");
		if (result != null && "1".equals(result.toString())) {
			System.out.println(map.toString());
		}
	}

	public static void main(String[] args) throws Throwable {
//		Map<String, String> map = new HashMap<String, String>();
//		// 请求消息体
//		map.put("ProductCode", "8CBE1F526BE144419083D25720106D0E");
//		map.put("RegionCode", "511723105");
//		Map<String, Object> m = send("30", JSON.toJSONString(map), "");
//		System.out.println(m);
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("ProductCode", "8CBE1F526BE144419083D25720106D0E");
//		map.put("UserName", "bdzwsylhy");
//		map.put("Password", "123");
//		map = HISUtil.send("01", JSON.toJSONString(map),"0");
//		map = (Map<String, Object>)map.get("Msg");
//		// 1.根据55-11获取居民信息
//		System.out.println(HISUtil.getPersonId("510104198806013991","0"));
		// 如果居民不存在就新增居民信息
//		System.out.println(HISUtil.addPerson("2017-11-23", "李四", "64A50C02DEAC420E88A4CA3C79EEC9C4", "510104198806013991",
//				"1", "1", "2", "4",  "12", "王五", "1988-6-1", "51041110620300", "18000000002",
//				"18000000002", "3", "3", "18", "其他fk", "18", "1", "12",  "4", "4", "4", "3", "3","0"));
		// 2.根据56-3居民id上传数据
//		System.out.println(HISUtil.upPersonData("773E0F1AA28B4B6DB1EFA4984983570B", "2018-01-28", "2",
//				"u0001血清低密度脂蛋白胆固醇(LDL-C)过高 21(mmol/L)\\u0001血清高密度脂蛋白胆固醇(HDL-C)过高 22(mmol/L)", "2", "1", null,
//				"测试狂犬", null, "3E4E129C1DDA48AFA7461B92A341517A", "嘉文", "1", "10", "10", "63", "1", "1", "1", "1",
//				"1", "1", "1", "1", "1", "2", "14", "2148532544", "1075840016", "541069314", "4", "1", "1", "1",
//				"1", "2", "2", "2", "2", "2", "2", "1", "2", "8", "2", "2", "2", "1", "2", "2", "2", "4", "16",
//				"16", null, "1", "1", "1", "1", null, "+", "+", "+", "+", "-", "10", "1", "10", "11", "12", "13",
//				"14", "15", "16", "17", "18", "19", "20", "21", "22", "10", "2\\u0001", "1", "2\\u0001有阴影", null,
//				null, null, null, null, null,
//				"{\"DrugName\": \"999\",\"Usage\": \"口服\",\"Amount\": \"每日三包\",\"MedicationTime\": 15,\"MedicationUnit\": 3,\"MedicationCompliance\": 1}",
//				"{\"HistoryType\": 1,\"InDate\": \"2018-01-01\",\"OutDate\": \"2018-01-10\",\"Reason\": \"呼吸不畅\",\"OrgName\": \"中心医院\",\"MedicalRecordNumber\": \"0001\"}",
//				"4", "30", "3", "跑步", null, "4", "4", "20", "23", "50", "2", "1", "1", "54", "25", "2", "16", "1",
//				"计算机", "30", "32", "32", "64", "8", "16", "2", "2","0"));
		// 查询医疗机构
//		List<Map<String, Object>> l = HISUtil.getMedicalIn("511723105", "0");
//		for (Map<String, Object> m : l) {
//			System.out.println(m.get("ID") + "|" + m.get("医院名称"));
//		}
		// 查询医生信息
//		List<Map<String, Object>> l = HISUtil.getDocters("A4B47DB2CBD5408FBAA7D926E753621B","0","8CBE1F526BE144419083D25720106D0E");
//		for (Map<String, Object> m : l) {
//			System.out.println(m.get("ID") + "|" + m.get("NAME"));
//		}
		// 登录
		// login("0");
		// 获取病人信息
	}
}
