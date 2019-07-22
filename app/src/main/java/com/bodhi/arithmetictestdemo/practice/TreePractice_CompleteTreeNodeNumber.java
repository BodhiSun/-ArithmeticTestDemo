package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/22 16:50
 * desc :已知一棵完全二叉树，求其节点的个数
 *
 * 要求：时间复杂度低于O(N)，N为这棵树的节点个数
 *
 *思路：
 * 满二叉树节点个数等于：2^n-1 n为层数
 *
 * 1.先找到左子树最左边节点判断二叉树的层数
 * 2.在找到右子树最左边的节点 判断是是最后一层
 * 如果是最后一层 整棵左子树一定是满二叉树 整棵左子树节点个数：2^(n-1)-1 加上根节点：2^(n-1)
 * 如果不是最后一层 则整棵右子树一定是满二叉树 整棵右子树的节点：2^(n-2)-1 加根节点：2^(n-2)
 * 3.递归判断另一半剩下的完全二叉树 求和
 *
 * 时间复杂度为O(logN)^2 因为每一层遍历1个节点 一共遍历logN个节点 每个节点找一次左边界又logN 所以为O(logN)^2 比O(N)小
 *
 */
public class TreePractice_CompleteTreeNodeNumber {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    public static int nodeNum(Node head) {
        if (head == null) {
            return 0;
        }
        return bs(head, 1, mostLeftLevel(head, 1));
    }

    /**
     *
     * @param node 当前节点
     * @param level 当前节点node在第几层
     * @return 返回以node为节点 当前子树一共有多少层
     */
    private static int mostLeftLevel(Node node, int level) {
        //因为为完全二叉树 所以只要遍历到最左的节点层数 即为整棵数的层数
        while (node!=null){
            level++;
            node=node.left;
        }
        return level-1;
    }

    /**
     *
     * @param node 当前节点
     * @param level 指当前node在第几层
     * @param h 指整棵二叉树的总高度
     * @return 返回的是以node节点为头的子树一共有多少个节点
     */
    public static int bs(Node node, int level, int h) {
        //如果当前节点到达了最后一层 则当前节点为1个
        if(level==h){
            return 1;
        }

        //如果右子树到达最后一层
        if(mostLeftLevel(node.right,level+1)==h){
            //左子树的总个数+当前节点+右子树的总个数 1<<N 等于2^N
            return (1<<(h-level))+bs(node.right,level+1,h);
        }else{
            //右子树的总个数+当前节点+左子树的总个数
            return (1<<(h-level-1))+bs(node.left,level+1,h);
        }
    }

    public static void test() {
        Node head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        System.out.println(nodeNum(head));

    }


}
