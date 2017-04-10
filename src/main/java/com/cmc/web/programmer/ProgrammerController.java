package com.cmc.web.programmer;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cmc.common.constants.AjaxResult;
import com.cmc.programmer.facade.model.ProgrammerModel;
import com.cmc.programmer.facade.service.ProgrammerService;

/**
 * 程序员Controller
 * @author Thomas Lee
 * @version 2017年3月17日 下午1:49:40
 */
@Controller
@RequestMapping("/programmer")
public class ProgrammerController {

    private static final Logger LOG = Logger.getLogger(ProgrammerController.class);

    @Autowired
    private ProgrammerService programmerService;

    /**
     * 添加程序员 
     * @param mProgrammer 程序员信息{name}
     * @return
     * @author Thomas Lee
     * @version 2017年3月17日 下午2:08:58
     */
    @ResponseBody
    @RequestMapping(value = "/add.htm", method = RequestMethod.GET)
    public AjaxResult add(ProgrammerModel mProgrammer) {
        boolean rst = programmerService.add(mProgrammer);
        if (!rst) {
            LOG.error("添加程序员失败！");
            return AjaxResult.getFailInstance("Failure.");
        }
        return AjaxResult.getSuccessInstance();
    }

}