/*
 * Licensed to the Modon Software Foundation (MSF).
 */
package com.cmc.algorithm.sort.exchange.quicksort;

import com.cmc.algorithm.sort.common.Sorter;

/**
 * 交换排序 —— 快速排序（Quick Sort）
 * <p>
 * 算法思路：<br><br>
 * 通过一趟排序将待排序的记录分割成独立的两部分。<br>
 * 其中一部分记录的元素值均比基准元素值小，另一部分记录的元素值比基准值大，<br>
 * 此时基准元素在其排好序后的正确位置，然后进行递归排序。
 * </p>
 * @author Thomas Lee
 * @version 2017年2月23日 下午6:56:07
 * @version 2017年4月7日 下午5:27:25
 */
public class QuickSorter extends Sorter {

    /**
     * 程序入口 
     * @param args 参数
     * @author Thomas Lee
     * @version 2017年4月7日 下午5:19:44
     */
    public static void main(String[] args) {
        Sorter quickSorter = new QuickSorter();
        quickSorter.sort(Sorter.NUMS);
        Sorter.out(Sorter.NUMS);
    }

    @Override
    public void sort(int[] nums) {
        this.quickSort(nums, 0, nums.length - 1);
    }

    /**
     * 快速排序
     * @param nums 数组
     * @param low 低值
     * @param high 高值
     * @author Thomas Lee
     * @version 2017年4月7日 下午5:18:34
     */
    private void quickSort(int[] nums, int low, int high) {
        // 终止条件，否则会因为无法退出导致栈溢出错误（java.lang.StackOverflowError)
        if (low < high) {
            int middle = this.getBaseNumIndex(nums, low, high);
            this.quickSort(nums, low, middle - 1);
            this.quickSort(nums, middle + 1, high);
        }
    }

    /**
     * 获取基准元素的下标
     * @param nums 数组
     * @param low 低值
     * @param high 高值
     * @return 基准元素下标
     * @author Thomas Lee
     * @version 2017年4月7日 下午5:18:29
     */
    private int getBaseNumIndex(int[] nums, int low, int high) {
        // 基准元素，排序中会空出来的一个位置
        int base = nums[low];
        while (low < high) {

            // 从high开始找比基准小的，与low换位置
            while (low < high && nums[high] >= base) {
                high--;
            }
            nums[low] = nums[high];

            // 从low开始找比基准大，放到之前high空出来的位置上
            while (low < high && nums[low] <= base) {
                low++;
            }
            nums[high] = nums[low];
        }

        // 此时low=high是基准元素的位置，也是空出来的那个位置
        nums[low] = base;
        return low;
    }

}