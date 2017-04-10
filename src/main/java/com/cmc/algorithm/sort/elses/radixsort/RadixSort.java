package com.cmc.algorithm.sort.elses.radixsort;

import com.cmc.algorithm.sort.common.Sorter;

public class RadixSort extends Sorter {

    public static void main(String[] args) {
        Sorter radixSort = new RadixSort();
        radixSort.sort(Sorter.NUMS);
        Sorter.out(Sorter.NUMS);
    }

    @Override
    public void sort(int[] nums) {
        radixSort(nums, 0, nums.length - 1, 3);
    }

    private void radixSort(int[] nums, int begin, int end, int digit) {
        // 基数
        final int radix = 10;
        // 存放各个桶的数据统计个数
        int[] count = new int[radix];
        int[] bucket = new int[end - begin + 1];

        /* 按照从低位到高位的顺序执行排序过程 */
        for (int d = 1; d <= digit; d++) {

            /* 置空各个桶的数据统计 */
            for (int i = 0; i < radix; i++) {
                count[i] = 0;
            }

            /* 统计各个桶将要装入的数据个数 */
            for (int i = begin; i <= end; i++) {
                int j = this.getDigit(nums[i], d);
                count[j]++;
            }

            /* count[i]表示第i个桶的右边界索引 */
            for (int i = 1; i < radix; i++) {
                count[i] = count[i] + count[i - 1];
            }

            /* 将数据依次装入桶中，这里要从右向左扫描，保证排序稳定性 */
            for (int i = end; i >= begin; i--) {
                int j = this.getDigit(nums[i], d);
                // 放入对应的桶中，count[j]-1是第j个桶的右边界索引
                bucket[count[j] - 1] = nums[i];
                // 对应桶的装入数据索引减一
                count[j]--;
            }

            /* 将已分配好的桶中数据再倒出来，此时已是对应当前位数有序的表 */
            for (int i = begin, j = 0; i <= end; i++, j++) {
                nums[i] = bucket[j];
            }

        }
    }

    /** 获取x这个数的d位数上的数字，比如获取123的1位数，结果返回3 */
    private int getDigit(int x, int d) {
        int a[] = { 1, 1, 10, 100 };
        return ((x / a[d]) % 10);
    }

}