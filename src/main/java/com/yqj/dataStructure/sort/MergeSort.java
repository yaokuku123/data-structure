package com.yqj.dataStructure.sort;

import java.util.Arrays;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: MergeSort
 * Author: yaoqijun
 * Date: 2020/11/11 20:57
 */
public class MergeSort {
    public static void main(String[] args) {
        int[] arr = {6, 12, 1, -3, 13, 5, 64};
        int[] temp = new int[arr.length];
        System.out.println("排序前：");
        System.out.println(Arrays.toString(arr));
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println("排序后：");
        System.out.println(Arrays.toString(arr));
    }

    //分
    private static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid, temp);
            mergeSort(arr, mid + 1, right, temp);
            merge(arr, left, mid, right, temp);
        }
    }

    //治
    private static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;
        int j = mid + 1;
        int t = 0;

        //先把两个子串小的部分拷贝到临时数组
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else {
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        //将剩余的部分全部复制到临时数组
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        //将临时数组的内容拷贝到原来的数组中
        int tempIndex = left;
        t = 0;
        while (tempIndex <= right) {
            arr[tempIndex] = temp[t];
            t++;
            tempIndex++;
        }
    }
}
