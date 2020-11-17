package com.yqj.dataStructure.algorithm;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: Hanoi
 * Author: yaoqijun
 * Date: 2020/11/17 9:18
 * 功能：利用分治思想解决汉诺塔问题
 */
public class Hanoi {
    public static void main(String[] args) {
        hanoi(3, 'A', 'B', 'C');
    }


    /**
     * 汉诺塔递归分治求解
     *
     * @param num 当前盘子
     * @param a   所在的盘子
     * @param b   借助的盘子
     * @param c   目的地盘子
     */
    public static void hanoi(int num, char a, char b, char c) {
        if (num == 1) {
            System.out.println("第1个盘从" + a + "=>" + c);
        } else {
            hanoi(num - 1, a, c, b);
            System.out.println("第" + num + "个盘从" + a + "=>" + c);
            hanoi(num - 1, b, a, c);
        }
    }
}
