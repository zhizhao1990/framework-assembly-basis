package com.cmc.demo.assertdemo;

/**
 * Assert Demo.
 * @author Thomas Lee
 * @version 2017年4月14日 上午11:57:02
 */
public class AssertDemo {

    public static void main(String[] args) {
        AssertDemo.testAssert();
    }

    public static void testAssert() {
        try {
            /* 断言1结果为true，则继续往下执行 */
            assert true;
            System.out.println("断言1没有问题，Go！");

            /* 断言2结果为false，程序终止 */
            assert false : "断言失败，此表达式的信息将会在抛出异常的时候输出！";
            System.out.println("断言2有问题，此语句不会被执行！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}