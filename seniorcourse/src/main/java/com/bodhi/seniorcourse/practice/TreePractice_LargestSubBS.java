package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/28 14:30
 * desc :树形dp-最大搜索二叉子树的大小
 *
 *【题目】给定一棵二叉树的头节点head,返回最大搜索二叉子树的大小
 *
 * 思路：
 * 最大搜索二叉子树有三种情况：1.最大搜索二叉子树在左子树，2.最大搜索二叉子树在右子树，3.整棵树就是搜索二叉树
 * 所以某一节点X要记录的信息有:1.左子树和右子树上最大搜索二叉树的大小，2.左子树和右子树上最大搜索二叉树的头节
 * 点,3.左子树上的最大值和右子树上的最小值。就可以判断是上面三种情况哪一种
 *简收集的信息 当前节点最大搜索子树的大小 当前节点最大搜索子树的头部 当前节点所在子树的最大最小值
 *
 *
 */
public class TreePractice_LargestSubBS {

    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 递归函数封装后返回给上层的数据信息
     */
    public static class ReturnType{
        public int size;
        public Node head;
        public int min;
        public int max;

        public ReturnType(int size, Node head, int min, int max) {
            this.size = size;
            this.head = head;
            this.min = min;
            this.max = max;
        }
    }

    public static ReturnType process(Node head){
        if(head == null){
            return new ReturnType(0,null,Integer.MAX_VALUE, Integer.MIN_VALUE);
        }

        Node left = head.left;
        ReturnType leftSubTressInfo = process(left);
        Node right = head.right;
        ReturnType rightSubTressInfo = process(right);

        //如果最大搜索二叉子树包含本身节点的情况下 总的大小
        int includeItSelf = 0;
        if (leftSubTressInfo.head == left && rightSubTressInfo.head == right
                && head.value > leftSubTressInfo.max && head.value < rightSubTressInfo.min) {
            includeItSelf = leftSubTressInfo.size+1+rightSubTressInfo.size;
        }

        int p1 = leftSubTressInfo.size;
        int p2 = rightSubTressInfo.size;
        int maxSize = Math.max(Math.max(p1,p2),includeItSelf);
        Node maxHead = p1>p2?leftSubTressInfo.head:rightSubTressInfo.head;
        //如果包含本身节点时 最大搜索二叉子树的头节点就是本身节点
        if(maxSize==includeItSelf){
            maxHead = head;
        }

        return new ReturnType(maxSize,maxHead,
                Math.min(Math.min(leftSubTressInfo.min,rightSubTressInfo.min),head.value),
                Math.max(Math.max(leftSubTressInfo.max,rightSubTressInfo.max),head.value));
    }

    public static void test(){
        Node head = new Node(6);
        head.left = new Node(4);
        head.right = new Node(9);
//        head.left.left=new Node(3);
        head.left.left=new Node(7);
        head.left.right=new Node(5);
        head.right.left=new Node(8);
        head.right.right=new Node(10);

        System.out.println(process(head).size);
    }
}
