package com.cmc.common.utils;

import java.util.Date;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

//import com.ucmed.common.ylpt.dataobject.SecurityCodeClass;


/**
 * 验证码相关操作
 * @author Zexin.Wang
 *
 */
//public class SecurityVoiceCodeUtil {
//	
//	private static Logger logger = Logger.getLogger(SecurityVoiceCodeUtil.class);
//
//	private static long expiredTime = 1800000;//30分钟
//	private Map<String, SecurityCodeClass> securityCodeMap = new ConcurrentHashMap<String, SecurityCodeClass>();
//	
//	public Map<String, SecurityCodeClass> getSecurityCodeMap() {
//		return securityCodeMap;
//	}
//
//	public void init() {
//		
//		Timer timer = new Timer();
//		timer.schedule(new TimerTask() {
//			@Override
//			public void run() {
//				for (String m : securityCodeMap.keySet()) {
//					if (securityCodeMap.get(m).getCreateTime().getTime() < ((new Date())
//							.getTime() - expiredTime)) {
//						securityCodeMap.remove(m);
//						
//						logger.info("remove securityCode "+ securityCodeMap.get(m)
//								.getSecurityCode() + " for phone " + m);
//					}
//				}
//			}
//		}, 0, 5000);
//	}
	
	/**
	 * 检验验证码是否正确
	 * @author Zexin.Wang
	 * @param phone
	 * @param securityCode
	 * @return
	 */
//	public boolean checkSecurityCode(String phone, String securityCode) {
//		return (securityCodeMap.containsKey(phone) && securityCodeMap
//				.get(phone).getSecurityCode().equals(securityCode));
//	}
//	
//	private void insertMap(String phone, String code,String type) {
//		SecurityCodeClass s = new SecurityCodeClass(code,type);
//		securityCodeMap.put(phone, s);
//	}
	
	/**
	 * 获取验证码并返回
	 * @author Zexin.Wang
	 * @param phone
	 * @param type
	 * @return
	 * 
	 */
//	public String getSecurityCode(String phone,String type){
//		String securityCode = SysUtil.getRandomString(4, 0);
//		if(null == securityCodeMap.get(phone)){
//			insertMap(phone,securityCode,type);
//		}else{
//			securityCode = securityCodeMap.get(phone).getSecurityCode();
//		}
//		
//		logger.info(phone+" get securityCode "+ securityCode +" for "+type);
//		
//		return securityCode;
//	}

//}
