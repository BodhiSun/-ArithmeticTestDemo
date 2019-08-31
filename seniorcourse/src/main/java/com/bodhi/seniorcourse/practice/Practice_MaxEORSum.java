package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/31 14:15
 * desc :数组最大异或和
 *
 * 【题目】
 * 给定一个数组，求子数组的最大异或和。一个数组的异或和为，数组中所有的数异或起来的结果。
 *
 * 异或运算性质：a^b=c b=a^c a=b^c  eg:1^2=3 2=1^3
 * 二进制负数的值：除符号位(最高位为符号位 0为正1为负)外其他位取反在加1
 * eg：11.....110 其中31个1第一个1表示符号位负 然后取反加1后为10 表示所以表示-2 负数比较也是高位1多的大
 * 移位运算:>>右移左边补0值变小  <<左移右边补0值变大
 * 或运算：| 相同为1，不同为0
 *
 *
 * 思路一：暴力方法 O(N^3)
 * 将数组每个字符一次遍历 求以每个字符结尾的所有子数组中的最大值 然后比较每个字符求得最大值中的最大值
 *
 * 思路二：暴力法改进 O(N^2)
 * 利用异或运算性质：a^b=c b=a^c ，所以要求k到length的异或结果 等于0到k位置异或上0到length
 * 准备一个数组用来记录0到i的每个字符的异或结果 求出K为开头到i的结果最大值 然后比较所有的最大值即可
 *
 *
 * 思路三：利用前缀树结构 O(N)
 * 将整型数组中的每个数转成32位的二进制数，然后依次将每个数中所有的二进制位从高位到低位 构成一个32层的前缀
 * 二叉树。假如当插到到i位置时想知道0到i中最大的异或和是多少 即0到i-1中最大的异或和 异或上i位置的结果最大，
 * 则先推导出一条期望的能和i位置数异或结果最大的数 然后在前面数所构成的前缀树中查找此期望数，在某对应位不
 * 满足的条件退而求其次选择另一条路，但是优先选择符合最大的值的对应位，这样选出一条真实的接近期望的数 然后
 * 和i进行异或 就是0到i范围内 最大的异或和。
 *
 *
 */
public class Practice_MaxEORSum {

    /**
     * 数组最大异或和-思路一暴力方法
     */
    public static int maxXorSum1(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }

        int maxEor =Integer.MIN_VALUE;
        for (int i =0;i<arr.length;i++){
            int maxEndCharEor=Integer.MIN_VALUE;
            for (int j=0;j<=i;j++){
                int res=0;
                for(int start=j;start<=i;start++){
                    res^=arr[start];
                }
                maxEndCharEor=Math.max(res,maxEndCharEor);
            }
            maxEor=Math.max(maxEor,maxEndCharEor);
        }
        return maxEor;
    }

    /**
     * 数组最大异或和-思路一暴力方法改进版
     */
    public static int maxXorSum2(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }

        //整个数组异或的最大值
        int max = Integer.MIN_VALUE;
        //0到i位置每个字符的异或结果
        int[]dp = new int[arr.length];
        //0到i位置的异或结果
        int eor = 0;
        for(int i =0;i<arr.length;i++){
            eor^=arr[i];
            //当前0到i位置异或结果大小
            max =Math.max(max,eor);
            //求以i结尾start开头的每个子串的异或结果
            for(int start=1;start<=i;start++){
                //0到start-1的异或结果 异或上 0到i的异或结果
                int curEor = dp[start-1]^eor;
                //更新全局最大值
                max = Math.max(curEor,max);
            }
            dp[i] = eor;
        }
        return max;
    }

    /**
     * 数组最大异或和-思路一前缀树方法
     */
    public static int maxXorSum3(int[] arr){
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //最大异或和
        int max =Integer.MIN_VALUE;
        //0到当前数的异或和
        int eor =0;
        //实例化自定义的前缀树结构 并初始加入一条全是0的路径
        NumTrie numTrie =new NumTrie();
        numTrie.add(0);
        for (int i = 0; i < arr.length; i++) {
            eor ^=arr[i];
            //求出即将要加入的i位置数 能构成的最大异或和 并更新
            max = Math.max(max,numTrie.maxXor(eor));
            //将0到当前i位置的异或和加入前缀树
            numTrie.add(eor);
        }
        return max;
    }

    /**
     * 构造前缀二叉树节点
     * 前缀树只需要路径不需要每个节点上的值
     */
    public static class Node{
        //二进制数每位上只有0,1两种值
        public Node[] nexts = new Node[2];
    }

    /**
     * 构造32层的二进制数据类型的前缀二叉树
     * 提供add加数方法 maxXor最大异或和方法
     */
    public static class NumTrie{

        //初始前缀树头结点
        public Node head =new Node();

        /**
         * 添加整数 形成前缀二叉树
         * @param num 要添加的整数
         */
        public void add(int num){
            //每次调整指针都从都从头节点开始向下排查
            Node cur =head;
            //二进制整数一共32位 所以每次遍历32次得到每位上的数
            for(int move = 31;move >=0;move--){
                //num右移move位 得到前32-move个位 然后&1每次把移位后的数最后一个数取出来 就从高位到低位把
                //每位上的数都取出来了
                int path = ((num>>move)&1);
                //判断前缀树当前节点是否满足当前位上的值
                cur.nexts[path] = cur.nexts[path]==null?new Node():cur.nexts[path];
                //当前指针来到下一层节点
                cur = cur.nexts[path];
            }
        }


        /**
         * 当即将要插入某个数num时 0到num-1位置构成的前缀树 和当前num所能构成的最大异或和
         * @param num 即将要插入的某数
         * @return
         */
        public int maxXor(int num){
            //每次都头指针开始检索
            Node cur = head;
            //最大异或和
            int res = 0;
            for (int move = 31; move >= 0; move--) {
                int path = (num >> move) & 1;
                //计算每位上期望的路径 当是符号位时相同值才能最大 当是数值位时相反值才能最大
                int best = move == 31 ? path : (path ^ 1);
                //求出每位上接近期望路径的真实路径
                best = cur.nexts[best]!=null?best:(best^1);
                //将给定数的给定位与前缀树中对应位的真实最大路径异或 然后从高到低逐位的值拼接一起
                res |=(path^best)<<move;
                //指针来到下一层节点
                cur =cur.nexts[best];
            }
            return res;
        }
    }

    public static void test(){
        int[] arr = {1,2,3,4};
        System.out.println(maxXorSum1(arr));
        System.out.println(maxXorSum2(arr));
        System.out.println(maxXorSum3(arr));
    }






}
