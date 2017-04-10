package com.cmc.common.utils;
//package com.ucmed.common.util;
//
//import org.apache.log4j.Logger;
//
//import com.ucmed.service.invoker.Service;
//import com.ucmed.service.invoker.ServiceInvoker;
//import com.ucmed.service.invoker.util.ApplicationContextUtil;
//
//import net.sf.json.JSONObject;
//
///**
// * @describe Service调用方法时的工具
// * @author yxl
// * @create_time 2014-6-26上午9:13:30
// */
//
//public class ServiceUtil {
//
//    private static final Logger LOG = Logger.getLogger(ServiceUtil.class);
//
//    private static ServiceInvoker serviceInvoker;
//
//    @SuppressWarnings("static-access")
//    public void setServiceInvoker(ServiceInvoker serviceInvoker) {
//        this.serviceInvoker = serviceInvoker;
//    }
//
//    public static JSONObject doService(String className, String methodName,
//            JSONObject params) {
//        serviceInvoker = ApplicationContextUtil.getBean("serviceInvoker",
//                ServiceInvoker.class);
//        Service s = new Service();
//        s.setMethod(methodName);
//        s.setService(className);
//        if(params != null) {
//            s.setParams(params);
//        }
//        try {
//            JSONObject res = serviceInvoker.doService(s).getParams();
//            if(res != null) {
//                return res;
//            }
//        } catch(Exception e) {
//            LOG.error("未知错误", e);
//        }
//        return null;
//    }
//}
