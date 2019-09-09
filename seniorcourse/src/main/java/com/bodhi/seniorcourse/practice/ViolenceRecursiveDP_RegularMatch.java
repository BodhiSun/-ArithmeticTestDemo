package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/9/9 12:16
 * desc :VR->DP 字符串正则匹配问题(难度很高)
 *
 * 【题目】
 * 给定字符串str，其中绝对不含有字符'.'和'*'。再给定字符串exp，其中可以含有'.'或'*'，'*'字符不能是exp的
 * 首字符，并且任意两个'*'字符不相邻。exp中的'.'代表任何一个字符，exp中的'*'表示'*'的前一个字符可以有0个
 * 或者多个。请写一个函数，判断str是否能被exp匹配。
 * 注：单独一个*不合法 *必须配合前面的字符才有效
 * 【举例】
 * str="abc"，exp="abc"，返回true。
 * str="abc"，exp="a.c"，exp中单个'.'可以代表任意字符，所以返回true。
 * str="abcd"，exp=".*"。exp中'*'的前一个字符是'.'，所以可表示任意数量的'.'字符，当exp是"...."时与"abcd"
 * 匹配，返回true。
 * str=""，exp="..*"。exp中'*'的前一个字符是'.'，可表示任意数量的'.'字符，但是".*"之前还有一个'.'字符，
 * 该字符不受'*'的影响，所以str起码有一个字符才能被exp匹配。所以返回false。
 *
 *
 * 递归思路：
 * 假设一个函数f(i,j)表示 字符串str从i开始到最后 能不能被exp从j开始到最后匹配上 匹配为true 不匹配为false
 * 此时会有如下几种情况:
 * 1.j+1位置没有字符 2.j+1位置有字符是* 3.j+1位置有字符不是*
 * 当为3时 要么i位置字符和j位置字符相等 要么j位置字符是.时才可能为true 进入递归f(i+1,j+1)，否则为false
 * 当为2时 如果i位置字符和j位置字符不匹配 则看i位置和j+2位置以后的字符，如果i位置和j位置能匹配时 则看j*
 * 能匹配i为前缀的所有情况是否有能匹配的
 *
 * 动态规划：
 * 1.i，j二维表 j为横向 i为纵向
 * 2.j == exp.length 时i == str.length为ture 其他为false ，最终求位置0,0
 * 3.if语句中i,j点依赖i + 1, j + 1
 * 4.while语句中i,j点依赖i(i可能++), j + 2
 * 5.根据位置依赖关系 所以需要倒数两列和最后一排的值
 * 6.根据basecase只有最后一列的值，需要根据题意(i开始和j开始能不能匹配)确定倒数第二列的值
 * 7.最后一排的值 当exp满足x*x*x*格式时 结果为T,F,T,F,T,F 不满足这种格式则后面都是F
 *
 */
public class ViolenceRecursiveDP_RegularMatch {

    /**
     * 暴力递归 字符串str能不能被exp匹配上
     * @param str
     * @param exp
     * @return
     */
    public static boolean isMatch(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        return isValid(s, e) ? process(s, e, 0, 0) : false;
    }

    /**
     * 暴力递归 字符串str从i开始到最后 能不能被exp从j开始到最后匹配上
     * @param str
     * @param exp
     * @param i
     * @param j
     * @return
     */
    public static boolean process(char[] str, char[] exp, int i, int j) {
        //如果exp来到最后没字符了 只有str也来到最后才返回true，否则返回false
        if (j == exp.length) {
            return i == str.length;
        }

        //如果j位置有字符即j+1位置没字符 或者j+1有字符且不等* 1,3两种情况
        if (j + 1 == exp.length || exp[j + 1] != '*') {
            //i位置有字符 并且 i位置字符==j位置字符相等或j位置字符为. 并且递归i+1和j+1
            return i != str.length && (exp[j] == str[i] || exp[j] == '.')
                    && process(str, exp, i + 1, j + 1);
        }

        //走到while的前提条件时 exp的j+1位置有字符且字符等于* 2情况
        //当j+1位置有字符字符等于* 并且i位置字符等于j位置字符时
        while (i != str.length && (exp[j] == str[i] || exp[j] == '.')) {
            //首次*默认等于0 然后将i和j+2比较 满足返回true 不满足返回false
            //二次i++后依然能够进来while循环 则默认*为1 比较的是原始的i+1和j+2位置 依次下去
            if (process(str, exp, i, j + 2)) {
                return true;
            }
            i++;
        }
        //当j+1位置有字符字符等于* 并且i位置字符不等于j位置字符时递归到i,j+2位置
        return process(str, exp, i, j + 2);
    }


    /**
     * DP 字符串str能不能被exp匹配上
     * @param str
     * @param exp
     * @return
     */
    public static boolean isMatchDP(String str, String exp) {
        if (str == null || exp == null) {
            return false;
        }
        char[] s = str.toCharArray();
        char[] e = exp.toCharArray();
        //检查s和e的有效性
        if (!isValid(s, e)) {
            return false;
        }
        //填好最后一排和倒数两列的值
        boolean[][] dp = initDPMap(s, e);

        //在填好依赖关系的数据后 根据VR中逻辑 推导出任意位置的值
        for (int i = s.length - 1; i > -1; i--) {
            for (int j = e.length - 2; j > -1; j--) {
                if (e[j + 1] != '*') {
                    dp[i][j] = (s[i] == e[j] || e[j] == '.')
                            && dp[i + 1][j + 1];
                } else {
                    int si = i;
                    while (si != s.length && (s[si] == e[j] || e[j] == '.')) {
                        if (dp[si][j + 2]) {
                            dp[i][j] = true;
                            break;
                        }
                        si++;
                    }
                    if (dp[i][j] != true) {
                        dp[i][j] = dp[si][j + 2];
                    }
                }
            }
        }
        return dp[0][0];
    }

    /**
     * 根据VR中basecase 和题意 推导出最后一排和倒数两列的值
     * @param s
     * @param e
     * @return
     */
    public static boolean[][] initDPMap(char[] s, char[] e) {
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true;
        for (int j = elen - 2; j > -1; j = j - 2) {
            if (e[j] != '*' && e[j + 1] == '*') {
                dp[slen][j] = true;
            } else {
                break;
            }
        }
        if (slen > 0 && elen > 0) {
            if ((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])) {
                dp[slen - 1][elen - 1] = true;
            }
        }
        return dp;
    }

    /**
     * 检查s和e是否有效
     * s中不能包含* .字符，e中不能有两个连续的*
     * @param s
     * @param e
     * @return
     */
    public static boolean isValid(char[] s, char[] e) {
        for (int i = 0; i < s.length; i++) {
            if (s[i] == '*' || s[i] == '.') {
                return false;
            }
        }
        for (int i = 0; i < e.length; i++) {
            if (e[i] == '*' && (i == 0 || e[i - 1] == '*')) {
                return false;
            }
        }
        return true;
    }

    public static void test() {
        String str = "abcccdefg";
        String exp = "ab.*d.*e.*";
        System.out.println(isMatch(str, exp));
        System.out.println(isMatchDP(str, exp));

    }




}
