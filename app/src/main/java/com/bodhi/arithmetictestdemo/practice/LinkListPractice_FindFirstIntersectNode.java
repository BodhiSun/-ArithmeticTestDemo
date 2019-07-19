package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/19 10:39
 * desc :两个单链表相交的一系列问题
 *
 *【题目】 给定两个单链表的头节点 head1和head2，请实现一个函数，如果两个链表相交，请返回相交的第一个节点；不相交返回null。
 * 要求：如果链表1 的长度为N，链表2的长度为M，时间复杂度请达到 O(N+M)，额外 空间复杂度请达到O(1)。
 *
 * 思路：本题可拆分成如下三题
 * 1.单链表可能有环，也可能无环。先判断一个单链表是否有环
 * 2.这两个链表可能相交，也可能不相交。在判断完是否有环的基础上判断是否有交点
 * 3.时间复杂度请达到 O(N+M)，空间复杂度请达到O(1)
 *
 * 解析：
 * 1.判断单链表是否有环：
 * 第一种方法用hash表 遍历链表一次将每个节点存入HashSet中 如果遍历到空节点了 说明无环，否则第一个重复存入的节点即为第一个入环点
 * 第二种方法准备快慢两个指针，如果快指针指到空节点说明无环，否则快慢指针比会重合。重合后快指针回到起点每次走一步，快慢再次重合即为第一个入环点
 *
 * 2.判断两个单链表是否相交
 * 第一种情况两个都是无环，两个无环单链表有相交的情况 如相交末尾节点必相等
 * 第二种情况一个有环一个无环，一个有环一个无环单链表不可能相交，相交就不符合单向链表的特性了
 * 第三种情况两个都有环，两个有环单链表有相交的情况，相交分先相交后共享一个环和直接在共享环上相交两种拓扑情况
 *
 *
 */
public class LinkListPractice_FindFirstIntersectNode {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 传入两个单链表头节点判断是否相交
     * @param head1
     * @param head2
     * @return
     */
    public static Node getIntersectNode(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        //分别获得两个链表的入环点(有可能为null)
        Node loop1 = getLoopNode(head1);
        Node loop2 = getLoopNode(head2);

        //两个无环链表相交问题
        if(loop1==null&&loop2==null){
            return noLoop(head1,head2);
        }

        //两个有环链表相交问题
        if(loop1!=null&&loop2!=null){
            return bothLoop(head1,loop1,head2,loop2);
        }

        //loop1,loop2中一个为空一个不为空 不可能相交返回null;
        return null;
    }

    /**
     * 两个有环链表相交 相交返回第一个相交点 不相交返回null
     * @param head1
     * @param loop1
     * @param head2
     * @param loop2
     * @return
     */
    private static Node bothLoop(Node head1, Node loop1, Node head2, Node loop2) {
        Node cur1 = null;
        Node cur2 = null;

        //如果loop1==loop2则是 先相交后共享一个环的拓扑情况 找相交点类似两个无环链表找相交点(刨除下面的环后)
        if (loop1==loop2){
            cur1=head1;
            cur2=head2;
            int n=0;
            //刨除下面的环
            while (cur1!=loop1){
                n++;
                cur1=cur1.next;
            }
            while (cur2!=loop2){
                n--;
                cur2=cur2.next;
            }
            cur1 = n > 0 ? head1 : head2;
            cur2 = cur1 == head1 ? head2 : head1;
            n = Math.abs(n);
            while (n != 0) {
                n--;
                cur1 = cur1.next;
            }
            while (cur1 != cur2) {
                cur1 = cur1.next;
                cur2 = cur2.next;
            }
            return cur1;
        }else{
            //有可能不相交 或是 在共享环上相交两种情况
            cur1=loop1.next;
            //遍历一遍链表1环上的所有点
            while (cur1!=loop1){
                //如果链表1环上有loop2 则是在共享环上相交这种情况 返回loop1或loop2都行
                if(cur1==loop2){
                    return loop1;
                }
                cur1=cur1.next;
            }

            //如果链表1环上没有loop2 则是两个有环的独立不相交链表 返回null
            return null;
        }
    }

    /**
     * 两个无环链表相交 相交返回第一个相交点 不相交返回null
     * @param head1
     * @param head2
     * @return
     */
    private static Node noLoop(Node head1, Node head2) {
        if (head1 == null || head2 == null) {
            return null;
        }

        Node cur1 = head1;//用于遍历到最后的末节点
        Node cur2 = head2;//用于遍历到最后的末节点
        int n =0;//用于找出那个链表长 并且表示两个链表长度差值
        while (cur1.next!=null){
            n++;
            cur1=cur1.next;
        }
        while (cur2.next!=null){
            n--;
            cur2=cur2.next;
        }

        //如果两个链表末节点不等 则一定不相交直接返回null
        if(cur1!=cur2){
            return null;
        }

        //根据n正负判断哪个是长链表并且 cur1指向长链表 cur2指向短链表
        cur1=n>0?head1:head2;
        cur2=cur1==head1?head2:head1;
        n=Math.abs(n);

        //长链表先走n步
        while (n!=0){
            n--;
            cur1=cur1.next;
        }

        //两个链表一起走直到相交
        while (cur1!=cur2){
            cur1=cur1.next;
            cur2=cur2.next;
        }
        return cur1;
    }

    /**
     * 判断一个单链表是否有环 有环返回第一个入环点 无环返回null
     * @param head
     * @return
     */
    private static Node getLoopNode(Node head) {
        if (head == null || head.next == null || head.next.next == null) {
            return null;
        }

        Node n1 = head.next; //慢指针一次走一步
        Node n2 = head.next.next; //快指针一次走两步

        //快慢指针在环中相遇或无环时终止
        while (n1!=n2){
            //如果快指针到结尾了 直接返回null 不可能有环
            if(n2.next==null||n2.next.next==null){
                return null;
            }

            n2=n2.next.next;
            n1=n1.next;
        }

        //此时n1==n2 此链表有环 并且快慢指针在环中相遇

        //将快指针回到起始位置,并且一次走一步 直至和慢指针相遇(一定会在第一个入环点相遇 数学证明过 当结论用)
        n2=head;
        while (n1!=n2){
            n1=n1.next;
            n2=n2.next;
        }

        return n1;
    }

    public static void  test(){
        // 1->2->3->4->5->6->7->null
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);

        // 0->9->8->6->7->null
        Node head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        //两个无环相交
        System.out.println(getIntersectNode(head1, head2).value);


        // 1->2->3->4->5->6->7->4...
        head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);
        head1.next.next.next = new Node(4);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(6);
        head1.next.next.next.next.next.next = new Node(7);
        head1.next.next.next.next.next.next = head1.next.next.next; // 7->4

        // 0->9->8->2...
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next; // 8->2
        //两个有环相交 先相交后共享环
        System.out.println(getIntersectNode(head1, head2).value);


        // 0->9->8->6->4->5->6..
        head2 = new Node(0);
        head2.next = new Node(9);
        head2.next.next = new Node(8);
        head2.next.next.next = head1.next.next.next.next.next; // 8->6
        //两个有环相交 在共享环上相交
        System.out.println(getIntersectNode(head1, head2).value);

    }


}
