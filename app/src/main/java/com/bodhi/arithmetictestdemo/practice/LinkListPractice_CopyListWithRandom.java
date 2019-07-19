package com.bodhi.arithmetictestdemo.practice;

import java.util.HashMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/19 10:32
 * desc :复制含有随机指针节点的链表
 *
 * 【题目】
 * Node类中next指针和正常单链表中next指针的意义一样，rand指针是Node类中新增的指针，可能指向链表中的任意一个节点，也可能指向null。
 * 给定一个由 Node节点类型组成的无环单链表的头节点head，请实现一个函数 完成这个链表中所有结构的复制，并返回复制的新链表的头节点。
 *
 * 进阶：不使用额外的数据结构，只用有限几个变量，且时间复杂度在O(N) 内
 *
 * 思路:
 * 1.用hash表结构处理，将所有节点都存在HashMap中，K值为节 点 V值为 节点' 然后根据原链表重新构建Map中拷贝节点的 next 和 random
 *
 * 2.遍历原链表节点 将所有节点都拷贝一份接在接在原节点后面 然后拷贝节点指向原节点的next节点
 *  根据链表原节点上的random指向 构建拷贝节点的random指向
 *  然后将原节点和拷贝节点分离
 *
 */
public class LinkListPractice_CopyListWithRandom {

    public static class Node {
        public int value;
        public Node next;
        public Node rand;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node copyListWithRand1(Node head) {
        HashMap<Node,Node> map = new HashMap<Node,Node>();
        Node cur = head;

        //将每个节点都存入到hash表中
        while (cur!=null){
            map.put(cur,new Node(cur.value));
            cur= cur.next;
        }

        //根据原链表的next random重新构建hash表中节点的指向
         cur = head;
        while (cur!=null){
            //根据原始链表的next指向 找到map中对应的指向的拷贝 接到当前拷贝的链表节点上
            map.get(cur).next=map.get(cur.next);
            map.get(cur).rand=map.get(cur.rand);
            cur=cur.next;
        }

        //返回深度拷贝后的链表头结点
        return map.get(head);
    }

    public static Node copyListWithRand2(Node head){
        if(head==null){
            return null;
        }

        Node cur = head;
        Node next = null;
        //拷贝节点并连接上
        while (cur!=null){
            next = cur.next;
            cur.next=new Node(cur.value);
            cur.next.next=next;
            cur=next;
        }

        cur=head;
        Node curCopy=null;
        //设置拷贝节点的random
        while (cur!=null){
            //一次跳两个 指向原节点的下一个节点
            next=cur.next.next;
            curCopy=cur.next;
            //根据原节点的random找到 原节点的random的拷贝节点 并接到原节点的拷贝节点上
            curCopy.rand=cur.rand!=null?cur.rand.next:null;
            cur=next;
        }

        Node res = head.next;
        cur=head;
        //分离原节点和拷贝节点
        while (cur!=null){
            next=cur.next.next;
            curCopy=cur.next;
            cur.next=next;
            curCopy.next=next!=null?next.next:null;
            cur=next;
        }
        return res;
    }

    public static void printRandLinkedList(Node head){
        Node cur=head;
        System.out.print("order: ");
        while (cur!=null){
            System.out.print(cur.value+" ");
            cur=cur.next;
        }
        System.out.println();

        cur=head;
        System.out.print("rand:  ");
        while (cur!=null){
            System.out.print(cur.rand == null ? "- " : cur.rand.value + " ");
            cur=cur.next;
        }
        System.out.println();
    }

    public static void test(){
        Node head = null;
        Node res1 = null;
        Node res2 = null;

        printRandLinkedList(head);
        res1=copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");

        head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = new Node(4);
        head.next.next.next.next = new Node(5);
        head.next.next.next.next.next = new Node(6);

        head.rand = head.next.next.next.next.next; // 1 -> 6
        head.next.rand = head.next.next.next.next.next; // 2 -> 6
        head.next.next.rand = head.next.next.next.next; // 3 -> 5
        head.next.next.next.rand = head.next.next; // 4 -> 3
        head.next.next.next.next.rand = null; // 5 -> null
        head.next.next.next.next.next.rand = head.next.next.next; // 6 -> 4

        printRandLinkedList(head);
        res1 = copyListWithRand1(head);
        printRandLinkedList(res1);
        res2 = copyListWithRand2(head);
        printRandLinkedList(res2);
        printRandLinkedList(head);
        System.out.println("=========================");
    }

}
