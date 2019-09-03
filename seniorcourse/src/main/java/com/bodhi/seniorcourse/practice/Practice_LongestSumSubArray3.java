package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/9/3 20:25
 * desc :累加和小于等于最长子数组
 *
 * 【题目】
 * 给定一个数组arr，值可正，可负，可0；一个整数aim，求累加和小于等于aim的，最长子数组，要求时间复杂度O(N)
 *
 * 思路：
 * 准备两个和arr等长的数组，minSum代表以数组中当前字符开头最小的累加和，minSumIndex代表当前字符最小累加和
 * 的右边下标 这两个数组是伴生的。minSum可以从数组的从后到前求值，N-1位置最小累加和为自己 arrSumIndex
 * 对应的位置为N-1,N-2最小累加和看N-1位置是正还是负 如果是正则N-2位置最小累加和也是自己，如果是负 则N-2开
 * 头的最小累加和就是arr[N-2]+arr[N-1] 对应minSumIndex的值就是把N-1上对应的位置直接拷贝过来，依次类推一直
 * 把两个数组的数据填充到0位置。然后开始求解过程：首先将以0位置开始能累加的最小和从minSum中取出 如minSum[0]
 * 大于aim 则以0位置开头的都不符合 看1位置开头的最小和从minSum中取出如minSum[1]小于aim则直接把范围从1扩到
 * minSumIndex[1]假设是K位置，然后看k+1位置开头最小的和从minSum中取出 加上minSum[1]是否小于aim 小于继续把
 * 范围扩到minSumIndex[k+1]位置， 不小于则以1开头的最大小于等于aim的范围为1到k,(精髓)然后将1位置值去掉看2到k的
 * 值是否小于aim 不小于则在把2位置去掉从3位置看 小于的话看加上k+1位置的最小和和aim比较 小于的话范围从2扩到
 * minSumIndex[k+1]位置 然后继续看后面 大于的话左边界还是向左扩到3位置，以此类推 求出最大值，左右边界都只经过
 * 数组中字符一次 即左右边界都不回退 所以时间复杂O(n)
 *
 *
 */
public class Practice_LongestSumSubArray3 {

    public static int getMaxLength(int[] arr, int aim) {
        if (arr == null || arr.length == 0 || aim <= 0) {
            return 0;
        }

        //生成最小累加和和最小累加右边界两个数组 及初始化末位置
        int[] sums = new int[arr.length];
        int[] ends = new int[arr.length];
        sums[arr.length-1]=arr[arr.length-1];
        ends[arr.length-1]=arr.length-1;
        //从后往前推导出两个数组中的值
        for(int i=arr.length-2;i>=0;i--){
            //如果后面的数是负数 则加上后面的数
            if(sums[i+1]<0){
                sums[i]=arr[i]+sums[i+1];
                ends[i] =ends[i+1];
            }else{
                //正数则为自己
                sums[i] =arr[i];
                ends[i]=i;
            }
        }

        //下面开始求解过程
        int len = 0;//全局更新的小于等于的最长大小
        int sum=0;//当前窗口扩出来的和
        int R = 0;//当前窗口右边边界(不包含R)
        for(int start=0;start<arr.length;start++){
            //当前扩出来的范围下一个位置R扩出来的仍然符合
            while (R <arr.length&& sum+sums[R]<=aim){
                sum+=sums[R];
                R=ends[R]+1;
            }

            //边界R不符合条件则左边向前扩 但是要考虑窗口范围不满足条件的情况 即窗口start=R没范围的情况
            sum-=R>start?arr[start]:0;
            len=Math.max(len,R-start);
            //窗口有范围的情况下 R=R,窗口没范围的情况下 R和start一起来到下一个位置
            R=Math.max(R,start+1);
        }
        return len;
    }

    public static void test(){
        int[] arr = new int[]{7,3,-1,1,1,1,7,7};
        System.out.println(getMaxLength(arr,3));
    }


}
