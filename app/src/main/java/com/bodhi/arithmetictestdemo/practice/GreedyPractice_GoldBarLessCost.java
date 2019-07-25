package com.bodhi.arithmetictestdemo.practice;

import java.util.PriorityQueue;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/25 10:41
 * desc :贪心问题 切金条用最小的费用 哈夫曼编码
 * 贪心问题就是按经验累积多做多累积
 *
 * 【题目】一块金条切成两半，是需要花费和长度数值一样的铜板。一群人想整分整块金条，怎么分最省铜板？
 * 例如,给定数组{10,20,30}，代表一共三个人，整块金条长度为 10+20+30=60. 金条要分成10,20,30三个部分。
 * 如果，先把长度60的金条分成10和50，花费60 再把长度50的金条分成20和30， 花费50 一共花费110铜板。
 * 但是如果，先把长度60的金条分成30和30，花费60 再把长度30金条分成10和20，花费30 一共花费90铜板。
 * 输入一个数组，请返回分割的最小代价。
 *
 * 思路：这是一个标准的哈夫曼编码问题 先把所有的数加到一个小根堆 每次从小根堆拿出两个最小的数结合 将合并
 * 后的结果放回小根堆，然后在从小根堆拿出两个数结合，然后在放回小根堆 记录每次结合的结果 当堆里面只剩一个
 * 数的时候停止循环 此时头结点已经生成，分割的顺序就是从头结点开始从上到下依次分割 所有非叶结合的结果累加
 * 在一起就是分割的最小代价。
 *
 * 哈夫曼编码：把所有的叶节点生成一棵树 两个叶节点合并的过程中代价就是两个叶节点的和
 * 像求总共的代价 而且总代价是由子代价累加或者累乘的这种贪心问题 都可以用哈夫曼编码
 */
public class GreedyPractice_GoldBarLessCost {

    public static int lessCost(int[] arr){
        //优先级队列就是堆
        PriorityQueue<Integer> pQ = new PriorityQueue<>();
        //将所有元素都加入到堆中
        for (int i = 0; i < arr.length; i++) {
            pQ.add(arr[i]);
        }

        //总的花费的代价
        int sum = 0;
        //当前两个数结合的代价
        int cur = 0;

        //直到当前堆里面只剩一个数停止循环
        while (pQ.size()>1){
            //每次从小根堆拿出两个最小的数结合
            Integer arr1 = pQ.peek();
            pQ.poll();
            Integer arr2 = pQ.peek();
            pQ.poll();
            cur = arr1+arr2;
            sum+=cur;
            //将合并后的结果放回小根堆
            System.out.println("arr1:"+arr1+"   arr2:"+arr2);
            pQ.add(cur);
        }
        return sum;
    }

    public static void test(){
        // solution
        int[] arr = { 6, 7, 8, 9 };
        System.out.println(lessCost(arr));
    }

}
