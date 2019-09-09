package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/9/9 10:08
 * desc :环形单链表的约瑟夫问题(经典题目 难度很高 尽量理解)
 *
 * 【题目】
 * 据说著名犹太历史学家Josephus有过以下故事：在罗马人占领乔塔帕特后，39个犹太人与Josephus及他的朋友躲到
 * 一个洞中，39个犹太人决定宁愿死也不要被敌人抓到，于是决定了一个自杀方式，41个人排成一个圆圈，由第1个人
 * 开始报数，报数到3的人就自杀，然后再由下一个人重新报1，报数到3的人再自杀，这样依次下去，直到剩下最后一
 * 个人时，那个人可以自由选择自己的命运。这就是著名的约瑟夫问题。现在请用单向环形链表描述该结构并呈现整个
 * 自杀过程。
 * 输入：一个环形单向链表的头节点head和报数的值m。
 * 返回：最后生存下来的节点，且这个节点自己组成环形单向链表，其他节点都删掉。
 * 进阶：如果链表节点数为N，想在时间复杂度为O(N)时完成原问题的要求，该怎么实现？
 *
 *
 * 思路：首先了解剃刀函数公式 y=x%i 在坐标系x,y上表现为剃刀样式的函数
 * 1.将n个人从0到n编号 然后将编号和报数对应起来 1-1,2-2,3-3,4-1。。。 这样在以编号为x轴，报数为y轴构成的坐
 * 标系上的图形也是剃刀函数 只是原始剃刀函数向右向上平移后的效果，根据原理左加右减 上加下减，所以编号和报
 * 数的函数式为：报数=(编号-1)%3+1
 * 2.剩下最后的那个人(即要找的按个节点)新编号为1 然后找到一个公式根据新编号推导出2个节点时他的编号，3个节
 * 点时他的编号 依次类推 推导出n个节点时 他的编号是多少，画图分析得出旧编号样式为上边1中公式向左平移s个单
 * 位，所以 旧=(新编号-1+s)%i+1 s=(m-1)%i+1带入进公式 m为间隔，i为当前剩余人数，当i=n时即可求出原来的编号
 * 所以从1递推到n 时间复杂度为O(n)
 *
 *
 */
public class Practice_JosephusProblem {

    public static class Node {
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }

    public static Node josephusKill2(Node head, int m) {
        if (head == null || head.next == head || m < 1) {
            return head;
        }
        Node cur = head.next;
        int tmp = 1; // tmp -> list size
        while (cur != head) {
            tmp++;
            cur = cur.next;
        }
        tmp = getLive(tmp, m); // tmp -> service node position
        while (--tmp != 0) {
            head = head.next;
        }
        head.next = head;
        return head;
    }


    /**
     * 长度为i 报到m就杀人的时候 返回对应的旧编号
     * @param i
     * @param m
     * @return
     */
    public static int getLive(int i, int m) {
        if (i == 1) {
            return 1;
        }
        return (getLive(i - 1, m) + m - 1) % i + 1;
    }

    public static void printCircularList(Node head) {
        if (head == null) {
            return;
        }
        System.out.print("Circular List: " + head.value + " ");
        Node cur = head.next;
        while (cur != head) {
            System.out.print(cur.value + " ");
            cur = cur.next;
        }
        System.out.println("-> " + head.value);
    }

    public static void test(){
        Node head2 = new Node(1);
        head2.next = new Node(2);
        head2.next.next = new Node(3);
        head2.next.next.next = new Node(4);
        head2.next.next.next.next = new Node(5);
        head2.next.next.next.next.next = head2;
        printCircularList(head2);
        head2 = josephusKill2(head2, 3);
        printCircularList(head2);
    }


}
