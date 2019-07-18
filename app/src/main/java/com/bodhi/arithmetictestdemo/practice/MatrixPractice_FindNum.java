package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/18 17:26
 * desc :在行列都排好序的矩阵中找数
 *
 * 【题目】 给定一个有N*M的整型矩阵matrix和一个整数K， matrix的每一行和每一 列都是排好序的。实现一个函数，判断K 是否在matrix中。
 * 【要求】 时间复杂度为O(N+M)，额外空间复杂度为O(1)。
 *
 * 思路：
 * 因为行列都排好序从右上点开始比较 比K大则向左移动比K小则向下移动
 */
public class MatrixPractice_FindNum {

    public static boolean isContains(int[][] matrix,int k){
        int row =0;
        int col = matrix[0].length-1;
        while (row<matrix.length&&col>-1){
            if(matrix[row][col]==k){
                return true;
            }else if(matrix[row][col]>k){
                col--;
            }else{//matrix[row][col]<k
                row++;
            }
        }

        return false;

    }


}


