package com.action;

import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.alibaba.fastjson.JSON;
import com.service.DataManageService;

/**
 * API管理
 * 
 * @author Daniel Duan
 * 
 */
@Controller
@RequestMapping(value = "/dataManage")
public class DataManageControl {

	public static Logger log = Logger.getLogger(DataManageControl.class);

	@Autowired
	private DataManageService dataManageService;

	public DataManageService getDataManageService() {
		return dataManageService;
	}

	public void setDataManageService(DataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}

	/**
	 * 替换空值
	 * 
	 * @param value
	 * @return
	 */
	private String getEmpty(String value) {
		if (StringUtils.isEmpty(value) || "null".equals(value)) {
			value = "";
		}
		return value;
	}

	/**
	 * 生化数据更新
	 */
	@RequestMapping(value = "/updShenHua.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void updShenHua(HttpServletRequest req, HttpServletResponse response, String field114, String field115,
			String field117, String field120, String field119, String field106, String field123, String field124,
			String field126, String field125, String code) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("field114", getEmpty(field114));
			params.put("field115", getEmpty(field115));
			params.put("field117", getEmpty(field117));
			params.put("field120", getEmpty(field120));
			params.put("field119", getEmpty(field119));
			params.put("field106", getEmpty(field106));
			params.put("field123", getEmpty(field123));
			params.put("field124", getEmpty(field124));
			params.put("field126", getEmpty(field126));
			params.put("field125", getEmpty(field125));
			params.put("code", getEmpty(code));
			out = response.getWriter();
			map.put("code", dataManageService.updShenHua(params));
		} catch (Exception e) {
			log.error("DataManageControl->updShenHua报错:", e);
			map.put("code", 0);
		} finally {
			if (out != null) {
				out.print(JSON.toJSONString(map));
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 血球数据更新
	 */
	@RequestMapping(value = "/updXueQiu.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void updXueQiu(HttpServletRequest req, HttpServletResponse response, String field98, String field234,
			String field235, String field236, String field224, String field225, String field226, String field222,
			String field97, String field227, String field229, String field228, String field243, String field231,
			String field223, String field99, String field232, String field230, String field233, String code) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("field98", getEmpty(field98));
			params.put("field234", getEmpty(field234));
			params.put("field235", getEmpty(field235));
			params.put("field236", getEmpty(field236));
			params.put("field224", getEmpty(field224));
			params.put("field225", getEmpty(field225));
			params.put("field226", getEmpty(field226));
			params.put("field222", getEmpty(field222));
			params.put("field97", getEmpty(field97));
			params.put("field227", getEmpty(field227));
			params.put("field229", getEmpty(field229));
			params.put("field228", getEmpty(field228));
			params.put("field243", getEmpty(field243));
			params.put("field231", getEmpty(field231));
			params.put("field223", getEmpty(field223));
			params.put("field99", getEmpty(field99));
			params.put("field232", getEmpty(field232));
			params.put("field230", getEmpty(field230));
			params.put("field233", getEmpty(field233));
			params.put("code", getEmpty(code));
			out = response.getWriter();
			map.put("code", dataManageService.updXueQiu(params));
		} catch (Exception e) {
			log.error("DataManageControl->updXueQiu报错:", e);
			map.put("code", 0);
		} finally {
			if (out != null) {
				out.print(JSON.toJSONString(map));
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 身高体重数据更新
	 */
	@RequestMapping(value = "/updSGTZ.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void updSGTZ(HttpServletRequest req, HttpServletResponse response, String field11, String field12,
			String code) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("field11", getEmpty(field11));
			params.put("field12", getEmpty(field12));
			// 身高
			String highStr = field11;
			// 体重
			String weightStr = field12;
			// 计算体质数
			String bmi = "";
			if (!StringUtils.isEmpty(highStr) && !StringUtils.isEmpty(weightStr)) {
				Double h = Double.parseDouble(highStr);
				Double w = Double.parseDouble(weightStr);
				DecimalFormat df = new DecimalFormat("0.00");
				h = h / 100;
				h = h * h;
				bmi = df.format(w / h);
			}
			String field219 = bmi;
			params.put("field219", getEmpty(field219));
			params.put("code", getEmpty(code));
			out = response.getWriter();
			map.put("code", dataManageService.updSGTZ(params));
		} catch (Exception e) {
			log.error("DataManageControl->updSGTZ报错:", e);
			map.put("code", 0);
		} finally {
			if (out != null) {
				out.print(JSON.toJSONString(map));
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 血压数据更新
	 */
	@RequestMapping(value = "/updXueYa.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void updXueYa(HttpServletRequest req, HttpServletResponse response, String field5, String field7,
			String field8, String field9, String field10, String code) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("field5", getEmpty(field5));
			params.put("field7", getEmpty(field7));
			params.put("field8", getEmpty(field8));
			params.put("field9", getEmpty(field9));
			params.put("field10", getEmpty(field10));
			params.put("code", getEmpty(code));
			out = response.getWriter();
			map.put("code", dataManageService.updXueYa(params));
		} catch (Exception e) {
			log.error("DataManageControl->updXueYa报错:", e);
			map.put("code", 0);
		} finally {
			if (out != null) {
				out.print(JSON.toJSONString(map));
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 替换尿液值
	 * 
	 * @param value
	 * @return
	 */
	private String getNYValue(String value) {
		if (StringUtils.isEmpty(value) || "null".equals(value)) {
			value = "";
		} else {
			value = value.replaceAll("DYFH", "=");
			value = value.replaceAll("XYFH", "<");
			value = value.replaceAll("DYFF", ">");
			value = value.replaceAll("JHFH", "+");
		}
		return value;
	}

	/**
	 * 尿液数据更新
	 */
	@RequestMapping(value = "/updNiaoYe.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void updNiaoYe(HttpServletRequest req, HttpServletResponse response, String field240, String field239,
			String field101, String field238, String field242, String field104, String field241, String field237,
			String field102, String field103, String field265, String field264, String field261, String field258,
			String field267, String field262, String field266, String field260, String field259, String field263,
			String code) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("field240", getNYValue(field240));
			params.put("field239", getNYValue(field239));
			params.put("field101", getNYValue(field101));
			params.put("field238", getNYValue(field238));
			params.put("field242", getNYValue(field242));
			params.put("field104", getNYValue(field104));
			params.put("field241", getNYValue(field241));
			params.put("field237", getNYValue(field237));
			params.put("field102", getNYValue(field102));
			params.put("field103", getNYValue(field103));
			params.put("field265", getNYValue(field265));
			params.put("field264", getNYValue(field264));
			params.put("field261", getNYValue(field261));
			params.put("field258", getNYValue(field258));
			params.put("field267", getNYValue(field267));
			params.put("field262", getNYValue(field262));
			params.put("field266", getNYValue(field266));
			params.put("field260", getNYValue(field260));
			params.put("field259", getNYValue(field259));
			params.put("field263", getNYValue(field263));
			params.put("code", getEmpty(code));
			out = response.getWriter();
			map.put("code", dataManageService.updNiaoYe(params));
		} catch (Exception e) {
			log.error("DataManageControl->updNiaoYe报错:", e);
			map.put("code", 0);
		} finally {
			if (out != null) {
				out.print(JSON.toJSONString(map));
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 心电数据更新
	 */
	@RequestMapping(value = "/updXinDian.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void updXinDian(HttpServletRequest req, HttpServletResponse response, String field128, String field127,
			String field77, String field246, String code) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("field128", getEmpty(field128));
			params.put("field127", getEmpty(field127));
			params.put("field77", getEmpty(field77));
			params.put("field246", getEmpty(field246));
			params.put("code", getEmpty(code));
			out = response.getWriter();
			map.put("code", dataManageService.updXinDian(params));
		} catch (Exception e) {
			log.error("DataManageControl->updXinDian报错:", e);
			map.put("code", 0);
		} finally {
			if (out != null) {
				out.print(JSON.toJSONString(map));
				out.flush();
				out.close();
			}
		}
	}

}
