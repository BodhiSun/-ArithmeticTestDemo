package com.bodhi.arithmetictestdemo;

import android.util.Log;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/12 15:34
 * desc :
 * <p>
 * 递归行为和递归行为时间复杂度 master公式的使用
 * T(N) = a*T(N/b) + O(N^d)
 * 1) log(b,a) > d -> 复杂度为O(N^log(b,a)
 * 2) log(b,a) = d -> 复杂度为O(N^d * logN
 * 3) log(b,a) < d -> 复杂度为O(N^d)
 */
public class SortUtil {


    /**
     * 冒泡排序
     * 时间复杂度O(N^2)，额外空间复杂度O(1)
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }

    /**
     * 选择排序
     * 时间复杂度O(N^2)，额外空间复杂度O(1)
     *
     * @param arr 待排序数组
     */
    public static void selectSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }

    }

    /**
     * 插入排序
     * 时间复杂度O(N^2)，额外空间复杂度O(1)
     *
     * @param arr 待排序数组
     */
    public static void insertionSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        int num1 = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                num1++;
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }

//        for (int i = 1; i < arr.length; i++) {
//            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
//                num1++;
//                swap(arr, j, j + 1);
//            }
//        }
        Log.e("num", "num1:" + num1);

    }


    /**
     * 归并排序
     * 时间复杂度O(N*logN)，额外空间复杂度O(N)
     *
     * @param arr 待排序数组
     */
    public static void mergeSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序
     *
     * @param arr 待排序数组
     * @param l   数组左边起始位置
     * @param r   数组右边终止位置
     */
    public static void mergeSort(Integer[] arr, int l, int r) {
        //数组中只有1个数
        if (l == r) {
            return;
        }

        //这样防止求中间位置时越界 >>1右移一位相当于除2
        int mid = l + ((r - l) >> 1);
        mergeSort(arr, l, mid);
        mergeSort(arr, mid + 1, r);
        mergeSort(arr, l, mid, r);
    }

    /**
     * 归并排序
     * 最终的左右两边子序列合在一起
     *
     * @param arr 待排序数组
     * @param l   数组左边起始位置
     * @param m   数组中间位置
     * @param r   数组右边终止位置
     *
     *  时间复杂度套进master公式的表示为：
     *   T(N)=2*T(N/2)+O(N^1)
     *   即a=2,b=2,d=1 满足log(b,a)=d
     *   所以归并排序时间复杂度为:0(N*LogN)
     */
    public static void mergeSort(Integer[] arr, int l, int m, int r) {
        //辅助数组
        Integer[] help = new Integer[r - l + 1];
        //辅助数组角标起始位
        int i = 0;
        //左子序指针起始位
        int p1 = l;
        //右子序指针起始位
        int p2 = m + 1;

        //左右子序的指针都没到末尾
        while (p1 <= m && p2 <= r) {
            if(arr[p1]<arr[p2]){
                //如果左子序小 就把左子序数据拿出来填充到help，同时左子序的指针移到到下一个
                help[i]=arr[p1];
                p1++;
            }else{
                help[i]=arr[p2];
                p2++;
            }
            i++;

            //简化写法
//            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        //右子序指针先到末尾
        while (p1 <= m) {
            help[i++]=arr[p1++];
        }
        //左子序指针先到末尾
        while (p2 <= r) {
            help[i++]=arr[p2++];
        }

        //将排序好的help数组 拷贝回待排序数组arr
        for (int j = 0; j < help.length; j++) {
            arr[l+j]=help[j];
        }
    }

    /**
     * 快速排序
     * 随机快排时间复杂度是长期期望值O(N*logN)，额外空间复杂度长期期望值O(logN) 空间浪费在记录划分点上了
     *
     * @param arr 待排序数组
     */
    public static void quickSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序
     *
     * @param arr 待排序数组
     * @param l   数组左边起始位置
     * @param r   数组右边终止位置
     */
    public static void quickSort(Integer[] arr, int l, int r) {
        //数组左边角标不超过右边时，过滤partition出现没有等于区域的无效位置
        if (l < r) {
            //随机取数组中一个数作为因子放置数组末尾,这样算法与原始数据状况无关。加上这行由经典快排变为随机快排序
            swap(arr,l+(int)(Math.random()*(r-l+1)),r);
            //partition分块过程
            Integer[] p =partition(arr,l,r);
            //L~等于区域前一个数位置，即小于区域继续递归排序
            quickSort(arr,l,p[0]-1);
            //等于区域后一个数位置~r，即大于区域继续递归排序
            quickSort(arr,p[1]+1,r);
        }
    }

    /**
     * 注:经典快排是找到小于等于区域和大于区域，然后小于等于区域最后一个位置不动即找到的Num，两边区域的数继续递归，每次只排序一个数
     *
     * 经典快速排序(改进后)partition过程是默认以最后一位(Num)做划分 小于区域放左边，大于区域放右边，返回中间等于区域
     * partition过程：当前待比较的数curr，小于Num区域下一个数less+1位置的数，给定的数Num
     * @param arr
     * @param l
     * @param r
     * @return
     */
    public static Integer[] partition(Integer[] arr, int l, int r) {
        int less =l-1;//默认0~less已经放的是小于Num的数了，所以初始小于等于Num的区域还不存在，即l的前一个位置为起始位
        int more =r;//默认more~r已经放的是大于Num的数了，所以初始大于Num的区域还不存在，即r的位置为起始位
        while (l<more){
            //当前数小于Num，当前数和小于区域下一个数交换，小于区域+1，当前数+1
            if(arr[l]<arr[r]){
                swap(arr,++less,l++);
            }else if(arr[l]>arr[r]){
                //当前数大于Num，当前数和大于区域前一个数交换，大于区域-1，当前数不变继续比较
                swap(arr,--more,l);
            }else{
                //相等
                l++;
            }
        }
        //初始more包含了Num值，没参与比对，所以排序后交换到正确位置
        swap(arr, more, r);
        return new Integer[] { less + 1, more };
    }






    //不完全准确
    public static void swap2(Integer[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
