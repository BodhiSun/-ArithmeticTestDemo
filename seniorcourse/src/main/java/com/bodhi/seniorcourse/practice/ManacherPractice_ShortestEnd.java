package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/20 17:14
 * desc :给定一个字符串 只能向后面添加字符 要求添加最短的字符使之成为回文串
 *
 * 思路：
 * 求出原串中在包含最后一个字符的前提下的最长回文串 前面剩下的字符逆序追加到后面即可
 * 改写manacher算法当某个位置c的回文右边界R等于字符串的最后一个位置时 记录R c ,然后0到L部分逆序
 *
 */
public class ManacherPractice_ShortestEnd {

    public static String shortestEnd(String str){
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] charArr = manacherString(str);
        //每个字符对应的最长回文半径数组
        int[] pArr = new int[charArr.length];
        int index = -1;//最右回文右边界中心位置
        int pR =-1;//最右回文右边界位置
        int maxContainsEnd = -1;//包含最后一个字符的最长回文子串半径

        for(int i =0;i!=charArr.length;i++){
            //如果i在边界内时 求出不用校验的区域
            pArr[i]=pR>i?Math.min(pArr[2*index-i],pR-i):1;
            while (i+pArr[i]<charArr.length&&i-pArr[i]>-1){
                //跳过不用校验区域 判断两边紧邻着的值 相等则半径区域扩大
                if(charArr[i+pArr[i]]==charArr[i-pArr[i]]){
                    pArr[i]++;
                }else{
                    //不等则终止向外扩 i位置的半径确定
                    break;
                }
            }

            //判断半径是否超过最右边界 及时更新最右边界位置和最右边界中心
            if(i+pArr[i]>pR){
                pR=i+pArr[i];
                index=i;
            }

            //当最右边界到达字符串末尾时 停止求后面的值
            if(pR==charArr.length){
                maxContainsEnd=pArr[i];
                break;
            }
        }

        //根据maxContainsEnd值 求出回文串前面所有字符的逆序
        char[] res = new char[str.length()-(maxContainsEnd-1)];
        for (int i = 0; i < res.length; i++) {
            //将charArr中正序取出来的原字符串中的字符 倒叙填充到res中
            res[res.length-1-i]=charArr[i*2+1];//i*2+1 跳过特殊字符
        }
        return String.valueOf(res);
    }

    /**
     * 将每个字符前后加上统一的任意符号
     * @param str
     * @return
     */
    public static char[] manacherString(String str){
        char[] charArr = str.toCharArray();
        //处理后的字符串数组总长度
        char[] res = new char[str.length()*2+1];
        int index = 0;
        for (int i = 0; i !=res.length; i++) {
            res[i]=(i&1)==0?'#':charArr[index++];
        }
        return res;
    }

    public static void test(){
        String str2 = "abcd123321";
        System.out.println(shortestEnd(str2));
    }
}
