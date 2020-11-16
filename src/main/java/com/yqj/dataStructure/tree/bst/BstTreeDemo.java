package com.yqj.dataStructure.tree.bst;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: BstTree
 * Author: yaoqijun
 * Date: 2020/11/16 10:44
 */
public class BstTreeDemo {
    public static void main(String[] args) {
        int[] arr = {7, 3, 10, 12, 5, 1, 9, 2};
        BstTree bstTree = new BstTree();
        for (int i = 0; i < arr.length; i++) {
            bstTree.addNode(new Node(arr[i]));
        }

        bstTree.delete(1);
        bstTree.infixOrder();
    }
}

class BstTree {
    private Node root;

    public void addNode(Node node) {
        if (root == null) {
            root = node;
        } else {
            root.addNode(node);
        }
    }

    public void infixOrder() {
        if (root == null) {
            System.out.println("二叉排序树为空");
        } else {
            root.infixOrder();
        }
    }

    public void delete(int value) {
        if (root == null) {
            return;
        } else {
            root.delete(root, value);
        }
    }
}

class Node {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    public Node delete(Node root, int value) {
        if (root == null) {
            return null;
        }

        if (value < root.value) {
            root.left = delete(root.left, value);
        } else if (value > root.value) {
            root.right = delete(root.right, value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left != null && root.right != null) {
                Node leftMax = root.left;
                while (leftMax.right != null) {
                    leftMax = leftMax.right;
                }
                root.value = leftMax.value;
                root.left = delete(root.left, leftMax.value);
            } else {
                return root.left != null ? root.left : root.right;
            }
        }
        return root;
    }

    /**
     * 查找删除的节点
     *
     * @param value
     * @return
     */
    public Node search(int value) {
        if (this.value == value) {
            return this;
        } else if (value < this.value) {
            if (this.left != null) {
                return this.left.search(value);
            } else {
                return null;
            }
        } else {
            if (this.right != null) {
                return this.right.search(value);
            } else {
                return null;
            }
        }
    }

    /**
     * 查找删除节点的父节点
     *
     * @param value
     * @return
     */
    public Node searchParent(int value) {
        //查找到父节点
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)) {
            return this;
        } else {
            //向左子树查找
            if ((value < this.value) && (this.left != null)) {
                return this.left.searchParent(value);
            } else if ((value >= this.value) && (this.right != null)) { //向右子树查找
                return this.right.searchParent(value);
            } else { //没有父节点
                return null;
            }
        }
    }

    /**
     * 二叉排序树添加节点
     *
     * @param node
     */
    public void addNode(Node node) {
        if (node == null) {
            return;
        }
        if (node.value < this.value) {
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.addNode(node);
            }
        } else {
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.addNode(node);
            }
        }
    }

    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }
}
