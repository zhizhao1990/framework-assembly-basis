package com.cmc.service.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务
 * 
 * Spring Task
 * 
 * @author HT.LCB
 * @since 2016年12月19日 下午1:31:23
 */
@Service
public class Task {

    private static final Logger LOG = Logger.getLogger(Task.class);

    @Scheduled(cron = "* * * * * ?")
    public void sayHi() {
        LOG.debug("inside task ===>");
        System.out.println("Hi~");
    }

}