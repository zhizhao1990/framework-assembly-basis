package com.cmc.permission.service.dao;

import org.apache.ibatis.annotations.Param;

import com.cmc.common.BaseMapper;
import com.cmc.permission.facade.entity.Permission;

public interface PermissionMapper extends BaseMapper<Permission> {

    /**
     * 通过权限ID获取权限信息
     * @author Thomas Lee
     * @version 2017年3月14日 上午10:54:00
     * @param permId 权限ID
     * @return 权限信息
     */
    Permission selectByPermId(@Param("permId") long permId);

}