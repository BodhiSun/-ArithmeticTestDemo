package com.bodhi.seniorcourse;

import java.util.Arrays;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/9 16:48
 * desc :BFPRT算法-在一个无序数组中找到第K小的数或第K大的数
 * 时间复杂度O(n)
 * <p>
 * 思路一：先排序然后直接取 O(N*logN)
 * <p>
 * 思路二：荷兰国旗问题的改进法 随机取一个数划分三个区域 patition过程中判断等于区域是否命中待求值 命中直接返回 否则在
 * 左边或者右边在进行partition过程依次递归下去 时间复杂度长期期望(概率期望)是O(n)
 * <p>
 * 思路三：BFPRT算法：
 * 第一步：将整个数组划分成每5个一组 一共 n/5组 O(1)
 * 第二步: 将每个组内单独排序 组与组之间不管 O(n)
 * 第三步：求每个组的中位数 然后组成一个长度为n/5的新数组 O(n)
 * 第四步：将中位数组成的新数组递归调用上述步骤 求出中位数组中的中位数 T(n/5)
 * 第五步：将原数组按照求解出来的中位数值划分 小于 等于 大于 三个区域 O(n)
 * 第六步: 如果等于区域没命中 继续将左边或者将右边(最多(7/10)n个数)继续划分 T(7/10)n)
 * 整个事件复杂度T(N)=T(N/5)+T((7/10)N)+O(N) =O(N)(不符合master公式但是数学已经证明了收敛于O(N),按3,5,7个数分组都可以)
 */
public class BFPRT_Arithmetic {


    /**
     * BFPRT算法-返回数组中第K小的数
     *
     * @param arr 原始数组
     * @param k 第K个小的数
     * @return
     */
    public static int getMinKthByBFPRT(int[] arr, int k) {
        int[] copyArr = Arrays.copyOf(arr, arr.length);
        return bfprt(copyArr, 0, copyArr.length - 1, k - 1);
    }

    /**
     * 返回一个数组在某一范围内的 第i位置小的数
     *
     * @param arr   给定数组
     * @param begin 起始位置
     * @param end   终止位置
     * @param i     第i位置的数
     * @return
     */
    private static int bfprt(int[] arr, int begin, int end, int i) {
        //递归到某一层 当范围内起始终止位置相等时 不在继续划分直接返回
        if (begin == end) {
            return arr[begin];
        }
        //求出中位数组中的中位数
        int pivot = medianOfMedians(arr, begin, end);
        //按求出的中位数对原数组进行划分 返回等于区域的小标
        int[] pivotRange =partition(arr,begin,end,pivot);
        //如果待求的位置刚好在等于区域 直接返回
        if(i>=pivotRange[0]&&i<=pivotRange[1]){
            return arr[i];
        }else if(i<pivotRange[0]){
            //如果在小于区域 递归左边小于区域继续求值
            return bfprt(arr,begin,pivotRange[0]-1,i);
        }else{
            return bfprt(arr,pivotRange[1]+1,end,i);
        }
    }

    /**
     * 快排改进版的partition过程 对给定数组的特定区域按给定数值划分小于 等于 大于区域 返回等于区域的下标
     * @param arr 给定数组
     * @param begin 划分区域起始位置
     * @param end 划分区域终止位置
     * @param pivotValue 划分数值
     * @return
     */
    private static int[] partition(int[] arr, int begin, int end, int pivotValue) {
        int small = begin-1;
        int cur = begin;
        int big =end+1;
        while (cur!=big){
            if(arr[cur]<pivotValue){
                //当前数和小于区域下一个位置交换 小于区域扩大 当前位置移动下一个
                swap(arr,++small,cur++);
            }else if (arr[cur]>pivotValue){
                //当前数和大于区域前一个位置交换 大于区域扩大
                swap(arr,cur,--big);
            }else{
                //当前位置移动
                cur++;
            }
        }
        int[] range=new int[2];
        range[0] = small+1;
        range[1]=big-1;
        return range;
    }

    /**
     * 将给定长度为n的数组均分成n/5组、组内排序、每组取出中位数组成新数组、递归求出中位数组中的中位数
     *
     * @param arr
     * @param begin
     * @param end
     * @return
     */
    private static int medianOfMedians(int[] arr, int begin, int end) {
        int num = end - begin + 1;
        //判断数组是否会刚好按5个划分完全 如有剩下的数就将剩下的数当做一组
        int offset = num % 5 == 0 ? 0 : 1;
        //生成对应组数数量大小的数组 用来存放每组内的中位数
        int[] mArr = new int[num / 5 + offset];
        for (int i = 0; i < mArr.length; i++) {
            //将原数组依次按0到4,5到9...每五个数分一组
            int beginI = begin + i * 5;
            int endI = beginI + 4;
            //当原数组不能按5整倍划分时 Math.min(end, endI)最后一组时会防止越界 求出每组中位数
            mArr[i] = getMedian(arr, beginI, Math.min(end, endI));
        }
        //递归调用bfprt求出中位数组中的中位数
        return bfprt(mArr,0,mArr.length-1,mArr.length/2);
    }

    /**
     * 将给定数组的特定范围内的数 先排序 后求出中位数返回
     * @param arr 给定数组
     * @param begin 待求范围的起始位
     * @param end 待求范围的终止位
     * @return
     */
    private static int getMedian(int[] arr, int begin, int end) {
        //对范围内的数进行排序
        insertionSort(arr,begin,end);
        int sum = end+begin;
        //奇数个取中间 偶数个取下中位数
        int mid = (sum/2)+(sum%2);
        return arr[mid];
    }

    /**
     * 通过插入排序的方式将给定数组特定范围内的数按从小到大排序
     * @param arr 给定数组
     * @param begin 待排序范围的起始位
     * @param end 待排序范围的终止位
     */
    private static void insertionSort(int[] arr, int begin, int end) {
        for(int i = begin+1;i!=end+1;i++){
            for(int j=i;j!=begin;j--){
                if(arr[j-1]>arr[j]){
                    swap(arr,j,j-1);
                }else{
                    //前面的已经比较过了 不用在重复比较
                    break;
                }
            }
        }
    }

    public static void swap(int[] arr, int index1, int index2){
        int tmp = arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=tmp;
    }

    public static void test(){
        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
        System.out.println(getMinKthByBFPRT(arr,4));
        System.out.println(getMinKthByBFPRT(arr,8));
        System.out.println(getMinKthByBFPRT(arr,16));
    }

}
