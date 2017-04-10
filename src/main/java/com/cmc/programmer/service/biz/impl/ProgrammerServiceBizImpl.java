package com.cmc.programmer.service.biz.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.cmc.common.utils.ModelDataObjectUtil;
import com.cmc.programmer.facade.entity.Programmer;
import com.cmc.programmer.facade.model.ProgrammerModel;
import com.cmc.programmer.facade.service.ProgrammerService;
import com.cmc.programmer.service.dao.ProgrammerMapper;

public class ProgrammerServiceBizImpl implements ProgrammerService {

    @Autowired
    private ProgrammerMapper programmerMapper;

    @Override
    public boolean add(ProgrammerModel mProgrammer) {
        Programmer programmer = ModelDataObjectUtil.model2do(mProgrammer, Programmer.class);
        int rst = programmerMapper.insert(programmer);
        return 0 != rst;
    }

}