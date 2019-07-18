package com.bodhi.arithmetictestdemo.practice;

import android.util.Log;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/18 10:56
 * desc :转圈打印矩阵
 * 思路：
 * 1.找到左上角的点和右下角的点 然后打印两点构成的矩形四边
 * 2.左上角向内侧移动一个点 右下角也向内侧移动一点 重复1操作
 * 3.当左下角的行或列有一个大于右下角时停止打印
 */
public class MatrixPractice_RotatePrint {

    public static void rotatePrint(int[][] matrix){
        //初始化两点
        int tR=0;//左上角的行
        int tC=0;//左上角的列
        int dR=matrix.length-1;//右下角的行
        int dC=matrix[0].length-1;//右下角的列

        //判断是否满足条件
        while (tR<=dR&&tC<=dC){
            //打印两点构成的矩形，打印完移动两点位置
            printEdge(matrix,tR++,tC++,dR--,dC--);
        }
        System.out.println();

    }

    /**
     * 打印矩阵上两点构成的矩形上所有的点
     * @param matrix 待打印矩阵
     * @param tR
     * @param tC
     * @param dR
     * @param dC
     * 考虑两种边界情况 在同一行或者在同一列
     */
    private static void printEdge(int[][] matrix, int tR, int tC, int dR, int dC) {

        if(tR==dR){//两点在同一行
            for (int i=tC;i<=dC;i++){
               System.out.print(matrix[tR][i]+" ");
            }
        }else if(tC==dC){//两点在同一列
            for(int i =tR;i<=dR;i++){
               System.out.print(matrix[i][tC]+" ");
            }
        }else{
            int curR=tR;
            int curC=tC;
            //打印上边行(不包含最后一个数)
            while (curC!=dC){
               System.out.print(matrix[tR][curC]+" ");
                curC++;
            }

            //打印右边列(不包含最后一个数)
            while (curR!=dR){
               System.out.print(matrix[curR][dC]+" ");
                curR++;
            }

            //打印下边行(不包含最后一个数)
            while (curC!=tC){
               System.out.print(matrix[dR][curC]+" ");
                curC--;
            }

            //打印左边列(不包含最后一个数)
            while (curR!=tR){
               System.out.print(matrix[curR][tC]+" ");
                curR--;
            }

        }
    }
}
