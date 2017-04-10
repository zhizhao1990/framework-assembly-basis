package com.cmc.common.utils;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateCalculate {

	private static final Logger LOG = LoggerFactory.getLogger(DateCalculate.class);

	/**
	 * 计算两个日期时间相差的分钟数
	 * @param date1
	 * @param date2
	 * @return
	 */
	public static double dateMinus(Date date1, Date date2) {
		double minutes = 0;
		try {
			double time1 = date1.getTime();
			double time2 = date2.getTime();
			double timeDiffer = (time1 - time2) / 1000 / 60; //时间换算为分钟数
			minutes = Math.abs(timeDiffer);
		} catch (Exception e) {
			LOG.error("dateminus error in datecalculate ", e);
		}
		return minutes;
	}
}
