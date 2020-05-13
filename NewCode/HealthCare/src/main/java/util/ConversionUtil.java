package util;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

import com.dto.HealthCheckDto;

/**
 * 体检信息结果转化类
 * 
 * @author Administrator
 *
 */
public class ConversionUtil {

	/**
	 * 判断字符串是否为浮点数（double和float）
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isDouble(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[.\\d]*$");
		return pattern.matcher(str).matches();
	}
	
	public static Map<String, String> convert(HealthCheckDto healthCheck) {
		Map<String, String> dataMap = new HashMap<String, String>();
		/** 一般体检 */
		// BMI
		String field219 = healthCheck.getField219();
		if (!StringUtils.isEmpty(field219)) {
			String field219Str = "正常";
			if (isDouble(field219)) {
				if (Double.parseDouble(field219) < 18.5) {
					field219Str = "↓";
				} else if (Double.parseDouble(field219) > 28) {
					field219Str = "↑";
				}
			}
			dataMap.put("field219", field219Str);
		} else {
			dataMap.put("field219", "");
		}
		// 血压右侧1 收缩压
		String field9 = healthCheck.getField9();
		if (!StringUtils.isEmpty(field9)) {
			String field9Str = "正常";
			if (isDouble(field9)) {
				if (Double.parseDouble(field9) > 140) {
					field9Str = "↑";
				}
			}
			dataMap.put("field9", field9Str);
		} else {
			dataMap.put("field9", "");
		}
		// 血压右侧2 舒张压
		String field10 = healthCheck.getField10();
		if (!StringUtils.isEmpty(field10)) {
			String field10Str = "正常";
			if (isDouble(field10)) {
				if (Double.parseDouble(field10) > 90) {
					field10Str = "↑";
				}
			}
			dataMap.put("field10", field10Str);
		} else {
			dataMap.put("field10", "");
		}
		/** 尿常规判断 */
		// 尿蛋白
		String field101 = healthCheck.getField101();
		if ("-".equals(field101)) {
			dataMap.put("field101", "正常");
		} else if (!StringUtils.isEmpty(field101)) {
			dataMap.put("field101", "异常");
		} else {
			dataMap.put("field101", "");
		}
		// 尿糖
		String field102 = healthCheck.getField102();
		if ("-".equals(field102)) {
			dataMap.put("field102", "正常");
		} else if (!StringUtils.isEmpty(field102)) {
			dataMap.put("field102", "异常");
		} else {
			dataMap.put("field102", "");
		}
		// 尿胆红素
		String field237 = healthCheck.getField237();
		if ("-".equals(field237)) {
			dataMap.put("field237", "正常");
		} else if (!StringUtils.isEmpty(field237)) {
			dataMap.put("field237", "异常");
		} else {
			dataMap.put("field237", "");
		}
		// 尿胆原
		String field238 = healthCheck.getField238();
		if ("-".equals(field238)) {
			dataMap.put("field238", "正常");
		} else if (!StringUtils.isEmpty(field238)) {
			dataMap.put("field238", "异常");
		} else {
			dataMap.put("field238", "");
		}
		// 尿潜血
		String field104 = healthCheck.getField104();
		if ("-".equals(field104)) {
			dataMap.put("field104", "正常");
		} else if (!StringUtils.isEmpty(field104)) {
			dataMap.put("field104", "异常");
		} else {
			dataMap.put("field104", "");
		}
		// 尿酮体
		String field103 = healthCheck.getField103();
		if ("-".equals(field103)) {
			dataMap.put("field103", "正常");
		} else if (!StringUtils.isEmpty(field103)) {
			dataMap.put("field103", "异常");
		} else {
			dataMap.put("field103", "");
		}
		// 亚硝酸盐
		String field239 = healthCheck.getField239();
		if ("-".equals(field239)) {
			dataMap.put("field239", "正常");
		} else if (!StringUtils.isEmpty(field239)) {
			dataMap.put("field239", "异常");
		} else {
			dataMap.put("field239", "");
		}
		// 尿白细胞
		String field240 = healthCheck.getField240();
		if ("-".equals(field240)) {
			dataMap.put("field240", "正常");
		} else if (!StringUtils.isEmpty(field240)) {
			dataMap.put("field240", "异常");
		} else {
			dataMap.put("field240", "");
		}
		// 尿比重
		String field241 = healthCheck.getField241();
		if (!StringUtils.isEmpty(field241)) {
			String field241Str = "正常";
			if (isDouble(field241)) {
				if (Double.parseDouble(field241) < 1) {
					field241Str = "↓";
				} else if (Double.parseDouble(field241) > 1.03) {
					field241Str = "↑";
				}
			}
			dataMap.put("field241", field241Str);
		} else {
			dataMap.put("field241", "");
		}
		// 尿酸碱值
		String field242 = healthCheck.getField242();
		if (!StringUtils.isEmpty(field242)) {
			String field242Str = "正常";
			if (isDouble(field242)) {
				if (Double.parseDouble(field242) < 4.5) {
					field242Str = "↓";
				} else if (Double.parseDouble(field242) > 8) {
					field242Str = "↑";
				}
			}
			dataMap.put("field242", field242Str);
		} else {
			dataMap.put("field242", "");
		}
		/** 血常规判断 */
		// 血红蛋白
		String field97 = healthCheck.getField97();
		if (!StringUtils.isEmpty(field97)) {
			String field97Str = "正常";
			if (isDouble(field97)) {
				if (Double.parseDouble(field97) < 110) {
					field97Str = "↓";
				} else if (Double.parseDouble(field97) > 160) {
					field97Str = "↑";
				}
			}
			dataMap.put("field97", field97Str);
		} else {
			dataMap.put("field97", "");
		}
		// 白细胞
		String field98 = healthCheck.getField98();
		if (!StringUtils.isEmpty(field98)) {
			String field98Str = "正常";
			if (isDouble(field98)) {
				if (Double.parseDouble(field98) < 4) {
					field98Str = "↓";
				} else if (Double.parseDouble(field98) > 10) {
					field98Str = "↑";
				}
			}
			dataMap.put("field98", field98Str);
		} else {
			dataMap.put("field98", "");
		}
		// 红细胞计数
		String field222 = healthCheck.getField222();
		if (!StringUtils.isEmpty(field222)) {
			String field222Str = "正常";
			if (isDouble(field222)) {
				if (Double.parseDouble(field222) < 3.5) {
					field222Str = "↓";
				} else if (Double.parseDouble(field222) > 5.5) {
					field222Str = "↑";
				}
			}
			dataMap.put("field222", field222Str);
		} else {
			dataMap.put("field222", "");
		}
		// 红细胞压积
		String field223 = healthCheck.getField223();
		if (!StringUtils.isEmpty(field223)) {
			String field223Str = "正常";
			if (isDouble(field223)) {
				if (Double.parseDouble(field223) < 35) {
					field223Str = "↓";
				} else if (Double.parseDouble(field223) > 50) {
					field223Str = "↑";
				}
			}
			dataMap.put("field223", field223Str);
		} else {
			dataMap.put("field223", "");
		}
		// 淋巴细胞绝对值
		String field234 = healthCheck.getField234();
		if (!StringUtils.isEmpty(field234)) {
			String field234Str = "正常";
			if (isDouble(field234)) {
				if (Double.parseDouble(field234) < 0.8) {
					field234Str = "↓";
				} else if (Double.parseDouble(field234) > 4) {
					field234Str = "↑";
				}
			}
			dataMap.put("field234", field234Str);
		} else {
			dataMap.put("field234", "");
		}
		// 中间细胞绝对值
		String field235 = healthCheck.getField235();
		if (!StringUtils.isEmpty(field235)) {
			String field235Str = "正常";
			if (isDouble(field235)) {
				if (Double.parseDouble(field235) < 0.1) {
					field235Str = "↓";
				} else if (Double.parseDouble(field235) > 1.8) {
					field235Str = "↑";
				}
			}
			dataMap.put("field235", field235Str);
		} else {
			dataMap.put("field235", "");
		}
		// 中性粒细胞绝对值
		String field236 = healthCheck.getField236();
		if (!StringUtils.isEmpty(field236)) {
			String field236Str = "正常";
			if (isDouble(field236)) {
				if (Double.parseDouble(field236) < 2) {
					field236Str = "↓";
				} else if (Double.parseDouble(field236) > 7) {
					field236Str = "↑";
				}
			}
			dataMap.put("field236", field236Str);
		} else {
			dataMap.put("field236", "");
		}
		// 平均红细胞血红蛋白浓度
		String field227 = healthCheck.getField227();
		if (!StringUtils.isEmpty(field227)) {
			String field227Str = "正常";
			if (isDouble(field227)) {
				if (Double.parseDouble(field227) < 320) {
					field227Str = "↓";
				} else if (Double.parseDouble(field227) > 360) {
					field227Str = "↑";
				}
			}
			dataMap.put("field227", field227Str);
		} else {
			dataMap.put("field227", "");
		}
		// 平均红细胞体积
		String field228 = healthCheck.getField228();
		if (!StringUtils.isEmpty(field228)) {
			String field228Str = "正常";
			if (isDouble(field228)) {
				if (Double.parseDouble(field228) < 82) {
					field228Str = "↓";
				} else if (Double.parseDouble(field228) > 99) {
					field228Str = "↑";
				}
			}
			dataMap.put("field228", field228Str);
		} else {
			dataMap.put("field228", "");
		}
		// 平均红细胞血红蛋白含量
		String field229 = healthCheck.getField229();
		if (!StringUtils.isEmpty(field229)) {
			String field229Str = "正常";
			if (isDouble(field229)) {
				if (Double.parseDouble(field229) < 27) {
					field229Str = "↓";
				} else if (Double.parseDouble(field229) > 31) {
					field229Str = "↑";
				}
			}
			dataMap.put("field229", field229Str);
		} else {
			dataMap.put("field229", "");
		}
		// 红细胞分布宽度标准差
		String field243 = healthCheck.getField243();
		if (!StringUtils.isEmpty(field243)) {
			String field243Str = "正常";
			if (isDouble(field243)) {
				if (Double.parseDouble(field243) < 33) {
					field243Str = "↓";
				} else if (Double.parseDouble(field243) > 50) {
					field243Str = "↑";
				}
			}
			dataMap.put("field243", field243Str);
		} else {
			dataMap.put("field243", "");
		}
		// 血小板分布宽度
		String field230 = healthCheck.getField230();
		if (!StringUtils.isEmpty(field230)) {
			String field230Str = "正常";
			if (isDouble(field230)) {
				if (Double.parseDouble(field230) < 10) {
					field230Str = "↓";
				} else if (Double.parseDouble(field230) > 18) {
					field230Str = "↑";
				}
			}
			dataMap.put("field230", field230Str);
		} else {
			dataMap.put("field230", "");
		}
		// 血小板
		String field99 = healthCheck.getField99();
		if (!StringUtils.isEmpty(field99)) {
			String field99Str = "正常";
			if (isDouble(field99)) {
				if (Double.parseDouble(field99) < 100) {
					field99Str = "↓";
				} else if (Double.parseDouble(field99) > 315) {
					field99Str = "↑";
				}
			}
			dataMap.put("field99", field99Str);
		} else {
			dataMap.put("field99", "");
		}
		// 红细胞分布宽度变异系数
		String field231 = healthCheck.getField231();
		if (!StringUtils.isEmpty(field231)) {
			String field231Str = "正常";
			if (isDouble(field231)) {
				if (Double.parseDouble(field231) < 11.5) {
					field231Str = "↓";
				} else if (Double.parseDouble(field231) > 17.5) {
					field231Str = "↑";
				}
			}
			dataMap.put("field231", field231Str);
		} else {
			dataMap.put("field231", "");
		}
		// 平均血小板体积
		String field232 = healthCheck.getField232();
		if (!StringUtils.isEmpty(field232)) {
			String field232Str = "正常";
			if (isDouble(field232)) {
				if (Double.parseDouble(field232) < 6.5) {
					field232Str = "↓";
				} else if (Double.parseDouble(field232) > 12.5) {
					field232Str = "↑";
				}
			}
			dataMap.put("field232", field232Str);
		} else {
			dataMap.put("field232", "");
		}
		// 血小板压积
		String field233 = healthCheck.getField233();
		if (!StringUtils.isEmpty(field233)) {
			String field233Str = "正常";
			if (isDouble(field233)) {
				if (Double.parseDouble(field233) < 0.1) {
					field233Str = "↓";
				} else if (Double.parseDouble(field233) > 0.3) {
					field233Str = "↑";
				}
			}
			dataMap.put("field233", field233Str);
		} else {
			dataMap.put("field233", "");
		}
		// 淋巴细胞百分比
		String field224 = healthCheck.getField224();
		if (!StringUtils.isEmpty(field224)) {
			String field224Str = "正常";
			if (isDouble(field224)) {
				if (Double.parseDouble(field224) < 20) {
					field224Str = "↓";
				} else if (Double.parseDouble(field224) > 40) {
					field224Str = "↑";
				}
			}
			dataMap.put("field224", field224Str);
		} else {
			dataMap.put("field224", "");
		}
		// 中间细胞百分比
		String field225 = healthCheck.getField225();
		if (!StringUtils.isEmpty(field225)) {
			String field225Str = "正常";
			if (isDouble(field225)) {
				if (Double.parseDouble(field225) < 1) {
					field225Str = "↓";
				} else if (Double.parseDouble(field225) > 15) {
					field225Str = "↑";
				}
			}
			dataMap.put("field225", field225Str);
		} else {
			dataMap.put("field225", "");
		}
		// 中性粒细胞百分比
		String field226 = healthCheck.getField226();
		if (!StringUtils.isEmpty(field226)) {
			String field225Str = "正常";
			if (isDouble(field226)) {
				if (Double.parseDouble(field226) < 50) {
					field225Str = "↓";
				} else if (Double.parseDouble(field226) > 70) {
					field225Str = "↑";
				}
			}
			dataMap.put("field226", field225Str);
		} else {
			dataMap.put("field226", "");
		}
		/** 生化判断 */
		// 血清谷丙转氨酶
		String field114 = healthCheck.getField114();
		if (!StringUtils.isEmpty(field114)) {
			String field114Str = "正常";
			if (isDouble(field114)) {
				if (Double.parseDouble(field114) < 0) {
					field114Str = "↓";
				} else if (Double.parseDouble(field114) > 40) {
					field114Str = "↑";
				}
			}
			dataMap.put("field114", field114Str);
		} else {
			dataMap.put("field114", "");
		}
		// 血清谷草转氨酶
		String field115 = healthCheck.getField115();
		if (!StringUtils.isEmpty(field115)) {
			String field115Str = "正常";
			if (isDouble(field115)) {
				if (Double.parseDouble(field115) < 0) {
					field115Str = "↓";
				} else if (Double.parseDouble(field115) > 40) {
					field115Str = "↑";
				}
			}
			dataMap.put("field115", field115Str);
		} else {
			dataMap.put("field115", "");
		}
		// 总胆红素
		String field117 = healthCheck.getField117();
		if (!StringUtils.isEmpty(field117)) {
			String field117Str = "正常";
			if (isDouble(field117)) {
				if (Double.parseDouble(field117) < 5) {
					field117Str = "↓";
				} else if (Double.parseDouble(field117) > 28) {
					field117Str = "↑";
				}
			}
			dataMap.put("field117", field117Str);
		} else {
			dataMap.put("field117", "");
		}
		// 血清肌酐
		String field119 = healthCheck.getField119();
		if (!StringUtils.isEmpty(field119)) {
			String field119Str = "正常";
			if (isDouble(field119)) {
				if (Double.parseDouble(field119) < 30) {
					field119Str = "↓";
				} else if (Double.parseDouble(field119) > 106) {
					field119Str = "↑";
				}
			}
			dataMap.put("field119", field119Str);
		} else {
			dataMap.put("field119", "");
		}
		// 血尿素氮
		String field120 = healthCheck.getField120();
		if (!StringUtils.isEmpty(field120)) {
			String field119Str = "正常";
			if (isDouble(field120)) {
				if (Double.parseDouble(field120) < 1.7) {
					field119Str = "↓";
				} else if (Double.parseDouble(field120) > 8.3) {
					field119Str = "↑";
				}
			}
			dataMap.put("field120", field119Str);
		} else {
			dataMap.put("field120", "");
		}
		// 总胆固醇
		String field123 = healthCheck.getField123();
		if (!StringUtils.isEmpty(field123)) {
			String field123Str = "正常";
			if (isDouble(field123)) {
				if (Double.parseDouble(field123) < 3.6) {
					field123Str = "↓";
				} else if (Double.parseDouble(field123) > 6.5) {
					field123Str = "↑";
				}
			}
			dataMap.put("field123", field123Str);
		} else {
			dataMap.put("field123", "");
		}
		// 甘油三酯
		String field124 = healthCheck.getField124();
		if (!StringUtils.isEmpty(field124)) {
			String field124Str = "正常";
			if (isDouble(field124)) {
				if (Double.parseDouble(field124) < 0) {
					field124Str = "↓";
				} else if (Double.parseDouble(field124) > 1.71) {
					field124Str = "↑";
				}
			}
			dataMap.put("field124", field124Str);
		} else {
			dataMap.put("field124", "");
		}
		// 空腹血糖mmol/L
		String field106 = healthCheck.getField106();
		if (!StringUtils.isEmpty(field106)) {
			String field106Str = "正常";
			if (isDouble(field106)) {
				if (Double.parseDouble(field106) < 3.89) {
					field106Str = "↓";
				} else if (Double.parseDouble(field106) > 6.11) {
					field106Str = "↑";
				}
			}
			dataMap.put("field106", field106Str);
		} else {
			dataMap.put("field106", "");
		}
		// 低密度
		String field125 = healthCheck.getField125();
		if (!StringUtils.isEmpty(field125)) {
			String field125Str = "正常";
			if (isDouble(field125)) {
				if (Double.parseDouble(field125) < 2.07) {
					field125Str = "↓";
				} else if (Double.parseDouble(field125) > 3.1) {
					field125Str = "↑";
				}
			}
			dataMap.put("field125", field125Str);
		} else {
			dataMap.put("field125", "");
		}
		// 高密度
		String field126 = healthCheck.getField126();
		if (!StringUtils.isEmpty(field126)) {
			String field126Str = "正常";
			if (isDouble(field126)) {
				if (Double.parseDouble(field126) < 0.9) {
					field126Str = "↓";
				} else if (Double.parseDouble(field126) > 2) {
					field126Str = "↑";
				}
			}
			dataMap.put("field126", field126Str);
		} else {
			dataMap.put("field126", "");
		}
		return dataMap;
	}

}
