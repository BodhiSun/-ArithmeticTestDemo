package com.bodhi.seniorcourse.practice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/27 18:31
 * desc :大楼边际线问题-平衡搜索二叉树的应用
 *
 * 【题目】
 * 给定一个N行3列二维数组，每一行表示有一座大楼，一共有N座大楼。 所有大楼的底部都坐落在X轴上，每一行的三个
 * 值(a,b,c)代表每座大楼的从(a,0)点开始，到 (b,0)点结束，高度为c。 输入的数据可以保证a<b,且a，b，c均为正
 * 数。大楼之间可以有重合。请输出整体的轮廓线。例子：给定一个二维数组 [ [1, 3, 3], [2, 4, 4], [5, 6,
 * 1]] 输出为轮廓线 [ [1, 2, 3], [2, 4, 4], [5, 6, 1] ]
 *
 * 思路：将一个大楼的起始和终止位置标出 跟踪每个位置最大高度的变化 生成所有轮廓线
 *
 */
public class Practice_Building_Outline {

    /**
     * 用来描述楼的边际线
     */
    public static class Node{
        public boolean isUp;//边际线方向是上还是下
        public int posi;//边际线位置
        public int h;//边际线高度

        public Node(boolean isUp, int posi, int h) {
            this.isUp = isUp;
            this.posi = posi;
            this.h = h;
        }
    }

    /**
     * 按大楼边线的位置从小到大排序
     */
    public static class NodeComparator implements Comparator<Node>{

        @Override
        public int compare(Node o1, Node o2) {
            //两个边界不等的时候按从小到大排序
            if (o1.posi != o2.posi) {
                return o1.posi - o2.posi;
            }

            //如两个边界相等(即重合一起) 并且方向相反时 向上的边界在前面
            if (o1.isUp != o2.isUp) {
                return o1.isUp ? -1 : 1;
            }

            return 0;
        }
    }


    /**
     * 给定二位数组代表的大楼 返回大楼的轮廓线
     * @param buildings 二位数组代表的大楼
     * @return
     */
    public static List<List<Integer>> buildingOutline(int[][] buildings){
        //每个大楼对应开始和结束两个边界线
        Node[] nodes = new Node[buildings.length*2];
        for (int i = 0; i < buildings.length; i++) {
            //每个大楼的每组向上向下边界依次存入数组
            nodes[i*2]=new Node(true,buildings[i][0],buildings[i][2]);
            nodes[i*2+1]=new Node(false,buildings[i][1],buildings[i][2]);
        }

        //按自定义的比较器 对边界进行排序
        Arrays.sort(nodes,new NodeComparator());

        //TreeMap 是基于Red-Black-Tree实现的 所以是一棵平衡搜索二叉树
        TreeMap<Integer,Integer> hMap = new TreeMap<>();//某一时刻上每个边界位置高度对应的次数
        TreeMap<Integer,Integer> pmMap = new TreeMap<>();//某一位置上对应的轮廓线的最大高度

        for (int i = 0; i < nodes.length; i++) {
            //如果是上边界 没有时存1次，有则在原有上加1
            if(nodes[i].isUp){
                if(!hMap.containsKey(nodes[i].h)){
                    hMap.put(nodes[i].h,1);
                }else{
                    hMap.put(nodes[i].h,hMap.get(nodes[i].h)+1);
                }
            }else{
                //如果是下边界 当边界次数是1时减1为0 所以直接移除此边界 否则减1
                if(hMap.containsKey(nodes[i].h)){
                    if(hMap.get(nodes[i].h)==1){
                        hMap.remove(nodes[i].h);
                    }else{
                        hMap.put(nodes[i].h,hMap.get(nodes[i].h)-1);
                    }
                }
            }

            //根据hMap中记录的每个边界高度情况 计算当前位置对应的轮廓线的最大高度
            if(hMap.isEmpty()){
                pmMap.put(nodes[i].posi,0);
            }else{
                //TreeMap中lastKey即为 存储中最大的key
                pmMap.put(nodes[i].posi,hMap.lastKey());
            }
        }

        //用来存储轮廓线数组
        List<List<Integer>> res = new ArrayList<>();
        int start = 0;//当前遍历的边界线位置
        int height = 0;//当前边界线的高度
        for (Map.Entry<Integer, Integer> entry : pmMap.entrySet()) {
            int curPosition = entry.getKey();
            int curMaxHeight = entry.getValue();
            //如果最大高度有变化时
            if(height!=curMaxHeight){
                //当前高度差是由两个楼之间的边界造成的 而不是和地面的高度差时 记录当前轮廓
                if(height!=0){
                    List<Integer> newRecord = new ArrayList<Integer>();
                    newRecord.add(start);
                    newRecord.add(curPosition);
                    newRecord.add(height);
                    res.add(newRecord);
                }

                //当高度差是和地面之间造成的时 更新起始位
                start = curPosition;
                height = curMaxHeight;
            }
        }

        return res;
    }


    public static void test(){
        int[][] buildings = new int[][]{{1,3,3},{2,4,4},{5,6,1}};
        System.out.println(buildingOutline(buildings));

    }




}
