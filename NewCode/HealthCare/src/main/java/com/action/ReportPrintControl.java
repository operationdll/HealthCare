package com.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.dto.DoctorSignDto;
import com.dto.HealthCenterDto;
import com.dto.HealthCheckDto;
import com.dto.PatientDto;
import com.dto.SelfCareDto;
import com.dto.TCMDto;
import com.dto.UserDto;
import com.form.PrintForm;
import com.service.DoctorService;
import com.service.HealthCenterService;
import com.service.HealthCheckService;
import com.service.PatientService;
import com.service.PrintService;

import util.ConversionUtil;
import util.CreatePDFUtil;

/**
 * 报告打印
 * 
 * @author Daniel Duan
 * 
 */
@Controller
@RequestMapping(value = "/print")
public class ReportPrintControl {

	public static Logger log = Logger.getLogger(ReportPrintControl.class);

	@Autowired
	private PrintService printService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private HealthCenterService healthCenterService;

	@Autowired
	private HealthCheckService healthCheckService;

	@Autowired
	private DoctorService doctorService;

	public HealthCenterService getHealthCenterService() {
		return healthCenterService;
	}

	public void setHealthCenterService(HealthCenterService healthCenterService) {
		this.healthCenterService = healthCenterService;
	}

	public HealthCheckService getHealthCheckService() {
		return healthCheckService;
	}

	public void setHealthCheckService(HealthCheckService healthCheckService) {
		this.healthCheckService = healthCheckService;
	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public PrintService getPrintService() {
		return printService;
	}

	public void setPrintService(PrintService printService) {
		this.printService = printService;
	}

	/**
	 * 报告打印列表
	 */
	@RequestMapping(value = "/printList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String printList(Model model) {
		return "print/printList";
	}

	/**
	 * 查询打印信息
	 */
	@RequestMapping(value = "/getPrintList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getPrintList(HttpServletRequest req, HttpServletResponse response, Model model, String idCard,
			String name, String code, String startDate, String endDate, Integer selAge) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idCard", idCard);
			if (name != null) {
				name = URLDecoder.decode(name, "UTF-8");
			}
			params.put("name", name);
			params.put("code", code);
			params.put("startDate", startDate + " 00:00:00");
			params.put("endDate", endDate + " 23:59:59");
			params.put("ageOver", null);
			params.put("ageLow", null);
			Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
			ca.setTime(new Date()); // 设置时间为当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ca.add(Calendar.YEAR, -65); // 年份减1
			Date resultDate = ca.getTime(); // 结果
			if (selAge == 2) {// 65岁以上
				params.put("ageOver", sdf.format(resultDate));
			} else if (selAge == 3) {// 65岁以下
				params.put("ageLow", sdf.format(resultDate));
			}
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			if (userDto != null) {
				params.put("hcid", userDto.getHcid());
			} else {
				params.put("hcid", "-1");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", printService.getPrintList(params));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("ReportPrintControl->getPrintList报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 创建pdf
	 * 
	 * @param model
	 * @param pid
	 * @param req
	 * @param response
	 * @param selReport
	 * @return
	 * @throws Exception
	 */
	private String createPDF(Integer pid, HttpServletRequest req, Integer selReport) throws Exception {
		// 病人信息
		PatientDto patientDto = patientService.getDetail(pid);
		// 体检单位信息
		HealthCenterDto healthCenter = healthCenterService.getHealthById(patientDto.getHcid());
		// 体检表信息
		HealthCheckDto healthCheck = healthCheckService.getHealthCheck(pid);
		// 设置体质指数
		// 体质指数
		if (StringUtils.isEmpty(healthCheck.getField219())) {
			// 身高
			String highStr = healthCheck.getField11();
			// 体重
			String weightStr = healthCheck.getField12();
			String bmi = "";
			if (!StringUtils.isEmpty(highStr) && !StringUtils.isEmpty(weightStr)) {
				Double h = Double.parseDouble(highStr);
				Double w = Double.parseDouble(weightStr);
				DecimalFormat df = new DecimalFormat("0.00");
				h = h / 100;
				h = h * h;
				bmi = df.format(w / h);
			}
			healthCheck.setField219(bmi);
		}
		// 自理能力信息
		SelfCareDto selfCareDto = healthCheckService.getSelfcare(pid);
		// 中医体质信息
		TCMDto tCMDto = healthCheckService.getTCM(pid);

		String code = patientDto.getCode();
		String url = req.getSession().getServletContext().getRealPath("/");
		UUID uuid = UUID.randomUUID();
		String fileName = uuid.toString().replace("-", "") + ".pdf";
		String template = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		Map<String, String> dataMap = new HashMap<String, String>();
		// 获取转换后的值
		Map<String, String> convertMap = ConversionUtil.convert(healthCheck);
		Integer sex = patientDto.getSex();
		String sexStr = "未知的性别";
		if (sex != null) {
			if (sex == 1) {
				sexStr = "男";
			} else if (sex == 2) {
				sexStr = "女";
			} else if (sex == 3) {
				sexStr = "未说明的性别";
			}
		}
		if (selReport == 1) {// 个人基本信息
			template = "personalBasic.pdf";
			dataMap.put("code", patientDto.getCode());
			dataMap.put("name", patientDto.getName());
			Integer psex = patientDto.getSex();
			if (psex != null) {
				if (psex == 3) {
					psex = 9;
				} else if (psex == 4) {
					psex = 0;
				}
				dataMap.put("sex", psex + "");
			}
			Date birthdate = patientDto.getBirthdate();
			if (birthdate != null) {
				dataMap.put("birthdate", format.format(birthdate));
			}
			dataMap.put("idcard", patientDto.getIdcard());
			dataMap.put("company", patientDto.getCompany());
			dataMap.put("phoneno", patientDto.getPhoneno());
			dataMap.put("cname", patientDto.getCname());
			dataMap.put("cphoneno", patientDto.getCphoneno());
			Integer rtype = patientDto.getRtype();
			if (rtype != null) {
				dataMap.put("rtype", rtype + "");
			}
			String nationality = patientDto.getNationality();
			if (!StringUtils.isEmpty(nationality)) {
				if ("汉族".equals(nationality)) {
					dataMap.put("nationality", "01");
				} else {
					dataMap.put("nationality", "99");
				}
			}
			Integer blood = patientDto.getBlood();
			if (blood != null) {
				dataMap.put("blood", blood + "");
			}
			Integer RH = patientDto.getRH();
			if (RH != null) {
				dataMap.put("RH", RH + "");
			}
			Integer elevel = patientDto.getElevel();
			if (elevel != null) {
				dataMap.put("elevel", elevel + "");
			}
			Integer career = patientDto.getCareer();
			if (career != null) {
				career = career - 1;
				dataMap.put("career", career + "");
			}
			Integer marital = patientDto.getMarital();
			if (marital != null) {
				dataMap.put("marital", marital + "");
			}
			dataMap.put("field1", patientDto.getField1());
			dataMap.put("field2", patientDto.getField2());
			dataMap.put("field3", patientDto.getField3());
			String paymentway = patientDto.getPaymentway();
			if (!StringUtils.isEmpty(paymentway)) {
				String[] arr = paymentway.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("paymentway1", num + "");
					} else if (i == 1) {
						dataMap.put("paymentway2", num + "");
					} else if (i == 2) {
						dataMap.put("paymentway3", num + "");
					}
				}
			}
			dataMap.put("field4", patientDto.getField4());

			String allergy = patientDto.getAllergy();
			if (!StringUtils.isEmpty(allergy)) {
				String[] arr = allergy.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("allergy1", num + "");
					} else if (i == 1) {
						dataMap.put("allergy2", num + "");
					} else if (i == 2) {
						dataMap.put("allergy3", num + "");
					} else if (i == 3) {
						dataMap.put("allergy4", num + "");
					}
				}
			}

