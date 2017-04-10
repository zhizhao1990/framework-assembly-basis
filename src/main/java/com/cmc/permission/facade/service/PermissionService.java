package com.cmc.permission.facade.service;

import java.util.List;

import com.cmc.permission.facade.model.PermissionModel;

public interface PermissionService {

    /**
     * 获取指定的权限信息
     * @author Thomas Lee
     * @version 2017年3月13日 下午6:01:43
     * @param permId 权限ID
     * @return 权限信息
     */
    public PermissionModel get(long permId);

    /**
     * 获取所有权限信息
     * @author Thomas Lee
     * @version 2017年3月13日 下午6:02:22
     * @return 所有权限信息
     */
    public List<PermissionModel> getAll();

}