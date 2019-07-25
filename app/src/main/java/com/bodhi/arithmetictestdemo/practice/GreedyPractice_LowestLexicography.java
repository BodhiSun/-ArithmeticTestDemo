package com.bodhi.arithmetictestdemo.practice;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/24 21:12
 * desc :贪心问题 最小字典序
 * 贪心策略：从提出策略 到证明策略是对的 最后证明按策略得出的结果是对的
 *
 * 【题目】给定一个字符串数组 要求将所有字符串拼接起来组成一个最小字典序的大字符串
 *
 * 字典序：先按照第一个字母、以 a、b、c……z 的顺序排列，如果第一个字母一样，那么比较第二个、第三个乃至
 * 后面的字母。如果比到最后两个单词不一样长（比如，sigh 和 sight），那么把短者排在前
 *
 * 思路：如果单纯的先按字典序把所有字符串排序 然后按照字典序的顺序拼接起来 这种思路是不对的 eg：b,ba
 * 按字典序拼接结果为 bba，但是最小字典序拼接结果应该为bab
 * 所以不是A<=B 输出为AB 而是AB<=BA 输出为AB这种策略
 *
 * 制定策略要注意以下几点：
 * 1.策略要有传递性 eg:如果有条件 a.b<b.a ,b.c<c.b 那么结论 a.c<c.a
 * 2.证明策略本身是正确合理的：
 * 首先把a~z理解为26进制的数 然后把26^N长度次方 抽象成m(N)
 * A条件：a.b<=b.a =》 a*m(b)+b<=b*m(a)+a
 * B条件：b.c<=c.b =》 b*m(c)+c<=c*m(b)+b
 *
 * A条件左右两边 -b *c后变为条件A1:  a*m(b)*c<=(b*m(a)+a-b)*c
 * B条件左右两边 -b *a后变为条件B1： (b*m(c)+c-b)*a<=c*m(b)*a
 *
 * 根据A1,B1推导出 (b*m(c)+c-b)*a<=(b*m(a)+a-b)*c
 * 展开简化后得出 m(c)*a+c<=m(a)*c+a 即a.c<c.a
 *
 * 3.证明按这个策略排完序后的结果是正确的
 * 先假如这个结果是正确的 那么任意交换排序后的两个字符串肯定都会比原来字典序大 假如排序后大字符串其中有任
 * 意a，b两个字符串 a,m1,m2,m3..mkb那么证明交换a,b后 b,m1,m2,m3..mka比原来大。
 *
 * 4.所以遇到贪心问题 可以提出各种策略 但是不要证明它对不对 只要用对数器验证它是对的就可以
 *
 *
 */
public class GreedyPractice_LowestLexicography {

    /**
     * 制定排序策略
     */
    public static class MyComparator implements Comparator<String>{

        @Override
        public int compare(String a, String b) {
            return (a+b).compareTo(b+a);
        }
    }

    /**
     * 拼接最小字典序字符串
     * @param strs
     * @return
     */
    public static String lowestString(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        //按照自定义比较器的排序方式重新排序str
        Arrays.sort(strs,new MyComparator());

        //然后将排序后的数组从低到高拼接起来
        String res ="";
        for (int i = 0; i < strs.length; i++) {
            res+=strs[i];
        }
        return res;
    }

    public static void test(){
        String[] strs1 = { "jibw", "ji", "jp", "bw", "jibw" };
        System.out.println(lowestString(strs1));

        String[] strs2 = { "ba", "b" };
        System.out.println(lowestString(strs2));

    }


}
