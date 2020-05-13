package com.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dto.DoctorSignDto;
import com.dto.UserDto;
import com.service.DoctorService;
import com.alibaba.fastjson.JSON;

/**
 * 家医管理
 * 
 * @author Daniel Duan
 * 
 */
@Controller
@RequestMapping(value = "/doctor")
public class DoctorControl {

	public static Logger log = Logger.getLogger(AreaControl.class);

	@Autowired
	private DoctorService doctorService;

	public DoctorService getDoctorService() {
		return doctorService;
	}

	public void setDoctorService(DoctorService doctorService) {
		this.doctorService = doctorService;
	}

	/**
	 * 初始化页面
	 * 
	 */
	@RequestMapping(value = "/doctor.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String doctor(HttpServletRequest req, Model model) {
		HttpSession session = req.getSession();
		UserDto userDto = (UserDto) session.getAttribute("user");
		model.addAttribute("hcid", userDto.getHcid());
		return "doctor/doctor";
	}

	/**
	 * 获取 区域列表信息
	 * 
	 * @param req
	 * @param response
	 * @param model
	 */
	@RequestMapping(value = "/getDoctor.do", method = { RequestMethod.GET })
	public void getDoctor(HttpServletRequest req, HttpServletResponse response, Model model, Integer hcid) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("doctor", doctorService.getDoctorByhcid(hcid));
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("DoctorControl->getDoctor报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 增加或修改签约医生信息
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/changeDoctor.do", method = { RequestMethod.POST })
	public void changeDoctor(HttpServletRequest req, HttpServletResponse response, DoctorSignDto doctorSignDto) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			int num = 0;
			if (null == doctorSignDto.getId()) {
				num = doctorService.insertDoctorSign(doctorSignDto);
			} else {
				num = doctorService.updDoctorSign(doctorSignDto);
			}
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("DoctorControl->changeDoctor报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

}
