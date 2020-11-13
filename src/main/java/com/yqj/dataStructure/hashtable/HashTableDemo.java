package com.yqj.dataStructure.hashtable;

import java.util.Scanner;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: HashTableDemo
 * Author: yaoqijun
 * Date: 2020/11/13 9:44
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("add:添加雇员");
            System.out.println("find:查看雇员");
            System.out.println("list:展示雇员");
            System.out.println("delete:删除雇员");
            System.out.println("exit:退出系统");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.println("请输入雇员id");
                    int id = scanner.nextInt();
                    System.out.println("请输入雇员name");
                    String name = scanner.next();
                    hashTable.add(new Employee(id, name));
                    break;
                case "find":
                    System.out.println("请输入查找的雇员id");
                    id = scanner.nextInt();
                    hashTable.find(id);
                    break;
                case "list":
                    hashTable.list();
                    break;
                case "delete":
                    System.out.println("请输入删除的雇员id");
                    id = scanner.nextInt();
                    hashTable.delete(id);
                    break;
                case "exit":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

class HashTable {
    private HashTableList[] hashTableLists;
    private int size;

    public HashTable(int size) {
        this.size = size;
        hashTableLists = new HashTableList[size];
        for (int i = 0; i < size; i++) {
            hashTableLists[i] = new HashTableList();
        }
    }

    public void add(Employee emp) {
        int listNo = hashFun(emp.id);
        hashTableLists[listNo].add(emp);
    }

    public void find(int id) {
        int listNo = hashFun(id);
        if (hashTableLists[listNo].find(id) == null) {
            System.out.println("未存储该雇员");
        } else {
            System.out.println("第" + listNo + "链表存储了" + id + "号雇员");
        }
    }

    public void list() {
        for (int i = 0; i < size; i++) {
            hashTableLists[i].list(i);
        }
    }

    public void delete(int id) {
        int listNo = hashFun(id);
        hashTableLists[listNo].delete(id);
    }

    public int hashFun(int id) {
        return id % size;
    }
}


class HashTableList {
    private Employee head = new Employee(-1, "");

    public void add(Employee emp) {
        Employee temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = emp;
    }

    public Employee find(int id) {
        if (head.next == null) {
            return null;
        }
        Employee temp = head.next;
        while (temp != null) {
            if (temp.id == id) {
                break;
            }
            temp = temp.next;
        }
        return temp;
    }

    public void list(int no) {
        if (head.next == null) {
            System.out.println("第" + no + "链表为空");
            return;
        }
        Employee temp = head.next;
        System.out.print("第" + no + "链表为：");
        while (temp != null) {
            System.out.printf(" => id=%d,name=%s", temp.id, temp.name);
            temp = temp.next;
        }
    }

    public void delete(int id) {
        if (head.next == null) {
            System.out.println("链表为空");
            return;
        }
        Employee temp = head;
        while (temp.next != null) {
            if (temp.next.id == id) {
                temp.next = temp.next.next;
                System.out.println("删除" + id + "雇员成功");
                return;
            }
            temp = temp.next;
        }
        System.out.println("未找到" + id + "雇员");
    }
}

//雇员类
class Employee {
    public int id;
    public String name;
    public Employee next;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
