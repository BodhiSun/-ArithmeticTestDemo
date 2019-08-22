package com.bodhi.seniorcourse.practice;

import static com.bodhi.seniorcourse.practice.SingleStackP_MaxRecAreaFromArr.maxRecFromArr;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/22 00:50
 * desc :单调栈结构-求二维数组最大子矩阵大小
 *
 * 【题目】
 * 给定一个整数矩阵map，其中的值只有0和1两种，求其中全是1的所有矩形区域中，最大的矩形区域1的数量
 * eg：1 1 1 0 其中最大的矩形区域为3个1，所以返回3
 *
 * 思路：参照直方图求最大矩形面积的解法 将矩阵的每一行上的数看做为底 然后分别求每一个行能构成的最大矩形面
 * 积 但是每一行上的数要进行换算 在同一列方向如果当前行的数为0则直接为0 如果当前行不为0 当前行的值为上一行
 * 的值加上当前行 ，然后将当前行构成的数组按照直方图求解的方法求出最大面积 然后比较每一行中能构成的最大面积
 * eg：1 0 1 1、1 1 1 1、1 1 1 0 转换后矩阵每一行的值为 1 0 1 1、 2 1 2 2、3 2 3 0
 *
 */
public class SingleStackP_MaxSubMatrixNum {

    public static int maxMatrixRecSize(int[][] map){
        if(map == null || map.length==0||map[0].length==0){
            return 0;
        }
        int maxArea =0;
        //记录矩阵每一行转换后的值
        int[] height = new int[map[0].length];
        //从上到下开始遍历每一个行
        for(int i =0;i<map.length;i++){
            //从左到右开始遍历每一行上的每一列上的值
            for(int j =0;j<map[0].length;j++){
                //同一列方向当前行的值为0 则值为0，否则为当前列方向上一行(即上次保存当前列)的值加当前行值(也就是1)
                height[j]  = map[i][j]==0?0:height[j]+1;
            }
            maxArea = Math.max(maxRecFromArr(height),maxArea);
        }

        return maxArea;
    }

    public static void test(){
        int[][] matrix = new int[3][4];
        matrix[0]=new int[]{1,0,1,1};
        matrix[1]=new int[]{1,1,1,1};
        matrix[2]=new int[]{1,1,1,0};
        System.out.println(maxMatrixRecSize(matrix));
    }

}
