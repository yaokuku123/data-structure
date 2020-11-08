package com.yqj.dataStructure.linkedList;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: DoubleLinkedList
 * Author: yaoqijun
 * Date: 2020/11/8 21:58
 * 功能：实现双向链表的相关操作
 */
public class DoubleLinkedListDemo {
    public static void main(String[] args) {
        //构造测试元素
        MessageD msg1 = new MessageD(1, "Yorick");
        MessageD msg2 = new MessageD(2, "Bob");
        MessageD msg3 = new MessageD(3, "Alice");
        MessageD msg4 = new MessageD(4, "Tom");
        //创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.addNode(msg1);
        doubleLinkedList.addNode(msg2);
        doubleLinkedList.addNode(msg3);
        doubleLinkedList.addNode(msg4);
        //展示
        System.out.println("添加元素后的内容：");
        doubleLinkedList.showNode();
        //修改
        MessageD updateMsg2 = new MessageD(2,"Bob~~");
        doubleLinkedList.updateNode(updateMsg2);
        //展示
        System.out.println("更新元素后的内容：");
        doubleLinkedList.showNode();
        //删除
        doubleLinkedList.deleteNode(3);
        //展示
        System.out.println("删除元素后的内容：");
        doubleLinkedList.showNode();
    }
}

class DoubleLinkedList {
    private MessageD head = new MessageD(0, "");

    /**
     * 添加元素
     *
     * @param messageD
     */
    public void addNode(MessageD messageD) {
        MessageD temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = messageD;
        messageD.pre = temp;
    }

    /**
     * 展示元素
     */
    public void showNode() {
        MessageD temp = head.next;
        if (temp == null) {
            System.out.println("链表为空");
        }
        while (temp != null) {
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 更新元素
     *
     * @param messageD
     */
    public void updateNode(MessageD messageD) {
        MessageD temp = head.next;
        boolean flag = false; //判别是否存在该元素
        while (temp != null) {
            if (temp.no == messageD.no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = messageD.name;
        } else {
            System.out.println("不存在该元素，修改失败");
        }
    }

    public void deleteNode(int index) {
        MessageD temp = head.next;
        boolean flag = false; //用于判断是否存在该元素
        while (temp != null) {
            if (temp.no == index) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag){
            temp.pre.next = temp.next;
            //避免最后一个元素指向错误，出现空指针异常
            if (temp.next!=null){
                temp.next.pre = temp.pre;
            }
        }else {
            System.out.println("不存在该元素，删除失败");
        }
    }

}

//节点
class MessageD {
    public int no;
    public String name;
    public MessageD next; //指向下一个节点的引用
    public MessageD pre;

    public MessageD(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "MessageD{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }
}
