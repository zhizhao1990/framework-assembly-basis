package com.cmc.user.service.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.util.Args;

import com.cmc.common.utils.DBDataUtil;
import com.cmc.common.utils.IdHandler;
import com.cmc.common.utils.ModelDataObjectUtil;
import com.cmc.common.utils.PaginationResult;
import com.cmc.user.facade.entity.User;
import com.cmc.user.facade.model.UserModel;
import com.cmc.user.facade.service.UserService;
import com.cmc.user.service.dao.UserMapper;

/**
 * 
 * @author Thomas Lee
 * @date 2017年3月3日 下午6:25:51
 */
public class UserServiceImpl implements UserService {

    private String arg1;
    private String arg2;

    public UserServiceImpl() {
    }

    public UserServiceImpl(String arg1, String arg2) {
        super();
        this.arg1 = arg1;
        this.arg2 = arg2;
    }

    public String getArg1() {
        return arg1;
    }

    public void setArg1(String arg1) {
        this.arg1 = arg1;
    }

    public String getArg2() {
        return arg2;
    }

    public void setArg2(String arg2) {
        this.arg2 = arg2;
    }

    private UserMapper userMapper;

    private String common;

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public UserMapper getUserMapper() {
        return userMapper;
    }

    public void setUserMapper(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public boolean add(UserModel dto) {
        // 数据校验：后台校验
        // Args.check(StringUtils.isNotEmpty(dto.getName()), "用户姓名");

        User user = ModelDataObjectUtil.model2do(dto, User.class);
        user.setId(UUID.randomUUID().toString());
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        return 0 == userMapper.insertSelective(user) ? false : true;
    }

    @Override
    public PaginationResult<UserModel> list(Long pageNo, Long pageSize) {
        PaginationResult<UserModel> rst = new PaginationResult<UserModel>();

        rst.setPageNo(pageNo);

        Long pageCount = userMapper.countUsers();
        rst.setPageCount(pageCount);

        Long begin = pageSize * (pageNo - 1);
        Long offset = pageSize;
        List<User> users = userMapper.selectList(begin, offset);
        List<UserModel> dtos = new ArrayList<UserModel>();
        for (UserModel dto : ModelDataObjectUtil.doList2modelList(users, UserModel.class)) {
            dto.setStrSex(DBDataUtil.transSexToFact(dto.getSex()));
            dtos.add(dto);
        }
        rst.setList(dtos);

        return rst;
    }

    @Override
    public UserModel get(UserModel dto) {
        String id = dto.getId();
        Args.notBlank(id, "用户id");
        User user = userMapper.selectByPrimaryKey(IdHandler.idDecrypt(id));
        return ModelDataObjectUtil.do2model(user, UserModel.class);
    }

    @Override
    public boolean update(UserModel dto) {
        User user = ModelDataObjectUtil.model2do(dto, User.class);
        user.setUpdateTime(new Date());
        return 0 == userMapper.updateByPrimaryKeySelective(user) ? false : true;
    }

    @Override
    public boolean delete(UserModel dto) {
        User user = ModelDataObjectUtil.model2do(dto, User.class);
        String id = user.getId();
        Args.notBlank(id, "用户id");
        user.setDeleteTime(new Date());
        return 0 == userMapper.deleteByPrimaryKey(id) ? false : true;
    }

    @Override
    public UserModel getByName(UserModel dto) {
        User user = userMapper.selectByName(dto.getName());
        return ModelDataObjectUtil.do2model(user, UserModel.class);
    }

}