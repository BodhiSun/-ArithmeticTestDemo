package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/23 14:45
 * desc :二叉树-Morris遍历
 * 相对传统的二叉树遍历O(h)的额外空间复杂度 Morris遍历的额外空间复杂度为O(1)常数时间
 * 时间复杂度:O(n)
 *
 * 实现思路：利用原二叉树中叶子节点的左右空节点 这些空余空间 即改变原来二叉树的结构来实现
 *
 * Morris实现思路：
 * 1)，当前遍历的节点位置记为cur，如果cur没有左孩子 cur向右移动 cur=cur.right
 * 2)，如果cur有左孩子 找到cur左子树上的最右节点的位置记为mostright
 * 如果mostright的右指针指向空 让其右指针指向cur cur向左移动 cur=cur.left
 * 如果mostright的右指针指向cur 让其右指针指回空 cur向右移动 cur=cur.right
 * 3)，当cur指向的节点 即没有左孩子 也没有右孩子时 cur来到了最后的节点整个遍历过程结束
 *
 *
 * Morris遍历的性质:
 * 如果一个节点有左子树 那么一定会到达此节点两次 并且第二次到达时 此节点的左子树一定遍历完了
 * 如果一个节点没有左子树 那么一定会到达此节点一次
 *
 *
 */
public class TreePractice_MorrisTraversal {

    public static class Node{
        int value;
        Node left;
        Node right;

        public Node(int value){
            this.value=value;
        }
    }

    /**
     * morris先序遍历
     * @param head
     */
    public static void morrisPre(Node head){
        if(head==null){
            return ;
        }
        //初始从头节点开始遍历 左子树最右节点为null
        Node cur = head;
        Node mostRight = null;
        while (cur!=null){
            mostRight=cur.left;
            //当前cur的左孩子不为空时判断左子树最右节点
            if(mostRight!=null){
                //找到当前节点左子树上的最右节点
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight = mostRight.right;
                }

                //如果最右节点的右指针为空 让其右指针指向cur cur向左移动
                if(mostRight.right==null){
                    mostRight.right=cur;
                    System.out.print(cur.value+" ");
                    cur=cur.left;

                    //终止本次循环过程 继续下一次循环
                    continue;
                }else{
                    //如果最右节点的右指针不为空 一定是指向cur (第一次到cur节点修改的) 让其右指针指回空
                    mostRight.right=null;
                }
            }else{
                System.out.print(cur.value+" ");
            }

            //当前cur的左孩子为空时或者 最右节点的右指针指向cur cur向右移动
            cur=cur.right;
        }
        System.out.println();
    }


    /**
     * morris中序遍历
     * @param head
     */
    public static void morrisIn(Node head){
        if(head==null){
            return ;
        }
        //初始从头节点开始遍历 左子树最右节点为null
        Node cur = head;
        Node mostRight = null;
        while (cur!=null){
            mostRight=cur.left;
            //当前cur的左孩子不为空时判断左子树最右节点
            if(mostRight!=null){
                //找到当前节点左子树上的最右节点
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight = mostRight.right;
                }

                //如果最右节点的右指针为空 让其右指针指向cur cur向左移动
                if(mostRight.right==null){
                    mostRight.right=cur;
                    cur=cur.left;
                    //终止本次循环过程 继续下一次循环
                    continue;
                }else{
                    //如果最右节点的右指针不为空 一定是指向cur (第一次到cur节点修改的) 让其右指针指回空
                    mostRight.right=null;
                }
            }
            System.out.print(cur.value+" ");

            //当前cur的左孩子为空时或者 最右节点的右指针指向cur cur向右移动
            cur=cur.right;
        }
        System.out.println();
    }


    /**
     * morris后序遍历
     * @param head
     */
    public static void morrisPos(Node head){
        if(head==null){
            return ;
        }
        //初始从头节点开始遍历 左子树最右节点为null
        Node cur = head;
        Node mostRight = null;
        while (cur!=null){
            mostRight=cur.left;
            //当前cur的左孩子不为空时判断左子树最右节点
            if(mostRight!=null){
                //找到当前节点左子树上的最右节点
                while (mostRight.right!=null&&mostRight.right!=cur){
                    mostRight = mostRight.right;
                }

                //如果最右节点的右指针为空 让其右指针指向cur cur向左移动
                if(mostRight.right==null){
                    mostRight.right=cur;
                    cur=cur.left;
                    //终止本次循环过程 继续下一次循环
                    continue;
                }else{
                    //如果最右节点的右指针不为空 一定是指向cur (第一次到cur节点修改的) 让其右指针指回空
                    mostRight.right=null;
                    printEdge(cur.left);
                }
            }
            //当前cur的左孩子为空时或者 最右节点的右指针指向cur cur向右移动
            cur=cur.right;
        }
        printEdge(head);
        System.out.println();
    }

    /**
     * 逆序打印传入节点的整条右边界
     * @param head
     */
    private static void printEdge(Node head) {
        //反转结点右边界 返回反转后的头节点
        Node tail = reverseEdge(head);
        Node cur = tail;
        while (cur!=null){
            System.out.print(cur.value+" ");
            cur=cur.right;
        }
        //打印完后 将节点原来的指向改回去
        reverseEdge(tail);
    }

    /**
     * 将传入节点的整条右边界指向反转 并返回原先的末尾节点
     * @param from
     * @return
     */
    private static Node reverseEdge(Node from) {
        Node pre = null;
        Node next =null;
        while (from!=null){
            next = from.right;
            from.right = pre;
            pre=from;
            from = next;
        }
        return pre;
    }

    public static void  test() {
        Node head = new Node(5);
        head.left = new Node(3);
        head.right = new Node(8);
        head.left.left = new Node(2);
        head.left.right = new Node(4);
        head.left.left.left = new Node(1);
        head.right.left = new Node(7);
        head.right.left.left = new Node(6);
        head.right.right = new Node(10);
        head.right.right.left = new Node(9);
        head.right.right.right = new Node(11);

        System.out.println("morris pre:");
        morrisPre(head);
        System.out.println("morris in:");
        morrisIn(head);
        System.out.println("morris pos:");
        morrisPos(head);
    }


}
