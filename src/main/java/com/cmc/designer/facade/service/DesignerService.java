package com.cmc.designer.facade.service;

import java.util.List;

import com.cmc.designer.facade.model.DesignerModel;

/**
 * Designer Service
 * @author Thomas Lee
 * @version 2017年3月21日 下午2:00:05
 */
public interface DesignerService {

    List<DesignerModel> getDesigners();

    void testHibernateCRUD();
    
    void testHibernateThreeStatus();
    
}