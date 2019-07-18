package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/18 10:56
 * desc :旋转正方形矩阵
 * 【题目】 给定一个整型正方形矩阵matrix,请把该矩阵调整成顺时针旋转90度的样子。
 * 【要求】 额外空间复杂度为O(1)。
 *
 * 思路：
 * 1.找到左上角的点和右下角的点 然后先转两点构成的正方形四边
 * 2.转的话找到相对应的点比如4*4的话 1对应4 4对应16 16对应13 13对应1一次交换，然后下一组2对应8重复下去
 * 3.左上角向内侧移动一个点 右下角也向内侧移动一点 重复1操作
 * 4.当左下角的行大于右下角时停止转
 */
public class MatrixPractice_RotateMatrix {

    public static void rotate(int[][] matrix){
        //初始化两点
        int tR=0;//左上角的行
        int tC=0;//左上角的列
        int dR=matrix.length-1;//右下角的行
        int dC=matrix[0].length-1;//右下角的列

        //判断是否满足条件
        while (tR<=dR){
            //旋转两点构成的正方形，旋转完移动两点位置
            rotateEdge(matrix,tR++,tC++,dR--,dC--);
        }
    }

    /**
     * 旋转正方形矩阵上两点构成的正方形边上所有的点
     * @param m 待旋转正方形矩阵
     * @param tR
     * @param tC
     * @param dR
     * @param dC
     */
    private static void rotateEdge(int[][] m, int tR, int tC, int dR, int dC) {
       int times=dC-tC;//第一圈每一行有多少列
        int tmp =0;
        for(int i=0;i!=times;i++){
            tmp =m[tR][tC+i];//对应的上边行 三个点触发点
            m[tR][tC+i]=m[dR-i][tC];//将左边列三个点 赋值给上边行对应的三个点
            m[dR-i][tC]=m[dR][dC-i];//将下边行三个点 赋值给左边列对应的三个点
            m[dR][dC-i]=m[tR+i][dC];//将右边列三个点 赋值给下边行对应的三个点
            m[tR+i][dC]=tmp;//将上边行三个点 赋值给右边列对应的三个点
        }
    }

    public static  void printMatrix(int[][] matrix){
        for (int i = 0; i != matrix.length; i++) {
            for (int j = 0; j != matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }


}
