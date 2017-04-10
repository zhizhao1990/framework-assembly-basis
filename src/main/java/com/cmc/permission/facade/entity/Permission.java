package com.cmc.permission.facade.entity;

import java.io.Serializable;
import javax.persistence.*;

public class Permission implements Serializable {

    @Id
    @Column(name = "perm_id")
    private Long permId;

    @Column(name = "parent_perm_id")
    private Long parentPermId;

    /**
     * 权限名称
     */
    @Column(name = "perm_name")
    private String permName;

    /**
     * 权限描述
     */
    private String description;

    private static final long serialVersionUID = 1L;

    /**
     * @return perm_id
     */
    public Long getPermId() {
        return permId;
    }

    /**
     * @param permId
     */
    public void setPermId(Long permId) {
        this.permId = permId;
    }

    /**
     * @return parent_perm_id
     */
    public Long getParentPermId() {
        return parentPermId;
    }

    /**
     * @param parentPermId
     */
    public void setParentPermId(Long parentPermId) {
        this.parentPermId = parentPermId;
    }

    /**
     * 获取权限名称
     * @return perm_name - 权限名称
     */
    public String getPermName() {
        return permName;
    }

    /**
     * 设置权限名称
     * @param permName 权限名称
     */
    public void setPermName(String permName) {
        this.permName = permName;
    }

    /**
     * 获取权限描述
     * @return description - 权限描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置权限描述
     * @param description 权限描述
     */
    public void setDescription(String description) {
        this.description = description;
    }
}