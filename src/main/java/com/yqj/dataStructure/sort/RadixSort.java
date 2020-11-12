package com.yqj.dataStructure.sort;

import java.util.Arrays;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: RedixSort
 * Author: yaoqijun
 * Date: 2020/11/12 9:12
 */
public class RadixSort {
    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 748, 14, 214};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        radixSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    private static void radixSort(int[] arr) {
        //创建二维数组，表示10个桶，每个桶为一维数组，需要开到最大，防止极端情况
        int[][] bucket = new int[10][arr.length];
        //记录每个桶保存原始的个数
        int[] bucketElmCounts = new int[10];
        //获取数据中最大元素的位数
        int maxVal = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i]>maxVal){
                maxVal = arr[i];
            }
        }
        int maxNum = (maxVal+"").length();
        //遍历maxNum轮，桶排序
        for (int i = 0, n = 1; i < maxNum; i++, n *= 10) {
            //将每轮的数据按顺序放入各个桶中
            for (int j = 0; j < arr.length; j++) {
                int bucketNum = arr[j] / n % 10;
                bucket[bucketNum][bucketElmCounts[bucketNum]] = arr[j];
                bucketElmCounts[bucketNum]++;
            }
            //取出桶中元素
            int index = 0;
            for (int j = 0; j < bucket.length; j++) {
                if (bucketElmCounts[j] != 0 ){
                    for (int k = 0; k < bucketElmCounts[j]; k++) {
                        arr[index++] = bucket[j][k];
                    }
                    //放入后将原数组对应位置置空，用于下一轮的数据存放准备
                    bucketElmCounts[j] = 0;
                }
            }
        }
    }
}
