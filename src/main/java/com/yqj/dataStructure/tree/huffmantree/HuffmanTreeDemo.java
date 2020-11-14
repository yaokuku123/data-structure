package com.yqj.dataStructure.tree.huffmantree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: HuffmanTreeDemo
 * Author: yaoqijun
 * Date: 2020/11/14 13:27
 */
public class HuffmanTreeDemo {
    public static void main(String[] args) {
        int[] arr = {13, 7, 8, 3, 29, 6, 1};
        Node root = huffmanTree(arr);
        preOrder(root);
    }

    public static Node huffmanTree(int[] arr){
        List<Node> list = new ArrayList<>();
        for (int item : arr) {
            list.add(new Node(item));
        }
        while (list.size()>1){
            //排序
            Collections.sort(list);
            //获取前两个最小的元素
            Node leftNode = list.remove(0);
            Node rightNode = list.remove(0);
            //组合成一棵树
            Node parent = new Node(leftNode.getValue() + rightNode.getValue());
            //构建树并加入该新的节点
            parent.setLeft(leftNode);
            parent.setRight(rightNode);
            list.add(parent);
        }
        return list.get(0);
    }

    public static void preOrder(Node node){
        if (node!=null){
            node.preOrder();
        }else {
            System.out.println("哈夫曼树为空无法遍历");
        }
    }

}

class Node implements Comparable<Node> {
    private int value;
    private Node left;
    private Node right;

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
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

    //从小到大排序
    @Override
    public int compareTo(Node o) {
        return this.value - o.value;
    }

    //前序遍历
    public void preOrder() {
        System.out.print(value + " ");
        if (left != null) {
            left.preOrder();
        }
        if (right != null) {
            right.preOrder();
        }
    }
}


