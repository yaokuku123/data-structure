package com.yqj.dataStructure.search;

import java.util.Arrays;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: FibSearch
 * Author: yaoqijun
 * Date: 2020/11/12 21:28
 */
public class FibSearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int index = fibSearch(arr, 89);
        System.out.println(index);
    }

    /**
     * 获取斐波那契数列
     *
     * @param maxSize
     * @return
     */
    public static int[] fib(int maxSize) {
        int[] arr = new int[maxSize];
        arr[0] = 1;
        arr[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            arr[i] = arr[i - 1] + arr[i - 2];
        }
        return arr;
    }

    /**
     * 斐波那契查找
     *
     * @param arr
     * @param val
     * @return
     */
    private static int fibSearch(int[] arr, int val) {
        int[] f = fib(20);
        int low = 0;
        int high = arr.length - 1;
        int k = 0; //斐波那契分割数值的下标
        int mid = 0;
        //遍历斐波那契数组找到比high值大的下标
        while (high > f[k] - 1) {
            k++;
        }
        //填充实现后面不足的部分为原数组的最后一个元素的数值
        int[] temp = Arrays.copyOf(arr, f[k] - 1);
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (temp[mid] > val) {
                high = mid - 1;
                k -= 1;
            } else if (temp[mid] < val) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid > high) {
                    return high;
                } else {
                    return mid;
                }
            }
        }
        return -1;
    }
}
