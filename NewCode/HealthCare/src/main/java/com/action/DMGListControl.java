package com.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
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
import com.dto.UserDto;
import com.form.FormModel;
import com.form.PrintForm;
import com.service.DataManageService;

/**
 * 数据审核
 * 
 * @author Daniel Duan
 * 
 */
@Controller
@RequestMapping(value = "/DMG")
public class DMGListControl {

	public static Logger log = Logger.getLogger(DMGListControl.class);

	@Autowired
	private DataManageService dataManageService;

	public DataManageService getDataManageService() {
		return dataManageService;
	}

	public void setDataManageService(DataManageService dataManageService) {
		this.dataManageService = dataManageService;
	}

	/**
	 * 数据审核页面
	 */
	@RequestMapping(value = "/toDataReview.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toGenerally(Model model) {
		return "datamanage/dataReview";
	}

	/**
	 * 查询审核信息
	 */
	@RequestMapping(value = "/getDataReview.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getDataReview(HttpServletRequest req, HttpServletResponse response, Model model, String idCard,
			String name, String code, String startDate, String endDate, Integer selAge, Integer order, String filed) {
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
			params.put("filed", null);
			if (order != null && order != 0) {
				if (1 == order) {// 升序
					params.put("filed", " order by " + filed);
				} else {// 降序
					params.put("filed", " order by " + filed + " DESC");
				}
			}
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
			map.put("datas", dataManageService.getDateList(params));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("DMGListControl->getDataReview报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 体检异常页面
	 */
	@RequestMapping(value = "/abnormalityData.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String abnormalityData(Model model) {
		return "datamanage/abnormalityData";
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
	 * 计算导出异常人数
	 * 
	 * @param list
	 * @return
	 * @throws Exception
	 */
	private Map<String, Object> getIPeople(List<FormModel> list) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("total", list.size());
		// BMI异常人数
		List<FormModel> list1 = new ArrayList<FormModel>();
		// 高血压异常人数
		List<FormModel> list2 = new ArrayList<FormModel>();
		// 高血糖异常人数
		List<FormModel> list3 = new ArrayList<FormModel>();
		// 肝功异常人数
		List<FormModel> list4 = new ArrayList<FormModel>();
		// 肾功异常人数
		List<FormModel> list5 = new ArrayList<FormModel>();
		// 血脂异常人数
		List<FormModel> list6 = new ArrayList<FormModel>();
		// 血小板异常人数
		List<FormModel> list7 = new ArrayList<FormModel>();
		// 血红蛋白异常人数
		List<FormModel> list8 = new ArrayList<FormModel>();
		// 白细胞异常人数
		List<FormModel> list9 = new ArrayList<FormModel>();
		// 心电图异常人数
		List<FormModel> list10 = new ArrayList<FormModel>();
		// 尿液分析异常人数
		List<FormModel> list11 = new ArrayList<FormModel>();
		// B超异常人数
		List<FormModel> list12 = new ArrayList<FormModel>();
		for (FormModel h : list) {
			if (h != null) {
				FormModel newf = new FormModel();
				// 体检日期
				if (StringUtils.isEmpty(h.getField27())) {
					h.setField27("");
				}
				newf.setField1(h.getField27());
				// 姓名
				if (StringUtils.isEmpty(h.getField28())) {
					h.setField28("");
				}
				newf.setField2(h.getField28());
				// 电子条码编号
				if (StringUtils.isEmpty(h.getField29())) {
					h.setField29("");
				}
				newf.setField3(h.getField29());
				// 年龄
				PrintForm f = new PrintForm();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
				if (!StringUtils.isEmpty(h.getField30())) {
					f.setBirthdate(sdf.parse(h.getField30()));
				} else {
					f.setBirthdate(new Date());
				}
				newf.setField4(f.getAge() + "");
				// 性别
				String sex = h.getField31();
				// 性别(1男2女3未说明的性别4未知的性别)
				if (!StringUtils.isEmpty(sex)) {
					if ("1".equals(sex)) {
						sex = "男";
					} else if ("2".equals(sex)) {
						sex = "女";
					} else if ("3".equals(sex)) {
						sex = "未说明的性别";
					} else if ("4".equals(sex)) {
						sex = "未知的性别";
					}
				} else {
					sex = "男";
				}
				newf.setField5(sex);
				// 身份证号
				if (StringUtils.isEmpty(h.getField32())) {
					h.setField32("");
				}
				newf.setField6(h.getField32());
				// 联系电话
				if (StringUtils.isEmpty(h.getField33())) {
					h.setField33("");
				}
				newf.setField7(h.getField33());
				// 家庭住址
				String address = h.getField34();
				if (StringUtils.isEmpty(address)) {
					address = h.getField35();
				}
				if (StringUtils.isEmpty(address)) {
					address = "";
				}
				newf.setField8(address);
				// BMI判断
				String field219Str = h.getField1();
				if (isDouble(field219Str)) {
					Double field219 = Double.valueOf(field219Str);
					if (field219 < 18.5 || field219 > 28) {
						newf.setField9(field219Str);
						list1.add(newf);
					}
				}
				// 高血压
				String field3 = h.getField3();
				if (!StringUtils.isEmpty(field3)) {
					String[] vs = field3.split("fg");
					if (vs.length > 1) {
						if (isDouble(vs[0]) && isDouble(vs[1])) {
							Double fieldl = Double.valueOf(vs[0]);
							Double fieldr = Double.valueOf(vs[1]);
							if (fieldl >= 140) {
								newf.setField10(vs[0] + "/" + vs[1]);
							} else if (fieldr >= 90) {
								newf.setField10(vs[0] + "/" + vs[1]);
							}
						}
						if (StringUtils.isEmpty(newf.getField10()) && vs.length == 4 && isDouble(vs[2])
								&& isDouble(vs[3])) {
							Double fieldl = Double.valueOf(vs[2]);
							Double fieldr = Double.valueOf(vs[3]);
							if (fieldl >= 140) {
								newf.setField10(vs[2] + "/" + vs[3]);
							} else if (fieldr >= 90) {
								newf.setField10(vs[2] + "/" + vs[3]);
							}
						}
					}
					if (!StringUtils.isEmpty(newf.getField10())) {
						list2.add(newf);
					}
				}
				// 高血糖
				if (isDouble(h.getField4())) {
					Double gxt = Double.valueOf(h.getField4());
					if (gxt >= 6.1) {
						newf.setField11(h.getField4());
						list3.add(newf);
					}
				}
				// 肝功
				boolean bol = false;
				// 1.血清谷丙转氨酶
				if (isDouble(h.getField5())) {
					Double field114 = Double.valueOf(h.getField5());
					if (field114 < 0 || field114 > 40) {
						bol = true;
					}
				}
				// 2.血清谷草转氨酶
				if (isDouble(h.getField6())) {
					Double field115 = Double.valueOf(h.getField6());
					if (field115 < 0 || field115 > 40) {
						bol = true;
					}
				}
				// 4.总胆红素
				if (isDouble(h.getField8())) {
					Double field117 = Double.valueOf(h.getField8());
					if (field117 < 5 || field117 > 28) {
						bol = true;
					}
				}
				if (bol) {
					newf.setField12(h.getField5());
					newf.setField13(h.getField6());
					newf.setField14(h.getField8());
					list4.add(newf);
				}
				// 肾功
				bol = false;
				// 1.血清肌酐
				if (isDouble(h.getField10())) {
					Double field119 = Double.valueOf(h.getField10());
					if (field119 < 30 || field119 > 106) {
						bol = true;
					}
				}
				// 2.血尿素氮
				if (isDouble(h.getField11())) {
					Double field120 = Double.valueOf(h.getField11());
					if (field120 < 1.7 || field120 > 8.3) {
						bol = true;
					}
				}
				if (bol) {
					newf.setField15(h.getField10());
					newf.setField16(h.getField11());
					list5.add(newf);
				}
				// 血脂
				bol = false;
				// 1.总胆固醇
				if (isDouble(h.getField12())) {
					Double field123 = Double.valueOf(h.getField12());
					if (field123 < 3.6 || field123 > 6.5) {
						bol = true;
					}
				}
				// 2.甘油三酯
				if (isDouble(h.getField13())) {
					Double field124 = Double.valueOf(h.getField13());
					if (field124 < 0 || field124 > 1.71) {
						bol = true;
					}
				}
				// 3.低密度
				if (isDouble(h.getField14())) {
					Double field125 = Double.valueOf(h.getField14());
					if (field125 < 2.07 || field125 > 3.1) {
						bol = true;
					}
				}
				// 4.高密度
				if (isDouble(h.getField15())) {
					Double field126 = Double.valueOf(h.getField15());
					if (field126 < 0.9 || field126 > 2) {
						bol = true;
					}
				}
				if (bol) {
					newf.setField17(h.getField12());
					newf.setField18(h.getField13());
					newf.setField19(h.getField14());
					newf.setField20(h.getField15());
					list6.add(newf);
				}
				// 血小板
				if (isDouble(h.getField16())) {
					Double field99 = Double.valueOf(h.getField16());
					if (field99 < 100 || field99 > 315) {
						newf.setField21(h.getField16());
					}
				}
				if (!StringUtils.isEmpty(newf.getField21())) {
					list7.add(newf);
				}
				// 血红蛋白
				if (isDouble(h.getField17())) {
					Double field97 = Double.valueOf(h.getField17());
					if (field97 < 110 || field97 > 160) {
						newf.setField22(h.getField17());
					}
				}
				if (!StringUtils.isEmpty(newf.getField22())) {
					list8.add(newf);
				}
				// 白细胞
				if (isDouble(h.getField18())) {
					Double field98 = Double.valueOf(h.getField18());
					if (field98 < 4 || field98 > 10) {
						newf.setField23(h.getField18());
					}
				}
				if (!StringUtils.isEmpty(newf.getField23())) {
					list9.add(newf);
				}
				// 心电图
				if (!StringUtils.isEmpty(h.getField19())) {
					if ("1".indexOf(h.getField19()) == -1) {
						String hstr = "";
						String[] strArr = h.getField19().split(",");
						for (String s : strArr) {
							if ("2".equals(s)) {
								hstr = hstr + "ST段改变 ";
							} else if ("3".equals(s)) {
								hstr = hstr + "陈旧性心肌梗塞 ";
							} else if ("4".equals(s)) {
								hstr = hstr + "窦性心动过速 ";
							} else if ("5".equals(s)) {
								hstr = hstr + "窦性心动过缓 ";
							} else if ("6".equals(s)) {
								hstr = hstr + "早搏 ";
							} else if ("7".equals(s)) {
								hstr = hstr + "房颤 ";
							} else if ("8".equals(s)) {
								hstr = hstr + "房室传导阻滞 ";
							} else if ("9".equals(s)) {
								hstr = hstr + h.getField37() + " ";
							}
						}
						newf.setField24(hstr);
						// 心率
						if (!StringUtils.isEmpty(h.getField36())) {
							newf.setField30(h.getField36());
						}
					}
				}
				if (!StringUtils.isEmpty(newf.getField24())) {
					list10.add(newf);
				}
				// 尿液分析
				bol = false;
				// 1.尿蛋白
				if (isDouble(h.getField20())) {
					Double field101 = Double.valueOf(h.getField20());
					if (field101 < 2 || field101 > 7) {
						bol = true;
					}
				} else if (!"-".equals(h.getField20())) {
					bol = true;
				}
				// 2.尿糖
				if (!StringUtils.isEmpty(h.getField21())) {
					if (!"-".equals(h.getField21())) {
						bol = true;
					}
				}
				// 3.尿酮体
				if (!StringUtils.isEmpty(h.getField22())) {
					if (!"-".equals(h.getField22())) {
						bol = true;
					}
				}
				// 4.尿潜血
				if (!StringUtils.isEmpty(h.getField23())) {
					if (!"-".equals(h.getField23())) {
						bol = true;
					}
				}
				if (bol) {
					newf.setField25(h.getField20());
					newf.setField26(h.getField21());
					newf.setField27(h.getField22());
					newf.setField28(h.getField23());
					list11.add(newf);
				}
				// B超
				// 1.腹部B超
				if (!StringUtils.isEmpty(h.getField25()) && "2".equals(h.getField25())) {
					newf.setField29(h.getField26());
				}
				if (!StringUtils.isEmpty(newf.getField29())) {
					list12.add(newf);
				}
			}
		}
		map.put("p1", list1);
		map.put("p2", list2);
		map.put("p3", list3);
		map.put("p4", list4);
		map.put("p5", list5);
		map.put("p6", list6);
		map.put("p7", list7);
		map.put("p8", list8);
		map.put("p9", list9);
		map.put("p10", list10);
		map.put("p11", list11);
		map.put("p12", list12);
		return map;
	}

	/**
	 * 查询体检异常信息
	 */
	@RequestMapping(value = "/getALData.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getALData(HttpServletRequest req, HttpServletResponse response, String startDate, String endDate,
			String startAge, String endAge) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("startDate", startDate + " 00:00:00");
			params.put("endDate", endDate + " 23:59:59");
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			if (userDto != null) {
				params.put("hcid", userDto.getHcid());
			} else {
				params.put("hcid", "-1");
			}
			params.put("startAge", startAge);
			params.put("endAge", endAge);
			// 查询
			List<FormModel> hlist = dataManageService.getALData(params);
			Map<String, Object> map = getIPeople(hlist);
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("DMGListControl->getALData报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 省体检异常页面
	 */
	@RequestMapping(value = "/provinceData.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String provinceData(Model model, String pname) {
		try {
			if (pname != null) {
				pname = URLDecoder.decode(pname, "UTF-8");
			}
			model.addAttribute("pname", pname);
		} catch (Exception e) {
			log.error("DMGListControl->provinceData报错:", e);
		}
		return "datamanage/provinceData";
	}

	/**
	 * 查询省体检异常信息
	 */
	@RequestMapping(value = "/getProvinceData.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getProvinceData(HttpServletRequest req, HttpServletResponse response, String pname) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			if (pname != null) {
				pname = URLDecoder.decode(pname, "UTF-8");
			}
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("pname", pname);
			// 查询
			List<FormModel> hlist = dataManageService.getProvinceData(params);
			Map<String, Object> map = getIPeople(hlist);
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("DMGListControl->getProvinceData报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 设置导出数据
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String setExcelData(HttpServletRequest req, Map<String, Object> map) throws Exception {
		String url = req.getSession().getServletContext().getRealPath("/");
		// 加载excel模板文件，进行标题设置
		FileInputStream fs = null;
		POIFSFileSystem ps = null;
		HSSFWorkbook wb = null;
		FileOutputStream out = null;
		UUID uuid = UUID.randomUUID();
		String fileName = url + "/template/" + uuid.toString().replace("-", "") + ".xls";
		try {
			fs = new FileInputStream(url + "/template/template3.xls");
			ps = new POIFSFileSystem(fs);
			wb = new HSSFWorkbook(ps);
			// BMI异常人数
			List<FormModel> list1 = (List<FormModel>) map.get("p1");
			HSSFSheet sheet = wb.getSheetAt(0);
			for (int j = 0; j < list1.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list1.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField9());
			}
			// 高血压异常人数
			List<FormModel> list2 = (List<FormModel>) map.get("p2");
			sheet = wb.getSheetAt(1);
			for (int j = 0; j < list2.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list2.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField10());
			}
			// 高血糖异常人数
			List<FormModel> list3 = (List<FormModel>) map.get("p3");
			sheet = wb.getSheetAt(2);
			for (int j = 0; j < list3.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list3.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField11());
			}
			// 肝功异常人数
			List<FormModel> list4 = (List<FormModel>) map.get("p4");
			sheet = wb.getSheetAt(3);
			for (int j = 0; j < list4.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list4.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField12());
				cell = row.createCell(9);
				cell.setCellValue(formModel.getField13());
				cell = row.createCell(10);
				cell.setCellValue(formModel.getField14());
			}
			// 肾功异常人数
			List<FormModel> list5 = (List<FormModel>) map.get("p5");
			sheet = wb.getSheetAt(4);
			for (int j = 0; j < list5.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list5.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField15());
				cell = row.createCell(9);
				cell.setCellValue(formModel.getField16());
			}
			// 血脂异常人数
			List<FormModel> list6 = (List<FormModel>) map.get("p6");
			sheet = wb.getSheetAt(5);
			for (int j = 0; j < list6.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list6.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField17());
				cell = row.createCell(9);
				cell.setCellValue(formModel.getField18());
				cell = row.createCell(10);
				cell.setCellValue(formModel.getField19());
				cell = row.createCell(11);
				cell.setCellValue(formModel.getField20());
			}
			// 血小板异常人数
			List<FormModel> list7 = (List<FormModel>) map.get("p7");
			sheet = wb.getSheetAt(6);
			for (int j = 0; j < list7.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list7.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField21());
			}
			// 血红蛋白异常人数
			List<FormModel> list8 = (List<FormModel>) map.get("p8");
			sheet = wb.getSheetAt(7);
			for (int j = 0; j < list8.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list8.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField22());
			}
			// 白细胞异常人数
			List<FormModel> list9 = (List<FormModel>) map.get("p9");
			sheet = wb.getSheetAt(8);
			for (int j = 0; j < list9.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list9.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField23());
			}
			// 心电图异常人数
			List<FormModel> list10 = (List<FormModel>) map.get("p10");
			sheet = wb.getSheetAt(9);
			for (int j = 0; j < list10.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list10.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField24());
				cell = row.createCell(9);
				cell.setCellValue(formModel.getField30());
			}
			// 尿液分析异常人数
			List<FormModel> list11 = (List<FormModel>) map.get("p11");
			sheet = wb.getSheetAt(10);
			for (int j = 0; j < list11.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list11.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField25());
				cell = row.createCell(9);
				cell.setCellValue(formModel.getField26());
				cell = row.createCell(10);
				cell.setCellValue(formModel.getField27());
				cell = row.createCell(11);
				cell.setCellValue(formModel.getField28());
			}
			// B超异常人数
			List<FormModel> list12 = (List<FormModel>) map.get("p12");
			sheet = wb.getSheetAt(11);
			for (int j = 0; j < list12.size(); j++) {
				HSSFRow row = sheet.createRow(j + 2);
				FormModel formModel = list12.get(j);
				HSSFCell cell = row.createCell(0);
				cell.setCellValue(formModel.getField1());
				cell = row.createCell(1);
				cell.setCellValue(formModel.getField2());
				cell = row.createCell(2);
				cell.setCellValue(formModel.getField3());
				cell = row.createCell(3);
				cell.setCellValue(formModel.getField4());
				cell = row.createCell(4);
				cell.setCellValue(formModel.getField5());
				cell = row.createCell(5);
				cell.setCellValue(formModel.getField6());
				cell = row.createCell(6);
				cell.setCellValue(formModel.getField7());
				cell = row.createCell(7);
				cell.setCellValue(formModel.getField8());
				cell = row.createCell(8);
				cell.setCellValue(formModel.getField29());
			}
			out = new FileOutputStream(fileName);
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
	 * 导出异常数据
	 */
	@RequestMapping(value = "/exportData.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void exportData(HttpServletRequest req, HttpServletResponse response, String startDate, String endDate,
			String startAge, String endAge) {
		response.setCharacterEncoding("UTF-8");
		File f = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("startDate", startDate + " 00:00:00");
			params.put("endDate", endDate + " 23:59:59");
			HttpSession session = req.getSession();
			UserDto userDto = (UserDto) session.getAttribute("user");
			if (userDto != null) {
				params.put("hcid", userDto.getHcid());
			} else {
				params.put("hcid", "-1");
			}
			params.put("startAge", startAge);
			params.put("endAge", endAge);
			// 查询
			List<FormModel> hlist = dataManageService.getALData(params);
			Map<String, Object> map = getIPeople(hlist);
			// 导出数据
			f = new File(this.setExcelData(req, map));
			String fileName = new String("体检异常统计报表.xls".getBytes("UTF-8"), "ISO8859-1");
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
			log.error("DMGListControl->exportData报错:", e);
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
	 * 数据审核页面(管理员)
	 */
	@RequestMapping(value = "/toDataReview1.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String toGenerally1(Model model, String hcid) {
		model.addAttribute("hcid", hcid);
		return "datamanage/dataReview1";
	}

	/**
	 * 查询审核信息(管理员)
	 */
	@RequestMapping(value = "/getDataReview1.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getDataReview1(HttpServletRequest req, HttpServletResponse response, Model model, String idCard,
			String name, String code, String startDate, String endDate, Integer selAge, Integer order, String filed,
			String hcid) {
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
			params.put("filed", null);
			if (order != null && order != 0) {
				if (1 == order) {// 升序
					params.put("filed", " order by " + filed);
				} else {// 降序
					params.put("filed", " order by " + filed + " DESC");
				}
			}
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
			params.put("hcid", hcid);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("datas", dataManageService.getDateList(params));
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("DMGListControl->getDataReview1报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 体检异常页面(管理员)
	 */
	@RequestMapping(value = "/abnormalityData1.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String abnormalityData1(Model model, String hcid) {
		model.addAttribute("hcid", hcid);
		return "datamanage/abnormalityData1";
	}

	/**
	 * 查询体检异常信息(管理员)
	 */
	@RequestMapping(value = "/getALData1.do", method = { RequestMethod.POST, RequestMethod.GET })
	public void getALData1(HttpServletRequest req, HttpServletResponse response, String startDate, String endDate,
			String startAge, String endAge, String hcid) {
		response.setContentType("application/json; charset=UTF-8");
		PrintWriter out = null;
		try {
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("startDate", startDate + " 00:00:00");
			params.put("endDate", endDate + " 23:59:59");
			params.put("hcid", hcid);
			params.put("startAge", startAge);
			params.put("endAge", endAge);
			// 查询
			List<FormModel> hlist = dataManageService.getALData(params);
			Map<String, Object> map = getIPeople(hlist);
			out = response.getWriter();
			out.print(JSON.toJSONString(map));
		} catch (Exception e) {
			log.error("DMGListControl->getALData1报错:", e);
		} finally {
			if (out != null) {
				out.flush();
				out.close();
			}
		}
	}

	/**
	 * 体检页面(管理员)
	 * 
	 * @param status(状态0数据审核1体检异常)
	 * @return
	 */
	@RequestMapping(value = "/healthCenter.do", method = { RequestMethod.POST, RequestMethod.GET })
	public String healthCenter(HttpServletRequest req, Model model, String status) {
		HttpSession session = req.getSession();
		UserDto userDto = (UserDto) session.getAttribute("user");
		if (userDto != null) {
			String areaCode = null;
			if (!StringUtils.isEmpty(userDto.getDistrict())) {
				areaCode = userDto.getDistrict();
			} else if (!StringUtils.isEmpty(userDto.getCity())) {
				areaCode = userDto.getCity();
			} else if (!StringUtils.isEmpty(userDto.getProvince())) {
				areaCode = userDto.getProvince();
			}
			model.addAttribute("code", areaCode);
		}
		model.addAttribute("status", status);
		return "datamanage/healthcenter";
	}

}
