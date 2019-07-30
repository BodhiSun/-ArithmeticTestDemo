package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/26 11:55
 * desc :暴力递归 汉诺塔问题 总步数为2^n - 1
 *
 * 【题目】假如有1,2,3一个三层汉诺塔和两个空塔 将三层汉诺塔上的数全部导入到最右边的空塔上 要求倒的过程中
 * 只能小压大不能大压小，并且每次只能移动每个塔最上面的数不能一次移动多个数 求打印一个n层汉诺塔 并还是只有
 * 两个空塔 打印把全部的数从最左边移动到最右边的全部过程
 *
 * 思路:
 * 1.要把1~n从from塔移到to塔上 先把1~n-1从from移动到help塔上
 * 2.在把n从from移动到to上
 * 3.把1~n-1从help移动到to上
 *
 * 时间复杂度为O(2^n) 因为总步数为2^n - 1
 *
 */
public class ViolenceRecursive_Hanoi {


    /**
     * 把from上1~n移动到to上
     * @param N 开始是1~n的问题
     * @param from 数据起始都在from
     * @param to 要移动到to上
     * @param help 中间可借助的help
     */
    public static void process(int N,String from,String to,String help){
        //如果为1层 直接从from 移动到 to
        if(N==1){
            System.out.println("move 1 from "+from+" to "+to);
        }else{
            //将n-1从from 移动到help 借助to
            process(N-1,from,help,to);
            //在把n从from移动到to上
            System.out.println("move "+N+" from "+from+" to "+to);
            //把n-1从help移动到to上 借助from
            process(N-1,help,to,from);
        }
    }

    //==========================================================================
    //或把步骤拆分开实现

    /**
     * 初始入口
     * @param N
     *
     * 把1~N从左移动到右
     * 拆分开:
     * 先把1~ N-1从左移到中
     * 再把N从左移到右
     * 最后再把1~ N-1从中移到右
     *
     */
    public static void moveLeftToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to right");
        }

        moveLeftToMid(N-1);
        System.out.println("move " + N + "from left to right");
        moveMidToRight(N-1);
    }

    /**
     * @param N
     *
     *把1~N从左移动到中
     */
    public static void moveLeftToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from left to mid");
        }

        moveLeftToRight(N-1);
        System.out.println("move " + N + "from left to mid");
        moveRightToMid(N-1);
    }

    /**
     * @param N
     *
     *把1~N从右移动到中
     */
    private static void moveRightToMid(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to mid");
        }

        moveRightToLeft(N-1);
        System.out.println("move " + N + "from right to mid");
        moveLeftToMid(N-1);
    }

    /**
     * @param N
     *
     *把1~N从右移动到左
     */
    private static void moveRightToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from right to left");
        }

        moveRightToMid(N-1);
        System.out.println("move " + N + "from right to left");
        moveMidToLeft(N-1);

    }

    /**
     * @param N
     *
     *把1~N从中移动到左
     */
    private static void moveMidToLeft(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to left");
        }

        moveMidToRight(N-1);
        System.out.println("move " + N + "from mid to left");
        moveRightToLeft(N-1);

    }

    /**
     * @param N
     *
     *把1~N从左移动到中
     */
    public static void moveMidToRight(int N) {
        if (N == 1) {
            System.out.println("move 1 from mid to right");
        }
        moveMidToLeft(N-1);
        System.out.println("move " + N + "from mid to right");
        moveLeftToRight(N-1);
    }


    public static void test(){
        int n = 3;
        process(n,"zuo","you","zhong");
        System.out.println("=================");

//        moveLeftToRight(n);  //有问题待进一步调试
    }

}
