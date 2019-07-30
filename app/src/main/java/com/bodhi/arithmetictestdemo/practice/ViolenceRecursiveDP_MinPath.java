package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/26 21:06
 * desc :暴力递归到动态规划(空间换时间) 求二维数组最小路径
 *
 * 【题目】给你一个二维数组，二维数组中的每个数都是正数，要求从左上 角走到右下角，每一步只能向右或者向下。
 * 沿途经过的数字要累 加起来。返回最小的路径和。
 *
 *  当暴力递归过程中有重复状态 并且重复状态与结果无关，即无后效性问题 暴力递归可改成动态规划
 *  动态规划步骤：
 *  1.写出暴力递归的尝试版本
 *  2.把可变参数找到，分析可变参数 可变参数的变化范围代表返回值的变化范围 可变参数是几维就构建几维结果表
 *  3.找到终止位置在表上设置上 找到base-case中 完全不依赖其他位置的值设置好
 *  4.一个普遍位置需要哪些位置 逆序回去就是填表的顺序
 *
 * 
 *
 */
public class ViolenceRecursiveDP_MinPath {

    /**
     * 暴力递归-尝试思路版本
     * 时间复杂度O()
     *
     * 从(i，j)出发 到达最右下角位置 最短路径和
     * @param matrix 二维数组
     * @param i 行
     * @param j 列
     * @return
     */
    public static int walk(int[][]matrix,int i,int j){

        //如果i,j已经是最右下角的点 和就只是右下角
        if(i ==matrix.length-1&& j==matrix[0].length-1){
            return matrix[i][j];
        }

        //i,j已经到最下面一行
        if(i==matrix.length-1){
            return matrix[i][j]+walk(matrix,i,j+1);
        }

        //i,j已经到最后面一列
        if(j==matrix[0].length-1){
            return matrix[i][j]+walk(matrix,i+1,j);
        }

        //即可以往下走 也可以往右走的情况
        int right=walk(matrix,i,j+1);//右边点到最右下角路径的和
        int bottom=walk(matrix,i+1,j);//下边点到最右下角路径的和

        return matrix[i][j]+Math.min(right,bottom);
    }

    /**
     * 根据递归版改出的动态规划版
     * @param m 给定数组
     * @return
     */
    public static int walkDP(int[][] m) {
        if(m==null||m.length==0||m[0]==null||m[0].length==0){
            return 0;
        }

        int row =m.length;
        int col=m[0].length;
        int[][] dp =new int[row][col];
        dp[0][0]=m[0][0];

        for(int i=1;i<row;i++){
            dp[i][0]=dp[i-1][0]+m[i][0];
        }
        for(int j=1;j<col;j++){
            dp[0][j]=dp[0][j-1]+m[0][j];
        }
        for(int i=1;i<row;i++){
            for(int j=1;j<col;j++){
                dp[i][j]=Math.min(dp[i-1][j],dp[i][j-1])+m[i][j];
            }
        }

        return dp[row-1][col-1];
    }



    public static void test(){
        int[][] m = { { 1, 3, 5, 9 }, { 8, 1, 3, 4 }, { 5, 0, 6, 1 }, { 8, 8, 4, 0 } };
//        int[][] m = { { 1,0, 6, 5 },{ 2,1, 2, 3 }, { 3,4,0, 1 }, { 4, 0, 6, 2 }};
        System.out.println(walk(m,0,0));;
        System.out.println(walkDP(m));;
    }

}
