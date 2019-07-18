package com.bodhi.arithmetictestdemo.practice;

import java.util.WeakHashMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/18 16:03
 * desc :“之”字形打印矩阵
 *
 * 【题目】 给定一个矩阵matrix，按照“之”个矩阵，例如： 1 2 3 4 5 6 7 。。3*4“之”字形打印的结果为：1，2，5，9，6，3，4。。
 * 【要求】 额外空间复杂度为O(1)。
 *
 * 思路：
 * 1.A,B在左上起始(0,0)点 A向右移动到最右边后向下移动 B向下移动到最下边后向右移动 最后A,B重合在右下终止点
 * 2.A,B在每同时移动一点后 形成一条对角线
 * 3.打印A,B形成的对象 按从左下到右上 或是从右上到左下的方向依次打印
 */
public class MatrixPractice_ZigZagPrintMatrix {

    public static void printMatrixZigZag(int[][] matrix){

        int tR=0;//A的行
        int tC=0;//A的列
        int dR=0;//B的行
        int dC=0;//B的列
        int endR=matrix.length-1;//总行数下边界
        int endC=matrix[0].length-1;//总列数右边界
        boolean fromUp =false;//打印的方向 是否从上到下

        //A已经向右走到边并且向下移动到最后一行
        while (tR != endR + 1) {
            //给定A,B两点和打印方向 打印一条对角线
            printLevel(matrix, tR, tC, dR, dC, fromUp);

            //A的行和列变化
            tR = tC == endC ? tR + 1 : tR;
            tC = tC == endC ? tC : tC + 1;
            //B的行和列变化
            dC = dR == endR ? dC + 1 : dC;
            dR = dR == endR ? dR : dR + 1;
            fromUp=!fromUp;
        }
        System.out.println();
    }

    /**
     *打印对角线
     * @param m
     * @param tR
     * @param tC
     * @param dR
     * @param dC
     * @param fromUp
     */
    private static void printLevel(int[][] m, int tR, int tC, int dR, int dC, boolean fromUp) {
        if (fromUp) {
            while (tR!=dR+1){
                //从右上向左下打印
                System.out.print(m[tR++][tC--] + " ");
            }
        }else{
            while (dR!=tR-1){
                //从左下向右上打印
                System.out.print(m[dR--][dC++]+" ");
            }
        }
    }


}
