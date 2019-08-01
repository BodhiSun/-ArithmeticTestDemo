package com.bodhi.arithmetictestdemo;

import android.util.Log;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/12 15:34
 * desc :非基于比较的排序
 * <p>
 *  桶排序包含:计数排序、基数排序
 *  1，非基于比较的排序，与被排序的样本的实际数据状况很有关系，所以实际中并不经常使用
 *  2，时间复杂度O(N)，额外空间复杂度O(N)
 *  3，稳定的排序
 */
public class SortUtil2 {


    /**
     * 桶排序-计数排序(按数值准备桶)
     * @param arr 待排序数组 only for 0~200 value
     */
    public static void bucketSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        //找到数组中最大值
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            max =Math.max(max,arr[i]);
        }

        //即整个数组值的范围为0~max 准备max+1个桶 桶内初始默认值现在都为0
        int[] bucket =new int[max+1];

        //向桶里添加数据
        for (int i = 0; i < arr.length; i++) {
            //出现一次对应的数 就在对应位置的桶++ 1次；
            bucket[arr[i]]++;
        }

        int i = 0;
        for (int j = 0; j < bucket.length; j++) {
            //将桶内数据倒出来 如果有多个数据循环倒出
            while (bucket[j]-->0){
                //重新给数组赋值
                arr[i++]=j;
            }
        }

    }

    /**
     * 桶排序-基数排序(按数值位准备桶)
     * @param arr 待排序数组 only for 0~200 value
     */
    public static void bucketSort2(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
    }


    //不完全准确
    public static void swap2(Integer[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
