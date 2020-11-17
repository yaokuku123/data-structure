package com.yqj.dataStructure.algorithm;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: BinarySearch
 * Author: yaoqijun
 * Date: 2020/11/17 9:13
 * 功能：使用非递归的二分查找算法
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 5, 12, 33, 67, 123};
        int index = binarySearch(arr, 5);
        System.out.println("index:" + index);
    }

    /**
     * 非递归的二分查找算法
     *
     * @param arr   原始有序升序数组
     * @param value 查找数值
     * @return 数值所在下标
     */
    public static int binarySearch(int[] arr, int value) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (value == arr[mid]) {
                return mid;
            } else if (value < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
}


