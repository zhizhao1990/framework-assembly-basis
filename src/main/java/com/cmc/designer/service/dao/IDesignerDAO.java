package com.cmc.designer.service.dao;

import java.util.List;

import com.cmc.designer.facade.entity.Designer;

public interface IDesignerDAO {

    /**
     * 查询设计师列表 
     * @author Thomas Lee
     * @version 2017年3月21日 下午9:17:16
     * @return 设计师列表
     */
    List<Designer> selectDesigners();

    /**
     * 插入设计师
     * @author Thomas Lee
     * @version 2017年3月21日 下午9:17:39
     * @param mDesigner
     * @return 是否插入成功
     */
    boolean insert(Designer designer);

    /**
     * 通过ID获取设计师信息
     * @author Thomas Lee
     * @version 2017年3月21日 下午9:20:23
     * @param id
     * @return 设计师信息
     */
    Designer select(int id);

    /**
     * 更新设计师信息
     * @author Thomas Lee
     * @version 2017年3月21日 下午9:23:34
     * @param designer
     */
    void update(Designer designer);

    /**
     * 删除设计师信息
     * @author Thomas Lee
     * @version 2017年3月21日 下午9:26:10
     * @param designer
     */
    void delete(Designer designer);

}