package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/23 18:00
 * desc :二叉树-搜索二叉树 以及搜索二叉树的查找、插入、删除
 *
 * 搜索二叉树概念：对于树中的任何一个节点为头的子树 左子树都比它小 右子树都比它大，单独一个节点也符合
 *
 * 几种常见的平衡搜索二叉树：CRUD时间复杂度：O(logN)
 * 1)AVL树：平衡性高度严苛的树 即对于树中的任何一个节点 它的左子树和右子树的高度差不超过1
 * 2)红黑树：平衡性舍掉一些 因为每条链上的黑色节点数目必须相同 所以最坏情况两条链之间高度差不会超过两倍
 * 平衡性越高插入节点时调整树的可能性越大 平衡性低些插入节点时调整的可能性就小些 但是又要满足CRUD为O(logN)
 * 即要保证效率又要尽可能减小调整树的可能性 所以各种树的结构以及约束条件 都是在平衡性上的取舍 严苛度不同而已
 *
 *
 *
 */
public class TreePractice_BS_CRUD {
}