package com.yqj.dataStructure.algorithm;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: Knapsack
 * Author: yaoqijun
 * Date: 2020/11/17 10:37
 * 功能：利用动态规划的思想实现01背包问题（01背包问题：获取规定重量的价值最高的一系列物品且物品不重复）
 */
public class Knapsack {
    public static void main(String[] args) {
        int[] val = {1500, 3000, 2000};
        int[] weight = {1, 4, 3};
        knapsack(val, weight, 4);
    }

    public static void knapsack(int[] val, int[] weight, int volumn) {

        int[][] v = new int[val.length + 1][volumn + 1];
        int[][] path = new int[val.length + 1][volumn + 1];

        //将第一列和第一行置零
        for (int i = 0; i < v.length; i++) {
            v[i][0] = 0;
        }
        for (int i = 0; i < v[0].length; i++) {
            v[0][i] = 0;
        }

        //构建动态规划表
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[i].length; j++) {
                if (weight[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
                    if (v[i - 1][j] > val[i - 1] + v[i - 1][j - weight[i - 1]]) {
                        v[i][j] = v[i - 1][j];
                    } else {
                        v[i][j] = val[i - 1] + v[i - 1][j - weight[i - 1]];
                        path[i][j] = 1;
                    }
                }
            }
        }

        //打印动态规划表
        for (int i = 0; i < v.length; i++) {
            for (int j = 0; j < v[i].length; j++) {
                System.out.print(v[i][j] + " ");
            }
            System.out.println();
        }

        //打印放入背包的物品
        int i = path.length - 1;
        int j = path[0].length - 1;
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "件商品放入背包");
                j -= weight[i - 1];
            }
            i--;
        }
    }
}
