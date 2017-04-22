/*
 * Licensed to the Modon Software Foundation (MSF).
 */
package com.cmc.algorithm.sort.common;

/**
 * 排序算法抽象类.
 * @author Thomas Lee
 * @version 2017年2月22日 下午8:25:20
 */
public abstract class Sorter {

    public static final int[] NUMS = { 1, 9, 3, 2, 5, 7, 1, 3, 8, 9, 10, 10, 20, 100 };

    /**
     * 排序算法
     * @param nums 要排序的数组.
     * @author Thomas Lee
     * @version 2017年2月23日 上午9:20:04
     */
    public abstract void sort(int[] nums);

    /**
     * 交换数组中的两个数值.
     * @param nums 数组.
     * @param i 数组索引.
     * @param j 数组索引.
     * @author Thomas Lee
     * @version 2017年4月7日 上午10:12:11
     */
    public static void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }

    /**
     * 打印数组到控制台.
     * @param nums 数组.
     * @author Thomas Lee
     * @version 2017年4月7日 上午10:12:46    
     */
    public static void out(int[] nums) {
        for (int num : nums) {
            System.out.print(num + " ");
        }
        System.out.println("\n");
    }

}