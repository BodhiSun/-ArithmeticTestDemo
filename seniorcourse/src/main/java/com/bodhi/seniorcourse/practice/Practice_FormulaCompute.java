package com.bodhi.seniorcourse.practice;

import java.util.LinkedList;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/30 10:14
 * desc :返回公式的计算结果
 *
 * 【题目】
 * 给定一个字符串str，str表示一个公式，公式里可能有整数、加减乘除符号和左右括号，返回公式的计算结果。
 * eg:
 * str="48*((70-65)-43)+8*1"，返回-1816。
 * str="3+1*4"，返回7。 str="3+(1*4)"，返回7。
 * 【说明】
 * 1．可以认为给定的字符串一定是正确的公式，即不需要对str做公式有效性检查。
 * 2．如果是负数，就需要用括号括起来，比如"4*(-3)"。但如果负数作为公式的开头或括号部分的开头，则可以没
 *  有括号，比如"-3*4"和"(-3*4)"都是合法的。
 * 3．不用考虑计算过程中会发生溢出的情况
 *
 *
 * 思路：
 * 当没有括号时准备一个栈将字符数组 从左到右每个字符依次压栈 当压入的是数字时检测当前栈顶的符号是否是乘、除
 * 不是乘、除则正常压栈，是乘除的话把栈顶的符号和栈顶下面的数字弹出 计算结果然后将结果压栈，将整个公式转换
 * 为只有加减的普通公式 然后顺序计算即可。
 * 如果有括号先将括号里的结果算出 将公式转为不带括号的样式 然后计算乘除符号的结果 将公式转为只有加减的普通
 * 公式 最后顺序计算结果。括号里的计算采用递归方法，当遇到( 时调用递归方法 方法里面遇到) 或者字符串末尾计
 * 算结果 返回当前括号里的结果和 上层函数开始计算的下一个位置 即终止）的下一个字符位置
 *
 *
 */
public class Practice_FormulaCompute {

    /**
     * 给定的字符串型公式 返回公式结果
     * @param str
     * @return
     */
    public static int getFormulaValue(String str){
        //将整个公式传入 从0位置开始计算结果 取返回的数组中第一个结果值
        return value(str.toCharArray(),0)[0];
    }

    /**
     * 计算字符公式数组 从i位置开始 遇到) 或者数组末尾结束的结果
     * @param str 传入要计算的字符公式数组
     * @param i 要计算公式结果的起始位
     * @return 返回计算的结果 和 结果终止位置下一个位置
     */
    private static int[] value(char[] str, int i) {
        //用双向链表存每个数字或运算符号
        LinkedList<String> que = new LinkedList<>();
        //代表字符构成的 每个整型数字
        int pre =0;
        //递归的返回结构 只存两个值 从i位置开始的计算的结果和结果的终止位
        int[] bra =null;

        //从要计算的起始位置开始依次遍历 直到末尾或者遇到）
        while (i<str.length&& str[i]!=')'){
            //如果遍历到的字符是数字 则还原数字对应的整数形式
            if(str[i]>='0'&&str[i]<='9'){
                pre = pre*10+(str[i++]-'0');//i位置字符对应的ASCII-0字符的ASCII等于对应的数字
            }else if(str[i]!='('){
                //如果遍历到的是加减乘除运算符
                //先运算符号前面的数字加入链表末尾 并做相应计算
                addNum(que,pre);
                //然后在将运算符号直接加入到双端链表末尾
                que.addLast(String.valueOf(str[i++]));
                //然后将数字字符重新置回0 用于记录下一个数字
                pre=0;
            }else{
                //如果遍历到的是( 符号 则递归求( 符号下一个位置开始 括号里面的值 并存到bra数组
                bra =value(str,i+1);
                pre =bra[0];
                //上层即将开始执行的位置 是结果位的下一个位置
                i = bra[1]+1;
            }
        }
        //当遍历到)符号 或是遍历到末尾时先将遍历到的最后一个数添加到链表里
        addNum(que,pre);
        //然后将当前从i位置开始 到结束的所有数结果 和结尾位置 返回给上层
        return new int[]{getNum(que),i};
    }

    /**
     * 将给定的数字加入到双向链表
     * @param que
     * @param num
     */
    public static void addNum(LinkedList<String> que,int num){
        if(!que.isEmpty()){
            //当前离链表末尾最近的数字
            int cur =0;
            //先将当前链表末尾的运算符号弹出
            String top = que.pollLast();
            //如果当前符号是 加减 操作的话不做特殊处理
            if(top.equals("+")||top.equals("-")){
                que.addLast(top);
            }else{
                //否则 运算符号为 乘除时，将符号下面的数字取出 和当前要加入的数运算
                cur = Integer.valueOf(que.pollLast());
                num = top.endsWith("*")?(cur*num):(cur/num);
            }
        }
        //将要加入的数或 运算后数加入的链表尾
        que.addLast(String.valueOf(num));
    }

    /**
     * 将当前只存在加减操作的链表中公式的结果计算并返回
     * @param que
     * @return
     */
    private static int getNum(LinkedList<String> que) {
        //所有数字的运算结果累加和
        int res=0;
        //标记当前是否是加操作/否则就是减操作
        boolean add=true;
        //链表中每个存入的数据(包括数字和加减符号)
        String cur =null;
        int num =0;
        while (!que.isEmpty()){
            cur = que.pollFirst();
            if(cur.equals("+")){
                add=true;
            }else if(cur.equals("-")){
                add=false;
            }else{
                //当前存入的数据是数字时
                num = Integer.valueOf(cur);
                res+=add?num:(-num);
            }
        }
        return res;
    }

    public static void test(){
        String exp = "48*((70-65)-4)+2*1";
        System.out.println(getFormulaValue(exp));

        exp = "10-5*3";
        System.out.println(getFormulaValue(exp));

        exp = "-3*4";
        System.out.println(getFormulaValue(exp));
    }




}
