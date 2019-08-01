package com.bodhi.arithmetictestdemo.practice;

import java.util.Arrays;
import java.util.Collections;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/31 17:17
 * desc :相临两数最大差值-桶排序应用
 *
 * 【题目】给定一个数组arr，求如果排序之后，相邻两数的最大差值，要求时间复杂度O(N)，且要求不能用非基于比较的排序。
 *
 * 思路：
 * 1.arr一共有N个数 那准备N+1个桶
 * 2.找到数组中的最小值和最大值 最小值放到0号桶，最大值放到N号桶 将最小最大值范围等分N+1份(向下取整)放入对应桶
 * 3.最小值==最大值 即整个数组只有一个数 那最大差值为0
 * 4.一共N个数 准备N+1个桶，左边为最小值 右边为最大值 中间必存在一个空桶 所以相邻两数最大差值一定不再同一个桶
 * 5.所以求最大差值时不用考虑同一个桶内数的差值 多出一个非空桶是为了排除最大差值来自同一个桶内的可能性
 * 6.所以每个桶内只需记录 桶内最大值和最小值 还有该桶是否存过数 三个变量即可
 * 7.最终最大差值是来自 每个当前非空桶的最小值减去它前一个非空桶中的最大值 然后依次计算更新最大差值
 *
 */
public class Practice_MaxGap {

    public static int maxGap(int[] nums) {
        if (nums == null || nums.length < 2) {
            return 0;
        }

        //找出整个数组中的最小值和最大值
        int len = nums.length;
        int min=Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < len; i++) {
            min =Math.min(min,nums[i]);
            max=Math.max(max,nums[i]);
        }

        if(min==max){
            return 0;
        }

        //划分成N+1个桶 并把桶内的最大值最小值填充上
        boolean[] hasNum=new boolean[len+1];
        int[] maxs=new int[len+1];
        int[] mins =new int[len+1];
        //确定当前数属于几号桶
        int bid=0;
        for (int i = 0; i < len; i++) {
            bid=bucket(nums[i],len,min,max);
            mins[bid]=hasNum[bid]?Math.min(mins[bid],nums[i]):nums[i];
            maxs[bid]=hasNum[bid]?Math.max(maxs[bid],nums[i]):nums[i];
            hasNum[bid]=true;
        }

        //根据每个桶的最大最小值 找出整个数组的最大差值
        int res=0;
        int lastMax=maxs[0];
        int i=1;
        for(;i<=len;i++){
            if(hasNum[i]){
                res=Math.max(res,mins[i]-lastMax);
                lastMax=maxs[i];
            }
        }
        return res;

    }

    /**
     * 确定当前数属于第几号桶
     * @param num 当前数
     * @param len 原数组长度
     * @param min 原数组最小值
     * @param max 原数组最大值
     * @return 返回属于地几号桶
     */
    public static int bucket(long num,long len,long min,long max){
        return (int)((num-min)*len/(max-min));
    }

    public static void test(){
        int testTimes=10000;
        int maxSize=20;
        int maxValue=100;
        boolean succeed=true;
        for (int i = 0; i < testTimes; i++) {
            int[] arr1 =generateRandomArray(maxSize,maxValue);
            int[] arr2=Arrays.copyOf(arr1,arr1.length);
            int[] arr3=Arrays.copyOf(arr1,arr1.length);
            if(maxGap(arr1)!=absoluteRight(arr2)){
                succeed=false;
                printArray(arr3);
                System.out.println(maxGap(arr1));
                System.out.println(absoluteRight(arr2));
                break;
            }
        }
        System.out.println(succeed ? "Nice!" : "Fucking fucked!");
    }

    private static void printArray(int[] arr3) {
        if(arr3==null){
            return ;
        }

        for (int i = 0; i < arr3.length; i++) {
            System.out.print(arr3[i] + " ");
        }
        System.out.println();
    }


    public static int absoluteRight(int[] nums){
        if(nums==null||nums.length<2){
            return 0;
        }

        Arrays.sort(nums);
        int gap = Integer.MIN_VALUE;
        for(int i=1;i<nums.length;i++){
            gap=Math.max(nums[i]-nums[i-1],gap);
        }
        return gap;
    }

    public static int[] generateRandomArray(int maxSize,int maxValue){
        int[] arr=new int[(int)((maxSize+1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            arr[i]=(int)((maxValue+1)*Math.random())- (int) (maxValue * Math.random());
        }
        return arr;
    }



}
