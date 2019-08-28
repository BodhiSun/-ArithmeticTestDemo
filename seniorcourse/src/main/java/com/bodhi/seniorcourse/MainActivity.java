package com.bodhi.seniorcourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bodhi.seniorcourse.practice.BfprtPractice_MinKNums;
import com.bodhi.seniorcourse.practice.KmpPractice_ShortestHaveTwice;
import com.bodhi.seniorcourse.practice.KmpPractice_Tree1SubEqualTree2;
import com.bodhi.seniorcourse.practice.ManacherPractice_ShortestEnd;
import com.bodhi.seniorcourse.practice.Practice_LongestSumSubArray;
import com.bodhi.seniorcourse.practice.Practice_MostEorNum;
import com.bodhi.seniorcourse.practice.SingleStackP_MaxPeakPairNum;
import com.bodhi.seniorcourse.practice.SingleStackP_MaxRecAreaFromArr;
import com.bodhi.seniorcourse.practice.SingleStackP_MaxSubMatrixNum;
import com.bodhi.seniorcourse.practice.Practice_Building_Outline;
import com.bodhi.seniorcourse.practice.TreePractice_LargestSubBS;
import com.bodhi.seniorcourse.practice.TreePractice_MaxAndMinNum;
import com.bodhi.seniorcourse.practice.TreePractice_MaxDistance;
import com.bodhi.seniorcourse.practice.TreePractice_MorrisTraversal;
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
    }

    private void testMaxMinNumOfTree() {
        System.out.println("------高度解答套路三部曲-------");
        System.out.println("--1.返回二叉树中的最大值和最小值-------");
        TreePractice_MaxAndMinNum.test();
        System.out.println("--2.最大搜索二叉子树的大小-------");
        TreePractice_LargestSubBS.test();
        System.out.println("--3.二叉树中最大距离-------");
        TreePractice_MaxDistance.test();
    }

    private void testLongestSumSubArray() {
        System.out.println("------累加和最长的子数组--最多异或和为0子数组-----");
        Practice_LongestSumSubArray.test();
        Practice_MostEorNum.test();
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
