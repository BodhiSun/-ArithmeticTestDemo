package com.bodhi.arithmetictestdemo.practice;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/25 12:25
 * desc :贪心问题 项目达到最大收益 IPO
 *
 * 【题目】给定一组项目 每个项目都有相应的花费costs[] 和相应的收益profits[] 每做完一个项目，马上获得的收益，
 * 可以用来做下一个项目，一个项目只能做一次 并且一次只能做一个项目不能同时进行。假如提供W表示初始资金 K表示
 * 最多能做的项目数 请输出在规定初始资金m和最大次数K限制下 能获得的最大钱数
 *
 * 思路：制定贪心策略
 * 1.整理所有项目 把花费costs和收益profits对应起来形成 p[]
 * 2.p把按照costs属性形成小根堆
 * 3.从形成的小根堆 堆顶开始costs<W 即弹出 剩下的都是待解锁的项目
 * 4.将弹出后的数据按照profits属性形成大根堆 开始做堆顶一个项目
 * 5.收益+原始W 然后重新解锁小根堆项目并弹出 然后将项目放到大根堆  或是W不够不解锁新项目 继续做大根堆堆顶
 * 项目 重复更新W 直到达到K次或大根堆里面没项目了停止
 *
 *
 */
public class GreedyPractice_ProjectMoreMoney {
    /**
     * 项目节点
     */
    public static class Node {
        public int p;//收益
        public int c;//成本

        public Node(int p, int c) {
            this.p = p;
            this.c = c;
        }
    }

    /**
     * 按花费从小到大比较器 用于创建小根堆
     */
    public static class MinCostComparator implements Comparator<Node>{
        @Override
        public int compare(Node o1, Node o2) {
            return o1.c-o2.c;
        }
    }

    /**
     * 按收益从大到小比较器 用于创建大根堆
     */
    public static class MaxProfitComparator implements Comparator<Node> {
        @Override
        public int compare(Node o1, Node o2) {
            return o2.p - o1.p;
        }
    }


    /**
     *
     * 获得最大化资本
     * @param k 最多可做的项目数量
     * @param w  初始资金
     * @param profits 每个项目对应的收益
     * @param capital 每个项目对应的成本
     * @return
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public static int gainMaximizedCapital(int k, int w, int[] profits, int[] capital){
        //整理所有项目
        Node[] nodes = new Node[profits.length];
        for (int i = 0; i < profits.length; i++) {
            nodes[i]=new Node(profits[i],capital[i]);
        }

        //优先级队列就是堆 传入相应的比较规则即可生成 大根堆或是小根堆
        PriorityQueue<Node> minCostQ=new PriorityQueue<>(new MinCostComparator());
        PriorityQueue<Node> maxProfits = new PriorityQueue<>(new MaxProfitComparator());

        //将所有项目加入到小根堆按成本排序
        for (int i = 0; i < nodes.length; i++) {
            minCostQ.add(nodes[i]);
        }
        for (int i = 0; i < k; i++) {
            //小根堆不为空 并且小根对头节点成本小于资产
            while (!minCostQ.isEmpty()&&minCostQ.peek().c<=w){
                //将小根堆弹出的项目加入到大根堆按收益排序
                maxProfits.add(minCostQ.poll());
            }

            //大根堆开始做项目 当大根堆空了的时候 返回总资产
            if(maxProfits.isEmpty()){
                return w;
            }
            w+=maxProfits.poll().p;
        }
        //或者当达到最多项目次数k上限时返回总资产
        return w;
    }



}