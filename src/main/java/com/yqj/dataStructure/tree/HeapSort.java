package com.yqj.dataStructure.tree;

import java.util.Arrays;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: HeapSort
 * Author: yaoqijun
 * Date: 2020/11/14 10:21
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, -1, -23, 66, -99};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void heapSort(int[] arr) {
        //建堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        //调整堆 n-1 次
        for (int i = arr.length - 1; i > 0; i--) {
            //交换堆顶元素到末尾
            int temp = arr[i];
            arr[i] = arr[0];
            arr[0] = temp;
            //调整堆 由于堆已经有序，只是改变堆顶元素，所以直接从0索引位置开始即可
            adjustHeap(arr, 0, i);
        }
    }

    private static void adjustHeap(int[] arr, int i, int length) {
        int temp = arr[i];
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k + 1] > arr[k]) {
                k++;
            }
            //只有当前元素大于了当前定位的调整值，再调整才有意义
            if (arr[k] > temp) {
                arr[i] = arr[k];
                i = k;
            } else {
                break;
            }
        }
        arr[i] = temp;
    }


}
