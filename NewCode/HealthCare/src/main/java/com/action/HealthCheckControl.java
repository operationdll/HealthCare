package com.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.dto.HealthCheckDto;
import com.dto.PatientDto;
import com.dto.SelfCareDto;
import com.dto.TCMDto;
import com.dto.UserDto;
import com.service.HealthCheckService;
import com.service.PatientService;

/**
 * 健康体检
 * 
 * @author Daniel Duan
 * 
 */
@Controller
@RequestMapping(value = "/healthCheck")
public class HealthCheckControl {

	public static Logger log = Logger.getLogger(HealthCheckControl.class);
	@Autowired
	private HealthCheckService healthCheckService;

	@Autowired
	private PatientService patientService;

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public HealthCheckService getHealthCheckService() {
		return healthCheckService;
	}

	public void setHealthCheckService(HealthCheckService healthCheckService) {
		this.healthCheckService = healthCheckService;
	}

	/**
	 * 健康体检页面
	 */
	@RequestMapping(value = "/healthCheck.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String healthCheck(Model model, Integer id) {
		PatientDto patientDto = patientService.getDetail(id);
		model.addAttribute("patient", patientDto);
		return "healthCases/healthCheck/healthCheck";
	}

	/**
	 * 一般情况页面
	 */
	@RequestMapping(value = "/toGenerally.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toGenerally(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/healthCheck/generally";
	}

