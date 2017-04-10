/*
 * Licensed to the Modon Software Foundation (MSF).
 */
package com.cmc.algorithm.sort.insert.straightinsertionsort;

import com.cmc.algorithm.sort.common.Sorter;

/**
 * 插入排序 —— 直接插入排序（Straight Insertion Sort）
 * <p>
 * 算法思路：<br><br>
 * 将一个记录插入到已排序好的有序表中，从而得到一个新的记录数增加一的有序表。即：<br>
 * 先将序列的第1个记录看成是一个有序的子序列，然后从第2个记录逐个进行插入，直至整个序列有序为止。
 * </p>
 * @author Thomas Lee
 * @version 2017年2月23日 下午4:47:02
 * @version 2017年4月7日 下午5:27:08
 */
public class StraightInsertSorter extends Sorter {

    public static void main(String[] args) {
        Sorter straightInsertSorter = new StraightInsertSorter();
        straightInsertSorter.sort(Sorter.NUMS);
        Sorter.out(Sorter.NUMS);
    }

    @Override
    public void sort(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                int tmpForInsert = nums[i];
                nums[i] = nums[i - 1];

                // 通过循环，逐个后移一位找到要插入的位置
                int j;
                for (j = i - 2; j >= 0 && tmpForInsert < nums[j]; j--) {
                    nums[j + 1] = nums[j];
                }

                // 插入
                nums[j + 1] = tmpForInsert;
            }
        }
    }

}