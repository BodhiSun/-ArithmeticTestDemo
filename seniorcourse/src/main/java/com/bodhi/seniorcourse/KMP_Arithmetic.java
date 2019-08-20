package com.bodhi.seniorcourse;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/9 15:41
 * desc:KMP算法-解决两个字符串str1长度n,str2长度m包含的问题 str2是str1中的子串返回在str1中str2开始的位置
 * 时间复杂度0(n)
 * <p>
 * 子序列：字符可以不连续
 * 子串：字符必须是连续的
 * <p>
 * <p>
 * 思路1-暴力方法：时间复杂度0(n*m)
 * 从str1中开始位置逐个字符和str2中字符比较 如发现不匹配的字符 则从str1中第二个字符开始比较 依次下去
 * <p>
 * <p>
 * 思路2-kmp算法：
 * KMP流程：将str2中每个字符i的前面字符串subStr中最大前缀(不包含subStr中最后一个字符)和最大后缀(不包含subStr
 * 中第一个字符)相等的子串最大长度值求出，str2 0位置字符值人为规定-1 1位置字符值为0 从2位置开始求值 存入next数组
 * 然后将str2和str1从0位置开始依次字符比较 当到了某个位置i的时候发现不匹配 那么假设在str2中对应的i位置的前
 * 面字符串最大前后缀相等子串长度为n 即str2中i前面字符串最大前缀0到j的长度为n 最大后缀k到(i-1)长度为n 那么
 * 将str2整体向右平移使j对应到str1的i-1位置 然后将str2和str1从str1的i位置也就是str2的j+1位置开始比较 比较
 * 到不匹配的字符重复上述str2向右平移的过程 如果当到新的i位置不匹配时 str2的对应的i位置最大前后缀相等子串
 * 长度为0 则将str2想右平移到str1的i+1位置 即str2的0位置开始和str1的i+1位置开始比较
 * <p>
 * kmp精髓：先跳过str1中从0位置到k位置个数 然后跳过了k位置到i-1位置个数 相当于每次跳过0到i-1位置个数
 * str1中0位置到k位置范围内开始的字符串肯定不会包含str2串 如果包含了就会和 i位置前的最大前后缀相等子串值不符
 */
public class KMP_Arithmetic {

    /**
     * 求字符串str2 在字符串str1中的开始位置
     *
     * @param s
     * @param m
     * @return
     */
    public static int getIndexOf(String s, String m) {
        //判断边界
        if (s == null || m == null || m.length() < 1 || s.length() < m.length()) {
            return -1;
        }

        char[] str1 = s.toCharArray();
        char[] str2 = m.toCharArray();
        int i1 = 0;//str1中当前比较的位置 指针
        int i2 = 0;//str2中当前比较的位置 指针
        int[] next = getNextArray(str2);

        while (i1 < str1.length && i2 < str2.length) {
            //匹配上的时候 指针都跳下一个
            if (str1[i1] == str2[i2]) {
                i1++;
                i2++;
            } else if (next[i2] == -1) {//没有匹配上时 并且如果是str2起始位置
                i1++;
            } else {
                //没有匹配上时 如果不是第一个字符 则跳到当前字符的最大前后缀位置
                i2 = next[i2];
            }
        }

        //如果str2中的指针 成功滑到了末尾 说明str1也同时滑过str2即包含str2
        return i2==str2.length?i1-i2:-1;
    }


    /**
     * 求字符串数组中每个字符前面的最大前缀和最大后缀子串相等的最大值
     * 思路：首先从左往右依次计算next[i]，当计算next[i]时，next[i-1]已经计算完毕
     * 当i-1位置最大前后缀的前缀下一个值和i-1位置的数相等时 则next[i]即为next[i-1]+1
     * 当i-1位置最大前后缀的前缀下一个值和i-1位置的数不等时 则继续判断前缀下一个数的最大前后缀的前缀下一
     * 个值是否和i-1位置的数相等 想等则为next[i]为next[next[i-1]]+1 不等则继续找前缀的前缀下一个数
     * 当一直不等并且判断到 next[j]=0时，即相等最大前后缀不存在 则next[i]为0
     *
     * @param str2
     * @return
     */
    public static int[] getNextArray(char[] str2) {
        if(str2.length==1){
            return new int[-1];
        }

        int[] next = new int[str2.length];
        //认为规定为-1
        next[0]=-1;
        next[1]=0;
        int i=2;//i当前带求的位置
        int cn =0;//cn初始为当前带求位置i的前一个数i-1的最大前后缀长度

        while (i<str2.length){
            //当i-1位置的数和i-1位置的最大前后缀下一个数相等 则i的最大前后缀为 i-1位置的最大前后缀+1
            if(str2[i-1]==str2[cn]){
                next[i++] =++cn;
            }else if(cn>0){
                //当不满足第一个条件 并且i-1还存在最大前后缀时 cn=i-1位置最大前后缀的最大前后缀 然后继续和str[i-1]比较
                cn=next[cn];
            }else{
                //当不满足第一个条件 并且i-1不存在最大前后缀或者 i-1位置最大前后缀的最大前后缀不存在 i位置为0
                next[i++]=0;
            }
        }

        return next;
    }

    public static void test(){
        String str = "abcabcababaccc";
        String match = "ababa";
        System.out.println(getIndexOf(str, match));
    }


}
