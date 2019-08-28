package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/28 17:31
 * desc :树形dp-二叉树上的最大距离
 *
 * 【题目】
 * 二叉树中，一个节点可以往上走和往下走，给定一棵二叉树的头节点head，请返回这棵二叉树上的最大距离。
 * 二叉树节点间距离的概念：二叉树一个节点到另一个节点间最短路径上的节点数量，叫做两个节点间的距离。
 *
 * 思路：
 * 和最大搜索二叉子树的大小 解题思路基本一样，
 * 第一步列可能性：最大距离有三种情况：1.在左子树，2.在右子树，3.整棵树构成
 * 第二步分析要收集的信息：每个节点构成的二叉树的最大距离 每个节点二叉树的高度
 * 第三步构建要收集的信息的数据类，写递归函数
 *
 */
public class TreePractice_MaxDistance {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static class ReturnType{
        public int maxDistance;
        public int h;

        public ReturnType(int maxDistance, int h) {
            this.maxDistance = maxDistance;
            this.h = h;
        }
    }

    public static ReturnType process(Node head){
        //第四步 BaseCase
        if (head==null) {
            return new ReturnType(0,0);
        }
        //第一步 假设左右能收集到信息
        ReturnType leftData = process(head.left);
        ReturnType rightData = process(head.right);

        //第二步 列出三种可能性
        int p1 = leftData.maxDistance;
        int p2 = rightData.maxDistance;
        int includeHeadDistance = leftData.h+1+rightData.h;

        //第三步 根据不同可能性 构建当前节点合理的返回的数据
        int resultDistance = Math.max(Math.max(p1,p2),includeHeadDistance);
        int includeH = Math.max(leftData.h,rightData.h)+1;
        return new ReturnType(resultDistance,includeH);
    }

    public static void test(){
        Node head1 = new Node(1);
        head1.left = new Node(2);
        head1.right = new Node(3);
        head1.left.left = new Node(4);
        head1.left.right = new Node(5);
        head1.right.left = new Node(6);
        head1.right.right = new Node(7);
        head1.left.left.left = new Node(8);
        head1.right.left.right = new Node(9);
        System.out.println(process(head1).maxDistance);
    }








}
