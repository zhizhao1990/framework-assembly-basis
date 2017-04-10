package com.cmc.algorithm.sort.common;

public abstract class BinaryNode {

    public static final int[] NUMS = { 1, 9, 3, 2, 5, 7, 1, 3, 8, 9, 10, 10, 20, 100 };

    private int value;
    private BinaryNode lChild;
    private BinaryNode rChild;

    public BinaryNode(int value) {
        this.value = value;
    }

    public BinaryNode(int value, BinaryNode lChild, BinaryNode rChild) {
        this.value = value;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    public abstract void addChild(int num);

    public BinaryNode getLChild() {
        return this.lChild;
    }

    public BinaryNode getRChild() {
        return this.rChild;
    }

    public int getValue() {
        return this.value;
    }

    /**
     * 中序遍历（LDR）
     * @author Thomas Lee
     * @param node
     * @version 2017年2月28日 下午2:41:53
     */
    public abstract void iterate(BinaryNode node);

    public abstract void iterateDLR(BinaryNode node);

    public abstract void iterateLRD(BinaryNode node);

    public void setLChild(BinaryNode lChild) {
        this.lChild = lChild;
    }

    public void setRChild(BinaryNode rChild) {
        this.rChild = rChild;
    }

    public void setValue(int value) {
        this.value = value;
    }

}