package com.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 中医体质信息
 * 
 * @author Daniel Duan
 * 
 */
public class TCMDto implements Serializable {

	private static final long serialVersionUID = 1L;
	// 主键
	private Integer id;
	private Integer field1;// （1）您精力充沛吗？（指精神头足，乐于做事）:1没有2很少3有时4经常5总是
	private Integer field2;// （2）您容易疲乏吗？（指体力如何，是否稍微活动一下或做一点家务劳动就感到累）:1没有2很少3有时4经常5总是
	private Integer field3;// （3）您容易气短，呼吸短促，接不上气吗？:1没有2很少3有时4经常5总是
	private Integer field4;// （4）您说话声音低弱无力吗？（指说话没有力气）:1没有2很少3有时4经常5总是
	private Integer field5;// （5）您感到闷闷不乐、情绪低沉吗？（指心情不愉快，情绪低落）:1没有2很少3有时4经常5总是
	private Integer field6;// （6）您容易精神紧张、焦虑不安吗？（指遇事是否心情紧张）:1没有2很少3有时4经常5总是
	private Integer field7;// （7）您因为生活状态改变而感到孤独、失落吗？:1没有2很少3有时4经常5总是
	private Integer field8;// （8）您容易感到害怕或受到惊吓吗？:1没有2很少3有时4经常5总是
	private Integer field9;// （9）您感到身体超重不轻松吗？（感觉身体沉重）:1没有2很少3有时4经常5总是
	private Integer field10;// （10）您眼睛干涩吗？:1没有2很少3有时4经常5总是
	private Integer field11;// （11）您手脚发凉吗？（不包含因周围温度低或穿的少导致的手脚发冷）:1没有2很少3有时4经常5总是
	private Integer field12;// （12）您胃脘部、背部或腰膝部怕冷吗？（指上腹部、背部、腰部或膝关节等，有一处或多处怕冷）:1没有2很少3有时4经常5总是
	private Integer field13;// （13）您比一般人耐受不了寒冷吗？（指比别人容易害怕冬天或是夏天的冷空调、电扇等）:1没有2很少3有时4经常5总是
	private Integer field14;// （14）您容易感冒吗？（指每年感冒的次数）:1没有2很少3有时4经常5总是
	private Integer field15;// （15）您没有感冒时也会鼻塞、流鼻涕吗？:1没有2很少3有时4经常5总是
	private Integer field16;// （16）您有口粘口腻，或睡眠打鼾吗？:1没有2很少3有时4经常5总是
	private Integer field17;// （17）您容易过敏（对药物、食物、气味、花粉或在季节交替、气候变化时）吗？:1没有2很少3有时4经常5总是
	private Integer field18;// （18）您的皮肤容易起荨麻疹吗？（包括风团、风疹块、风疙瘩）:1没有2很少3有时4经常5总是
	private Integer field19;// （19）您的皮肤在不知不觉中会出现青紫瘀块、皮下出血吗？（指皮肤在没有外伤的情况下出现青一块紫一块的情况）:1没有2很少3有时4经常5总是
	private Integer field20;// （20）您的皮肤一抓就红，并出现抓痕吗？（指被指甲或钝物划过后皮肤的反应）:1没有2很少3有时4经常5总是
	private Integer field21;// （21）您皮肤或口唇干吗？:1没有2很少3有时4经常5总是
	private Integer field22;// （22）您有肢体麻木或固定部位疼痛的感觉吗？:1没有2很少3有时4经常5总是
	private Integer field23;// （23）您面部或鼻部有油腻感或者油亮发光吗？（指脸上或鼻子）:1没有2很少3有时4经常5总是
	private Integer field24;// （24）您面色或目眶晦暗，或出现褐色斑块/斑点吗？:1没有2很少3有时4经常5总是
	private Integer field25;// （25）您有皮肤湿疹、疮疖吗？:1没有2很少3有时4经常5总是
	private Integer field26;// （26）您感到口干咽燥、总想喝水吗？:1没有2很少3有时4经常5总是
	private Integer field27;// （27）您感到口苦或嘴里有异味吗？(指口苦或口臭):1没有2很少3有时4经常5总是
	private Integer field28;// （28）您腹部肥大吗？（指腹部脂肪肥厚）:1没有2很少3有时4经常5总是
	private Integer field29;// （29）您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗？(指不喜欢吃凉的食物，或吃了凉的食物后会不舒服) :1没有2很少3有时4经常5总是
	private Integer field30;// （30）您有大便粘滞不爽、解不尽的感觉吗？（大便容易粘在马桶或便坑壁上）:1没有2很少3有时4经常5总是
	private Integer field31;// （31）您容易大便干燥吗？:1没有2很少3有时4经常5总是
	private Integer field32;// （32）您舌苔厚腻或有舌苔厚厚的感觉吗？（如果自我感觉不清楚可由调查员观察后填写）:1没有2很少3有时4经常5总是
	private Integer field33;// （33）您舌下静脉瘀紫或增粗吗？(可以调查员辅助观察后填写):1没有2很少3有时4经常5总是
	private String field34;// 气虚质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field35;// 阳虚质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field36;// 阴虚质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field37;// 痰湿质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field38;// 湿热质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field39;// 血瘀质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field40;// 气郁质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field41;// 特禀质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field42;// 平和质:1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
	private String field43;// 气虚质其他
	private String field44;// 阳虚质其他
	private String field45;// 阴虚质其他
	private String field46;// 痰湿质其他
	private String field47;// 湿热质其他
	private String field48;// 血瘀质其他
	private String field49;// 气郁质其他
	private String field50;// 特禀质其他
	private String field51;// 平和质其他
	private String field52;// 随访医生
	private Date createDate;// 创建日期
	private Date updateDate;// 修改日期
	private Integer pid;// 病人
	private String field53;// 气虚质得分
	private String field54;// 阳虚质得分
	private String field55;// 阴虚质得分
	private String field56;// 痰湿质得分
	private String field57;// 湿热质得分
	private String field58;// 血瘀质得分
	private String field59;// 气郁质得分
	private String field60;// 特禀质得分
	private String field61;// 平和质得分

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

