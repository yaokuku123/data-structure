package com.yqj.dataStructure.sort;

import java.util.Arrays;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: SelectSort
 * Author: yaoqijun
 * Date: 2020/11/11 14:42
 */
public class SelectSort {
    public static void main(String[] args) {
        int[] arr = {101,34,119,1};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void selectSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            int minVal = arr[minIndex];
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < minVal){
                    minIndex = j;
                    minVal = arr[j];
                }
            }
            if (minIndex != i){
                arr[minIndex] = arr[i];
                arr[i] = minVal;
            }
        }
    }
}
