package com.bodhi.seniorcourse.practice;

import java.util.HashMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/28 11:49
 * desc :最多异或和为0的子数组
 *
 * 【题目】
 * 给定一个数组arr，你可以任意把arr分成很多不相容的子数组，你的目的是：分出来的子数组中，异或和为0的子数组
 * 最多。请返回：分出来的子数组中，异或和为0的子数组最多是多少？
 *
 * 定义数组的异或和的概念：
 * 数组中所有的数异或起来，得到的结果叫做数组的异或和，比如数组{3,2,1}的异或和是，3^2^1 = 0
 *
 * 异或:也叫半加运算，其运算法则相当于不带进位的二进制加法 二进制下用1表示真，0表示假 则有如下异或运算法则：
 *  0^0=0, 1^1=0, 0^1=1, 1^0=1 所以异或运算满足交换律和结合律 这些法则与加法是相同的，只是不带进位
 *
 *
 * 思路：
 * 当求在数组上某一段范围0到i内能分出来多少异或和为0的数组时 假设存在最优划分 则以i为结尾的最后一段数组有两
 * 种情况：第一种i所在的部分不是异或和为0 则0到i上最多能划多少块和0到i-1最多能划多少块，数量一定相等 第二种
 * i所在的部分是异或和为0的子数组 此时k一定是i所在部分的最左边界 同时k一定也是在0到i范围内 左边离i最近的异或
 * 和为0的位置 中间不可能存在一个j到i是异或和0 否则之前的假设最优划分就不成立。在第二种情况的基础上在假设0到
 * i异或和为sum 同时k到i异或和为0 所以0到k-1的异或和为sum 所以找0到i-1范围内 异或和还是sum的最晚位置 它的
 * 位置的下一个位置就是k的位置。所以dp[i]=Math.max(dp[i-1],dp[k-1]+1)即两种决策中选最大的就是答案
 *
 */
public class Practice_MostEorNum {

    /**
     * 整体的结题思路变成 找最晚出现异或和等于当前异或和的位置
     * @param arr
     * @return
     */
    public static int mostEor(int[] arr){
        //最优划分最大有多少个子数组满足
        int ans = 0;
        //从0到当前位置异或和的结果
        int xor = 0;
        //每个位置的两种决策下 最优划分分成多少个满足条件子数组
        int[] dp = new int[arr.length];
        //每个位置异或和结果 和 对应的位置
        HashMap<Integer,Integer> map = new HashMap<>();
        map.put(0,-1);

        for (int i = 0; i < arr.length; i++) {
            //到每一个位置的异或和
            xor^=arr[i];

            if(map.containsKey(xor)){
                //和当前异或和相等的最晚位置k
                int k = map.get(xor);
                dp[i] = k==-1?1:(dp[k]+1);
            }

            //两种决策中取最大值
            if(i>0){
                dp[i] =Math.max(dp[i-1],dp[i]);
            }

            //因为找的是最晚的位置 所以每次的异或和都更新
            map.put(xor,i);

            //更新最大值
            ans = Math.max(ans,dp[i]);
        }
        return ans;
    }

    public static void test(){
        int[] arr = new int[]{0,3,2,1,0,1,2,3};
        System.out.println(mostEor(arr));
    }



}
