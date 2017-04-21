package com.cmc.algorithm.sort.binarytree.binarysorttree;

import com.cmc.algorithm.sort.common.BinaryNode;

/**
 * <p>
 * 排序二叉树的描述也是一个递归的描述， 所以排序二叉树的构造自然也用递归的.
 * <ul>
 * 排序二叉树的3个特征：
 * <li>1：当前node的所有左孩子的值都小于当前node的值；
 * <li>2：当前node的所有右孩子的值都大于当前node的值；
 * <li>3：孩子节点也满足以上两点.
 * @author Thomas Lee
 * @version 2017年2月27日 下午6:20:19
 * @version 2017年2月28日 下午3:16:05
 * @version 2017年4月21日 上午11:35:05
 */
public class BinarySortTree extends BinaryNode {

    public static void main(String[] args) {
        // 构建二叉排序树
        BinaryNode binarySortNode = new BinarySortTree(BinaryNode.NUMS[0]);
        for (int i = 1; i < BinaryNode.NUMS.length; i++) {
            binarySortNode.addChild(new BinarySortTree(BinaryNode.NUMS[i]));
        }

        // 有序遍历二叉排序树
        binarySortNode.iterate();
    }

    public BinarySortTree(int value) {
        super(value);
    }

}