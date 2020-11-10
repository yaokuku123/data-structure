package com.yqj.dataStructure.recursion;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: Queen
 * Author: yaoqijun
 * Date: 2020/11/10 15:59
 */
public class Queen {

    //表示皇后的个数
    int max = 8;

    //保存换后放置的位置
    int[] array = new int[max];

    //保存可能放置位置的个数
    static int count = 0;

    public static void main(String[] args) {
        Queen queen = new Queen();
        queen.check(0);
        System.out.println("count=" + count);
    }

    /**
     * 放置皇后
     *
     * @param n
     */
    public void check(int n) {
        //表示已经成功放置完毕八个皇后
        if (n == max) {
            print();
            count++;
            return;
        }
        for (int i = 0; i < max; i++) {
            //先放置皇后，表示将第n个皇后放在第i列
            array[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    /**
     * 检测放入第n个皇后与前面的皇后是否存在位置冲突
     *
     * @param n
     * @return
     */
    public boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            //检测是否同一列，同一对角线，默认已经保证不在同一行
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }

    /**
     * 打印皇后的位置
     */
    public void print() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
