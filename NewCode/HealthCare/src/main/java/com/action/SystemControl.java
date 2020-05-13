package com.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.dao.DataManageMapper;
import com.dto.HealthCenterDto;
import com.dto.HislogDto;
import com.dto.PatientDto;
import com.dto.UserDto;
import com.form.FormModel;
import com.his.HISUtil;
import com.service.AreaService;
import com.service.HealthCenterService;
import com.service.PatientService;
import com.service.UserService;

import util.ConversionUtil;
import util.PageBean;

/**
 * 系统管理
 * 
 * @author Daniel Duan
 * 
 */
@Controller
@RequestMapping(value = "/system")
public class SystemControl {

	public static Logger log = Logger.getLogger(SystemControl.class);

	@Autowired
	private UserService userService;

	@Autowired
	private PatientService patientService;

	@Autowired
	private AreaService areaService;

	@Autowired
	private HealthCenterService healthCenterService;

	@Autowired
	private DataManageMapper dataManageMapper;

	public DataManageMapper getDataManageMapper() {
		return dataManageMapper;
	}

	public void setDataManageMapper(DataManageMapper dataManageMapper) {
		this.dataManageMapper = dataManageMapper;
	}

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	public PatientService getPatientService() {
		return patientService;
	}

	public void setPatientService(PatientService patientService) {
		this.patientService = patientService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public HealthCenterService getHealthCenterService() {
		return healthCenterService;
	}

	public void setHealthCenterService(HealthCenterService healthCenterService) {
		this.healthCenterService = healthCenterService;
	}

	/**
	 * 初始化页面
	 */
	@RequestMapping(value = "/init.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String init(Model model) {
		return "index";
	}

	/**
	 * 登录验证方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login.do", method = { RequestMethod.POST })
	public void login(HttpServletRequest req, HttpServletResponse response, Model model, String userName,
			String password) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			String code = "{code:0}";
			UserDto userDto = new UserDto();
			userDto.setUserName(userName);
			userDto.setUserPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
			userDto = userService.getUser(userDto);
			if (userDto == null) {
				code = "{code:1}";
			} else {
				// 把用户存入session中
				HttpSession session = req.getSession();
				session.setAttribute("user", userDto);
			}
			out = response.getWriter();
			out.print(JSON.parse(code));
		} catch (Exception e) {
			log.error("SystemControl->login报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 退出方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "/logout.do", method = { RequestMethod.POST })
	public void logout(HttpServletRequest req, HttpServletResponse response) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			String code = "{code:0}";
			HttpSession session = req.getSession();
			session.setAttribute("user", null);
			out = response.getWriter();
			out.print(JSON.parse(code));
		} catch (Exception e) {
			log.error("SystemControl->logout报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改密码页面
	 */
	@RequestMapping(value = "/updatePWD.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String updatePWD(Model model, String userName) {
		model.addAttribute("userName", userName);
		return "updatePWD";
	}

	/**
	 * 修改密码方法
	 * 
	 * @return
	 */
	@RequestMapping(value = "/doUpdatePWD.do", method = { RequestMethod.POST })
	public void doUpdatePWD(HttpServletRequest req, HttpServletResponse response, Model model, String password,
			String oldpwd) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			String code = "{code:1}";
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			if (!userDto.getUserPassword().equals(DigestUtils.md5DigestAsHex(oldpwd.getBytes()))) {
				code = "{code:2}";
			} else {
				userDto.setUserPassword(DigestUtils.md5DigestAsHex(password.getBytes()));
				int num = userService.updatePWD(userDto);
				if (num > 0) {
					code = "{code:0}";
				}
			}
			out = response.getWriter();
			out.print(JSON.parse(code));
		} catch (Exception e) {
			log.error("SystemControl->doUpdatePWD报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 区域列表页面
	 */
	@RequestMapping(value = "/healthcenterList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String healthcenterList(Model model) {
		return "system/healthcenterList";
	}

	/**
	 * 获取 区域列表信息
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/getAreaList.do", method = { RequestMethod.GET })
	public void getAreaList(HttpServletRequest req, HttpServletResponse response, Model model, Integer page,
			String name) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("start", (page - 1) * 10);
			param.put("limit", 10);
			if (name != null) {
				name = URLDecoder.decode(name, "UTF-8");
			}
			param.put("name", name);
			// 分页信息
			PageBean<HealthCenterDto> pageBean = new PageBean<HealthCenterDto>();
			pageBean.setTotalCount(healthCenterService.getAreaListCount(param));
			pageBean.setPageList(healthCenterService.getAreaList(param));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", pageBean);
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("SystemControl->getAreaList报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 增加卫生所信息
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/addHealthCenter.do", method = { RequestMethod.POST })
	public void addHealthCenter(HttpServletRequest req, HttpServletResponse response, HealthCenterDto healthCenterDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCenterDto.setName(URLDecoder.decode(healthCenterDto.getName(), "UTF-8"));
			int num = healthCenterService.insertHealthCenter(healthCenterDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("SystemControl->addHealthCenter报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改卫生所信息
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updHealthCenter.do", method = { RequestMethod.POST })
	public void updHealthCenter(HttpServletRequest req, HttpServletResponse response, HealthCenterDto healthCenterDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			healthCenterDto.setName(URLDecoder.decode(healthCenterDto.getName(), "UTF-8"));
			int num = healthCenterService.updHealthCenter(healthCenterDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("SystemControl->updHealthCenter报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 用户列表页面
	 */
	@RequestMapping(value = "/userList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String userList(Model model) {
		return "system/userList";
	}

	/**
	 * 获取用户列表信息
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/getUserList.do", method = { RequestMethod.GET })
	public void getUserList(HttpServletRequest req, HttpServletResponse response, Model model, Integer page,
			String name) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("start", (page - 1) * 10);
			param.put("limit", 10);
			if (name != null) {
				name = URLDecoder.decode(name, "UTF-8");
			}
			param.put("name", name);
			// 分页信息
			PageBean<UserDto> pageBean = new PageBean<UserDto>();
			pageBean.setTotalCount(userService.getUserListCount(param));
			pageBean.setPageList(userService.getUserList(param));

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", pageBean);
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("SystemControl->getUserList报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 获取所有区域列表信息
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/getAllAreaList.do", method = { RequestMethod.GET })
	public void getAllAreaList(HttpServletRequest req, HttpServletResponse response, Model model) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", healthCenterService.getAllAreaList());
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("SystemControl->getAllAreaList报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 增加用户信息
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/addUser.do", method = { RequestMethod.POST })
	public void addUser(HttpServletRequest req, HttpServletResponse response, UserDto userDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			// 获取该用户是否存在
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("name", userDto.getUserName());
			int isExist = userService.getUserCount(map);
			if (isExist > 0) {
				out.print(JSON.parse("{code:2}"));
			} else {
				userDto.setUserPassword(DigestUtils.md5DigestAsHex(userDto.getUserPassword().getBytes()));
				int num = userService.insertUser(userDto);
				if (num > 0) {
					out.print(JSON.parse("{code:0}"));
				} else {
					out.print(JSON.parse("{code:1}"));
				}
			}
		} catch (Exception e) {
			log.error("SystemControl->addUser报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改用户信息
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updUser.do", method = { RequestMethod.POST })
	public void updUser(HttpServletRequest req, HttpServletResponse response, String name, String pwd, Integer hcid,
			int id, String oldName, String province, String city, String district) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			int isExist = 0;
			if (!oldName.equals(name)) {
				// 获取该用户是否存在
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("name", name);
				isExist = userService.getUserCount(map);
			}
			if (isExist > 0) {
				out.print(JSON.parse("{code:2}"));
			} else {
				UserDto userDto = new UserDto();
				userDto.setUserName(URLDecoder.decode(name, "UTF-8"));
				userDto.setUserPassword(DigestUtils.md5DigestAsHex(pwd.getBytes()));
				userDto.setHcid(hcid);
				userDto.setId(id);
				userDto.setProvince(province);
				userDto.setCity(city);
				userDto.setDistrict(district);
				int num = userService.updUser(userDto);
				if (num > 0) {
					out.print(JSON.parse("{code:0}"));
				} else {
					out.print(JSON.parse("{code:1}"));
				}
			}
		} catch (Exception e) {
			log.error("SystemControl->updUser报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 扫描身份证页面
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/toIdScan.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toIdScan(HttpServletRequest req, Model model) {
		Map<Integer, PatientDto> pmap = new HashMap<Integer, PatientDto>();
		Object obj = req.getServletContext().getAttribute("pmap");
		if (obj != null) {
			pmap = (Map<Integer, PatientDto>) obj;
		}
		HttpSession session = req.getSession();
		UserDto userDto = (UserDto) session.getAttribute("user");
		if (userDto != null) {
			Integer hcid = userDto.getHcid();
			pmap.put(hcid, null);
			req.getServletContext().setAttribute("pmap", pmap);
		}
		return "idScan";
	}

	/**
	 * 获取初始化用户信息
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/getPatient.do", method = { RequestMethod.GET })
	public void getPatient(HttpServletRequest req, HttpServletResponse response, String dateTime) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			if (null == dateTime || "".equals(dateTime)) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				dateTime = format.format(new Date());
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("dateTime", dateTime);
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			params.put("hcid", userDto.getHcid());

			out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("dateTime", dateTime);
			List<PatientDto> ps = patientService.getPatientByUUID(params);
			if (ps != null && ps.size() > 0) {
				map.put("datas", ps.get(0));
			}
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("SystemControl->getUUID报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 户口本录入页面
	 */
	@RequestMapping(value = "/idHousehold.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String idHousehold(Model model) {
		return "idHousehold";
	}

	/**
	 * 省数据同步区域选取页面
	 */
	@RequestMapping(value = "/synDataView.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String synDataView(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		UserDto userDto = (UserDto) session.getAttribute("user");
		model.addAttribute("userDto", userDto);
		return "synData/synData1";
	}

	/**
	 * 省数据同步页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/toSynData2.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toSynData2(Model model, String RegionID, String RegionCode, Integer hcid) {
		try {
			FormModel f = healthCenterService.getCenterByid(hcid);
			model.addAttribute("doctors", HISUtil.getDocters(f.getField4(), f.getField3(), f.getField5()));
			model.addAttribute("RegionID", RegionID);
			model.addAttribute("RegionCode", RegionCode);
			model.addAttribute("hcid", hcid);
			model.addAttribute("hname", f.getField2());
		} catch (Exception e) {
			log.error("SystemControl->toSynData2报错:" + e.toString());
		}
		return "synData/synData2";
	}

	/**
	 * 替换字符串
	 * 
	 * @param str
	 * @return
	 */
	private static String replaceDate(String str) {
		if (StringUtils.isEmpty(str)) {
			str = "";
		} else {
			if ("null".equals(str)) {
				str = "";
			} else {
				str = str.replaceAll("\"", "");
			}
		}
		return str;
	}

	/**
	 * 尿液替换字符串 验证尿的值（选项值分别为：-、+-、1+、+、2+、3+、4+）（Api值分别为：-、+-、+、++、+++、++++）
	 * 
	 * @param str
	 * @return
	 */
	private static String replaceUrineData(String str) {
		if (StringUtils.isEmpty(str)) {
			str = "";
		} else {
			if ("null".equals(str)) {
				str = "";
			} else {
				str = str.replaceAll("\"", "");
				if ("1+".equals(str)) {
					str = "+";
				} else if ("2+".equals(str)) {
					str = "++";
				} else if ("3+".equals(str)) {
					str = "+++";
				} else if ("4+".equals(str)) {
					str = "++++";
				}
			}
		}
		return str;
	}

	/**
	 * 根据正则表达式替换字符串
	 * 
	 * @param regEx
	 * @param retult
	 * @return
	 */
	private String replaceReg(String regEx, String retult) {
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(retult);
		retult = m.replaceAll("").trim();
		return retult;
	}

	/**
	 * 传输数据到省平台
	 * 
	 * @return
	 */
	private String sendSynData(HttpServletRequest req, List<FormModel> synData, String RegionID, String RegionCode,
			String hid, String dcode, String dname, HislogDto hislogDto, String productCode, String regionCode) {
		String rmsg = "";
		int sum = 0;
		String regEx = "";
		// 设置进度条
		HttpSession session = req.getSession();
		Map<String, Integer> promap = new HashMap<String, Integer>();
		promap.put("total", synData.size());
		promap.put("num", 0);
		session.setAttribute("SynProgress", promap);
		int pronum = 0;
		for (FormModel f : synData) {
			// 条码号
			String code = f.getField237();
			// 身份证
			String idcard = f.getField1();
			try {
				// 产品编号
				String PRODUCTCODE = productCode;
				// 建档日期
				String BuildDate = f.getField2();
				// 户主姓名
				String MasterName = f.getField3();
				// 性别 0未知的性别,1男,2女,9未说明的性别
				String GenderCode = replaceDate(f.getField4());
				if ("3".equals(GenderCode)) {
					GenderCode = "9";
				} else if ("4".equals(GenderCode)) {
					GenderCode = "0";
				}
				if (StringUtils.isEmpty(GenderCode)) {
					GenderCode = "1";
				}
				// 婚姻状况 1未婚,2已婚,3丧偶,4离婚,5未说明的婚姻状况
				String MarryStatus = replaceDate(f.getField5());
				if (StringUtils.isEmpty(MarryStatus)) {
					MarryStatus = "1";
				}
				// 职业1国家机关 党群组织 企业 事业单位负责人,2专业技术人员,3办事人员和有关人员,4商业 服务业人员,5农 林 牧 渔 水利业生产人员,6生产
				// 运输设备操作人员及有关人员,7军人,8不便分类的其他从业人员,9无职业(选择8后面加上选择的身份的拼音小写，例如:8_xs，表示学生)
				String JobCode = replaceDate(f.getField6());
				if (StringUtils.isEmpty(JobCode)) {
					JobCode = "1";
				}
				// 常驻类型1户籍,2非户籍
				String HukouInd = replaceDate(f.getField7());
				if (StringUtils.isEmpty(HukouInd)) {
					HukouInd = "1";
				}
				// 文化程度 11研究生,12大学本科,13大学专科和专科学校,14中等专业学校,15技工学校,16高中，3初中，2小学，1文盲和半文盲，6不详
				String EducationCode = replaceDate(f.getField8());
				if (StringUtils.isEmpty(EducationCode)) {
					EducationCode = "11";
				}
				if ("1".equals(EducationCode)) {
					EducationCode = "11";
				} else if ("2".equals(EducationCode)) {
					EducationCode = "12";
				} else if ("3".equals(EducationCode)) {
					EducationCode = "13";
				} else if ("4".equals(EducationCode)) {
					EducationCode = "14";
				} else if ("5".equals(EducationCode)) {
					EducationCode = "15";
				} else if ("6".equals(EducationCode)) {
					EducationCode = "16";
				} else if ("7".equals(EducationCode)) {
					EducationCode = "3";
				} else if ("8".equals(EducationCode)) {
					EducationCode = "2";
				} else if ("9".equals(EducationCode)) {
					EducationCode = "1";
				} else if ("10".equals(EducationCode)) {
					EducationCode = "6";
				}
				// 联系人姓名
				String ContactPerson = f.getField9();
				if (StringUtils.isEmpty(ContactPerson)) {
					ContactPerson = "";
				}
				// 生日
				String BirthDay = f.getField10();
				// 本人电话
				String PersonTel = f.getField11();
				if (StringUtils.isEmpty(PersonTel)) {
					PersonTel = "";
				}
				// 联系人电话
				String ContaceTel = f.getField12();
				if (StringUtils.isEmpty(ContaceTel)) {
					ContaceTel = "";
				}
				// 血型 1 A型,2 B型,3 o型,4 AB型, 5 不详
				String BloodType = replaceDate(f.getField13());
				if (StringUtils.isEmpty(BloodType)) {
					BloodType = "1";
				}
				// RH阴性 1 阳性,2 阴性,3 不详
				String RhBlood = replaceDate(f.getField14());
				if (StringUtils.isEmpty(RhBlood)) {
					RhBlood = "2";
				}
				// 医疗费用支付方式 1 城镇职工基本医疗保险,2 城镇居民基本医疗保险,4 新型农村合作医疗8贫困救助，16 商业医疗保险 ，32 全公费 ，64
				// 全自费(选了64就不能选择其它选项了) ，128 其他
				String PaymentWaystring = f.getField15();
				if (StringUtils.isEmpty(PaymentWaystring)) {
					PaymentWaystring = "";
				}
				if (!StringUtils.isEmpty(PaymentWaystring)) {
					String[] pdata = PaymentWaystring.split(",");
					PaymentWaystring = "";
					int num = 0;
					for (String s : pdata) {
						if ("3".equals(s)) {
							s = "4";
						} else if ("4".equals(s)) {
							s = "8";
						} else if ("5".equals(s)) {
							s = "16";
						} else if ("6".equals(s)) {
							s = "32";
						} else if ("7".equals(s)) {
							s = "64";
						} else if ("8".equals(s)) {
							s = "128";
						}
						num = num + Integer.parseInt(s);
					}
					if (num != 0) {
						PaymentWaystring = num + "";
					}
				}
				// 医疗费用其他
				String OtherPaymentWaystring = f.getField16();
				if (StringUtils.isEmpty(OtherPaymentWaystring)) {
					OtherPaymentWaystring = "";
				}
				// 药物过敏史 1 无(选1就不能选择其它选项), 2 青霉素, 4 磺胺, 8 链霉素, 16 其他
				String DrugAllergyHistory = replaceDate(f.getField17());
				if (StringUtils.isEmpty(DrugAllergyHistory)) {
					DrugAllergyHistory = "";
				}
				if (!StringUtils.isEmpty(DrugAllergyHistory)) {
					String[] pdata = DrugAllergyHistory.split(",");
					DrugAllergyHistory = "";
					int num = 0;
					for (String s : pdata) {
						if ("3".equals(s)) {
							s = "4";
						} else if ("4".equals(s)) {
							s = "8";
						} else if ("5".equals(s)) {
							s = "16";
						}
						num = num + Integer.parseInt(s);
					}
					if (num != 0) {
						DrugAllergyHistory = num + "";
					}
				}
				// 暴露史 1 无，2 化学品，4 毒物，8 射线
				String ExposureHistory = replaceDate(f.getField18());
				if (StringUtils.isEmpty(ExposureHistory)) {
					ExposureHistory = "";
				}
				if (!StringUtils.isEmpty(ExposureHistory)) {
					String[] pdata = ExposureHistory.split(",");
					ExposureHistory = "";
					int num = 0;
					for (String s : pdata) {
						if ("3".equals(s)) {
							s = "4";
						} else if ("4".equals(s)) {
							s = "8";
						}
						num = num + Integer.parseInt(s);
					}
					if (num != 0) {
						ExposureHistory = num + "";
					}
				}
				// 残疾情况 1 无(选1就不能选择其它选项),2 视力残疾,4 听力残疾,8 语言残疾, 16 肢体残疾,32 智力残疾,64 精神残疾,128 其他残疾
				String Disability = replaceDate(f.getField19());
				if (StringUtils.isEmpty(Disability)) {
					Disability = "";
				}
				if (!StringUtils.isEmpty(Disability)) {
					String[] pdata = Disability.split(",");
					Disability = "";
					int num = 0;
					for (String s : pdata) {
						if ("3".equals(s)) {
							s = "4";
						} else if ("4".equals(s)) {
							s = "8";
						} else if ("5".equals(s)) {
							s = "16";
						} else if ("6".equals(s)) {
							s = "32";
						} else if ("7".equals(s)) {
							s = "64";
						} else if ("8".equals(s)) {
							s = "128";
						}
						num = num + Integer.parseInt(s);
					}
					if (num != 0) {
						Disability = num + "";
					}
				}
				// 厨房排风设施 1 无(选1就不能选择其它选项), 2 油烟机, 4 换气扇,8 烟囱
				String KitchenExhaust = replaceDate(f.getField20());
				if (StringUtils.isEmpty(KitchenExhaust)) {
					KitchenExhaust = "1";
				}
				if ("3".equals(KitchenExhaust)) {
					KitchenExhaust = "4";
				} else if ("4".equals(KitchenExhaust)) {
					KitchenExhaust = "8";
				}
				// 燃料类型 1 液化气,2 煤,4 天然气,8 沼气,16 柴火,32 其他
				String FuelType = replaceDate(f.getField21());
				if (StringUtils.isEmpty(FuelType)) {
					FuelType = "1";
				}
				if ("3".equals(FuelType)) {
					FuelType = "4";
				} else if ("4".equals(FuelType)) {
					FuelType = "8";
				} else if ("5".equals(FuelType)) {
					FuelType = "16";
				} else if ("6".equals(FuelType)) {
					FuelType = "32";
				}
				// 饮水 1 自来水, 2经净化过滤的水, 4井水, 8河湖水,16 塘水,32 其他
				String Drinkingwater = replaceDate(f.getField22());
				if (StringUtils.isEmpty(Drinkingwater)) {
					Drinkingwater = "1";
				}
				if ("3".equals(Drinkingwater)) {
					Drinkingwater = "4";
				} else if ("4".equals(Drinkingwater)) {
					Drinkingwater = "8";
				} else if ("5".equals(Drinkingwater)) {
					Drinkingwater = "16";
				} else if ("6".equals(Drinkingwater)) {
					Drinkingwater = "32";
				}
				// 厕所 1卫生厕所, 2一格或二格粪池式,3马桶,4露天粪坑,5 简易棚厕
				String Toilet = replaceDate(f.getField23());
				if (StringUtils.isEmpty(Toilet)) {
					Toilet = "1";
				}
				// 禽畜栏 0 无，1 单设,2 室内,3 室外
				String LivestockColumn = replaceDate(f.getField24());
				if (StringUtils.isEmpty(LivestockColumn)) {
					LivestockColumn = "0";
				}
				if (!StringUtils.isEmpty(LivestockColumn)) {
					LivestockColumn = (Integer.parseInt(LivestockColumn) - 1) + "";
				}
				// 根据身份证查询人员id
				String personId = HISUtil.getPersonId(idcard, PRODUCTCODE, regionCode);
				if ("".equals(personId)) {
					// 如果居民不存在就新增居民信息
					personId = HISUtil.addPerson(BuildDate, MasterName, RegionID, idcard, GenderCode, MarryStatus,
							JobCode, HukouInd, EducationCode, ContactPerson, BirthDay, RegionCode, PersonTel,
							ContaceTel, BloodType, RhBlood, PaymentWaystring, OtherPaymentWaystring, DrugAllergyHistory,
							ExposureHistory, Disability, KitchenExhaust, FuelType, Drinkingwater, Toilet,
							LivestockColumn, PRODUCTCODE, regionCode);
				}
				if ("".equals(personId)) {
					rmsg = rmsg + "条码号:" + code + "上传失败;";
				} else {
					// 同步数据
					// 体检日期
					String ExamDate = f.getField25();
					// 症状（1 无症状、2 头痛、4 头晕、8 心悸、 16 胸闷、32 胸痛、
					// 64 慢性咳嗽、128 咳痰、256 呼吸困难、512多饮、1024 多尿、
					// 2048 体重下降、4096 乏力、8192 关节肿痛、
					// 16384 视力模糊、32768 手脚麻木、65536 尿急、
					// 131072 尿痛、262144 便秘、524288 腹泻、
					// 1048576 恶心呕吐、2097152 眼花、4194304 耳鸣、
					// 8388608 乳房胀痛、16777216 其他）
					String Symptom = replaceDate(f.getField26());
					if (!StringUtils.isEmpty(Symptom)) {
						String[] pdata = Symptom.split(",");
						Symptom = "";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							} else if ("5".equals(s)) {
								s = "16";
							} else if ("6".equals(s)) {
								s = "32";
							} else if ("7".equals(s)) {
								s = "64";
							} else if ("8".equals(s)) {
								s = "128";
							} else if ("9".equals(s)) {
								s = "256";
							} else if ("10".equals(s)) {
								s = "512";
							} else if ("11".equals(s)) {
								s = "1024";
							} else if ("12".equals(s)) {
								s = "2048";
							} else if ("13".equals(s)) {
								s = "4096";
							} else if ("14".equals(s)) {
								s = "8192";
							} else if ("15".equals(s)) {
								s = "16384";
							} else if ("16".equals(s)) {
								s = "32768";
							} else if ("17".equals(s)) {
								s = "65536";
							} else if ("18".equals(s)) {
								s = "131072";
							} else if ("19".equals(s)) {
								s = "262144";
							} else if ("20".equals(s)) {
								s = "524288";
							} else if ("21".equals(s)) {
								s = "1048576";
							} else if ("22".equals(s)) {
								s = "2097152";
							} else if ("23".equals(s)) {
								s = "4194304";
							} else if ("24".equals(s)) {
								s = "8388608";
							} else if ("25".equals(s)) {
								s = "16777216";
							}
							num = num + Integer.parseInt(s);
						}
						if (num != 0) {
							Symptom = num + "";
						}
					}
					// 健康评价异常情况
					String AssessmentAbnormal = f.getField27();
					if (!StringUtils.isEmpty(AssessmentAbnormal)) {
						String[] pdata = AssessmentAbnormal.split("，");
						AssessmentAbnormal = "";
						int num = 0;
						for (String s : pdata) {
							if (num == 0) {
								AssessmentAbnormal = s;
								num = 1;
							} else {
								AssessmentAbnormal = AssessmentAbnormal + "\\u0001" + s;
							}
						}
					}
					// 健康指导（1纳入慢性病患者健康管理、2建议复查、4建议转诊）
					String Guidance = f.getField28();
					if (!StringUtils.isEmpty(Guidance)) {
						String[] pdata = Guidance.split(",");
						Guidance = "";
						int num = 0;
						for (String s : pdata) {
							if ("2".equals(s)) {
								s = "1";
							} else if ("3".equals(s)) {
								s = "2";
							} else if ("4".equals(s)) {
								s = "4";
							} else {
								s = "";
							}
							if (!StringUtils.isEmpty(s)) {
								num = num + Integer.parseInt(s);
							}
						}
						if (num != 0) {
							Guidance = num + "";
						}
					}
					// 危险因素控制（1 戒烟 2 健康饮酒 4 饮食 8 锻炼 16 减体重 32 建议接种疫苗 64 其他）
					String RiskCrtl = f.getField29();
					if (!StringUtils.isEmpty(RiskCrtl)) {
						String[] pdata = RiskCrtl.split(",");
						RiskCrtl = "";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							} else if ("5".equals(s)) {
								s = "16";
							} else if ("6".equals(s)) {
								s = "32";
							} else if ("7".equals(s)) {
								s = "64";
							}
							num = num + Integer.parseInt(s);
						}
						if (num != 0) {
							RiskCrtl = num + "";
						}
					}
					// 危险因素控制减重目标KG
					String RiskCrtlWeight = f.getField30();
					if (!StringUtils.isEmpty(RiskCrtlWeight)) {
						RiskCrtlWeight = RiskCrtlWeight.replace("kg", "");
					}
					// 危险因素控制建议疫苗
					String RiskCrtlVaccine = f.getField31();
					if (RiskCrtlVaccine == null) {
						RiskCrtlVaccine = "";
					}
					// 危险因素控制其他
					String RiskCrtlOther = f.getField32();
					// 体温
					String BodyTemperature = replaceDate(f.getField33());
					if (!StringUtils.isEmpty(BodyTemperature)) {
						regEx = "[^0-9.]";
						BodyTemperature = replaceReg(regEx, BodyTemperature);
						if (".".equals(BodyTemperature.substring(BodyTemperature.length() - 1))) {
							BodyTemperature = BodyTemperature.substring(0, BodyTemperature.length() - 1);
						}
					}
					// 脉率（次/分钟，范围为10-200）
					String PulseRate = replaceDate(f.getField34());
					// 心率（次/分钟,范围为10-200）
					String HeartRate = replaceDate(f.getField35());
					// 呼吸频率（次/分钟,范围为5-99）
					String RespiratoryRate = replaceDate(f.getField36());
					// 左侧收缩压
					String LeftSbp = replaceDate(f.getField37());
					// 左侧舒张压
					String LeftDbp = replaceDate(f.getField38());
					// 右侧收缩压
					String RightSbp = replaceDate(f.getField39());
					// 右侧舒张压
					String RightDbp = replaceDate(f.getField40());
					// 身高
					String Height = replaceDate(f.getField41());
					// 体重
					String Weight = replaceDate(f.getField42());
					// 腰围
					String Waistline = replaceDate(f.getField43());
					// 体质指数
					String Bmi = replaceDate(f.getField44());
					if (StringUtils.isEmpty(Bmi)) {
						if (!StringUtils.isEmpty(Height) && !StringUtils.isEmpty(Weight)) {
							Double h = Double.parseDouble(Height);
							Double w = Double.parseDouble(Weight);
							DecimalFormat df = new DecimalFormat("0.00");
							h = h / 100;
							h = h * h;
							Bmi = df.format(w / h);
						}
					}
					// 足背动脉搏动（1 未触及、2 触及双侧对称、4 触及左侧弱或消失、 8 触及右侧弱或消失 ）
					String DorsalisPedisArteryPulse = replaceDate(f.getField45());
					if (!StringUtils.isEmpty(DorsalisPedisArteryPulse)) {
						if ("3".equals(DorsalisPedisArteryPulse)) {
							DorsalisPedisArteryPulse = "4";
						} else if ("4".equals(DorsalisPedisArteryPulse)) {
							DorsalisPedisArteryPulse = "8";
						}
					} else {
						DorsalisPedisArteryPulse = "1";
					}
					// 口唇（1 红润、2 苍白、4 发绀、8 皲裂、16 疱疹）
					String Lips = replaceDate(f.getField46());
					if (!StringUtils.isEmpty(Lips)) {
						if ("3".equals(Lips)) {
							Lips = "4";
						} else if ("4".equals(Lips)) {
							Lips = "8";
						} else if ("5".equals(Lips)) {
							Lips = "16";
						}
					} else {
						Lips = "1";
					}
					// 齿列（1 正常、2 缺齿、4 龋齿、8 义齿(假牙)
					String Dentition = replaceDate(f.getField47());
					if (!StringUtils.isEmpty(Dentition)) {
						String[] pdata = Dentition.split(",");
						Dentition = "";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							}
							num = num + Integer.parseInt(s);
						}
						if (num != 0) {
							Dentition = num + "";
						}
					}
					// 齿列缺齿
					String MissingTeeth = f.getField48();
					if (StringUtils.isEmpty(MissingTeeth)) {
						MissingTeeth = "";
					} else {
						String[] pdata = MissingTeeth.split("，");
						MissingTeeth = "";
						long num = 0;
						int v2 = 1;
						for (String s : pdata) {
							num = num + getTooth(s, v2);
							v2++;
						}
						if (num != 0) {
							MissingTeeth = num + "";
						}
					}
					// 齿列龋齿
					String Caries = f.getField49();
					if (StringUtils.isEmpty(Caries)) {
						Caries = "";
					} else {
						String[] pdata = Caries.split("，");
						Caries = "";
						long num = 0;
						int v2 = 1;
						for (String s : pdata) {
							num = num + getTooth(s, v2);
							v2++;
						}
						if (num != 0) {
							Caries = num + "";
						}
					}
					// 齿列义齿
					String Denture = f.getField50();
					if (StringUtils.isEmpty(Denture)) {
						Denture = "";
					} else {
						String[] pdata = Denture.split("，");
						Denture = "";
						long num = 0;
						int v2 = 1;
						for (String s : pdata) {
							num = num + getTooth(s, v2);
							v2++;
						}
						if (num != 0) {
							Denture = num + "";
						}
					}
					// 咽部（1 无充血、2 充血、4 淋巴滤泡增生）
					String Throat = replaceDate(f.getField51());
					if (!StringUtils.isEmpty(Throat)) {
						if ("3".equals(Throat)) {
							Throat = "4";
						}
					} else {
						Throat = "1";
					}
					// 左眼视力
					String LeftEye = f.getField52();
					if (LeftEye == null) {
						LeftEye = "";
					}
					// 右眼视力
					String RightEye = f.getField53();
					if (RightEye == null) {
						RightEye = "";
					}
					// 左眼矫正视力
					String LeftEyeVc = f.getField54();
					if (LeftEyeVc == null) {
						LeftEyeVc = "";
					}
					// 右眼矫正视力
					String RightEyeVc = f.getField55();
					if (RightEyeVc == null) {
						RightEyeVc = "";
					}
					// 听力（1 听见、2 听不清或无法听见）
					String Hearing = replaceDate(f.getField56());
					if (StringUtils.isEmpty(Hearing)) {
						Hearing = "1";
					}
					// 运动功能（1 可顺利完成、2 无法独立完成任何一个动作）
					String MotorFunction = replaceDate(f.getField57());
					if (StringUtils.isEmpty(MotorFunction)) {
						MotorFunction = "1";
					}
					// 眼底（1 正常、2 异常）
					String Fundus = replaceDate(f.getField58());
					if (StringUtils.isEmpty(Fundus)) {
						Fundus = "1";
					}
					// 皮肤（1 正常、2 潮红、4 苍白、8 发绀、16 黄染、32 色素沉着、64 其他）
					String Skin = replaceDate(f.getField59());
					if (!StringUtils.isEmpty(Skin)) {
						if ("3".equals(Skin)) {
							Skin = "4";
						} else if ("4".equals(Skin)) {
							Skin = "8";
						} else if ("5".equals(Skin)) {
							Skin = "16";
						} else if ("6".equals(Skin)) {
							Skin = "32";
						} else if ("7".equals(Skin)) {
							Skin = "64";
						}
					} else {
						Skin = "1";
					}
					// 巩膜（1 正常、2 黄染、4 充血、8 其他）
					String Sclera = replaceDate(f.getField60());
					if (!StringUtils.isEmpty(Sclera)) {
						if ("3".equals(Sclera)) {
							Sclera = "4";
						} else if ("4".equals(Sclera)) {
							Sclera = "8";
						}
					} else {
						Sclera = "1";
					}
					// 淋巴结（1 未触及、2 锁骨上、4 腋窝、8 其他
					String LymphNodes = replaceDate(f.getField61());
					if (!StringUtils.isEmpty(LymphNodes)) {
						if ("3".equals(LymphNodes)) {
							LymphNodes = "4";
						} else if ("4".equals(LymphNodes)) {
							LymphNodes = "8";
						}
					} else {
						LymphNodes = "1";
					}
					// 肺桶状胸（1 否、2 是）
					String BarrelChest = replaceDate(f.getField62());
					if (StringUtils.isEmpty(BarrelChest)) {
						BarrelChest = "1";
					}
					// 肺呼吸音（1 正常、2 异常）
					String BreathSounds = replaceDate(f.getField63());
					if (StringUtils.isEmpty(BreathSounds)) {
						BreathSounds = "1";
					}
					// 肺罗音（1 无、2 干罗音、4 湿罗音、8 其他）
					String Rale = replaceDate(f.getField64());
					if (!StringUtils.isEmpty(Rale)) {
						if ("3".equals(Rale)) {
							Rale = "4";
						} else if ("4".equals(Rale)) {
							Rale = "8";
						}
					} else {
						Rale = "1";
					}
					// 心脏心律（1 齐、2 不齐、4 绝对不齐）
					String Rhythm = replaceDate(f.getField65());
					if (!StringUtils.isEmpty(Rhythm)) {
						if ("3".equals(Rhythm)) {
							Rhythm = "4";
						}
					} else {
						Rhythm = "1";
					}
					// 心脏杂音（1 无、2 有）
					String HeartMurmur = replaceDate(f.getField66());
					if (StringUtils.isEmpty(HeartMurmur)) {
						HeartMurmur = "1";
					}
					// 腹部压痛（1 无、2 有）
					String AbdominalTenderness = replaceDate(f.getField67());
					if (StringUtils.isEmpty(AbdominalTenderness)) {
						AbdominalTenderness = "1";
					}
					// 腹部包块（1 无、2 有）
					String AbdominalMass = replaceDate(f.getField68());
					if (StringUtils.isEmpty(AbdominalMass)) {
						AbdominalMass = "1";
					}
					// 腹部肝大（1 无、2 有）
					String TheAbdomenLiver = replaceDate(f.getField69());
					if (StringUtils.isEmpty(TheAbdomenLiver)) {
						TheAbdomenLiver = "1";
					}
					// 腹部脾大（1 无、2 有）
					String Splenomegaly = replaceDate(f.getField70());
					if (StringUtils.isEmpty(Splenomegaly)) {
						Splenomegaly = "1";
					}
					// 移动性浊音（1 无、2 有）
					String ShiftingDullness = replaceDate(f.getField71());
					if (StringUtils.isEmpty(ShiftingDullness)) {
						ShiftingDullness = "1";
					}
					// 下肢水肿（1 无、2 单侧、4 双侧不对称、8 双侧对称）
					String LowerExtremityEdema = replaceDate(f.getField72());
					if (!StringUtils.isEmpty(LowerExtremityEdema)) {
						if ("3".equals(LowerExtremityEdema)) {
							LowerExtremityEdema = "4";
						} else if ("4".equals(LowerExtremityEdema)) {
							LowerExtremityEdema = "8";
						}
					} else {
						LowerExtremityEdema = "1";
					}
					// 肛门指诊（1 未及异常、2 触痛、4 包块、8 前列腺异常 、16 其他）
					String Dre = replaceDate(f.getField73());
					if (!StringUtils.isEmpty(Dre)) {
						if ("3".equals(Dre)) {
							Dre = "4";
						} else if ("4".equals(Dre)) {
							Dre = "8";
						} else if ("5".equals(Dre)) {
							Dre = "16";
						}
					} else {
						Dre = "1";
					}
					// 乳腺（1 未见异常、2 乳房切除、4 异常泌乳、8 乳腺包块、16 其他）
					String Breast = replaceDate(f.getField74());
					if (!StringUtils.isEmpty(Breast)) {
						if ("3".equals(Breast)) {
							Breast = "4";
						} else if ("4".equals(Breast)) {
							Breast = "8";
						} else if ("5".equals(Breast)) {
							Breast = "16";
						}
					}
					// 空腹血糖，保存单位mmol/L
					String FastingBloodGlucose = replaceDate(f.getField75());
					// 血红蛋白g/L
					String Hemoglobin = replaceDate(f.getField76());
					// 白细胞×10^9/L
					String Leukocyte = replaceDate(f.getField77());
					// 血小板×10^9/L
					String Platelet = replaceDate(f.getField78());
					// 血常规其他
					String OtherBlood = replaceDate(f.getField79());
					// 尿蛋白（选项值分别为：-、+-、+、++、+++、++++）
					String UrineProtein = replaceUrineData(f.getField80());
					// 尿糖
					String Urine = replaceUrineData(f.getField81());
					// 尿酮体
					String Ketone = replaceUrineData(f.getField82());
					// 尿潜血
					String OccultBloodInUrine = replaceUrineData(f.getField83());
					// 尿常规其他
					String OtherUrine = replaceDate(f.getField84());
					// 尿微量白蛋白
					String UrinaryAlbumin = replaceDate(f.getField85());
					// 大便潜血（1 阴性、2 阳性）
					String FecalOccultBlood = replaceDate(f.getField86());
					// 血清谷丙转氨酶U/L
					String SerumAla = replaceDate(f.getField87());
					// 血清谷草转氨酶U/L
					String SerumAa = replaceDate(f.getField88());
					// 白蛋白g/L
					String Albumin = replaceDate(f.getField89());
					// 总胆红素μmol/L
					String TotalBilirubin = replaceDate(f.getField90());
					if (!ConversionUtil.isDouble(TotalBilirubin)) {
						TotalBilirubin = "";
					}
					// 结合胆红素μmol/L
					String Bilirubin = replaceDate(f.getField91());
					// 血清肌酐μmol/L
					String SerumCreatinine = replaceDate(f.getField92());
					// 血尿素氮mmol/L
					String Bun = replaceDate(f.getField93());
					// 血钾浓度mmol/L
					String PotassiumConcentration = replaceDate(f.getField94());
					// 总胆固醇mmol/L
					String TotalCholesterol = replaceDate(f.getField96());
					// 甘油三酯mmol/L
					String Triglycerides = replaceDate(f.getField97());
					// 血清低密度脂蛋白胆固醇mmol/L
					String LdlCholesterol = replaceDate(f.getField98());
					// 血清高密度脂蛋白胆固醇mmol/L
					String HdlCholesterol = replaceDate(f.getField99());
					// 糖化血红蛋白%
					String GlycatedHemoglobin = replaceDate(f.getField100());
					// 心电图（1 正常 、2 异常）如果存在异常，则保存异常信息，值与信息以\u0001分隔;如（2\u0001血丝）
					String Ecg = replaceDate(f.getField101());
					if (!StringUtils.isEmpty(Ecg)) {
						String[] pdata = Ecg.split(",");
						Ecg = "";
						String ecgStr = "";
						for (String s : pdata) {
							if ("1".equals(s)) {
								ecgStr = "1";
								break;
							} else if ("2".equals(s)) {
								ecgStr = ecgStr + "ST段改变";
							} else if ("3".equals(s)) {
								ecgStr = ecgStr + "陈旧性心肌梗塞";
							} else if ("4".equals(s)) {
								ecgStr = ecgStr + "窦性心动过速";
							} else if ("5".equals(s)) {
								ecgStr = ecgStr + "窦性心动过缓";
							} else if ("6".equals(s)) {
								ecgStr = ecgStr + "早搏";
							} else if ("7".equals(s)) {
								ecgStr = ecgStr + "房颤";
							} else if ("8".equals(s)) {
								ecgStr = ecgStr + "房室传导阻滞";
							} else if ("9".equals(s)) {
								ecgStr = ecgStr + f.getField95();
							}
						}
						if (!"".equals(ecgStr) && !"1".equals(ecgStr)) {
							ecgStr = "2\\u0001" + replaceDate(ecgStr);
						}
						Ecg = ecgStr;
					}
					// 胸部X线片（1 正常 、2 异常）
					String ChestXRay = replaceDate(f.getField102());
					// B超（1 正常 、2 异常） 如果存在异常，则保存异常信息，值与信息以\u0001分隔;如（2\u0001血丝）
					String BUltrasonicWave = replaceDate(f.getField103());
					if (!StringUtils.isEmpty(BUltrasonicWave) && "2".equals(BUltrasonicWave)) {
						BUltrasonicWave = "2\\u0001" + f.getField110();
						if (BUltrasonicWave.length() > 50) {
							BUltrasonicWave = BUltrasonicWave.substring(0, 50);
						}
					}
					// 腹部B超其他（1 正常 、2 异常）如果存在异常，则保存异常信息，值与信息以\u0001分隔;如（2\u0001血丝）
					String BUltrasonicOther = replaceDate(f.getField247());
					if (!StringUtils.isEmpty(BUltrasonicOther) && "2".equals(BUltrasonicOther)) {
						BUltrasonicOther = "2\\u0001" + f.getField248();
						if (BUltrasonicOther.length() > 50) {
							BUltrasonicOther = BUltrasonicOther.substring(0, 50);
						}
					} else {
						BUltrasonicOther = "1";
					}
					// 红细胞×10^9/L
					String Erythrocytes = replaceDate(f.getField104());
					// 白细胞分类计数
					String DifferentialCount = replaceDate(f.getField105());
					// 尿比重
					String Sg = replaceDate(f.getField107());
					// 尿酸碱度
					String Ph = replaceDate(f.getField108());
					// 主要用药情况
					String Drug = "";
					String DrugName1 = replaceDate(f.getField109());
					if (!StringUtils.isEmpty(DrugName1)) {
						String Usage = f.getField106();
						if (!StringUtils.isEmpty(Usage)) {
							if ("1".equals(Usage)) {
								Usage = "口服";
							} else if ("2".equals(Usage)) {
								Usage = "皮下注射";
							} else if ("3".equals(Usage)) {
								Usage = "静脉注射";
							} else if ("4".equals(Usage)) {
								Usage = "肌肉注射";
							}
						}
						String Amount = f.getField111();
						String MedicationTime = f.getField112();
						String MedicationUnit = "";// 单位1年、2月、3天
						String[] strArr = getDrug(MedicationTime);
						MedicationTime = strArr[0];
						MedicationUnit = strArr[1];
						String MedicationCompliance = f.getField113();
						Drug = "{\"DrugName\": \"" + DrugName1 + "\",\r\n" + "        \"Usage\": \"" + Usage + "\",\r\n"
								+ "        \"Amount\": \"" + Amount + "\",\r\n" + "        \"MedicationTime\": \""
								+ MedicationTime + "\",\r\n" + "        \"MedicationUnit\": \"" + MedicationUnit
								+ "\",\r\n" + "        \"MedicationCompliance\": \"" + MedicationCompliance + "\"}";
					}
					String DrugName2 = replaceDate(f.getField114());
					if (!StringUtils.isEmpty(DrugName2)) {
						String Usage = f.getField115();
						if (!StringUtils.isEmpty(Usage)) {
							if ("1".equals(Usage)) {
								Usage = "口服";
							} else if ("2".equals(Usage)) {
								Usage = "皮下注射";
							} else if ("3".equals(Usage)) {
								Usage = "静脉注射";
							} else if ("4".equals(Usage)) {
								Usage = "肌肉注射";
							}
						}
						String Amount = f.getField116();
						String MedicationTime = f.getField117();
						String MedicationUnit = "";// 单位1年、2月、3天
						String[] strArr = getDrug(MedicationTime);
						MedicationTime = strArr[0];
						MedicationUnit = strArr[1];
						String MedicationCompliance = f.getField118();
						if (!"".equals(Drug)) {
							Drug = Drug + ",";
						}
						Drug = Drug + "{\"DrugName\": \"" + DrugName2 + "\",\r\n" + "        \"Usage\": \"" + Usage
								+ "\",\r\n" + "        \"Amount\": \"" + Amount + "\",\r\n"
								+ "        \"MedicationTime\": \"" + MedicationTime + "\",\r\n"
								+ "        \"MedicationUnit\": \"" + MedicationUnit + "\",\r\n"
								+ "        \"MedicationCompliance\": \"" + MedicationCompliance + "\"}";
					}
					String DrugName3 = replaceDate(f.getField119());
					if (!StringUtils.isEmpty(DrugName3)) {
						String Usage = f.getField120();
						if (!StringUtils.isEmpty(Usage)) {
							if ("1".equals(Usage)) {
								Usage = "口服";
							} else if ("2".equals(Usage)) {
								Usage = "皮下注射";
							} else if ("3".equals(Usage)) {
								Usage = "静脉注射";
							} else if ("4".equals(Usage)) {
								Usage = "肌肉注射";
							}
						}
						String Amount = f.getField121();
						String MedicationTime = f.getField122();
						String MedicationUnit = "";// 单位1年、2月、3天
						String[] strArr = getDrug(MedicationTime);
						MedicationTime = strArr[0];
						MedicationUnit = strArr[1];
						String MedicationCompliance = f.getField123();
						if (!"".equals(Drug)) {
							Drug = Drug + ",";
						}
						Drug = Drug + "{\"DrugName\": \"" + DrugName3 + "\",\r\n" + "        \"Usage\": \"" + Usage
								+ "\",\r\n" + "        \"Amount\": \"" + Amount + "\",\r\n"
								+ "        \"MedicationTime\": \"" + MedicationTime + "\",\r\n"
								+ "        \"MedicationUnit\": \"" + MedicationUnit + "\",\r\n"
								+ "        \"MedicationCompliance\": \"" + MedicationCompliance + "\"}";
					}
					String DrugName4 = replaceDate(f.getField124());
					if (!StringUtils.isEmpty(DrugName4)) {
						String Usage = f.getField125();
						if (!StringUtils.isEmpty(Usage)) {
							if ("1".equals(Usage)) {
								Usage = "口服";
							} else if ("2".equals(Usage)) {
								Usage = "皮下注射";
							} else if ("3".equals(Usage)) {
								Usage = "静脉注射";
							} else if ("4".equals(Usage)) {
								Usage = "肌肉注射";
							}
						}
						String Amount = f.getField126();
						String MedicationTime = f.getField127();
						String MedicationUnit = "";// 单位1年、2月、3天
						String[] strArr = getDrug(MedicationTime);
						MedicationTime = strArr[0];
						MedicationUnit = strArr[1];
						String MedicationCompliance = f.getField128();
						if (!"".equals(Drug)) {
							Drug = Drug + ",";
						}
						Drug = Drug + "{\"DrugName\": \"" + DrugName4 + "\",\r\n" + "        \"Usage\": \"" + Usage
								+ "\",\r\n" + "        \"Amount\": \"" + Amount + "\",\r\n"
								+ "        \"MedicationTime\": \"" + MedicationTime + "\",\r\n"
								+ "        \"MedicationUnit\": \"" + MedicationUnit + "\",\r\n"
								+ "        \"MedicationCompliance\": \"" + MedicationCompliance + "\"}";
					}
					String DrugName5 = replaceDate(f.getField129());
					if (!StringUtils.isEmpty(DrugName5)) {
						String Usage = f.getField130();
						if (!StringUtils.isEmpty(Usage)) {
							if ("1".equals(Usage)) {
								Usage = "口服";
							} else if ("2".equals(Usage)) {
								Usage = "皮下注射";
							} else if ("3".equals(Usage)) {
								Usage = "静脉注射";
							} else if ("4".equals(Usage)) {
								Usage = "肌肉注射";
							}
						}
						String Amount = f.getField131();
						String MedicationTime = f.getField132();
						String MedicationUnit = "";// 单位1年、2月、3天
						String[] strArr = getDrug(MedicationTime);
						MedicationTime = strArr[0];
						MedicationUnit = strArr[1];
						String MedicationCompliance = f.getField133();
						if (!"".equals(Drug)) {
							Drug = Drug + ",";
						}
						Drug = Drug + "{\"DrugName\": \"" + DrugName5 + "\",\r\n" + "        \"Usage\": \"" + Usage
								+ "\",\r\n" + "        \"Amount\": \"" + Amount + "\",\r\n"
								+ "        \"MedicationTime\": \"" + MedicationTime + "\",\r\n"
								+ "        \"MedicationUnit\": \"" + MedicationUnit + "\",\r\n"
								+ "        \"MedicationCompliance\": \"" + MedicationCompliance + "\"}";
					}
					String DrugName6 = replaceDate(f.getField134());
					if (!StringUtils.isEmpty(DrugName6)) {
						String Usage = f.getField135();
						if (!StringUtils.isEmpty(Usage)) {
							if ("1".equals(Usage)) {
								Usage = "口服";
							} else if ("2".equals(Usage)) {
								Usage = "皮下注射";
							} else if ("3".equals(Usage)) {
								Usage = "静脉注射";
							} else if ("4".equals(Usage)) {
								Usage = "肌肉注射";
							}
						}
						String Amount = f.getField136();
						String MedicationTime = f.getField137();
						String MedicationUnit = "";// 单位1年、2月、3天
						String[] strArr = getDrug(MedicationTime);
						MedicationTime = strArr[0];
						MedicationUnit = strArr[1];
						String MedicationCompliance = f.getField138();
						if (!"".equals(Drug)) {
							Drug = Drug + ",";
						}
						Drug = Drug + "{\"DrugName\": \"" + DrugName6 + "\",\r\n" + "        \"Usage\": \"" + Usage
								+ "\",\r\n" + "        \"Amount\": \"" + Amount + "\",\r\n"
								+ "        \"MedicationTime\": \"" + MedicationTime + "\",\r\n"
								+ "        \"MedicationUnit\": \"" + MedicationUnit + "\",\r\n"
								+ "        \"MedicationCompliance\": \"" + MedicationCompliance + "\"}";
					}
					// 住院情况
					String Hospt = "";
					String InDate1 = replaceDate(f.getField139());
					if (!StringUtils.isEmpty(InDate1) && !"0000-00-00".equals(InDate1)) {
						String OutDate = f.getField140();
						String Reason = f.getField141();
						String OrgName = f.getField142();
						String MedicalRecordNumber = f.getField143();
						Hospt = "{\"HistoryType\": 1,\r\n" + "        \"InDate\": \"" + InDate1 + "\",\r\n"
								+ "        \"OutDate\": \"" + OutDate + "\",\r\n" + "        \"Reason\": \"" + Reason
								+ "\",\r\n" + "        \"OrgName\": \"" + OrgName + "\",\r\n"
								+ "        \"MedicalRecordNumber\": \"" + MedicalRecordNumber + "\"\r\n" + "    }";
					}
					String InDate2 = replaceDate(f.getField144());
					if (!StringUtils.isEmpty(InDate2) && !"0000-00-00".equals(InDate2)) {
						String OutDate = f.getField145();
						String Reason = f.getField146();
						String OrgName = f.getField147();
						String MedicalRecordNumber = f.getField148();
						if (!"".equals(Hospt)) {
							Hospt = Hospt + ",";
						}
						Hospt = Hospt + "{\"HistoryType\": 1,\r\n" + "        \"InDate\": \"" + InDate2 + "\",\r\n"
								+ "        \"OutDate\": \"" + OutDate + "\",\r\n" + "        \"Reason\": \"" + Reason
								+ "\",\r\n" + "        \"OrgName\": \"" + OrgName + "\",\r\n"
								+ "        \"MedicalRecordNumber\": \"" + MedicalRecordNumber + "\"\r\n" + "    }";
					}
					// 锻炼频率（1 每天、2 每周一次以上、4 偶尔、8 不锻炼）
					String ExerciseFrequency = replaceDate(f.getField149());
					if (!StringUtils.isEmpty(ExerciseFrequency)) {
						if ("3".equals(ExerciseFrequency)) {
							ExerciseFrequency = "4";
						} else if ("4".equals(ExerciseFrequency)) {
							ExerciseFrequency = "8";
						}
					} else {
						ExerciseFrequency = "1";
					}
					// 每次锻炼时间（分钟）
					String EachExerciseTime = replaceDate(f.getField150());
					// 坚持锻炼时间（年）
					String ExerciseTime = replaceDate(f.getField151());
					// 锻炼方式
					String ExerciseMethod = f.getField152();
					if (StringUtils.isEmpty(ExerciseMethod)) {
						ExerciseMethod = "";
					} else {
						ExerciseMethod = ExerciseMethod.replace("\\", "\\\\");
					}
					// 饮食习惯（1 荤素均衡、2 荤食为主、4 素食为主、8 嗜盐、16 嗜油、32 嗜糖）
					String Diet = replaceDate(f.getField153());
					if (!StringUtils.isEmpty(Diet)) {
						if ("3".equals(Diet)) {
							Diet = "4";
						} else if ("4".equals(Diet)) {
							Diet = "8";
						} else if ("5".equals(Diet)) {
							Diet = "16";
						} else if ("6".equals(Diet)) {
							Diet = "32";
						}
					}
					// 吸烟状况（1 从不吸烟、2 已戒烟、4 吸烟）
					String SmokingStatus = f.getField154();
					if (!StringUtils.isEmpty(SmokingStatus)) {
						if ("3".equals(SmokingStatus)) {
							SmokingStatus = "4";
						}
					} else {
						SmokingStatus = "1";
					}
					// 日吸烟量（平均支数）
					String Smoking = replaceDate(f.getField155());
					// 开始吸烟年龄
					String SmokingAge = replaceDate(f.getField156());
					// 戒烟年龄
					String AgeQuit = replaceDate(f.getField157());
					// 饮酒频率（1 从不、2 偶尔、4 经常、8 每天）
					String DrinkingFrequency = replaceDate(f.getField158());
					if (!StringUtils.isEmpty(DrinkingFrequency)) {
						if ("3".equals(DrinkingFrequency)) {
							DrinkingFrequency = "4";
						} else if ("4".equals(DrinkingFrequency)) {
							DrinkingFrequency = "8";
						}
					} else {
						DrinkingFrequency = "1";
					}
					// 日饮酒量（平均两数）
					String DailyAlcoholIntake = replaceDate(f.getField159());
					// 是否戒酒（1 未戒酒、2 已戒酒）
					String IsAlcohol = replaceDate(f.getField160());
					if (StringUtils.isEmpty(IsAlcohol)) {
						IsAlcohol = "";
					}
					// 戒酒年龄
					String AlcoholAge = replaceDate(f.getField161());
					// 开始饮酒年龄
					String AgeStartedDrinking = replaceDate(f.getField162());
					// 近一年内是否曾醉酒（1 是、2 否）
					String IsDrunkLastYear = replaceDate(f.getField163());
					if (StringUtils.isEmpty(IsDrunkLastYear)) {
						IsDrunkLastYear = "1";
					}
					// 饮酒种类（1 白酒、2 啤酒、4 红酒、8 黄酒、16 其他）
					String AlcoholType = replaceDate(f.getField164());
					if (!StringUtils.isEmpty(AlcoholType)) {
						String[] pdata = AlcoholType.split(",");
						AlcoholType = "";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							} else if ("5".equals(s)) {
								s = "16";
							}
							num = num + Integer.parseInt(s);
						}
						if (num != 0) {
							AlcoholType = num + "";
						}
					}
					// 是否职业暴露（1 无、2 有）
					String IsOe = replaceDate(f.getField165());
					if (StringUtils.isEmpty(IsOe)) {
						IsOe = "1";
					}
					// 工种
					String Occupation = f.getField166();
					if (Occupation == null) {
						Occupation = "";
					}
					// 从业时间（年数）
					String WorkingTime = replaceDate(f.getField167());
					// 脑血管疾病（1 未发现、2 缺血性卒中、4 脑出血、8 蛛网膜下腔出血、16 短暂性脑缺血发作、32 其他）
					String Cerebrovascular = replaceDate(f.getField168());
					if (!StringUtils.isEmpty(Cerebrovascular)) {
						String[] pdata = Cerebrovascular.split(",");
						Cerebrovascular = "";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							} else if ("5".equals(s)) {
								s = "16";
							} else if ("6".equals(s)) {
								s = "32";
							}
							num = num + Integer.parseInt(s);
						}
						if (num != 0) {
							Cerebrovascular = num + "";
						}
					} else {
						Cerebrovascular = "1";
					}
					// 肾脏疾病（1 未发现、2 糖尿病肾病、4 肾功能衰竭、8 急性肾炎、16 慢性肾炎、32 其他）
					String Kidney = replaceDate(f.getField169());
					if (!StringUtils.isEmpty(Kidney)) {
						String[] pdata = Kidney.split(",");
						Kidney = "";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							} else if ("5".equals(s)) {
								s = "16";
							} else if ("6".equals(s)) {
								s = "32";
							}
							num = num + Integer.parseInt(s);
						}
						if (num != 0) {
							Kidney = num + "";
						}
					} else {
						Kidney = "1";
					}
					// 心脏疾病（1 未发现、2 心肌梗死、4 心绞痛、8 冠状动脉血运重建、16 充血性心力衰竭、32 心前区疼痛、64 其他）
					String Heart = replaceDate(f.getField170());
					if (!StringUtils.isEmpty(Heart)) {
						String[] pdata = Heart.split(",");
						Heart = "1";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							} else if ("5".equals(s)) {
								s = "16";
							} else if ("6".equals(s)) {
								s = "32";
							} else if ("7".equals(s)) {
								s = "64";
							}
							if (!StringUtils.isEmpty(s)) {
								num = num + Integer.parseInt(s);
							}
						}
						if (num != 0) {
							Heart = num + "";
						}
					} else {
						Heart = "1";
					}
					// 血管疾病（1 未发现、2 夹层动脉瘤、4 动脉闭塞性疾病、8 其他）
					String Vascular = replaceDate(f.getField261());
					if (!StringUtils.isEmpty(Vascular)) {
						String[] pdata = Vascular.split(",");
						Vascular = "1";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							}
							if (!StringUtils.isEmpty(s)) {
								num = num + Integer.parseInt(s);
							}
						}
						if (num != 0) {
							Vascular = num + "";
						}
					} else {
						Vascular = "1";
					}
					// 眼部疾病（1 未发现、2 视网膜出血或渗出、4 视乳头水肿、8 白内障、16 其他）
					String Eyes = replaceDate(f.getField171());
					if (!StringUtils.isEmpty(Eyes)) {
						String[] pdata = Eyes.split(",");
						Eyes = "";
						int num = 0;
						for (String s : pdata) {
							if ("3".equals(s)) {
								s = "4";
							} else if ("4".equals(s)) {
								s = "8";
							} else if ("5".equals(s)) {
								s = "16";
							}
							num = num + Integer.parseInt(s);
						}
						if (num != 0) {
							Eyes = num + "";
						}
					} else {
						Eyes = "1";
					}
					// 神经系统疾病（1 未发现、2 有）
					String Nervoussystems = replaceDate(f.getField172());
					if (!StringUtils.isEmpty(Nervoussystems)) {
						String[] pdata = Nervoussystems.split(",");
						Nervoussystems = "";
						for (String s : pdata) {
							if ("1".equals(s)) {
								Nervoussystems = "1";
							} else {
								Nervoussystems = "2";
							}
							break;
						}
					} else {
						Nervoussystems = "1";
					}
					// 其他系统疾病（1 未发现、2 有）
					String Others = replaceDate(f.getField173());
					if (!StringUtils.isEmpty(Others)) {
						String[] pdata = Others.split(",");
						Others = "1";
						for (String s : pdata) {
							if ("1".equals(s)) {
								Others = "1";
							} else {
								Others = "2";
							}
							break;
						}
					} else {
						Others = "1";
					}
					// 健康状态（1 满意、2 基本满意、4 说不清楚、8 不太满意、16 不满意）
					String Health = replaceDate(f.getField174());
					if (!StringUtils.isEmpty(Health)) {
						if ("3".equals(Health)) {
							Health = "4";
						} else if ("4".equals(Health)) {
							Health = "8";
						} else if ("5".equals(Health)) {
							Health = "16";
						}
					}
					// 认知功能选项（1 粗筛阴性、2 粗筛阳性，简易智力状态检查）
					String CognitiveFunction = replaceDate(f.getField175());
					// 情感状态选项（1 粗筛阴性、2 粗筛阳性，老年人抑郁评分检查）
					String EmotionalState = replaceDate(f.getField176());
					// 生活能力选项（1 可自理（0～3分）、2 轻度依赖（4～8分）、4 中度依赖（9～18分) 、8 不能自理（≥19分））
					String LifeSkillsScore = replaceDate(f.getField177());
					Double lifeScore = 0.0;
					if (!StringUtils.isEmpty(LifeSkillsScore)) {
						lifeScore = Double.parseDouble(LifeSkillsScore);
					}
					// 生活能力得分
					LifeSkillsScore = lifeScore + "";
					// 老年人自理能力评估1可自理2轻度依赖4中度依赖8不能自理
					String LifeSkills = replaceDate(f.getField249());
					if (!StringUtils.isEmpty(LifeSkills)) {
						if ("3".equals(LifeSkills)) {
							LifeSkills = "4";
						} else if ("4".equals(LifeSkills)) {
							LifeSkills = "8";
						}
					}
					// 气虚质（是：1，倾向是：2，否则为0）
					String QualityDeficiency = f.getField178();
					if (!StringUtils.isEmpty(QualityDeficiency)) {
						String[] pdata = QualityDeficiency.split(",");
						QualityDeficiency = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								QualityDeficiency = s;
							}
						}
					} else {
						QualityDeficiency = "0";
					}
					// 阳虚质（是：1，倾向是：2，否则为0）
					String YangQuality = f.getField179();
					if (!StringUtils.isEmpty(YangQuality)) {
						String[] pdata = YangQuality.split(",");
						YangQuality = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								YangQuality = s;
							}
						}
					} else {
						YangQuality = "0";
					}
					// 阴虚质（是：1，倾向是：2，否则为0）
					String YinQuality = f.getField180();
					if (!StringUtils.isEmpty(YinQuality)) {
						String[] pdata = YinQuality.split(",");
						YinQuality = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								YinQuality = s;
							}
						}
					} else {
						YinQuality = "0";
					}
					// 痰湿质（是：1，倾向是：2，否则为0）
					String Phlegm = f.getField181();
					if (!StringUtils.isEmpty(Phlegm)) {
						String[] pdata = Phlegm.split(",");
						Phlegm = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								Phlegm = s;
							}
						}
					} else {
						Phlegm = "0";
					}
					// 湿热质（是：1，倾向是：2，否则为0）
					String DampHeat = f.getField182();
					if (!StringUtils.isEmpty(DampHeat)) {
						String[] pdata = DampHeat.split(",");
						DampHeat = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								DampHeat = s;
							}
						}
					} else {
						DampHeat = "0";
					}
					// 血瘀质（是：1，倾向是：2，否则为0）
					String BloodQuality = f.getField183();
					if (!StringUtils.isEmpty(BloodQuality)) {
						String[] pdata = BloodQuality.split(",");
						BloodQuality = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								BloodQuality = s;
							}
						}
					} else {
						BloodQuality = "0";
					}
					// 气郁质（是：1，倾向是：2，否则为0）
					String QiQuality = f.getField184();
					if (!StringUtils.isEmpty(QiQuality)) {
						String[] pdata = QiQuality.split(",");
						QiQuality = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								QiQuality = s;
							}
						}
					} else {
						QiQuality = "0";
					}
					// 特秉质（是：1，倾向是：2，否则为0）
					String TeBingQuality = f.getField185();
					if (!StringUtils.isEmpty(TeBingQuality)) {
						String[] pdata = TeBingQuality.split(",");
						TeBingQuality = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								TeBingQuality = s;
							}
						}
					} else {
						TeBingQuality = "0";
					}
					// 平和质（是：1，基本是：2，否则为0）
					String ModerateQuality = f.getField186();
					if (!StringUtils.isEmpty(ModerateQuality)) {
						String[] pdata = ModerateQuality.split(",");
						ModerateQuality = "0";
						for (String s : pdata) {
							if ("1".equals(s) || "2".equals(s)) {
								ModerateQuality = s;
							}
						}
					} else {
						ModerateQuality = "0";
					}
					// （1）您精力充沛吗？（指精神头足，乐于做事）:1没有2很少3有时4经常5总是
					String A01 = f.getField187();
					// （2）您容易疲乏吗？（指体力如何，是否稍微活动一下或做一点家务劳动就感到累）:1没有2很少3有时4经常5总是
					String A02 = f.getField188();
					// （3）您容易气短，呼吸短促，接不上气吗？:1没有2很少3有时4经常5总是
					String A03 = f.getField189();
					// （4）您说话声音低弱无力吗？（指说话没有力气）:1没有2很少3有时4经常5总是
					String A04 = f.getField190();
					// （5）您感到闷闷不乐、情绪低沉吗？（指心情不愉快，情绪低落）:1没有2很少3有时4经常5总是
					String A05 = f.getField191();
					// （6）您容易精神紧张、焦虑不安吗？（指遇事是否心情紧张）:1没有2很少3有时4经常5总是
					String A06 = f.getField192();
					// （7）您因为生活状态改变而感到孤独、失落吗？:1没有2很少3有时4经常5总是
					String A07 = f.getField193();
					// （8）您容易感到害怕或受到惊吓吗？:1没有2很少3有时4经常5总是
					String A08 = f.getField194();
					// （9）您感到身体超重不轻松吗？（感觉身体沉重）:1没有2很少3有时4经常5总是
					String A09 = f.getField195();
					// （10）您眼睛干涩吗？:1没有2很少3有时4经常5总是
					String A10 = f.getField196();
					// （11）您手脚发凉吗？（不包含因周围温度低或穿的少导致的手脚发冷）:1没有2很少3有时4经常5总是
					String A11 = f.getField197();
					// （12）您胃脘部、背部或腰膝部怕冷吗？（指上腹部、背部、腰部或膝关节等，有一处或多处怕冷）:1没有2很少3有时4经常5总是
					String A12 = f.getField198();
					// （13）您比一般人耐受不了寒冷吗？（指比别人容易害怕冬天或是夏天的冷空调、电扇等）:1没有2很少3有时4经常5总是
					String A13 = f.getField199();
					// （14）您容易感冒吗？（指每年感冒的次数）:1没有2很少3有时4经常5总是
					String A14 = f.getField200();
					// （15）您没有感冒时也会鼻塞、流鼻涕吗？:1没有2很少3有时4经常5总是
					String A15 = f.getField201();
					// （16）您有口粘口腻，或睡眠打鼾吗？:1没有2很少3有时4经常5总是
					String A16 = f.getField202();
					// （17）您容易过敏（对药物、食物、气味、花粉或在季节交替、气候变化时）吗？:1没有2很少3有时4经常5总是
					String A17 = f.getField203();
					// （18）您的皮肤容易起荨麻疹吗？（包括风团、风疹块、风疙瘩）:1没有2很少3有时4经常5总是
					String A18 = f.getField204();
					// （19）您的皮肤在不知不觉中会出现青紫瘀块、皮下出血吗？（指皮肤在没有外伤的情况下出现青一块紫一块的情况）:1没有2很少3有时4经常5总是
					String A19 = f.getField205();
					// （20）您的皮肤一抓就红，并出现抓痕吗？（指被指甲或钝物划过后皮肤的反应）:1没有2很少3有时4经常5总是
					String A20 = f.getField206();
					// （21）您皮肤或口唇干吗？:1没有2很少3有时4经常5总是
					String A21 = f.getField207();
					// （22）您有肢体麻木或固定部位疼痛的感觉吗？:1没有2很少3有时4经常5总是
					String A22 = f.getField208();
					// （23）您面部或鼻部有油腻感或者油亮发光吗？（指脸上或鼻子）:1没有2很少3有时4经常5总是
					String A23 = f.getField209();
					// （24）您面色或目眶晦暗，或出现褐色斑块/斑点吗？:1没有2很少3有时4经常5总是
					String A24 = f.getField210();
					// （25）您有皮肤湿疹、疮疖吗？:1没有2很少3有时4经常5总是
					String A25 = f.getField211();
					// （26）您感到口干咽燥、总想喝水吗？:1没有2很少3有时4经常5总是
					String A26 = f.getField212();
					// （27）您感到口苦或嘴里有异味吗？(指口苦或口臭):1没有2很少3有时4经常5总是
					String A27 = f.getField213();
					// （28）您腹部肥大吗？（指腹部脂肪肥厚）:1没有2很少3有时4经常5总是
					String A28 = f.getField214();
					// （29）您吃(喝)凉的东西会感到不舒服或者怕吃(喝)凉的东西吗？(指不喜欢吃凉的食物，或吃了凉的食物后会不舒服) :1没有2很少3有时4经常5总是
					String A29 = f.getField215();
					// （30）您有大便粘滞不爽、解不尽的感觉吗？（大便容易粘在马桶或便坑壁上）:1没有2很少3有时4经常5总是
					String A30 = f.getField216();
					// （31）您容易大便干燥吗？:1没有2很少3有时4经常5总是
					String A31 = f.getField217();
					// （32）您舌苔厚腻或有舌苔厚厚的感觉吗？（如果自我感觉不清楚可由调查员观察后填写）:1没有2很少3有时4经常5总是
					String A32 = f.getField218();
					// （33）您舌下静脉瘀紫或增粗吗？(可以调查员辅助观察后填写):1没有2很少3有时4经常5总是
					String A33 = f.getField219();
					// 其他
					String Other = "";
					// 症状其他
					String field220 = f.getField220();
					if (!StringUtils.isEmpty(field220)) {
						Other = "{\r\n" + "        \"AttrName\": \"Symptom\",\r\n" + "        \"OtherText\": \""
								+ field220 + "\"\r\n" + "    },";
					}
					// 脑血管疾病其他
					String field221 = f.getField221();
					if (!StringUtils.isEmpty(field221)) {
						Other = Other + "{\r\n" + "        \"AttrName\": \"Cerebrovascular\",\r\n"
								+ "        \"OtherText\": \"" + field221 + "\"\r\n" + "    },";
					}
					// 肾脏疾病其他
					String field222 = f.getField222();
					if (!StringUtils.isEmpty(field222)) {
						Other = Other + "{\r\n" + "        \"AttrName\": \"Kidney\",\r\n" + "        \"OtherText\": \""
								+ field222 + "\"\r\n" + "    },";
					}
					// 心脏疾病其他
					String field223 = f.getField223();
					if (!StringUtils.isEmpty(field223)) {
						Other = Other + "{\r\n" + "        \"AttrName\": \"Heart\",\r\n" + "        \"OtherText\": \""
								+ field223 + "\"\r\n" + "    },";
					}
					// 血管疾病其他
					String field262 = f.getField262();
					if (!StringUtils.isEmpty(field262)) {
						Other = Other + "{\r\n" + "        \"AttrName\": \"Vascular\",\r\n"
								+ "        \"OtherText\": \"" + field262 + "\"\r\n" + "    },";
					}
					// 眼部疾病其他
					String field224 = f.getField224();
					if (!StringUtils.isEmpty(field224)) {
						Other = Other + "{\r\n" + "        \"AttrName\": \"Eyes\",\r\n" + "        \"OtherText\": \""
								+ field224 + "\"\r\n" + "    },";
					}
					// 神经系统疾病其他
					String field225 = f.getField225();
					if (!StringUtils.isEmpty(field225)) {
						Other = Other + "{\r\n" + "        \"AttrName\": \"Nervoussystems\",\r\n"
								+ "        \"OtherText\": \"" + field225 + "\"\r\n" + "    },";
					}
					// 其他系统疾病其他
					String field226 = f.getField226();
					if (!StringUtils.isEmpty(field226)) {
						Other = Other + "{\r\n" + "        \"AttrName\": \"Others\",\r\n" + "        \"OtherText\": \""
								+ field226 + "\"\r\n" + "    },";
					}
					// 饮酒种类其他
					String field227 = f.getField227();
					if (!StringUtils.isEmpty(field227)) {
						Other = Other + "{\r\n" + "        \"AttrName\": \"AlcoholType\",\r\n"
								+ "        \"OtherText\": \"" + field227 + "\"\r\n" + "    },";
					}
					if (!"".equals(Other)) {
						Other = Other.substring(0, Other.length() - 1);
					}
					// 妇科外阴1未见异常2异常 如果存在异常，则保存异常信息，值与信息以\\u0001分隔;如（2\\u0001不懂1）
					String Vulva = replaceDate(f.getField250());
					if (!StringUtils.isEmpty(Vulva)) {
						if ("2".equals(Vulva)) {
							Vulva = Vulva + "\\u0001" + replaceDate(f.getField256());
						}
					}
					// 妇科阴道1未见异常2异常
					String Vaginal = replaceDate(f.getField251());
					if (!StringUtils.isEmpty(Vaginal)) {
						if ("2".equals(Vaginal)) {
							Vaginal = Vaginal + "\\u0001" + replaceDate(f.getField257());
						}
					}
					// 妇科宫颈1未见异常2异常
					String Cervix = replaceDate(f.getField252());
					if (!StringUtils.isEmpty(Cervix)) {
						if ("2".equals(Cervix)) {
							Cervix = Cervix + "\\u0001" + replaceDate(f.getField258());
						}
					}
					// 妇科宫体1未见异常2异常
					String Palace = replaceDate(f.getField253());
					if (!StringUtils.isEmpty(Palace)) {
						if ("2".equals(Palace)) {
							Palace = Palace + "\\u0001" + replaceDate(f.getField259());
						}
					}
					// 妇科附件1未见异常2异常
					String UterineAdnexa = replaceDate(f.getField254());
					if (!StringUtils.isEmpty(UterineAdnexa)) {
						if ("2".equals(UterineAdnexa)) {
							UterineAdnexa = UterineAdnexa + "\\u0001" + replaceDate(f.getField260());
						}
					}
					// 妇科其他
					String WOther = replaceDate(f.getField255());

					// 中医体质结果
					// 多选就加起来 ,比如我选了1，3,5 就加起来 2的0次方+2的2次方+2的4次方=21 Integer.parseInt(calculate(n) +
					// "");
					int TcHealthGuide = 0;
					int tcmNum = 0;
					// 气虚质
					int identity_Qi = 0;
					String field228 = f.getField228();
					if (!StringUtils.isEmpty(field228)) {
						identity_Qi = getTcHealthGuides(field228);
						String field238 = f.getField238();
						if (ConversionUtil.isDouble(field238)) {
							if (tcmNum < Integer.parseInt(field238)) {
								tcmNum = Integer.parseInt(field238);
								TcHealthGuide = identity_Qi;
							}
						}
					}
					// 阳虚质
					int identity_Yang = 0;
					String field229 = f.getField229();
					if (!StringUtils.isEmpty(field229)) {
						identity_Yang = getTcHealthGuides(field229);
						String field239 = f.getField239();
						if (ConversionUtil.isDouble(field239)) {
							if (tcmNum < Integer.parseInt(field239)) {
								tcmNum = Integer.parseInt(field239);
								TcHealthGuide = identity_Yang;
							}
						}
					}
					// 阴虚质
					int identity_Yin = 0;
					String field230 = f.getField230();
					if (!StringUtils.isEmpty(field230)) {
						identity_Yin = getTcHealthGuides(field230);
						String field240 = f.getField240();
						if (ConversionUtil.isDouble(field240)) {
							if (tcmNum < Integer.parseInt(field240)) {
								tcmNum = Integer.parseInt(field240);
								TcHealthGuide = identity_Yin;
							}
						}
					}
					// 痰湿质
					int identity_Phlegm = 0;
					String field231 = f.getField231();
					if (!StringUtils.isEmpty(field231)) {
						identity_Phlegm = getTcHealthGuides(field231);
						String field241 = f.getField241();
						if (ConversionUtil.isDouble(field241)) {
							if (tcmNum < Integer.parseInt(field241)) {
								tcmNum = Integer.parseInt(field241);
								TcHealthGuide = identity_Phlegm;
							}
						}
					}
					// 湿热质
					int identity_Hot = 0;
					String field232 = f.getField232();
					if (!StringUtils.isEmpty(field232)) {
						identity_Hot = getTcHealthGuides(field232);
						String field242 = f.getField242();
						if (ConversionUtil.isDouble(field242)) {
							if (tcmNum < Integer.parseInt(field242)) {
								tcmNum = Integer.parseInt(field242);
								TcHealthGuide = identity_Hot;
							}
						}
					}
					// 血瘀质
					int identity_Blood = 0;
					String field233 = f.getField233();
					if (!StringUtils.isEmpty(field233)) {
						identity_Blood = getTcHealthGuides(field233);
						String field243 = f.getField243();
						if (ConversionUtil.isDouble(field243)) {
							if (tcmNum < Integer.parseInt(field243)) {
								tcmNum = Integer.parseInt(field243);
								TcHealthGuide = identity_Blood;
							}
						}
					}
					// 气郁质
					int identity_StQi = 0;
					String field234 = f.getField234();
					if (!StringUtils.isEmpty(field234)) {
						identity_StQi = getTcHealthGuides(field234);
						String field244 = f.getField244();
						if (ConversionUtil.isDouble(field244)) {
							if (tcmNum < Integer.parseInt(field244)) {
								tcmNum = Integer.parseInt(field244);
								TcHealthGuide = identity_StQi;
							}
						}
					}
					// 特禀质
					int identity_Specific = 0;
					String field235 = f.getField235();
					if (!StringUtils.isEmpty(field235)) {
						identity_Specific = getTcHealthGuides(field235);
						String field245 = f.getField245();
						if (ConversionUtil.isDouble(field245)) {
							if (tcmNum < Integer.parseInt(field245)) {
								tcmNum = Integer.parseInt(field245);
								TcHealthGuide = identity_Specific;
							}
						}
					}
					// 平和质
					int identity_Peaceful = 0;
					String field236 = f.getField236();
					if (!StringUtils.isEmpty(field236)) {
						identity_Peaceful = getTcHealthGuides(field236);
						String field246 = f.getField246();
						if (ConversionUtil.isDouble(field246)) {
							if (tcmNum < Integer.parseInt(field246)) {
								tcmNum = Integer.parseInt(field246);
								TcHealthGuide = identity_Peaceful;
							}
						}
					}
					String TcHealthGuides = "'{\"identity_Qi\":\"" + identity_Qi + "\",\"identity_Yang\":\""
							+ identity_Yang + "\",\"identity_Yin\":\"" + identity_Yin + "\",\"identity_Phlegm\":\""
							+ identity_Phlegm + "\",\"identity_Hot\":\"" + identity_Hot + "\",\"identity_Blood\":\""
							+ identity_Blood + "\",\"identity_StQi\":\"" + identity_StQi + "\",\"identity_Specific\":\""
							+ identity_Specific + "\",\"identity_Peaceful\":\"" + identity_Peaceful + "\"}'";
					Map<String, Object> rmap = HISUtil.upPersonData(personId, ExamDate, Symptom, AssessmentAbnormal,
							Guidance, RiskCrtl, RiskCrtlWeight, RiskCrtlVaccine, RiskCrtlOther, dcode, dname,
							BodyTemperature, PulseRate, HeartRate, RespiratoryRate, LeftSbp, LeftDbp, RightSbp,
							RightDbp, Height, Weight, Waistline, Bmi, DorsalisPedisArteryPulse, Lips, Dentition,
							MissingTeeth, Caries, Denture, Throat, LeftEye, RightEye, LeftEyeVc, RightEyeVc, Hearing,
							MotorFunction, Fundus, Skin, Sclera, LymphNodes, BarrelChest, BreathSounds, Rale, Rhythm,
							HeartMurmur, AbdominalTenderness, AbdominalMass, TheAbdomenLiver, Splenomegaly,
							ShiftingDullness, LowerExtremityEdema, Dre, Breast, FastingBloodGlucose, Hemoglobin,
							Leukocyte, Platelet, OtherBlood, UrineProtein, Urine, Ketone, OccultBloodInUrine,
							OtherUrine, UrinaryAlbumin, FecalOccultBlood, SerumAla, SerumAa, Albumin, TotalBilirubin,
							Bilirubin, SerumCreatinine, Bun, PotassiumConcentration, TotalCholesterol, Triglycerides,
							LdlCholesterol, HdlCholesterol, GlycatedHemoglobin, Ecg, ChestXRay, BUltrasonicOther,
							Erythrocytes, DifferentialCount, Sg, Ph, Drug, Hospt, ExerciseFrequency, EachExerciseTime,
							ExerciseTime, ExerciseMethod, Diet, SmokingStatus, Smoking, SmokingAge, AgeQuit,
							DrinkingFrequency, DailyAlcoholIntake, IsAlcohol, AlcoholAge, AgeStartedDrinking,
							IsDrunkLastYear, AlcoholType, IsOe, Occupation, WorkingTime, Cerebrovascular, Kidney, Heart,
							Vascular, Eyes, Nervoussystems, Others, Health, LifeSkills, LifeSkillsScore,
							CognitiveFunction, EmotionalState, ModerateQuality, QualityDeficiency, YangQuality,
							YinQuality, Phlegm, DampHeat, BloodQuality, QiQuality, TeBingQuality, A01, A02, A03, A04,
							A05, A06, A07, A08, A09, A10, A11, A12, A13, A14, A15, A16, A17, A18, A19, A20, A21, A22,
							A23, A24, A25, A26, A27, A28, A29, A30, A31, A32, A33, Other, TcHealthGuides, PRODUCTCODE,
							regionCode, TcHealthGuide, BUltrasonicWave, Vulva, Vaginal, Cervix, Palace, UterineAdnexa,
							WOther, f.getField264());
					if (!(Boolean) rmap.get("result")) {
						rmsg = rmsg + "条码号:" + code + "上传失败;";
					} else {
						sum++;
					}
					// 修改体检信息
					if (StringUtils.isEmpty(f.getField264()) && (null != rmap.get("mtid"))) {
						rmap.put("pid", f.getField263());
						patientService.updPatientMt(rmap);
					}
				}
			} catch (Exception e) {
				rmsg = rmsg + "条码号:" + code + "上传失败;";
				log.error("SystemControl->synData报错:" + code, e);
			}
			pronum++;
			promap.put("num", pronum);
			session.setAttribute("SynProgress", promap);
		}
		if (synData.size() > 0) {
			if (hislogDto == null) {// 新增数据
				hislogDto = new HislogDto();
				hislogDto.setHcid(Integer.parseInt(hid));
				hislogDto.setRegionid(RegionID);
				hislogDto.setRegioncode(RegionCode);
				hislogDto.setDcode(dcode);
				hislogDto.setDname(dname);
				hislogDto.setSucnum(sum);
				hislogDto.setErrnum(synData.size() - sum);
				hislogDto.setErromsg(rmsg);
				userService.insertHislog(hislogDto);
			} else {// 修改数据
				hislogDto.setSucnum(hislogDto.getSucnum() + sum);
				hislogDto.setErrnum(synData.size() - sum);
				hislogDto.setErromsg(rmsg);
				userService.updateHislog(hislogDto);
			}
		}
		rmsg = "一共" + synData.size() + "条信息,成功" + sum + "条信息,失败" + (synData.size() - sum) + "条信息!";
		return rmsg;
	}

	/**
	 * 同步数据
	 * 
	 * @return
	 */
	@RequestMapping(value = "/synData.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void synData(HttpServletRequest req, HttpServletResponse response, Model model, String startDate,
			String endDate, Integer hcid, String dcode, String dname, String idCard, String RegionID,
			String RegionCode) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			if (dname != null) {
				dname = URLDecoder.decode(dname, "UTF-8");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			// 开始日期
			params.put("startDate", startDate + " 00:00:00");
			// 结束日期
			params.put("endDate", endDate + " 23:59:59");
			// 卫生院
			params.put("hid", hcid);
			// 身份证号码
			params.put("idCard", idCard);
			// 获取同步数据信息
			List<FormModel> synData = userService.getSynData(params);
			if (synData.size() > 0) {
				System.out.println("总共" + synData.size() + "条数据....");
			}
			// 根据卫生院id获取相关信息
			FormModel f = healthCenterService.getCenterByid(hcid);
			// 传输数据到省平台
			String rmsg = sendSynData(req, synData, RegionID, RegionCode, f.getField1(), dcode, dname, null,
					f.getField3(), f.getField5());
			out = response.getWriter();
			out.print(JSON.parse("{rmsg:'" + rmsg + "'}"));
		} catch (Exception e) {
			log.error("SystemControl->synData报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	private String[] getDrug(String MedicationTime) {
		String[] rstr = new String[2];
		String MedicationUnit = "";
		if (!StringUtils.isEmpty(MedicationTime)) {
			if ("1".equals(MedicationTime)) {
				MedicationTime = "7";
				MedicationUnit = "3";
			} else if ("2".equals(MedicationTime)) {
				MedicationTime = "14";
				MedicationUnit = "3";
			} else if ("3".equals(MedicationTime)) {
				MedicationTime = "21";
				MedicationUnit = "3";
			} else if ("4".equals(MedicationTime)) {
				MedicationTime = "1";
				MedicationUnit = "2";
			} else if ("5".equals(MedicationTime)) {
				MedicationTime = "2";
				MedicationUnit = "2";
			} else if ("6".equals(MedicationTime)) {
				MedicationTime = "3";
				MedicationUnit = "2";
			} else if ("7".equals(MedicationTime)) {
				MedicationTime = "4";
				MedicationUnit = "2";
			} else if ("8".equals(MedicationTime)) {
				MedicationTime = "5";
				MedicationUnit = "2";
			} else if ("9".equals(MedicationTime)) {
				MedicationTime = "6";
				MedicationUnit = "2";
			} else if ("10".equals(MedicationTime)) {
				MedicationTime = "7";
				MedicationUnit = "2";
			} else if ("11".equals(MedicationTime)) {
				MedicationTime = "8";
				MedicationUnit = "2";
			} else if ("12".equals(MedicationTime)) {
				MedicationTime = "9";
				MedicationUnit = "2";
			} else if ("13".equals(MedicationTime)) {
				MedicationTime = "10";
				MedicationUnit = "2";
			} else if ("14".equals(MedicationTime)) {
				MedicationTime = "11";
				MedicationUnit = "2";
			} else if ("15".equals(MedicationTime)) {
				MedicationTime = "1";
				MedicationUnit = "1";
			} else if ("16".equals(MedicationTime)) {
				MedicationTime = "2";
				MedicationUnit = "1";
			} else if ("17".equals(MedicationTime)) {
				MedicationTime = "3";
				MedicationUnit = "1";
			} else if ("18".equals(MedicationTime)) {
				MedicationTime = "4";
				MedicationUnit = "1";
			} else if ("19".equals(MedicationTime)) {
				MedicationTime = "5";
				MedicationUnit = "1";
			}
		} else {
			MedicationTime = "";
		}
		rstr[0] = MedicationTime;
		rstr[1] = MedicationUnit;
		return rstr;
	}

	private long calculate(int n) {
		if (n == 0)
			return 1;
		return 2 * calculate(n - 1);
	}

	private int getTcHealthGuides(String str) {
		long num = 0;
		String[] pdata = str.split(",");
		for (String s : pdata) {
			// 1是2倾向是3情志调摄4饮食调养5起居调摄6运动保健7穴位保健
			if ("3".equals(s)) {
				num = num + Integer.parseInt(calculate(0) + "");
			} else if ("4".equals(s)) {
				num = num + Integer.parseInt(calculate(1) + "");
			} else if ("5".equals(s)) {
				num = num + Integer.parseInt(calculate(2) + "");
			} else if ("6".equals(s)) {
				num = num + Integer.parseInt(calculate(3) + "");
			} else if ("7".equals(s)) {
				num = num + Integer.parseInt(calculate(4) + "");
			}
		}
		return Integer.parseInt(num + "");
	}

	/**
	 * 计算牙齿的值
	 * 
	 * @param v1 值
	 * @param v2 第几组
	 * @return
	 */
	private long getTooth(String s, int v2) {
		long sum = 0;
		char[] vs = s.toCharArray();
		for (char c : vs) {
			int v1 = Character.getNumericValue(c);
			long num = 0;
			int n = -1;
			// 第一组1~8的分别为:2的[24~31]次方的值，比如1为2的24次方，8则是2的31次方；
			if (1 == v2) {
				if (v1 == 1) {
					n = 24;
				} else if (v1 == 2) {
					n = 25;
				} else if (v1 == 3) {
					n = 26;
				} else if (v1 == 4) {
					n = 27;
				} else if (v1 == 5) {
					n = 28;
				} else if (v1 == 6) {
					n = 29;
				} else if (v1 == 7) {
					n = 30;
				} else if (v1 == 8) {
					n = 31;
				}
			} else if (2 == v2) {// 第二组1~8的分别为:2的[23~16]次方的值，比如1为2的23次方，8则是2的16次方；
				n = -1;
				if (v1 == 1) {
					n = 23;
				} else if (v1 == 2) {
					n = 22;
				} else if (v1 == 3) {
					n = 21;
				} else if (v1 == 4) {
					n = 20;
				} else if (v1 == 5) {
					n = 19;
				} else if (v1 == 6) {
					n = 18;
				} else if (v1 == 7) {
					n = 17;
				} else if (v1 == 8) {
					n = 16;
				}
			} else if (3 == v2) {// 第三组1~8的分别为:2的[8~15]次方的值，比如1为2的8次方，8则是2的15次方；
				n = -1;
				if (v1 == 1) {
					n = 8;
				} else if (v1 == 2) {
					n = 9;
				} else if (v1 == 3) {
					n = 10;
				} else if (v1 == 4) {
					n = 11;
				} else if (v1 == 5) {
					n = 12;
				} else if (v1 == 6) {
					n = 13;
				} else if (v1 == 7) {
					n = 14;
				} else if (v1 == 8) {
					n = 15;
				}
			} else if (4 == v2) {// 第四组1~8的分别为:2的[7~0]次方的值，比如1为2的7次方，8则是2的0次方；
				n = -1;
				if (v1 == 1) {
					n = 7;
				} else if (v1 == 2) {
					n = 6;
				} else if (v1 == 3) {
					n = 5;
				} else if (v1 == 4) {
					n = 4;
				} else if (v1 == 5) {
					n = 3;
				} else if (v1 == 6) {
					n = 2;
				} else if (v1 == 7) {
					n = 1;
				} else if (v1 == 8) {
					n = 0;
				}
			}
			if (n != -1) {
				num = Long.parseLong(calculate(n) + "");
			}
			sum = sum + num;
		}
		return sum;
	}

	/**
	 * 省数据同步文件上传页面
	 */
	@RequestMapping(value = "/toHislog.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toUpload(Model model) {
		model.addAttribute("centers", healthCenterService.getAllAreaList());
		return "synData/synData3";
	}

	/**
	 * 省数据同步错误信息读取
	 * 
	 * @param file
	 */
	@RequestMapping(value = "/getHislog.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getHislog(HttpServletRequest req, HttpServletResponse response, Integer hcid) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> hparams = new HashMap<String, Object>();
			// 卫生院id
			hparams.put("hcid", hcid);
			List<HislogDto> hisList = userService.getHislog(hparams);
			for (HislogDto hislogDto : hisList) {
				String retult = new String(hislogDto.getErromsg());
				String regEx = "[^0-9;]";
				Pattern p = Pattern.compile(regEx);
				Matcher m = p.matcher(retult);
				retult = m.replaceAll("").trim();
				String[] strs = retult.split(";");
				String codes = "";
				for (String c : strs) {
					codes = codes + "'" + c + "',";
				}
				if (!StringUtils.isEmpty(codes)) {
					codes = codes.substring(0, codes.length() - 1);
					// 获取同步数据信息
					Map<String, Object> params = new HashMap<String, Object>();
					params.put("codes", codes);
					List<FormModel> synData = userService.uploadSynData(params);
					// 根据卫生院id获取相关信息
					FormModel f = healthCenterService.getCenterByid(hislogDto.getHcid());
					// 传输数据到省平台
					String rmsg = sendSynData(req, synData, hislogDto.getRegionid(), hislogDto.getRegioncode(),
							f.getField1(), hislogDto.getDcode(), hislogDto.getDname(), hislogDto, f.getField3(),
							f.getField5());
					out = response.getWriter();
					out.print(JSON.parse("{rmsg:'" + rmsg + "'}"));
				}
			}
		} catch (Exception e) {
			log.error("SystemControl->getHislog报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 获取进度方法
	 * 
	 * @param req
	 * @param response
	 * @param sName    进度条session名称
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getProgress.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getProgress(HttpServletRequest req, HttpServletResponse response, String sName) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			HttpSession session = req.getSession();
			Map<String, Integer> promap = (Map<String, Integer>) session.getAttribute(sName);
			String result1 = "0";
			if (promap != null) {
				int total = promap.get("total");
				int num = promap.get("num");
				// 创建一个数值格式化对象
				NumberFormat numberFormat = NumberFormat.getInstance();
				// 设置精确到小数点后2位
				numberFormat.setMaximumFractionDigits(2);
				result1 = numberFormat.format((float) num / (float) total * 100);
			}
			out = response.getWriter();
			out.print(JSON.parse("{num:'" + result1 + "%'}"));
		} catch (Exception e) {
			log.error("SystemControl->getProgress报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 通过区域代码获取卫生院信息
	 * 
	 * @param req
	 * @param response
	 * @param district 区/县编码
	 */
	@RequestMapping(value = "/getHealthByDistrict.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getHealthByDistrict(HttpServletRequest req, HttpServletResponse response, String district) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", healthCenterService.getHealthByDistrict(district));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("SystemControl->getHealthByDistrict报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 初始化统计页面
	 */
	@RequestMapping(value = "/toStatistics.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toStatistics(Model model) {
		// 本年体检人数
		model.addAttribute("yearnum", dataManageMapper.getYearNum().getField1());
		// 本季度体检人数
		model.addAttribute("seasonnum", dataManageMapper.getSeasonNum().getField1());
		// 本月体检人数
		model.addAttribute("monthnum", dataManageMapper.getMonthNum().getField1());
		// 当天体检人数
		model.addAttribute("daynum", dataManageMapper.getDayNum().getField1());
		// 省年体检数据量
		String provincenum = "";
		List<FormModel> list = dataManageMapper.getProvinceNum();
		for (FormModel f : list) {
			provincenum = provincenum + f.getField1() + f.getField2() + ";";
		}
		model.addAttribute("provincenum", provincenum);
		return "system/statistics";
	}

	/**
	 * 各个卫生院体检数据
	 */
	@RequestMapping(value = "/toCData.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toCData(Model model) {
		return "system/CDataList";
	}

	/**
	 * 获取各个卫生院体检数据
	 */
	@RequestMapping(value = "/getCData.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getCData(HttpServletRequest req, HttpServletResponse response, String startDate, String endDate) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			// 开始日期
			map.put("startDate", startDate + " 00:00:00");
			// 结束日期
			map.put("endDate", endDate + " 23:59:59");
			map.put("datas", userService.getCData(map));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("SystemControl->getCData报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

}
