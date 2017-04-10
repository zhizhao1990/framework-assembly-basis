package com.cmc.service.login;

import com.cmc.common.constants.LoginStatus;
import com.cmc.common.constants.SignUpStatus;
import com.cmc.user.facade.model.UserModel;

public interface LoginService {

	/**
	 * 登录
	 * @param name 用户名
	 * @param password 密码
	 * @return
	 */
	LoginStatus login(UserModel dto);

	boolean logout(String name);

	SignUpStatus signUp(UserModel dto);

}