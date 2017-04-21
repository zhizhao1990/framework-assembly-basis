package com.cmc.algorithm.sort.common;

/**
 * 二叉树抽象类.
 * @author Thomas Lee
 * @version 2017年4月21日 上午10:01:51
 */
public abstract class BinaryNode {

    public static final int[] NUMS = { 1, 9, 3, 2, 5, 7, 1, 3, 8, 9, 10, 10, 20, 100 };

    private int value;
    private BinaryNode lChild;
    private BinaryNode rChild;

    /**
     * 构造方法.
     * @param value 节点的值.
     */
    public BinaryNode(int value) {
        this.value = value;
    }

    /**
     * 构造方法.
     * @param value 节点的值.
     * @param lChild 左子树.
     * @param rChild 右子树.
     */
    public BinaryNode(int value, BinaryNode lChild, BinaryNode rChild) {
        this.value = value;
        this.lChild = lChild;
        this.rChild = rChild;
    }

    /**
     * 添加子树.
     * <p>
     * 虽然说，二叉排序树按照中序遍历不一定是从小到大，但是该方法中已经从根元素递归进行比较了（特殊的二叉排序树），所以中序遍历的时候一定是从小到大。
     * </p>
     * @param node 子树.
     * @author Thomas Lee
     * @version 2017年4月21日 上午10:03:12
     */
    public void addChild(BinaryNode node) {
        if (node.getValue() < this.getValue()) {
            if (null != this.getLChild()) {
                this.getLChild().addChild(node);
            } else {
                this.setLChild(node);
            }
        } else {
            if (null != this.getRChild()) {
                this.getRChild().addChild(node);
            } else {
                this.setRChild(node);
            }
        }
    }

    /**
     * 有序遍历二叉树.
     * @author Thomas Lee
     * @version 2017年2月28日 下午2:41:53
     * @version 2017年4月21日 上午9:43:22
     */
    public void iterate() {
        // 关键词this是指运行时调用的类，而不是代码所在的类（静态的才是所在的类）. 即，this是动态的，属于对象的；而不是静态的，属于类的.
        this.iterateLDR();
        System.out.println("\n");
    }

    /**
     * 中序遍历. 
     * @param node
     * @author Thomas Lee
     * @version 2017年4月21日 上午10:09:53
     */
    public void iterateLDR() {
        if (null != this.getLChild()) {
            this.getLChild().iterateLDR();
        }
        System.out.print(this.getValue() + " ");
        if (null != this.getRChild()) {
            this.getRChild().iterateLDR();
        }
    }

    /**
     * 先序遍历
     * @param node
     * @author Thomas Lee
     * @version 2017年4月21日 上午9:59:20
     */
    public void iterateDLR() {
        System.out.print(this.getValue() + " ");
        if (null != this.getLChild()) {
            this.getLChild().iterateDLR();
        }
        if (null != this.getRChild()) {
            this.getRChild().iterateDLR();
        }
    }

    /**
     * 后序遍历
     * @param node
     * @author Thomas Lee
     * @version 2017年4月21日 上午9:59:35
     */
    public void iterateLRD() {
        if (null != this.getLChild()) {
            this.getLChild().iterateLRD();
        }
        if (null != this.getRChild()) {
            this.getRChild().iterateLRD();
        }
        System.out.print(this.getValue() + " ");
    }

    public BinaryNode getLChild() {
        return this.lChild;
    }

    public BinaryNode getRChild() {
        return this.rChild;
    }

    public int getValue() {
        return this.value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setLChild(BinaryNode lChild) {
        this.lChild = lChild;
    }

    public void setRChild(BinaryNode rChild) {
        this.rChild = rChild;
    }

}