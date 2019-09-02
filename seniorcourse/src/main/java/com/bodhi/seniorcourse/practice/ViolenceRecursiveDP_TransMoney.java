package com.bodhi.seniorcourse.practice;

import java.util.HashMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/9/2 11:53
 * desc :暴力递归到动态规划-换钱的方法数
 *
 * 【题目】
 * 给定数组arr，arr中所有的值都为正数且不重复。每个值代表一种面值的货币，每种面值的货币可以使用任意张，再
 * 给定一个整数aim代表要找的钱数，求换钱有多少种方法。
 * eg:
 * arr=[5,10,25,1]，aim=0。 组成0元的方法有1种，就是所有面值的货币都不用。所以返回1。
 * arr=[3,5]，aim=2。 任何方法都无法组成2元。所以返回0。
 * arr=[5,10,25,1]，aim=15。 组成15元的方法有6种，分别为3张5元、1张10元+1张5元、1张 10元+5张1元、
 * 10张1元+1张5元、2张5元+5张1元和15张1元。所以返回6。
 *
 *
 * VR到DP的步骤:
 * 1.写出暴力递归的尝试版本
 * 尝试思路：假如arr=[200,100,50,20] aim=1000;当200=0张时 后面的数组合成1000的方法可能有a种，当200=1张时
 * 后面的数组合800可能有b种，当200=2,3,4,5时 后面组合成600,400,200,0有c、d、e、f种，所以一共有a+b+c+d+e+F
 * 2.判断尝试的递归方案是否有后效性
 * 后效性:例如在下面process1方法中 当起始位置index确定 aim确定时 返回的结果一定相同，即在步骤一的例子中只
 * 要从50开始 即index=2,aim=600时，无论是200*0+100*4这条路 或是200*1+100*2等任何路径 只要到达求从50开始
 * aim等于600 那么process1返回的结果一定确定，和前面的状态或路径无关 这就叫无后效性。
 * 3.把可变参数找到即index、aim，分析可变参数可变参数的变化范围代表返回值的变化范围 可变参数是几维就构建几维结果表
 * 4.找到终止位置index为0 aim时为待求终止位置 在表上标记上 找到base-case中 完全不依赖其他位置的值设置好
 * 5.一个普遍位置需要哪些位置 子递归中的参数含义 逆序回去就是填表的顺序
 *
 */
public class ViolenceRecursiveDP_TransMoney {

    /**
     * 暴力递归方法返回给定数组 兑换aim的方法数
     * @return
     */
    public static int transMoneyVR(int[]arr,int aim){
        if(arr == null|| arr.length ==0 || aim<0){
            return 0;
        }
        return processVR(arr,0,aim);

    }

    /**
     * 暴力递归基础上优化-记忆化搜索 返回给定数组兑换aim的方法数
     * 算是粗糙的优化方法
     * @return
     */
    public static int transMoneyMS(int[]arr,int aim){
        if(arr == null|| arr.length ==0 || aim<0){
            return 0;
        }
        return processMap(arr,0,aim);
    }


    /**
     * 暴力递归基础上->动态规划 返回给定数组兑换aim的方法数
     * @return
     */
    public static int transMoneyDP(int[]arr,int aim){
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        //构建可变参数的二维数组 arr取值范围为纵向从上到下，aim取值范围为横向从左到右
        int[][] dp = new int[arr.length][aim+1];

        //把二维表中 完全不依赖其他位置的值设置好
        //即当aim=0时 arr在哪个位置都为1
        for (int i = 0; i < arr.length; i++) {
            dp[i][0]=1;
        }
        for (int j=1;arr[0]*j<=aim;j++){
            dp[0][arr[0]*j]=1;
        }

        //然后根据VR中if-false逻辑 根据arr下一行的 左边值推当前值
        int num = 0;
        for (int i=1;i<arr.length;i++){
            for(int j=1;j<=aim;j++){
                num=0;
                for (int k=0;j-arr[i]*k>=0;k++){
                    num+=dp[i-1][j-arr[i]*k];
                }
                dp[i][j]=num;
            }
        }
        return dp[arr.length-1][aim];
    }

