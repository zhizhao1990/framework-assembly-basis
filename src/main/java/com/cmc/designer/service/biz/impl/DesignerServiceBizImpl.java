package com.cmc.designer.service.biz.impl;

import java.util.List;

import com.cmc.common.utils.ModelDataObjectUtil;
import com.cmc.designer.facade.entity.Designer;
import com.cmc.designer.facade.model.DesignerModel;
import com.cmc.designer.facade.service.DesignerService;
import com.cmc.designer.service.dao.IDesignerDAO;

public class DesignerServiceBizImpl implements DesignerService {

    private IDesignerDAO designerDAO;

    public void setDesignerDAO(IDesignerDAO designerDAO) {
        this.designerDAO = designerDAO;
    }

    @Override
    public List<DesignerModel> getDesigners() {
        List<Designer> designers = designerDAO.selectDesigners();
        return ModelDataObjectUtil.doList2modelList(designers, DesignerModel.class);
    }

    @Override
    public void testHibernateCRUD() {
        Designer designer = new Designer();
        designer.setAge(10);
        designer.setNiceName("NICE");
        designer.setUserName("LCB");
        designerDAO.insert(designer);
    }

    @Override
    public void testHibernateThreeStatus() {
        Designer designer = new Designer();
        designer.setAge(10);
        designerDAO.insert(designer);
    }

}