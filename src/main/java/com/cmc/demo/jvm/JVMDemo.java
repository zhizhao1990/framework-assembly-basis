package com.cmc.demo.jvm;

/**
 * JVM Demo
 * @author Thomas Lee
 * @version 2017年4月13日 下午2:36:54
 */
public class JVMDemo {
    public static void main(String[] args) {

    }

    /**
     * 请求程序进行垃圾回收的两种方法
     * <p>
     * 垃圾回收器通常是作为一个单独的低优先级的线程运行，<br>
     * 不可预知的情况下对内存堆中已经死亡的或者长时间没有使用的对象进行清除和回收，<br>
     * 程序员不能实时的调用垃圾回收器对某个对象或所有对象进行垃圾回收.
     * </p>
     * @author Thomas Lee
     * @version 2017年4月13日 下午2:38:01
     */
    public void releaseResoureWith2Ways() {
        System.gc();
        Runtime.getRuntime().gc();
    }

}