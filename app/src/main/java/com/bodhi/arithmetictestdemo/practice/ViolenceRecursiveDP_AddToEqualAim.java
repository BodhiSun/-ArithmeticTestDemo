package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/30 14:12
 * desc :暴力递归到动态规划(DP) 求数组中任意组合得到指定值
 *
 * 【题目】给你一个数组arr，和一个整数aim。如果可以任意选择arr中的数字，能不能累加得到aim，返回true或
 * 者false。arr和aim都是正数
 *
 * 动态规划：
 * 1.写出暴力递归isSum尝试思路
 * 2.判断isSum是否无后效性 无后效性
 * 3.找可变参数为 i,sum 列出可变参数变化范围 构建二维表
 * 4.找出终止位置 和 base-case不依赖其他位置的值提前填好
 * 5.普遍位置推断出依赖哪些位置
 *
 *
 */
public class ViolenceRecursiveDP_AddToEqualAim {


    /**
     *暴力递归-尝试思路版
     * @param arr 给定数组
     * @param i 到了i位置
     * @param sum i位置形成的和
     * @param aim 给定整数
     * @return
     */
    public static boolean isSum(int[]arr,int i,int sum,int aim){
        if(i==arr.length){
            return sum==aim;
        }

        //要或者不要当前数
        return isSum(arr,i+1,sum,aim)||isSum(arr,i+1,sum+arr[i],aim);

    }

    /**
     * 根据递归版改出的动态规划版
     * @param arr 给定数组
     * @param aim 给定整数
     * @return
     */
    public static boolean isSumDP(int[] arr,int aim){
        boolean[][]dp = new boolean[arr.length+1][aim+1];
        for (int i = 0; i < dp.length; i++) {
            dp[i][aim]=true;
        }

        for(int i=arr.length-1;i>=0;i--){
            for(int j=aim-1;j>=0;j--){
                dp[i][j]=dp[i+1][j];
                if(j+arr[i]<=aim){
                    dp[i][j]=dp[i][j]||dp[i+1][j+arr[i]];
                }
            }
        }
        return dp[0][0];
    }



    public static void test(){
        int[] arr = { 1, 4, 8 };
        int aim = 5;
        int aim2 = 7;
        System.out.println(isSum(arr,0,0,aim));
        System.out.println(isSumDP(arr,aim));
        System.out.println("=============");
        System.out.println(isSum(arr,0,0,aim2));
        System.out.println(isSumDP(arr,aim2));


    }





}