	public Integer getField6() {
		return field6;
	}

	public void setField6(Integer field6) {
		this.field6 = field6;
	}

	public Integer getField7() {
		return field7;
	}

	public void setField7(Integer field7) {
		this.field7 = field7;
	}

	public Integer getField8() {
		return field8;
	}

	public void setField8(Integer field8) {
		this.field8 = field8;
	}

	public Integer getField9() {
		return field9;
	}

	public void setField9(Integer field9) {
		this.field9 = field9;
	}

	public Integer getField10() {
		return field10;
	}

	public void setField10(Integer field10) {
		this.field10 = field10;
	}

	public Integer getField11() {
		return field11;
	}

	public void setField11(Integer field11) {
		this.field11 = field11;
	}

	public Integer getField12() {
		return field12;
	}

	public void setField12(Integer field12) {
		this.field12 = field12;
	}

	public Integer getField13() {
		return field13;
	}

	public void setField13(Integer field13) {
		this.field13 = field13;
	}

	public Integer getField14() {
		return field14;
	}

	public void setField14(Integer field14) {
		this.field14 = field14;
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

	public Integer getField20() {
		return field20;
	}

	public void setField20(Integer field20) {
		this.field20 = field20;
	}

	public Integer getField21() {
		return field21;
	}

	public void setField21(Integer field21) {
		this.field21 = field21;
	}

	public Integer getField22() {
		return field22;
	}

	public void setField22(Integer field22) {
		this.field22 = field22;
	}

	public Integer getField23() {
		return field23;
	}

	public void setField23(Integer field23) {
		this.field23 = field23;
	}

	public Integer getField24() {
		return field24;
	}

	public void setField24(Integer field24) {
		this.field24 = field24;
	}

	public Integer getField25() {
		return field25;
	}

	public void setField25(Integer field25) {
		this.field25 = field25;
	}

	public Integer getField26() {
		return field26;
	}

	public void setField26(Integer field26) {
		this.field26 = field26;
	}

	public Integer getField27() {
		return field27;
	}

	public void setField27(Integer field27) {
		this.field27 = field27;
	}

	public Integer getField28() {
		return field28;
	}

	public void setField28(Integer field28) {
		this.field28 = field28;
	}

	public Integer getField29() {
		return field29;
	}

	public void setField29(Integer field29) {
		this.field29 = field29;
	}

	public Integer getField30() {
		return field30;
	}

	public void setField30(Integer field30) {
		this.field30 = field30;
	}

	public Integer getField31() {
		return field31;
	}

	public void setField31(Integer field31) {
		this.field31 = field31;
	}

	public Integer getField32() {
		return field32;
	}

	public void setField32(Integer field32) {
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

}
