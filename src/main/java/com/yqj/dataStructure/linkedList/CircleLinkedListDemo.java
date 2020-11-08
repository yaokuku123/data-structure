package com.yqj.dataStructure.linkedList;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: CircleLinkedListDemo
 * Author: yaoqijun
 * Date: 2020/11/8 22:30
 * 功能：实现环形链表
 */
public class CircleLinkedListDemo {
    public static void main(String[] args) {
        CircleLinkedList circleLinkedList = new CircleLinkedList();
        circleLinkedList.addNode(5);
        circleLinkedList.showNode();
    }
}

class CircleLinkedList{
    private Node first = null;

    /**
     * 添加num个元素
     * @param num
     */
    public void addNode(int num){
        //校验输入
        if (num < 1){
            System.out.println("num 值不正确");
            return;
        }
        Node curNode = null;
        for (int i = 0; i < num; i++) {
            Node node = new Node(i);
            if (i == 0){
                first = node;
                first.next = first;
                curNode = first;
            }else {
                curNode.next = node;
                node.next = first;
                curNode = node;
            }
        }
    }

    /**
     * 展示元素
     */
    public void showNode(){
        if (first == null){
            System.out.println("链表为空");
        }
        Node temp = first;
        while (temp.next != first){
            System.out.println(temp);
            temp = temp.next;
        }
    }
}

class Node{
    public int no;
    public Node next;

    public Node(int no) {
        this.no = no;
    }

    @Override
    public String toString() {
        return "Node{" +
                "no=" + no + "}";
    }
}
