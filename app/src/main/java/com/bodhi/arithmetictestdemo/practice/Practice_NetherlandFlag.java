package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/31 11:26
 * desc :荷兰国旗问题
 *
 * 【题目】给定一个数组arr，和一个数num，请把小于num的数放在数组的左边，等于num的数放在数组的中间，
 * 大于num的数放在数组的右边。
 *
 */
public class Practice_NetherlandFlag {

    /**
     * L到R上 按给定数划分 小于放左边 等于放中间 大于放右边
     * @param arr 给定数组
     * @param L
     * @param R
     * @param num 给定数
     * @return 返回等于区域的下标
     */
    public static int[] partition(int[]arr ,int L,int R,int num){
        int less =L-1;//小于区域
        int more =R+1;//大于区域
        int cur=L;//当前遍历的位置

        while (cur<more){

            if(arr[cur]<num){
                //当前位置小于给定数 将当前数和小于区域下一个数交换 小于区域向右扩1,当前位置+1；
                swap(arr,++less,cur++);
            }else if(arr[cur]>num){
                //当前位置大于给定数 将当前数和大于区域前一个数交换 大于区域向左扩1
                swap(arr,--more,cur);
            }else{
                cur++;
            }
        }

        return new int[]{less+1,more-1};

    }

    public static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
