package com.yqj.dataStructure.tree;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: BinaryTreeDemo
 * Author: yaoqijun
 * Date: 2020/11/13 12:07
 * 功能：二叉树的前中后遍历
 */
public class BinaryTreeDemo {
    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        //创建4个节点
        Node root = new Node(1, "node1");
        Node node2 = new Node(2, "node2");
        Node node3 = new Node(3, "node3");
        Node node4 = new Node(4, "node4");
        //手动增加节点的关系
        binaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        //遍历
        binaryTree.preOrder();
        System.out.println("******");
        binaryTree.infixOrder();
        System.out.println("******");
        binaryTree.postOrder();
    }
}

class BinaryTree {
    private Node root;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void preOrder() {
        this.root.preOrder();
    }

    public void infixOrder() {
        this.root.infixOrder();
    }

    public void postOrder() {
        this.root.postOrder();
    }
}

class Node {
    private int no;
    private String name;
    private Node left;
    private Node right;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    //前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    //中序遍历
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    //后序遍历
    public void postOrder() {
        if (this.left != null) {
            this.left.postOrder();
        }
        if (this.right != null) {
            this.right.postOrder();
        }
        System.out.println(this);
    }
}


