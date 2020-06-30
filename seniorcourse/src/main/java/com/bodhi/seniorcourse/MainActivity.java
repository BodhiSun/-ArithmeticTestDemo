package com.bodhi.seniorcourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bodhi.seniorcourse.practice.BfprtPractice_MinKNums;
import com.bodhi.seniorcourse.practice.KmpPractice_ShortestHaveTwice;
import com.bodhi.seniorcourse.practice.KmpPractice_Tree1SubEqualTree2;
import com.bodhi.seniorcourse.practice.ManacherPractice_ShortestEnd;
import com.bodhi.seniorcourse.practice.Practice_FormulaCompute;
import com.bodhi.seniorcourse.practice.LinkListPractice_JosephusProblem;
import com.bodhi.seniorcourse.practice.Practice_LongestSumSubArray;
import com.bodhi.seniorcourse.practice.Practice_LongestSumSubArray2;
import com.bodhi.seniorcourse.practice.Practice_LongestSumSubArray3;
import com.bodhi.seniorcourse.practice.Practice_MaxEORSum;
import com.bodhi.seniorcourse.practice.Practice_MostEorNum;
import com.bodhi.seniorcourse.practice.SingleStackP_MaxPeakPairNum;
import com.bodhi.seniorcourse.practice.SingleStackP_MaxRecAreaFromArr;
import com.bodhi.seniorcourse.practice.SingleStackP_MaxSubMatrixNum;
import com.bodhi.seniorcourse.practice.Practice_Building_Outline;
import com.bodhi.seniorcourse.practice.TreePractice_LargestSubBS;
import com.bodhi.seniorcourse.practice.TreePractice_MaxAndMinNum;
import com.bodhi.seniorcourse.practice.TreePractice_MaxDistance;
import com.bodhi.seniorcourse.practice.TreePractice_MaxHappy;
import com.bodhi.seniorcourse.practice.TreePractice_MorrisTraversal;
import com.bodhi.seniorcourse.practice.ViolenceRecursiveDP_PokerGame;
import com.bodhi.seniorcourse.practice.ViolenceRecursiveDP_RegularMatch;
import com.bodhi.seniorcourse.practice.ViolenceRecursiveDP_RobotWalk;
import com.bodhi.seniorcourse.practice.ViolenceRecursiveDP_TransMoney;
import com.bodhi.seniorcourse.practice.WindowPractice_MinEqualNumSubArr;
import com.bodhi.seniorcourse.practice.WindowPractice_SlidWinMaxNumArr;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testKMP();
        testManacher();
        testBFPRT();
        testWindow();
        testSingleStack();
        testMorrisTraversal();
        testBalanceBinarySearchTree();
        testLongestSumSubArray();
        testMaxMinNumOfTree();
        testLruCache();
        testLfuCache();
        testFormulaCompute();
        testSkipListStructure();
        testMaxEorSum();
        testTransMoneyNum();
        testJosephusProblem();
        testRegularMatch();
    }

    private void testRegularMatch() {
        System.out.println("-----VR->DP 字符串正则匹配问题-----");
        ViolenceRecursiveDP_RegularMatch.test();
    }

    private void testJosephusProblem() {
        System.out.println("-----环形单链表的约瑟夫问题-----");
        LinkListPractice_JosephusProblem.test();
    }

    private void testTransMoneyNum() {
        System.out.println("------高度解答套路的暴力递归到动态规划-------");
        System.out.println("-----换钱的方法数-----");
        ViolenceRecursiveDP_TransMoney.test();
        System.out.println("-----纸牌获得最大分数-----");
        ViolenceRecursiveDP_PokerGame.test();
        System.out.println("-----机器人多少个路径-----");
        ViolenceRecursiveDP_RobotWalk.test();
    }

    private void testMaxEorSum() {
        System.out.println("-----数组最大异或和-----");
        Practice_MaxEORSum.test();
    }

    private void testSkipListStructure() {
        System.out.println("------跳表结构-----");
        SkipList_Structure.test();
    }

    private void testFormulaCompute() {
        System.out.println("------字符串公式的计算结果-----");
        Practice_FormulaCompute.test();
    }

    private void testLfuCache() {
        System.out.println("------LfuCache-------");
        LfuCache_Structure.test();
    }

    private void testLruCache() {
        System.out.println("------LruCache-------");
        LruCache_Structure.test();
    }

    private void testMaxMinNumOfTree() {
        System.out.println("------高度解答套路的树形dp四部曲-------");
        System.out.println("--1.返回二叉树中的最大值和最小值-------");
        TreePractice_MaxAndMinNum.test();
        System.out.println("--2.最大搜索二叉子树的大小-------");
        TreePractice_LargestSubBS.test();
        System.out.println("--3.二叉树中最大距离-------");
        TreePractice_MaxDistance.test();
        System.out.println("--4.公司参加晚会的最大活跃指数-------");
        TreePractice_MaxHappy.test();
    }

    private void testLongestSumSubArray() {
        System.out.println("------累加和最长的子数组--最多异或和为0子数组-----");
        Practice_LongestSumSubArray.test();
        Practice_MostEorNum.test();
        System.out.println("------累加和最长的子数组-只有正数-----");
        Practice_LongestSumSubArray2.test();
        System.out.println("------累加和小于等于最长子数组-----");
        Practice_LongestSumSubArray3.test();
    }

    private void testBalanceBinarySearchTree() {
        System.out.println("------BalanceBinarySearchTree-------");
        Practice_Building_Outline.test();
    }

    private void testMorrisTraversal() {
        System.out.println("------MorrisTraversal-------");
        TreePractice_MorrisTraversal.test();
    }

    private void testSingleStack() {
        System.out.println("------SingleStack-------");
        SingleStackP_MaxRecAreaFromArr.test();
        System.out.println("-------------");
        SingleStackP_MaxSubMatrixNum.test();
        System.out.println("-------------");
        SingleStackP_MaxPeakPairNum.test();
    }

    private void testWindow() {
        System.out.println("------Window-------");
        WindowPractice_SlidWinMaxNumArr.test();
        System.out.println("-------------");
        WindowPractice_MinEqualNumSubArr.test();
    }

    private void testBFPRT() {
        System.out.println("------BFPRT-------");
        BFPRT_Arithmetic.test();
        System.out.println("-------------");
        BfprtPractice_MinKNums.test();
    }

    private void testManacher() {
        System.out.println("------Manacher-------");
        Manacher_Arithmetic.test();
        System.out.println("-------------");
        ManacherPractice_ShortestEnd.test();

    }

    private void testKMP() {
        System.out.println("------KMP-------");
        KMP_Arithmetic.test();
        System.out.println("-------------");
        KmpPractice_ShortestHaveTwice.test();
        System.out.println("-------------");
        KmpPractice_Tree1SubEqualTree2.test();
    }
}
