package com.action;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

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
import com.dto.PatientDto;
import com.dto.UserDto;
import com.service.HealthCheckService;
import com.service.PatientService;

/**
 * 病人管理
 * 
 * @author Daniel Duan
 * 
 */
@Controller
@RequestMapping(value = "/patient")
public class PatientControl {

	public static Logger log = Logger.getLogger(PatientControl.class);

	@Autowired
	private PatientService patientService;

	@Autowired
	private HealthCheckService healthCheckService;

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
	 * 初始化页面
	 * 
	 * @param status状态(0不刷新1刷新)
	 */
	@RequestMapping(value = "/list.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String list(Model model, Integer status) {
		model.addAttribute("status", status);
		return "healthCases/patientList";
	}

	/**
	 * 个人档案页面
	 */
	@RequestMapping(value = "/patientCase.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String patientCase(Model model, Integer id) {
		model.addAttribute("uid", id);
		return "healthCases/patientCase";
	}

	/**
	 * 病人详情页面
	 */
	@RequestMapping(value = "/detail.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String detail(Model model, Integer id, Integer selOption) {
		model.addAttribute("uid", id);
		model.addAttribute("selOption", selOption);
		return "healthCases/index";
	}

	/**
	 * 居民健康档案
	 */
	@RequestMapping(value = "/phealth.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String phealth(Model model, Integer id) {
		model.addAttribute("uid", id);
		return "healthCases/phealth";
	}

