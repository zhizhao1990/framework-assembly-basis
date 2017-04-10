package com.cmc.common.utils;

import org.apache.commons.lang.StringUtils;
//import org.jpxx.commons.cache.CacheManager;


/**
 * 验证码相关操作
 * @author Zexin.Wang
 *
 */
public class SecurityCodeUtil {
	
	private static Integer expiredTime = 1800;//30分钟
	
	private static final String PHONE_PREFIX = "wxsecuritycode";
	
	/**
	 * 将电话号码加上前缀，作为存入memcached的key
	 * @author Zexin.Wang
	 * @param phone
	 * @return
	 * @date 2016年1月6日 上午9:35:20 
	 * @description
	 */
	private String getPhoneKey(String phone) {
	    return PHONE_PREFIX + phone;
	}

	/**
	 *  检验验证码是否正确
	 * @author Zexin.Wang
	 * @param phone
	 * @param securityCode
	 * @return
	 * @date 2016年1月6日 上午9:43:11 
	 * @description
	 */
//	public boolean checkSecurityCode(String phone, String securityCode,CacheManager cacheManager) {
//	    String phoneKey = getPhoneKey(phone);
//	    String code = (String)cacheManager.get(phoneKey);
//		return (StringUtils.isNotBlank(code)&&code.equals(securityCode));
//	}
	
	/**
	 * 获取验证码并返回
	 * @author Zexin.Wang
	 * @param phone
	 * @param type
	 * @return
	 * 
	 */
//	public String getSecurityCode(String phone,String type,CacheManager cacheManager){
//		String phoneKey = getPhoneKey(phone);
//        String code = (String)cacheManager.get(phoneKey);
//		if(StringUtils.isBlank(code)){
//		    code = SysUtil.getRandomString(4, 0);
//			cacheManager.put(phoneKey, code, expiredTime);
//		}
//		return code;
//	}

}
