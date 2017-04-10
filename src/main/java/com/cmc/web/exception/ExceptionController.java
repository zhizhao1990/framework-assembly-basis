package com.cmc.web.exception;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 异常统一处理入口
 * 处理方法：
 * 1. 利用Servlet原生（在xml中配置<error-page>）
 * 2. 利用Spring提供的HandlerExceptionResolver 
 * @author lcb
 */
@Controller
@RequestMapping("error.htm")
public class ExceptionController {

}