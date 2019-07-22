package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/22 16:51
 * desc :折纸问题
 *
 * 【题目】给定一 个输入参数N，代表纸条都从下边向上方连续对折N次， 请从上到下打印所有折痕的方向。
 * 例如：N=1时，打印： down N=2时，打印： down down up
 *
 */
public class Practice_PaperFolding {

    /**
     * 打印折痕
     */
    public static void printAllFolds(int N){
        printProcess(1,N,true);
    }

    private static void printProcess(int i, int N, boolean down) {
        if(i>N){
            return;
        }
        printProcess(i+1,N,true);
        System.out.println(down?"down":"up");
        printProcess(i+1,N,false);
    }

}
