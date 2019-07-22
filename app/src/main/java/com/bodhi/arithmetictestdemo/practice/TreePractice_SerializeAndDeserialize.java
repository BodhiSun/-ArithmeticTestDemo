package com.bodhi.arithmetictestdemo.practice;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/22 11:05
 * desc :二叉树的序列化和反序列化
 * 序列化：按先序遍历依次遍历出每个节点 结果为节点值+!表示 !表示终止 叶子节点用“#!_#!”表示叶子节点的左右两个节点为空
 *         最后以string的形式保存 即为序列化
 * 反序列化：将保存的string重建出来
 *
 * 第二种方式:按层序列化
 *
 */
public class TreePractice_SerializeAndDeserialize {
    public static class Node {
        public int value;
        public Node left;
        public Node right;

        public Node(int data) {
            this.value = data;
        }
    }

    /**
     * 序列化二叉树
     * @param head
     * @return
     */
    public static String serialByPre(Node head){
        if(head==null){
            return "#!";
        }
        String res =head.value+"!";
        res+=serialByPre(head.left);
        res+=serialByPre(head.right);
        return res;
    }

    /**
     * 反序列化二叉树-重建二叉树
     * @param preStr
     * @return
     */
    public static Node reconByPreString(String preStr){
        //因为遍历序列化时用!做终止符 所以重建时用它分割节点
        String[] values = preStr.split("!");
        //用队列存放所有节点 方便取数时并及时去除掉构建完的节点
        Queue<String> queue=new LinkedList<String>();
        for (int i = 0; i!= values.length; i++) {
            queue.offer(values[i]);
        }
        return reconPreOrder(queue);
    }

    private static Node reconPreOrder(Queue<String> queue) {
        String value = queue.poll();
        if(value.equals("#")){
            return null;
        }
        Node head = new Node(Integer.valueOf(value));
        head.left = reconPreOrder(queue);
        head.right=reconPreOrder(queue);
        return head;
    }


    /**
     * 按层序列化
     * @param head
     * @return
     */
    public static String serialByLevel(Node head){
        if(head==null){
            return "#!";
        }

        String res = head.value+"!";
        Queue<Node> queue = new LinkedList<Node>();
        queue.offer(head);
        while (!queue.isEmpty()){
            head = queue.poll();
            if(head.left!=null){
                res+=head.left.value+"!";
                queue.offer(head.left);
            }else{
                res +="#!";
            }

            if(head.right!=null){
                res+=head.right.value+"!";
                queue.offer(head.right);
            }else{
                res+="#!";
            }
        }
        return res;
    }


    /**
     * 按层反序列化
     * @param levelStr
     * @return
     */
    public static Node reconByLevelString(String levelStr){
        String[] values = levelStr.split("!");
        int index = 0;
        Node head = generateNodeByString(values[index++]);
        Queue<Node> queue = new LinkedList<Node>();
        if(head!=null){
            queue.offer(head);
        }
        Node node = null;
        while (!queue.isEmpty()){
            node=queue.poll();
            node.left = generateNodeByString(values[index++]);
            node.right=generateNodeByString(values[index++]);
            if(node.left!=null){
                queue.offer(node.left);
            }
            if(node.right!=null){
                queue.offer(node.right);
            }
        }
        return head;
    }

    private static Node generateNodeByString(String value) {
        if(value.equals("#")){
            return null;
        }
        return new Node(Integer.valueOf(value));
    }

    public static void printTree(Node head){
        System.out.println("Binary Tree:");
        printInOrder(head,0,"H",17);
        System.out.println();
    }

    public static void printInOrder(Node head,int height,String to ,int len){
        if(head==null){
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

    public static void test(){
        Node head = null;
        printTree(head);

        String pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        String level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);

        System.out.println("====================================");


        head = new Node(1);
        head.left = new Node(2);
        head.right = new Node(3);
        head.left.left = new Node(4);
        head.left.right = new Node(5);
        head.right.left = new Node(6);
        head.right.right = new Node(7);
        head.left.left.left = new Node(8);
        head.left.left.right = new Node(9);
        head.left.right.left = new Node(10);
        head.left.right.right = new Node(11);
        printTree(head);

        pre = serialByPre(head);
        System.out.println("serialize tree by pre-order: " + pre);
        head = reconByPreString(pre);
        System.out.print("reconstruct tree by pre-order, ");
        printTree(head);

        level = serialByLevel(head);
        System.out.println("serialize tree by level: " + level);
        head = reconByLevelString(level);
        System.out.print("reconstruct tree by level, ");
        printTree(head);


    }


}
