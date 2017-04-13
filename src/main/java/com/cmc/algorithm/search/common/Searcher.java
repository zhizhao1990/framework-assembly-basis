/**
 * 
 */
package com.cmc.algorithm.search.common;

/**
 * 查找算法接口
 * @param <T> 实现了Comparable<T>接口的泛型
 * @author Thomas Lee
 * @version 2017年3月29日 下午10:16:01
 */
public interface Searcher<T extends Comparable<T>> {

    /** 在指定数组中未查到指定元素的结果返回值 */
    int NOT_FOUND = -1;
    int KEY = 7;

    /** 查找对象集合 */
    Integer[] NUMS = { 1, 2, 3, 4, 5, 6, 7 };

    /**
     * 查找方法
     * @author Thomas Lee
     * @version 2017年3月29日 下午9:44:12
     * @param nums 查找对象集合
     * @param key 要查找的对象
     * @return 如果查询到要查询的元素就返回其下标，否则返回-1
     */
    int search(T[] nums, T key);

}