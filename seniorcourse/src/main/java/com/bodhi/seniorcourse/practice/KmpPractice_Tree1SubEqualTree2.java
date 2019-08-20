package com.bodhi.seniorcourse.practice;


/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/20 12:54
 * desc :有两棵树T1，T2,求T1中某一颗子树是否包含T2
 * 子树：从某一点作为头结点开始 包括下面的所有子节点 即为子树
 *
 * 思路：
 * 将T1，T2序列化成字符串 即前序遍历两棵树 #_为空节点 然后判断字符串S2是否是S1的子串即可
 *
 *
 */
public class KmpPractice_Tree1SubEqualTree2 {

    public static class Node{
        public int value;
        public Node left;
        public Node right;

        public Node(int data){
            this.value=data;
        }
    }

    public static boolean isSubtree(Node t1,Node t2){
        String t1Str = serialByPre(t1);
        String t2Str =serialByPre(t2);
        //kmp判断是否为子串
        return getIndexOf(t1Str,t2Str)!=-1;
    }

    /**
     * 将给定的树先序遍历序列化成字符串 !表示终止 #!表示空节点
     * @param head
     * @return
     */
    private static String serialByPre(Node head) {
        if(head==null){
            return "#!";
        }

        String res = head.value+"!";
        res+=serialByPre(head.left);
        res+=serialByPre(head.right);
        return res;
    }


    /**
     * KMP算法
     * 求字符串str2 在字符串str1中的开始位置
     *
     * @param s
     * @param m
     * @return
     */
    public static int getIndexOf(String s, String m) {
        if(s==null||m==null||m.length()<1||s.length()<m.length()){
            return -1;
        }

        char[] ss = s.toCharArray();
        char[] ms = m.toCharArray();
        int[] nextArr = getNextArray(ms);
        int index=0;//s中当前比较的位置 指针
        int mi=0;//m中当前比较的位置 指针
        while (index<ss.length&&mi<ms.length){
            //匹配上的时候 指针都跳下一个
            if(ss[index]==ms[mi]){
                index++;
                mi++;
            }else if (nextArr[mi]==-1){//没有匹配上时 并且如果是m起始位置
                index++;
            }else{
                //没有匹配上时 如果不是第一个字符 则跳到当前字符的最大前后缀位置
                mi=nextArr[mi];
            }
        }

        //如果m中的指针 成功滑到了末尾 说明s也同时滑过m即包含m
        return mi==ms.length?index-mi:-1;
    }

    /**
     * KMP中next数组的求解
     * 求每个字符的最大相等前后缀长度值
     * @param ms
     * @return
     */
    public static int[] getNextArray(char[] ms) {
        if(ms.length==1){
            return new int[]{-1};
        }

        //构建数组 并且人为规定前两个位置的值
        int[] nextArr=new int[ms.length];
        nextArr[0] = -1;
        nextArr[1]=0;

        int pos =2;//前带求的位置
        int cn =0;//初始为当前带求位置的前一个数的最大前后缀长度
        while (pos<nextArr.length){
            if(ms[pos-1]==ms[cn]){
                nextArr[pos++] =++cn;
            }else if(cn>0){
                cn=nextArr[cn];
            }else{
                nextArr[pos++]=0;
            }
        }
        return nextArr;
    }

    public static void test(){
        Node t1 = new Node(1);
        t1.left=new Node(2);
        t1.right=new Node(3);
        t1.left.left = new Node(4);
        t1.left.right = new Node(5);
        t1.right.left = new Node(6);
        t1.right.right = new Node(7);
        t1.left.left.right = new Node(8);
        t1.left.right.left = new Node(9);

        Node t2 = new Node(2);
        t2.left = new Node(4);
        t2.left.right = new Node(8);
        t2.right = new Node(5);
        t2.right.left = new Node(9);

        System.out.println(isSubtree(t1, t2));

    }


}


