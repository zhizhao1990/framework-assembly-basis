package com.cmc.demo.outofmemoryerror;

import java.util.ArrayList;
import java.util.List;

/**
 * OutOfMemoryError Demo.
 * @author Thomas Lee
 * @version 2017年4月14日 下午1:24:03
 */
public class OutOfMemoryErrorDemo {

    public static void main(String[] args) {
        OutOfMemoryErrorDemo.causeOutOfMemoryError();
    }

    public static void causeOutOfMemoryError() {
        List<Integer[]> list = new ArrayList<Integer[]>();
        for (;;) {
            Integer[] tmp = new Integer[100000];
            list.add(tmp);
        }
    }

}