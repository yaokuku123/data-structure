package com.yqj.dataStructure.search;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: InsertValueSearch
 * Author: yaoqijun
 * Date: 2020/11/12 12:18
 */
public class InsertValueSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = insertValueSearch(arr, 0, arr.length - 1, 1);
        System.out.println(index);
    }

    private static int insertValueSearch(int[] arr, int left, int right, int val) {
        if (left > right || val < arr[left] || val > arr[right]) {
            return -1;
        }
        //插值插值的计算公式，该方法需要查找的值参与计算，故为自适应算法
        int mid = left + (right - left) * (val - arr[left]) / (arr[right] - arr[left]);
        if (arr[mid] > val) {
            return insertValueSearch(arr, left, mid - 1, val);
        } else if (arr[mid] < val) {
            return insertValueSearch(arr, mid + 1, right, val);
        } else {
            return mid;
        }
    }
}
