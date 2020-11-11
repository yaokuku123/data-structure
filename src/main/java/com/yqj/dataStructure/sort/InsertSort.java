package com.yqj.dataStructure.sort;

import java.util.Arrays;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: InsertSort
 * Author: yaoqijun
 * Date: 2020/11/11 15:43
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {6, 12, 1, -3, 13, 5, 64};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            int insertVal = arr[i];
            int insertIndex = i - 1;
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            arr[insertIndex + 1] = insertVal;
        }
    }
}