			String exposure = patientDto.getExposure();
			if (!StringUtils.isEmpty(exposure)) {
				String[] arr = exposure.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("exposure1", num + "");
					} else if (i == 1) {
						dataMap.put("exposure2", num + "");
					} else if (i == 2) {
						dataMap.put("exposure3", num + "");
					}
				}
			}

			String disease = patientDto.getDisease();
			if (!StringUtils.isEmpty(disease)) {
				String[] arr = disease.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("disease1", num + "");
					} else if (i == 1) {
						dataMap.put("disease2", num + "");
					} else if (i == 2) {
						dataMap.put("disease3", num + "");
					} else if (i == 3) {
						dataMap.put("disease4", num + "");
					} else if (i == 4) {
						dataMap.put("disease5", num + "");
					} else if (i == 5) {
						dataMap.put("disease6", num + "");
					}
				}
			}

			dataMap.put("field10", patientDto.getField10());
			Date ctime1 = patientDto.getCtime1();
			if (ctime1 != null) {
				dataMap.put("ctime1", format1.format(ctime1));
			}
			Date ctime2 = patientDto.getCtime2();
			if (ctime2 != null) {
				dataMap.put("ctime2", format1.format(ctime2));
			}
			Date ctime3 = patientDto.getCtime3();
			if (ctime3 != null) {
				dataMap.put("ctime3", format1.format(ctime3));
			}
			Date ctime4 = patientDto.getCtime4();
			if (ctime4 != null) {
				dataMap.put("ctime4", format1.format(ctime4));
			}
			if (patientDto.getSurgery() != null) {
				dataMap.put("surgery", patientDto.getSurgery() + "");
			}
			dataMap.put("sname1", patientDto.getSname1());
			Date stime1 = patientDto.getStime1();
			if (stime1 != null) {
				dataMap.put("stime1", format1.format(stime1));
			}
			dataMap.put("sname2", patientDto.getSname2());
			Date stime2 = patientDto.getStime2();
			if (stime2 != null) {
				dataMap.put("stime2", format1.format(stime2));
			}
			if (patientDto.getTrauma() != null) {
				dataMap.put("trauma", patientDto.getTrauma() + "");
			}
			dataMap.put("tname1", patientDto.getTname1());
			Date ttime1 = patientDto.getTtime1();
			if (ttime1 != null) {
				dataMap.put("ttime1", format1.format(ttime1));
			}
			dataMap.put("tname2", patientDto.getTname2());
			Date ttime2 = patientDto.getTtime2();
			if (ttime2 != null) {
				dataMap.put("ttime2", format1.format(ttime2));
			}
			if (patientDto.getBtran() != null) {
				dataMap.put("btran", patientDto.getBtran() + "");
			}
			dataMap.put("breason1", patientDto.getBreason1());
			Date btime1 = patientDto.getBtime1();
			if (btime1 != null) {
				dataMap.put("btime1", format1.format(btime1));
			}
			dataMap.put("breason2", patientDto.getBreason2());
			Date btime2 = patientDto.getBtime2();
			if (btime2 != null) {
				dataMap.put("btime2", format1.format(btime2));
			}

			String father = patientDto.getFather();
			if (!StringUtils.isEmpty(father)) {
				String[] arr = father.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("father1", num + "");
					} else if (i == 1) {
						dataMap.put("father2", num + "");
					} else if (i == 2) {
						dataMap.put("father3", num + "");
					} else if (i == 3) {
						dataMap.put("father4", num + "");
					} else if (i == 4) {
						dataMap.put("father5", num + "");
					} else if (i == 5) {
						dataMap.put("father6", num + "");
					}
				}
			}

			String mother = patientDto.getMother();
			if (!StringUtils.isEmpty(mother)) {
				String[] arr = mother.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("mother1", num + "");
					} else if (i == 1) {
						dataMap.put("mother2", num + "");
					} else if (i == 2) {
						dataMap.put("mother3", num + "");
					} else if (i == 3) {
						dataMap.put("mother4", num + "");
					} else if (i == 4) {
						dataMap.put("mother5", num + "");
					} else if (i == 5) {
						dataMap.put("mother6", num + "");
					}
				}
			}

			String bsrelative = patientDto.getBsrelative();
			if (!StringUtils.isEmpty(bsrelative)) {
				String[] arr = bsrelative.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("bsrelative1", num + "");
					} else if (i == 1) {
						dataMap.put("bsrelative2", num + "");
					} else if (i == 2) {
						dataMap.put("bsrelative3", num + "");
					} else if (i == 3) {
						dataMap.put("bsrelative4", num + "");
					} else if (i == 4) {
						dataMap.put("bsrelative5", num + "");
					} else if (i == 5) {
						dataMap.put("bsrelative6", num + "");
					}
				}
			}

			String children = patientDto.getChildren();
			if (!StringUtils.isEmpty(children)) {
				String[] arr = children.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("children1", num + "");
					} else if (i == 1) {
						dataMap.put("children2", num + "");
					} else if (i == 2) {
						dataMap.put("children3", num + "");
					} else if (i == 3) {
						dataMap.put("children4", num + "");
					} else if (i == 4) {
						dataMap.put("children5", num + "");
					} else if (i == 5) {
						dataMap.put("children6", num + "");
					}
				}
			}

			if (patientDto.getGenetic() != null) {
				dataMap.put("genetic", patientDto.getGenetic() + "");
			}
			dataMap.put("dname", patientDto.getDname());

			String disability = patientDto.getDisability();
			if (!StringUtils.isEmpty(disability)) {
				String[] arr = disability.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("disability1", num + "");
					} else if (i == 1) {
						dataMap.put("disability2", num + "");
					} else if (i == 2) {
						dataMap.put("disability3", num + "");
					} else if (i == 3) {
						dataMap.put("disability4", num + "");
					} else if (i == 4) {
						dataMap.put("disability5", num + "");
					} else if (i == 5) {
						dataMap.put("disability6", num + "");
					}
				}
			}

			dataMap.put("field5", patientDto.getField5());
			dataMap.put("field6", patientDto.getField6());
			dataMap.put("field7", patientDto.getField7());
			dataMap.put("field8", patientDto.getField8());
			if (patientDto.getField9() != null) {
				dataMap.put("field9", patientDto.getField9() + "");
			}
			if (patientDto.getKfacility() != null) {
				dataMap.put("kfacility", patientDto.getKfacility() + "");
			}
			if (patientDto.getFtype() != null) {
				dataMap.put("ftype", patientDto.getFtype() + "");
			}
			if (patientDto.getDwater() != null) {
				dataMap.put("dwater", patientDto.getDwater() + "");
			}
			if (patientDto.getToilet() != null) {
				dataMap.put("toilet", patientDto.getToilet() + "");
			}
			if (patientDto.getPoultry() != null) {
				dataMap.put("poultry", patientDto.getPoultry() + "");
			}
		} else if (selReport == 2 || selReport == 3) {// 健康档案封面//健康档案封面A4
			template = "healthFileCover.pdf";
			dataMap.put("num1", code.substring(0, 1));
			dataMap.put("num2", code.substring(1, 2));
			dataMap.put("num3", code.substring(2, 3));
			dataMap.put("num4", code.substring(3, 4));
			dataMap.put("num5", code.substring(4, 5));
			dataMap.put("num6", code.substring(5, 6));
			dataMap.put("num7", code.substring(6, 7));
			dataMap.put("num8", code.substring(7, 8));
			dataMap.put("num9", code.substring(8, 9));
			dataMap.put("num10", code.substring(9, 10));
			dataMap.put("num11", code.substring(10, 11));
			dataMap.put("num12", code.substring(11, 12));
			dataMap.put("name", patientDto.getName());
			dataMap.put("address", patientDto.getAddress());
			dataMap.put("address1", patientDto.getAddress1());
			dataMap.put("phoneno", patientDto.getPhoneno());
			dataMap.put("countyname", patientDto.getCountyname());
			dataMap.put("vcname", patientDto.getVcname());
			dataMap.put("fileunit", patientDto.getFileunit());
			dataMap.put("fileuser", patientDto.getFileuser());
			dataMap.put("doctor", patientDto.getDoctor());
		} else if (selReport == 4) {// 健康体检
			template = "healthCheckup.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("code", code);
			dataMap.put("doctor", patientDto.getDoctor());
			dataMap.put("field1", format.format(healthCheck.getField1()));

			String field2 = healthCheck.getField2();
			if (!StringUtils.isEmpty(field2)) {
				String[] arr = field2.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field2_1", num + "");
					} else if (i == 1) {
						dataMap.put("field2_2", num + "");
					} else if (i == 2) {
						dataMap.put("field2_3", num + "");
					} else if (i == 3) {
						dataMap.put("field2_4", num + "");
					} else if (i == 4) {
						dataMap.put("field2_5", num + "");
					} else if (i == 5) {
						dataMap.put("field2_6", num + "");
					} else if (i == 6) {
						dataMap.put("field2_7", num + "");
					} else if (i == 7) {
						dataMap.put("field2_8", num + "");
					} else if (i == 8) {
						dataMap.put("field2_9", num + "");
					} else if (i == 9) {
						dataMap.put("field2_10", num + "");
					}
				}
			}

			dataMap.put("field4", healthCheck.getField4());
			dataMap.put("field5", healthCheck.getField5());
			dataMap.put("field6", healthCheck.getField6());
			dataMap.put("field7", healthCheck.getField7());
			dataMap.put("field8", healthCheck.getField8());
			dataMap.put("field9", healthCheck.getField9());
			dataMap.put("field10", healthCheck.getField10());
			dataMap.put("field11", healthCheck.getField11());
			dataMap.put("field12", healthCheck.getField12());
			dataMap.put("field13", healthCheck.getField13());
			dataMap.put("field219", healthCheck.getField219());
			Integer field15 = healthCheck.getField15();
			if (field15 != null) {
				dataMap.put("field15", field15 + "");
			}
			Integer field16 = healthCheck.getField16();
			if (field16 != null) {
				dataMap.put("field16", field16 + "");
			}
			Integer field17 = healthCheck.getField17();
			if (field17 != null) {
				dataMap.put("field17", field17 + "");
			}
			Integer field18 = healthCheck.getField18();
			if (field18 != null) {
				dataMap.put("field18", field18 + "");
			}
			Integer field19 = healthCheck.getField19();
			if (field19 != null) {
				dataMap.put("field19", field19 + "");
			}
			dataMap.put("field20", healthCheck.getField20());
			dataMap.put("field21", healthCheck.getField21());
			dataMap.put("field22", healthCheck.getField22());

			String field23 = healthCheck.getField23();
			if (!StringUtils.isEmpty(field23)) {
				String[] arr = field23.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field23_1", num + "");
					} else if (i == 1) {
						dataMap.put("field23_2", num + "");
					} else if (i == 2) {
						dataMap.put("field23_3", num + "");
					} else if (i == 3) {
						dataMap.put("field23_4", num + "");
					} else if (i == 4) {
						dataMap.put("field23_5", num + "");
					}
				}
			}

			Integer field24 = healthCheck.getField24();
			if (field24 != null) {
				dataMap.put("field24", field24 + "");
			}
			dataMap.put("field25", healthCheck.getField25());
			dataMap.put("field26", healthCheck.getField26());
			dataMap.put("field27", healthCheck.getField27());
			Integer field28 = healthCheck.getField28();
			if (field28 != null) {
				dataMap.put("field28", field28 + "");
			}
			dataMap.put("field29", healthCheck.getField29());
			Integer field30 = healthCheck.getField30();
			if (field30 != null) {
				dataMap.put("field30", field30 + "");
			}
			dataMap.put("field31", healthCheck.getField31());
			dataMap.put("field32", healthCheck.getField32());
			Integer field33 = healthCheck.getField33();
			if (field33 != null) {
				dataMap.put("field33", field33 + "");
			}

			String field34 = healthCheck.getField34();
			if (!StringUtils.isEmpty(field34)) {
				String[] arr = field34.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field34_1", num + "");
					} else if (i == 1) {
						dataMap.put("field34_2", num + "");
					} else if (i == 2) {
						dataMap.put("field34_3", num + "");
					} else if (i == 3) {
						dataMap.put("field34_4", num + "");
					}
				}
			}
			dataMap.put("field35", healthCheck.getField35());
			Integer field36 = healthCheck.getField36();
			if (field36 != null) {
				dataMap.put("field36", field36 + "");
			}
			dataMap.put("field37", healthCheck.getField37());
			dataMap.put("field38", healthCheck.getField38());
			dataMap.put("field39", healthCheck.getField39());
			Integer field40 = healthCheck.getField40();
			if (field40 != null) {
				dataMap.put("field40", field40 + "");
			}
			dataMap.put("field41", healthCheck.getField41());
			Integer field42 = healthCheck.getField42();
			if (field42 != null) {
				dataMap.put("field42", field42 + "");
			}
			dataMap.put("field43", healthCheck.getField43());
			Integer field44 = healthCheck.getField44();
			if (field44 != null) {
				dataMap.put("field44", field44 + "");
			}
			dataMap.put("field45", healthCheck.getField45());
			Integer field46 = healthCheck.getField46();
			if (field46 != null) {
				dataMap.put("field46", field46 + "");
			}
			dataMap.put("field47", healthCheck.getField47());
			Integer field48 = healthCheck.getField48();
			if (field48 != null) {
				dataMap.put("field48", field48 + "");
			}
			Integer field49 = healthCheck.getField49();
			if (field49 != null) {
				dataMap.put("field49", field49 + "");
			}

			String field50 = healthCheck.getField50();
			if (!StringUtils.isEmpty(field50)) {
				String[] arr = field50.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field50_1", num + "");
					} else if (i == 1) {
						dataMap.put("field50_2", num + "");
					} else if (i == 2) {
						dataMap.put("field50_3", num + "");
					}
				}
			}
			dataMap.put("field51", healthCheck.getField51());
			dataMap.put("field52", healthCheck.getField52());
			dataMap.put("field53", healthCheck.getField53());
			dataMap.put("field54", healthCheck.getField54());
			dataMap.put("field55", healthCheck.getField55());
			dataMap.put("field56", healthCheck.getField56());
			dataMap.put("field57", healthCheck.getField57());
			dataMap.put("field58", healthCheck.getField58());
			dataMap.put("field59", healthCheck.getField59());
			dataMap.put("field60", healthCheck.getField60());
			dataMap.put("field61", healthCheck.getField61());
			dataMap.put("field62", healthCheck.getField62());
			Integer field63 = healthCheck.getField63();
			if (field63 != null) {
				dataMap.put("field63", field63 + "");
			}
			dataMap.put("field64", healthCheck.getField64());
			dataMap.put("field65", healthCheck.getField65());
			dataMap.put("field66", healthCheck.getField66());
			dataMap.put("field67", healthCheck.getField67());
			Integer field68 = healthCheck.getField68();
			if (field68 != null) {
				dataMap.put("field68", field68 + "");
			}
			Integer field69 = healthCheck.getField69();
			if (field69 != null) {
				dataMap.put("field69", field69 + "");
			}
			Integer field70 = healthCheck.getField70();
			if (field70 != null) {
				dataMap.put("field70", field70 + "");
			}
			Integer field71 = healthCheck.getField71();
			if (field71 != null) {
				dataMap.put("field71", field71 + "");
			}
			Integer field72 = healthCheck.getField72();
			if (field72 != null) {
				dataMap.put("field72", field72 + "");
			}
			Integer field73 = healthCheck.getField73();
			if (field73 != null) {
				dataMap.put("field73", field73 + "");
			}
			Integer field74 = healthCheck.getField74();
			if (field74 != null) {
				dataMap.put("field74", field74 + "");
			}
			Integer field75 = healthCheck.getField75();
			if (field75 != null) {
				dataMap.put("field75", field75 + "");
			}
			Integer field76 = healthCheck.getField76();
			if (field76 != null) {
				dataMap.put("field76", field76 + "");
			}
			dataMap.put("field77", healthCheck.getField77());
			Integer field78 = healthCheck.getField78();
			if (field78 != null) {
				dataMap.put("field78", field78 + "");
			}
			Integer field79 = healthCheck.getField79();
			if (field79 != null) {
				dataMap.put("field79", field79 + "");
			}
			Integer field80 = healthCheck.getField80();
			if (field80 != null) {
				dataMap.put("field80", field80 + "");
			}
			Integer field81 = healthCheck.getField81();
			if (field81 != null) {
				dataMap.put("field81", field81 + "");
			}
			Integer field82 = healthCheck.getField82();
			if (field82 != null) {
				dataMap.put("field82", field82 + "");
			}
			Integer field83 = healthCheck.getField83();
			if (field83 != null) {
				dataMap.put("field83", field83 + "");
			}
			Integer field84 = healthCheck.getField84();
			if (field84 != null) {
				dataMap.put("field84", field84 + "");
			}
			Integer field85 = healthCheck.getField85();
			if (field85 != null) {
				dataMap.put("field85", field85 + "");
			}
			Integer field86 = healthCheck.getField86();
			if (field86 != null) {
				dataMap.put("field86", field86 + "");
			}
			Integer field87 = healthCheck.getField87();
			if (field87 != null) {
				dataMap.put("field87", field87 + "");
			}
			Integer field88 = healthCheck.getField88();
			if (field88 != null) {
				dataMap.put("field88", field88 + "");
			}
			Integer field89 = healthCheck.getField89();
			if (field89 != null) {
				dataMap.put("field89", field89 + "");
			}
			Integer field90 = healthCheck.getField90();
			if (field90 != null) {
				dataMap.put("field90", field90 + "");
			}
			Integer field91 = healthCheck.getField91();
			if (field91 != null) {
				dataMap.put("field91", field91 + "");
			}
			Integer field92 = healthCheck.getField92();
			if (field92 != null) {
				dataMap.put("field92", field92 + "");
			}
			Integer field93 = healthCheck.getField93();
			if (field93 != null) {
				dataMap.put("field93", field93 + "");
			}
			dataMap.put("field94", healthCheck.getField94());
			Integer field95 = healthCheck.getField95();
			if (field95 != null) {
				dataMap.put("field95", field95 + "");
			}
			Integer field96 = healthCheck.getField96();
			if (field96 != null) {
				dataMap.put("field96", field96 + "");
			}
			dataMap.put("field97", healthCheck.getField97());
			dataMap.put("field98", healthCheck.getField98());
			dataMap.put("field99", healthCheck.getField99());
			dataMap.put("field100", healthCheck.getField100());
			dataMap.put("field101", healthCheck.getField101());
			dataMap.put("field102", healthCheck.getField102());
			dataMap.put("field103", healthCheck.getField103());
			dataMap.put("field104", healthCheck.getField104());
			dataMap.put("field105", healthCheck.getField105());
			dataMap.put("field106", healthCheck.getField106());
			dataMap.put("field107", healthCheck.getField107());
			dataMap.put("field108", healthCheck.getField108());
			dataMap.put("field109", healthCheck.getField109());
			Integer field110 = healthCheck.getField110();
			if (field110 != null) {
				dataMap.put("field110", field110 + "");
			}
			dataMap.put("field111", healthCheck.getField111());
			Integer field112 = healthCheck.getField112();
			if (field112 != null) {
				dataMap.put("field112", field112 + "");
			}
			dataMap.put("field113", healthCheck.getField113());
			dataMap.put("field114", healthCheck.getField114());
			dataMap.put("field115", healthCheck.getField115());
			dataMap.put("field116", healthCheck.getField116());
			dataMap.put("field117", healthCheck.getField117());
			dataMap.put("field118", healthCheck.getField118());
			dataMap.put("field119", healthCheck.getField119());
			dataMap.put("field120", healthCheck.getField120());
			dataMap.put("field121", healthCheck.getField121());
			dataMap.put("field122", healthCheck.getField122());
			dataMap.put("field123", healthCheck.getField123());
			dataMap.put("field124", healthCheck.getField124());
			dataMap.put("field125", healthCheck.getField125());
			dataMap.put("field126", healthCheck.getField126());

			String field127 = healthCheck.getField127();
			if (!StringUtils.isEmpty(field127)) {
				String[] arr = field127.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field127_1", num + "");
					} else if (i == 1) {
						dataMap.put("field127_2", num + "");
					} else if (i == 2) {
						dataMap.put("field127_3", num + "");
					} else if (i == 3) {
						dataMap.put("field127_4", num + "");
					} else if (i == 4) {
						dataMap.put("field127_5", num + "");
					} else if (i == 5) {
						dataMap.put("field127_6", num + "");
					} else if (i == 6) {
						dataMap.put("field127_7", num + "");
					} else if (i == 7) {
						dataMap.put("field127_8", num + "");
					}
				}
			}
			dataMap.put("field128", healthCheck.getField128());
			Integer field129 = healthCheck.getField129();
			if (field129 != null) {
				dataMap.put("field129", field129 + "");
			}
			Integer field130 = healthCheck.getField130();
			if (field130 != null) {
				dataMap.put("field130", field130 + "");
			}
			Integer field132 = healthCheck.getField132();
			if (field132 != null) {
				dataMap.put("field132", field132 + "");
			}
			dataMap.put("field133", healthCheck.getField245());
			dataMap.put("field136", healthCheck.getField133());
			Integer field134 = healthCheck.getField134();
			if (field134 != null) {
				dataMap.put("field134", field134 + "");
			}
			dataMap.put("field135", healthCheck.getField135());

			String field137 = healthCheck.getField137();
			if (!StringUtils.isEmpty(field137)) {
				String[] arr = field137.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field137_1", num + "");
					} else if (i == 1) {
						dataMap.put("field137_2", num + "");
					} else if (i == 2) {
						dataMap.put("field137_3", num + "");
					} else if (i == 3) {
						dataMap.put("field137_4", num + "");
					} else if (i == 4) {
						dataMap.put("field137_5", num + "");
					}
				}
			}
			dataMap.put("field138", healthCheck.getField138());

			String field139 = healthCheck.getField139();
			if (!StringUtils.isEmpty(field139)) {
				String[] arr = field139.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field139_1", num + "");
					} else if (i == 1) {
						dataMap.put("field139_2", num + "");
					} else if (i == 2) {
						dataMap.put("field139_3", num + "");
					} else if (i == 3) {
						dataMap.put("field139_4", num + "");
					} else if (i == 4) {
						dataMap.put("field139_5", num + "");
					}
				}
			}
			dataMap.put("field140", healthCheck.getField140());

			String field141 = healthCheck.getField141();
			if (!StringUtils.isEmpty(field141)) {
				String[] arr = field141.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field141_1", num + "");
					} else if (i == 1) {
						dataMap.put("field141_2", num + "");
					} else if (i == 2) {
						dataMap.put("field141_3", num + "");
					} else if (i == 3) {
						dataMap.put("field141_4", num + "");
					} else if (i == 4) {
						dataMap.put("field141_5", num + "");
					}
				}
			}
			dataMap.put("field142", healthCheck.getField142());

			String field143 = healthCheck.getField143();
			if (!StringUtils.isEmpty(field143)) {
				String[] arr = field143.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field143_1", num + "");
					} else if (i == 1) {
						dataMap.put("field143_2", num + "");
					} else if (i == 2) {
						dataMap.put("field143_3", num + "");
					}
				}
			}
			dataMap.put("field144", healthCheck.getField144());

			String field145 = healthCheck.getField145();
			if (!StringUtils.isEmpty(field145)) {
				String[] arr = field145.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field145_1", num + "");
					} else if (i == 1) {
						dataMap.put("field145_2", num + "");
					} else if (i == 2) {
						dataMap.put("field145_3", num + "");
					}
				}
			}
			dataMap.put("field146", healthCheck.getField146());

			String field147 = healthCheck.getField147();
			if (!StringUtils.isEmpty(field147)) {
				String[] arr = field147.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field147_1", num + "");
					} else if (i == 1) {
						dataMap.put("field147_2", num + "");
					} else if (i == 2) {
						dataMap.put("field147_3", num + "");
					}
				}
			}
			dataMap.put("field148", healthCheck.getField148());
			Date field149 = healthCheck.getField149();
			if (field149 != null) {
				dataMap.put("field149", format1.format(field149));
			}
			Date field150 = healthCheck.getField150();
			if (field150 != null) {
				dataMap.put("field150", format1.format(field150));
			}
			Date field151 = healthCheck.getField151();
			if (field151 != null) {
				dataMap.put("field151", format1.format(field151));
			}
			Date field152 = healthCheck.getField152();
			if (field152 != null) {
				dataMap.put("field152", format1.format(field152));
			}
			dataMap.put("field153", healthCheck.getField153());
			dataMap.put("field154", healthCheck.getField154());
			dataMap.put("field155", healthCheck.getField155());
			dataMap.put("field156", healthCheck.getField156());
			dataMap.put("field157", healthCheck.getField157());
			dataMap.put("field158", healthCheck.getField158());
			Date field159 = healthCheck.getField159();
			if (field159 != null) {
				dataMap.put("field159", format1.format(field159));
			}
			Date field160 = healthCheck.getField160();
			if (field160 != null) {
				dataMap.put("field160", format1.format(field160));
			}
			Date field161 = healthCheck.getField161();
			if (field161 != null) {
				dataMap.put("field161", format1.format(field161));
			}
			Date field162 = healthCheck.getField162();
			if (field162 != null) {
				dataMap.put("field162", format1.format(field162));
			}
			dataMap.put("field163", healthCheck.getField163());
			dataMap.put("field164", healthCheck.getField164());
			dataMap.put("field165", healthCheck.getField165());
			dataMap.put("field166", healthCheck.getField166());
			dataMap.put("field167", healthCheck.getField167());
			dataMap.put("field168", healthCheck.getField168());
			dataMap.put("field169", healthCheck.getField169());
			dataMap.put("field170", transferString(healthCheck.getField170(), 1));
			dataMap.put("field171", healthCheck.getField171());
			dataMap.put("field172", transferString(healthCheck.getField172(), 2));
			dataMap.put("field173", transferString(healthCheck.getField173(), 3));
			dataMap.put("field174", healthCheck.getField174());
			dataMap.put("field175", transferString(healthCheck.getField175(), 1));
			dataMap.put("field176", healthCheck.getField176());
			dataMap.put("field177", transferString(healthCheck.getField177(), 2));
			dataMap.put("field178", transferString(healthCheck.getField178(), 3));
			dataMap.put("field179", healthCheck.getField179());
			dataMap.put("field180", transferString(healthCheck.getField180(), 1));
			dataMap.put("field181", healthCheck.getField181());
			dataMap.put("field182", transferString(healthCheck.getField182(), 2));
			dataMap.put("field183", transferString(healthCheck.getField183(), 3));
			dataMap.put("field184", healthCheck.getField184());
			dataMap.put("field185", transferString(healthCheck.getField185(), 1));
			dataMap.put("field186", healthCheck.getField186());
			dataMap.put("field187", transferString(healthCheck.getField187(), 2));
			dataMap.put("field188", transferString(healthCheck.getField188(), 3));
			dataMap.put("field189", healthCheck.getField189());
			dataMap.put("field190", transferString(healthCheck.getField190(), 1));
			dataMap.put("field191", healthCheck.getField191());
			dataMap.put("field192", transferString(healthCheck.getField192(), 2));
			dataMap.put("field193", transferString(healthCheck.getField193(), 3));
			dataMap.put("field194", healthCheck.getField194());
			dataMap.put("field195", transferString(healthCheck.getField195(), 1));
			dataMap.put("field196", healthCheck.getField196());
			dataMap.put("field197", transferString(healthCheck.getField197(), 2));
			dataMap.put("field198", transferString(healthCheck.getField198(), 3));
			dataMap.put("field199", healthCheck.getField199());
			Date field200 = healthCheck.getField200();
			if (field200 != null) {
				dataMap.put("field200", format1.format(field200));
			}
			dataMap.put("field201", healthCheck.getField201());
			dataMap.put("field202", healthCheck.getField202());
			Date field203 = healthCheck.getField203();
			if (field203 != null) {
				dataMap.put("field203", format1.format(field203));
			}
			dataMap.put("field204", healthCheck.getField204());
			dataMap.put("field205", healthCheck.getField205());
			Date field206 = healthCheck.getField206();
			if (field206 != null) {
				dataMap.put("field206", format1.format(field206));
			}
			dataMap.put("field207", healthCheck.getField207());
			Integer field208 = healthCheck.getField208();
			if (field208 != null) {
				dataMap.put("field208", field208 + "");
			}
			dataMap.put("field209", healthCheck.getField209());
			dataMap.put("field211", healthCheck.getField211());
			dataMap.put("field212", healthCheck.getField212());
			dataMap.put("field213", healthCheck.getField213());

			String field214 = healthCheck.getField214();
			if (!StringUtils.isEmpty(field214)) {
				String[] arr = field214.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field214_1", num + "");
					} else if (i == 1) {
						dataMap.put("field214_2", num + "");
					} else if (i == 2) {
						dataMap.put("field214_3", num + "");
					} else if (i == 3) {
						dataMap.put("field214_4", num + "");
					}
				}
			}
			String field215 = healthCheck.getField215();
			if (!StringUtils.isEmpty(field215)) {
				String[] arr = field215.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field215_1", num + "");
					} else if (i == 1) {
						dataMap.put("field215_2", num + "");
					} else if (i == 2) {
						dataMap.put("field215_3", num + "");
					} else if (i == 3) {
						dataMap.put("field215_4", num + "");
					} else if (i == 4) {
						dataMap.put("field215_5", num + "");
					} else if (i == 5) {
						dataMap.put("field215_6", num + "");
					} else if (i == 6) {
						dataMap.put("field215_7", num + "");
					}
				}
			}
			dataMap.put("field216", healthCheck.getField216());
			dataMap.put("field217", healthCheck.getField217());
			dataMap.put("field218", healthCheck.getField218());
			dataMap.put("field219", healthCheck.getField131());
			dataMap.put("field220", healthCheck.getField220());
			dataMap.put("field221", healthCheck.getField221());
			dataMap.put("field222", healthCheck.getField256());
			dataMap.put("field223", healthCheck.getField257());
			dataMap.put("field224", healthCheck.getField269());
			String field268 = healthCheck.getField268();
			if (!StringUtils.isEmpty(field268)) {
				String[] arr = field268.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field224_1", num + "");
					} else if (i == 1) {
						dataMap.put("field224_2", num + "");
					} else if (i == 2) {
						dataMap.put("field224_3", num + "");
					}
				}
			}
		} else if (selReport == 5) {// 自理能力
			template = "selfcare.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("idcard", patientDto.getIdcard());
			Integer total = 0;
			Integer field1 = selfCareDto.getField1();
			if (field1 != null) {
				if (field1 == 3) {
					field1 = 3;
				} else if (field1 == 4) {
					field1 = 5;
				} else {
					field1 = 0;
				}
				total = total + field1;
				dataMap.put("s1", field1 + "");
			}
			Integer field2 = selfCareDto.getField2();
			if (field2 != null) {
				if (field2 == 2) {
					field2 = 1;
				} else if (field2 == 3) {
					field2 = 3;
				} else if (field2 == 4) {
					field2 = 7;
				} else {
					field2 = 0;
				}
				total = total + field2;
				dataMap.put("s2", field2 + "");
			}
			Integer field3 = selfCareDto.getField3();
			if (field3 != null) {
				if (field3 == 3) {
					field3 = 3;
				} else if (field3 == 4) {
					field3 = 5;
				} else {
					field3 = 0;
				}
				total = total + field3;
				dataMap.put("s3", field3 + "");
			}
			Integer field4 = selfCareDto.getField4();
			if (field4 != null) {
				if (field4 == 2) {
					field4 = 1;
				} else if (field4 == 3) {
					field4 = 5;
				} else if (field4 == 4) {
					field4 = 10;
				} else {
					field4 = 0;
				}
				total = total + field4;
				dataMap.put("s4", field4 + "");
			}
			Integer field5 = selfCareDto.getField5();
			if (field5 != null) {
				if (field5 == 2) {
					field5 = 1;
				} else if (field5 == 3) {
					field5 = 5;
				} else if (field5 == 4) {
					field5 = 10;
				} else {
					field5 = 0;
				}
				total = total + field5;
				dataMap.put("s5", field5 + "");
			}
			if (total == 0) {
				if (field1 != null && field2 != null && field3 != null && field4 != null && field5 != null) {
					dataMap.put("total", total + "");
				}
			} else {
				dataMap.put("total", total + "");
			}
			dataMap.put("field7", selfCareDto.getField7());
			Date field8 = selfCareDto.getField8();
			if (field8 != null) {
				dataMap.put("field8", format.format(selfCareDto.getField8()));
			}
		} else if (selReport == 6) {// 中医体质
			template = "TCM.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("num1", code.substring(0, 1));
			dataMap.put("num2", code.substring(1, 2));
			dataMap.put("num3", code.substring(2, 3));
			dataMap.put("num4", code.substring(3, 4));
			dataMap.put("num5", code.substring(4, 5));
			dataMap.put("num6", code.substring(5, 6));
			dataMap.put("num7", code.substring(6, 7));
			dataMap.put("num8", code.substring(7, 8));
			dataMap.put("num9", code.substring(8, 9));
			dataMap.put("num10", code.substring(9, 10));
			dataMap.put("num11", code.substring(10, 11));
			dataMap.put("num12", code.substring(11, 12));
			if (tCMDto.getField1() != null) {
				dataMap.put("field1", tCMDto.getField1() + "");
			}
			if (tCMDto.getField2() != null) {
				dataMap.put("field2", tCMDto.getField2() + "");
			}
			if (tCMDto.getField3() != null) {
				dataMap.put("field3", tCMDto.getField3() + "");
			}
			if (tCMDto.getField4() != null) {
				dataMap.put("field4", tCMDto.getField4() + "");
			}
			if (tCMDto.getField5() != null) {
				dataMap.put("field5", tCMDto.getField5() + "");
			}
			if (tCMDto.getField6() != null) {
				dataMap.put("field6", tCMDto.getField6() + "");
			}
			if (tCMDto.getField7() != null) {
				dataMap.put("field7", tCMDto.getField7() + "");
			}
			if (tCMDto.getField8() != null) {
				dataMap.put("field8", tCMDto.getField8() + "");
			}
			if (tCMDto.getField9() != null) {
				dataMap.put("field9", tCMDto.getField9() + "");
			}
			if (tCMDto.getField10() != null) {
				dataMap.put("field10", tCMDto.getField10() + "");
			}
			if (tCMDto.getField11() != null) {
				dataMap.put("field11", tCMDto.getField11() + "");
			}
			if (tCMDto.getField12() != null) {
				dataMap.put("field12", tCMDto.getField12() + "");
			}
			if (tCMDto.getField13() != null) {
				dataMap.put("field13", tCMDto.getField13() + "");
			}
			if (tCMDto.getField14() != null) {
				dataMap.put("field14", tCMDto.getField14() + "");
			}
			if (tCMDto.getField15() != null) {
				dataMap.put("field15", tCMDto.getField15() + "");
			}
			if (tCMDto.getField16() != null) {
				dataMap.put("field16", tCMDto.getField16() + "");
			}
			if (tCMDto.getField17() != null) {
				dataMap.put("field17", tCMDto.getField17() + "");
			}
			if (tCMDto.getField18() != null) {
				dataMap.put("field18", tCMDto.getField18() + "");
			}
			if (tCMDto.getField19() != null) {
				dataMap.put("field19", tCMDto.getField19() + "");
			}
			if (tCMDto.getField20() != null) {
				dataMap.put("field20", tCMDto.getField20() + "");
			}
			if (tCMDto.getField21() != null) {
				dataMap.put("field21", tCMDto.getField21() + "");
			}
			if (tCMDto.getField22() != null) {
				dataMap.put("field22", tCMDto.getField22() + "");
			}
			if (tCMDto.getField23() != null) {
				dataMap.put("field23", tCMDto.getField23() + "");
			}
			if (tCMDto.getField24() != null) {
				dataMap.put("field24", tCMDto.getField24() + "");
			}
			if (tCMDto.getField25() != null) {
				dataMap.put("field25", tCMDto.getField25() + "");
			}
			if (tCMDto.getField26() != null) {
				dataMap.put("field26", tCMDto.getField26() + "");
			}
			if (tCMDto.getField27() != null) {
				dataMap.put("field27", tCMDto.getField27() + "");
			}
			if (tCMDto.getField28() != null) {
				dataMap.put("field28", tCMDto.getField28() + "");
			}
			if (tCMDto.getField29() != null) {
				dataMap.put("field29", tCMDto.getField29() + "");
			}
			if (tCMDto.getField30() != null) {
				dataMap.put("field30", tCMDto.getField30() + "");
			}
			if (tCMDto.getField31() != null) {
				dataMap.put("field31", tCMDto.getField31() + "");
			}
			if (tCMDto.getField32() != null) {
				dataMap.put("field32", tCMDto.getField32() + "");
			}
			if (tCMDto.getField33() != null) {
				dataMap.put("field33", tCMDto.getField33() + "");
			}
			// 气虚质得分
			dataMap.put("field53", tCMDto.getField53());
			// 阳虚质得分
			dataMap.put("field54", tCMDto.getField54());
			// 阴虚质得分
			dataMap.put("field55", tCMDto.getField55());
			// 痰湿质得分
			dataMap.put("field56", tCMDto.getField56());
			// 湿热质得分
			dataMap.put("field57", tCMDto.getField57());
			// 血瘀质得分
			dataMap.put("field58", tCMDto.getField58());
			// 气郁质得分
			dataMap.put("field59", tCMDto.getField59());
			// 特禀质得分
			dataMap.put("field60", tCMDto.getField60());
			// 平和质得分
			dataMap.put("field61", tCMDto.getField61());
			String field34 = tCMDto.getField34();
			if (!StringUtils.isEmpty(field34)) {
				String[] arr = field34.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field34", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field343", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field344", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field345", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field346", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field347", "√");
						}
					}
				}
			}

			String field35 = tCMDto.getField35();
			if (!StringUtils.isEmpty(field35)) {
				String[] arr = field35.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field35", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field353", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field354", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field355", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field356", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field357", "√");
						}
					}
				}
			}

			String field36 = tCMDto.getField36();
			if (!StringUtils.isEmpty(field36)) {
				String[] arr = field36.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field36", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field363", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field364", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field365", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field366", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field367", "√");
						}
					}
				}
			}

			String field37 = tCMDto.getField37();
			if (!StringUtils.isEmpty(field37)) {
				String[] arr = field37.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field37", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field373", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field374", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field375", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field376", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field377", "√");
						}
					}
				}
			}

			String field38 = tCMDto.getField38();
			if (!StringUtils.isEmpty(field38)) {
				String[] arr = field38.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field38", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field383", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field384", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field385", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field386", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field387", "√");
						}
					}
				}
			}

			String field39 = tCMDto.getField39();
			if (!StringUtils.isEmpty(field39)) {
				String[] arr = field39.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field39", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field393", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field394", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field395", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field396", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field397", "√");
						}
					}
				}
			}

			String field40 = tCMDto.getField40();
			if (!StringUtils.isEmpty(field40)) {
				String[] arr = field40.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field40", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field403", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field404", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field405", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field406", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field407", "√");
						}
					}
				}
			}

			String field41 = tCMDto.getField41();
			if (!StringUtils.isEmpty(field41)) {
				String[] arr = field41.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field41", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field413", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field414", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field415", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field416", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field417", "√");
						}
					}
				}
			}

			String field42 = tCMDto.getField42();
			if (!StringUtils.isEmpty(field42)) {
				String[] arr = field42.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("field42", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("field423", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("field424", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("field425", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("field426", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("field427", "√");
						}
					}
				}
			}
		} else if (selReport == 7) {// 体检报告(单页)
			template = "mreport1.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("sex", sexStr);
			dataMap.put("age", patientDto.getAge().toString());
			dataMap.put("idcard", patientDto.getIdcard());
			dataMap.put("code", patientDto.getCode());
			dataMap.put("phoneno", patientDto.getPhoneno());
			dataMap.put("address", patientDto.getAddress1());
			// 身高体重
			String hwStr = "";
			// 身高
			String field11 = healthCheck.getField11();
			if (!StringUtils.isEmpty(field11)) {
				hwStr = "身高" + field11 + "cm";
			}
			// 体重
			String field12 = healthCheck.getField12();
			if (!StringUtils.isEmpty(field12)) {
				hwStr = hwStr + "体重" + field12 + "kg";
			}
			dataMap.put("field219", hwStr);
			// 血压
			String field9 = healthCheck.getField9();
			if (StringUtils.isEmpty(field9)) {
				field9 = "";
			}
			String field10 = healthCheck.getField10();
			if (StringUtils.isEmpty(field10)) {
				field10 = "";
			}
			dataMap.put("field9", field9 + "/" + field10);
			// 血红蛋白
			dataMap.put("field97", healthCheck.getField97());
			dataMap.put("hfield97", convertMap.get("field97"));
			// 白细胞
			dataMap.put("field98", healthCheck.getField98());
			dataMap.put("hfield98", convertMap.get("field98"));
			// 血小板
			dataMap.put("field99", healthCheck.getField99());
			dataMap.put("hfield99", convertMap.get("field99"));
			// 尿蛋白
			dataMap.put("field101", healthCheck.getField101());
			// 尿糖
			dataMap.put("field102", healthCheck.getField102());
			// 尿酮体
			dataMap.put("field103", healthCheck.getField103());
			// 尿潜血
			dataMap.put("field104", healthCheck.getField104());
			// 血清谷丙转氨酶
			dataMap.put("field114", healthCheck.getField114());
			dataMap.put("hfield114", convertMap.get("field114"));
			// 血清谷草转氨酶
			dataMap.put("field115", healthCheck.getField115());
			dataMap.put("hfield115", convertMap.get("field115"));
			// 总胆红素
			dataMap.put("field117", healthCheck.getField117());
			dataMap.put("hfield117", convertMap.get("field117"));
			// 血清肌酐
			dataMap.put("field119", healthCheck.getField119());
			dataMap.put("hfield119", convertMap.get("field119"));
			// 血尿素氮
			dataMap.put("field120", healthCheck.getField120());
			dataMap.put("hfield120", convertMap.get("field120"));
			// 总胆固醇
			dataMap.put("field123", healthCheck.getField123());
			dataMap.put("hfield123", convertMap.get("field123"));
			// 甘油三酯
			dataMap.put("field124", healthCheck.getField124());
			dataMap.put("hfield124", convertMap.get("field124"));
			// 空腹血糖
			dataMap.put("field106", healthCheck.getField106());
			dataMap.put("hfield106", convertMap.get("field106"));
			// 血清低密度脂蛋白
			dataMap.put("field125", healthCheck.getField125());
			dataMap.put("hfield125", convertMap.get("field125"));
			// 血清高密度脂蛋白
			dataMap.put("field126", healthCheck.getField126());
			dataMap.put("hfield126", convertMap.get("field126"));

			// 心电结论
			// 心电图1正常2ST段改变3陈旧性心肌梗塞4窦性心动过速5窦性心动过缓6早搏7房颤8房室传导阻滞9其他
			String ecgStr = "";
			String field127 = healthCheck.getField127();
			if (field127 != null && !"".equals(field127)) {
				String[] arr = field127.split(",");
				for (int i = 0; i < arr.length; i++) {
					if (Integer.parseInt(arr[i]) == 1) {
						ecgStr = ecgStr + " 正常";
					} else if (Integer.parseInt(arr[i]) == 2) {
						ecgStr = ecgStr + " ST段改变";
					} else if (Integer.parseInt(arr[i]) == 3) {
						ecgStr = ecgStr + " 陈旧性心肌梗塞";
					} else if (Integer.parseInt(arr[i]) == 4) {
						ecgStr = ecgStr + " 窦性心动过速";
					} else if (Integer.parseInt(arr[i]) == 5) {
						ecgStr = ecgStr + " 窦性心动过缓";
					} else if (Integer.parseInt(arr[i]) == 6) {
						ecgStr = ecgStr + " 早搏";
					} else if (Integer.parseInt(arr[i]) == 7) {
						ecgStr = ecgStr + " 房颤";
					} else if (Integer.parseInt(arr[i]) == 8) {
						ecgStr = ecgStr + " 房室传导阻滞";
					} else if (Integer.parseInt(arr[i]) == 9) {
						// 心电图其他
						String field128 = healthCheck.getField128();
						ecgStr = ecgStr + " 其他 " + field128;
					}
				}
			}
			dataMap.put("ECG", ecgStr);
			dataMap.put("field77", healthCheck.getField77());

			// B超检查
			String bsuperStr = "";
			Integer field130 = healthCheck.getField130();
			if (field130 != null) {
				if (field130 == 1) {
					bsuperStr = "正常";
				} else if (field130 == 2) {
					bsuperStr = healthCheck.getField245();
				}
			}
			Integer field132 = healthCheck.getField132();
			if (field132 != null && field132 == 2) {
				bsuperStr = bsuperStr + " " + healthCheck.getField133();
			}
			dataMap.put("bsuper", bsuperStr);

			dataMap.put("field1", format.format(healthCheck.getField1()));
		} else if (selReport == 8) {// 体检报告(双面)
			template = "mreport2.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("code", patientDto.getCode());
			dataMap.put("sex", sexStr);
			dataMap.put("age", patientDto.getAge().toString());
			dataMap.put("field1", format.format(healthCheck.getField1()));
			dataMap.put("field11", healthCheck.getField11());
			dataMap.put("field12", healthCheck.getField12());
			dataMap.put("field219", healthCheck.getField219());
			dataMap.put("hfield219", convertMap.get("field219"));
			dataMap.put("field9", healthCheck.getField9());
			dataMap.put("hfield9", convertMap.get("field9"));
			dataMap.put("field10", healthCheck.getField10());
			dataMap.put("hfield10", convertMap.get("field10"));
			dataMap.put("field97", healthCheck.getField97());
			dataMap.put("hfield97", convertMap.get("field97"));
			dataMap.put("field98", healthCheck.getField98());
			dataMap.put("hfield98", convertMap.get("field98"));
			dataMap.put("field222", healthCheck.getField222());
			dataMap.put("hfield222", convertMap.get("field222"));
			dataMap.put("field223", healthCheck.getField223());
			dataMap.put("hfield223", convertMap.get("field223"));
			dataMap.put("field224", healthCheck.getField224());
			dataMap.put("hfield224", convertMap.get("field224"));
			dataMap.put("field225", healthCheck.getField225());
			dataMap.put("hfield225", convertMap.get("field225"));
			dataMap.put("field226", healthCheck.getField226());
			dataMap.put("hfield226", convertMap.get("field226"));
			dataMap.put("field227", healthCheck.getField227());
			dataMap.put("hfield227", convertMap.get("field227"));
			dataMap.put("field228", healthCheck.getField228());
			dataMap.put("hfield228", convertMap.get("field228"));
			dataMap.put("field229", healthCheck.getField229());
			dataMap.put("hfield229", convertMap.get("field229"));
			dataMap.put("field243", healthCheck.getField243());
			dataMap.put("hfield243", convertMap.get("field243"));
			dataMap.put("field230", healthCheck.getField230());
			dataMap.put("hfield230", convertMap.get("field230"));
			dataMap.put("field99", healthCheck.getField99());
			dataMap.put("hfield99", convertMap.get("field99"));
			dataMap.put("field231", healthCheck.getField231());
			dataMap.put("hfield231", convertMap.get("field231"));
			dataMap.put("field232", healthCheck.getField232());
			dataMap.put("hfield232", convertMap.get("field232"));
			dataMap.put("field233", healthCheck.getField233());
			dataMap.put("hfield233", convertMap.get("field233"));
			dataMap.put("field234", healthCheck.getField234());
			dataMap.put("hfield234", convertMap.get("field234"));
			dataMap.put("field235", healthCheck.getField235());
			dataMap.put("hfield235", convertMap.get("field235"));
			dataMap.put("field236", healthCheck.getField236());
			dataMap.put("hfield236", convertMap.get("field236"));
			dataMap.put("field101", healthCheck.getField101());
			dataMap.put("field258", healthCheck.getField258());
			dataMap.put("hfield101", convertMap.get("field101"));
			dataMap.put("field102", healthCheck.getField102());
			dataMap.put("field259", healthCheck.getField259());
			dataMap.put("hfield102", convertMap.get("field102"));
			dataMap.put("field237", healthCheck.getField237());
			dataMap.put("field260", healthCheck.getField260());
			dataMap.put("hfield237", convertMap.get("field237"));
			dataMap.put("field238", healthCheck.getField238());
			dataMap.put("field261", healthCheck.getField261());
			dataMap.put("hfield238", convertMap.get("field238"));
			dataMap.put("field104", healthCheck.getField104());
			dataMap.put("field262", healthCheck.getField262());
			dataMap.put("hfield104", convertMap.get("field104"));
			dataMap.put("field103", healthCheck.getField103());
			dataMap.put("field263", healthCheck.getField263());
			dataMap.put("hfield103", convertMap.get("field103"));
			dataMap.put("field239", healthCheck.getField239());
			dataMap.put("field264", healthCheck.getField264());
			dataMap.put("hfield239", convertMap.get("field239"));
			dataMap.put("field240", healthCheck.getField240());
			dataMap.put("field265", healthCheck.getField265());
			dataMap.put("hfield240", convertMap.get("field240"));
			dataMap.put("field241", healthCheck.getField241());
			dataMap.put("field266", healthCheck.getField266());
			dataMap.put("hfield241", convertMap.get("field241"));
			dataMap.put("field242", healthCheck.getField242());
			dataMap.put("field267", healthCheck.getField267());
			dataMap.put("hfield242", convertMap.get("field242"));
			dataMap.put("field117", healthCheck.getField117());
			dataMap.put("hfield117", convertMap.get("field117"));
			dataMap.put("field115", healthCheck.getField115());
			dataMap.put("hfield115", convertMap.get("field115"));
			dataMap.put("field114", healthCheck.getField114());
			dataMap.put("hfield114", convertMap.get("field114"));
			dataMap.put("field123", healthCheck.getField123());
			dataMap.put("hfield123", convertMap.get("field123"));
			dataMap.put("field124", healthCheck.getField124());
			dataMap.put("hfield124", convertMap.get("field124"));
			dataMap.put("field119", healthCheck.getField119());
			dataMap.put("hfield119", convertMap.get("field119"));
			dataMap.put("field120", healthCheck.getField120());
			dataMap.put("hfield120", convertMap.get("field120"));
			dataMap.put("field106", healthCheck.getField106());
			dataMap.put("hfield106", convertMap.get("field106"));
			dataMap.put("field125", healthCheck.getField125());
			dataMap.put("hfield125", convertMap.get("field125"));
			dataMap.put("field126", healthCheck.getField126());
			dataMap.put("hfield126", convertMap.get("field126"));
			dataMap.put("field14", healthCheck.getField14());
			dataMap.put("field247", healthCheck.getField247());
			dataMap.put("field248", healthCheck.getField248());
			dataMap.put("checkDate", format.format(healthCheck.getField1()));
			String field246 = healthCheck.getField246();
			if (!StringUtils.isEmpty(field246)) {
				String imgName = uuid.toString().replace("-", "") + ".jpg";
				field246 = CreatePDFUtil.encodeBase64File(field246, url + "/printTemplate/" + imgName);
				dataMap.put("field246", field246);
			}
			dataMap.put("field249", healthCheck.getField249());
			// 心电检查
			// 心电图1正常2ST段改变3陈旧性心肌梗塞4窦性心动过速5窦性心动过缓6早搏7房颤8房室传导阻滞9其他
			String ecgStr = "";
			String field127 = healthCheck.getField127();
			if (!StringUtils.isEmpty(field127)) {
				String[] arr = field127.split(",");
				for (int i = 0; i < arr.length; i++) {
					if (Integer.parseInt(arr[i]) == 1) {
						ecgStr = ecgStr + " 正常";
					} else if (Integer.parseInt(arr[i]) == 2) {
						ecgStr = ecgStr + " ST段改变";
					} else if (Integer.parseInt(arr[i]) == 3) {
						ecgStr = ecgStr + " 陈旧性心肌梗塞";
					} else if (Integer.parseInt(arr[i]) == 4) {
						ecgStr = ecgStr + " 窦性心动过速";
					} else if (Integer.parseInt(arr[i]) == 5) {
						ecgStr = ecgStr + " 窦性心动过缓";
					} else if (Integer.parseInt(arr[i]) == 6) {
						ecgStr = ecgStr + " 早搏";
					} else if (Integer.parseInt(arr[i]) == 7) {
						ecgStr = ecgStr + " 房颤";
					} else if (Integer.parseInt(arr[i]) == 8) {
						ecgStr = ecgStr + " 房室传导阻滞";
					} else if (Integer.parseInt(arr[i]) == 9) {
						// 心电图其他
						String field128 = healthCheck.getField128();
						ecgStr = ecgStr + " 其他 " + field128;
					}
				}
			}
			dataMap.put("ecgStr", ecgStr);
		} else if (selReport == 9) {// 体检报告(A3)
			template = "mreportA3.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("idcard", patientDto.getIdcard());
			dataMap.put("phoneno", patientDto.getPhoneno());
			dataMap.put("address", patientDto.getAddress1());
			dataMap.put("code", patientDto.getCode());
			dataMap.put("hname", healthCenter.getName());
			dataMap.put("sex", sexStr);
			dataMap.put("age", patientDto.getAge().toString());
			dataMap.put("field1", format.format(healthCheck.getField1()));
			dataMap.put("field11", healthCheck.getField11());
			dataMap.put("field12", healthCheck.getField12());
			dataMap.put("field219", healthCheck.getField219());
			dataMap.put("hfield219", convertMap.get("field219"));
			dataMap.put("field9", healthCheck.getField9());
			dataMap.put("hfield9", convertMap.get("field9"));
			dataMap.put("field10", healthCheck.getField10());
			dataMap.put("hfield10", convertMap.get("field10"));
			dataMap.put("field97", healthCheck.getField97());
			dataMap.put("hfield97", convertMap.get("field97"));
			dataMap.put("field98", healthCheck.getField98());
			dataMap.put("hfield98", convertMap.get("field98"));
			dataMap.put("field222", healthCheck.getField222());
			dataMap.put("hfield222", convertMap.get("field222"));
			dataMap.put("field223", healthCheck.getField223());
			dataMap.put("hfield223", convertMap.get("field223"));
			dataMap.put("field224", healthCheck.getField224());
			dataMap.put("hfield224", convertMap.get("field224"));
			dataMap.put("field225", healthCheck.getField225());
			dataMap.put("hfield225", convertMap.get("field225"));
			dataMap.put("field226", healthCheck.getField226());
			dataMap.put("hfield226", convertMap.get("field226"));
			dataMap.put("field227", healthCheck.getField227());
			dataMap.put("hfield227", convertMap.get("field227"));
			dataMap.put("field228", healthCheck.getField228());
			dataMap.put("hfield228", convertMap.get("field228"));
			dataMap.put("field229", healthCheck.getField229());
			dataMap.put("hfield229", convertMap.get("field229"));
			dataMap.put("field243", healthCheck.getField243());
			dataMap.put("hfield243", convertMap.get("field243"));
			dataMap.put("field230", healthCheck.getField230());
			dataMap.put("hfield230", convertMap.get("field230"));
			dataMap.put("field99", healthCheck.getField99());
			dataMap.put("hfield99", convertMap.get("field99"));
			dataMap.put("field231", healthCheck.getField231());
			dataMap.put("hfield231", convertMap.get("field231"));
			dataMap.put("field232", healthCheck.getField232());
			dataMap.put("hfield232", convertMap.get("field232"));
			dataMap.put("field233", healthCheck.getField233());
			dataMap.put("hfield233", convertMap.get("field233"));
			dataMap.put("field234", healthCheck.getField234());
			dataMap.put("hfield234", convertMap.get("field234"));
			dataMap.put("field235", healthCheck.getField235());
			dataMap.put("hfield235", convertMap.get("field235"));
			dataMap.put("field236", healthCheck.getField236());
			dataMap.put("hfield236", convertMap.get("field236"));
			dataMap.put("field14", healthCheck.getField14());
			dataMap.put("field101", healthCheck.getField101());
			dataMap.put("field258", healthCheck.getField258());
			dataMap.put("hfield101", convertMap.get("field101"));
			dataMap.put("field102", healthCheck.getField102());
			dataMap.put("field259", healthCheck.getField259());
			dataMap.put("hfield102", convertMap.get("field102"));
			dataMap.put("field237", healthCheck.getField237());
			dataMap.put("field260", healthCheck.getField260());
			dataMap.put("hfield237", convertMap.get("field237"));
			dataMap.put("field238", healthCheck.getField238());
			dataMap.put("field261", healthCheck.getField261());
			dataMap.put("hfield238", convertMap.get("field238"));
			dataMap.put("field104", healthCheck.getField104());
			dataMap.put("field262", healthCheck.getField262());
			dataMap.put("hfield104", convertMap.get("field104"));
			dataMap.put("field103", healthCheck.getField103());
			dataMap.put("field263", healthCheck.getField263());
			dataMap.put("hfield103", convertMap.get("field103"));
			dataMap.put("field239", healthCheck.getField239());
			dataMap.put("field264", healthCheck.getField264());
			dataMap.put("hfield239", convertMap.get("field239"));
			dataMap.put("field240", healthCheck.getField240());
			dataMap.put("field265", healthCheck.getField265());
			dataMap.put("hfield240", convertMap.get("field240"));
			dataMap.put("field241", healthCheck.getField241());
			dataMap.put("field266", healthCheck.getField266());
			dataMap.put("hfield241", convertMap.get("field241"));
			dataMap.put("field242", healthCheck.getField242());
			dataMap.put("field267", healthCheck.getField267());
			dataMap.put("hfield242", convertMap.get("field242"));
			dataMap.put("field247", healthCheck.getField247());
			dataMap.put("field117", healthCheck.getField117());
			dataMap.put("hfield117", convertMap.get("field117"));
			dataMap.put("field115", healthCheck.getField115());
			dataMap.put("hfield115", convertMap.get("field115"));
			dataMap.put("field114", healthCheck.getField114());
			dataMap.put("hfield114", convertMap.get("field114"));
			dataMap.put("field123", healthCheck.getField123());
			dataMap.put("hfield123", convertMap.get("field123"));
			dataMap.put("field124", healthCheck.getField124());
			dataMap.put("hfield124", convertMap.get("field124"));
			dataMap.put("field119", healthCheck.getField119());
			dataMap.put("hfield119", convertMap.get("field119"));
			dataMap.put("field120", healthCheck.getField120());
			dataMap.put("hfield120", convertMap.get("field120"));
			dataMap.put("field106", healthCheck.getField106());
			dataMap.put("hfield106", convertMap.get("field106"));
			dataMap.put("field125", healthCheck.getField125());
			dataMap.put("hfield125", convertMap.get("field125"));
			dataMap.put("field126", healthCheck.getField126());
			dataMap.put("hfield126", convertMap.get("field126"));
			dataMap.put("field248", healthCheck.getField248());
			String field246 = healthCheck.getField246();
			if (!StringUtils.isEmpty(field246)) {
				String imgName = uuid.toString().replace("-", "") + ".jpg";
				field246 = CreatePDFUtil.encodeBase64File(field246, url + "/printTemplate/" + imgName);
				dataMap.put("field246", field246);
			}
			// 心电检查
			// 心电图1正常2ST段改变3陈旧性心肌梗塞4窦性心动过速5窦性心动过缓6早搏7房颤8房室传导阻滞9其他
			String ecgStr = "";
			String field127 = healthCheck.getField127();
			if (!StringUtils.isEmpty(field127)) {
				String[] arr = field127.split(",");
				for (int i = 0; i < arr.length; i++) {
					if (Integer.parseInt(arr[i]) == 1) {
						ecgStr = ecgStr + " 正常";
					} else if (Integer.parseInt(arr[i]) == 2) {
						ecgStr = ecgStr + " ST段改变";
					} else if (Integer.parseInt(arr[i]) == 3) {
						ecgStr = ecgStr + " 陈旧性心肌梗塞";
					} else if (Integer.parseInt(arr[i]) == 4) {
						ecgStr = ecgStr + " 窦性心动过速";
					} else if (Integer.parseInt(arr[i]) == 5) {
						ecgStr = ecgStr + " 窦性心动过缓";
					} else if (Integer.parseInt(arr[i]) == 6) {
						ecgStr = ecgStr + " 早搏";
					} else if (Integer.parseInt(arr[i]) == 7) {
						ecgStr = ecgStr + " 房颤";
					} else if (Integer.parseInt(arr[i]) == 8) {
						ecgStr = ecgStr + " 房室传导阻滞";
					} else if (Integer.parseInt(arr[i]) == 9) {
						// 心电图其他
						String field128 = healthCheck.getField128();
						ecgStr = ecgStr + " 其他 " + field128;
					}
				}
			}
			dataMap.put("ecgStr", ecgStr);
			dataMap.put("field249", healthCheck.getField249());
			String field210 = healthCheck.getField210();
			if (!StringUtils.isEmpty(field210)) {
				String imgName = uuid.toString().replace("-", "") + ".jpg";
				field210 = CreatePDFUtil.encodeBase64File(field210, url + "/printTemplate/" + imgName);
				dataMap.put("field210", field210);
			}
			dataMap.put("field244", healthCheck.getField244());
			dataMap.put("field245", healthCheck.getField245());
			dataMap.put("field250", healthCheck.getField250());

			// 健康体检结果
			String reStr = "体检无异常";
			Integer field208 = healthCheck.getField208();
			if (field208 != null && field208 == 2) {
				String field209 = healthCheck.getField209();
				String field211 = healthCheck.getField211();
				String field212 = healthCheck.getField212();
				String field213 = healthCheck.getField213();
				if (!StringUtils.isEmpty(field209)) {
					reStr = field209 + ";";
				}
				if (!StringUtils.isEmpty(field211)) {
					reStr = reStr + field211 + ";";
				}
				if (!StringUtils.isEmpty(field212)) {
					reStr = reStr + field212 + ";";
				}
				if (!StringUtils.isEmpty(field213)) {
					reStr = reStr + field213 + ";";
				}
				// 健康指导1定期随访2纳入慢性病患者健康管理3建议复查4建议转诊
				String field214 = healthCheck.getField214();
				if (!StringUtils.isEmpty(field214)) {
					if ("1".equals(field214)) {
						reStr = reStr + "定期随访" + ";";
					} else if ("2".equals(field214)) {
						reStr = reStr + "纳入慢性病患者健康管理" + ";";
					} else if ("3".equals(field214)) {
						reStr = reStr + "建议复查" + ";";
					} else if ("4".equals(field214)) {
						reStr = reStr + "建议转诊" + ";";
					}
				}
				// 危险因素控制1戒烟2健康饮酒3饮食4锻炼5减体重6建议接种疫苗7其他
				String field215 = healthCheck.getField215();
				if (!StringUtils.isEmpty(field215)) {
					if ("1".equals(field215)) {
						reStr = reStr + "戒烟" + ";";
					} else if ("2".equals(field215)) {
						reStr = reStr + "健康饮酒" + ";";
					} else if ("3".equals(field215)) {
						reStr = reStr + "控制饮食" + ";";
					} else if ("4".equals(field215)) {
						reStr = reStr + "多锻炼" + ";";
					} else if ("5".equals(field215)) {
						reStr = reStr + "减体重" + ";";
					} else if ("6".equals(field215)) {
						reStr = reStr + "建议接种疫苗" + ";";
					}
				}
				// 建议接种疫苗
				String field217 = healthCheck.getField217();
				if (!StringUtils.isEmpty(field217)) {
					reStr = reStr + field217 + ";";
				}
				// 健康体检其他
				String field218 = healthCheck.getField218();
				if (!StringUtils.isEmpty(field218)) {
					reStr = reStr + field218 + ";";
				}
			}
			dataMap.put("resultLab", reStr);
		} else if (selReport == 10 || selReport == 11) {// 10存档报告(65岁)A3//11存档报告A3(国)
			template = "archive.pdf";
			dataMap.put("num1", code.substring(0, 1));
			dataMap.put("num2", code.substring(1, 2));
			dataMap.put("num3", code.substring(2, 3));
			dataMap.put("num4", code.substring(3, 4));
			dataMap.put("num5", code.substring(4, 5));
			dataMap.put("num6", code.substring(5, 6));
			dataMap.put("num7", code.substring(6, 7));
			dataMap.put("num8", code.substring(7, 8));
			dataMap.put("num9", code.substring(8, 9));
			dataMap.put("num10", code.substring(9, 10));
			dataMap.put("num11", code.substring(10, 11));
			dataMap.put("num12", code.substring(11, 12));
			dataMap.put("name", patientDto.getName());
			dataMap.put("address", patientDto.getAddress());
			dataMap.put("address1", patientDto.getAddress1());
			dataMap.put("phoneno", patientDto.getPhoneno());
			dataMap.put("countyname", patientDto.getCountyname());
			dataMap.put("vcname", patientDto.getVcname());
			dataMap.put("fileunit", patientDto.getFileunit());
			dataMap.put("fileuser", patientDto.getFileuser());
			dataMap.put("doctor", patientDto.getDoctor());

			dataMap.put("code", code);
			dataMap.put("doctor", patientDto.getDoctor());
			dataMap.put("field1", format.format(healthCheck.getField1()));
			String field2 = healthCheck.getField2();
			if (!StringUtils.isEmpty(field2)) {
				String[] arr = field2.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field2_1", num + "");
					} else if (i == 1) {
						dataMap.put("field2_2", num + "");
					} else if (i == 2) {
						dataMap.put("field2_3", num + "");
					} else if (i == 3) {
						dataMap.put("field2_4", num + "");
					} else if (i == 4) {
						dataMap.put("field2_5", num + "");
					} else if (i == 5) {
						dataMap.put("field2_6", num + "");
					} else if (i == 6) {
						dataMap.put("field2_7", num + "");
					} else if (i == 7) {
						dataMap.put("field2_8", num + "");
					} else if (i == 8) {
						dataMap.put("field2_9", num + "");
					} else if (i == 9) {
						dataMap.put("field2_10", num + "");
					}
				}
			}
			dataMap.put("field4", healthCheck.getField4());
			dataMap.put("field5", healthCheck.getField5());
			dataMap.put("field6", healthCheck.getField6());
			dataMap.put("field7", healthCheck.getField7());
			dataMap.put("field8", healthCheck.getField8());
			dataMap.put("field9", healthCheck.getField9());
			dataMap.put("field10", healthCheck.getField10());
			dataMap.put("field11", healthCheck.getField11());
			dataMap.put("field12", healthCheck.getField12());
			dataMap.put("field13", healthCheck.getField13());
			dataMap.put("field219", healthCheck.getField219());
			Integer field15 = healthCheck.getField15();
			if (field15 != null) {
				dataMap.put("field15", field15 + "");
			}
			Integer field16 = healthCheck.getField16();
			if (field16 != null) {
				dataMap.put("field16", field16 + "");
			}
			Integer field17 = healthCheck.getField17();
			if (field17 != null) {
				dataMap.put("field17", field17 + "");
			}
			Integer field18 = healthCheck.getField18();
			if (field18 != null) {
				dataMap.put("field18", field18 + "");
			}
			Integer field19 = healthCheck.getField19();
			if (field19 != null) {
				dataMap.put("field19", field19 + "");
			}
			dataMap.put("field20", healthCheck.getField20());
			dataMap.put("field21", healthCheck.getField21());
			dataMap.put("field22", healthCheck.getField22());
			String field23 = healthCheck.getField23();
			if (!StringUtils.isEmpty(field23)) {
				String[] arr = field23.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field23_1", num + "");
					} else if (i == 1) {
						dataMap.put("field23_2", num + "");
					} else if (i == 2) {
						dataMap.put("field23_3", num + "");
					} else if (i == 3) {
						dataMap.put("field23_4", num + "");
					} else if (i == 4) {
						dataMap.put("field23_5", num + "");
					}
				}
			}
			Integer field24 = healthCheck.getField24();
			if (field24 != null) {
				dataMap.put("field24", field24 + "");
			}
			dataMap.put("field25", healthCheck.getField25());
			dataMap.put("field26", healthCheck.getField26());
			dataMap.put("field27", healthCheck.getField27());
			Integer field28 = healthCheck.getField28();
			if (field28 != null) {
				dataMap.put("field28", field28 + "");
			}
			dataMap.put("field29", healthCheck.getField29());
			Integer field30 = healthCheck.getField30();
			if (field30 != null) {
				dataMap.put("field30", field30 + "");
			}
			dataMap.put("field31", healthCheck.getField31());
			dataMap.put("field32", healthCheck.getField32());
			Integer field33 = healthCheck.getField33();
			if (field33 != null) {
				dataMap.put("field33", field33 + "");
			}
			String field34 = healthCheck.getField34();
			if (!StringUtils.isEmpty(field34)) {
				String[] arr = field34.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field34_1", num + "");
					} else if (i == 1) {
						dataMap.put("field34_2", num + "");
					} else if (i == 2) {
						dataMap.put("field34_3", num + "");
					} else if (i == 3) {
						dataMap.put("field34_4", num + "");
					}
				}
			}
			dataMap.put("field35", healthCheck.getField35());
			Integer field36 = healthCheck.getField36();
			if (field36 != null) {
				dataMap.put("field36", field36 + "");
			}
			dataMap.put("field37", healthCheck.getField37());
			dataMap.put("field38", healthCheck.getField38());
			dataMap.put("field39", healthCheck.getField39());
			Integer field40 = healthCheck.getField40();
			if (field40 != null) {
				dataMap.put("field40", field40 + "");
			}
			dataMap.put("field41", healthCheck.getField41());
			Integer field42 = healthCheck.getField42();
			if (field42 != null) {
				dataMap.put("field42", field42 + "");
			}
			dataMap.put("field43", healthCheck.getField43());
			Integer field44 = healthCheck.getField44();
			if (field44 != null) {
				dataMap.put("field44", field44 + "");
			}
			dataMap.put("field45", healthCheck.getField45());
			Integer field46 = healthCheck.getField46();
			if (field46 != null) {
				dataMap.put("field46", field46 + "");
			}
			dataMap.put("field47", healthCheck.getField47());
			Integer field48 = healthCheck.getField48();
			if (field48 != null) {
				dataMap.put("field48", field48 + "");
			}
			Integer field49 = healthCheck.getField49();
			if (field49 != null) {
				dataMap.put("field49", field49 + "");
			}
			String field50 = healthCheck.getField50();
			if (!StringUtils.isEmpty(field50)) {
				String[] arr = field50.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field50_1", num + "");
					} else if (i == 1) {
						dataMap.put("field50_2", num + "");
					} else if (i == 2) {
						dataMap.put("field50_3", num + "");
					}
				}
			}
			dataMap.put("field51", healthCheck.getField51());
			dataMap.put("field52", healthCheck.getField52());
			dataMap.put("field53", healthCheck.getField53());
			dataMap.put("field54", healthCheck.getField54());
			dataMap.put("field55", healthCheck.getField55());
			dataMap.put("field56", healthCheck.getField56());
			dataMap.put("field57", healthCheck.getField57());
			dataMap.put("field58", healthCheck.getField58());
			dataMap.put("field59", healthCheck.getField59());
			dataMap.put("field60", healthCheck.getField60());
			dataMap.put("field61", healthCheck.getField61());
			dataMap.put("field62", healthCheck.getField62());
			Integer field63 = healthCheck.getField63();
			if (field63 != null) {
				dataMap.put("field63", field63 + "");
			}
			dataMap.put("field64", healthCheck.getField64());
			dataMap.put("field65", healthCheck.getField65());
			dataMap.put("field66", healthCheck.getField66());
			dataMap.put("field67", healthCheck.getField67());
			Integer field68 = healthCheck.getField68();
			if (field68 != null) {
				dataMap.put("field68", field68 + "");
			}
			Integer field69 = healthCheck.getField69();
			if (field69 != null) {
				dataMap.put("field69", field69 + "");
			}
			Integer field70 = healthCheck.getField70();
			if (field70 != null) {
				dataMap.put("field70", field70 + "");
			}
			Integer field71 = healthCheck.getField71();
			if (field71 != null) {
				dataMap.put("field71", field71 + "");
			}
			Integer field72 = healthCheck.getField72();
			if (field72 != null) {
				dataMap.put("field72", field72 + "");
			}
			Integer field73 = healthCheck.getField73();
			if (field73 != null) {
				dataMap.put("field73", field73 + "");
			}
			Integer field74 = healthCheck.getField74();
			if (field74 != null) {
				dataMap.put("field74", field74 + "");
			}
			Integer field75 = healthCheck.getField75();
			if (field75 != null) {
				dataMap.put("field75", field75 + "");
			}
			Integer field76 = healthCheck.getField76();
			if (field76 != null) {
				dataMap.put("field76", field76 + "");
			}
			dataMap.put("field77", healthCheck.getField77());
			Integer field78 = healthCheck.getField78();
			if (field78 != null) {
				dataMap.put("field78", field78 + "");
			}
			Integer field79 = healthCheck.getField79();
			if (field79 != null) {
				dataMap.put("field79", field79 + "");
			}
			Integer field80 = healthCheck.getField80();
			if (field80 != null) {
				dataMap.put("field80", field80 + "");
			}
			Integer field81 = healthCheck.getField81();
			if (field81 != null) {
				dataMap.put("field81", field81 + "");
			}
			Integer field82 = healthCheck.getField82();
			if (field82 != null) {
				dataMap.put("field82", field82 + "");
			}
			Integer field83 = healthCheck.getField83();
			if (field83 != null) {
				dataMap.put("field83", field83 + "");
			}
			Integer field84 = healthCheck.getField84();
			if (field84 != null) {
				dataMap.put("field84", field84 + "");
			}
			Integer field85 = healthCheck.getField85();
			if (field85 != null) {
				dataMap.put("field85", field85 + "");
			}
			Integer field86 = healthCheck.getField86();
			if (field86 != null) {
				dataMap.put("field86", field86 + "");
			}
			Integer field87 = healthCheck.getField87();
			if (field87 != null) {
				dataMap.put("field87", field87 + "");
			}
			Integer field88 = healthCheck.getField88();
			if (field88 != null) {
				dataMap.put("field88", field88 + "");
			}
			Integer field89 = healthCheck.getField89();
			if (field89 != null) {
				dataMap.put("field89", field89 + "");
			}
			Integer field90 = healthCheck.getField90();
			if (field90 != null) {
				dataMap.put("field90", field90 + "");
			}
			Integer field91 = healthCheck.getField91();
			if (field91 != null) {
				dataMap.put("field91", field91 + "");
			}
			Integer field92 = healthCheck.getField92();
			if (field92 != null) {
				dataMap.put("field92", field92 + "");
			}
			Integer field93 = healthCheck.getField93();
			if (field93 != null) {
				dataMap.put("field93", field93 + "");
			}
			dataMap.put("field94", healthCheck.getField94());
			Integer field95 = healthCheck.getField95();
			if (field95 != null) {
				dataMap.put("field95", field95 + "");
			}
			Integer field96 = healthCheck.getField96();
			if (field96 != null) {
				dataMap.put("field96", field96 + "");
			}
			dataMap.put("field97", healthCheck.getField97());
			dataMap.put("field98", healthCheck.getField98());
			dataMap.put("field99", healthCheck.getField99());
			dataMap.put("field100", healthCheck.getField100());
			dataMap.put("field101", healthCheck.getField101());
			dataMap.put("field102", healthCheck.getField102());
			dataMap.put("field103", healthCheck.getField103());
			dataMap.put("field104", healthCheck.getField104());
			dataMap.put("field105", healthCheck.getField105());
			dataMap.put("field106", healthCheck.getField106());
			dataMap.put("field107", healthCheck.getField107());
			dataMap.put("field108", healthCheck.getField108());
			dataMap.put("field109", healthCheck.getField109());
			Integer field110 = healthCheck.getField110();
			if (field110 != null) {
				dataMap.put("field110", field110 + "");
			}
			dataMap.put("field111", healthCheck.getField111());
			Integer field112 = healthCheck.getField112();
			if (field112 != null) {
				dataMap.put("field112", field112 + "");
			}
			dataMap.put("field113", healthCheck.getField113());
			dataMap.put("field114", healthCheck.getField114());
			dataMap.put("field115", healthCheck.getField115());
			dataMap.put("field116", healthCheck.getField116());
			dataMap.put("field117", healthCheck.getField117());
			dataMap.put("field118", healthCheck.getField118());
			dataMap.put("field119", healthCheck.getField119());
			dataMap.put("field120", healthCheck.getField120());
			dataMap.put("field121", healthCheck.getField121());
			dataMap.put("field122", healthCheck.getField122());
			dataMap.put("field123", healthCheck.getField123());
			dataMap.put("field124", healthCheck.getField124());
			dataMap.put("field125", healthCheck.getField125());
			dataMap.put("field126", healthCheck.getField126());
			String field127 = healthCheck.getField127();
			if (!StringUtils.isEmpty(field127)) {
				String[] arr = field127.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field127_1", num + "");
					} else if (i == 1) {
						dataMap.put("field127_2", num + "");
					} else if (i == 2) {
						dataMap.put("field127_3", num + "");
					} else if (i == 3) {
						dataMap.put("field127_4", num + "");
					} else if (i == 4) {
						dataMap.put("field127_5", num + "");
					} else if (i == 5) {
						dataMap.put("field127_6", num + "");
					} else if (i == 6) {
						dataMap.put("field127_7", num + "");
					} else if (i == 7) {
						dataMap.put("field127_8", num + "");
					}
				}
			}
			dataMap.put("field128", healthCheck.getField128());
			Integer field129 = healthCheck.getField129();
			if (field129 != null) {
				dataMap.put("field129", field129 + "");
			}
			Integer field130 = healthCheck.getField130();
			if (field130 != null) {
				dataMap.put("field130", field130 + "");
			}
			Integer field132 = healthCheck.getField132();
			if (field132 != null) {
				dataMap.put("field132", field132 + "");
			}
			dataMap.put("field133", healthCheck.getField245());
			dataMap.put("field136", healthCheck.getField133());
			Integer field134 = healthCheck.getField134();
			if (field134 != null) {
				dataMap.put("field134", field134 + "");
			}
			dataMap.put("field135", healthCheck.getField135());
			String field137 = healthCheck.getField137();
			if (!StringUtils.isEmpty(field137)) {
				String[] arr = field137.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field137_1", num + "");
					} else if (i == 1) {
						dataMap.put("field137_2", num + "");
					} else if (i == 2) {
						dataMap.put("field137_3", num + "");
					} else if (i == 3) {
						dataMap.put("field137_4", num + "");
					} else if (i == 4) {
						dataMap.put("field137_5", num + "");
					}
				}
			}
			dataMap.put("field138", healthCheck.getField138());
			String field139 = healthCheck.getField139();
			if (!StringUtils.isEmpty(field139)) {
				String[] arr = field139.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field139_1", num + "");
					} else if (i == 1) {
						dataMap.put("field139_2", num + "");
					} else if (i == 2) {
						dataMap.put("field139_3", num + "");
					} else if (i == 3) {
						dataMap.put("field139_4", num + "");
					} else if (i == 4) {
						dataMap.put("field139_5", num + "");
					}
				}
			}
			dataMap.put("field140", healthCheck.getField140());
			String field141 = healthCheck.getField141();
			if (!StringUtils.isEmpty(field141)) {
				String[] arr = field141.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field141_1", num + "");
					} else if (i == 1) {
						dataMap.put("field141_2", num + "");
					} else if (i == 2) {
						dataMap.put("field141_3", num + "");
					} else if (i == 3) {
						dataMap.put("field141_4", num + "");
					} else if (i == 4) {
						dataMap.put("field141_5", num + "");
					}
				}
			}
			dataMap.put("field142", healthCheck.getField142());
			String field143 = healthCheck.getField143();
			if (!StringUtils.isEmpty(field143)) {
				String[] arr = field143.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field143_1", num + "");
					} else if (i == 1) {
						dataMap.put("field143_2", num + "");
					} else if (i == 2) {
						dataMap.put("field143_3", num + "");
					}
				}
			}
			dataMap.put("field144", healthCheck.getField144());
			String field145 = healthCheck.getField145();
			if (!StringUtils.isEmpty(field145)) {
				String[] arr = field145.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field145_1", num + "");
					} else if (i == 1) {
						dataMap.put("field145_2", num + "");
					} else if (i == 2) {
						dataMap.put("field145_3", num + "");
					}
				}
			}
			dataMap.put("field146", healthCheck.getField146());
			String field147 = healthCheck.getField147();
			if (!StringUtils.isEmpty(field147)) {
				String[] arr = field147.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field147_1", num + "");
					} else if (i == 1) {
						dataMap.put("field147_2", num + "");
					} else if (i == 2) {
						dataMap.put("field147_3", num + "");
					}
				}
			}
			dataMap.put("field148", healthCheck.getField148());
			Date field149 = healthCheck.getField149();
			if (field149 != null) {
				dataMap.put("field149", format1.format(field149));
			}
			Date field150 = healthCheck.getField150();
			if (field150 != null) {
				dataMap.put("field150", format1.format(field150));
			}
			Date field151 = healthCheck.getField151();
			if (field151 != null) {
				dataMap.put("field151", format1.format(field151));
			}
			Date field152 = healthCheck.getField152();
			if (field152 != null) {
				dataMap.put("field152", format1.format(field152));
			}
			dataMap.put("field153", healthCheck.getField153());
			dataMap.put("field154", healthCheck.getField154());
			dataMap.put("field155", healthCheck.getField155());
			dataMap.put("field156", healthCheck.getField156());
			dataMap.put("field157", healthCheck.getField157());
			dataMap.put("field158", healthCheck.getField158());
			Date field159 = healthCheck.getField159();
			if (field159 != null) {
				dataMap.put("field159", format1.format(field159));
			}
			Date field160 = healthCheck.getField160();
			if (field160 != null) {
				dataMap.put("field160", format1.format(field160));
			}
			Date field161 = healthCheck.getField161();
			if (field161 != null) {
				dataMap.put("field161", format1.format(field161));
			}
			Date field162 = healthCheck.getField162();
			if (field162 != null) {
				dataMap.put("field162", format1.format(field162));
			}
			dataMap.put("field163", healthCheck.getField163());
			dataMap.put("field164", healthCheck.getField164());
			dataMap.put("field165", healthCheck.getField165());
			dataMap.put("field166", healthCheck.getField166());
			dataMap.put("field167", healthCheck.getField167());
			dataMap.put("field168", healthCheck.getField168());
			dataMap.put("field169", healthCheck.getField169());
			dataMap.put("field170", transferString(healthCheck.getField170(), 1));
			dataMap.put("field171", healthCheck.getField171());
			dataMap.put("field172", transferString(healthCheck.getField172(), 2));
			dataMap.put("field173", transferString(healthCheck.getField173(), 3));
			dataMap.put("field174", healthCheck.getField174());
			dataMap.put("field175", transferString(healthCheck.getField175(), 1));
			dataMap.put("field176", healthCheck.getField176());
			dataMap.put("field177", transferString(healthCheck.getField177(), 2));
			dataMap.put("field178", transferString(healthCheck.getField178(), 3));
			dataMap.put("field179", healthCheck.getField179());
			dataMap.put("field180", transferString(healthCheck.getField180(), 1));
			dataMap.put("field181", healthCheck.getField181());
			dataMap.put("field182", transferString(healthCheck.getField182(), 2));
			dataMap.put("field183", transferString(healthCheck.getField183(), 3));
			dataMap.put("field184", healthCheck.getField184());
			dataMap.put("field185", transferString(healthCheck.getField185(), 1));
			dataMap.put("field186", healthCheck.getField186());
			dataMap.put("field187", transferString(healthCheck.getField187(), 2));
			dataMap.put("field188", transferString(healthCheck.getField188(), 3));
			dataMap.put("field189", healthCheck.getField189());
			dataMap.put("field190", transferString(healthCheck.getField190(), 1));
			dataMap.put("field191", healthCheck.getField191());
			dataMap.put("field192", transferString(healthCheck.getField192(), 2));
			dataMap.put("field193", transferString(healthCheck.getField193(), 3));
			dataMap.put("field194", healthCheck.getField194());
			dataMap.put("field195", transferString(healthCheck.getField195(), 1));
			dataMap.put("field196", healthCheck.getField196());
			dataMap.put("field197", transferString(healthCheck.getField197(), 2));
			dataMap.put("field198", transferString(healthCheck.getField198(), 3));
			dataMap.put("field199", healthCheck.getField199());
			Date field200 = healthCheck.getField200();
			if (field200 != null) {
				dataMap.put("field200", format1.format(field200));
			}
			dataMap.put("field201", healthCheck.getField201());
			dataMap.put("field202", healthCheck.getField202());
			Date field203 = healthCheck.getField203();
			if (field203 != null) {
				dataMap.put("field203", format1.format(field203));
			}
			dataMap.put("field204", healthCheck.getField204());
			dataMap.put("field205", healthCheck.getField205());
			Date field206 = healthCheck.getField206();
			if (field206 != null) {
				dataMap.put("field206", format1.format(field206));
			}
			dataMap.put("field207", healthCheck.getField207());
			Integer field208 = healthCheck.getField208();
			if (field208 != null) {
				dataMap.put("field208", field208 + "");
			}
			dataMap.put("field209", healthCheck.getField209());
			dataMap.put("field211", healthCheck.getField211());
			dataMap.put("field212", healthCheck.getField212());
			dataMap.put("field213", healthCheck.getField213());
			String field214 = healthCheck.getField214();
			if (!StringUtils.isEmpty(field214)) {
				String[] arr = field214.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field214_1", num + "");
					} else if (i == 1) {
						dataMap.put("field214_2", num + "");
					} else if (i == 2) {
						dataMap.put("field214_3", num + "");
					} else if (i == 3) {
						dataMap.put("field214_4", num + "");
					}
				}
			}
			String field215 = healthCheck.getField215();
			if (!StringUtils.isEmpty(field215)) {
				String[] arr = field215.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field215_1", num + "");
					} else if (i == 1) {
						dataMap.put("field215_2", num + "");
					} else if (i == 2) {
						dataMap.put("field215_3", num + "");
					} else if (i == 3) {
						dataMap.put("field215_4", num + "");
					} else if (i == 4) {
						dataMap.put("field215_5", num + "");
					} else if (i == 5) {
						dataMap.put("field215_6", num + "");
					} else if (i == 6) {
						dataMap.put("field215_7", num + "");
					}
				}
			}
			dataMap.put("field216", healthCheck.getField216());
			dataMap.put("field217", healthCheck.getField217());
			dataMap.put("field218", healthCheck.getField218());
			dataMap.put("field219", healthCheck.getField269());
			String field268 = healthCheck.getField268();
			if (!StringUtils.isEmpty(field268)) {
				String[] arr = field268.split(",");
				for (int i = 0; i < arr.length; i++) {
					int num = Integer.parseInt(arr[i]);
					if (i == 0) {
						dataMap.put("field219_1", num + "");
					} else if (i == 1) {
						dataMap.put("field219_2", num + "");
					} else if (i == 2) {
						dataMap.put("field219_3", num + "");
					}
				}
			}

			if (tCMDto.getField1() != null) {
				dataMap.put("tfield1", tCMDto.getField1() + "");
			}
			if (tCMDto.getField2() != null) {
				dataMap.put("tfield2", tCMDto.getField2() + "");
			}
			if (tCMDto.getField3() != null) {
				dataMap.put("tfield3", tCMDto.getField3() + "");
			}
			if (tCMDto.getField4() != null) {
				dataMap.put("tfield4", tCMDto.getField4() + "");
			}
			if (tCMDto.getField5() != null) {
				dataMap.put("tfield5", tCMDto.getField5() + "");
			}
			if (tCMDto.getField6() != null) {
				dataMap.put("tfield6", tCMDto.getField6() + "");
			}
			if (tCMDto.getField7() != null) {
				dataMap.put("tfield7", tCMDto.getField7() + "");
			}
			if (tCMDto.getField8() != null) {
				dataMap.put("tfield8", tCMDto.getField8() + "");
			}
			if (tCMDto.getField9() != null) {
				dataMap.put("tfield9", tCMDto.getField9() + "");
			}
			if (tCMDto.getField10() != null) {
				dataMap.put("tfield10", tCMDto.getField10() + "");
			}
			if (tCMDto.getField11() != null) {
				dataMap.put("tfield11", tCMDto.getField11() + "");
			}
			if (tCMDto.getField12() != null) {
				dataMap.put("tfield12", tCMDto.getField12() + "");
			}
			if (tCMDto.getField13() != null) {
				dataMap.put("tfield13", tCMDto.getField13() + "");
			}
			if (tCMDto.getField14() != null) {
				dataMap.put("tfield14", tCMDto.getField14() + "");
			}
			if (tCMDto.getField15() != null) {
				dataMap.put("tfield15", tCMDto.getField15() + "");
			}
			if (tCMDto.getField16() != null) {
				dataMap.put("tfield16", tCMDto.getField16() + "");
			}
			if (tCMDto.getField17() != null) {
				dataMap.put("tfield17", tCMDto.getField17() + "");
			}
			if (tCMDto.getField18() != null) {
				dataMap.put("tfield18", tCMDto.getField18() + "");
			}
			if (tCMDto.getField19() != null) {
				dataMap.put("tfield19", tCMDto.getField19() + "");
			}
			if (tCMDto.getField20() != null) {
				dataMap.put("tfield20", tCMDto.getField20() + "");
			}
			if (tCMDto.getField21() != null) {
				dataMap.put("tfield21", tCMDto.getField21() + "");
			}
			if (tCMDto.getField22() != null) {
				dataMap.put("tfield22", tCMDto.getField22() + "");
			}
			if (tCMDto.getField23() != null) {
				dataMap.put("tfield23", tCMDto.getField23() + "");
			}
			if (tCMDto.getField24() != null) {
				dataMap.put("tfield24", tCMDto.getField24() + "");
			}
			if (tCMDto.getField25() != null) {
				dataMap.put("tfield25", tCMDto.getField25() + "");
			}
			if (tCMDto.getField26() != null) {
				dataMap.put("tfield26", tCMDto.getField26() + "");
			}
			if (tCMDto.getField27() != null) {
				dataMap.put("tfield27", tCMDto.getField27() + "");
			}
			if (tCMDto.getField28() != null) {
				dataMap.put("tfield28", tCMDto.getField28() + "");
			}
			if (tCMDto.getField29() != null) {
				dataMap.put("tfield29", tCMDto.getField29() + "");
			}
			if (tCMDto.getField30() != null) {
				dataMap.put("tfield30", tCMDto.getField30() + "");
			}
			if (tCMDto.getField31() != null) {
				dataMap.put("tfield31", tCMDto.getField31() + "");
			}
			if (tCMDto.getField32() != null) {
				dataMap.put("tfield32", tCMDto.getField32() + "");
			}
			if (tCMDto.getField33() != null) {
				dataMap.put("tfield33", tCMDto.getField33() + "");
			}
			// 气虚质得分
			dataMap.put("tfield53", tCMDto.getField53());
			// 阳虚质得分
			dataMap.put("tfield54", tCMDto.getField54());
			// 阴虚质得分
			dataMap.put("tfield55", tCMDto.getField55());
			// 痰湿质得分
			dataMap.put("tfield56", tCMDto.getField56());
			// 湿热质得分
			dataMap.put("tfield57", tCMDto.getField57());
			// 血瘀质得分
			dataMap.put("tfield58", tCMDto.getField58());
			// 气郁质得分
			dataMap.put("tfield59", tCMDto.getField59());
			// 特禀质得分
			dataMap.put("tfield60", tCMDto.getField60());
			// 平和质得分
			dataMap.put("tfield61", tCMDto.getField61());
			String tfield34 = tCMDto.getField34();
			if (!StringUtils.isEmpty(tfield34)) {
				String[] arr = tfield34.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield34", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield343", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield344", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield345", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield346", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield347", "√");
						}
					}
				}
			}
			String tfield35 = tCMDto.getField35();
			if (!StringUtils.isEmpty(tfield35)) {
				String[] arr = tfield35.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield35", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield353", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield354", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield355", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield356", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield357", "√");
						}
					}
				}
			}
			String tfield36 = tCMDto.getField36();
			if (!StringUtils.isEmpty(tfield36)) {
				String[] arr = tfield36.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield36", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield363", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield364", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield365", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield366", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield367", "√");
						}
					}
				}
			}
			String tfield37 = tCMDto.getField37();
			if (!StringUtils.isEmpty(tfield37)) {
				String[] arr = tfield37.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield37", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield373", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield374", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield375", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield376", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield377", "√");
						}
					}
				}
			}
			String tfield38 = tCMDto.getField38();
			if (!StringUtils.isEmpty(tfield38)) {
				String[] arr = tfield38.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield38", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield383", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield384", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield385", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield386", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield387", "√");
						}
					}
				}
			}
			String tfield39 = tCMDto.getField39();
			if (!StringUtils.isEmpty(tfield39)) {
				String[] arr = tfield39.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield39", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield393", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield394", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield395", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield396", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield397", "√");
						}
					}
				}
			}
			String tfield40 = tCMDto.getField40();
			if (!StringUtils.isEmpty(tfield40)) {
				String[] arr = tfield40.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield40", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield403", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield404", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield405", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield406", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield407", "√");
						}
					}
				}
			}
			String tfield41 = tCMDto.getField41();
			if (!StringUtils.isEmpty(tfield41)) {
				String[] arr = tfield41.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield41", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield413", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield414", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield415", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield416", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield417", "√");
						}
					}
				}
			}
			String tfield42 = tCMDto.getField42();
			if (!StringUtils.isEmpty(tfield42)) {
				String[] arr = tfield42.split(",");
				for (int i = 0; i < arr.length; i++) {
					int res = Integer.parseInt(arr[i]);
					if (res == 1 || res == 2) {
						dataMap.put("tfield42", (res + 1) + "");
					} else {
						if (3 == res) {// 情志调摄
							dataMap.put("tfield423", "√");
						} else if (4 == res) {// 饮食调养
							dataMap.put("tfield424", "√");
						} else if (5 == res) {// 起居调摄
							dataMap.put("tfield425", "√");
						} else if (6 == res) {// 运动保健
							dataMap.put("tfield426", "√");
						} else if (7 == res) {// 穴位保健
							dataMap.put("tfield427", "√");
						}
					}
				}
			}

			dataMap.put("idcard", patientDto.getIdcard());
			Integer total = 0;
			Integer sfield1 = selfCareDto.getField1();
			if (sfield1 != null) {
				if (sfield1 == 3) {
					sfield1 = 3;
				} else if (sfield1 == 4) {
					sfield1 = 5;
				} else {
					sfield1 = 0;
				}
				total = total + sfield1;
				dataMap.put("s1", sfield1 + "");
			}
			Integer sfield2 = selfCareDto.getField2();
			if (sfield2 != null) {
				if (sfield2 == 2) {
					sfield2 = 1;
				} else if (sfield2 == 3) {
					sfield2 = 3;
				} else if (sfield2 == 4) {
					sfield2 = 7;
				} else {
					sfield2 = 0;
				}
				total = total + sfield2;
				dataMap.put("s2", sfield2 + "");
			}
			Integer sfield3 = selfCareDto.getField3();
			if (sfield3 != null) {
				if (sfield3 == 3) {
					sfield3 = 3;
				} else if (sfield3 == 4) {
					sfield3 = 5;
				} else {
					sfield3 = 0;
				}
				total = total + sfield3;
				dataMap.put("s3", sfield3 + "");
			}
			Integer sfield4 = selfCareDto.getField4();
			if (sfield4 != null) {
				if (sfield4 == 2) {
					sfield4 = 1;
				} else if (sfield4 == 3) {
					sfield4 = 5;
				} else if (sfield4 == 4) {
					sfield4 = 10;
				} else {
					sfield4 = 0;
				}
				total = total + sfield4;
				dataMap.put("s4", sfield4 + "");
			}
			Integer sfield5 = selfCareDto.getField5();
			if (sfield5 != null) {
				if (sfield5 == 2) {
					sfield5 = 1;
				} else if (sfield5 == 3) {
					sfield5 = 5;
				} else if (sfield5 == 4) {
					sfield5 = 10;
				} else {
					sfield5 = 0;
				}
				total = total + sfield5;
				dataMap.put("s5", sfield5 + "");
			}
			if (total == 0) {
				if (sfield1 != null && sfield2 != null && sfield3 != null && sfield4 != null && sfield5 != null) {
					dataMap.put("total", total + "");
				}
			} else {
				dataMap.put("total", total + "");
			}
			dataMap.put("sfield7", selfCareDto.getField7());
			Date field8 = selfCareDto.getField8();
			if (field8 != null) {
				dataMap.put("sfield8", format.format(selfCareDto.getField8()));
			}
		} else if (selReport == 13) {// 生化报告
			template = "biochemical.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("sex", sexStr);
			dataMap.put("age", patientDto.getAge().toString());
			dataMap.put("code", patientDto.getCode());
			dataMap.put("fileunit", healthCenter.getName());
			dataMap.put("field114", healthCheck.getField114());
			dataMap.put("hfield114", convertMap.get("field114"));
			dataMap.put("field115", healthCheck.getField115());
			dataMap.put("hfield115", convertMap.get("field115"));
			dataMap.put("field117", healthCheck.getField117());
			dataMap.put("hfield117", convertMap.get("field117"));
			dataMap.put("field119", healthCheck.getField119());
			dataMap.put("hfield119", convertMap.get("field119"));
			dataMap.put("field120", healthCheck.getField120());
			dataMap.put("hfield120", convertMap.get("field120"));
			dataMap.put("field123", healthCheck.getField123());
			dataMap.put("hfield123", convertMap.get("field123"));
			dataMap.put("field124", healthCheck.getField124());
			dataMap.put("hfield124", convertMap.get("field124"));
			dataMap.put("field106", healthCheck.getField106());
			dataMap.put("hfield106", convertMap.get("field106"));
			dataMap.put("field125", healthCheck.getField125());
			dataMap.put("hfield125", convertMap.get("field125"));
			dataMap.put("field126", healthCheck.getField126());
			dataMap.put("hfield126", convertMap.get("field126"));
			dataMap.put("field248", healthCheck.getField248());
			dataMap.put("field1", format.format(healthCheck.getField1()));
		} else if (selReport == 14) {// 血常规报告
			template = "blood.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("sex", sexStr);
			dataMap.put("age", patientDto.getAge().toString());
			dataMap.put("code", patientDto.getCode());
			dataMap.put("fileunit", healthCenter.getName());
			// 血红蛋白
			dataMap.put("field97", healthCheck.getField97());
			dataMap.put("hfield97", convertMap.get("field97"));
			// 白细胞
			dataMap.put("field98", healthCheck.getField98());
			dataMap.put("hfield98", convertMap.get("field98"));
			// 红细胞计数
			dataMap.put("field222", healthCheck.getField222());
			dataMap.put("hfield222", convertMap.get("field222"));
			// 红细胞压积
			dataMap.put("field223", healthCheck.getField223());
			dataMap.put("hfield223", convertMap.get("field223"));
			// 淋巴细胞绝对值
			dataMap.put("field234", healthCheck.getField234());
			dataMap.put("hfield234", convertMap.get("field234"));
			// 中间细胞绝对值
			dataMap.put("field235", healthCheck.getField235());
			dataMap.put("hfield235", convertMap.get("field235"));
			// 中性粒细胞绝对值
			dataMap.put("field236", healthCheck.getField236());
			dataMap.put("hfield236", convertMap.get("field236"));
			// 平均红细胞血红蛋白浓度
			dataMap.put("field227", healthCheck.getField227());
			dataMap.put("hfield227", convertMap.get("field227"));
			// 平均红细胞体积
			dataMap.put("field228", healthCheck.getField228());
			dataMap.put("hfield228", convertMap.get("field228"));
			// 平均红细胞血红蛋白含量
			dataMap.put("field229", healthCheck.getField229());
			dataMap.put("hfield229", convertMap.get("field229"));
			// 红细胞分布宽度标准差
			dataMap.put("field243", healthCheck.getField243());
			dataMap.put("hfield243", convertMap.get("field243"));
			// 血小板分布宽度
			dataMap.put("field230", healthCheck.getField230());
			dataMap.put("hfield230", convertMap.get("field230"));
			// 血小板
			dataMap.put("field99", healthCheck.getField99());
			dataMap.put("hfield99", convertMap.get("field99"));
			// 红细胞分布宽度变异系数
			dataMap.put("field231", healthCheck.getField231());
			dataMap.put("hfield231", convertMap.get("field231"));
			// 平均血小板体积
			dataMap.put("field232", healthCheck.getField232());
			dataMap.put("hfield232", convertMap.get("field232"));
			// 血小板压积
			dataMap.put("field233", healthCheck.getField233());
			dataMap.put("hfield233", convertMap.get("field233"));
			// 淋巴细胞百分比
			dataMap.put("field224", healthCheck.getField224());
			dataMap.put("hfield224", convertMap.get("field224"));
			// 中间细胞百分比
			dataMap.put("field225", healthCheck.getField225());
			dataMap.put("hfield225", convertMap.get("field225"));
			// 中性粒细胞百分比
			dataMap.put("field226", healthCheck.getField226());
			dataMap.put("hfield226", convertMap.get("field226"));
			dataMap.put("field14", healthCheck.getField14());
			dataMap.put("field1", format.format(healthCheck.getField1()));
		} else if (selReport == 15) {// 尿常规报告
			template = "urine.pdf";
			dataMap.put("name", patientDto.getName());
			dataMap.put("sex", sexStr);
			dataMap.put("age", patientDto.getAge().toString());
			dataMap.put("code", patientDto.getCode());
			dataMap.put("fileunit", healthCenter.getName());

			dataMap.put("field101", healthCheck.getField101());
			dataMap.put("field258", healthCheck.getField258());
			dataMap.put("hfield101", convertMap.get("field101"));

			dataMap.put("field102", healthCheck.getField102());
			dataMap.put("field259", healthCheck.getField259());
			dataMap.put("hfield102", convertMap.get("field102"));

			dataMap.put("field237", healthCheck.getField237());
			dataMap.put("field260", healthCheck.getField260());
			dataMap.put("hfield237", convertMap.get("field237"));

			dataMap.put("field238", healthCheck.getField238());
			dataMap.put("field261", healthCheck.getField261());
			dataMap.put("hfield238", convertMap.get("field238"));

			dataMap.put("field104", healthCheck.getField104());
			dataMap.put("field262", healthCheck.getField262());
			dataMap.put("hfield104", convertMap.get("field104"));

			dataMap.put("field103", healthCheck.getField103());
			dataMap.put("field263", healthCheck.getField263());
			dataMap.put("hfield103", convertMap.get("field103"));

			dataMap.put("field239", healthCheck.getField239());
			dataMap.put("field264", healthCheck.getField264());
			dataMap.put("hfield239", convertMap.get("field239"));

			dataMap.put("field240", healthCheck.getField240());
			dataMap.put("field265", healthCheck.getField265());
			dataMap.put("hfield240", convertMap.get("field240"));

			dataMap.put("field241", healthCheck.getField241());
			dataMap.put("field266", healthCheck.getField266());
			dataMap.put("hfield241", convertMap.get("field241"));

			dataMap.put("field242", healthCheck.getField242());
			dataMap.put("field267", healthCheck.getField267());
			dataMap.put("hfield242", convertMap.get("field242"));

			dataMap.put("field247", healthCheck.getField247());
			dataMap.put("field1", format.format(healthCheck.getField1()));
		} else if (selReport == 16) {// B超报告
			template = "bsuper1.pdf";
			String field210 = healthCheck.getField210();
			String field251 = healthCheck.getField251();
			String field252 = healthCheck.getField252();
			String field253 = healthCheck.getField253();
			String field254 = healthCheck.getField254();
			String field255 = healthCheck.getField255();
			if (!StringUtils.isEmpty(field210)) {
				String imgName = uuid.toString().replace("-", "") + ".jpg";
				field210 = CreatePDFUtil.encodeBase64File(field210, url + "/printTemplate/" + imgName);
				dataMap.put("field210", field210);
			}
			if (!StringUtils.isEmpty(field251)) {
				String imgName251 = uuid.toString().replace("-", "") + ".jpg";
				field251 = CreatePDFUtil.encodeBase64File(field251, url + "/printTemplate/" + imgName251);
				dataMap.put("field251", field251);
				if (!StringUtils.isEmpty(field252)) {
					String imgName = uuid.toString().replace("-", "") + ".jpg";
					field252 = CreatePDFUtil.encodeBase64File(field252, url + "/printTemplate/" + imgName);
					dataMap.put("field252", field252);
				}
				if (!StringUtils.isEmpty(field253)) {
					String imgName = uuid.toString().replace("-", "") + ".jpg";
					field253 = CreatePDFUtil.encodeBase64File(field253, url + "/printTemplate/" + imgName);
					dataMap.put("field253", field253);
				}
				if (!StringUtils.isEmpty(field254)) {
					String imgName = uuid.toString().replace("-", "") + ".jpg";
					field254 = CreatePDFUtil.encodeBase64File(field254, url + "/printTemplate/" + imgName);
					dataMap.put("field254", field254);
				}
				if (!StringUtils.isEmpty(field255)) {
					String imgName = uuid.toString().replace("-", "") + ".jpg";
					field255 = CreatePDFUtil.encodeBase64File(field255, url + "/printTemplate/" + imgName);
					dataMap.put("field255", field255);
				}
				template = "bsuper2.pdf";
			}
			dataMap.put("name", patientDto.getName());
			dataMap.put("sex", sexStr);
			dataMap.put("age", patientDto.getAge().toString());
			dataMap.put("code", patientDto.getCode());
			dataMap.put("field244", healthCheck.getField244());
			dataMap.put("field245", healthCheck.getField245());
			dataMap.put("field250", healthCheck.getField250());
			dataMap.put("field1", format.format(healthCheck.getField1()));
		} else if (selReport == 17) {// 心电图报告
			template = "elediogram.pdf";
			String field246 = healthCheck.getField246();
			if (!StringUtils.isEmpty(field246)) {
				String imgName = uuid.toString().replace("-", "") + ".jpg";
				field246 = CreatePDFUtil.encodeBase64File(field246, url + "/printTemplate/" + imgName);
				dataMap.put("field246", field246);
			}
			dataMap.put("name", patientDto.getName());
			dataMap.put("sex", sexStr);
			dataMap.put("age", patientDto.getAge().toString());
			dataMap.put("code", patientDto.getCode());
			dataMap.put("field1", format.format(healthCheck.getField1()));
			dataMap.put("field249", healthCheck.getField249());
			// 心电检查
			// 心电图1正常2ST段改变3陈旧性心肌梗塞4窦性心动过速5窦性心动过缓6早搏7房颤8房室传导阻滞9其他
			String ecgStr = "";
			String field127 = healthCheck.getField127();
			if (!StringUtils.isEmpty(field127)) {
				String[] arr = field127.split(",");
				for (int i = 0; i < arr.length; i++) {
					if (Integer.parseInt(arr[i]) == 1) {
						ecgStr = ecgStr + " 正常";
					} else if (Integer.parseInt(arr[i]) == 2) {
						ecgStr = ecgStr + " ST段改变";
					} else if (Integer.parseInt(arr[i]) == 3) {
						ecgStr = ecgStr + " 陈旧性心肌梗塞";
					} else if (Integer.parseInt(arr[i]) == 4) {
						ecgStr = ecgStr + " 窦性心动过速";
					} else if (Integer.parseInt(arr[i]) == 5) {
						ecgStr = ecgStr + " 窦性心动过缓";
					} else if (Integer.parseInt(arr[i]) == 6) {
						ecgStr = ecgStr + " 早搏";
					} else if (Integer.parseInt(arr[i]) == 7) {
						ecgStr = ecgStr + " 房颤";
					} else if (Integer.parseInt(arr[i]) == 8) {
						ecgStr = ecgStr + " 房室传导阻滞";
					} else if (Integer.parseInt(arr[i]) == 9) {
						// 心电图其他
						String field128 = healthCheck.getField128();
						ecgStr = ecgStr + " 其他 " + field128;
					}
				}
			}
			dataMap.put("ecgStr", ecgStr);
		} else if (selReport == 20) {// 家医签约
			template = "doctor.pdf";
			dataMap = new HashMap<String, String>();
			dataMap.put("field1", healthCenter.getName());
			dataMap.put("field2", patientDto.getName());
			dataMap.put("field3", sexStr);
			Date birthdate = patientDto.getBirthdate();
			if (birthdate != null) {
				dataMap.put("birthday", format.format(birthdate));
			}
			dataMap.put("address", patientDto.getAddress1());
			dataMap.put("field4", patientDto.getPhoneno());
			dataMap.put("dno", patientDto.getCode());
			// 获取家医信息
			DoctorSignDto doctorSignDto = doctorService.getDoctorByhcid(patientDto.getHcid());
			if (doctorSignDto != null) {
				dataMap.put("field5", doctorSignDto.getField1());
				dataMap.put("field6", doctorSignDto.getField2());
				dataMap.put("field7", doctorSignDto.getField3());
				dataMap.put("field8", doctorSignDto.getField4());
				dataMap.put("field9", doctorSignDto.getField5());
				dataMap.put("field10", doctorSignDto.getField6());
				dataMap.put("field11", doctorSignDto.getField7());
				dataMap.put("field12", doctorSignDto.getField8());
				dataMap.put("field13", doctorSignDto.getField9());
				dataMap.put("field14", doctorSignDto.getField10());
				dataMap.put("field15", doctorSignDto.getField11());
				dataMap.put("field16", doctorSignDto.getField12());
				dataMap.put("field17", doctorSignDto.getField13());
				dataMap.put("field18", doctorSignDto.getField14());
				dataMap.put("field19", doctorSignDto.getField15());
				dataMap.put("field20", doctorSignDto.getField16());
				dataMap.put("field21", doctorSignDto.getField17());
				dataMap.put("field22", doctorSignDto.getField18());
				dataMap.put("field23", doctorSignDto.getField19());
				dataMap.put("field24", doctorSignDto.getField20());
				dataMap.put("field25", doctorSignDto.getField21());
				dataMap.put("field26", doctorSignDto.getField22());
				dataMap.put("field27", doctorSignDto.getField23());
				String field24 = doctorSignDto.getField24();
				if (!StringUtils.isEmpty(field24)) {
					String[] arr = field24.split(",");
					for (int i = 0; i < arr.length; i++) {
						int res = Integer.parseInt(arr[i]);
						if (1 == res) {
							dataMap.put("field28", "✓");
						} else if (2 == res) {
							dataMap.put("field29", "✓");
						} else if (3 == res) {
							dataMap.put("field30", "✓");
						} else if (4 == res) {
							dataMap.put("field31", "✓");
						} else if (5 == res) {
							dataMap.put("field32", "✓");
						} else if (6 == res) {
							dataMap.put("field33", "✓");
						} else if (7 == res) {
							dataMap.put("field34", "✓");
						} else if (8 == res) {
							dataMap.put("field35", "✓");
						} else if (9 == res) {
							dataMap.put("field36", "✓");
						}
					}
				}
				dataMap.put("field37", doctorSignDto.getField25());
				dataMap.put("field38", doctorSignDto.getField26());
				dataMap.put("field40", doctorSignDto.getField27());
			}
			dataMap.put("field39", patientDto.getName());
			String data = format1.format(healthCheck.getField1());
			dataMap.put("field41", data.split("-")[0]);
			dataMap.put("field44", data.split("-")[0]);
			dataMap.put("field42", data.split("-")[1]);
			dataMap.put("field45", data.split("-")[1]);
			dataMap.put("field43", data.split("-")[2]);
			dataMap.put("field46", data.split("-")[2]);
		}
		if (!"".equals(template)) {
			String fileStr = CreatePDFUtil.newPdf(url + "/printTemplate/", url + "/template/" + template, fileName, dataMap);
			return "printTemplate/" + fileStr + "/" + fileName;
		}
		return "";
	}

	/**
	 * 打印
	 */
	@RequestMapping(value = "/printPDF.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String printPDF(Model model, Integer pid, HttpServletRequest req, HttpServletResponse response,
			Integer selReport) {
		try {
			String fileName = createPDF(pid, req, selReport);
			model.addAttribute("fileName", fileName);
		} catch (Exception e) {
			log.error("ReportPrintControl->printPDF报错:" + e.toString());
		}
		return "print/printPDF";
	}

	/**
	 * 批量打印
	 */
	@RequestMapping(value = "/printAllPDF.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String printAllPDF(Model model, String pids, HttpServletRequest req, HttpServletResponse response,
			Integer selReport) {
		try {
			String fileName = "";
			String url = req.getSession().getServletContext().getRealPath("/");
			UUID uuid = UUID.randomUUID();
			String[] ids = pids.split(";");
			List<String> files = new ArrayList<String>();
			for (String id : ids) {
				if (!"".equals(id)) {
					String fName = url + "/" + createPDF(Integer.parseInt(id), req, selReport);
					files.add(fName);
				}
			}
			String fName = uuid.toString().replace("-", "") + ".pdf";
			fileName = CreatePDFUtil.PDFMerger(url + "/printTemplate/", files, fName);
			model.addAttribute("fileName", fileName);
		} catch (Exception e) {
			log.error("ReportPrintControl->printAllPDF报错:" + e.toString());
		}
		return "print/printPDF";
	}

	/**
	 * 初始化修改检查员页面
	 */
	@RequestMapping(value = "/updateCP.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String updateCP(Model model, Integer pid) {
		model.addAttribute("healthCheck", healthCheckService.getHealthCheck(pid));
		model.addAttribute("patient", patientService.getDetail(pid));
		return "print/updateCP";
	}

	/**
	 * 修改检查员
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updCP.do", method = { RequestMethod.POST })
	public void updCP(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto,
			String field1Str, String fileuser, String doctor) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (field1Str != null && !"".equals(field1Str)) {
				healthCheckDto.setField1(format.parse(field1Str));
			}
			healthCheckDto.setField14(URLDecoder.decode(healthCheckDto.getField14(), "UTF-8"));
			healthCheckDto.setField247(URLDecoder.decode(healthCheckDto.getField247(), "UTF-8"));
			healthCheckDto.setField248(URLDecoder.decode(healthCheckDto.getField248(), "UTF-8"));
			healthCheckDto.setField249(URLDecoder.decode(healthCheckDto.getField249(), "UTF-8"));
			healthCheckDto.setField250(URLDecoder.decode(healthCheckDto.getField250(), "UTF-8"));
			PatientDto patientDto = patientService.getDetail(healthCheckDto.getPid());
			patientDto.setFileuser(URLDecoder.decode(fileuser, "UTF-8"));
			patientDto.setDoctor(URLDecoder.decode(doctor, "UTF-8"));
			int num = healthCheckService.updCP(healthCheckDto, patientDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("ReportPrintControl->updCP报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 初始化修改检查员页面
	 */
	@RequestMapping(value = "/toAllCP.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toAllCP(HttpServletRequest req, Model model, String fidCard, String fname, String fcode,
			String fstartDate, String fendDate, Integer fselAge) {
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idCard", fidCard);
			params.put("name", URLDecoder.decode(fname, "UTF-8"));
			params.put("code", fcode);
			params.put("startDate", fstartDate + " 00:00:00");
			params.put("endDate", fendDate + " 23:59:59");
			params.put("ageOver", null);
			params.put("ageLow", null);
			Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
			ca.setTime(new Date()); // 设置时间为当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ca.add(Calendar.YEAR, -65); // 年份减1
			Date resultDate = ca.getTime(); // 结果
			if (fselAge == 2) {// 65岁以上
				params.put("ageOver", sdf.format(resultDate));
			} else if (fselAge == 3) {// 65岁以下
				params.put("ageLow", sdf.format(resultDate));
			}
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			params.put("hcid", userDto.getHcid());
			List<PrintForm> printForms = printService.getPrintList(params);
			String ids = "";
			for (PrintForm pf : printForms) {
				ids = ids + pf.getPid() + ",";
			}
			if (!"".equals(ids)) {
				ids = ids.substring(0, ids.length() - 1);
			}
			model.addAttribute("ids", ids);
		} catch (Exception e) {
			log.error("ReportPrintControl->toAllCP报错:" + e.toString());
		}
		return "print/updateAllCP";
	}

	/**
	 * 修改所有检查员
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updAllCP.do", method = { RequestMethod.POST })
	public void updAllCP(HttpServletRequest req, HttpServletResponse response, HealthCheckDto healthCheckDto,
			String field1Str, String ids, String fileuser, String doctor) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (field1Str != null && !"".equals(field1Str)) {
				healthCheckDto.setField1(format.parse(field1Str));
			}
			healthCheckDto.setField14(URLDecoder.decode(healthCheckDto.getField14(), "UTF-8"));
			healthCheckDto.setField247(URLDecoder.decode(healthCheckDto.getField247(), "UTF-8"));
			healthCheckDto.setField248(URLDecoder.decode(healthCheckDto.getField248(), "UTF-8"));
			healthCheckDto.setField249(URLDecoder.decode(healthCheckDto.getField249(), "UTF-8"));
			healthCheckDto.setField250(URLDecoder.decode(healthCheckDto.getField250(), "UTF-8"));
			int num = healthCheckService.updAllCP(healthCheckDto, ids, URLDecoder.decode(fileuser, "UTF-8"),
					URLDecoder.decode(doctor, "UTF-8"));
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("ReportPrintControl->updAllCP报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 下载
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/downLoad.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void downLoad(HttpServletRequest req, HttpServletResponse response, Model model, String idCard, String name,
			String code, String startDate, String endDate, Integer selAge) {
		File f = new File(this.setExcelData(req, idCard, name, code, startDate, endDate, selAge));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setCharacterEncoding("UTF-8");
			String fileName = new String("体检统计报表.xls".getBytes("UTF-8"), "ISO8859-1");
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control", "maxage=3600");
			response.setHeader("Pragma", "public");
			response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/octet-stream;charset=UTF-8;");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(f));
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (f != null) {
				f.delete();
			}
		}
	}

	/**
	 * 设置导出数据
	 * 
	 * @param req
	 * @param idCard
	 * @param name
	 * @param code
	 * @param startDate
	 * @param endDate
	 * @param selAge
	 * @return
	 */
	public String setExcelData(HttpServletRequest req, String idCard, String name, String code, String startDate,
			String endDate, Integer selAge) {
		String url = req.getSession().getServletContext().getRealPath("/");
		// 加载excel模板文件，进行标题设置
		FileInputStream fs = null;
		POIFSFileSystem ps = null;
		HSSFWorkbook wb = null;
		FileOutputStream out = null;
		UUID uuid = UUID.randomUUID();
		String fileName = url + "/template/" + uuid.toString().replace("-", "") + ".xls";
		try {
			fs = new FileInputStream(url + "/template/template.xls");
			ps = new POIFSFileSystem(fs);
			wb = new HSSFWorkbook(ps);
			HSSFSheet sheet = wb.getSheetAt(0);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idCard", idCard);
			if (name != null) {
				name = URLDecoder.decode(name, "UTF-8");
			}
			params.put("name", name);
			params.put("code", code);
			params.put("startDate", startDate + " 00:00:00");
			params.put("endDate", endDate + " 23:59:59");
			params.put("ageOver", null);
			params.put("ageLow", null);
			Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
			ca.setTime(new Date()); // 设置时间为当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ca.add(Calendar.YEAR, -65); // 年份减1
			Date resultDate = ca.getTime(); // 结果
			if (selAge == 2) {// 65岁以上
				params.put("ageOver", sdf.format(resultDate));
			} else if (selAge == 3) {// 65岁以下
				params.put("ageLow", sdf.format(resultDate));
			}
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			params.put("hcid", userDto.getHcid());
			List<PrintForm> printForms = printService.getPrintList(params);
			for (int j = 0; j < printForms.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				PrintForm printForm = printForms.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(sdf.format(printForm.getExamDate()));
				cell = row.createCell(1);
				cell.setCellValue(printForm.getName());
				cell = row.createCell(2);
				cell.setCellValue(printForm.getCode());
				cell = row.createCell(3);
				cell.setCellValue(printForm.getAge());
				cell = row.createCell(4);
				Integer sex = printForm.getSex();
				String sexStr = "";
				if (sex != null && sex != 0) {
					if (1 == sex) {
						sexStr = "男";
					} else if (2 == sex) {
						sexStr = "女";
					} else if (3 == sex) {
						sexStr = "未说明的性别";
					} else if (4 == sex) {
						sexStr = "未知的性别";
					}
				}
				cell.setCellValue(sexStr);
				cell = row.createCell(5);
				cell.setCellValue(printForm.getIdCard());
				cell = row.createCell(6);
				cell.setCellValue(printForm.getPhoneno());
				cell = row.createCell(7);
				String address = printForm.getAddress();
				if (StringUtils.isEmpty(address)) {
					address = printForm.getAddress1();
				}
				cell.setCellValue(address);
			}
			out = new FileOutputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
				if (wb != null) {
					wb.write(out);
					wb.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}

	/**
	 * 转换值
	 * 
	 * @return
	 */
	private String transferString(Integer value, int num) {
		String r = "";
		if (value != null) {
			if (num == 1) {// 用法(1)1口服2皮下注射3静脉注射4肌肉注射
				if (value == 1) {
					r = "口服";
				} else if (value == 2) {
					r = "皮下注射";
				} else if (value == 3) {
					r = "静脉注射";
				} else if (value == 4) {
					r = "肌肉注射";
				}
			} else if (num == 2) {// 用药时间
				// 用药时间(1)1 1周 2 2周 3 3周 4 1个月 5 2个月 6 3个月 7 4个月 8 5个月 9 6个月 10 7个月 11 8个月 12
				// 9个月 13 10个月 14 11个月 15 1年 16 2年 17 3年 18 4年 19 5年
				if (value == 1) {
					r = "1周";
				} else if (value == 2) {
					r = "2周";
				} else if (value == 3) {
					r = "3周";
				} else if (value == 4) {
					r = "1个月";
				} else if (value == 5) {
					r = "2个月";
				} else if (value == 6) {
					r = "3个月";
				} else if (value == 7) {
					r = "4个月";
				} else if (value == 8) {
					r = "5个月";
				} else if (value == 9) {
					r = "6个月";
				} else if (value == 10) {
					r = "7个月";
				} else if (value == 11) {
					r = "8个月";
				} else if (value == 12) {
					r = "9个月";
				} else if (value == 13) {
					r = "10个月";
				} else if (value == 14) {
					r = "11个月";
				} else if (value == 15) {
					r = "1年";
				} else if (value == 16) {
					r = "2年";
				} else if (value == 17) {
					r = "3年";
				} else if (value == 18) {
					r = "4年";
				} else if (value == 19) {
					r = "5年";
				}
			} else if (num == 3) {// 服药依从性1规律2间断3不服药
				if (value == 1) {
					r = "规律";
				} else if (value == 2) {
					r = "间断";
				} else if (value == 3) {
					r = "不服药";
				}
			}
		}
		return r;
	}

	/**
	 * 判断高血压或高血糖
	 * 
	 * @param v1
	 * @param v2
	 * @return
	 */
	private boolean isHigh(String v1, String v2) {
		if (!StringUtils.isEmpty(v1)) {
			String[] strs = v1.split(",");
			for (String str : strs) {
				if (v2.equals(str)) {
					return true;
				}
			}
		}
		return false;
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
	 * 获取血压/血糖值
	 * 
	 * @param v
	 * @return
	 */
	private String getBPValue(String v) {
		if (null != v && !"".equals(v)) {
			String[] vs = v.split("fg");
			// 判断高血压
			if (vs.length > 1) {
				if (isDouble(vs[0])) {
					Double field7 = Double.valueOf(vs[0]);
					if (field7 >= 140) {
						return vs[0] + "-" + vs[1];
					}
				}
				if (isDouble(vs[1])) {
					Double field8 = Double.valueOf(vs[1]);
					if (field8 >= 90) {
						return vs[0] + "-" + vs[1];
					}
				}
			}
			if (vs.length > 3) {
				if (isDouble(vs[2])) {
					Double field7 = Double.valueOf(vs[2]);
					if (field7 >= 140) {
						return vs[2] + "-" + vs[3];
					}
				}
				if (isDouble(vs[3])) {
					Double field8 = Double.valueOf(vs[3]);
					if (field8 >= 90) {
						return vs[2] + "-" + vs[3];
					}
				}
			}
		}
		return "";
	}

	/**
	 * 高血糖高血压设置导出数据
	 * 
	 * @param req
	 * @param idCard
	 * @param name
	 * @param code
	 * @param startDate
	 * @param endDate
	 * @param selAge
	 * @param stauts(0高血糖1高血压)
	 * @return
	 */
	public String gxtyData(HttpServletRequest req, String idCard, String name, String code, String startDate,
			String endDate, Integer selAge, Integer stauts) {
		String url = req.getSession().getServletContext().getRealPath("/");
		// 加载excel模板文件，进行标题设置
		FileInputStream fs = null;
		POIFSFileSystem ps = null;
		HSSFWorkbook wb = null;
		FileOutputStream out = null;
		UUID uuid = UUID.randomUUID();
		String fileName = url + "/template/" + uuid.toString().replace("-", "") + ".xls";
		try {
			String v = "3";
			String colStr = "高血糖";
			if (stauts == 0) {
				fs = new FileInputStream(url + "/template/template1.xls");
			} else {
				v = "2";
				colStr = "高血压";
				fs = new FileInputStream(url + "/template/template2.xls");
			}
			ps = new POIFSFileSystem(fs);
			wb = new HSSFWorkbook(ps);
			HSSFSheet sheet = wb.getSheetAt(0);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("idCard", idCard);
			if (name != null) {
				name = URLDecoder.decode(name, "UTF-8");
			}
			params.put("name", name);
			params.put("code", code);
			params.put("startDate", startDate + " 00:00:00");
			params.put("endDate", endDate + " 23:59:59");
			params.put("ageOver", null);
			params.put("ageLow", null);
			Calendar ca = Calendar.getInstance();// 得到一个Calendar的实例
			ca.setTime(new Date()); // 设置时间为当前时间
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			ca.add(Calendar.YEAR, -65); // 年份减1
			Date resultDate = ca.getTime(); // 结果
			if (selAge == 2) {// 65岁以上
				params.put("ageOver", sdf.format(resultDate));
			} else if (selAge == 3) {// 65岁以下
				params.put("ageLow", sdf.format(resultDate));
			}
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			params.put("hcid", userDto.getHcid());
			List<PrintForm> printForms = printService.gxtyData(params);
			int rowNum = 0;
			for (int j = 0; j < printForms.size(); j++) {
				PrintForm printForm = printForms.get(j);
				boolean isHigh = isHigh(printForm.getFiled1(), v);
				String bpStr = "";
				if (stauts == 0) {
					colStr = "高血糖";
					if (isDouble(printForm.getFiled3())) {
						Double gxt = Double.valueOf(printForm.getFiled3());
						if (gxt >= 6.11) {
							bpStr = gxt + "";
						}
					}
				} else {
					colStr = "高血压";
					bpStr = getBPValue(printForm.getFiled2());
				}
				if (isHigh || !"".equals(bpStr)) {
					rowNum = rowNum + 1;
					if (!isHigh) {
						colStr = "";
					}
					if (stauts != 1) {
						bpStr = printForm.getFiled3();
					}
					HSSFRow row = sheet.createRow(rowNum);
					HSSFCell cell = row.createCell(0);
					cell.setCellValue(sdf.format(printForm.getExamDate()));
					cell = row.createCell(1);
					cell.setCellValue(printForm.getName());
					cell = row.createCell(2);
					Integer sex = printForm.getSex();
					String sexStr = "";
					if (sex != null && sex != 0) {
						if (1 == sex) {
							sexStr = "男";
						} else if (2 == sex) {
							sexStr = "女";
						} else if (3 == sex) {
							sexStr = "未说明的性别";
						} else if (4 == sex) {
							sexStr = "未知的性别";
						}
					}
					cell.setCellValue(sexStr);
					cell = row.createCell(3);
					cell.setCellValue(printForm.getIdCard());
					cell = row.createCell(4);
					cell.setCellValue(bpStr);
					cell = row.createCell(5);
					String address = printForm.getAddress();
					if (StringUtils.isEmpty(address)) {
						address = printForm.getAddress1();
					}
					cell.setCellValue(address);
					cell = row.createCell(6);
					cell.setCellValue(printForm.getPhoneno());
					cell = row.createCell(7);
					cell.setCellValue(printForm.getAge());
					cell = row.createCell(8);
					cell.setCellValue(colStr);
					cell = row.createCell(9);
				}
			}
			out = new FileOutputStream(fileName);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fs != null) {
					fs.close();
				}
				if (wb != null) {
					if (out != null) {
						wb.write(out);
					}
					wb.close();
				}
				if (out != null) {
					out.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileName;
	}

	/**
	 * 高血糖高血压下载
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/gxtyDownLoad.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void gxtyDownLoad(HttpServletRequest req, HttpServletResponse response, Model model, String idCard,
			String name, String code, String startDate, String endDate, Integer selAge, Integer stauts) {
		File f = new File(this.gxtyData(req, idCard, name, code, startDate, endDate, selAge, stauts));
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			response.setCharacterEncoding("UTF-8");
			String fileName = new String("高血糖.xls".getBytes("UTF-8"), "ISO8859-1");
			if (stauts == 1) {
				fileName = new String("高血压.xls".getBytes("UTF-8"), "ISO8859-1");
			}
			fileName = fileName.replaceAll("\\+", "%20");
			response.setHeader("Cache-control", "private");
			response.setHeader("Cache-Control", "maxage=3600");
			response.setHeader("Pragma", "public");
			response.setContentType("application/vnd.ms-excel");
			response.setContentType("application/octet-stream;charset=UTF-8;");
			response.setHeader("Accept-Ranges", "bytes");
			response.setHeader("Content-disposition", "attachment; filename=\"" + fileName + "\"");
			ServletOutputStream out = response.getOutputStream();
			bis = new BufferedInputStream(new FileInputStream(f));
			bos = new BufferedOutputStream(out);
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (bis != null) {
					bis.close();
				}
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (f != null) {
				f.delete();
			}
		}
	}

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

}