	/**
	 * 根据病人id获取体检表信息
	 */
	@RequestMapping(value = "/getHealthCheck.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getHealthCheck(HttpServletRequest req, HttpServletResponse response, Model model, Integer pid) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", healthCheckService.getHealthCheck(pid));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("HealthCheckControl->getHealthCheck报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改一般情况
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updGenerally.do", method = { RequestMethod.POST })
	public void updGenerally(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto,
			String field1Str) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCheckDto.setField3(URLDecoder.decode(healthCheckDto.getField3(), "UTF-8"));
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (field1Str != null && !"".equals(field1Str)) {
				healthCheckDto.setField1(format.parse(field1Str));
			}
			int num = healthCheckService.updGenerally(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updGenerally报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 生活方式页面
	 */
	@RequestMapping(value = "/toLifestyle.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toLifestyle(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/healthCheck/lifestyle";
	}

	/**
	 * 修改生活方式
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updLifestyle.do", method = { RequestMethod.POST })
	public void updLifestyle(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCheckDto.setField22(URLDecoder.decode(healthCheckDto.getField22(), "UTF-8"));
			healthCheckDto.setField35(URLDecoder.decode(healthCheckDto.getField35(), "UTF-8"));
			int num = healthCheckService.updLifestyle(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updLifestyle报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 脏器功能页面
	 */
	@RequestMapping(value = "/toOrgan.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toOrgan(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/healthCheck/organ";
	}

	/**
	 * 修改脏器功能
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updOrgan.do", method = { RequestMethod.POST })
	public void updOrgan(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCheckDto.setField51(URLDecoder.decode(healthCheckDto.getField51(), "UTF-8"));
			healthCheckDto.setField52(URLDecoder.decode(healthCheckDto.getField52(), "UTF-8"));
			healthCheckDto.setField53(URLDecoder.decode(healthCheckDto.getField53(), "UTF-8"));
			healthCheckDto.setField54(URLDecoder.decode(healthCheckDto.getField54(), "UTF-8"));
			healthCheckDto.setField55(URLDecoder.decode(healthCheckDto.getField55(), "UTF-8"));
			healthCheckDto.setField56(URLDecoder.decode(healthCheckDto.getField56(), "UTF-8"));
			healthCheckDto.setField57(URLDecoder.decode(healthCheckDto.getField57(), "UTF-8"));
			healthCheckDto.setField58(URLDecoder.decode(healthCheckDto.getField58(), "UTF-8"));
			healthCheckDto.setField59(URLDecoder.decode(healthCheckDto.getField59(), "UTF-8"));
			healthCheckDto.setField60(URLDecoder.decode(healthCheckDto.getField60(), "UTF-8"));
			healthCheckDto.setField61(URLDecoder.decode(healthCheckDto.getField61(), "UTF-8"));
			healthCheckDto.setField62(URLDecoder.decode(healthCheckDto.getField62(), "UTF-8"));
			int num = healthCheckService.updOrgan(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updOrgan报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 查体页面
	 */
	@RequestMapping(value = "/toExam.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toExam(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/healthCheck/exam";
	}

	/**
	 * 修改查体
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updExam.do", method = { RequestMethod.POST })
	public void updExam(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCheckDto.setField94(URLDecoder.decode(healthCheckDto.getField94(), "UTF-8"));
			healthCheckDto.setField131(URLDecoder.decode(healthCheckDto.getField131(), "UTF-8"));
			healthCheckDto.setField220(URLDecoder.decode(healthCheckDto.getField220(), "UTF-8"));
			healthCheckDto.setField221(URLDecoder.decode(healthCheckDto.getField221(), "UTF-8"));
			healthCheckDto.setField256(URLDecoder.decode(healthCheckDto.getField256(), "UTF-8"));
			healthCheckDto.setField257(URLDecoder.decode(healthCheckDto.getField257(), "UTF-8"));
			int num = healthCheckService.updExam(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updExam报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 辅助检查页面
	 */
	@RequestMapping(value = "/toAssistantExam.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String AssistantExam(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/healthCheck/assistantExam";
	}

	/**
	 * 修改辅助检查
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updAssistantExam.do", method = { RequestMethod.POST })
	public void updAssistantExam(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCheckDto.setField100(URLDecoder.decode(healthCheckDto.getField100(), "UTF-8"));
			healthCheckDto.setField105(URLDecoder.decode(healthCheckDto.getField105(), "UTF-8"));
			healthCheckDto.setField128(URLDecoder.decode(healthCheckDto.getField128(), "UTF-8"));
			healthCheckDto.setField133(URLDecoder.decode(healthCheckDto.getField133(), "UTF-8"));
			healthCheckDto.setField136(URLDecoder.decode(healthCheckDto.getField136(), "UTF-8"));
			healthCheckDto.setField245(URLDecoder.decode(healthCheckDto.getField245(), "UTF-8"));
			int num = healthCheckService.updAssistantExam(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updAssistantExam报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 现存健康问题页面
	 */
	@RequestMapping(value = "/toHealthyProblem.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toHealthyProblem(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/healthCheck/healthyProblem";
	}

	/**
	 * 修改现存健康问题
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updHealthyProblem.do", method = { RequestMethod.POST })
	public void updHealthyProblem(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCheckDto.setField138(URLDecoder.decode(healthCheckDto.getField138(), "UTF-8"));
			healthCheckDto.setField140(URLDecoder.decode(healthCheckDto.getField140(), "UTF-8"));
			healthCheckDto.setField142(URLDecoder.decode(healthCheckDto.getField142(), "UTF-8"));
			healthCheckDto.setField269(URLDecoder.decode(healthCheckDto.getField269(), "UTF-8"));
			healthCheckDto.setField144(URLDecoder.decode(healthCheckDto.getField144(), "UTF-8"));
			healthCheckDto.setField146(URLDecoder.decode(healthCheckDto.getField146(), "UTF-8"));
			healthCheckDto.setField148(URLDecoder.decode(healthCheckDto.getField148(), "UTF-8"));
			int num = healthCheckService.updHealthyProblem(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updHealthyProblem报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 住院用药页面
	 */
	@RequestMapping(value = "/toHospitalDrug.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toHospitalDrug(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/healthCheck/hospitalDrug";
	}

	/**
	 * 修改住院用药
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updHospitalDrug.do", method = { RequestMethod.POST })
	public void updHospitalDrug(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto,
			String field149Time, String field150Time, String field151Time, String field152Time) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (field149Time != null && !"".equals(field149Time)) {
				healthCheckDto.setField149(format.parse(field149Time));
			}
			if (field150Time != null && !"".equals(field150Time)) {
				healthCheckDto.setField150(format.parse(field150Time));
			}
			if (field151Time != null && !"".equals(field151Time)) {
				healthCheckDto.setField151(format.parse(field151Time));
			}
			if (field152Time != null && !"".equals(field152Time)) {
				healthCheckDto.setField152(format.parse(field152Time));
			}
			healthCheckDto.setField153(URLDecoder.decode(healthCheckDto.getField153(), "UTF-8"));
			healthCheckDto.setField154(URLDecoder.decode(healthCheckDto.getField154(), "UTF-8"));
			healthCheckDto.setField155(URLDecoder.decode(healthCheckDto.getField155(), "UTF-8"));
			healthCheckDto.setField156(URLDecoder.decode(healthCheckDto.getField156(), "UTF-8"));
			healthCheckDto.setField157(URLDecoder.decode(healthCheckDto.getField157(), "UTF-8"));
			healthCheckDto.setField158(URLDecoder.decode(healthCheckDto.getField158(), "UTF-8"));
			healthCheckDto.setField171(URLDecoder.decode(healthCheckDto.getField171(), "UTF-8"));
			healthCheckDto.setField176(URLDecoder.decode(healthCheckDto.getField176(), "UTF-8"));
			healthCheckDto.setField181(URLDecoder.decode(healthCheckDto.getField181(), "UTF-8"));
			healthCheckDto.setField186(URLDecoder.decode(healthCheckDto.getField186(), "UTF-8"));
			healthCheckDto.setField169(URLDecoder.decode(healthCheckDto.getField169(), "UTF-8"));
			healthCheckDto.setField174(URLDecoder.decode(healthCheckDto.getField174(), "UTF-8"));
			healthCheckDto.setField179(URLDecoder.decode(healthCheckDto.getField179(), "UTF-8"));
			healthCheckDto.setField184(URLDecoder.decode(healthCheckDto.getField184(), "UTF-8"));
			int num = healthCheckService.updHospitalDrug(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updHospitalDrug报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 健康评价页面
	 */
	@RequestMapping(value = "/toHealthyComment.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toHealthyComment(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/healthCheck/healthyComment";
	}

	/**
	 * 判断字符串是否为浮点数（double和float）
	 * 
	 * @param str
	 * @return
	 */
	private boolean isDouble(String str) {
		if (null == str || "".equals(str)) {
			return false;
		}
		Pattern pattern = Pattern.compile("^[.\\d]*$");
		return pattern.matcher(str).matches();
	}

	/**
	 * 自动生成多个结果
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/getAllResult.do", method = { RequestMethod.GET })
	public void getAllResult(HttpServletRequest req, HttpServletResponse response, Model model, String code,
			String pname, String idcard, String startDate, String endDate, Boolean isNum) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", code);
			params.put("pname", URLDecoder.decode(pname, "UTF-8"));
			params.put("idcard", idcard);
			params.put("startDate", startDate + " 00:00:00");
			params.put("endDate", endDate + " 23:59:59");
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			if (userDto != null) {
				params.put("hcid", userDto.getHcid());
			} else {
				params.put("hcid", "-1");
			}
			List<PatientDto> patientDtos = patientService.getList(params);
			int num = 0;
			for (PatientDto p : patientDtos) {
				HealthCheckDto healthCheckDto = healthCheckService.getHealthCheck(p.getId());
				healthCheckDto = getResult(healthCheckDto, p, isNum);
				int num1 = healthCheckService.updHealthyResult(healthCheckDto);
				num = num + num1;
			}
			if (num > 0) {
				String rstr = "{code:0}";
				if (num != patientDtos.size()) {
					rstr = "{code:" + num + "}";
				}
				out.print(JSON.parse(rstr));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->getAllResult报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 自动生成结果
	 */
	@RequestMapping(value = "/getResult.do", method = { RequestMethod.POST })
	public void getResult(HttpServletRequest req, HttpServletResponse response, Integer pid) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			HealthCheckDto healthCheckDto = healthCheckService.getHealthCheck(pid);
			PatientDto patientDto = patientService.getDetail(pid);
			healthCheckDto = getResult(healthCheckDto, patientDto, false);
			int num = healthCheckService.updHealthyResult(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->getResult报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 自动算出健康评价
	 * 
	 * @param healthCheckDto
	 * @return
	 */
	private HealthCheckDto getResult(HealthCheckDto healthCheckDto, PatientDto patientDto, Boolean isNum) {
		boolean isAbnormal = false;
		String abnormalStr = "";
		// 判断高血压
		boolean bol = false;
		String field7Str = healthCheckDto.getField7();
		if (isDouble(field7Str)) {
			Integer field7 = Integer.parseInt(field7Str);
			if (field7 >= 140) {
				field7Str = field7 + "";
				isAbnormal = true;
				bol = true;
			}
		}
		String field8Str = healthCheckDto.getField8();
		if (isDouble(field8Str)) {
			Integer field8 = Integer.parseInt(field8Str);
			if (field8 >= 90) {
				field8Str = field8 + "";
				isAbnormal = true;
				bol = true;
			}
		}
		if (bol) {
			if (isNum) {
				abnormalStr = abnormalStr + "血压偏高左侧" + field7Str + "   " + field8Str + "，";
			} else {
				abnormalStr = abnormalStr + "血压偏高，";
			}
		}
		if (!bol) {
			String field9Str = healthCheckDto.getField9();
			if (isDouble(field9Str)) {
				Integer field9 = Integer.parseInt(field9Str);
				if (field9 >= 140) {
					field9Str = field9 + "";
					isAbnormal = true;
					bol = true;
				}
			}
			String field10Str = healthCheckDto.getField10();
			if (isDouble(field10Str)) {
				Integer field10 = Integer.parseInt(field10Str);
				if (field10 >= 90) {
					field10Str = field10 + "";
					isAbnormal = true;
					bol = true;
				}
			}
			if (bol) {
				if (isNum) {
					abnormalStr = abnormalStr + "血压偏高右侧" + field9Str + "   " + field10Str + "，";
				} else {
					abnormalStr = abnormalStr + "血压偏高，";
				}
			}
		}
		String field219Str = healthCheckDto.getField219();
		if (isDouble(field219Str)) {
			Double field219 = Double.valueOf(field219Str);
			if (field219 < 18.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "BMI偏低" + field219 + "，";
				} else {
					abnormalStr = abnormalStr + "BMI偏低，";
				}
			}
			if (field219 > 28) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "BMI偏高" + field219 + "，";
				} else {
					abnormalStr = abnormalStr + "BMI偏高，";
				}
			}
		}
		String field220Str = healthCheckDto.getField220();
		if (isDouble(field220Str)) {
			Integer field220 = Integer.parseInt(field220Str);
			if (field220 > 140) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "收缩压偏高" + field220 + "，";
				} else {
					abnormalStr = abnormalStr + "收缩压偏高，";
				}
			}
		}
		String field221Str = healthCheckDto.getField221();
		if (isDouble(field221Str)) {
			Integer field221 = Integer.parseInt(field221Str);
			if (field221 > 90) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "舒张压偏高" + field221 + "，";
				} else {
					abnormalStr = abnormalStr + "舒张压偏高，";
				}
			}
		}
		String field97Str = healthCheckDto.getField97();
		if (isDouble(field97Str)) {
			Double field97 = Double.valueOf(field97Str);
			if (field97 < 110) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血红蛋白偏低" + field97 + "，";
				} else {
					abnormalStr = abnormalStr + "血红蛋白偏低，";
				}
			}
			if (field97 > 160) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血红蛋白偏高" + field97 + "，";
				} else {
					abnormalStr = abnormalStr + "血红蛋白偏高，";
				}
			}
		}
		String field98Str = healthCheckDto.getField98();
		if (isDouble(field98Str)) {
			Double field98 = Double.valueOf(field98Str);
			if (field98 < 4) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "白细胞计数偏低" + field98 + "，";
				} else {
					abnormalStr = abnormalStr + "白细胞计数偏低，";
				}
			}
			if (field98 > 10) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "白细胞计数偏高" + field98 + "，";
				} else {
					abnormalStr = abnormalStr + "白细胞计数偏高，";
				}
			}
		}
		String field222Str = healthCheckDto.getField222();
		if (isDouble(field222Str)) {
			Double field222 = Double.valueOf(field222Str);
			if (field222 < 3.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "红细胞计数偏低" + field222 + "，";
				} else {
					abnormalStr = abnormalStr + "红细胞计数偏低，";
				}
			}
			if (field222 > 5.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "红细胞计数偏高" + field222 + "，";
				} else {
					abnormalStr = abnormalStr + "红细胞计数偏高，";
				}
			}
		}
		String field223Str = healthCheckDto.getField223();
		if (isDouble(field223Str)) {
			Double field223 = Double.valueOf(field223Str);
			if (field223 < 35) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "红细胞压积偏低" + field223 + "，";
				} else {
					abnormalStr = abnormalStr + "红细胞压积偏低，";
				}
			}
			if (field223 > 50) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "红细胞压积偏高" + field223 + "，";
				} else {
					abnormalStr = abnormalStr + "红细胞压积偏高，";
				}
			}
		}
		String field224Str = healthCheckDto.getField224();
		if (isDouble(field224Str)) {
			Double field224 = Double.valueOf(field224Str);
			if (field224 < 20) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "淋巴细胞百分比偏低" + field224 + "，";
				} else {
					abnormalStr = abnormalStr + "淋巴细胞百分比偏低，";
				}
			}
			if (field224 > 40) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "淋巴细胞百分比偏高" + field224 + "，";
				} else {
					abnormalStr = abnormalStr + "淋巴细胞百分比偏高，";
				}
			}
		}
		String field225Str = healthCheckDto.getField225();
		if (isDouble(field225Str)) {
			Double field225 = Double.valueOf(field225Str);
			if (field225 < 1) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "中间细胞百分比偏低" + field225 + "，";
				} else {
					abnormalStr = abnormalStr + "中间细胞百分比偏低，";
				}
			}
			if (field225 > 15) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "中间细胞百分比偏高" + field225 + "，";
				} else {
					abnormalStr = abnormalStr + "中间细胞百分比偏高，";
				}
			}
		}
		String field226Str = healthCheckDto.getField226();
		if (isDouble(field226Str)) {
			Double field226 = Double.valueOf(field226Str);
			if (field226 < 1) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "中性粒细胞百分比偏低" + field226 + "，";
				} else {
					abnormalStr = abnormalStr + "中性粒细胞百分比偏低，";
				}
			}
			if (field226 > 15) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "中性粒细胞百分比偏高" + field226 + "，";
				} else {
					abnormalStr = abnormalStr + "中性粒细胞百分比偏高，";
				}
			}
		}
		String field227Str = healthCheckDto.getField227();
		if (isDouble(field227Str)) {
			Double field227 = Double.valueOf(field227Str);
			if (field227 < 320) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "平均红细胞血红蛋白浓度偏低" + field227 + "，";
				} else {
					abnormalStr = abnormalStr + "平均红细胞血红蛋白浓度偏低，";
				}
			}
			if (field227 > 360) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "平均红细胞血红蛋白浓度偏高" + field227 + "，";
				} else {
					abnormalStr = abnormalStr + "平均红细胞血红蛋白浓度偏高，";
				}
			}
		}
		String field228Str = healthCheckDto.getField228();
		if (isDouble(field228Str)) {
			Double field228 = Double.valueOf(field228Str);
			if (field228 < 82) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "平均红细胞体积偏低" + field228 + "，";
				} else {
					abnormalStr = abnormalStr + "平均红细胞体积偏低，";
				}
			}
			if (field228 > 99) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "平均红细胞体积偏高" + field228 + "，";
				} else {
					abnormalStr = abnormalStr + "平均红细胞体积偏高，";
				}
			}
		}
		String field229Str = healthCheckDto.getField229();
		if (isDouble(field229Str)) {
			Double field229 = Double.valueOf(field229Str);
			if (field229 < 27) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "平均红细胞血红蛋白含量偏低" + field229 + "，";
				} else {
					abnormalStr = abnormalStr + "平均红细胞血红蛋白含量偏低，";
				}
			}
			if (field229 > 31) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "平均红细胞血红蛋白含量偏高" + field229 + "，";
				} else {
					abnormalStr = abnormalStr + "平均红细胞血红蛋白含量偏高，";
				}
			}
		}
		String field243Str = healthCheckDto.getField243();
		if (isDouble(field243Str)) {
			Double field243 = Double.valueOf(field243Str);
			if (field243 < 33) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "红细胞分布宽度标准差偏低" + field243 + "，";
				} else {
					abnormalStr = abnormalStr + "红细胞分布宽度标准差偏低，";
				}
			}
			if (field243 > 50) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "红细胞分布宽度标准差偏高" + field243 + "，";
				} else {
					abnormalStr = abnormalStr + "红细胞分布宽度标准差偏高，";
				}
			}
		}
		String field230Str = healthCheckDto.getField230();
		if (isDouble(field230Str)) {
			Double field230 = Double.valueOf(field230Str);
			if (field230 < 10) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血小板分布宽度偏低" + field230 + "，";
				} else {
					abnormalStr = abnormalStr + "血小板分布宽度偏低，";
				}
			}
			if (field230 > 18) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血小板分布宽度偏高" + field230 + "，";
				} else {
					abnormalStr = abnormalStr + "血小板分布宽度偏高，";
				}
			}
		}
		String field99Str = healthCheckDto.getField99();
		if (isDouble(field99Str)) {
			Double field99 = Double.valueOf(field99Str);
			if (field99 < 100) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血小板计数偏低" + field99 + "，";
				} else {
					abnormalStr = abnormalStr + "血小板计数偏低，";
				}
			}
			if (field99 > 315) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血小板计数偏高" + field99 + "，";
				} else {
					abnormalStr = abnormalStr + "血小板计数偏高，";
				}
			}
		}
		String field231Str = healthCheckDto.getField231();
		if (isDouble(field231Str)) {
			Double field231 = Double.valueOf(field231Str);
			if (field231 < 11.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "红细胞分布宽度变异系数偏低" + field231 + "，";
				} else {
					abnormalStr = abnormalStr + "红细胞分布宽度变异系数偏低，";
				}
			}
			if (field231 > 17.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "红细胞分布宽度变异系数偏高" + field231 + "，";
				} else {
					abnormalStr = abnormalStr + "红细胞分布宽度变异系数偏高，";
				}
			}
		}
		String field232Str = healthCheckDto.getField232();
		if (isDouble(field232Str)) {
			Double field232 = Double.valueOf(field232Str);
			if (field232 < 6.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "平均血小板体积偏低" + field232 + "，";
				} else {
					abnormalStr = abnormalStr + "平均血小板体积偏低，";
				}
			}
			if (field232 > 12.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "平均血小板体积偏高" + field232 + "，";
				} else {
					abnormalStr = abnormalStr + "平均血小板体积偏高，";
				}
			}
		}
		String field233Str = healthCheckDto.getField233();
		if (isDouble(field233Str)) {
			Double field233 = Double.valueOf(field233Str);
			if (field233 < 0.1) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血小板压积偏低" + field233 + "，";
				} else {
					abnormalStr = abnormalStr + "血小板压积偏低，";
				}
			}
			if (field233 > 0.3) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血小板压积偏高" + field233 + "，";
				} else {
					abnormalStr = abnormalStr + "血小板压积偏高，";
				}
			}
		}
		String field234Str = healthCheckDto.getField234();
		if (isDouble(field234Str)) {
			Double field234 = Double.valueOf(field234Str);
			if (field234 < 0.1) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "淋巴细胞绝对值偏低" + field234 + "，";
				} else {
					abnormalStr = abnormalStr + "淋巴细胞绝对值偏低，";
				}
			}
			if (field234 > 0.3) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "淋巴细胞绝对值偏高" + field234 + "，";
				} else {
					abnormalStr = abnormalStr + "淋巴细胞绝对值偏高，";
				}
			}
		}
		String field235Str = healthCheckDto.getField235();
		if (isDouble(field235Str)) {
			Double field235 = Double.valueOf(field235Str);
			if (field235 < 0.1) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "中间细胞绝对值偏低" + field235 + "，";
				} else {
					abnormalStr = abnormalStr + "中间细胞绝对值偏低，";
				}
			}
			if (field235 > 1.8) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "中间细胞绝对值偏高" + field235 + "，";
				} else {
					abnormalStr = abnormalStr + "中间细胞绝对值偏高，";
				}
			}
		}
		String field236Str = healthCheckDto.getField236();
		if (isDouble(field236Str)) {
			Double field236 = Double.valueOf(field236Str);
			if (field236 < 2) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "中性粒细胞绝对值偏低" + field236 + "，";
				} else {
					abnormalStr = abnormalStr + "中性粒细胞绝对值偏低，";
				}
			}
			if (field236 > 7) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "中性粒细胞绝对值偏高" + field236 + "，";
				} else {
					abnormalStr = abnormalStr + "中性粒细胞绝对值偏高，";
				}
			}
		}
		String field101Str = healthCheckDto.getField101();
		if (field101Str != null && !"".equals(field101Str)) {
			if (isDouble(field101Str)) {
				Double field101 = Double.valueOf(field101Str);
				if (field101 < 2) {
					isAbnormal = true;
					if (isNum) {
						abnormalStr = abnormalStr + "尿蛋白偏低" + field101 + "，";
					} else {
						abnormalStr = abnormalStr + "尿蛋白偏低，";
					}
				}
				if (field101 > 7) {
					isAbnormal = true;
					if (isNum) {
						abnormalStr = abnormalStr + "尿蛋白偏高" + field101 + "，";
					} else {
						abnormalStr = abnormalStr + "尿蛋白偏高，";
					}
				}
			} else if (!"-".equals(field101Str)) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "尿蛋白" + field101Str + "，";
			}
		}
		String field102 = healthCheckDto.getField102();
		if (field102 != null && !"".equals(field102)) {
			if (!"-".equals(field102)) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "尿糖" + field102 + "，";
			}
		}
		String field237 = healthCheckDto.getField237();
		if (field237 != null && !"".equals(field237)) {
			if (!"-".equals(field237)) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "尿胆红素" + field237 + "，";
			}
		}
		String field238 = healthCheckDto.getField238();
		if (field238 != null && !"".equals(field238)) {
			if (!"-".equals(field238)) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "尿胆原" + field238 + "，";
			}
		}
		String field104 = healthCheckDto.getField104();
		if (field104 != null && !"".equals(field104)) {
			if (!"-".equals(field104)) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "尿潜血" + field104 + "，";
			}
		}
		String field103 = healthCheckDto.getField103();
		if (field103 != null && !"".equals(field103)) {
			if (!"-".equals(field103)) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "尿酮体" + field103 + "，";
			}
		}
		String field239 = healthCheckDto.getField239();
		if (field239 != null && !"".equals(field239)) {
			if (!"-".equals(field239)) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "亚硝酸盐" + field239 + "，";
			}
		}
		String field240 = healthCheckDto.getField240();
		if (field240 != null && !"".equals(field240)) {
			if (!"-".equals(field240)) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "尿白细胞" + field240 + "，";
			}
		}
		String field241Str = healthCheckDto.getField241();
		if (isDouble(field241Str)) {
			Double field241 = Double.valueOf(field241Str);
			if (field241 < 1) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "尿比重偏低" + field241 + "，";
				} else {
					abnormalStr = abnormalStr + "尿比重偏低，";
				}
			}
			if (field241 > 1.03) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "尿比重偏高" + field241 + "，";
				} else {
					abnormalStr = abnormalStr + "尿比重偏高，";
				}
			}
		}
		String field242Str = healthCheckDto.getField242();
		if (isDouble(field242Str)) {
			Double field242 = Double.valueOf(field242Str);
			if (field242 < 4.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "尿酸碱值偏低" + field242 + "，";
				} else {
					abnormalStr = abnormalStr + "尿酸碱值偏低，";
				}
			}
			if (field242 > 8) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "尿酸碱值偏高" + field242 + "，";
				} else {
					abnormalStr = abnormalStr + "尿酸碱值偏高，";
				}
			}
		}
		String field117Str = healthCheckDto.getField117();
		if (isDouble(field117Str)) {
			Double field117 = Double.valueOf(field117Str);
			if (field117 < 5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "总胆红素偏低" + field117 + "，";
				} else {
					abnormalStr = abnormalStr + "总胆红素偏低，";
				}
			}
			if (field117 > 28) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "总胆红素偏高" + field117 + "，";
				} else {
					abnormalStr = abnormalStr + "总胆红素偏高，";
				}
			}
		}
		String field115Str = healthCheckDto.getField115();
		if (isDouble(field115Str)) {
			Double field115 = Double.valueOf(field115Str);
			if (field115 < 0) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清谷草转氨酶偏低" + field115 + "，";
				} else {
					abnormalStr = abnormalStr + "血清谷草转氨酶偏低，";
				}
			}
			if (field115 > 40) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清谷草转氨酶偏高" + field115 + "，";
				} else {
					abnormalStr = abnormalStr + "血清谷草转氨酶偏高，";
				}
			}
		}
		String field114Str = healthCheckDto.getField114();
		if (isDouble(field114Str)) {
			Double field114 = Double.valueOf(field114Str);
			if (field114 < 0) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清谷丙转氨酶偏低" + field114 + "，";
				} else {
					abnormalStr = abnormalStr + "血清谷丙转氨酶偏低，";
				}
			}
			if (field114 > 40) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清谷丙转氨酶偏高" + field114 + "，";
				} else {
					abnormalStr = abnormalStr + "血清谷丙转氨酶偏高，";
				}
			}
		}
		String field123Str = healthCheckDto.getField123();
		if (isDouble(field123Str)) {
			Double field123 = Double.valueOf(field123Str);
			if (field123 < 3.6) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "总胆固醇偏低" + field123 + "，";
				} else {
					abnormalStr = abnormalStr + "总胆固醇偏低，";
				}
			}
			if (field123 > 6.5) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "总胆固醇偏高" + field123 + "，";
				} else {
					abnormalStr = abnormalStr + "总胆固醇偏高，";
				}
			}
		}
		String field124Str = healthCheckDto.getField124();
		if (isDouble(field124Str)) {
			Double field124 = Double.valueOf(field124Str);
			if (field124 < 0) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "甘油三酯偏低" + field124 + "，";
				} else {
					abnormalStr = abnormalStr + "甘油三酯偏低，";
				}
			}
			if (field124 > 1.71) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "甘油三酯偏高" + field124 + "，";
				} else {
					abnormalStr = abnormalStr + "甘油三酯偏高，";
				}
			}
		}
		String field119Str = healthCheckDto.getField119();
		if (isDouble(field119Str)) {
			Double field119 = Double.valueOf(field119Str);
			if (field119 < 30) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清肌酐偏低" + field119 + "，";
				} else {
					abnormalStr = abnormalStr + "血清肌酐偏低，";
				}
			}
			if (field119 > 106) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清肌酐偏高" + field119 + "，";
				} else {
					abnormalStr = abnormalStr + "血清肌酐偏高，";
				}
			}
		}
		String field120Str = healthCheckDto.getField120();
		if (isDouble(field120Str)) {
			Double field120 = Double.valueOf(field120Str);
			if (field120 < 1.7) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血尿素氮偏低" + field120 + "，";
				} else {
					abnormalStr = abnormalStr + "血尿素氮偏低，";
				}
			}
			if (field120 > 8.3) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血尿素氮偏高" + field120 + "，";
				} else {
					abnormalStr = abnormalStr + "血尿素氮偏高，";
				}
			}
		}
		String field106Str = healthCheckDto.getField106();
		if (isDouble(field106Str)) {
			Double field106 = Double.valueOf(field106Str);
			if (field106 < 3.89) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "空腹血糖偏低" + field106 + "，";
				} else {
					abnormalStr = abnormalStr + "空腹血糖偏低，";
				}
			}
			if (field106 > 6.11) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "空腹血糖偏高" + field106 + "，";
				} else {
					abnormalStr = abnormalStr + "空腹血糖偏高，";
				}
			}
		}
		String field125Str = healthCheckDto.getField125();
		if (isDouble(field125Str)) {
			Double field125 = Double.valueOf(field125Str);
			if (field125 < 2.07) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清低密度偏低" + field125 + "，";
				} else {
					abnormalStr = abnormalStr + "血清低密度偏低，";
				}
			}
			if (field125 > 3.1) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清低密度偏高" + field125 + "，";
				} else {
					abnormalStr = abnormalStr + "血清低密度偏高，";
				}
			}
		}
		String field126Str = healthCheckDto.getField126();
		if (isDouble(field126Str)) {
			Double field126 = Double.valueOf(field126Str);
			if (field126 < 0.9) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清高密度偏低" + field126 + "，";
				} else {
					abnormalStr = abnormalStr + "血清高密度偏低，";
				}
			}
			if (field126 > 2) {
				isAbnormal = true;
				if (isNum) {
					abnormalStr = abnormalStr + "血清高密度偏高" + field126 + "，";
				} else {
					abnormalStr = abnormalStr + "血清高密度偏高，";
				}
			}
		}
		Integer field75 = healthCheckDto.getField75();
		if (field75 != null && 0 != field75) {
			if (field75 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "肺呼吸音异常，";
			}
		}
		Integer field79 = healthCheckDto.getField79();
		if (field79 != null && 0 != field79) {
			if (field79 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "心脏有杂音，";
			}
		}
		Integer field80 = healthCheckDto.getField80();
		if (field80 != null && 0 != field80) {
			if (field80 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "腹部压痛，";
			}
		}
		Integer field81 = healthCheckDto.getField81();
		if (field81 != null && 0 != field81) {
			if (field81 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "腹部包块，";
			}
		}
		Integer field82 = healthCheckDto.getField82();
		if (field82 != null && 0 != field82) {
			if (field82 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "腹部肝大，";
			}
		}
		Integer field83 = healthCheckDto.getField83();
		if (field83 != null && 0 != field83) {
			if (field83 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "腹部脾大，";
			}
		}
		Integer field84 = healthCheckDto.getField84();
		if (field84 != null && 0 != field84) {
			if (field84 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "腹部移动性浊音，";
			}
		}
		// field87` int(1) COMMENT '肛门指诊1未及异常2触痛3包块4前列腺异常5其他',
		Integer field87 = healthCheckDto.getField87();
		if (field87 != null && 0 != field87) {
			if (field87 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "肛门指诊触痛，";
			} else if (field87 == 3) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "肛门指诊包块，";
			} else if (field87 == 4) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "肛门指诊前列腺异常，";
			}
		}
		// field88` int(1) COMMENT '乳腺1未见异常2乳房切除3异常泌乳4乳腺包块5其他',
		Integer field88 = healthCheckDto.getField88();
		if (field88 != null && 0 != field88) {
			if (field88 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "乳腺乳房切除，";
			} else if (field88 == 3) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "乳腺异常泌乳，";
			} else if (field88 == 4) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "乳腺包块，";
			}
		}
		// 妇科(1)1未见异常2异常
		String field89Str = "";
		Integer field89 = healthCheckDto.getField89();
		if (field89 != null && 0 != field89) {
			if (field89 == 2) {
				isAbnormal = true;
				field89Str = "妇科异常，";
			}
		}
		Integer field90 = healthCheckDto.getField90();
		if (field90 != null && 0 != field90) {
			if (field90 == 2) {
				isAbnormal = true;
				if ("".equals(field89Str)) {
					field89Str = "妇科异常，";
				}
			}
		}
		Integer field91 = healthCheckDto.getField91();
		if (field91 != null && 0 != field91) {
			if (field91 == 2) {
				isAbnormal = true;
				if ("".equals(field89Str)) {
					field89Str = "妇科异常，";
				}
			}
		}
		Integer field92 = healthCheckDto.getField92();
		if (field92 != null && 0 != field92) {
			if (field92 == 2) {
				isAbnormal = true;
				if ("".equals(field89Str)) {
					field89Str = "妇科异常，";
				}
			}
		}
		Integer field93 = healthCheckDto.getField93();
		if (field93 != null && 0 != field93) {
			if (field93 == 2) {
				isAbnormal = true;
				if ("".equals(field89Str)) {
					field89Str = "妇科异常，";
				}
			}
		}
		abnormalStr = abnormalStr + field89Str;
		// `field127` varchar(50) COMMENT
		// '心电图1正常2ST段改变3陈旧性心肌梗塞4窦性心动过速5窦性心动过缓6早搏7房颤8房室传导阻滞9其他',
		String field127 = healthCheckDto.getField127();
		if (field127 != null && !"".equals(field127)) {
			String[] field127List = field127.split(",");
			for (String str : field127List) {
				if ("2".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "ST段改变，";
				} else if ("3".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "陈旧性心肌梗塞，";
				} else if ("4".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "窦性心动过速，";
				} else if ("5".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "窦性心动过缓，";
				} else if ("6".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "早搏，";
				} else if ("7".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "房颤，";
				} else if ("8".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "房室传导阻滞，";
				}
			}
		}
		Integer field129 = healthCheckDto.getField129();
		if (field129 != null && 0 != field129) {
			if (field129 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "胸部X线片异常，";
			}
		}
		Integer field130 = healthCheckDto.getField130();
		if (field130 != null && 0 != field130) {
			if (field130 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "腹部B超异常:" + healthCheckDto.getField245() + "，";
			}
		}
		Integer field132 = healthCheckDto.getField132();
		if (field132 != null && 0 != field132) {
			if (field132 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "腹部B超其他异常:" + healthCheckDto.getField133() + "，";
			}
		}
		Integer field134 = healthCheckDto.getField134();
		if (field134 != null && 0 != field134) {
			if (field134 == 2) {
				isAbnormal = true;
				abnormalStr = abnormalStr + "宫颈涂片异常，";
			}
		}
		// `field137` varchar(50) COMMENT '脑血管疾病1未发现2缺血性卒中3脑出血4蛛网膜下腔出血5短暂性脑缺血6其他',
		String field137 = healthCheckDto.getField137();
		if (field137 != null && !"".equals(field137)) {
			String[] field137List = field137.split(",");
			for (String str : field137List) {
				if ("2".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "缺血性卒中，";
				} else if ("3".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "脑出血，";
				} else if ("4".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "蛛网膜下腔出血，";
				} else if ("5".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "短暂性脑缺血，";
				}
			}
		}
		// field139` varchar(50) COMMENT '肾脏疾病1未发现2糖尿病肾病3肾功能衰竭4急性肾炎5慢性肾炎6其他',
		String field139 = healthCheckDto.getField139();
		if (field139 != null && !"".equals(field139)) {
			String[] field139List = field139.split(",");
			for (String str : field139List) {
				if ("2".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "糖尿病肾病，";
				} else if ("3".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "肾功能衰竭，";
				} else if ("4".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "急性肾炎，";
				} else if ("5".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "慢性肾炎，";
				}
			}
		}
		// field141` varchar(50)心脏疾病1 未发现 2 心肌梗死 3 心绞痛 4 冠状动脉血运重建 5 充血性心力衰竭 6 心前区疼痛 7 其他
		String field141 = healthCheckDto.getField141();
		if (field141 != null && !"".equals(field141)) {
			String[] field141List = field141.split(",");
			for (String str : field141List) {
				if ("2".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "心肌梗死，";
				} else if ("3".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "心绞痛，";
				} else if ("4".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "冠状动脉血运重建，";
				} else if ("5".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "充血性心力衰竭，";
				} else if ("6".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "心前区疼痛，";
				}
			}
		}
		// field268` varchar(50)血管疾病1 未发现 2 夹层动脉瘤 3 动脉闭塞性疾病 4 其他
		String field268 = healthCheckDto.getField268();
		if (!StringUtils.isEmpty(field268)) {
			String[] field268List = field268.split(",");
			for (String str : field268List) {
				if ("2".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "夹层动脉瘤，";
				} else if ("3".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "动脉闭塞性疾病，";
				}
			}
		}
		// field143` varchar(50) COMMENT '眼部疾病1未发现2视网膜出血或渗出3视乳头水肿4白内障5其他',
		String field143 = healthCheckDto.getField143();
		if (field143 != null && !"".equals(field143)) {
			String[] field143List = field143.split(",");
			for (String str : field143List) {
				if ("2".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "视网膜出血或渗出，";
				} else if ("3".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "视乳头水肿，";
				} else if ("4".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "白内障，";
				}
			}
		}
		// field145` varchar(50) COMMENT '神经系统疾病1未发现2阿尔茨海默症(老年痴呆症)3帕金森病4其他',
		String field145 = healthCheckDto.getField145();
		if (field145 != null && !"".equals(field145)) {
			String[] field145List = field145.split(",");
			for (String str : field145List) {
				if ("2".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "阿尔茨海默症(老年痴呆症)，";
				} else if ("3".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "帕金森病，";
				}
			}
		}
		// field147` varchar(50) COMMENT
		// '其他系统疾病1未发现2糖尿病3慢性支气管炎4慢性阻塞性肺气肿5恶性肿瘤6老年性骨关节病7其他',
		String field147 = healthCheckDto.getField147();
		if (field147 != null && !"".equals(field147)) {
			String[] field147List = field147.split(",");
			for (String str : field147List) {
				if ("2".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "糖尿病，";
				} else if ("3".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "慢性支气管炎，";
				} else if ("4".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "慢性阻塞性肺气肿，";
				} else if ("5".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "恶性肿瘤，";
				} else if ("6".equals(str)) {
					isAbnormal = true;
					abnormalStr = abnormalStr + "老年性骨关节病，";
				}
			}
		}
		if (isAbnormal) {
			String[] arrStr = abnormalStr.split("，");
			String problemResult1 = "";
			String problemResult2 = "";
			String problemResult3 = "";
			String problemResult4 = "";
			for (int i = 0; i < arrStr.length; i++) {
				if (i % 4 == 0) {
					problemResult1 = problemResult1 + arrStr[i] + "，";
				} else if (i % 4 == 1) {
					problemResult2 = problemResult2 + arrStr[i] + "，";
				} else if (i % 4 == 2) {
					problemResult3 = problemResult3 + arrStr[i] + "，";
				} else {
					problemResult4 = problemResult4 + arrStr[i] + "，";
				}
			}
			problemResult1 = problemResult1.substring(0, problemResult1.length() - 1);
			if (!"".equals(problemResult2)) {
				problemResult2 = problemResult2.substring(0, problemResult2.length() - 1);
			}
			if (!"".equals(problemResult3)) {
				problemResult3 = problemResult3.substring(0, problemResult3.length() - 1);
			}
			if (!"".equals(problemResult4)) {
				problemResult4 = problemResult4.substring(0, problemResult4.length() - 1);
			}
			healthCheckDto.setField208(2);
			healthCheckDto.setField209(problemResult1);
			healthCheckDto.setField211(problemResult2);
			healthCheckDto.setField212(problemResult3);
			healthCheckDto.setField213(problemResult4);
			healthCheckDto.setField214("3");
			String conDanger = "";
			if (healthCheckDto.getField24() != null && 3 == healthCheckDto.getField24()) {
				conDanger = "1,";
			}
			if (healthCheckDto.getField28() != null && 1 != healthCheckDto.getField28()) {
				conDanger = conDanger + "2,";
			}
			conDanger = conDanger + "3,4,7";
			// 目标体重
			// 标准体重正负10%
			// 性别(1男2女3未说明的性别4未知的性别)
			healthCheckDto.setField216("");
			Integer sex = patientDto.getSex();
			if (sex != null && (sex == 1 || sex == 2)) {
				// 身高
				String field11 = healthCheckDto.getField11();
				// 体重
				String field12 = healthCheckDto.getField12();
				if (!StringUtils.isEmpty(field11) && !StringUtils.isEmpty(field12)) {
					double high = Double.parseDouble(field11);
					double weight = Double.parseDouble(field12);
					double standardW = 0;
					double field216 = 0;
					if (sex == 1) {// 男性:(身高cm-80)*0.7=标准体重
						standardW = (high - 80) * 0.7;
						field216 = standardW;
						standardW = standardW + standardW * 0.1;
					} else if (sex == 2) {// 女性:(身高cm-70)*0.6=标准体重
						standardW = (high - 70) * 0.6;
						field216 = standardW;
						standardW = standardW + standardW * 0.1;
					}
					DecimalFormat df = new DecimalFormat("0.0");
					if (standardW > 0 && (weight - standardW) > 0) {
						healthCheckDto.setField216(df.format(field216) + "kg");
						conDanger = conDanger + ",5";
					}
				}
			}
			healthCheckDto.setField215(conDanger);
			healthCheckDto.setField218("防跌倒");
		} else {
			healthCheckDto.setField208(1);
			healthCheckDto.setField209("");
			healthCheckDto.setField211("");
			healthCheckDto.setField212("");
			healthCheckDto.setField213("");
			healthCheckDto.setField214("");
			healthCheckDto.setField216("");
			healthCheckDto.setField215("");
			healthCheckDto.setField218("");
		}
		return healthCheckDto;
	}

	/**
	 * 修改健康评价
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updHealthyComment.do", method = { RequestMethod.POST })
	public void updHealthyComment(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCheckDto.setField209(URLDecoder.decode(healthCheckDto.getField209(), "UTF-8"));
			healthCheckDto.setField211(URLDecoder.decode(healthCheckDto.getField211(), "UTF-8"));
			healthCheckDto.setField212(URLDecoder.decode(healthCheckDto.getField212(), "UTF-8"));
			healthCheckDto.setField213(URLDecoder.decode(healthCheckDto.getField213(), "UTF-8"));
			healthCheckDto.setField216(URLDecoder.decode(healthCheckDto.getField216(), "UTF-8"));
			healthCheckDto.setField217(URLDecoder.decode(healthCheckDto.getField217(), "UTF-8"));
			healthCheckDto.setField218(URLDecoder.decode(healthCheckDto.getField218(), "UTF-8"));
			int num = healthCheckService.updHealthyComment(healthCheckDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updHealthyComment报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 自理能力页面
	 */
	@RequestMapping(value = "/toSelfcare.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toSelfcare(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		return "healthCases/selfcare";
	}

	/**
	 * 根据病人id获取自理能力信息
	 */
	@RequestMapping(value = "/getSelfcare.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getSelfcare(HttpServletRequest req, HttpServletResponse response, Model model, Integer pid) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", healthCheckService.getSelfcare(pid));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("HealthCheckControl->getSelfcare报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改自理能力
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updSelfcare.do", method = { RequestMethod.POST })
	public void updSelfcare(HttpServletRequest req, HttpServletResponse response, SelfCareDto selfCare,
			String itemField8) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (itemField8 != null && !"".equals(itemField8)) {
				selfCare.setField8(format.parse(itemField8));
			}
			selfCare.setField7(URLDecoder.decode(selfCare.getField7(), "UTF-8"));
			selfCare.setField9(URLDecoder.decode(selfCare.getField9(), "UTF-8"));
			int num = healthCheckService.updSelfcare(selfCare);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updSelfcare报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 中医体质页面
	 */
	@RequestMapping(value = "/toTCM.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toTCM(Model model, Integer id) {
		model.addAttribute("patient", patientService.getDetail(id));
		model.addAttribute("healthCheck", healthCheckService.getHealthCheck(id));
		return "healthCases/TCM";
	}

	/**
	 * 获取中医体质信息
	 */
	@RequestMapping(value = "/getTCM.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getTCM(HttpServletRequest req, HttpServletResponse response, Model model, Integer pid) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", healthCheckService.getTCM(pid));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("HealthCheckControl->getTCM报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改中医体质
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updTCM.do", method = { RequestMethod.POST })
	public void updTCM(HttpServletRequest req, HttpServletResponse response, TCMDto tCMDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			tCMDto.setField43(URLDecoder.decode(tCMDto.getField43(), "UTF-8"));
			tCMDto.setField44(URLDecoder.decode(tCMDto.getField44(), "UTF-8"));
			tCMDto.setField45(URLDecoder.decode(tCMDto.getField45(), "UTF-8"));
			tCMDto.setField46(URLDecoder.decode(tCMDto.getField46(), "UTF-8"));
			tCMDto.setField47(URLDecoder.decode(tCMDto.getField47(), "UTF-8"));
			tCMDto.setField48(URLDecoder.decode(tCMDto.getField48(), "UTF-8"));
			tCMDto.setField49(URLDecoder.decode(tCMDto.getField49(), "UTF-8"));
			tCMDto.setField50(URLDecoder.decode(tCMDto.getField50(), "UTF-8"));
			tCMDto.setField51(URLDecoder.decode(tCMDto.getField51(), "UTF-8"));
			tCMDto.setField52(URLDecoder.decode(tCMDto.getField52(), "UTF-8"));
			int num = healthCheckService.updTCM(tCMDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("HealthCheckControl->updTCM报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 同步脉率心率
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/syndata.do", method = { RequestMethod.GET })
	public void syndata(HttpServletRequest req, HttpServletResponse response, Model model, String code, String pname,
			String idcard, String startDate, String endDate, Integer synnum) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("code", code);
			if (pname != null) {
				pname = URLDecoder.decode(pname, "UTF-8");
			}
			params.put("pname", pname);
			params.put("idcard", idcard);
			params.put("startDate", startDate + " 00:00:00");
			params.put("endDate", endDate + " 23:59:59");
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			params.put("hcid", userDto.getHcid());
			List<PatientDto> patientDtos = patientService.getList(params);
			// 1脉率同步心率2心率同步脉率
			int num = healthCheckService.syndata(patientDtos, synnum);
			out.print(JSON.parse("{code:" + num + "}"));
		} catch (Exception e) {
			log.error("HealthCheckControl->syndata报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

}
