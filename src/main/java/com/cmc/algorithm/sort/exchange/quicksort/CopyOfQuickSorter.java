package com.cmc.algorithm.sort.exchange.quicksort;

import com.cmc.algorithm.sort.common.Sorter;

public class CopyOfQuickSorter extends Sorter {

    public static void main(String[] args) {
        Sorter quickSorter = new CopyOfQuickSorter();
        quickSorter.sort(Sorter.NUMS);
        Sorter.out(Sorter.NUMS);
    }

    @Override
    public void sort(int[] nums) {
        this.quickSort(nums, 0, nums.length - 1);
    }

    /**
     * 快速排序
     * @author Thomas Lee
     * @version 2017年4月8日 下午6:31:37
     * @param nums
     * @param low
     * @param high
     */
    private void quickSort(int[] nums, int low, int high) {
        if (low < high) {
            int baseIndex = this.getBaseIndex(nums, low, high);
            this.quickSort(nums, low, baseIndex - 1);
            this.quickSort(nums, baseIndex + 1, high);
        }
    }

    /**
     * 获取基准元素的下标
     * @author Thomas Lee
     * @version 2017年4月8日 下午6:34:59
     * @param nums
     * @param low
     * @param high
     * @return
     */
    private int getBaseIndex(int[] nums, int low, int high) {
        int base = nums[low];

        while (low < high) {
            if (nums[high] >= base && low < high) {
                high--;
            }
            nums[low] = nums[high];

            if (nums[low] <= base && low < high) {
                low++;
            }
            nums[high] = nums[low];
        }

        nums[low] = base;
        return low;
    }

}