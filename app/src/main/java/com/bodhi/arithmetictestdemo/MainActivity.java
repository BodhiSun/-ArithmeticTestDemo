package com.bodhi.arithmetictestdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        testHeapSort();

    }



    private void testBubbleSort() {
        Integer[] arr1=new Integer[]{93,26,101,1,66,101,35,303,13};
        Integer[] arr2=new Integer[]{12};
        Integer[] arr3=new Integer[]{93,26,101,7,11,66,101,35,303,101,1,5,2,7,11};
        SortUtil.bubbleSort(arr1);
        SortUtil.bubbleSort(arr2);
        SortUtil.bubbleSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString()+"\n"+Arrays.asList(arr2).toString()+"\n"+Arrays.asList(arr3).toString()+"\n");

    }

    private void testSelectionSort() {
        Integer[] arr1=new Integer[]{93,26,101,1,66,101,35,303,13};
        Integer[] arr2=new Integer[]{12};
        Integer[] arr3=new Integer[]{93,26,101,7,11,66,101,35,303,101,1,5,2,7,11};
        SortUtil.selectSort(arr1);
        SortUtil.selectSort(arr2);
        SortUtil.selectSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString()+"\n"+Arrays.asList(arr2).toString()+"\n"+Arrays.asList(arr3).toString()+"\n");

    }

    private void testInsertionSort() {
        Integer[] arr1=new Integer[]{93,26,101,1,66,101,35,303,13};
        Integer[] arr2=new Integer[]{12};
        Integer[] arr3=new Integer[]{93,26,101,7,11,66,101,35,303,101,1,5,2,7,11};
        SortUtil.insertionSort(arr1);
        SortUtil.insertionSort(arr2);
        SortUtil.insertionSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString()+"\n"+Arrays.asList(arr2).toString()+"\n"+Arrays.asList(arr3).toString()+"\n");

    }

    private void testMergeSort() {
        Integer[] arr1=new Integer[]{93,26,101,1,66,101,35,303,13};
        Integer[] arr2=new Integer[]{12};
        Integer[] arr3=new Integer[]{93,26,101,7,11,66,101,35,303,101,1,5,2,7,11};
        SortUtil.mergeSort(arr1);
//        SortUtil.mergeSort(arr2);
//        SortUtil.mergeSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString()+"\n"+Arrays.asList(arr2).toString()+"\n"+Arrays.asList(arr3).toString()+"\n");

    }


    private void testQuickSort() {
        Integer[] arr1=new Integer[]{93,26,101,1,66,101,35,303,13};
        Integer[] arr2=new Integer[]{12};
        Integer[] arr3=new Integer[]{93,26,101,7,11,66,101,35,303,101,1,5,2,7,11};
        SortUtil.quickSort(arr1);
        SortUtil.quickSort(arr2);
        SortUtil.quickSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString()+"\n"+Arrays.asList(arr2).toString()+"\n"+Arrays.asList(arr3).toString()+"\n");
    }

    private void testHeapSort() {
        Integer[] arr1=new Integer[]{93,26,101,1,66,101,35,303,13};
        Integer[] arr2=new Integer[]{12};
        Integer[] arr3=new Integer[]{93,26,101,7,11,66,101,35,303,101,1,5,2,7,11};
        SortUtil.heapSort(arr1);
        SortUtil.heapSort(arr2);
        SortUtil.heapSort(arr3);

        tv_sort_result.setText(Arrays.asList(arr1).toString()+"\n"+Arrays.asList(arr2).toString()+"\n"+Arrays.asList(arr3).toString()+"\n");


    }
}
