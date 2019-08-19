package com.bodhi.seniorcourse;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/9 16:48
 * desc :Manacher算法-处理字符串str中关于回文串的问题 即str中最长回文串长度
 * 时间复杂度0(n)
 *
 * 回文直径：以一个中心开始向两边扩 能扩出来的长度范围。 回文半径即一半(包含中心)
 * 最右回文右边界：起始位置在-1 然后有回文就是最右边的字符 没回文就是自身点 边界位置不往回走 依次向后移动求出每个字符
 * 最右回文右边界中心：第一次到达 最右回文右边界时的 中心点位置
 *
 *
 *思路1-暴力方法：时间复杂度0(n^2)
 * 为了兼容奇偶问题在每个字符两边加上一个统一的任意符号，将字符串的每一个字符作为回文子串的中心对称点，求出
 * 最长的回文子串的长度 再除以2就是最后的结果
 *
 *
 * 思路2-Manacher算法
 *
 *
 *
 *
 *
 *
 */
public class Manacher_Arithmetic {


    /**
     * manacher算法 求出字符串中最长回文子串的长度
     * @param str
     * @return
     */
    public static int maxLcpsLength(String str){
        if (str == null || str.length() == 0) {
            return 0;
        }
        //处理字符串成带求解样式
        char[] charArr = manacherString(str);
        //存放每个字符的最大回文串长度
        int[] pArr= new int[charArr.length];
        int inde=-1;
        int pR=-1;//最右回文右边界

        return -1;




    }


    /**
     * 将每个字符前后加上统一的任意符号
     * @param str
     * @return
     */
    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        char[] res = new char[str.length()*2+1];
        int index=0;
        for (int i=0;i!=res.length;i++){
            res[i]=(i&1)==0?'#':charArr[index++];
        }
        return res;
    }





}
