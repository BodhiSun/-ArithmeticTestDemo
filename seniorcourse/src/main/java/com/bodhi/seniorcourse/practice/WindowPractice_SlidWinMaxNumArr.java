package com.bodhi.seniorcourse.practice;

import java.util.LinkedList;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/21 15:04
 * desc :窗口结构-生成滑动窗口最大值数组
 *
 * 【题目】
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑一个位置
 * 请实现一个函数返回生成所有滑窗口最大值的数组。
 * 已知 如果数组长度为n 窗口大小为w ，则一共可产生 n-w+1个窗口
 *
 */
public class WindowPractice_SlidWinMaxNumArr {

    /**
     * 给定一个数组和 滑动窗口的大小 返回所有窗口最大值的数组
     * 时间复杂度：O(n) 因为每个数都只会进队列依次出队列一次
     *
     * @param arr 给定数组
     * @param w   滑动窗口大小
     * @return
     */
    public static int[] getMaxWindow(int[] arr, int w) {
        if (arr == null || w < 1 || arr.length < w) {
            return null;
        }
        //LinkList就是双向队列 qmax中存数组的角标
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        // arr.length-w+1 可产生最大值的个数
        int[] res = new int[arr.length - w + 1];
        int index = 0;
        //依次遍历数组中的字符
        for (int i = 0; i < arr.length; i++) {
            //当队列不空 并且队列末尾值小于等于当前值时就弹出末尾
            while (!qmax.isEmpty() && arr[qmax.peekLast()] <= arr[i]) {
                qmax.pollLast();
            }

            //否则队列为空或者队列末尾大于当前值时加入末尾
            qmax.addLast(i);

            //判断队列头是否超过范围即过期
            if (qmax.peekFirst() == i - w) {
                qmax.pollFirst();
            }

            //i>=w-1判断当前窗口是否形成
            if (i >= w - 1) {
                //将当前滑动窗口的最大值添加到res数组中
                res[index++] = arr[qmax.peekFirst()];
            }
        }
        return res;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void test() {
        int[] arr = new int[]{4, 3, 5, 4, 3, 3, 6, 7};
        printArray(getMaxWindow(arr, 3));
    }


}
