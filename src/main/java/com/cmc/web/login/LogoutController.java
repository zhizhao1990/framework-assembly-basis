package com.cmc.web.login;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmc.common.constants.AjaxGeneralResult;
import com.cmc.common.constants.Constants;

/**
 * 登出控制器
 * 
 * @author LCB
 * @version 2016/07/20 16:28
 */
@Controller
@RequestMapping("logout.htm")
public class LogoutController {

	@ResponseBody
	@RequestMapping(method = RequestMethod.POST, params = "action=logout")
	public AjaxGeneralResult logout(HttpServletRequest req) {
		req.getSession().removeAttribute(Constants.USER_SESSION);
		return AjaxGeneralResult.getSuccessRst("登出");
	}

}