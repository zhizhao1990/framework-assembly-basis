/**
 * 
 */
package com.cmc.algorithm.search.binarysearch;

import com.cmc.algorithm.search.common.Searcher;

/**
 * 二分查找算法
 * <p>
 * 基本思路：
 * </p>
 * <p>
 * 注意事项：
 * <br>
 * 1. 
 * </p>
 * @param <T> 实现了Comparable<T>接口的泛型.
 * @author Thomas Lee
 * @version 2017年3月30日 上午10:46:01
 * @version 2017年4月16日 下午12:02:56
 */
public class BinarySearcher<T extends Comparable<T>> implements Searcher<T> {

    public static void main(String[] args) {
        Searcher<Integer> search = new BinarySearcher<Integer>();
        int rst = search.search(Searcher.NUMS, Searcher.KEY);
        System.out.println("下标为：" + rst);
    }

    @Override
    public int search(T[] nums, T key) {
        return this.binarySearch(nums, 0, nums.length - 1, key);
    }

    /**
     * 在给定的有序数组中二叉树查找指定元素.
     * @param nums 有序数组.
     * @param low 数组最低值.
     * @param high 数组最高值.
     * @param key 要查询的对象.
     * @return 如果查询到要查询的元素就返回其下标，否则返回-1.
     * @author Thomas Lee
     * @version 2017年3月29日 下午10:45:20
     * @version 2017年3月30日 上午10:51:10
     */
    private int binarySearch(T[] nums, int low, int high, T key) {
        if (low > high) {
            return Searcher.NOT_FOUND;
        }
        int middle = (low + high) >> 1;
        if (nums[middle] == key) {
            return middle;
        } else if (nums[middle].compareTo(key) > 0) {
            high = middle - 1;
            return this.binarySearch(nums, low, high, key);
        } else {
            low = middle + 1;
            return this.binarySearch(nums, low, high, key);
        }
    }

}