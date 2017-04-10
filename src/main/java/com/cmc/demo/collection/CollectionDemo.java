package com.cmc.demo.collection;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Collection 实例
 * @author Thomas Lee
 * @version 2017年2月9日 下午3:40:08
 */
public class CollectionDemo {

    @SuppressWarnings("unused")
    private static final Logger LOG = LoggerFactory.getLogger(CollectionDemo.class);

    /**
     * Stack实例
     * @author Thomas Lee
     * @version 2017年2月9日 下午3:46:57
     */
    public static void demoStack() {
        Stack<Integer> s = new Stack<Integer>();
        for (int i = 0; i < 10; i++) {
            s.push(i);
        }

        /* 集合遍历方式，这样栈内的东西还是存在的 */
        for (Integer x : s) {
            System.out.println(x);
        }

        /* 栈弹出遍历方式，这样栈内的东西就不存在栈内了 */
        while (!s.empty()) {
            System.out.println(s.pop());
        }
    }

    /**
     * Queue实例
     * @author Thomas Lee
     * @version 2017年2月9日 下午3:49:21
     */
    public static void demoQueue() {
        Queue<Integer> q = new LinkedBlockingQueue<Integer>();
        for (int i = 0; i < 5; i++) {
            q.offer(i);
        }

        /* 集合遍历方式，这样队列内的东西还是存在的 */
        for (int x : q) {
            System.out.println(x);
        }

        /* 队列移除遍历方式，这样队列内的东西就不存在栈内了 */
        while (q.peek() != null) {
            System.out.println(q.poll());
        }
    }

}