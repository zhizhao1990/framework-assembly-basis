package com.cmc.demo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 实现Runnable接口
 * @author Thomas Lee
 * @version 2017年2月7日 下午2:43:39
 */
class MyRunner implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(MyThread.class);
    public static final long INTERVAL = 1000;

    public static void main(String[] args) {
        Runnable myRunner = new MyRunner();
        Thread myThread = new Thread(myRunner);
        myThread.start();
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