package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/18 17:52
 * desc :打印两个有序链表的公共部分
 * 【题目】 给定两个有序链表的头指针head1和head2，打印两个 链表的公共部分。
 *
 * 思路：和归并排序思路差不多
 */
public class LinkListPractice_PrintCommonPart {

    public static class CusNode {
        public int value;
        public CusNode next;

        public CusNode(int data) {
            this.value = data;
        }
    }

    public static void printCommonPart(CusNode head1, CusNode head2) {
        System.out.print("Common Part: ");
        while (head1!=null&&head2!=null){
            if(head1.value<head2.value){
                head1=head1.next;
            }else if(head1.value>head2.value){
                head2=head2.next;
            }else{
                System.out.print(head1.value + " ");
                head1 = head1.next;
                head2 = head2.next;
            }
        }
        System.out.println();
    }

    public static void printLinkedList(CusNode node) {
        System.out.print("Linked List: ");
        while (node!=null){
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }




}
