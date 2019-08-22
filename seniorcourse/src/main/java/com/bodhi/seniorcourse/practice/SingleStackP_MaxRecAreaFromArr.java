package com.bodhi.seniorcourse.practice;

import java.util.Stack;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/22 00:50
 * desc :单调栈结构-求最大子矩形面积大小
 *
 * 【题目】
 * 给定一个数组，假定数组中的每个数代表一个直方图 求整个直方图能构成的矩形的最大面积
 *
 * 逻辑思路：
 * 从左边开始 将每个直方图向左右移动 移动到比当前值小的停下 然后计算左右移动多少个距离 乘以当前直方图的值
 * 就是以当前直方图能构成的最大的矩形面积 然后依次移动后面的 算出以每个直方图能构成的矩形面积 取出最大值
 *
 * 代码思路：
 * 找左右两边最近的比当前数小的值 所以采用单调栈的数据结构实现 只是栈底到栈顶是从小到大的顺序，初始栈是空
 * 的将第一个数的下标压栈 然后第二个数如果比第一个数大直接压栈 如果比第一个数小 则此时栈顶的数的右边最近比
 * 它最小的就是当前数 如果栈顶下面有值 则栈顶下面挨着的数就是左边最近的比它小的数 如果没值则左边记为-1表示
 * 左边界，依次遍历数组压栈记录左右边界，当数组所有的数都取完栈中有数据时 依次将栈顶数据弹出 栈顶数右边最
 * 近比它小的数没有值 记为数组的长度表示右边界 下边挨着的数表示左边最近比它小的数 栈底的左边值为-1 右边值
 * 为数组长度 根据数组每个字符的左右边界值计算出每个直方图能构成的最大面积 即(R-L-1)*数组的值
 *
 *
 */
public class SingleStackP_MaxRecAreaFromArr {

    public static int maxRecFromArr(int[] heights){
        if(heights==null || heights.length==0){
            return 0;
        }

        int maxArea=0;
        //准备一个栈作为单调栈
        Stack<Integer> stack = new Stack<Integer>();

        //依次遍历数组
        for(int i =0;i<heights.length;i++){
            //当前数比栈顶数小的时候
            while (!stack.isEmpty()&&heights[i]<=heights[stack.peek()]){
                //将栈顶数弹出
                int t = stack.pop();
                //栈顶数的左边界
                int l = stack.isEmpty()?-1:stack.peek();
                //以当前栈顶能构成的最大的矩形的面积
                int curArea = (i-l-1)*heights[t];
                //记录最大的区域
                maxArea = Math.max(maxArea,curArea);
            }

            //否则直接压栈
            stack.push(i);
        }

        //当数组的数全部取出 栈不空时
        while (!stack.isEmpty()){
            int t = stack.pop();
            int l = stack.isEmpty()?-1:stack.peek();
            int curArea =(heights.length-l-1)*heights[t];
            maxArea = Math.max(maxArea,curArea);
        }
        return maxArea;
    }

    public static void test(){
        int[] heights = new int[]{4,3,2,5,6};
        System.out.println(maxRecFromArr(heights));
    }

}
