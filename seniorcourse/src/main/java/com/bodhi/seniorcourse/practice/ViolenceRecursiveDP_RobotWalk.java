package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/9/2 19:05
 * desc :暴力递归到动态规划-机器人多少个路径
 *
 * 【题目】
 * 有一个长度为N的路经1~N，有一个机器人假设在M位置，如果他可以走P步(只要不越边界，左右随便走) 请问机器人
 * 走P步后他停在K位置上的走法有多少种。
 *
 * VR->DP思路：
 * 1.waysVR中可变参数只有 M,P M横向P纵向 所以构建二维表
 * 2.basecase中当p=0时，只有M=K为1 其他都为0
 * 3.普遍位置的依赖是 M+1 P-1和M-1,P-1 即依赖左上角和右上角的两个点 杨辉三角形
 *
 */
public class ViolenceRecursiveDP_RobotWalk {

    /**
     * 暴力递归的尝试方法
     * @param N 路径长度1-N
     * @param M 来到的位置
     * @param P 机器人可走的步数
     * @param K 机器人要停在的位置
     * @return 一共有多少种走法
     */
    public static int waysVR(int N,int M,int P,int K){
        if(N<2||M<1||M>N||P<0||K<1||K>N){
            return 0;
        }

        //当没有剩余步数时 看是否到达指定位置
        if(P==0){
            return M==K ? 1: 0;
        }
        int res=0;
        if(M==1){
            //当来到左边界 下一步只能往右走
            res=waysVR(N,M+1,P-1,K);
        }else if(M==N){
            //来到右边界 下一步只能往左走
            res=waysVR(N,M-1,P-1,K);
        }else{
            //在普遍位置时 既可以往左也可以往右
            res=waysVR(N,M+1,P-1,K)+waysVR(N,M-1,P-1,K);
        }
        return res;
    }


    /**
     * 暴力递归转动态规划
     * @param N 路径长度1-N
     * @param M 来到的位置
     * @param P 机器人可走的步数
     * @param K 机器人要停在的位置
     * @return 一共有多少种走法
     */
    public static int waysDP(int N,int M,int P,int K){
        int[][] dp  = new int[P+1][N+1];
        //basecask中的逻辑
        dp[0][K]=1;

        //普遍位置的逻辑
        for (int i =1;i<=P;i++){
            for(int j=1;j<=N;j++){
                //左边越界
                dp[i][j]+=j-1<1?0:dp[i-1][j-1];
                //右边越界
                dp[i][j]+=j+1>N?0:dp[i-1][j+1];
            }
        }
        return dp[P][M];
    }

    public static void test(){
        System.out.println(waysVR(5, 3, 0, 3));
        System.out.println(waysVR(5, 3, 2, 2));
        System.out.println(waysDP(5, 3, 0, 3));
        System.out.println(waysDP(5, 3, 2, 2));
    }

}
