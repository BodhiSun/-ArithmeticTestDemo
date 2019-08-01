package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/30 20:40
 * desc :找到数组中的最大值-递归方法 分治思想
 *
 * 1.递归即是分治的思想
 * 2.递归必须有终止条件
 * 3.递归-即函数调用自身 就是系统帮助压栈
 * 4.任何递归行为都可以改成非递归
 * 5.递归太深容易造成堆栈的溢出.
 * 系统压栈：就是把当前函数执行到第几行，各个参数值等所有信息保存归档 然后执行子过程 当执行到终止条件后
 * 获得返回值 然后把栈顶保存信息的快照拿出来 还原函数执行过程 继续执行函数下一步。
 *
 * 递归行为时间复杂度通式 master公式的使用
 * T(N) = a*T(N/b) + O(N^d)
 * T(n):样本量  T(N/b):样本分割成子过程大小  a:子过程调用的次数  O(N^d)除去调用子过程之外的代价
 * 1) log(b,a) > d -> 复杂度为O(N^log(b,a))
 * 2) log(b,a) = d -> 复杂度为O(N^d * logN)
 * 3) log(b,a) < d -> 复杂度为O(N^d)
 *
 * master公式适用范围：划分的子过程规模必须是一样的
 *
 *
 */
public class Practice_FindArrayMaxNum {

    /**
     * 时间复杂度：T(N)=2T(N/2)+O(1); a=2 b=2 d=0 符合master公式第一种情况 时间复杂度：O(N)
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static int findMaxNum(int[]arr,int l,int r){
        //当划分到只有一个数的时候 直接返回
        if(l==r){
            return arr[l];
        }

        int mid=(l+r)/2;
        //分别找到数组左半部分和右半部分的最大值，然后返回最大的那个
        int maxLeft =findMaxNum(arr,l,mid);
        int maxRight =findMaxNum(arr,mid+1,r);
        return Math.max(maxLeft,maxRight);

    }

    public static void test(){
        int[] arr=new int[]{4,2,1,5};
        int maxNum = findMaxNum(arr, 0, arr.length - 1);
        System.out.println(maxNum);
    }
}
