package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/9/3 20:25
 * desc :累加和最长子数组-窗口
 *
 * 【题目】
 * 给定一个数组arr，注意全是正数；一个整数aim，求累加和等于aim的，最长子数组，注意要求额外空间复杂度O(1)，
 * 时间复杂度O(N)
 *
 *思路：用窗口的结构解决
 * 准备两个指针L、R,然后R向右扩数 判断当前窗口内累加和sum是否大于aim 如果大于aim L指针向右移动 如果小于aim R
 * 指针向右移动 当sum等于aim时 记录下当前窗口内数据个数 然后L指针向右移动检查下一个字符开头有没有可能累加
 * 倒aim，因为都是正数所以累加和只会增加不会变少 并且以某一字符开始累加到aim的子串也最多只能有一个。因为L
 * 和 R指针都只会从左到右滑过窗口一次 所以时间复杂度为O(N)
 *
 */
public class Practice_LongestSumSubArray2 {

    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }
        //初始窗口扩在数组的第一个字符
        int L = 0;
        int R = 0;
        //窗口累加和最大值
        int sum = arr[0];
        int len = 0;//累加和等于aim的窗口大小
        while (R < arr.length) {
            if (sum == aim) {
                len = Math.max(len, R - L + 1);
                //L右移
                sum -= arr[L++];
            } else if (sum < aim) {
                R++;
                //右边扩的时候 注意越界
                if (R == arr.length) {
                    break;
                }
                sum += arr[R];
            } else {
                //sum>aim
                sum -= arr[L++];
            }
        }
        return len;
    }

    public static void test(){
        int[] arr = new int[]{7,3,2,1,1,7,7,7};
        System.out.println(getMaxLength(arr,7));
    }


}
