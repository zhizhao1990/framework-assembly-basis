package com.cmc.demo.stackoverflowerror;

/**
 * StackOverflow Demo.
 * <p>
 * 栈溢出，通常情况下是递归太深导致的.
 * </p>
 * @author Thomas Lee
 * @version 2017年4月14日 下午1:17:35
 */
public class StackOverflowErrorDemo {

    public static void main(String[] args) {
        StackOverflowErrorDemo.causeStackOverflow();
    }

    /**
     * 造成StackOverflow错误
     * <p>
     * 递归太深.
     * </p>
     * @author Thomas Lee
     * @version 2017年4月14日 下午1:19:35
     */
    public static void causeStackOverflow() {
        main(null);
    }

}