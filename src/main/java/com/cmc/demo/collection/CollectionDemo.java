package com.cmc.demo.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Collection demo.
 * @author Thomas Lee
 * @version 2017年2月9日 下午3:40:08
 */
public class CollectionDemo {

    public static void main(String[] args) {
    }

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

    /**
     * 测试java.utils.Collections，对集合进行查找、排序和线程安全等操作。
     * @author Thomas Lee
     * @version 2017年3月27日 下午9:39:08
     */
    public static void testCollections() {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        Collections.binarySearch(list, "key");
        Collections.sort(list);
        Collections.synchronizedList(list);
    }

}