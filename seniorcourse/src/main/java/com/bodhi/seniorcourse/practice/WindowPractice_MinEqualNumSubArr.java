package com.bodhi.seniorcourse.practice;

import java.util.LinkedList;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/21 16:52
 * desc :最大值减去最小值小于或等于num的子数组数量
 * 【题目】给定数组arr和整数num，共返回有多少个子数组满足 子数组内的最大值-最小值<=num
 * 【要求】如果数组长度为N，请实现时间复杂度为O(N)的解法
 * 子数组：必须是连续的
 *
 * 思路1：暴力方法找到所有的子数组 然后遍历每个子数组是否满足条件
 *
 * 思路2：窗口的实现方式
 * 已知：
 * 结论一：当一个数组内的最大值减去最小值满足<=num 那么数组内的任意子数组一定都满足条件
 * 结论二：当一个数组内的最大值减去最小值已经>num 那么此数组在怎么扩大依旧不会满足条件
 * 逻辑：
 * 准备最大值和最小值两个双向队列作为窗口变更结构，起始start,end都在数组的0位置，end向右移动更新 最大值
 * 和最小值队列 同时计算当前范围是否满足条件 满足end继续向右移动 继续更新两个队列 继续计算条件 ，当end移动
 * 到某一位置时 此范围内的数组不再满足条件 则将从start位置到end前一个位置范围 以start开头的字串都记录下来
 * 然后start向右移动 更新两个队列的值 计算并判断是否满足条件 满足的话end向右移动否则sart向右移动 依次循环
 *
 *
 *
 *
 *
 */
public class WindowPractice_MinEqualNumSubArr {

    /**
     * 暴力方法求出arr数组中所有满足最大值减最小值小于等于num 条件的子数组个数
     * 时间复杂度：O(n^3)=找到所有子数组的复杂度*遍历每个子数组=O(n^2)*O(n)
     *
     * @param arr 原始数组
     * @param num 小于等于给定的值
     * @return
     */
    public static int getNum1(int[] arr, int num) {
        //标记满足条件的子数组个数
        int res = 0;
        //start到end角标位组成的便是arr中的所有子数组
        for (int start = 0; start < arr.length; start++) {
            for (int end = start; end < arr.length; end++) {
                //校验每个子数组是否满足条件
                if (isValid(arr, start, end, num)) {
                    res++;
                }
            }
        }

        return res;
    }

    /**
     * 判断当前数组中特定范围的子数组是否满足最大值减最小值小于等于num的条件
     *
     * @param arr
     * @param start
     * @param end
     * @return
     */
    private static boolean isValid(int[] arr, int start, int end, int num) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = start; i <= end; i++) {
            max = Math.max(max, arr[i]);
            min = Math.min(min, arr[i]);
        }
        return max - min <= num;
    }


    /**
     * 窗口方法的实现方式 求出arr数组中所有满足最大值减最小值小于等于num 条件的子数组个数
     * 时间复杂度：O(n)
     *
     * @param arr
     * @param num
     * @return
     */
    public static int getNum2(int[] arr,int num){
        if(arr==null||arr.length==0){
            return 0;
        }

        //准备窗口内最大值和最小值两个窗口更新结构
        LinkedList<Integer> qmax = new LinkedList<Integer>();
        LinkedList<Integer> qmin = new LinkedList<Integer>();
        int start=0;
        int end =0;
        //满足条件的子数组个数
        int res = 0;
        while (start<arr.length){

            //此while循环是当start确定的时候end向右扩 直到扩到不满足条件返回
            while (end<arr.length){
                //end向右移动时 最大值结构更新
                while (!qmax.isEmpty()&&arr[qmax.peekLast()]<=arr[end]){
                    qmax.pollLast();
                }
                qmax.addLast(end);
                //end向右移动时 最小值结构更新
                while (!qmin.isEmpty()&&arr[qmin.peekLast()]>=arr[end]){
                    qmin.pollLast();
                }
                qmin.addLast(end);

                //如果不满足条件
                if(arr[qmax.getFirst()]-arr[qmin.getFirst()]>num){
                    break;
                }
                end++;
            }

            //判断最小值和最大值的更新结构是否过期
            if(qmax.peekFirst()==start){
                qmax.pollFirst();
            }
            if(qmin.peekFirst()==start){
                qmin.pollFirst();
            }

            //一次性找到 end-start个满足条件的 以start位置开头的子数组
            res+=end-start;
            start++;
        }
        return res;
    }


    public static void test(){
        int[] arr = new int[]{4, 2, 5,9};
        System.out.println(getNum1(arr,2));
        System.out.println(getNum2(arr,2));
    }


}
