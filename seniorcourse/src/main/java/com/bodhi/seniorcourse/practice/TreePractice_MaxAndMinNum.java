package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/28 15:47
 * desc : 树形dp-返回二叉树中的最大值和最小值
 *
 * 思路：以head为头节点的二叉树中 最大值一定来自于左、右、和自己中的最大值，最小值一定来自于左、右、和自己
 * 中的最小值。所以采用递归的方法 逐级往上返回当前节点所在树的最大和最小值
 *
 */
public class TreePractice_MaxAndMinNum {

    /**
     * 构建二叉树
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 构建递归的返回类型
     */
    public static class ReturnData{
        public int min;
        public int max;

        public ReturnData( int min,int max) {
            this.min = min;
            this.max = max;
        }
    }

    public static void printMinAndMax(Node head){
        ReturnData data = processTree(head);
        System.out.println("min:" + data.min);
        System.out.println("max:" + data.max);
    }

    /**
     * 递归解析以head为头结点的二叉树 返回整棵树的最大值和最小值
     * @param head
     * @return
     */
    private static ReturnData processTree(Node head) {
        //如果head为空 递归到叶子节点的子节点
        if(head==null){
            //为了不影响上层比较 将最小设置为系统最大 最大设置为系统最小
            return new ReturnData(Integer.MAX_VALUE,Integer.MIN_VALUE);
        }

        Node left =head.left;
        ReturnData leftData = processTree(left);
        Node right =head.right;
        ReturnData rightData = processTree(right);

        ReturnData returnData = new ReturnData(
                Math.min(Math.min(leftData.min,rightData.min),head.value),
                Math.max(Math.max(leftData.max,rightData.max),head.value));
        return returnData;
    }

    public static void test(){
        Node head = new Node(6);
        head.left = new Node(4);
        head.right = new Node(9);
        head.left.left=new Node(3);
        head.left.right=new Node(5);
        head.right.left=new Node(8);
        head.right.right=new Node(10);

        printMinAndMax(head);
    }


}
