package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/22 16:48
 * desc :判断一棵二叉树是否是平衡二叉树
 *
 * 平衡二叉树概念：对于树中的任何一个节点 它的左子树和右子树的高度差不超过1 这颗数就是平衡二叉树
 *
 * 思路：
 *
 * 第一步 列出可能性：
 *   1.给定一个节点X 首先判断X的左数树是否平衡 不平衡则不是平衡树
 *   2.判断X的右树是否平衡 不平衡则不是平衡树
 *   3.在左树平衡的前提下 获去左树高度
 *   4.在右树平衡的前提下 获去左树高度
 * 第二步 设计返回类型 ReturnData
 * 第三步 整个递归函数按照同样的过程 得到子树的信息 整合子树信息
 *
 * 思想：树形DP(动态规划 dynamic programming)
 *
 */
public class TreePractice_IsBalancedTree {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 设计返回类型 包含两个信息 是否平衡 和 高度
     */
    public static class ReturnData{
        public  boolean isB;
        public int h;

        public ReturnData(boolean isB, int h) {
            this.isB = isB;
            this.h = h;
        }
    }

    public static ReturnData process(Node head){
        if(head==null){
            //空树是平衡的
            return new ReturnData(true,0);
        }

        //当左树不平衡 此颗树一定不是平衡树
        ReturnData leftData=process(head.left);
        if(!leftData.isB)
            return new ReturnData(false,0);

        //当右树不平衡 此颗树一定不是平衡树
        ReturnData rightData=process(head.right);
        if(!rightData.isB)
            return new ReturnData(false,0);

        //如果左右子树的高度差大于1 此颗树不是平衡树
        if(Math.abs(leftData.h-rightData.h)>1){
            return new ReturnData(false,0);
        }

        //经过上面三步排除 此时一定是平衡树 高度为左右子树最高的+1(加上自己返回给上层)
        return new ReturnData(true,Math.max(leftData.h,rightData.h)+1);
    }

    /**
     * 判断head所在的这颗树的平衡性
     * @param head
     * @return
     */
    public static boolean isBalance(Node head){
        return process(head).isB;
    }


    public static void test() {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
//        head.right.left = new Node(6);
//        head.right.right = new Node(7);
        head.left.left.left = new Node(8);

        System.out.println(isBalance(head));
    }






}
