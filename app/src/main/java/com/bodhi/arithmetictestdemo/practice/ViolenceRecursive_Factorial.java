package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/26 10:43
 * desc :暴力递归 n!求n的阶乘 即1*2*3*...*n
 *
 * 暴力递归：
 * 1，把问题转化为规模缩小了的同类问题的子问题
 * 2，有明确的不需要继续进行递归的条件(base case)
 * 3，有当得到了子问题的结果之后的决策过程
 * 4，不记录每一个子问题的解
 *
 * 应用场景：某个问题不知道确切的解法不知道怎么做 但是知道怎么试
 *
 *
 */
public class ViolenceRecursive_Factorial {


    /**
     *这种解法就是NP类问题思想
     * 求n的阶乘 可以转化为求(n-1)的阶乘*n (n-1)的阶乘可以转化为求(n-2)的阶乘*(n-1)
     * 当逐步转化一直到求1的阶乘时直接返回1 不需要递归下去了
     *
     * @param n
     * @return
     */
    public static long getFactorial(int n){
        if(n==1){
            return 1l;
        }

        return (long)n*getFactorial(n-1);
    }


    /**
     * 这种解法就是P类问题思想 我知道怎么算 程序按这种算法算就可以
     * @param n
     * @return
     */
    public static long getFactorial2(int n) {
        long result = 1L;
        for (int i = 1; i <= n; i++) {
            result *= i;
        }
        return result;
    }


    public static void test() {
        int n=5;
        System.out.println(getFactorial(n));
        System.out.println(getFactorial2(n));
    }

}
