package com.cmc.algorithm.sort.common;

import java.util.Comparator;

/**
 * 高级排序算法抽象类，支持泛型
 * @author Thomas Lee
 * @version 2017年2月22日 下午8:25:20
 */
public abstract class AdvancedSorter<T extends Comparable<T>> extends Sorter {

    public static final Integer[] NUMS = { 1, 9, 3, 2, 5, 7, 1, 3, 8, 9, 10, 10, 20, 100 };

    /**
     * 泛型列表排序算法
     * @param list 带排序的列表
     * @param comp 比较器
     * @author Thomas Lee
     * @version 2017年3月29日 下午2:52:19
     */
    public abstract void sort(T[] list, Comparator<T> comp);

    /**
     * 交换泛型数据
     * @param nums 序列
     * @param i 下标
     * @param j 下标
     * @author Thomas Lee
     * @version 2017年3月29日 下午7:23:18
     */
    public static <T> void swap(T[] nums, int i, int j) {
        T tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 输出泛型列表
     * @param nums 序列
     * @author Thomas Lee
     * @version 2017年3月29日 下午7:31:48
     */
    public static <T> void out(T[] nums) {
        for (T num : nums) {
            System.out.print(num + " ");
        }
    }

}