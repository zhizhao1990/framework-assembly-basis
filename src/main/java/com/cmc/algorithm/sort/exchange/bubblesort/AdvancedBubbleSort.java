package com.cmc.algorithm.sort.exchange.bubblesort;

import java.util.Comparator;

import com.cmc.algorithm.sort.common.AdvancedSorter;

/**
 * 高级冒泡排序
 * @param <T> T extends Comparable<T>
 * @author Thomas Lee
 * @version 2017年4月14日 下午5:19:07
 */
public class AdvancedBubbleSort<T extends Comparable<T>> extends AdvancedSorter<T> {

    public static void main(String[] args) {
        AdvancedSorter<Integer> advanceSorter = new AdvancedBubbleSort<Integer>();
        advanceSorter.sort(AdvancedSorter.NUMS, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1.intValue() - o2.intValue();
            }
        });
        AdvancedSorter.out(AdvancedSorter.NUMS);
    }

    @Override
    public void sort(int[] nums) {
    }

    @Override
    public void sort(T[] list, Comparator<T> comp) {
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length - 1 - i; j++) {
                if (comp.compare(list[j], list[j + 1]) > 0) {
                    AdvancedSorter.swap(list, j, j + 1);
                }
            }
        }
    }

    /* 有待以后研究
    public <T extends Comparable<T>> void sort(T[] list) {
        boolean swapped = true;
        for (int i = 1, len = list.length; i < len && swapped; ++i) {
            swapped = false;
            for (int j = 0; j < len - i; ++j) {
                if (list[j].compareTo(list[j + 1]) > 0) {
                    T temp = list[j];
                    list[j] = list[j + 1];
                    list[j + 1] = temp;
                    swapped = true;
                }
            }
        }
    }
    */

}