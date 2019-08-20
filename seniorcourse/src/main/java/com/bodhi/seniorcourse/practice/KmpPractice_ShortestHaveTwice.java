package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/20 11:40
 * desc :一个字符串只能在后面添加字符变成一个大的字符串 要求添加最少的字符 使之大字符串变成包含两个原始串
 * eg:abcabc->abcabcabc
 *
 *
 * 思路：
 * 求原字符串每个字符前面的最大前缀和最大后缀子串相等的最大值的next数组 在多求一个终止位置的next值 即表示
 * 原字符串中最大前缀和最大后缀 然后将原字符串向右平移 将前缀重合上后缀 然后在后面补全除前缀剩下的字符即可
 *
 *
 *
 */
public class KmpPractice_ShortestHaveTwice {

    /**
     * 返回在后面要添加最少的字符串 是大穿变成包含两个原始串
     * @param str 原始串
     * @return 添加的最少字符串
     */
    public static String answer(String str){
        //判空
        if (str == null || str.length() == 0) {
            return "";
        }
        char[] chas = str.toCharArray();
        //判断特殊情况
        if (chas.length == 1) {
            return str + str;
        }
        if (chas.length == 2) {
            return chas[0] == chas[1] ? (str + String.valueOf(chas[0])) : (str + str);
        }
        //终止位置的最大前缀和最大后缀相等的值
        int endNext = endNextLength(chas);
        return str+ str.substring(endNext);
    }

    /**
     * 改进原KMP算法next数组的求解 使之返回终止位置的next值
     * @param chas
     * @return 终止位置的next值
     */
    private static int endNextLength(char[] chas) {
        int[] next = new int[chas.length+1];
        next[0] =-1;
        next[1]=0;
        int i=2;//i当前带求的位置
        int cn =0;//初始为当前带求位置i的前一个数i-1的最大前后缀长度
        while (i<next.length){
            //i-1位置的数和i-1位置的最大前后缀前缀位置下一个数相等
            if(chas[i-1]==chas[cn]){
                next[i++]=++cn;
            }else if(cn>0){
            //当不满足第一个条件 并且i-1存在最大前后缀时 跳到i-1最大前后缀 前缀位置的最大前后缀 然后继续和str[i-1]比较
                cn=next[cn];
            }else{
                //当不满足第一个条件 并且i-1不存在最大前后缀或者 i-1位置最大前后缀 前缀的最大前后缀不存在 i位置为0
                next[i++]=0;
            }
        }
        return next[next.length-1];
    }

    public static void test(){
        String test1 = "a";
        System.out.println(answer(test1));

        String test2 = "aa";
        System.out.println(answer(test2));

        String test3 = "ab";
        System.out.println(answer(test3));

        String test4 = "abc";
        System.out.println(answer(test4));

        String test5 = "abcdabc";
        System.out.println(answer(test5));

    }

}
