package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/30 22:10
 * desc :小和问题-分治思想
 *
 * 【题目】在一个数组中，每一个数左边比当前数小的数累加起来，叫做这个数组的小和。求一个数组 的小和。
 * 例子：[1,3,4,2,5]
 * 1左边比1小的数，没有；
 * 3左边比3小的数，1；
 * 4左边比4小的数，1、3；
 * 2左边比2小的数，1；
 * 5左边比5小的数，1、3、4、2；
 * 所以小和为1+1+3+1+1+3+4+2=16
 *
 * 逆序对问题 在一个数组中，左边的数如果比右边的数大，则这两个数构成一个逆序对，请打印所有逆序对。
 *
 */
public class Recursion_SmallSum {

    public static int smallSum(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }
        return mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * l-r范围上一共产生多少小和并返回
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int mergeSort(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);

        //左侧部分产生的小和 右侧部分产生的小和 合并过程产生的小和
        return mergeSort(arr, l, mid) +
                mergeSort(arr, mid + 1, r) +
                merge(arr, l, mid, r);
    }

    /**
     * 左右两部分合并产生的小和 只比归并排序多了一个res字段
     * @param arr
     * @param l
     * @param m
     * @param r
     * @return
     */
    public static int merge(int[] arr, int l, int m, int r) {
        int[] help = new int[r - l + 1];
        int i = 0;
        int p1 = l;
        int p2 = m + 1;
        int res = 0;//多申请一个变量 用于记录产生的小和

        while (p1 <= m && p2 <= r) {
            //如果p1比p2小 产生 p1* p2到r总个数 的小和
            res += arr[p1] < arr[p2] ? (r - p2 + 1) * arr[p1] : 0;
            //
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];
        }

        while (p1 <= m) {
            help[i++] = arr[p1++];
        }
        while (p2 <= r) {
            help[i++] = arr[p2++];
        }
        for (i = 0; i < help.length; i++) {
            arr[l + i] = help[i];
        }
        return res;

    }


}
