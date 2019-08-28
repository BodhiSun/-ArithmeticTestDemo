package com.bodhi.seniorcourse.practice;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/28 18:25
 * desc :树形dp-公司参加晚会的最大活跃指数
 *
 * 【题目】
 * 一个公司的上下级关系是一棵多叉树，这个公司要举办晚会，你作为组织者已经洞悉了大家心理，如果一个员工的直
 * 接上级到场，这个员工肯定不会来。每个员工都有一个自己的活跃值，决定谁来你会给这个员工发邀请函，怎么能让
 * 晚会的气氛最活跃？返回最大活跃指数。
 *
 * 思路：当前节点x时有两种可能性：1.节点x来参加，2.节点x不来参加
 * 所以要收集的信息是 当前节点为头节点树它本身来参加时的总活跃度 和它本身不来参加时的总活跃度
 *
 *
 *
 */
public class TreePractice_MaxHappy {

    /**
     * 构建多叉树结构
     */
    public static class Node{
        public int active;//活跃度
        public List<Node> nexts;//下级

        public Node(int active){
            this.active=active;
            nexts = new ArrayList<>();
        }
    }

    /**
     * 构建递归的返回类型
     */
    public static class ReturnData{
        int comeAct;//来的活跃度
        int notComeAct;//不来的活跃度

        public ReturnData(int comeAct, int notComeAct) {
            this.comeAct = comeAct;
            this.notComeAct = notComeAct;
        }
    }

    public static int getMaxActive(Node head){
        ReturnData data = process(head);
        return Math.max(data.comeAct,data.notComeAct);
    }

    public static ReturnData process(Node head){
        int comeAct=head.active;//当前节点来参加时总的活跃度
        int notComeAct =0;//当前节点不来参加时总的活跃度

        //一个领导对应多个下属
        for (int i = 0; i < head.nexts.size(); i++) {
            //假设能收集到信息
            ReturnData nextData = process(head.nexts.get(i));

            //当前节点来或不来时的两种可能性
            comeAct += nextData.notComeAct;//当前节点来时 他的下属节点一定不能来
            notComeAct += Math.max(nextData.notComeAct,nextData.comeAct);//当前节点不来 下属可来可不来
        }

        //返回当前节点所在树能构成的活跃最大值
        return new ReturnData(comeAct,notComeAct);
    }

    public static void test(){
        Node head1 = new Node(20);
        List<Node> nexts1 = new ArrayList<>();
        Node headN1 = new Node(20);
        Node headN2 = new Node(20);
        Node headN3 = new Node(20);
        nexts1.add(headN1);
        nexts1.add(headN2);
        nexts1.add(headN3);
        head1.nexts=nexts1;

        List<Node> nexts2 = new ArrayList<>();
        Node headN21 = new Node(10);
        Node headN22 = new Node(10);
        Node headN23 = new Node(10);
        nexts2.add(headN21);
        nexts2.add(headN22);
        nexts2.add(headN23);
        headN1.nexts=nexts2;

        List<Node> nexts3 = new ArrayList<>();
        Node headN31 = new Node(80);
        nexts3.add(headN31);
        headN2.nexts=nexts3;

        List<Node> nexts4 = new ArrayList<>();
        Node headN41 = new Node(40);
        Node headN42 = new Node(40);
        Node headN43 = new Node(40);
        nexts4.add(headN41);
        nexts4.add(headN42);
        nexts4.add(headN43);
        headN3.nexts=nexts4;

        System.out.println(getMaxActive(head1));
    }




}
