package com.bodhi.arithmetictestdemo.practice;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/22 16:49
 * desc :判断一棵树是否是搜索二叉树、判断一棵树是否是完全二叉树
 *
 * 搜索二叉树概念：对于树中的任何一个节点为头的子树 左子树都比它小 右子树都比它大
 * 完全二叉树概念：
 *
 * 搜索二叉树解题思路：如果二叉树的中序遍历结果是升序的 此二叉树就是搜索二叉树
 * 完全二叉树解题思路：
 * 1.按层遍历
 * 2.如果二叉树任意节点有右孩子没有左孩子 一定不是完全二叉树
 * 3.如果发现任意节点只有左孩子没有右孩子 或者左右孩子都没有 则剩下节点必须为叶子节点 否则一定不是完全二叉树
 */
public class TreePractice_IsBSTAndCBT {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }


    public static boolean isBSTInOrderUnRecur(Node head){
        System.out.print("in-order:");
        int temp=Integer.MIN_VALUE;

        if(head==null){
            return false;
        }

        Stack<Node> stack = new Stack<Node>();
        while (!stack.isEmpty() || head != null) {
            if (head != null) {
                stack.push(head);
                head = head.left;
            } else {
                head = stack.pop();
                if (head.value > temp) {
                    temp = head.value;
                } else {
                    return false;
                }
                head = head.right;
            }
        }
        return true;
    }

    public static void inOrderRecur(Node head){
        if(head==null){
            return ;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    public static boolean isBST2(Node head) {
        if (head == null) {
            return true;
        }
        boolean res = true;
        Node pre = null;
        Node cur1 = head;
        Node cur2 = null;
        while (cur1 != null) {
            cur2 = cur1.left;
            if (cur2 != null) {
                while (cur2.right != null && cur2.right != cur1) {
                    cur2 = cur2.right;
                }
                if (cur2.right == null) {
                    cur2.right = cur1;
                    cur1 = cur1.left;
                    continue;
                } else {
                    cur2.right = null;
                }
            }
            if (pre != null && pre.value > cur1.value) {
                res = false;
            }
            pre = cur1;
            cur1 = cur1.right;
        }
        return res;
    }


    public static boolean isCBT(Node head) {
        if (head == null) {
            return true;
        }
        Queue<Node> queue = new LinkedList<Node>();
        boolean leaf = false;//是否开启叶子节点判断模式
        Node l = null;
        Node r = null;
        queue.offer(head);
        while (!queue.isEmpty()){
            head=queue.poll();
            l = head.left;
            r=head.right;
            //或前为第一个条件 或后为第二个条件
            if((l==null&&r!=null)||(leaf&&(l!=null||r!=null))){
                return false;
            }

            if(l!=null){
                queue.offer(l);
            }

            if(r!=null){
                queue.offer(r);
            }

            //开启阶段叶子判断模式
            if(l==null||r==null){
                leaf=true;
            }
        }
        return true;
    }

    public static void test(){
        Node head = new Node(4);
        head.left = new Node(2);
        head.right = new Node(7);
        head.left.left = new Node(1);
        head.left.right = new Node(3);
        head.right.left = new Node(5);
//        head.right.left.right = new Node(6);

        printTree(head);
        inOrderRecur(head);
        System.out.println();

        System.out.println("isBST："+isBSTInOrderUnRecur(head));
        System.out.println("isBST2:"+isBST2(head));
        System.out.println("isCBT:"+isCBT(head));
    }


    // for test -- print tree
    public static void printTree(Node head) {
        System.out.println("Binary Tree:");
        printInOrder(head, 0, "H", 17);
        System.out.println();
    }

    public static void printInOrder(Node head, int height, String to, int len) {
        if (head == null) {
            return;
        }
        printInOrder(head.right, height + 1, "v", len);
        String val = to + head.value + to;
        int lenM = val.length();
        int lenL = (len - lenM) / 2;
        int lenR = len - lenM - lenL;
        val = getSpace(lenL) + val + getSpace(lenR);
        System.out.println(getSpace(height * len) + val);
        printInOrder(head.left, height + 1, "^", len);
    }

    public static String getSpace(int num) {
        String space = " ";
        StringBuffer buf = new StringBuffer("");
        for (int i = 0; i < num; i++) {
            buf.append(space);
        }
        return buf.toString();
    }








}
