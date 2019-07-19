package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/19 10:26
 * desc :将单向链表按某值划分成左边小、中间相等、右边大的形式
 *
 * 【题目】 给定一个单向链表的头节点head，节点的值类型是整型，再给定一个 整数pivot。实现上述要求
 *
 * 进阶： 在原问题的要求之上再增加如下两个要求:
 * 1.要求左、中、右三部分每部分里的节点从左 到右的 顺序与原链表中节点的先后次序一致。
 * 2.如果链表长度为N，时间复杂度请达到O(N)，额外空间复杂度请达到O(1)。
 *
 * 思路：
 * 1.把所有节点都拿出来用数组存放，然后对数组进行处理(荷兰国旗问题),最后对处理后的数组重新生成链表重连
 *
 * 2.进阶问题 用荷兰国旗的解法保证不了稳定性和空间复杂度。
 * 解法：
 * 1.准备三个变量 less equal more,第一次遍历链表分别找到第一个小于pivot 等于pivot 和大于pivot三个节点赋值给三个变量
 * 2.三个变量分别生成三个链表 ，遍历原始链表 小于pivot的节点放到less链表后面，等于pivot的节点放到equal链表后面，大于pivot的节点放到more链表后面
 * 3.最后将less equal more三个链表首位相连 生成新的大链表
 *
 */
public class LinkListPractice_SmallerEqualBigger {
    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node listPartition1(Node head,int pivot){
        if(head==null){
            return head;
        }

        Node cur = head;

        //遍历整个链表的长度
        int i =0;
        while (cur!=null){
            i++;
            cur = cur.next;
        }

        //根据链表长度生成对应长度的数组,并对数组进行赋值
        Node[] nodeArr=new Node[i];
        i=0;
        cur =head;
        for (i =0;i!=nodeArr.length;i++){
            nodeArr[i] =cur;
            cur=cur.next;
        }

        //对数组进行按小 等 大分块处理 荷兰国旗问题解法
        arrPartition(nodeArr,pivot);

        //重新遍历排序后的数组 重连生成新的链表
        for (i = 1; i!=nodeArr.length; i++) {
            nodeArr[i-1].next=nodeArr[i];
        }
        //将新生成的链表的最后一个节点下一个指向设置为空
        nodeArr[i-1].next=null;

        return nodeArr[0];
    }

    public static void arrPartition(Node[] nodeArr, int pivot) {
        int small = -1;//小于区域 初始没有 所以设为-1
        int big = nodeArr.length;//遍历的总长度 也是大于区域 初始没有所以设为总长度
        int index = 0;//比较的起始位置即开始比较的第一个数
        while (index!=big){
            if(nodeArr[index].value<pivot){
                //当前比较数和小于区域前一个数交换，小于区域++ 当前指针++ 相当于小于区域推着等于区域往前移动
                swap(nodeArr,++small,index++);
            }else if (nodeArr[index].value==pivot){
                //相等时 当前指针++ 即小于区域前一个数到index为等于区域
                index++;
            }else{
                //当前比较数和大于区域后一个数交换， 大于区域-- 当前指针不变 交换过来的数继续和pivot比较。相当于大于区域向后挤压等于区域可能的大小
                swap(nodeArr,--big,index);
            }
        }
    }

    public static Node listPartition2(Node head, int pivot) {
        //声明小中大三个链表的首尾节点
        Node sH = null;
        Node sT = null;
        Node eH = null;
        Node eT = null;
        Node bH = null;
        Node bT = null;

        //用于保存下一个节点
        Node next = null;
        //遍历每个节点 分辨分配到三个链表
        while (head!=null){
            next = head.next;
            //将head节点与原链表分离 否则当head指向第二个节点时 第二个节点原本指向第三个节点 head此时作为第二节点又指向自己 冲突
            head.next=null;

            if(head.value<pivot){
                if(sH==null){
                    //若首节点为空 首尾节点都指向此几点
                    sH=head;
                    sT=head;
                }else{
                    //将此节点接到sT下一个
                    sT.next=head;
                    //sT移动到末尾
                    sT=head;
                }
            }else if(head.value==pivot){
                if(eH==null){
                    eH=head;
                    eT=head;
                }else{
                    eT.next=head;
                    eT=head;
                }
            }else{
                if (bH == null) {
                    bH = head;
                    bT = head;
                } else {
                    bT.next = head;
                    bT = head;
                }
            }
            //head指向原链表下一个节点 继续遍历
            head = next;
        }

        // 小链表和等于链表链接一起
        if(sT!=null){
            sT.next=eH;
            eT=eT==null?sT:eT;
        }

        //在和大链表链接在一起
        if(eT!=null){
            eT.next=bH;
        }

        return sH!=null?sH:eH!=null?eH:bH;
    }


    public static void swap(Node[] nodeArr, int a, int b) {
        Node temp = nodeArr[a];
        nodeArr[a]=nodeArr[b];
        nodeArr[b]=temp;

    }


    public static void printLinkedList(Node node){
        System.out.print("Linked List: ");
        while (node != null) {
            System.out.print(node.value + " ");
            node=node.next;
        }
        System.out.println();
    }

    public static void test(){
        Node head1 = new Node(7);
        head1.next=new Node(9);
        head1.next.next = new Node(1);
        head1.next.next.next = new Node(8);
        head1.next.next.next.next = new Node(5);
        head1.next.next.next.next.next = new Node(2);
        head1.next.next.next.next.next.next = new Node(5);

        printLinkedList(head1);
//        Node node = listPartition1(head1, 5);
        Node node = listPartition2(head1, 5);
        printLinkedList(node);
    }



}
