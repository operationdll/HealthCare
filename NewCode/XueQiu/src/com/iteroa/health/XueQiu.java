package com.iteroa.health;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.iteroa.util.DateLog;
import com.iteroa.util.XueQiuDao;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * 血球信息获取
 * 
 * @author Daniel Duan
 * @date: 24/04/2019
 *
 */
public class XueQiu implements SerialPortEventListener {

	private static final String PORTNAME = "血球串口";

	/**
	 * 检测系统中可用的端口
	 */
	private CommPortIdentifier portId;
	/**
	 * 枚举类
	 */
	@SuppressWarnings("rawtypes")
	private Enumeration portList;
	/**
	 * 输入流
	 */
	private static InputStream inputStream;
	/**
	 * RS-232的串行口
	 */
	private SerialPort serialPort;
	/**
	 * 获取日志类
	 */
	private Logger log = Logger.getLogger(XueQiu.class);

	/**
	 * 监听串口
	 * 
	 * @param COM      串口号
	 * @param baudRate 波特率
	 * @param logInt   是否输出日志(0为不输出1为输出)
	 * @throws Exception
	 */
	public void init(String COM, int baudRate) throws Exception {
		// 获取系统中可用的端口
		portList = CommPortIdentifier.getPortIdentifiers();
		while (portList.hasMoreElements()) {
			portId = (CommPortIdentifier) portList.nextElement();
			if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL && portId.getName().equals(COM)) {
				serialPort = (SerialPort) portId.open(PORTNAME, 2000);
				// 设置串口监听
				serialPort.addEventListener(new XueQiu());
				// 设置开启监听
				serialPort.notifyOnDataAvailable(true);
				// 设置波特率、数据位、停止位、检验位
				serialPort.setSerialPortParams(baudRate, SerialPort.DATABITS_8, SerialPort.STOPBITS_1,
						SerialPort.PARITY_NONE);
				// 获取输入流
				inputStream = serialPort.getInputStream();
			}
		}
		if (serialPort == null) {
			throw new Exception("未找到对应串口号" + COM);
		}
	}

	/**
	 * 监听函数
	 * 
	 * @param serialPortEvent
	 */
	public void serialEvent(SerialPortEvent serialPortEvent) {
		switch (serialPortEvent.getEventType()) {
		// 获取到有效信息
		case SerialPortEvent.DATA_AVAILABLE:
			try {
				readInfoStream(inputStream);
			} catch (Exception e) {
				log.error("血球信息报错:", e);
			}
			break;
		default:
			break;
		}
	}

	// 接收的完整数据字符串
	private String reStr = "";

	public void readInfoStream(InputStream input) throws Exception {
		try {
			// 字节数组
			byte[] bcache = new byte[input.available()];
			int readSize = 0;// 每次读取的字节长度
			ByteArrayOutputStream infoStream = new ByteArrayOutputStream();
			while ((readSize = input.read(bcache)) > 0) {
				// 将bcache中读取的input数据写入infoStream
				infoStream.write(bcache, 0, readSize);
			}
			// 接收的数据
			String dataStr = infoStream.toString();
			reStr = reStr + dataStr;
			// 结束本次接收数据
			if (reStr.endsWith("TRANSFER FINISH")) {
				// 把接收的数据输出到日志文件中
				DateLog.writeDate(reStr);
				// 封装数据
				String[] strArr = reStr.split("TRANSFER FINISH");
				for (String str : strArr) {
					if (str.startsWith("R,")) {
						String[] data = str.split(",");
						if (data.length > 71) {
							// 档案编号
							String code = data[10];
							if (code != null && code.length() == 12) {
								// 白细胞 98
								String WBC = data[17];
								if (WBC != null && !"".equals(WBC)) {
									WBC = WBC.split(" ")[0];
								}
								// 淋巴细胞绝对值 234
								String LYM = data[20];
								if (LYM != null && !"".equals(LYM)) {
									LYM = LYM.split(" ")[0];
								}
								// 中间细胞绝对值 235
								String MID = data[23];
								if (MID != null && !"".equals(MID)) {
									MID = MID.split(" ")[0];
								}
								// 中性粒细胞绝对值 236
								String GRA = data[26];
								if (GRA != null && !"".equals(GRA)) {
									GRA = GRA.split(" ")[0];
								}
								// 淋巴细胞百分比 224
								String LYMP = data[29];
								if (LYMP != null && !"".equals(LYMP)) {
									LYMP = LYMP.split(" ")[0];
								}
								// 中间细胞百分比 225
								String MIDP = data[32];
								if (MIDP != null && !"".equals(MIDP)) {
									MIDP = MIDP.split(" ")[0];
								}
								// 中性粒细胞百分比 226
								String GRAP = data[35];
								if (GRAP != null && !"".equals(GRAP)) {
									GRAP = GRAP.split(" ")[0];
								}
								// 红细胞计数 222
								String RBC = data[38];
								if (RBC != null && !"".equals(RBC)) {
									RBC = RBC.split(" ")[0];
								}
								// 血红蛋白 97
								String HGB = data[41];
								if (HGB != null && !"".equals(HGB)) {
									HGB = HGB.split(" ")[0];
								}
								// 平均红细胞血红蛋白浓度 227
								String MCHC = data[44];
								if (MCHC != null && !"".equals(MCHC)) {
									MCHC = MCHC.split(" ")[0];
								}
								// 平均红细胞血红蛋白含量 229
								String MCH = data[47];
								if (MCH != null && !"".equals(MCH)) {
									MCH = MCH.split(" ")[0];
								}
								// 平均红细胞体积 228
								String MCV = data[50];
								if (MCV != null && !"".equals(MCV)) {
									MCV = MCV.split(" ")[0];
								}
								// 红细胞分布宽度标准差 243
								String RDWCV = data[53];
								if (RDWCV != null && !"".equals(RDWCV)) {
									RDWCV = RDWCV.split(" ")[0];
								}
								// 红细胞分布宽度变异系数 231
								String RDWSD = data[56];
								if (RDWSD != null && !"".equals(RDWSD)) {
									RDWSD = RDWSD.split(" ")[0];
								}
								// 红细胞压积 223
								String HCT = data[59];
								if (HCT != null && !"".equals(HCT)) {
									HCT = HCT.split(" ")[0];
								}
								// 血小板 99
								String PLT = data[62];
								if (PLT != null && !"".equals(PLT)) {
									PLT = PLT.split(" ")[0];
								}
								// 平均血小板体积 232
								String MPV = data[65];
								if (MPV != null && !"".equals(MPV)) {
									MPV = MPV.split(" ")[0];
								}
								// 血小板分布宽度 230
								String PDW = data[68];
								if (PDW != null && !"".equals(PDW)) {
									PDW = PDW.split(" ")[0];
								}
								// 血小板压积 233
								String PCT = data[71];
								if (PCT != null && !"".equals(PCT)) {
									PCT = PCT.split(" ")[0];
								}
								// 封装参数
								Map<String, String> params = new HashMap<String, String>();
								params.put("field98", WBC);
								params.put("field234", LYM);
								params.put("field235", MID);
								params.put("field236", GRA);
								params.put("field224", LYMP);
								params.put("field225", MIDP);
								params.put("field226", GRAP);
								params.put("field222", RBC);
								params.put("field97", HGB);
								params.put("field227", MCHC);
								params.put("field229", MCH);
								params.put("field228", MCV);
								params.put("field243", RDWCV);
								params.put("field231", RDWSD);
								params.put("field223", HCT);
								params.put("field99", PLT);
								params.put("field232", MPV);
								params.put("field230", PDW);
								params.put("field233", PCT);
								params.put("code", code);
								params.put("No", data[1]);
								new XueQiuDao(params);
							}
						} else {
							log.error("血球信息错误:" + reStr);
						}
					}
				}
				reStr = "";
			}
		} finally {
			// 输入流关闭
			if (input != null) {
				input.close();
			}
		}
	}

	public void close() {
		serialPort.close();
	}

}
