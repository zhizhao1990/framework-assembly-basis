package com.cmc.algorithm.sort.insert.shellsort;

import com.cmc.algorithm.sort.common.Sorter;

/**
 * 插入排序-希尔排序
 * 
 * @author Thomas Lee
 * @version 2017年2月23日 下午4:42:29
 */
public class ShellSorter extends Sorter {

    public static void main(String[] args) {
        Sorter shellSort = new ShellSorter();
        shellSort.sort(Sorter.NUMS);
        Sorter.out(Sorter.NUMS);
    }

    /**
     * 算法思路：<br>
     * 类似直接插入排序，只是其增量是1，而本排序增量是increase
     */
    @Override
    public void sort(int[] nums) {
        int increase = nums.length / 2;
        while (increase >= 1) {
            shellInsertSort(nums, increase);
            increase = increase / 2;
        }
    }

    private int[] shellInsertSort(int[] nums, int increase) {
        for (int i = increase; i < nums.length; i++) {
            if (nums[i] < nums[i - increase]) {
                int tmpForInsert = nums[i];
                nums[i] = nums[i - increase];

                /* 通过循环，逐个后移一位找到要插入的位置。 */
                int j;
                for (j = i - 2 * increase; j >= 0 && tmpForInsert < nums[j]; j = j - increase) {
                    nums[j + increase] = nums[j];
                }

                // 插入
                nums[j + increase] = tmpForInsert;
            }
        }
        return nums;
    }

}