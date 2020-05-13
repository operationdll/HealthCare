package com.iteroa.util;

import java.io.*;
import java.util.*;

import org.dom4j.*;
import org.dom4j.io.*;

public class XMLRead {

	@SuppressWarnings("rawtypes")
	public static List<Map<String, String>> getResult() throws Exception {
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		File file = new File(ApiURL.getFileURL());
		File[] tempList = file.listFiles();
		for (File f : tempList) {
			if (f.isFile()) {
				String fileName = f.getName();
				String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
				if (suffix != null && "XML".equals(suffix.toUpperCase())) {
					Map<String, String> map = new HashMap<String, String>();
					SAXReader reader = new SAXReader();
					Document doc = reader.read(f);
					Element root = doc.getRootElement();
					Element foo;
					String code = "";
					for (Iterator i = root.elementIterator("examinfo"); i.hasNext();) {
						foo = (Element) i.next();
						code = foo.elementText("barcode");
					}
					String xinlv = "";
					String result = "";
					String isOk = "";
					int num = 0;
					for (Iterator i = root.elementIterator("examitem"); i.hasNext();) {
						foo = (Element) i.next();
						if (num == 0) {
							xinlv = foo.elementText("result");
							num++;
						} else if (num == 1) {
							result = foo.elementText("result");
							isOk = foo.elementText("unit");
						}
					}
					map.put("code", code);
					map.put("xinlv", xinlv);
					map.put("result", result);
					map.put("isOk", isOk);
					list.add(map);
				}
			}
		}
		return list;
	}

	public static void main(String[] args) {
		try {
			List<Map<String, String>> list = XMLRead.getResult();
			for (Map<String, String> m : list) {
				System.out.println(m);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
