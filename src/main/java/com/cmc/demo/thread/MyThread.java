package com.cmc.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 继承Thread接口
 * @author Thomas Lee
 * @version 2017年3月28日 下午5:23:32
 */
public class MyThread extends Thread {

    private static final Logger LOG = LoggerFactory.getLogger(MyThread.class);
    public static final long INTERVAL = 1000;

    public static void main(String[] args) {
        Thread myThred = new MyThread();
        myThred.start();
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(INTERVAL);
                System.out.println("heart beats ... ");
            }
        } catch (InterruptedException e) {
            LOG.error(e.getMessage(), e);
        }
    }

}