package com.yqj.dataStructure.search;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: BinarySearch
 * Author: yaoqijun
 * Date: 2020/11/12 10:59
 */
public class BinarySearch {
    public static void main(String[] args) {
//        int[] arr1 = {1, 8, 10, 89, 1000, 1234};
//        int index = binarySearch1(arr1, 0, arr1.length - 1, 1000);
//        System.out.println("index=" + index);

        int[] arr2 = {1, 8, 10, 89, 1000, 1000, 1000, 1234};
        List<Integer> list = binarySearch2(arr2, 0, arr2.length - 1, 1000);
        System.out.println(list);
    }

    /**
     * 二分查找，查到含有val的全部元素所在位置的下标集合
     *
     * @param arr
     * @param left
     * @param right
     * @param val
     * @return
     */
    private static List<Integer> binarySearch2(int[] arr, int left, int right, int val) {
        //没有找到返回空集合
        if (left > right) {
            return new ArrayList<>();
        }
        int mid = (left + right) / 2;
        if (arr[mid] > val) {
            return binarySearch2(arr, left, mid - 1, val);
        } else if (arr[mid] < val) {
            return binarySearch2(arr, mid + 1, right, val);
        } else {
            List<Integer> list = new ArrayList<>();
            int temp = mid - 1;
            while (true) {
                if (temp < 0 || arr[temp] != val) {
                    break;
                }
                list.add(temp);
                temp--;
            }
            list.add(mid);
            temp = mid + 1;
            while (true) {
                if (temp > arr.length - 1 || arr[temp] != val) {
                    break;
                }
                list.add(temp);
                temp++;
            }
            return list;
        }
    }

    /**
     * 二分查找，查到含有val元素后返回该元素所在位置的下标
     *
     * @param arr   原数组
     * @param left  左索引
     * @param right 右索引
     * @param val   查找数值
     * @return 所在位置下标
     */
    private static int binarySearch1(int[] arr, int left, int right, int val) {
        //没有查到，退出
        if (left > right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (arr[mid] < val) {
            return binarySearch1(arr, mid + 1, right, val);
        } else if (arr[mid] > val) {
            return binarySearch1(arr, left, mid - 1, val);
        } else {
            return mid;
        }
    }


}
