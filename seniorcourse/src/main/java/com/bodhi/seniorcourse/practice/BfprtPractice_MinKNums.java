package com.bodhi.seniorcourse.practice;

import static com.bodhi.seniorcourse.BFPRT_Arithmetic.getMinKthByBFPRT;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/21 11:50
 * desc :在一个无序数组中找出前K小的数
 *
 * 思路一：用堆实现
 * 时间复杂度：O(N*logK)
 *
 * 思路二：用BFPRT算法实现 找到第K小的数 然后在原数组比较找出小于等于K的数(注意边界)
 * 时间复杂度：O(N)
 *
 */
public class BfprtPractice_MinKNums {

    /**
     * 用堆的方式实现 取出数组前K小的数
     * @param arr
     * @param k
     * @return
     */
    public static int[] getMinKNumsByHeap(int[] arr,int k){
        if(k<1||k>arr.length){
            return arr;
        }
        int[] kHeap = new int[k];
        //将arr前K个数构建大根堆存入到kHeap数组
        for(int i =0;i!=k;i++){
            heapInsert(kHeap,arr[i],i);
        }

        for (int i=k;i!=arr.length;i++){
            if(arr[i]<kHeap[0]){
                kHeap[0]=arr[i];
                heapify(kHeap,0,k);
            }
        }
        return kHeap;
    }

    /**
     * heapify 当大根堆结构的数组index结点变小时 下沉逻辑
     * @param arr 大根堆结构的数组
     * @param index 发生变化的节点位置
     * @param heapSize 数组的大小
     */
    private static void heapify(int[] arr, int index, int heapSize) {
        int left = index*2+1;
        int right =index*2+2;
        int largest = index;
        //不越界的情况下 将index位置的值下沉
        while (left<heapSize){
            //在不越界的情况下找到 当前变更的数和左右两个孩子中的最大值
            if(arr[left]>arr[index]){
                largest = left;
            }
            if(right<heapSize&&arr[right]>arr[largest]){
                largest=right;
            }
            //如果最大的数不是当前变更进来的数 下沉一层 并更新index，left，right位置继续向下下沉
            if(largest!=index){
                swap(arr,largest,index);
            }else{
                break;
            }
            index=largest;
            left=largest*2+1;
            right=largest*2+2;
        }
    }

    /**
     * heapInsert 构建大根堆
     * @param arr
     * @param value
     * @param index
     */
    private static void heapInsert(int[] arr, int value, int index) {
        arr[index] =value;
        while (index!=0){
            int parent = (index-1)/2;
            if(arr[parent]<arr[index]){
                swap(arr,parent,index);
                index=parent;
            }else{
                break;
            }
        }
    }

    /**
     * 用BFPRT算法实现 取出数组前K小的数
     * @param arr 原数组
     * @param k 前K小的数
     * @return
     */
    public static int[] getMinKNumsByBFPRT(int[] arr,int k){
        if(k<1||k>arr.length){
            return arr;
        }
        //找到原数组中第K个小的数
        int minKth =getMinKthByBFPRT(arr,k);

        int[] res = new int[k];
        int index =0;
        //遍历原始数组 先把所有比第K个小的数小的填充到数组
        for(int i=0;i!=arr.length;i++){
            if(arr[i]<minKth){
                res[index++]=arr[i];
            }
        }
        //然后 将剩下的空位用minKth填充
        for(;index!=res.length;index++){
            res[index]=minKth;
        }
        return res;
    }
    
    public static void swap(int[] arr,int index1,int index2){
        int temp = arr[index1];
        arr[index1]=arr[index2];
        arr[index2]=temp;
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i != arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void test(){
        int[] arr = { 6, 9, 1, 3, 1, 2, 2, 5, 6, 1, 3, 5, 9, 7, 2, 5, 6, 1, 9 };
        // sorted : { 1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 5, 5, 6, 6, 6, 7, 9, 9, 9 }
        printArray(getMinKNumsByHeap(arr, 10));
        printArray(getMinKNumsByBFPRT(arr, 10));
    }

}
