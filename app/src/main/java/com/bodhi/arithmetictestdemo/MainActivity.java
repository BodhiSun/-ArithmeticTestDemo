package com.bodhi.arithmetictestdemo;

import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseArray;
import android.widget.TextView;

import com.bodhi.arithmetictestdemo.bean.Student;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_CopyListWithRandom;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_FindFirstIntersectNode;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_PrintCommonPart;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_ReverseList;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_SmallerEqualBigger;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_isPalindrome;
import com.bodhi.arithmetictestdemo.practice.MatrixPractice_FindNum;
import com.bodhi.arithmetictestdemo.practice.MatrixPractice_RotateMatrix;
import com.bodhi.arithmetictestdemo.practice.MatrixPractice_RotatePrint;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_ReverseList.Node;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_ReverseList.DoubleNode;
import com.bodhi.arithmetictestdemo.practice.MatrixPractice_ZigZagPrintMatrix;
import com.bodhi.arithmetictestdemo.practice.LinkListPractice_PrintCommonPart.CusNode;
import com.bodhi.arithmetictestdemo.practice.Practice_PaperFolding;
import com.bodhi.arithmetictestdemo.practice.TreePractice_CompleteTreeNodeNumber;
import com.bodhi.arithmetictestdemo.practice.TreePractice_IsBSTAndCBT;
import com.bodhi.arithmetictestdemo.practice.TreePractice_IsBalancedTree;
import com.bodhi.arithmetictestdemo.practice.TreePractice_PreInPosTraversal;
import com.bodhi.arithmetictestdemo.practice.TreePractice_PrintBinaryTree;
import com.bodhi.arithmetictestdemo.practice.TreePractice_SerializeAndDeserialize;
import com.bodhi.arithmetictestdemo.practice.TreePractice_TrieTree;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MainActivity extends AppCompatActivity {
    private TextView tv_sort_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_sort_result = findViewById(R.id.tv_sort_result);

//        testBubbleSort();
//        testSelectionSort();
//        testInsertionSort();
//        testMergeSort();
//        testQuickSort();
//        testHeapSort();
//        testBucketSort();

//        testRotatePrintMatrix();
//        testRotateMatrix();
//        testLinkedList();
//        testZigZagPrintMatrix();
//        testFindNumInSortMatrix();
//        testPrintCommonPart();
//        testLinkedListIsPalindrome();
//        testLinkedSmallerEqualBigger();
//        testLinkedCopyListWithRand();
//        testLinkedFindIntersectNode();
//          testPreInPosTraversal();
//        testPrintBinaryTree();
//        testSerialAndReconsTree();

//        testPaperFold();
//        testIsBalancedTree();
//        testIsBSTAndCBT();
//          testCompleteTreeNodeNum();
          testTrieTree();


        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.N) {
            //系统自带的对结构
            PriorityQueue<Student> heap = new PriorityQueue(new IdAscendingCompator());//优先级队列就是堆结构
            Student student2 = new Student("小舞", 16, "女");
            Student student = new Student("小三", 12, "男");
            Student student3 = new Student("公子", 300, "男");
            heap.add(student2);
            heap.add(student);
            heap.add(student3);

            while (!heap.isEmpty()) {
                Student poll = heap.poll();//poll方法每次弹出头部 堆大小减1
                System.out.println(poll.toString());
            }
        }

    }

    private void testTrieTree() {
        TreePractice_TrieTree.test();
    }

    private void testCompleteTreeNodeNum() {
        TreePractice_CompleteTreeNodeNumber.test();
    }

    private void testIsBSTAndCBT() {
        TreePractice_IsBSTAndCBT.test();
    }

    private void testIsBalancedTree() {
        TreePractice_IsBalancedTree.test();
    }

    private void testPaperFold() {
        Practice_PaperFolding.printAllFolds(1);
        System.out.println("=======================");
        Practice_PaperFolding.printAllFolds(2);
        System.out.println("=======================");
        Practice_PaperFolding.printAllFolds(3);
    }

    private void testSerialAndReconsTree() {
        TreePractice_SerializeAndDeserialize.test();
    }

    private void testPrintBinaryTree() {
        TreePractice_PrintBinaryTree.test();
    }

    private void testPreInPosTraversal() {
        TreePractice_PreInPosTraversal.test();
    }

    private void testLinkedFindIntersectNode() {
        LinkListPractice_FindFirstIntersectNode.test();
    }

    private void testLinkedCopyListWithRand() {
        LinkListPractice_CopyListWithRandom.test();
    }

    private void testLinkedSmallerEqualBigger() {
        LinkListPractice_SmallerEqualBigger.test();
    }

    private void testLinkedListIsPalindrome() {
        LinkListPractice_isPalindrome.linkedListIsPalindrome();
    }

    private void testPrintCommonPart() {
        CusNode node1 = new CusNode(2);
        node1.next = new CusNode(3);
        node1.next.next = new CusNode(5);
        node1.next.next.next = new CusNode(6);

        CusNode node2 = new CusNode(1);
        node2.next = new CusNode(2);
        node2.next.next = new CusNode(5);
        node2.next.next.next = new CusNode(7);
        node2.next.next.next.next = new CusNode(8);

        LinkListPractice_PrintCommonPart.printLinkedList(node1);
        LinkListPractice_PrintCommonPart.printLinkedList(node2);
        LinkListPractice_PrintCommonPart.printCommonPart(node1, node2);
    }

    private void testFindNumInSortMatrix() {
        int[][] matrix = new int[][]{{0, 1, 2, 3, 4, 5, 6},// 0
                {10, 12, 13, 15, 16, 17, 18},// 1
                {23, 24, 25, 26, 27, 28, 29},// 2
                {44, 45, 46, 47, 48, 49, 50},// 3
                {65, 66, 67, 68, 69, 70, 71},// 4
                {96, 97, 98, 99, 100, 111, 122},// 5
                {166, 176, 186, 187, 190, 195, 200},// 6
                {233, 243, 321, 341, 356, 370, 380} // 7
        };
        int K = 233;
        System.out.println(MatrixPractice_FindNum.isContains(matrix, K) + "");
    }

    private void testZigZagPrintMatrix() {
        int[][] matrix = new int[][]{{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};
        MatrixPractice_ZigZagPrintMatrix.printMatrixZigZag(matrix);
    }

    private void testLinkedList() {
        //构造单向链表
        Node head1 = new Node(1);
        head1.next = new Node(2);
        head1.next.next = new Node(3);

        LinkListPractice_ReverseList.printLinkedList(head1);
        head1 = LinkListPractice_ReverseList.reverseLsit(head1);
        LinkListPractice_ReverseList.printLinkedList(head1);

        //构造双向链表
        DoubleNode head2 = new DoubleNode(1);
        head2.next = new DoubleNode(2);
        head2.next.last = head2;
        head2.next.next = new DoubleNode(3);
        head2.next.next.last = head2.next;
        head2.next.next.next = new DoubleNode(4);
        head2.next.next.next.last = head2.next.next;

        LinkListPractice_ReverseList.printDoubleLinkedList(head2);
        LinkListPractice_ReverseList.printDoubleLinkedList(LinkListPractice_ReverseList.reverseList(head2));

    }

    private void testRotateMatrix() {
        int[][] arr = new int[4][4];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (i + 1) * 10 + j + 1;
            }
        }
        MatrixPractice_RotateMatrix.printMatrix(arr);
        MatrixPractice_RotateMatrix.rotate(arr);
        MatrixPractice_RotateMatrix.printMatrix(arr);
    }

    private void testRotatePrintMatrix() {
        int[][] arr = new int[3][4];
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[0].length; j++) {
                arr[i][j] = (i + 1) * 10 + j + 1;
            }
        }
        MatrixPractice_RotatePrint.rotatePrint(arr);
    }

    private void testBubbleSort() {
        Integer[] arr1 = new Integer[]{93, 26, 101, 1, 66, 101, 35, 303, 13};
        Integer[] arr2 = new Integer[]{12};
        Integer[] arr3 = new Integer[]{93, 26, 101, 7, 11, 66, 101, 35, 303, 101, 1, 5, 2, 7, 11};
        SortUtil.bubbleSort(arr1);
        SortUtil.bubbleSort(arr2);
        SortUtil.bubbleSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString() + "\n" + Arrays.asList(arr2).toString() + "\n" + Arrays.asList(arr3).toString() + "\n");

    }

    private void testSelectionSort() {
        Integer[] arr1 = new Integer[]{93, 26, 101, 1, 66, 101, 35, 303, 13};
        Integer[] arr2 = new Integer[]{12};
        Integer[] arr3 = new Integer[]{93, 26, 101, 7, 11, 66, 101, 35, 303, 101, 1, 5, 2, 7, 11};
        SortUtil.selectSort(arr1);
        SortUtil.selectSort(arr2);
        SortUtil.selectSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString() + "\n" + Arrays.asList(arr2).toString() + "\n" + Arrays.asList(arr3).toString() + "\n");

    }

    private void testInsertionSort() {
        Integer[] arr1 = new Integer[]{93, 26, 101, 1, 66, 101, 35, 303, 13};
        Integer[] arr2 = new Integer[]{12};
        Integer[] arr3 = new Integer[]{93, 26, 101, 7, 11, 66, 101, 35, 303, 101, 1, 5, 2, 7, 11};
        SortUtil.insertionSort(arr1);
        SortUtil.insertionSort(arr2);
        SortUtil.insertionSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString() + "\n" + Arrays.asList(arr2).toString() + "\n" + Arrays.asList(arr3).toString() + "\n");

    }

    private void testMergeSort() {
        Integer[] arr1 = new Integer[]{93, 26, 101, 1, 66, 101, 35, 303, 13};
        Integer[] arr2 = new Integer[]{12};
        Integer[] arr3 = new Integer[]{93, 26, 101, 7, 11, 66, 101, 35, 303, 101, 1, 5, 2, 7, 11};
        SortUtil.mergeSort(arr1);
//        SortUtil.mergeSort(arr2);
//        SortUtil.mergeSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString() + "\n" + Arrays.asList(arr2).toString() + "\n" + Arrays.asList(arr3).toString() + "\n");

    }


    private void testQuickSort() {
        Integer[] arr1 = new Integer[]{93, 26, 101, 1, 66, 101, 35, 303, 13};
        Integer[] arr2 = new Integer[]{12};
        Integer[] arr3 = new Integer[]{93, 26, 101, 7, 11, 66, 101, 35, 303, 101, 1, 5, 2, 7, 11};
        SortUtil.quickSort(arr1);
        SortUtil.quickSort(arr2);
        SortUtil.quickSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString() + "\n" + Arrays.asList(arr2).toString() + "\n" + Arrays.asList(arr3).toString() + "\n");
    }

    private void testHeapSort() {
        Integer[] arr1 = new Integer[]{93, 26, 101, 1, 66, 101, 35, 303, 13};
        Integer[] arr2 = new Integer[]{12};
        Integer[] arr3 = new Integer[]{93, 26, 101, 7, 11, 66, 101, 35, 303, 101, 1, 5, 2, 7, 11};
        SortUtil.heapSort(arr1);
        SortUtil.heapSort(arr2);
        SortUtil.heapSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString() + "\n" + Arrays.asList(arr2).toString() + "\n" + Arrays.asList(arr3).toString() + "\n");


    }

    private void testBucketSort() {
        Integer[] arr1 = new Integer[]{93, 26, 101, 1, 66, 101, 35, 183, 13};
        Integer[] arr2 = new Integer[]{12};
        Integer[] arr3 = new Integer[]{93, 26, 101, 7, 11, 66, 101, 35, 183, 101, 1, 5, 2, 7, 11};
        SortUtil2.bucketSort(arr1);
        SortUtil2.bucketSort(arr2);
        SortUtil2.bucketSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString() + "\n" + Arrays.asList(arr2).toString() + "\n" + Arrays.asList(arr3).toString() + "\n");


    }
}
