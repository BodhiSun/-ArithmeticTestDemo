package com.bodhi.arithmetictestdemo.practice;

import java.util.Stack;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/17 19:14
 * desc :实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返 回栈中最小元素的操作。
 *  要求：
 *  1．pop、push、getMin操作的时间复杂度都是O(1)。
 *  2．设计的栈类型可以使用现成的栈结构。
 *
 *  思路设计两个栈 一个正常压入数据 另一个维持最小的值
 */
public class HeapPractice_GetMinStack {

    public static class MyStack2{
        //直接用系统提供的栈结构
        private Stack<Integer> stackData;//正常存数据的栈
        private Stack<Integer> stackMin;//维持最小值的栈

        public MyStack2(){
            this.stackData=new Stack<Integer>();
            this.stackMin=new Stack<Integer>();
        }

        /**
         * 压栈
         * @param newNum 要压入进来的新数
         */
        public void push(int newNum){
            //当有一个新的数压入时 如果两个栈都为空 则分别填入两个栈中
            if(this.stackMin.isEmpty()){
                this.stackMin.push(newNum);
            }else if(newNum<getMin()){//如果min栈不为空时 并且当前数比min栈的栈顶数小 则还是把当前数分别压入两个栈
                this.stackMin.push(newNum);
            }else{ //如果min栈不为空时 并且当前数比min栈的栈顶数大 则还是把当前数压入data栈 把min栈栈顶重复压入min栈
                int newMin =this.stackMin.peek();
                this.stackMin.push(newMin);
            }

            this.stackData.push(newNum);
        }

        /**
         * 出栈
         * @return
         */
        public Integer pop(){
            if(this.stackData.isEmpty()){
                throw new RuntimeException("you stack is empty");
            }

            //min栈和data栈同步维持压入和弹出操作
            this.stackMin.pop();

            return this.stackData.pop();
        }

        /**
         * 返回当前栈中数据的最小值
         * @return
         */
        private int getMin() {
            if(this.stackMin.isEmpty()){
                throw new RuntimeException("your stack is empty");
            }
            //查询栈顶的数据
            return this.stackMin.peek();
        }

    }
}
