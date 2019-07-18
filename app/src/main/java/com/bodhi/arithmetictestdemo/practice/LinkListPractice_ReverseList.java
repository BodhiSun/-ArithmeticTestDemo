package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/18 15:19
 * desc :反转单向和双向链表
 *【题目】 分别实现反转单向链表和反转双向链表的函数。
 * 要求】 如果链表长度为N，时间复杂度要求为O(N)，额外空间 复杂度要求为O(1)
 *
 */
public class LinkListPractice_ReverseList {

    /**
     * 自定义单向链表
     */
    public static class Node{
        public int value;//链表节点的值
        public Node next;//指向下一个节点

        public Node(int data){
            this.value=data;
        }
    }

    /**
     * 反转单向链表
     * @param head 链表的头节点
     * @return
     */
    public static Node reverseLsit(Node head){
        Node pre =null;//链表前一个节点为空
        Node next = null;//链表后一个节点为空

        while (head!=null){
            next = head.next;
            head.next=pre;
            pre=head;
            head=next;
        }

        return pre;
    }

    /**
     * 打印单向链表
     * @param head 链表的头节点
     * @return
     */
    public static void printLinkedList(Node head){
        System.out.print("Linked List:");
        while (head!=null){
            System.out.print(head.value+" ");
            head=head.next;
        }
        System.out.println();
    }

    /**
     * 自定义双向链表
     */
    public static class DoubleNode {
        public int value;//链表节点的值
        public DoubleNode next;//指向下一个节点
        public DoubleNode last;//指向上一个节点

        public DoubleNode(int data) {
            this.value = data;
        }
    }

    /**
     * 反转双向链表
     * @param head 链表的头节点
     * @return
     */
    public static DoubleNode reverseList(DoubleNode head) {
        DoubleNode pre = null;
        DoubleNode next = null;

        while (head!=null){
            next=head.next;
            head.next=pre;
            head.last=next;
            pre=head;
            head=next;

        }
        return pre;
    }

    /**
     * 打印双向链表
     * @param head 链表的头节点
     * @return
     */
    public static void printDoubleLinkedList(DoubleNode head) {
        System.out.print("Double Linked List: ");
        DoubleNode end = null;//链表的末尾节点

        //正向打印链表
        while (head!=null){
            System.out.print(head.value + " ");
            end = head;//当循环到链表最后一个节点时 end指向末尾节点
            head = head.next;
        }

        //反向打印链表
        System.out.print("| ");
        while(end!=null){
            System.out.print(end.value + " ");
            end = end.last;
        }
        System.out.println();
    }

}
