package com.cmc.common.utils;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 获取Spring的ApplicationContext
 * 
 * @author shin
 *
 */
public class ApplicationContextUtil implements ApplicationContextAware {
	
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationContextUtil.class);
	
	private static ApplicationContext appContext = null;

	@SuppressWarnings("static-access")
	@Override
	public void setApplicationContext(ApplicationContext appContext)
			throws BeansException {
		this.appContext = appContext;
		LOG.debug("AppContext set application context for spring");
	}
	
	/**
	 * 获取xml中定义的bean
	 * 
	 * @param beanId
	 * @param type
	 * @return
	 */
	public static <T> T getBean(String beanId, Class<T> type) {
		if(appContext != null)
			return appContext.getBean(beanId, type);
		LOG.warn("appContext is null");
		return null;
	}
	
	public static Object getBean(String beanId) {
		if(appContext != null)
			return appContext.getBean(beanId);
		LOG.warn("appContext is null");
		return null;
	}
	
	public static void setAppContextBySpringUtil(ServletContext context) {
		appContext = WebApplicationContextUtils.getWebApplicationContext(context);
	}
}