    /**
     * 动态规划进一步优化->动态规划 返回给定数组兑换aim的方法数
     * 建立起空间感后发现 当前位置的值可以根据它左边的值和下方的值求出
     * @return
     */
    public static int transMoneyDP2(int[]arr,int aim){
        if (arr == null || arr.length == 0 || aim < 0) {
            return 0;
        }

        //构建可变参数的二维数组 arr取值范围为纵向从上到下，aim取值范围为横向从左到右
        int[][] dp = new int[arr.length][aim+1];

        //把二维表中 完全不依赖其他位置的值设置好
        //即当aim=0时 arr在哪个位置都为1
        for (int i = 0; i < arr.length; i++) {
            dp[i][0]=1;
        }
        for (int j=1;arr[0]*j<=aim;j++){
            dp[0][arr[0]*j]=1;
        }

        //然后根据VR中if-false逻辑 根据arr下一行的 左边值推当前值
        for (int i=1;i<arr.length;i++){
            for(int j=1;j<=aim;j++){
                dp[i][j] = dp[i - 1][j];
                dp[i][j] += j - arr[i] >= 0 ? dp[i][j - arr[i]] : 0;
            }
        }
        return dp[arr.length-1][aim];
    }


    /**
     * VR的尝试思路 任意自由组合arr数组中index及其以后所有的钱 达到目标钱数aim 返回有多少种方法
     * @param arr 给定数组
     * @param index 从哪个位置开始
     * @param aim 要兑换的数
     * @return 返回有多少种方法
     */
    private static int processVR(int[] arr, int index, int aim) {
        int res =0;
        //basecase
        if(index==arr.length){
            //当index递归到数组边界外时 此时如果前面的数已经组合到aim了此种方法为有效方法返回1 否则返回0
            res=aim==0?1:0;
        }else{
            //当前index位置的钱从0张到最大张数时 后面的数能构成aim的不同方法数
            for (int num = 0; arr[index]*num <=aim; num++) {
                res+=processVR(arr,index+1,aim-arr[index]*num);
            }
        }
        return res;
    }


    //记忆化搜索优化存储用的缓存池
    public static HashMap<String,Integer> map =new HashMap<>();
    /**
     * 在暴力递归方法的基础上优化版本-记忆化搜索
     * 在暴力递归方法中 当index确定aim确定递归表达式的返回结果相同 不同路径时会造成重复计算，又因为此递归
     * 过程是无后效性的 所以将index和aim的计算结果全局保存下来 当其他路径求此状态的结果 不用计算直接返回,
     * 相当于有一个缓存池存储之前算过的记录。
     *
     * @param arr
     * @param index
     * @param aim
     * @return
     */
    private static int processMap(int[] arr, int index, int aim) {
        int res =0;
        if(index==arr.length){
            //当index递归到数组边界外时 此时如果前面的数已经组合到aim了此种方法为有效方法返回1 否则返回0
            res=aim==0?1:0;
        }else{
            //当前index位置的钱从0张到最大张数时 后面的数能构成aim的不同方法数
            for (int num = 0; arr[index]*num <=aim; num++) {

                //用index_aim作为key 结果作为value
                String key = String.valueOf(index+1)+"_"+String.valueOf(aim-arr[index]*num);
                //每次计算之前先查一下是否存过对应的记录 可大幅度减少重复计算
                if(map.containsKey(key)){
                    res+=map.get(key);
                }else{
                    res+=processMap(arr,index+1,aim-arr[index]*num);
                }
            }
        }
        //每次计算的结果都存入到map中
        map.put(String.valueOf(index)+"_"+String.valueOf(aim),res);
        return res;
    }


    public static void test(){
        int[] arr=new int[]{5,10,25,1};
        int aim=15;
        System.out.println(transMoneyVR(arr,aim));
        System.out.println(transMoneyMS(arr,aim));
        System.out.println(transMoneyDP(arr,aim));
        System.out.println(transMoneyDP2(arr,aim));
    }


}
