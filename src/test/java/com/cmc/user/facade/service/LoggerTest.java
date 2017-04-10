package com.cmc.user.facade.service;

import org.apache.log4j.Logger;
import org.junit.Test;

public class LoggerTest {

	private static final Logger logger = Logger.getLogger(LoggerTest.class);

	@Test
	public void testLogger() {
		logger.debug("Logger DEBUG tests.");
		logger.error("Logger ERROR tests.");
	}

}
