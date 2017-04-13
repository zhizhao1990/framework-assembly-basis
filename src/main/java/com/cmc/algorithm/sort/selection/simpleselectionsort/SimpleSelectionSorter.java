/*
 * Licensed to the Modon Software Foundation (MSF).
 */
package com.cmc.algorithm.sort.selection.simpleselectionsort;

import com.cmc.algorithm.sort.common.Sorter;

/**
 * 选择排序 —— 简单选择排序（Simple Selection Sort）
 * <p>
 * 算法思路：<br><br>
 * 在要排序的一组数中，选出最小（或者最大）的一个数与第1个位置的数交换；<br>
 * 然后在剩下的数当中再找最小（或者最大）的与第2个位置的数交换，依次类推，
 * 直到第n-1个元素（倒数第二个数）和第n个元素（最后一个数）比较为止。
 * </p>
 * @author Thomas Lee
 * @version 2017年2月22日 下午8:15:37
 * @version 2017年4月7日 下午5:27:17
 */
public class SimpleSelectionSorter extends Sorter {

    public static void main(String[] args) {
        Sorter simpleSelectionSort = new SimpleSelectionSorter();
        simpleSelectionSort.sort(Sorter.NUMS);
        Sorter.out(Sorter.NUMS);
    }

    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    Sorter.swap(nums, i, j);
                }
            }
        }
    }

}