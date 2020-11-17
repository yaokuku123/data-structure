package com.yqj.dataStructure.algorithm;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: ViolenceSearch
 * Author: yaoqijun
 * Date: 2020/11/17 9:41
 */
public class ViolenceSearch {
    public static void main(String[] args) {
        String str1 = "hello world ni hao yorick jun";
        String str2 = "ni hao";
        int index = violenceSearch(str1, str2);
        System.out.println("index:"+index);
    }

    /**
     * 使用暴力的方式查找字符串的匹配问题
     *
     * @param str1
     * @param str2
     */
    public static int violenceSearch(String str1, String str2) {
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int i = 0;
        int j = 0;

        while (i < s1.length && j < s2.length) {
            if (s1[i] == s2[j]) {
                i++;
                j++;
            } else {
                i = i - j + 1;
                j = 0;
            }
        }
        if (j == s2.length) {
            return i - j;
        } else {
            return -1;
        }
    }
}