	/**
	 * 根据id获取病人信息
	 */
	@RequestMapping(value = "/getPatient.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getPatient(HttpServletRequest req, HttpServletResponse response, Model model, Integer id) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", patientService.getDetail(id));
			map.put("healthcheck", healthCheckService.getHealthCheck(id));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("PatientControl->getPatient报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 获取 区域列表信息
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/getList.do", method = { RequestMethod.GET })
	public void getList(HttpServletRequest req, HttpServletResponse response, Model model, String code, String pname,
			String idcard, String startDate, String endDate) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
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
			if (userDto != null) {
				params.put("hcid", userDto.getHcid());
			} else {
				params.put("hcid", "-1");
			}
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", patientService.getList(params));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("PatientControl->getList报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改居民健康档案
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updPatientHeath.do", method = { RequestMethod.POST })
	public void updPatientHeath(HttpServletRequest req, HttpServletResponse response, String name, int id,
			String address, String address1, String phoneno, String countyname, String vcname, String fileunit,
			String fileuser, String doctor) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			PatientDto patientDto = new PatientDto();
			patientDto.setId(id);
			patientDto.setName(URLDecoder.decode(name, "UTF-8"));
			patientDto.setAddress(URLDecoder.decode(address, "UTF-8"));
			patientDto.setAddress1(URLDecoder.decode(address1, "UTF-8"));
			patientDto.setPhoneno(phoneno);
			patientDto.setCountyname(URLDecoder.decode(countyname, "UTF-8"));
			patientDto.setVcname(URLDecoder.decode(vcname, "UTF-8"));
			patientDto.setFileunit(URLDecoder.decode(fileunit, "UTF-8"));
			patientDto.setFileuser(URLDecoder.decode(fileuser, "UTF-8"));
			patientDto.setDoctor(URLDecoder.decode(doctor, "UTF-8"));
			int num = patientService.updPatientHeath(patientDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("PatientControl->updPatientHeath报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改个人档案
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updHealthCase.do", method = { RequestMethod.POST })
	public void updHealthCase(HttpServletRequest req, HttpServletResponse response, PatientDto patientDto,
			String sbirthdate, String sctime1, String sctime2, String sctime3, String sctime4, String sstime1,
			String sstime2, String sttime1, String sttime2, String sbtime1, String sbtime2) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			patientDto.setName(URLDecoder.decode(patientDto.getName(), "UTF-8"));
			patientDto.setNationality(URLDecoder.decode(patientDto.getNationality(), "UTF-8"));
			patientDto.setCompany(URLDecoder.decode(patientDto.getCompany(), "UTF-8"));
			patientDto.setCname(URLDecoder.decode(patientDto.getCname(), "UTF-8"));
			patientDto.setSname1(URLDecoder.decode(patientDto.getSname1(), "UTF-8"));
			patientDto.setSname2(URLDecoder.decode(patientDto.getSname2(), "UTF-8"));
			patientDto.setTname1(URLDecoder.decode(patientDto.getTname1(), "UTF-8"));
			patientDto.setTname2(URLDecoder.decode(patientDto.getTname2(), "UTF-8"));
			patientDto.setBreason1(URLDecoder.decode(patientDto.getBreason1(), "UTF-8"));
			patientDto.setBreason2(URLDecoder.decode(patientDto.getBreason2(), "UTF-8"));
			patientDto.setDname(URLDecoder.decode(patientDto.getDname(), "UTF-8"));
			patientDto.setField4(URLDecoder.decode(patientDto.getField4(), "UTF-8"));
			patientDto.setField5(URLDecoder.decode(patientDto.getField5(), "UTF-8"));
			patientDto.setField8(URLDecoder.decode(patientDto.getField8(), "UTF-8"));
			patientDto.setField10(URLDecoder.decode(patientDto.getField10(), "UTF-8"));

			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (sbirthdate != null && !"".equals(sbirthdate)) {
				patientDto.setBirthdate(format.parse(sbirthdate));
			}
			if (sctime1 != null && !"".equals(sctime1)) {
				patientDto.setCtime1(format.parse(sctime1));
			}
			if (sctime2 != null && !"".equals(sctime2)) {
				patientDto.setCtime2(format.parse(sctime2));
			}
			if (sctime3 != null && !"".equals(sctime3)) {
				patientDto.setCtime3(format.parse(sctime3));
			}
			if (sctime4 != null && !"".equals(sctime4)) {
				patientDto.setCtime4(format.parse(sctime4));
			}
			if (sstime1 != null && !"".equals(sstime1)) {
				patientDto.setStime1(format.parse(sstime1));
			}
			if (sstime2 != null && !"".equals(sstime2)) {
				patientDto.setStime2(format.parse(sstime2));
			}
			if (sttime1 != null && !"".equals(sttime1)) {
				patientDto.setTtime1(format.parse(sttime1));
			}
			if (sttime2 != null && !"".equals(sttime2)) {
				patientDto.setTtime2(format.parse(sttime2));
			}
			if (sbtime1 != null && !"".equals(sbtime1)) {
				patientDto.setBtime1(format.parse(sbtime1));
			}
			if (sbtime2 != null && !"".equals(sbtime2)) {
				patientDto.setBtime2(format.parse(sbtime2));
			}
			int num = patientService.updHealthCase(patientDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("PatientControl->updHealthCase报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 刷新病人信息
	 * 
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getPatientByHid.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getPatientByHid(HttpServletRequest req, HttpServletResponse response, Model model, String itemCode,
			String itemName, Integer itemSex, String itemNationality, String itemBirthdate, String itemIdcard,
			String itemAddress1, Integer hcid, Integer isExist) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<Integer, PatientDto> pmap = new HashMap<Integer, PatientDto>();
			Object obj = req.getServletContext().getAttribute("pmap");
			if (obj != null) {
				pmap = (Map<Integer, PatientDto>) obj;
			}
			PatientDto p = pmap.get(hcid);
			if (p == null) {
				p = new PatientDto();
			}
			p.setCode(itemCode);
			if (!StringUtils.isEmpty(itemName)) {
				p.setName(URLDecoder.decode(itemName, "UTF-8"));
			}
			p.setSex(itemSex);
			if (!StringUtils.isEmpty(itemNationality)) {
				p.setNationality(URLDecoder.decode(itemNationality, "UTF-8"));
			}
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			if (!StringUtils.isEmpty(itemBirthdate)) {
				p.setBirthdate(format.parse(itemBirthdate));
			}
			p.setIdcard(itemIdcard);
			if (!StringUtils.isEmpty(itemAddress1)) {
				p.setAddress1(URLDecoder.decode(itemAddress1, "UTF-8"));
			}
			p.setHcid(hcid);
			// 0默认1我会提出身份证信息已存在
			p.setField9(isExist);
			pmap.put(hcid, p);
			req.getServletContext().setAttribute("pmap", pmap);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("code", 1);
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("PatientControl->getPatientByHid报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 根据id获取病人信息
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/scanGetPatient.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void scanGetPatient(HttpServletRequest req, HttpServletResponse response, Model model, Integer hcid) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			Object obj = req.getServletContext().getAttribute("pmap");
			if (obj != null) {
				Map<Integer, PatientDto> pmap = (Map<Integer, PatientDto>) obj;
				PatientDto p = pmap.get(hcid);
				if (p != null) {
					if (p.getField9() != null && p.getField9() == 1) {
						// 0默认1我会提出身份证信息已存在
						map.put("msg", 1);
						p.setField9(0);
						pmap.put(hcid, p);
						req.getServletContext().setAttribute("pmap", pmap);
					}
					map.put("datas", p);
				}
			}
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("PatientControl->scanGetPatient报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 根据id删除病人信息
	 */
	@RequestMapping(value = "/delPatient.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void delPatient(HttpServletRequest req, HttpServletResponse response, Model model, Integer id) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", patientService.delPatient(id));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("PatientControl->delPatient报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

}
