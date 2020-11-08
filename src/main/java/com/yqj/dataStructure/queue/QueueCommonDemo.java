package com.yqj.dataStructure.queue;

import java.util.Scanner;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: QueueCommon
 * Author: yaoqijun
 * Date: 2020/11/2 8:43
 * 实现：普通的队列，实现向队列中添加元素，获取队列中的元素等功能
 */
public class QueueCommonDemo {
    public static void main(String[] args) {
        Queue queue = new Queue(3);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        while (loop){
            System.out.println("a(Add),g(Get),c(Cat),s(Show),e(Exit)");
            System.out.println("输入操作：");
            key = scanner.next().charAt(0);
            switch (key){
                case 'a':
                    try {
                        System.out.println("输入数据：");
                        int num = scanner.nextInt();
                        queue.addElm(num);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'g':
                    try {
                        System.out.println(queue.getElm());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'c':
                    try {
                        System.out.println(queue.catElm());
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 's':
                    try {
                        queue.showElm();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("退出程序...");
    }
}

class Queue {
    private int maxSize;
    private int front;
    private int tail;
    private int[] arr;

    public Queue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.front = -1;
        this.tail = -1;
    }

    /**
     * 判断队列是否空
     *
     * @return
     */
    public boolean isEmpty() {
        return this.front == this.tail;
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return this.tail == maxSize - 1;
    }

    /**
     * 入队
     *
     * @param num
     */
    public void addElm(int num) {
        if (isFull()) {
            throw new RuntimeException("队列满,无法入队");
        }
        tail++;
        arr[tail] = num;
    }

    /**
     * 出队
     * @return
     */
    public int getElm(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无法出队");
        }
        front++;
        return arr[front];
    }

    /**
     * 瞄一眼头元素
     * @return
     */
    public int catElm(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无法获取元素");
        }
        return arr[front + 1];
    }

    /**
     * 展示队列元素
     */
    public void showElm(){
        if(isEmpty()){
            throw new RuntimeException("队列空，无法展示");
        }
        for (int i = 0; i < maxSize; i++) {
            System.out.printf("arr[%d]=%d\n",i,arr[i]);
        }
    }

}
