package util;

import java.util.HashMap;
import java.util.Map;

/**
 * 获取医疗机构信息
 * 
 * @author Daniel
 *
 */
public class OrgUtils {

	private static Map<String, Map<String, String>> orgMap = new HashMap<String, Map<String, String>>();

	static {
		/** 开江 */
		// 讲治镇卫生院
		Map<String, String> map = new HashMap<String, String>();
		map.put("ProductCode", "22CF8136DE8A4D67A1A70E1EAF49E62C");
		map.put("ORGID", "51172300000000000000000511723105");
		map.put("wsymc", "讲治镇卫生院");
		orgMap.put("jzzwsy", map);
		// 靖安乡卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "BC4F5400EEBF4999829C7AB37AB71060");
		map.put("ORGID", "51172300000000000000000511723207");
		map.put("wsymc", "靖安乡卫生院");
		orgMap.put("jaxwsy", map);
		// 开江县第二人民医院
		map = new HashMap<String, String>();
		map.put("ProductCode", "6A545721127D4289897FEF29227885F6");
		map.put("ORGID", "51172300000000000000000511723107");
		map.put("wsymc", "开江县第二人民医院");
		orgMap.put("kjxwsy", map);
		// 普安镇卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "743E55791CD9407782F51F9D3FA9DAF9");
		map.put("ORGID", "51172300000000000000000511723101");
		map.put("wsymc", "普安镇卫生院");
		orgMap.put("pazwsy", map);
		// 广福镇卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "7C942CBE6D1F4258A398EA2924BDF3DC");
		map.put("ORGID", "51172300000000000000000511723108");
		map.put("wsymc", "广福镇卫生院");
		orgMap.put("gfzwsy", map);
		// 长岭中心卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "AED15C955FBE494E96CEDE6818BA3800");
		map.put("ORGID", "51172300000000000000000511723109");
		map.put("wsymc", "长岭中心卫生院");
		orgMap.put("clzxwsy", map);
		// 回龙中心卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "1321D9A65AC54A9F93A0F8CA537AD94C");
		map.put("ORGID", "51172300000000000000000511723102");
		map.put("wsymc", "回龙中心卫生院");
		orgMap.put("hlzxwsy", map);
		/** 西充 */
		// 青龙乡卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "E8010B319C8A4A968CCD75DECD8A4AAE");
		map.put("ORGID", "1B3C59F299F342818DBAD741A553F7C8");
		map.put("wsymc", "青龙乡卫生院");
		orgMap.put("qlxwsy", map);
		// 中岭乡卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "7BDBF15EA43B462C9D0B0B640FA4BF41");
		map.put("ORGID", "5DB2DB56F25D4AB99A211F21B5D5DAE8");
		map.put("wsymc", "中岭乡卫生院");
		orgMap.put("zlxwsy", map);
		// 复安乡卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "48370DB7A7C3401AAC0F35960691532C");
		map.put("ORGID", "7FBAC5917A5644E6B03671324775550E");
		map.put("wsymc", "复安乡卫生院");
		orgMap.put("faxwsy", map);
		// 关文镇卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "6961A07B606D4D65AEA2A2850989AF61");
		map.put("ORGID", "A00BCD4079AB443393E4F0D817996BA7");
		map.put("wsymc", "关文镇卫生院");
		orgMap.put("wazwsy", map);
		// 义兴中心卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "45549797C90F47B880AC7D22A013B1AE");
		map.put("ORGID", "0F928E9D714A4E03BD251F69CA8BABA4");
		map.put("wsymc", "义兴中心卫生院");
		orgMap.put("yxzx", map);
		// 西碾乡卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "67D31F94B8E14E97A66D5BA1060935C9");
		map.put("ORGID", "F6E9697C43CF4159865D0E6DB1F5F236");
		map.put("wsymc", "西碾乡卫生院");
		orgMap.put("xnxwsy", map);
		// 仁和镇卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "4097FE5329B743AD86F81E2E1B979506");
		map.put("ORGID", "C2D4BB18C20A45F690A83FBA9AC918D4");
		map.put("wsymc", "仁和镇卫生院");
		orgMap.put("rhzwsy", map);
		// 紫岩乡卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "3E07FC35BA5C4B029EC1BAFEAB9F4F36");
		map.put("ORGID", "AB30CF8D3CAB4CE7BCA2A922A4F7F4C5");
		map.put("wsymc", "紫岩乡卫生院");
		orgMap.put("zyxwsy", map);
		// 古楼镇卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "15E3D176BFC441F79B9B09E6CB966BC3");
		map.put("ORGID", "084717F15A3446218CB369D76124CCB8");
		map.put("wsymc", "古楼镇卫生院");
		orgMap.put("glzwsy", map);
		// 扶君乡卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "82C7C295A26645458037078E6FAD27B8");
		map.put("ORGID", "E52271E9E7A2444BB625B0099627D860");
		map.put("wsymc", "扶君乡卫生院");
		orgMap.put("fjxwsy", map);
		// 金山乡卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "B9D77FA2C7DC4BE6957665E7DA273488");
		map.put("ORGID", "C8B9305EFBDB4A0A8E51C321F52F8428");
		map.put("wsymc", "金山乡卫生院");
		orgMap.put("jsxwsy", map);
		// 晋城中心卫生院
		map = new HashMap<String, String>();
		map.put("ProductCode", "EA37D89CC22240F39347B652ABEFA708");
		map.put("ORGID", "6AEA7820EA324492B4A4B1D2D7D811CE");
		map.put("wsymc", "晋城中心卫生院");
		orgMap.put("jczxwsy", map);
	}

	public static Map<String, String> getWsy(String key) {
		return orgMap.get(key);
	}

	public static void main(String[] args) {
		System.out.println(OrgUtils.getWsy("jzzwsy"));
	}

}
