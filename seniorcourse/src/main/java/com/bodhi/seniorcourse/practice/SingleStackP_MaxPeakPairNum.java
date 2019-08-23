package com.bodhi.seniorcourse.practice;

import java.util.Stack;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/22 17:03
 * desc :单调栈结构-求数组代表的环形山中能相互看见的山最多有多少对
 *
 * 【题目】
 * 给定一个数组围成一个环形山 相邻两个山峰之间能相互看见 不相邻的山峰之间只要两条路径上任一路径没有比两个
 * 山峰中最小值大的山峰 也可以相互看见，求环形山中能相互看见的一共有多少对
 *
 * 如果数组中不含有相同的数 则有数组长度为1时值为0 长度为2时值为1 长度为n时值为(n-2)*2+1=2*n-3
 *
 * 如果数组中有相同的数，只能采用单调栈结构
 * 思路：
 * 准备一个从底到顶是从大到小顺序的栈 栈中存放的是数组中的值和对应值出现个数K 并按值排序。先在数组中找到第
 * 一个碰到的最大值(有可能有多个相同的最大值)作为数组遍历的起始位依次遍历数组，起始栈为空 将最大值和1次压
 * 入栈中 如果第二个数比栈顶数小直接压入当前数和1次 如果相等则将栈顶值中出现的次数加1 依次遍历数组中的数
 * 和栈顶值比较 如果当前数比栈顶值大时结算开始 先计算栈顶值出现多次时内部可以产生的相互看见的对数C(K,2)=
 * A(k,2)/A(2,2)(当k=1时为0 k=2时为1)然后计算栈顶可以与左右两边产生的相互看见的对数k*2 所以此时栈顶结算
 * 出来的一共有C(K,2)+K*2对 把栈顶弹出然后把当前数压栈 当数组中的数全部遍历完后 开始结算栈中的数 栈内值出
 * 先多次时内部产生的依然是C(K,2),计算左右两边可以产生的时 如果当前栈顶下有两个或两个以上的值时 左右两边
 * 产生的依然是K*2 当当前栈顶为倒数第二个数时 如果栈底数重复的次数大于1 则当前栈顶左右两边产生的是K*2 如果
 * 重复次数=1 左右两边产生的是K 当当前栈顶为栈底时 左右两边产生的为0
 *
 *
 *
 */
public class SingleStackP_MaxPeakPairNum {

    /**
     * 用来入栈的数据对
     */
    public static class Pair{
        public int value;
        public int times;

        public Pair(int value) {
            this.value = value;
            this.times=1;//栈内的数至少出现1次
        }
    }


    /**
     * 给定数组构成的环形山 一共可以产生多少对相互看见的
     * @param arr
     * @return
     */
    public static long communications(int[] arr) {
        if (arr == null || arr.length < 2) {
            return 0;
        }

        int size = arr.length;
        //第一个最大值的位置
        int maxIndex = 0;
        for (int i = 0; i < size; i++) {
            maxIndex = arr[maxIndex] < arr[i] ? i : maxIndex;
        }
        //第一个最大值
        int curValue = arr[maxIndex];
        //从第一个最大值开始下一个要遍历的位置
        int index = nextIndex(size,maxIndex);
        //可产生的相互看见的总对数
        long res =0L;
        //准备从底到顶为从大到小的单调栈
        Stack<Pair> stack = new Stack<>();
        //初始将最大值压栈
        stack.push(new Pair(curValue));

        //从最大值位置开始遍历 直到遍历一圈后停止
        while (index!=maxIndex){
            curValue = arr[index];
            //当当前数大于栈顶的数时 结算栈顶构成的数量 并弹出
            while (!stack.isEmpty()&&stack.peek().value<curValue){
                int times = stack.pop().times;
                res+=getInternalSum(times)+times*2;
            }

            //当当前数和栈顶数相等时 栈顶次数加1
            if(!stack.isEmpty()&&stack.peek().value==curValue){
                stack.peek().times++;
            }else{
                //当当前数小于栈顶数相等时 直接入栈
                stack.push(new Pair(curValue));
            }

            //更新下一个待遍历位置
            index = nextIndex(size,index);
        }

        //当数组中的数遍历结束后 处理栈中的数据
        while (!stack.isEmpty()){
            int times = stack.pop().times;
            res+=getInternalSum(times);
            if(!stack.isEmpty()){
                res+=times;
                //当前栈里还剩2个或2个以上
                if(stack.size()>1){
                    res+=times;
                }else{
                    //当前栈还剩1个 即上面计算的栈顶是倒数第二个
                    res+=stack.peek().times>1?times:0;
                }
            }
        }
        return res;
    }

    /**
     * 根据栈中数出现的次数计算出 内部可以产生多少对
     *
     * @param times
     * @return
     */
    private static long getInternalSum(int times) {
        return times == 1L ? 0L : times * (times - 1) / 2L;

    }

    /**
     * 大小为size的数组构成的环形中 给定开始位置的角标 计算出下一个遍历的角标
     * @param size
     * @param i
     * @return
     */
    private static int nextIndex(int size, int i) {
        //当遍历到数组末尾时 使指针指向初始位置 否则继续向后遍历
        return i < (size - 1) ? (i + 1) : 0;
    }

    public static void test(){
        int[] arr = {5,2,8,2};
        System.out.println(communications(arr));
    }


}
