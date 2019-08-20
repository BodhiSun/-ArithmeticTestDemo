package com.bodhi.seniorcourse;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bodhi.seniorcourse.practice.KmpPractice_ShortestHaveTwice;
import com.bodhi.seniorcourse.practice.KmpPractice_Tree1SubEqualTree2;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        testKMP();
        testManacher();

    }

    private void testManacher() {
        System.out.println("------Manacher-------");
        Manacher_Arithmetic.test();
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
