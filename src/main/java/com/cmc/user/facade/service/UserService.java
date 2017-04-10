package com.cmc.user.facade.service;

import com.cmc.common.BizException;
import com.cmc.common.utils.PaginationResult;
import com.cmc.user.facade.model.UserModel;

/**
 * User Interface
 * 
 * @author Thomas Lee
 * @since 2016年12月22日 上午9:50:01
 */
public interface UserService {

    /**
     * 添加用户
     * @param dto
     * @return
     */
    boolean add(UserModel dto) throws BizException;

    public String getArg1();

    public String getCommon();

    /**
     * 显示用户列表
     * @return
     */
    PaginationResult<UserModel> list(Long pageNo, Long pageSize);

    /**
     * 显示用户
     * @param dto
     * @return
     */
    UserModel get(UserModel dto);

    /**
     * 修改用户
     * @param dto
     * @return
     */
    boolean update(UserModel dto);

    /**
     * 删除用户
     * 
     * @param dto
     * @return
     */
    boolean delete(UserModel dto);

    /**
     * 通过用户名获取用户信息
     * @param dto UserDTO{name}
     * @return
     */
    UserModel getByName(UserModel dto);

}