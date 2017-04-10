package com.cmc.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

public class JSONUtils {
	/**
	 * convert jsonObject to object
	 * @param obj
	 * @param c
	 * @return
	 */
	public static <T> T jsonToObject(JSONObject obj, Class<T> c) {
		return c.cast(JSONObject.toBean(obj, c));
	}

	public static JSONObject modelToJson(Object model) {
		return modelToJson(model, null);
	}

	/**
	 * @param model
	 *            传入要转化为JOSNObject的model
	 * @param dateFormat
	 *            转化的日期格式，不传的话默认为yyyy-MM-dd HH:mm:ss
	 * @return
	 */
	public static JSONObject modelToJson(Object model, String dateFormat) {
		JsonConfig jsonConfig = new JsonConfig();
		String date = dateFormat == null ? null : dateFormat;
		jsonConfig.registerJsonValueProcessor(Date.class, new JsonDateValueProcessor(date));
		JSONObject b = JSONObject.fromObject(model, jsonConfig);
		return b;
	}

	private static class JsonDateValueProcessor implements JsonValueProcessor {
		private String format = "yyyy-MM-dd HH:mm:ss";

		public JsonDateValueProcessor(String format) {
			super();
			if (format != null) {
				this.format = format;
			}
		}

		@Override
		public Object processArrayValue(Object paramObject, JsonConfig paramJsonConfig) {
			return process(paramObject);
		}

		@Override
		public Object processObjectValue(String paramString, Object paramObject, JsonConfig paramJsonConfig) {
			return process(paramObject);
		}

		private Object process(Object value) {
			if (value instanceof Date) {
				SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.CHINA);
				return sdf.format(value);
			}
			return value == null ? "" : value.toString();
		}
	}
}
