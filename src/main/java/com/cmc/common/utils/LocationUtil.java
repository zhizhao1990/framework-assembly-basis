/**
 * 
 */
package com.cmc.common.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Locale;

import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * @author John Lee
 * 
 */
public class LocationUtil {

	private static final Logger logger = Logger.getLogger(LocationUtil.class);

	/**
	 * 
	 * @param locale
	 * @return
	 */
	public String loadLocation(Locale locale) {

		String localeStr = locale.toString();
		String path = "/location_" + localeStr + ".xml";
		InputStream is = this.getClass().getResourceAsStream(path);
		if (null == is) {
			path = "/location_zh_CN.xml";
			is = this.getClass().getResourceAsStream(path);
		}

		StringBuffer buf = new StringBuffer();

		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line = null;
			while (null != (line = reader.readLine())) {
				buf.append(line);
			}
		} catch (IOException e) {
			logger.error("", e);
		}

		return buf.toString();
	}

	@SuppressWarnings("unchecked")
	public String loadChineseLocation() {

		String path = "/location_zh_CN.xml";
		InputStream is = this.getClass().getResourceAsStream(path);
		SAXReader saxReader = new SAXReader();
		try {
			Document document = saxReader.read(is);
			Element root = document.getRootElement();
			for (Element e : (List<Element>) root.elements()) {
				// 找到中国的地区
				if (e.attribute("Code").getValue().equals("1")) {
					String s = e.asXML();
					logger.debug(s);
					return s;
				}
			}
		} catch (DocumentException e) {
			logger.error("InputStream:" + is, e);
		}

		return "";
	}
}
