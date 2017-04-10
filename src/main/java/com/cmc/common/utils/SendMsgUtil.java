package com.cmc.common.utils;
//package com.ucmed.common.util;
//
//import org.json.JSONObject;
//
//public class SendMsgUtil {
//
//	/**
//	 * 发送短信、可以发送定时和即时短信 sendSMS(String[] mobiles,String smsContent, String
//	 * addSerial, int smsPriority) 1、mobiles 手机数组长度不能超过1000 2、smsContent
//	 * 最多500个汉字或1000个纯英文
//	 * 、请客户不要自行拆分短信内容以免造成混乱、亿美短信平台会根据实际通道自动拆分、计费以实际拆分条数为准、亿美推荐短信长度70字以内
//	 * 3、addSerial 附加码(长度小于15的字符串) 用户可通过附加码自定义短信类别,或添加自定义主叫号码( 联系亿美索取主叫号码列表)
//	 * 4、优先级范围1~5，数值越高优先级越高(相对于同一序列号) 5、其它短信发送请参考使用手册自己尝试使用
//	 */
//	public static int sendSMS(String phone, String msg) {
//		JSONObject params = new JSONObject();
//		JSONObject ret = null;
//		try {
//			params.put("msg", msg);
//			params.put("phone", phone);
//			ret = TortoiseUtil.extend(params);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ret.optInt("result");
//	}
//	public static void main(String[] args) {
//		sendSMSByTortoise("abcd","https://www.baidu.com测试长度测试长度测试长度测试长度测试长度测试http://www.baidu.com长度测试长度测试长度测试长度测试长度玄武短信https://www.baidu.com玄武短信https://www.baidu.com玄武短信https://www.baidu.com玄武短信https://www.baidu.com玄武短信https://www.baidu.com玄武短信https://www.baidu.com/183057503751830575037518305750375");//18305750375
//	}
//
//	public static int sendSMSByTortoise(String phone, String msg) {
//		JSONObject params = new JSONObject();
//		JSONObject ret = null;
//		try {
//			params.put("msg", msg);
//			params.put("phone", phone);
//			ret = TortoiseUtil.extend(params);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return ret.optInt("result");
//	}
//}
