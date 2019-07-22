package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/21 21:58
 * desc :在二叉树中找到一个节点的后继节点
 *
 * 定义：
 * 在二叉树的中序遍历的序列中， node的下一个节点叫作node的后继节点
 * 在二叉树的中序遍历的序列中， node的上一个节点叫作node的前驱节点
 *
 *后继节点快速定位两种思路：
 * 1.节点x如果有右子树 则他的右子树中最左的孩子为他的后继节点
 * 2.节点x如果没有右子树 则向上移动到他的父节点 判断当前父节点是否是它父父节点的左子树 如果是返回父父节点 不是继续向上
 *
 */
public class TreePractice_SuccessorNode {

    /**
     * 新的二叉树节点
     * 该结构比普通二叉树节点结构多了一个指向父节点的parent指针
     */
    public static class Node {
        public int value;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.value = data;
        }
    }


    /**
     * 给定一个节点返回它的后继节点
     * @param node
     * @return
     */
    public static Node getSuccessorNode(Node node) {
        if (node == null) {
            return node;
        }

        //判断是否有右子树 有的话找到右子树最左的的节点
        if(node.right!=null) {
            return getLeftMost(node.right);
        }else{
            //没有右子树判断父节点是否是父父节点的左子树
            Node parent = node.parent;
            while (parent!=null&&parent.left!=node){
                node=parent;
                parent=node.parent;
            }
            return parent;
        }
    }


    /**
     * 找到给定节点的最左边节点
     * @param node
     * @return
     */
    public static Node getLeftMost(Node node){
        if(node==null){
            return null;
        }

        while (node.left!=null){
            node = node.left;
        }

        return node;
    }




}
