package com.yqj.dataStructure.sparse;

/**
 * Copyright(C),2019-2020,XXX公司
 * FileName: SparseTest
 * Author: yaoqijun
 * Date: 2020/11/2 8:42
 * 实现：将普通二维数组转化为稀疏矩阵的存储方式；将稀疏矩阵恢复为原始的二维数组
 */
public class SparseTest {
    private int sum;

    public static void main(String[] args) {

        //初始化普通二维数组
        int arr[][] = initArr();

        //转换为稀疏数组
        int sparseArr[][] = arrToSparseArr(arr);

        //转换为普通二维数组
        int arrRestore[][] = sparseArrToArr(sparseArr);
    }


    /**
     * 模拟创建普通二维数组，并给予其中某些位置初始值
     *
     * @return 普通二维数组
     */
    public static int[][] initArr() {
        int arr[][] = new int[8][10];
        arr[1][2] = 1;
        arr[2][3] = 2;
        arr[3][1] = 1;

        //打印原始二维数组
        System.out.println("原始二维数组：");
        for (int[] line : arr) {
            for (int item : line) {
                System.out.printf("%d\t", item);
            }
            System.out.println();
        }
        return arr;
    }

    /**
     * 转换为稀疏数组
     *
     * @param arr 原始二维数组
     * @return 稀疏数组
     */
    public static int[][] arrToSparseArr(int[][] arr) {
        //1.遍历原始二维数组获取存储有效元素的个数
        int sum = 0;
        for (int[] line : arr) {
            for (int item : line) {
                if (item != 0) {
                    sum++;
                }
            }
        }
        //2.构造稀疏数组
        int sparseArr[][] = new int[sum + 1][3];
        //3.初始化第一列 存储二维数组的长、宽和总有效元素个数
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        sparseArr[0][2] = sum;
        //4.稀疏数组其他位置赋值 三列分别存储 行(row) 列(col) 值(val)
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                }
            }
        }
        //5.打印查看稀疏数组
        System.out.println("稀疏数组：");
        for (int[] line : sparseArr) {
            System.out.printf("%d\t%d\t%d\t\n", line[0], line[1], line[2]);
        }
        return sparseArr;
    }

    /**
     * 转换为普通二维数组
     * @param sparseArr 稀疏数组
     * @return 普通二维数组
     */
    private static int[][] sparseArrToArr(int[][] sparseArr) {
        //根据第一行的数值构造普通二维数组
        int arr[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        //填入元素数值
        for (int i = 1; i < sparseArr.length; i++) { //跳过第一行
            arr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //打印恢复后的二维数组
        System.out.println("恢复后的二维数组：");
        for (int[] line : arr) {
            for (int item : line) {
                System.out.printf("%d\t",item);
            }
            System.out.println();
        }
        return arr;
    }
}
