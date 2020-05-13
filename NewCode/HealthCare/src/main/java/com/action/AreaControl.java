package com.action;

import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.dto.AreaDto;
import com.service.AreaService;

/**
 * 区域管理
 * 
 * @author Daniel Duan
 * 
 */
@Controller
@RequestMapping(value = "/area")
public class AreaControl {

	public static Logger log = Logger.getLogger(AreaControl.class);

	@Autowired
	private AreaService areaService;

	public AreaService getAreaService() {
		return areaService;
	}

	public void setAreaService(AreaService areaService) {
		this.areaService = areaService;
	}

	/**
	 * 初始化页面
	 * 
	 */
	@RequestMapping(value = "/list.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String list(Model model) {
		return "area/areaList";
	}

	/**
	 * 增加区域信息
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/insertArea.do", method = { RequestMethod.POST })
	public void insertArea(HttpServletRequest req, HttpServletResponse response, String name, String code, String pname,
			Integer level, String pcode, String hcode) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			AreaDto areaDto = new AreaDto();
			areaDto.setCode(code);
			areaDto.setName(URLDecoder.decode(name, "UTF-8"));
			areaDto.setPname(URLDecoder.decode(pname, "UTF-8"));
			areaDto.setPcode(pcode);
			areaDto.setHcode(hcode);
			areaDto.setLevel(level);
			int num = areaService.insertArea(areaDto);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("AreaControl->insertArea报错:" + e.toString());
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
	@RequestMapping(value = "/getAreaList.do", method = { RequestMethod.GET })
	public void getAreaList(HttpServletRequest req, HttpServletResponse response, Model model, String name,
			Integer level, String pcode) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> param = new HashMap<String, Object>();
			if (name != null) {
				name = URLDecoder.decode(name, "UTF-8");
			}
			param.put("name", name);
			param.put("level", level);
			param.put("pcode", pcode);

			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", areaService.getAreaList(param));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("AreaControl->getAreaList报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 修改区域信息
	 * 
	 * @param req
	 * @param response
	 * @param name
	 */
	@RequestMapping(value = "/updArea.do", method = { RequestMethod.POST })
	public void updArea(HttpServletRequest req, HttpServletResponse response, String name, String code, String pname,
			Integer level, String pcode, String hcode, Integer id,String oldCode) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			AreaDto areaDto = new AreaDto();
			areaDto.setId(id);
			areaDto.setCode(code);
			areaDto.setName(URLDecoder.decode(name, "UTF-8"));
			areaDto.setPname(URLDecoder.decode(pname, "UTF-8"));
			areaDto.setPcode(pcode);
			areaDto.setHcode(hcode);
			areaDto.setLevel(level);
			int num = areaService.updArea(areaDto,oldCode);
			out = response.getWriter();
			if (num > 0) {
				out.print(JSON.parse("{code:0}"));
			} else {
				out.print(JSON.parse("{code:1}"));
			}
		} catch (Exception e) {
			log.error("AreaControl->updArea报错:" + e.toString());
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 初始化城市页面
	 * 
	 */
	@RequestMapping(value = "/toCityList.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toCityList(Model model, String pname, String pcode) {
		try {
			model.addAttribute("pcode", pcode);
			model.addAttribute("pname", URLDecoder.decode(pname, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("AreaControl->toCityList报错:" + e.toString());
		}
		return "area/cityList";
	}
	
	/**
	 * 初始化区县页面
	 * 
	 */
	@RequestMapping(value = "/toDistrict.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toDistrict(Model model, String pname, String pcode) {
		try {
			model.addAttribute("pcode", pcode);
			model.addAttribute("pname", URLDecoder.decode(pname, "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			log.error("AreaControl->toDistrict报错:" + e.toString());
		}
		return "area/district";
	}

}
