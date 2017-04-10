package com.cmc.service.login;

import java.util.Date;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.cmc.common.constants.LoginStatus;
import com.cmc.common.constants.SignUpStatus;
import com.cmc.common.utils.ModelDataObjectUtil;
import com.cmc.user.facade.entity.User;
import com.cmc.user.facade.model.UserModel;
import com.cmc.user.service.dao.UserMapper;

public class LoginServiceImpl implements LoginService {
    
    @Autowired
	private UserMapper userMapper;

	public LoginStatus login(UserModel dto) {
		if (successLogin(dto)) {
			return LoginStatus.SUCCESS;
		} else {
			User user = userMapper.selectByName(dto.getName());
			if (null == user) {
				return LoginStatus.USERNAME_NO_EXIST;
			} else if (!StringUtils.equals(dto.getTemp1(), user.getTemp1())) {
				return LoginStatus.PASSWORD_ERROR;
			}
		}
		return LoginStatus.UNKNOWN_ERROR;
	}

	@Override
	public boolean logout(String name) {
		return false;
	}

	@Override
	public SignUpStatus signUp(UserModel dto) {
		User user = userMapper.selectByName(dto.getName());
		if (null != user) {
			return SignUpStatus.USERNAME_ALREADY_EXIST;
		}
		if (successSignUp(dto)) {
			return SignUpStatus.SUCCESS;
		}
		return SignUpStatus.UNKNOWN_ERROR;
	}

	private boolean successLogin(UserModel dto) {
		User user = userMapper.selectByName(dto.getName());
		return user != null && StringUtils.equals(dto.getTemp1(), user.getTemp1());
	}

	private boolean successSignUp(UserModel dto) {
		User user = ModelDataObjectUtil.model2do(dto, User.class);
		user.setId(UUID.randomUUID().toString());
		user.setCreateTime(new Date());
		return 0 == userMapper.insertSelective(user) ? false : true;
	}

}