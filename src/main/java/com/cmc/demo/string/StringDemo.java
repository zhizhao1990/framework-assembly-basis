package com.cmc.demo.string;

import java.io.UnsupportedEncodingException;

/**
 * String Demo.
 * @author Thomas Lee
 * @version 2017年4月14日 上午11:50:52
 */
public class StringDemo {

    public static void main(String[] args) {
        StringDemo.testCharacter();
    }

    /**
     * 测试编码
     * @author Thomas Lee
     * @version 2017年4月14日 上午11:53:21
     */
    public static void testCharacter() {
        try {
            String tmp = "tmp";
            tmp = new String(tmp.getBytes("GB2312"), "ISO-8859-1");
            System.out.println(tmp);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

}