package com.cmc.web.login;

import java.io.FileNotFoundException;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmc.common.constants.AjaxGeneralResult;
import com.cmc.common.constants.Constants;
import com.cmc.common.constants.LoginStatus;
import com.cmc.common.constants.SignUpStatus;
import com.cmc.service.login.LoginService;
import com.cmc.user.facade.model.UserModel;
import com.cmc.user.facade.service.UserService;

@Controller
@RequestMapping("login.htm")
public class LoginController {

    @SuppressWarnings("unused")
    private static Logger LOG = LoggerFactory.getLogger(Constants.class);

    @Autowired
    private LoginService loginService;

    @Autowired
    private UserService userService;

    @RequestMapping(method = RequestMethod.GET, params = "action=login")
    public String login(HttpServletRequest req) throws FileNotFoundException {
        // InputStream ins =
        // this.getClass().getClassLoader().getResourceAsStream("config.properties");
        // ClassLoader.getSystemResourceAsStream ("");
        // req.getServletContext().getRealPath("");
        // InputStream in = new FileInputStream(news
        // File(req.getServletContext().getRealPath("WEB-INF\\classes\\config.properties")));
        // String tmp = Constants.TMP;
        return "login/login";
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, params = "action=login")
    public AjaxGeneralResult login(HttpServletRequest request, UserModel dto) {
        LoginStatus status = loginService.login(dto);
        if (LoginStatus.SUCCESS == status) {
            UserModel userDTO = userService.getByName(dto);
            request.getSession().setAttribute(Constants.USER_SESSION, userDTO);
        }
        return AjaxGeneralResult.getGeneralRst(status.getCode(), status.getDesc());
    }

    @ResponseBody
    @RequestMapping(method = RequestMethod.POST, params = "action=signup")
    public AjaxGeneralResult signUp(UserModel dto) {
        SignUpStatus status = loginService.signUp(dto);
        return AjaxGeneralResult.getGeneralRst(status.getCode(), status.getDesc());
    }

}