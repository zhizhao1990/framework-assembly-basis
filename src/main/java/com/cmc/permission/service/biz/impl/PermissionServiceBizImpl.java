package com.cmc.permission.service.biz.impl;

import java.util.List;

import com.cmc.common.utils.ModelDataObjectUtil;
import com.cmc.permission.facade.entity.Permission;
import com.cmc.permission.facade.model.PermissionModel;
import com.cmc.permission.facade.service.PermissionService;
import com.cmc.permission.service.dao.PermissionMapper;

public class PermissionServiceBizImpl implements PermissionService {

    private PermissionMapper permissionMapper;

    public void setPermissionMapper(PermissionMapper permissionMapper) {
        this.permissionMapper = permissionMapper;
    }

    @Override
    public PermissionModel get(long permId) {
        /* 使用BaseMapper中的方法进行查询
        Permission permissionForQuery = new Permission();
        permissionForQuery.setPermId(permId);
        Permission permission = permissionMapper.selectOne(permissionForQuery);
        */

        // 使用自定义的方法进行查询
        Permission permission = permissionMapper.selectByPermId(permId);
        return ModelDataObjectUtil.do2model(permission, PermissionModel.class);
    }

    @Override
    public List<PermissionModel> getAll() {
        List<Permission> permissions = permissionMapper.selectAll();
        return ModelDataObjectUtil.doList2modelList(permissions, PermissionModel.class);
    }

}