package com.yqj.dataStructure.tree.thread;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: ThreadedBinaryTreeDemo
 * Author: yaoqijun
 * Date: 2020/11/14 9:38
 */
public class ThreadedBinaryTreeDemo {
    public static void main(String[] args) {
        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        //创建4个节点
        Node root = new Node(1, "node1");
        Node node2 = new Node(2, "node2");
        Node node3 = new Node(3, "node3");
        Node node4 = new Node(4, "node4");
        //手动增加节点的关系
        threadedBinaryTree.setRoot(root);
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);

        //线索化测试
        threadedBinaryTree.infixThreadNode();
        System.out.println(node2.getRight());
        System.out.println(node4.getLeft());

        //中序线索化二叉树遍历
        threadedBinaryTree.infixOrderThreadedTree();
    }
}

class ThreadedBinaryTree {
    private Node root;
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    public void infixThreadNode(){
        this.infixThreadNode(root);
    }

    //中序线索化
    public void infixThreadNode(Node node) {
        if (node == null) {
            return;
        }

        //左边线索化
        infixThreadNode(node.getLeft());

        //线索化当前节点
        if (node.getLeft() == null) {
            node.setLeft(pre);
            node.setLeftType(1);
        }
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        pre = node;

        //右边线索化
        infixThreadNode(node.getRight());

    }

    //遍历中序线索化二叉树
    public void infixOrderThreadedTree(){

        Node node = root;
        while (node!=null){
            //获取到最左边的节点
            while (node.getLeftType() == 0 ){
                node = node.getLeft();
            }
            System.out.println(node);
            //持续获取右边为线索化的节点
            while (node.getRightType()==1){
                node = node.getRight();
                System.out.println(node);
            }
            node = node.getRight();
        }
    }

    public void preOrder() {
        if (root != null) {
            root.preOrder();
        } else {
            System.out.println("二叉树为空");
        }

    }

    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public void postOrder() {
        if (root != null) {
            root.postOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    public Node preOrderSearch(int no) {
        if (root != null) {
            return root.preOrderSearch(no);
        } else {
            return null;
        }
    }

    public Node infixOrderSearch(int no) {
        if (root != null) {
            return root.infixOrderSearch(no);
        } else {
            return null;
        }
    }

    public Node postOrderSearch(int no) {
        if (root != null) {
            return root.postOrderSearch(no);
        } else {
            return null;
        }
    }

    public void delete(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delete(no);
            }
        } else {
            System.out.println("二叉树为空");
        }
    }
}


class Node {
    private int no;
    private String name;
    private Node left;
    private Node right;
    private int leftType; // 0表示未线索化，1表示线索化
    private int rightType;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
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

    //前序查找
    public Node preOrderSearch(int no) {
        if (this.no == no) {
            return this;
        }
        Node temp = null;
        if (this.left != null) {
            temp = this.left.preOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.right != null) {
            temp = this.right.preOrderSearch(no);
        }
        return temp;
    }

    //中序查找
    public Node infixOrderSearch(int no) {
        Node temp = null;
        if (this.left != null) {
            temp = this.left.infixOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.no == no) {
            return this;
        }
        if (this.right != null) {
            temp = this.right.infixOrderSearch(no);
        }
        return temp;
    }

    //后序查找
    public Node postOrderSearch(int no) {
        Node temp = null;
        if (this.left != null) {
            temp = this.left.postOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.right != null) {
            temp = this.right.postOrderSearch(no);
        }
        if (temp != null) {
            return temp;
        }
        if (this.no == no) {
            return this;
        }
        return temp;
    }

    //删除节点
    public void delete(int no) {
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }
        if (this.left != null) {
            this.left.delete(no);
        }
        if (this.right != null) {
            this.right.delete(no);
        }
    }
}





