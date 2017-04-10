package com.cmc.demo.classloadsequence;

/**
 * 类加载顺序
 * 
 * @author Thomas Lee
 * @version 2017年2月18日 上午11:55:54
 */
public class ClassLoadSequence { // 第一步，准备加载类

    public static void main(String[] args) {
        // 第四步，new一个类
        new ClassLoadSequence();
    }

    // 第二步，静态变量和静态代码块，加载顺序由编写先后决定
    static int num = 4;

    {
        num += 3;
        // 第五步，按照顺序加载匿名代码块，代码块中有打印
        System.out.println("b");
    }

    int a = 5; //6.第六步，按照顺序加载变量

    // 第七步，按照顺序打印c
    {
        System.out.println("c");
    }

    // 第八步，最后加载构造函数，完成对象的建立
    ClassLoadSequence() {
        System.out.println("d");
    }

    // 第三步，静态块，因为有输出，故打印a
    static {
        System.out.println("a");
    }

    // 静态方法，调用的时候才加载，注意看，e没有加载
    static void run() {
        System.out.println("e");
    }

}