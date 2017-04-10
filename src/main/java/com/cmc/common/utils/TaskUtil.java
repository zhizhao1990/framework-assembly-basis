package com.cmc.common.utils;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

/**
 * 定时任务工具类
 * 
 * @author HT.LCB
 * @since 2016年12月19日 上午10:24:26
 */
public class TaskUtil {

    /** 线程间隔时间1000ms */
    public static final long THREAD_INTERNAL_TIME = 1000;

    /* >>>>>>>>>>>>>>>>>>>>>>>>>> JavaSE Thread Start<<<<<<<<<<<<<<<<<<<<<<<<<< */

    public static void JavaSEThread() {
        Thread myThread = new Thread(new TaskUtil().new MyRunner());
        myThread.start();
    }

    class MyRunner implements Runnable {

        public void run() {
            try {
                while (true) {
                    System.out.println("Hello~");
                    Thread.sleep(THREAD_INTERNAL_TIME);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /* >>>>>>>>>>>>>>>>>>>>>>>>>> JavaSE Thread End<<<<<<<<<<<<<<<<<<<<<<<<<< */

    /* >>>>>>>>>>>>>>>>>>>>>>>>>> JavaSE Timer Start<<<<<<<<<<<<<<<<<<<<<<<<<< */

    // http://batitan.iteye.com/blog/253483 
    public static void JavaSETimer() {
        Timer timer = new Timer();
        TimerTask timerTask = new TimerTask() {
            public void run() {
                System.out.println("Hello~");
            }
        };
        timer.schedule(timerTask, new Date(), THREAD_INTERNAL_TIME);
    }

    /* >>>>>>>>>>>>>>>>>>>>>>>>>> JavaSE Timer End<<<<<<<<<<<<<<<<<<<<<<<<<< */

    /* >>>>>>>>>>>>>>>>>>>>>>>>>> Spring Task Start<<<<<<<<<<<<<<<<<<<<<<<<<< */

    // 参考Task类

    /* >>>>>>>>>>>>>>>>>>>>>>>>>> Spring Task End<<<<<<<<<<<<<<<<<<<<<<<<<< */

    /* >>>>>>>>>>>>>>>>>>>>>>>>>> Spring Quartz Start<<<<<<<<<<<<<<<<<<<<<<<<<< */

    // 暂时不集成

    /* >>>>>>>>>>>>>>>>>>>>>>>>>> Spring Quartz End<<<<<<<<<<<<<<<<<<<<<<<<<< */

    public static void main(String[] args) {
        // TaskUtil.JavaSEThread();
        // TaskUtil.JavaSETimer();
    }

}