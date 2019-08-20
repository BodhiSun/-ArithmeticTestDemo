package com.bodhi.seniorcourse;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/9 16:48
 * desc :Manacher算法-处理字符串str中关于回文串的问题 即str中最长回文子串长度
 * 时间复杂度0(n)
 *
 * 回文直径：以一个中心开始向两边扩 能扩出来的长度范围。 回文半径即一半(包含中心)
 * 最右回文右边界：起始位置在-1 然后有回文就是最右边的字符 没回文就是自身点 边界位置不往回走 当时每个字符所在回文右边界
 * 最右回文右边界中心：第一次到达 最右回文右边界时的 中心点位置
 *
 *
 *思路1-暴力方法：时间复杂度0(n^2)
 * 为了兼容奇偶问题在每个字符两边加上一个统一的任意符号，将字符串的每一个字符作为回文子串的中心对称点，求出
 * 最长的回文子串的长度 再除以2就是最后的结果
 *
 *
 * 思路2-Manacher算法(实际就是在暴力方法的基础上有加速)
 * 第一步 处理原字符串：
 * 为了兼容长度奇偶不同情况的求值 在原字符串的每个相邻两个字符中间插入一个分隔符 同时在首尾也要添加一个分隔符 可以用#号
 * 第二步 回文半径数组len：
 * 用一个辅助数组Len[i]表示以字符T[i]为中心的最长回文子串的最右字符到T[i]的长度，即经过兼容处理后的字符串
 * 的回文半径数组 比如以T[i]为中心的最长回文字串是T[l,r],那么Len[i]=r-i+1。
 * 第三步 len数组的求解：
 * 首先从左往右依次计算Len[i]，当计算Len[i]时，Len[j](0<=j<i)已经计算完毕
 * 设R为之前计算中最长回文子串的右端点的最大值 即R为当前i位置的最右回文右边界
 * 设取得这个最大值的位置为c 即最右回文右边界中心为c
 * 设R关于c的对称点为L 即回文左边界为L
 * 第一种情况：i>R 即i在边界R外
 * 暴力方法 扩半径求值
 * 第二种情况：i<=R 即i在边界R内 设i关于中心点c的对称点为i' 又细分三种情况：
 * 1)i'的回文半径数组范围 在L到R中间 即i'的左半径大于L
 *  所以i位置的回文半径也在L到R的内部 不用算大小等于i'的半径 i'之前已经算完并记录了 O(1)
 * 2)i'的回文半径数组范围 超过L到R 即i'的左半径小于L
 *  这种情况i位置的回文半径不用扩就是i到R O(1)
 * 3)i'的回文半径刚好压线 即i'的左半径等于L
 *  此时i位置的回文半径 大于等于i到R 因为从i到R不用验但是R之后还能不能继续扩需要去验证
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
        //存放每个字符的最大回文串长度半径的回文半径数组
        int[] pArr= new int[charArr.length];
        int C=-1;//最右回文右边界中心初始值
        int R=-1;//最右回文右边界初始值
        int max = Integer.MIN_VALUE;
        //依次求每个字符的回文半径
        for (int i = 0; i < charArr.length; i++) {
            //2*C-i即为i关于C的对称点i' i'的半径和R到i的距离哪个小取哪个
            pArr[i]=R>i?Math.min(pArr[2*C-i],R-i):1;//此语句求出的是当在边界内时 不用校验的区域
            //进一步遍历循环扩半径 求最终区域
            while (i+pArr[i]<charArr.length&&i-pArr[i]>-1){
                //当为第一种情况和第二种情况的3)情况时需要继续扩 校验可能相等
                if(charArr[i+pArr[i]]==charArr[i-pArr[i]]){
                    //i位置的回文半径增加
                    pArr[i]++;
                }else{
                    //当为第一种情况校验也可能不等 同时当为第二种情况1)2)情况时一定不等
                    break;
                }
            }

            //当扩大之后的区域超出了R的范围 更新R,C
            if(i+pArr[i]>R){
                R=i+pArr[i];
                C=i;
            }
            max=Math.max(max,pArr[i]);
        }

        //处理之后的字符串的回文半径数组值-1 即为对应的原始回文字符串的长度
        return max-1;
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

    public static void test(){
        String str1 = "abc1234321ab";
        System.out.println(maxLcpsLength(str1));
    }





}
