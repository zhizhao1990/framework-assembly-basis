package com.cmc.algorithm.sort.elses.mergesort;

import com.cmc.algorithm.sort.common.Sorter;

public class MergeSort extends Sorter {

    public static void main(String[] args) {
        Sorter mergeSort = new MergeSort();
        mergeSort.sort(Sorter.NUMS);
        Sorter.out(Sorter.NUMS);
    }

    @Override
    public void sort(int[] nums) {
        this.sort(Sorter.NUMS, 0, Sorter.NUMS.length - 1);
    }

    /**
     * 算法思路：<br>j
     * 将两个（或两个以上）有序表合并成一个新的有序表。
     * 即把待排序序列分为若干个子序列，每个子序列是有序的，
     * 然后再把有序子序列合并为整体有序序列。
     */
    private void sort(int[] nums, int low, int high) {
        int mid = (low + high) / 2;
        if (low < high) {
            this.sort(nums, low, mid);
            this.sort(nums, mid + 1, high);
            // 左右归并
            this.merge(nums, low, mid, high);
        }
    }

    /**
     * 合并左右有序序列
     * 
     * @author Thomas Lee
     * @param nums 要排序的序列
     * @param low 左端序列下标
     * @param mid mid = (low + high) / 2
     * @param high 右端序列下标
     * @version 2017年2月24日 下午2:38:43
     */
    private void merge(int[] nums, int low, int mid, int high) {
        // 存放合并序列的临时数组
        int[] tempArray = new int[high - low + 1];
        // 左端序列下标
        int i = low;
        // 右端序列下标
        int j = mid + 1;
        // 临时数组序列
        int k = 0;

        /* 把较小的数先移到新数组中 */
        while (i <= mid && j <= high) {
            if (nums[i] < nums[j]) {
                tempArray[k++] = nums[i++];
            } else {
                tempArray[k++] = nums[j++];
            }
        }

        /* 把左边剩余的数移入数组 */
        while (i <= mid) {
            tempArray[k++] = nums[i++];
        }

        /* 把右边边剩余的数移入数组 */
        while (j <= high) {
            tempArray[k++] = nums[j++];
        }

        this.copyTempArray2OriginArray(tempArray, nums, low);
    }

    private void copyTempArray2OriginArray(int[] tempArray, int[] nums, int low) {
        for (int m = 0; m < tempArray.length; m++) {
            nums[m + low] = tempArray[m];
        }
    }

}