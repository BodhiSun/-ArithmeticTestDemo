package com.bodhi.seniorcourse.practice;

import java.util.LinkedList;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/21 15:04
 * desc :了解窗口和窗口内最大值或最小值的更新结构(单调双向队列)
 * <p>
 * 【题目】生成滑动窗口最大值数组
 * 有一个整型数组arr和一个大小为w的窗口从数组的最左边滑到最右边，窗口每次向右滑一个位置
 * 请实现一个函数返回生成所有滑窗口最大值的数组。
 * 已知 如果数组长度为n 窗口大小为w ，则一共可产生 n-w+1个窗口
 * <p>
 * <p>
 * 窗口：即数组中一段L到R之间的数即为窗口
 * 窗口性质：
 * 1.L,R初始都在数组的最左边 位置可表示为-1
 * 2.L和R只能向右移，不可以后退
 * 3.R右移表示窗口增加数 L右移表示窗口剔除数
 * 4.L不可超过R
 * <p>
 * <p>
 * 双向队列:既可以从头部弹出，也可以从尾部弹出的队列结构
 * 队列满足从左到右(头到尾)由大到小排列，从头遍历数组入队列或出队列规则：
 * 1.如果队列为空，则当前数字入队列
 * 2.如果当前数字小于队列尾，则当前数字入队列
 * 3.如果当前数字大于队列尾 则弹出队列尾 直到当前数字小于等于队列尾，或者队列空，然后当前数字入队列
 * 4.如果窗口有限制大小时 当队列头超出滑动窗口范围，则弹出队列头
 * 这样能始终保证队列头为当前的最大值
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
