package com.cmc.web.test;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cmc.designer.facade.model.DesignerModel;
import com.cmc.designer.facade.service.DesignerService;

@Controller
@RequestMapping("/test")
public class TestController {

    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @Autowired
    private DesignerService designerService;

    /**
     * 测试Hibernate.
     * @author Thomas Lee
     * @version 2017年3月21日 下午2:12:48
     */
    @RequestMapping(value = "/testhibernate", method = RequestMethod.GET)
    public void testHibernate() {
        designerService.testHibernateCRUD();
        List<DesignerModel> mDesigners = designerService.getDesigners();
        if (!CollectionUtils.isEmpty(mDesigners)) {
            Iterator<DesignerModel> imDesigner = mDesigners.iterator();
            while (imDesigner.hasNext()) {
                DesignerModel mDesigner = imDesigner.next();
                LOG.info(mDesigner.toString());
            }
        }
    }

    /**
     * 测试Hibernate的三种状态.
     * @author Thomas Lee
     * @version 2017年3月22日 下午4:38:07
     */
    @RequestMapping(value = "/testhibernatethreestatus", method = RequestMethod.GET)
    public void testHibernateThreeStatus() {
        designerService.testHibernateThreeStatus();
    }

}