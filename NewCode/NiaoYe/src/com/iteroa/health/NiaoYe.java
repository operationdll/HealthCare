package com.iteroa.health;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

import com.iteroa.util.DateLog;
import com.iteroa.util.NiaoYeDao;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;

/**
 * 尿液信息获取
 * 
 * @author Daniel Duan
 * @date: 24/04/2019
 *
 */
public class NiaoYe implements SerialPortEventListener {

	private static final String PORTNAME = "尿液串口";

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
	private Logger log = Logger.getLogger(NiaoYe.class);

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
				serialPort.addEventListener(new NiaoYe());
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
				log.error("尿液信息报错:", e);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * 获取值方法
	 * 
	 * @param str   传入的字符串
	 * @param sign1 开始标志位
	 * @param sign2 结束标志位
	 * @return 值和单位,第一个为值,第二个为单位
	 */
	private String[] getValue(String str, String sign1, String sign2) {
		String[] rearr = new String[2];
		String value = "";
		if (str.indexOf(sign1) != -1 && str.indexOf(sign2) != -1) {
			int begin = str.indexOf(sign1) + sign1.length();
			int end = str.indexOf(sign2, begin);
			if (begin != -1 && end != -1) {
				value = str.substring(begin, end);
			}
		}
		value = value.trim();
		String[] strArr = value.split(" ");
		rearr[0] = strArr[0];
		if (strArr.length > 1) {
			rearr[1] = value.substring(value.lastIndexOf(" ")).trim();
		} else {
			rearr[1] = "";
		}
		return rearr;
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
			if (reStr.endsWith("")) {
				// 把接收的数据输出到日志文件中
				DateLog.writeDate(reStr);
				// 封装数据
				String[] strArr = reStr.split("");
				for (String retult : strArr) {
					// 去除换行符和制表符
					Pattern p = Pattern.compile("\t|\r|\n");
					Matcher m = p.matcher(retult);
					retult = m.replaceAll("");

					// code
					String[] resultArr = getValue(retult, "NAME:", "ID");
					String code = resultArr[0];
					if (code != null && code.length() > 11) {
						Map<String, String> params = new HashMap<String, String>();

						resultArr = getValue(retult, "LEU", "NIT");
						String leu = resultArr[0];
						String leuUnit = resultArr[1];
						params.put("field240", leu);
						params.put("field265", leuUnit);
						
						resultArr = getValue(retult, "NIT", "URO");
						String nit = resultArr[0];
						String nitUnit = resultArr[1];
						params.put("field239", nit);
						params.put("field264", nitUnit);

						resultArr = getValue(retult, "PRO", "PH");
						String pro = resultArr[0];
						String proUnit = resultArr[1];
						params.put("field101", pro);
						params.put("field258", proUnit);

						resultArr = getValue(retult, "URO", "PRO");
						String uro = resultArr[0];
						String uroUnit = resultArr[1];
						params.put("field238", uro);
						params.put("field261", uroUnit);

						resultArr = getValue(retult, "PH", "BLO");
						String ph = resultArr[0];
						String phUnit = resultArr[1];
						params.put("field242", ph);
						params.put("field267", phUnit);

						resultArr = getValue(retult, "BLO", "SG");
						String blo = resultArr[0];
						String bloUnit = resultArr[1];
						params.put("field104", blo);
						params.put("field262", bloUnit);

						resultArr = getValue(retult, "SG", "KET");
						String sg = resultArr[0];
						String sgUnit = resultArr[1];
						params.put("field241", sg);
						params.put("field266", sgUnit);

						resultArr = getValue(retult, "BIL", "GLU");
						String bil = resultArr[0];
						String bilUnit = resultArr[1];
						params.put("field237", bil);
						params.put("field260", bilUnit);

						resultArr = getValue(retult, "GLU", "ASC");
						String glu = resultArr[0];
						String gluUnit = resultArr[1];
						params.put("field102", glu);
						params.put("field259", gluUnit);

						resultArr = getValue(retult, "KET", "BIL");
						String ket = resultArr[0];
						String ketUnit = resultArr[1];
						params.put("field103", ket);
						params.put("field263", ketUnit);

						params.put("code", code);
						new NiaoYeDao(params);
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
