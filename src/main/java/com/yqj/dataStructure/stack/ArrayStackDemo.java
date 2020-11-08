package com.yqj.dataStructure.stack;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: ArrayStackDemo
 * Author: yaoqijun
 * Date: 2020/11/8 22:55
 */
public class ArrayStackDemo {
    public static void main(String[] args) {
        Stack stack = new Stack(3);
        String key = "";
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("push");
            System.out.println("pop");
            System.out.println("show");
            System.out.println("exit");
            key = scanner.next();
            switch (key){
                case "push":
                    System.out.println("请输入值：");
                    int value = scanner.nextInt();
                    stack.push(value);
                    break;
                case "pop":
                    System.out.println(stack.pop());
                    break;
                case "show":
                    stack.show();
                    break;
                case "exit":
                    loop = false;
                    scanner.close();
                    break;
                default:
                    break;
            }
        }
    }
}

class Stack {
    private int maxSize;
    private int[] arr;
    private int top = -1;

    public Stack(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[this.maxSize];
    }

    public boolean isFull() {
        return top == maxSize - 1;
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public void push(int value) {
        if (isFull()){
            System.out.println("栈满");
            return;
        }
        top++;
        arr[top] = value;
    }

    public int pop(){
        if (isEmpty()){
            System.out.println("栈空");
            return -1;
        }
        int temp = arr[top];
        top--;
        return temp;
    }

    public void show(){
        if (isEmpty()){
            System.out.println("栈空");
            return;
        }
        for (int i = top; i >= 0 ; i--) {
            System.out.printf("stack[%d]=%d\n",i,arr[i]);
        }
    }
}
