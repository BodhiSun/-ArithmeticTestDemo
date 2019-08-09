package com.bodhi.seniorcourse;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/9 15:41
 * desc:KMP算法-解决两个字符串str1长度n,str2长度m包含的问题 str2是str1中的字串返回在str1中str2开始的位置
 * 时间复杂度0(n)
 *
 * 子序列：字符可以不连续
 * 子串：字符必须是连续的
 *
 *
 * 思路1-暴力方法：时间复杂度0(n*m)
 * 从str1中开始位置逐个字符和str2中字符比较 如发现不匹配的字符 则从str1中第二个字符开始比较 依次下去
 *
 *
 *思路2-kmp算法：
 * KMP流程：将str2中每个字符i的前面字符串subStr中最大前缀(不包含subStr中最后一个字符)和最大后缀(不包含subStr
 * 中第一个字符)相等的子串最大长度值求出，str2 0位置字符值人为规定-1 1位置字符值为0 从2位置开始求值 存入next数组
 * 然后将str2和str1从0位置开始依次字符比较 当到了某个位置i的时候发现不匹配 那么假设在str2中对应的i位置的前
 * 面字符串最大前后缀相等子串长度为n 即str2中i前面字符串最大前缀0-j的长度为n 最大后缀k-(i-1)长度为n 那么
 * 将str2整体向右平移使j对应到str1的i-1位置 然后将str2和str1从str1的i位置也就是str2的j+1位置开始比较 比较
 * 到不匹配的字符重复上述str2向右平移的过程 如果当到新的i位置不匹配时 str2的对应的i位置最大前后缀相等子串
 * 长度为0 则将str2想右平移到str1的i+1位置 即str2的0位置开始和str1的i+1位置开始比较
 *
 * kmp精髓：先跳过str1中从0位置到k位置个数 然后跳过了k位置到i-1位置个数 相当于每次跳过0到i-1位置个数
 * 0位置到k位置范围内开始的字符串肯定不会包含str2串 如果包含了就会和 i位置前的最大前后缀相等子串值不符
 *
 *
 */
public class KMP_Arithmetic {

    /**
     * 求字符串m 在字符串s中的开始位置
     * @param s
     * @param m
     * @return
     */
    public static int getIndexOf(String s, String m) {
        //判断边界
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }

        char[] str1 = m.toCharArray();
        char[] str2 = s.toCharArray();
        int i1=0;//str1中当前比较的位置
        int i2=0;//str2中当前比较的位置
        int[] next = getNextArray(str2);




        return -1;
    }


    /**
     *  求字符串数组中每个字符前面的最大前缀和最大后缀子串相等的最大值
     * @param ms
     * @return
     */
    public static int[] getNextArray(char[] ms) {

        return null;
    }


}
