package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/24 14:36
 * desc :岛问题
 *
 * 【题目】一个矩阵中只有0和1两种值，每个位置都可以和自己的上、下、左、右 四个位置相连，如果有一片1连在一起，
 * 这个部分叫做一个岛，独立一个1也算一个岛 求一个矩阵中有多少个岛？
 *
 * 第一种方法用两层for循环逐个遍历每个元素 然后通过一个感染函数将每个岛的每个元素找出并标记出来
 *
 * 第二种方法 DFS(深度优先遍历) 将整个数组分割成若干个小数组 计算每个小数组内岛的个数 然后处理不同数组
 * 合并后 边界对岛数量的增减 最后求出全部岛的数量。
 * 合并边界的处理采用并查集结构 处理减还是合并
 *
 */
public class MatrixPractice_Islands {

    public static int countIslands(int[][] m){
        if(m==null||m[0]==null){
            return 0;
        }

        int N=m.length;//行数
        int M=m[0].length;//列数
        int res=0;//岛的个数
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                //如果遍历到1则岛的数量增加 并且标记当前岛所有元素
                if(m[i][j]==1){
                    res++;
                    infect(m,i,j,N,M);
                }
            }
        }
        return res;
    }

    /**
     * 感染函数 标记当前岛所有元素为2
     * @param m
     * @param i
     * @param j
     * @param N
     * @param M
     */
    private static void infect(int[][] m, int i, int j, int N, int M) {
        //如果越界 或者元素不为1则结束标记过程
        if(i<0||i>=N||j<0||j>=M||m[i][j]!=1){
            return;
        }
        //将当前岛元素标记为2
        m[i][j]=2;

        //继续递归标记上下右左
        infect(m,i+1,j,N,M);
        infect(m,i-1,j,N,M);
        infect(m,i,j+1,N,M);
        infect(m,i,j-1,N,M);

    }

}
