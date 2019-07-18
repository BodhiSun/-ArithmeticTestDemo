package com.bodhi.arithmetictestdemo.practice;

import java.util.Stack;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/18 18:23
 * desc :判断一个链表是否为回文结构
 *
 * 【题目】 给定一个链表的头节点head，请判断该链表是否为回 文结构。 例如： 15->6->15，返回true。 1->2->3，返回false。
 *  进阶： 如果链表长度为N，时间复杂度达到O(N)，额外空间复杂 度达到O(1)。
 *
 */
public class LinkListPractice_isPalindrome {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 第一种方法 need n extra space
     * 将数组直接压入到栈中 然后在一次从栈中弹出和链表逐个节点比较是否全相等
     * @return
     */
    public static boolean isPalindrome1(Node head){
        Stack<Node> stack = new Stack<Node>();
        Node cur = head;
        while (cur!=null){
            stack.push(cur);
            cur=cur.next;
        }

        while (head != null) {
            if(head.value!=stack.pop().value){
                return false;
            }
            head = head.next;
        }
        return true;


    }

    /**
     * 第二种方法 need n/2 extra space
     * 1.快指针一次走两步，慢指针一次走一步 然后快指针走到末尾慢指针走到中间
     * 2.然后将后半段节点压入栈中 重复方法一的比较方式
     * @return
     */
    public static boolean isPalindrome2(Node head){
        if (head == null || head.next == null) {
            return true;
        }
        Node right = head.next;//慢指针
        Node cur = head;//快指针

        //将快慢指针移动到对应位置
        while (cur.next!=null&&cur.next.next!=null){
            right = right.next;
            cur = cur.next.next;
        }

        //从慢指针开始将节点压入栈中
        Stack<Node> stack  =new Stack<Node>();
        while (right != null) {
            stack.push(right);
            right=right.next;
        }

        //比较栈中数据和链表前半段数据是否相等
        while (!stack.isEmpty()){
            if(head.value!=stack.pop().value){
                return false;
            }
            head=head.next;
        }
        return true;
    }

    /**
     * 第三种方法 need O(1) extra space
     * 1.快指针一次走两步，慢指针一次走一步 然后快指针走到末尾慢指针走到中间
     * 2.将链表后半部分逆序反向指向中点 中点节点的next指向为空
     * 3.依次从链表的左右两端像中间节点移动并比较 有一个不等为false
     * 4.将链表调整回原来的指向顺序
     *
     * @return
     */
    public static boolean isPalindrome3(Node head){
        if (head == null || head.next == null) {
            return true;
        }
        Node n1 = head;//慢指针
        Node n2 = head;//快指针

        //找中间节点 节点为奇数个刚好到中点 偶数个就到中点的前一个
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next; // n1 -> mid
            n2 = n2.next.next; // n2 -> end
        }

        //快指针指向中点下一个节点
        n2=n1.next;
        //中点节点下一个置空
        n1.next=null;
        Node n3=null;//临时节点

        //将链表后半部分逆序反向指向中点
        while (n2!=null){
            n3=n2.next;
            n2.next=n1;
            n1=n2;
            n2=n3;
        }

        //此时n1已经指向了最后节点 用n3保存最后的节点用于最后调整回原来链表
        n3=n1;
        //将n2指想头节点
        n2=head;

        //从左右两端分别开始比对是否是回文
        boolean res=true;
        while (n1!=null&&n2!=null){
            if(n1.value!=n2.value){
                res=false;
                break;
            }
            n1=n1.next;
            n2=n2.next;
        }

        //调整链表为原来的指向
        n1=n3.next;
        n3.next=null;
        while (n1!=null){
            n2=n1.next;
            n1.next=n3;
            n3=n1;
            n1=n2;
        }
        return res;
    }

    public static void printLinkedList(Node node) {
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }

    public static void linkedListIsPalindrome(){
        Node head = null;
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(2);
        head.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(2);
        head.next.next.next.next = new Node(1);
        printLinkedList(head);
        System.out.print(isPalindrome1(head) + " | ");
        System.out.print(isPalindrome2(head) + " | ");
        System.out.println(isPalindrome3(head) + " | ");
        printLinkedList(head);
        System.out.println("=========================");

    }


}
