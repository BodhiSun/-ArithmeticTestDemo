package com.bodhi.arithmetictestdemo.practice;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/17 20:44
 * desc :
 * 如何仅用队列结构实现栈结构？
 * 如何仅用栈结构实现队列结构？
 */
public class HeapPractice_StackAndQueueConvert {

    //两个队列实现一个栈
    public static class TwoQueueToStack{
        //直接用系统提供的队列
        private Queue<Integer> data;//正常存储数据队列
        private Queue<Integer> help;//辅助队列

        public TwoQueueToStack(){
            data = new LinkedList<Integer>();
            help = new LinkedList<Integer>();
        }

        public void push(int pushInt){
            data.add(pushInt);
        }

        /**
         * 弹出栈顶即队列最后一个进去的数
         * @return
         */
        public int pop(){
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty");
            }

            //data中不止一个数时 把除最后一个数都倒入到help中
            while (data.size()>1){
                help.add(data.poll());
            }

            //此时data只剩下最后进入的数据 弹出并返回 ，此时data为空
            int res = data.poll();
            swap();
            return res;
        }

        /**
         * 返回栈顶即队列最后一个进去的数
         * @return
         */
        public int peek(){
            if (data.isEmpty()) {
                throw new RuntimeException("Stack is empty!");
            }

            while (data.size() != 1) {
                help.add(data.poll());
            }

            int res = data.poll();
            help.add(res);
            swap();
            return res;
        }

        /**
         * 将data和help引用交换
         */
        private void swap() {
            Queue<Integer> tmp = help;
            help = data;
            data = tmp;
        }


    }

    /**
     * 用两个栈倒一下数据
     */
    public static class TwoStackToQueue{
        private Stack<Integer> stackPush;//带填充数据的栈
        private Stack<Integer> stackPop;//带弹出数据的栈

        public TwoStackToQueue() {
            stackPush = new Stack<Integer>();
            stackPop = new Stack<Integer>();
        }

        public void push(int pushInt) {
            stackPush.push(pushInt);
        }

        public int poll() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            dao();
            return stackPop.pop();
        }

        public int peek() {
            if (stackPop.empty() && stackPush.empty()) {
                throw new RuntimeException("Queue is empty!");
            }
            dao();
            return stackPop.peek();
        }


        /**
         * 将stackPush数据倒入stackPop栈中
         * 两个限制条件：
         * 1.倒一次必须全完倒完
         * 2.stackPop不空的情况下不能倒
         */
        public void dao(){
            if(!stackPop.isEmpty()){
                return;
            }

            while (!stackPush.isEmpty()){
                stackPop.push(stackPush.pop());
            }

        }
    }

}
