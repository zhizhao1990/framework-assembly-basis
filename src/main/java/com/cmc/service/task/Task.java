package com.cmc.service.task;

import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * 定时任务.
 * Spring Task
 * @author Thomas Lee
 * @since 2016年12月19日 下午1:31:23
 * @version 2017年4月24日 下午1:28:49
 */
@Service
public class Task {

    private static final Logger LOG = Logger.getLogger(Task.class);

    /**
     * Task method demo.
     * @author Thomas Lee
     * @version 2017年4月24日 下午1:29:08
     */
    @Scheduled(cron = "* * * * * ?")
    public void sayHi() {
        LOG.debug("inside task ===>");
        System.out.println("Hi~");
    }

}