package com.bodhi.arithmetictestdemo.practice;

import android.webkit.WebView;

import java.util.Stack;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/19 17:14
 * desc : 声明二叉树结构，实现二叉树的先序、中序、后序遍历，包括递归方式和非递归 方式
 */
public class TreePractice_PreInPosTraversal {

    /**
     * 声明一个二叉树结构 包括值，左节点，右节点
     */
    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    /**
     * 二叉树先序遍历-递归方式
     * 先序遍历：先打印当前节点然后再打印整棵左子树最后打印整棵右子树
     * @param head 二叉树的头结点
     */
    public static void preOrderRecur(Node head){
        if(head==null){
            return ;
        }
        System.out.print(head.value + " ");
        preOrderRecur(head.left);
        preOrderRecur(head.right);
    }

    /**
     * 二叉树中序遍历-递归方式
     * 中序遍历：先打印整棵左子树再打印当前节点最后打印整棵右子树
     * @param head 二叉树的头结点
     */
    public static void inOrderRecur(Node head){
        if(head==null){
            return ;
        }
        inOrderRecur(head.left);
        System.out.print(head.value + " ");
        inOrderRecur(head.right);
    }

    /**
     * 二叉树后序遍历-递归方式
     * 后序遍历：先打印整棵左子树后打印整棵右子树最后再打印当前节点
     * @param head 二叉树的头结点
     */
    public static void posOrderRecur(Node head){
        if(head==null){
            return ;
        }
        posOrderRecur(head.left);
        posOrderRecur(head.right);
        System.out.print(head.value + " ");
    }

    /**
     * 二叉树先序遍历-非递归方式
     * 先序遍历：先打印当前节点然后再打印左节点最后打印整棵右节点
     * @param head 二叉树的头结点
     */
    public static void preOrderUnRecur(Node head){
        System.out.print("pre-order: ");
        if(head!=null){
           //声明一个栈 用来存放节点 因为栈的属性可以压入后再按逆序回到上节点
            Stack<Node> stack = new Stack<Node>();
            //将头结点压入栈中
            stack.add(head);
            //循环出栈压栈 直至所有节点都压入并又都弹出 栈为空后
            while (!stack.isEmpty()){
                head=stack.pop();
                //打印头结点
                System.out.print(head.value + " ");
                //先压右子树再压左子树 第二次循环时会将最后压的左子树先弹出
                if(head.right!=null){
                    stack.push(head.right);
                }
                if(head.left!=null){
                    stack.push(head.left);
                }
            }
        }
        System.out.println();
    }

    /**
     * 二叉树中序遍历-非递归方式
     * 中序遍历：先打印左节点再打印当前节点最后打印右节点
     * @param head 二叉树的头结点
     */
    public static void inOrderUnRecur(Node head){
        System.out.print("in-order: ");
        if (head != null) {
            Stack<Node> stack=new Stack<Node>();
            //栈不等于空或当前节点不等于空继续遍历 否则树上所有的节点都遍历完毕
            while (!stack.isEmpty()||head!=null){
                //把所有左节点都压入栈
                if(head!=null){
                    stack.push(head);
                    head= head.left;
                }else{
                    //从栈中弹出一个节点变成当前节点 并打印
                    head=stack.pop();
                    System.out.print(head.value + " ");
                    //当前节点移动到右节点
                    head = head.right;
                }
            }
        }
        System.out.println();
    }

    /**
     * 二叉树后序遍历-非递归方式
     * 后序遍历：先打印左节点后打印右节点最后再打印当前节点
     * @param head 二叉树的头结点
     *  后序 左-右-中 刚好为 中-右-左的逆序
     *  中-右-左 可以有先序遍历 中-左-右改出来
     */
    public static void posOrderUnRecur1(Node head) {
        System.out.print("pos-order: ");
        if (head != null) {
            Stack<Node> s1 = new Stack<Node>();//正常排序用的栈
            Stack<Node> s2 = new Stack<Node>();//辅助栈 用于存放改版先序遍历的结果

            //先序改版 先压左节点 后压右节点 将遍历节点压s2辅助栈
            s1.push(head);
            while (!s1.isEmpty()) {
                head = s1.pop();
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }

            //将辅助栈中 中-右-左遍历结果在依次弹出 变成左-右-中 顺序
            while (!s2.isEmpty()) {
                System.out.print(s2.pop().value + " ");
            }
        }
        System.out.println();
    }

    /**
     * 只用一个栈的极客写法 了解即可 掌握更好
     * @param h
     */
    public static void posOrderUnRecur2(Node h) {
        System.out.print("pos-order: ");
        if(h!=null){
            Stack<Node> stack = new Stack<Node>();
            stack.push(h);
            Node c = null;
            while (!stack.isEmpty()){
                c =stack.peek();
                if(c.left!=null&&h!=c.left&&h!=c.right){
                    stack.push(c.left);
                }else if(c.right!=null&&h!=c.right){
                    stack.push(c.right);
                }else{
                    System.out.print(stack.pop().value + " ");
                    h=c;
                }
            }
        }
        System.out.println();
    }

}
