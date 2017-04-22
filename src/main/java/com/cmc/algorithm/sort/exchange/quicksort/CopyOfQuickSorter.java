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

    private void quickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }
        int baseIndex = this.getBaseIndex(nums, low, high);
        this.quickSort(nums, low, baseIndex - 1);
        this.quickSort(nums, baseIndex + 1, high);
    }

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