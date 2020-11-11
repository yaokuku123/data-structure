package com.yqj.dataStructure.sort;

import java.util.Arrays;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: ShellSort
 * Author: yaoqijun
 * Date: 2020/11/11 18:11
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 7, 9, 8, 3, 6};
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        shellSort2(arr);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 使用交换法的希尔排序
     *
     * @param arr
     */
    public static void shellSort1(int[] arr) {
        int temp = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    /**
     * 使用移位法的希尔排序
     *
     * @param arr
     */
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                int tempIndex = i - gap;
                int tempVal = arr[i];
                while (tempIndex >= 0 && tempVal < arr[tempIndex]) {
                    arr[tempIndex + gap] = arr[tempIndex];
                    tempIndex -= gap;
                }
                arr[tempIndex + gap] = tempVal;
            }
        }
    }
}
