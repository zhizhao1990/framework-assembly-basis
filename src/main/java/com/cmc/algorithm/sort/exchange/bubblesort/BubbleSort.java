package com.cmc.algorithm.sort.exchange.bubblesort;

import com.cmc.algorithm.sort.common.Sorter;

/**
 * 交换排序—冒泡排序（Bubble Sort）
 * @author Thomas Lee
 * @version 2017年2月22日 下午7:17:16
 */
public class BubbleSort extends Sorter {

    public static void main(String[] args) {
        Sorter bubbleSort = new BubbleSort();
        bubbleSort.sort(NUMS);
        Sorter.out(NUMS);
    }

    /**
     * 算法思路：<br>
     * 在要排序的一组数中，对当前还未排好序的范围内的全部数， 自上而下对相邻的两个数依次进行比较和调整，让较大的数往下沉，较小的往上冒。
     * 即：每当两相邻的数比较后发现它们的排序与排序要求相反时，就将它们互换。
     */
    @Override
    public void sort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            // j < nums.length - i - 1，-1是因为下面要用到j+1，-i是因为，已经把最大的数排列到最后面，所以就没有必要（继续排序的话，是没有意义的）继续排序了
            for (int j = 0; j < nums.length - i - 1; j++) {
                if (nums[j] > nums[j + 1]) {
                    super.swap(nums, j, j + 1);
                }
            }
            /*
             * 伪冒泡排序 for (int j = i; j < nums.length - 1; j++) { if (nums[j] < nums[j + 1]) {
             * Sort.swap(nums, j, j + 1); } // 调换位置 Sort.swap(nums, i, j + 1); }
             */
        }
    }

}