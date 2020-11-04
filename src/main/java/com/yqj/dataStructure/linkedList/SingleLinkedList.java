package com.yqj.dataStructure.linkedList;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: SingleLinkedList
 * Author: yaoqijun
 * Date: 2020/11/4 11:07
 * 功能：实现单向链表的相关操作
 */
public class SingleLinkedList {
    public static void main(String[] args) {
        //构造测试元素
        Message msg1 = new Message(1, "Yorick");
        Message msg2 = new Message(2, "Bob");
        Message msg3 = new Message(3, "Alice");
        //创建单向链表
        LinkedList linkedList = new LinkedList();
        //添加元素
//        linkedList.addNode(msg1);
//        linkedList.addNode(msg3);
//        linkedList.addNode(msg2);
        //按顺序添加元素
        linkedList.addNodeByOrder(msg2);
        linkedList.addNodeByOrder(msg1);
        linkedList.addNodeByOrder(msg3);
        //修改msg2的名字
        Message updateMsg = new Message(2, "update-Bob");
        linkedList.updateNode(updateMsg);
        //删除msg1
        linkedList.deleteNode(1);
        //展示链表的元素
        linkedList.showNode();
    }
}

//维护单向链表
class LinkedList {
    //头节点
    private Message head = new Message(0, "");

    /**
     * 向链表中添加元素
     *
     * @param message
     */
    public void addNode(Message message) {
        Message temp = head; //找到末尾的那个元素，在它的next指向位置添加新元素
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = message;
    }

    /**
     * 按编号由小到大排序插入
     *
     * @param message
     */
    public void addNodeByOrder(Message message) {
        Message temp = head;
        boolean flag = false; //判断编号是否已经存在
        while (temp.next != null) {
            if (temp.next.no == message.no) { //编号存在
                flag = true;
                break;
            } else if (temp.next.no > message.no) {
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("编号已存在，添加失败");
        } else {
            message.next = temp.next;
            temp.next = message;
        }
    }

    /**
     * 依据编号查找某元素然后修改
     *
     * @param message
     */
    public void updateNode(Message message) {
        Message temp = head.next;
        boolean flag = false; //判别是否存在该元素
        while (temp != null) {
            if (temp.no == message.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = message.name;
        } else {
            System.out.println("不存在该元素，修改失败");
        }
    }

    /**
     * 删除某编号的元素
     * @param index
     */
    public void deleteNode(int index) {
        Message temp = head;
        boolean flag = false; //用于判断是否存在该元素
        while (temp.next != null) {
            if (temp.next.no == index) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.next = temp.next.next;
        }else {
            System.out.println("不存在该元素，删除失败");
        }
    }

    /**
     * 展示链表元素
     */
    public void showNode() {
        Message temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }


}

//节点
class Message {
    public int no;
    public String name;
    public Message next; //指向下一个节点的引用

    public Message(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Message{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
