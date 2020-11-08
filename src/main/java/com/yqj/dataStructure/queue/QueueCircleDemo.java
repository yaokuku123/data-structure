package com.yqj.dataStructure.queue;

import java.util.Scanner;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: QueueCircle
 * Author: yaoqijun
 * Date: 2020/11/4 10:48
 * 功能：循环队列实现，解决队列假满的现象，其中空出一个位置不保存元素
 */
public class QueueCircleDemo {
    public static void main(String[] args) {
        CircleQueue queue = new CircleQueue(4);
        boolean loop = true;
        Scanner scanner = new Scanner(System.in);
        char key = ' ';
        while (loop) {
            System.out.println("a(Add),g(Get),c(Cat),s(Show),e(Exit)");
            System.out.println("输入操作：");
            key = scanner.next().charAt(0);
            switch (key) {
                case 'a':
                    System.out.println("输入数据：");
                    int num = scanner.nextInt();
                    queue.addElm(num);
                    break;
                case 'g':
                    System.out.println(queue.getElm());
                    break;
                case 'c':
                    System.out.println(queue.catElm());
                    break;
                case 's':
                    queue.showElm();
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

class CircleQueue {
    private int maxsize;
    private int front;
    private int tail;
    private int[] arr;

    public CircleQueue(int maxsize) {
        this.maxsize = maxsize;
        this.arr = new int[maxsize];
        this.front = 0; //front指向队头第一个元素
        this.tail = 0; //tail指向队尾的下一个元素
    }

    /**
     * 判断队列是否满
     *
     * @return
     */
    public boolean isFull() {
        return (tail + 1) % maxsize == front;
    }

    /**
     * 判断队列是否空
     *
     * @return
     */
    public boolean isEmpty() {
        return tail == front;
    }

    /**
     * 入队
     *
     * @param num
     */
    public void addElm(int num) {
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        arr[tail] = num;
        tail = (tail + 1) % maxsize;
    }

    /**
     * 出队
     *
     * @return
     */
    public int getElm() {
        if (isEmpty()){
            System.out.println("队列空");
            return -1;
        }
        int temp = arr[front];
        front = (front + 1) % maxsize;
        return temp;
    }

    /**
     * 瞄一眼头元素
     * @return
     */
    public int catElm(){
        if (isEmpty()){
            System.out.println("队列空");
            return -1;
        }
        return arr[front];
    }

    /**
     * 展示队列元素
     */
    public void showElm(){
        if (isEmpty()){
            System.out.println("队列空");
            return;
        }
        for (int i = front; i < front+size() ; i++) {
            System.out.printf("arr[%d]=%d\n",i%maxsize,arr[i%maxsize]);
        }
    }

    public int size(){
        return (tail - front + maxsize) % maxsize;
    }

}
