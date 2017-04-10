package com.cmc.algorithm.sort.binarytree.binarysorttree;

import com.cmc.algorithm.sort.common.BinaryNode;

/**
 * <p>
 * 排序二叉树的描述也是一个递归的描述， 所以排序二叉树的构造自然也用递归的：
 * <ul>
 * 排序二叉树的3个特征：
 * <li>1：当前node的所有左孩子的值都小于当前node的值；
 * <li>2：当前node的所有右孩子的值都大于当前node的值；
 * <li>3：孩子节点也满足以上两点
 * @author Thomas Lee
 * @version 2017年2月27日 下午6:20:19
 * @version 2017年2月28日 下午3:16:05
 */
public class BinarySortNode extends BinaryNode {

    public BinarySortNode(int value) {
        super(value);
    }

    public BinarySortNode(int value, BinaryNode lChild, BinaryNode rChild) {
        super(value, lChild, rChild);
    }

    @Override
    public void iterate(BinaryNode node) {
        if (null != node.getLChild()) {
            this.iterate(node.getLChild());
        }
        System.out.print(node.getValue() + " ");
        if (null != node.getRChild()) {
            this.iterate(node.getRChild());
        }
    }

    public void iterateDLR(BinaryNode node) {
        System.out.print(node.getValue() + " ");
        if (null != node.getLChild()) {
            this.iterateDLR(node.getLChild());
        }
        if (null != node.getRChild()) {
            this.iterateDLR(node.getRChild());
        }
    }

    public void iterateLRD(BinaryNode node) {
        if (null != node.getLChild()) {
            this.iterateLRD(node.getLChild());
        }
        if (null != node.getRChild()) {
            this.iterateLRD(node.getRChild());
        }
        System.out.print(node.getValue() + " ");
    }

    /** 虽然说，二叉排序树按照中序遍历不一定是从小到大，但是该方法中已经从根元素递归进行比较了（特殊的二叉排序树），所以中序遍历的时候一定是从小到大。 */
    @Override
    public void addChild(int num) {
        if (num < this.getValue()) {
            if (null != this.getLChild()) {
                this.getLChild().addChild(num);
            } else {
                this.setLChild(new BinarySortNode(num));
            }
        } else {
            if (null != this.getRChild()) {
                this.getRChild().addChild(num);
            } else {
                this.setRChild(new BinarySortNode(num));
            }
        }
    }

    public static void main(String[] args) {

        /* 构建二叉排序树 */
        BinaryNode binarySortNode = new BinarySortNode(BinaryNode.NUMS[0]);
        for (int i = 1; i < BinaryNode.NUMS.length; i++) {
            binarySortNode.addChild(BinaryNode.NUMS[i]);
        }

        // （中序）遍历二叉排序树
        binarySortNode.iterate(binarySortNode);
        System.out.println("\n");
        // 先序遍历二叉排序树
        binarySortNode.iterateDLR(binarySortNode);
        System.out.println("\n");
        // 后序遍历二叉排序树
        binarySortNode.iterateLRD(binarySortNode);
    }

}