package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/26 20:27
 * desc :暴力递归 打印一个字符串的全部子序列，包括空字符串
 *
 * 尝试思路：
 *1.字符串的每个字符一共有两种状态 要或者不要 然后将状态结果传给下个字符
 *2.接过传递过来的状态结果 然后在加上当前字符的状态结果 合并传递给下一个字符
 *3.当递归到最后一个字符后递归终止
 *
 *
 */
public class ViolenceRecursive_PrintAllSubsquence {


    /**
     *
     * @param str 当前要打印的字符串的字符数组
     * @param i 当前第几个字符
     * @param result 上次递归传递过来的结果
     */
    public static void printAllSub(char[] str,int i,String result){
        if(i==str.length){
            System.out.println("result:"+result);
            return;
        }
        //扔给下级的两种状态 加或者不加自己
        printAllSub(str,i+1,result);
        printAllSub(str,i+1,result+str[i]);
    }

    public static void test(){
        String temp="abc";
        printAllSub(temp.toCharArray(),0,"");
    }


}
