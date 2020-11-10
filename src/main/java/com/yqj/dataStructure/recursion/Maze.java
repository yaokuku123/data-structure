package com.yqj.dataStructure.recursion;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: maze
 * Author: yaoqijun
 * Date: 2020/11/10 10:56
 */
public class Maze {
    public static void main(String[] args) {
        //构造迷宫
        int[][] map = new int[8][7];
        for (int i = 0; i < map.length; i++) {
            map[i][0] = 1;
            map[i][map[0].length - 1] = 1;
        }
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[map.length - 1][i] = 1;
        }
        map[3][1] = 1;
        map[3][2] = 1;

        //打印迷宫
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("*************");
        //迷宫寻路
        getWay(map, 1, 1);

        //打印迷宫
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 获取迷宫路径
     *
     * @param map
     * @param startI
     * @param startJ
     */
    public static boolean getWay(int[][] map, int startI, int startJ) {
        if (map[map.length - 2][map[0].length - 2] == 2) {
            return true;
        } else {
            if (map[startI][startJ] == 0) {
                map[startI][startJ] = 2;
                if (getWay(map, startI + 1, startJ)) {
                    return true;
                } else if (getWay(map, startI, startJ + 1)) {
                    return true;
                } else if (getWay(map, startI - 1, startJ)) {
                    return true;
                } else if (getWay(map, startI, startJ - 1)) {
                    return true;
                } else {
                    map[startI][startJ] = 3;
                    return false;
                }
            } else {
                return false;
            }
        }
    }
}
