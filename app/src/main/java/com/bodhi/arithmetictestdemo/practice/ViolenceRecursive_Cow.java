package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/26 21:01
 * desc : 暴力递归：母牛生小牛求牛的总数量
 *
 * 【题目】母牛每年生一只母牛，新出生的母牛成长三年后也能每年生一只 母牛，假设不会死。求N年后，母牛的数量。
 *
 * 进阶：如果每只母牛只能活10年，求N年后，母牛的数量。
 *
 * 思路：列出来每年的总牛数 得到规律：F(n)=F(n-1)+F(n-3) 即今年总牛=去年的总牛+新生的总牛
 *
 */
public class ViolenceRecursive_Cow {


    /**
     * 时间复杂度O(n)
     * @param n n年后牛的总数量
     * @return
     */
    public static int cowNumber1(int n){

        //考虑边界情况 递归的终止条件
        if(n<1){
            return 0;
        }

        //考虑边界情况 前三年直接返回
        if(n==1||n==2||n==3){
            return n;
        }

        return cowNumber1(n-1)+cowNumber1(n-3);
    }



    public static int cowNumber2(int n) {
        if (n < 1) {
            return 0;
        }
        if (n == 1 || n == 2 || n == 3) {
            return n;
        }
        int res = 3;
        int pre = 2;
        int prepre = 1;
        int tmp1 = 0;
        int tmp2 = 0;
        for (int i = 4; i <= n; i++) {
            tmp1 = res;
            tmp2 = pre;
            res = res + prepre;
            pre = tmp1;
            prepre = tmp2;
        }
        return res;
    }

    public static void test() {
        int n = 6;
        System.out.println(cowNumber1(n));
        System.out.println(cowNumber2(n));
    }
}
