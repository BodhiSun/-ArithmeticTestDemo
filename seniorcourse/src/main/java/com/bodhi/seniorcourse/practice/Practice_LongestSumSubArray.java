package com.bodhi.seniorcourse.practice;

import java.util.HashMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/28 10:42
 * desc :累加和最长子数组
 *
 * 【题目】
 * 给定一个数组arr（有0、正、负），和一个整数aim，求在arr中，累加和等于aim的最长子数组的长度
 * eg：
 *  arr ={7,3,2,1,1,7,7,7}  aim = 7
 * 其中有很多的子数组累加和等于7，但是最长的子数组是{3,2,1,1}，所以返回其长度4
 *
 * 思路：总的思路是找出以每个位置结尾的情况下如果有要求答案，那答案一定在其中。
 * 具体流程：依次计算每个位置的累加和 假设来到位置i 并且0到i位置的总的累加和是sum,如果想求以i位置结尾的
 * 最长累加和是aim 只要求出0到i-1位置范围内最早出现累加和是(sum-aim）的位置k即可，则k+1到i就是以i结尾
 * 累加和是aim的最长子数组起始位 所以记录的数据为 0到当前位置总的累加和sum 然后找前面记录的累加和中有没有
 * 等于(sum-aim)的值的位置k 然后记录k到i的长度 找到最大长度值即可。所有的累加和值只记录第一次出现的位置，
 * 后面再遇见同样的数都不更新 初始累加和为0位置在-1 为了不错过从0开始出现答案的机会
 *
 *
 * 【变形】
 * 1.一个数组中只有整数，有奇数也有偶数，求奇数和偶数个数相等的最长子数组。
 * 解法：把奇数改为1，偶数改为-1，把累加和为0的最长子数组查出即可
 *
 * 2.数组中只有1和0的话，求1和0个数相等的最长子数组
 * 解法：把0改为-1，计算出累加和为0的最长子数组即可
 *
 * 3.数组中只有0、1、2的话，求1、2数量相等的最长子数组
 * 解法：把2变-1，计算出累加和为0的最长子数组即可
 *
 */
public class Practice_LongestSumSubArray {

    public static int maxSumLength(int[] arr,int aim){
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //用来记录每个位置的累加和
        HashMap<Integer,Integer> map = new HashMap<>();
        //初始添加0，-1 为了不错过从0开始出现答案的机会
        map.put(0,-1);
        //出现目标累加和子数组的最大长度
        int len = 0;
        //当前位置的累加和
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum+=arr[i];
            //查前面的累加和中是否出现过值为(sum-aim)的累加和记录 存在更新len值
            if(map.containsKey(sum-aim)){
                len = Math.max(i-map.get(sum-aim),len);
            }

            //查当前位置的累加和值 是否存过记录没有的话存入
            if(!map.containsKey(sum)){
                map.put(sum,i);
            }
        }
        return len;
    }


    public static void test(){
        int[] arr = new int[]{7,3,2,1,1,7,7,7};
        System.out.println(maxSumLength(arr,7));
    }


}
