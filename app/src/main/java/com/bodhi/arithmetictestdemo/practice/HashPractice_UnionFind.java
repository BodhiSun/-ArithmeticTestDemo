package com.bodhi.arithmetictestdemo.practice;

import java.util.HashMap;
import java.util.List;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/24 10:16
 * desc :认识并查集结构 用map实现
 *
 * 并查集结构：初始化的时候一次性提供所有数据样本 假如有1,2,3,4,5 5个数据样本 首先5个数据各自构成一个集合 并将头指针指向自己
 * 此时每个数据就是自己所在集合的代表节点 如果1,2两个节点所在集合合并 则将2挂在1下面 2指向1 1仍然指向自己 此时1就是1,2所在集
 * 合的代表节点 合并的原则是数量少的集合挂在数量多的集合代表节点下面 形成一个多叉树。
 * 判断两个元素是否属于一个集合就是 通过两个元素向上找 找自己所在集合的代表节点 判断两个代表节点是否相等。
 *
 * 优化：当查一个元素的代表节点时 查完之后更改多叉树的结构 将此节点开始向上的所有节点都直接挂在代表节点(多叉树结构变扁平)
 * 即n1-n2-n3-n4-n5 当查完n4代表节点时 多叉树结构变成 n1-n2 n1-n3 n1-n4-n5
 *
 * 应用场景：
 * 1.非常快的检查两个元素是否属于一个集合 isSameSet
 * 2.两个元素各自所在的集合 合并在一起 union
 *
 * 结论：假设有n个数据样本，任意的查两个元素是否在一个集合，也可以任意的把两个元素各自所在的集合合并，调多少次都可以只要
 * 查询次数加合并次数整体逼近n及以上，单次操作无论查询还是合并平均时间复杂度都是0(1)常数级别
 *
 */
public class HashPractice_UnionFind {
    public static class Node {
        // whatever you like String,int...
    }

    public static class UnionFindSet {
        //前一个node k是某一个节点 后一个node v是它父节点
        public HashMap<Node,Node> fatherMap;
        //某一个节点它所在的集合一共有多少个节点 即所在集合的代表节点下面一共有多少个节点
        public HashMap<Node,Integer> sizeMap;

        //初始化的时候一次性提供所有数据样本 不接受临时插入数据
        public UnionFindSet(List<Node> nodes){
            makeSets(nodes);
        }

        private void makeSets(List<Node> nodes){
            //创建两张表
            fatherMap = new HashMap<Node, Node>();
            sizeMap = new HashMap<Node, Integer>();

            //首先每个数据节点各自构成一个集合 并将头指针指向自己 此时每个集合大小都为1
            for (Node node : nodes) {
                fatherMap.put(node, node);
                sizeMap.put(node, 1);
            }
        }

        /**
         * 查找当前节点集合的代表节点 即当前节点所在集合的头结点
         * @param node
         * @return
         */
        private Node findHead(Node node){
            //向上查找当前节点的父节点是否等于自己 不是继续向上递归查找
            Node father = fatherMap.get(node);
            if(father!=node){
                father=findHead(father);
            }

            //并将沿途的所有节点直接挂在集合代表节点上
            fatherMap.put(node, father);

            return father;
        }

        /**
         * 判断两个节点是否在同一集合
         * @param a
         * @param b
         * @return
         */
        public boolean isSameSet(Node a,Node b){
            return findHead(a) ==findHead(b);
        }

        /**
         * 合并两个节点所在的集合
         */
        public void union(Node a, Node b){
            if(a==null||b==null){
                return;
            }
            Node aHead = findHead(a);
            Node bHead = findHead(b);
            if(aHead!=bHead){
                int aSetSize = sizeMap.get(aHead);
                int bSetSize = sizeMap.get(bHead);
                if(aSetSize<=bSetSize){
                    fatherMap.put(aHead,bHead);
                    sizeMap.put(bHead,aSetSize+bSetSize);
                }else{
                    fatherMap.put(bHead,aHead);
                    sizeMap.put(aHead,aSetSize+bSetSize);
                }
            }
        }


    }

}
