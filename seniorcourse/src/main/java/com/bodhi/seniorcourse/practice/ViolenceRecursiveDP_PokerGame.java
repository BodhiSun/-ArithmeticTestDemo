package com.bodhi.seniorcourse.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/9/2 17:29
 * desc :暴力递归到动态规划-排成一条线的纸牌博弈问题
 *
 * 【题目】
 * 给定一个整型数组arr，代表数值不同的纸牌排成一条线。玩家A和玩家B依次拿走每张纸牌，规定玩家A先拿，玩家B
 * 后拿，但是每个玩家每次只能拿走最左或最右的纸牌，玩家A和玩家B都绝顶聪明。请返回最后获胜者的分数。
 * eg:
 * arr=[1,2,100,4]。
 *开始时玩家A只能拿走1或4。如果玩家A拿走1，则排列变为[2,100,4]，接下来玩家B可以拿走2或4，然后继续轮到玩
 * 家A。如果开始时玩家A拿走4，则排列变为[1,2,100]，接下来玩家B可以拿走1或100，然后继续轮到玩家A。玩家A作
 * 为绝顶聪明的人不会先拿4，因为拿4之后，玩家B将拿走100。所以玩家A会先拿1，让排列变为[2,100,4]，接下来玩
 * 家B不管怎么选，100都会被玩家A拿走。玩家A会获胜，分数为101。所以返回101。
 * arr=[1,100,2]。
 *开始时玩家A不管拿1还是2，玩家B作为绝顶聪明的人，都会把100拿走。玩家B会获胜，分数为100。所以返回100。
 *
 *
 * 暴力递归的尝试思路分析：
 * 假如i到j表示arr中从左到右的纸牌，用f(i,j)表示在arr[i~j]中A先手时能够获得的最大分数，s(i,j)表示A后手时
 * 能够获得的最大分数。首先分析f(i~j) A可先去arr[i],取完后剩余arr[i+1,j] 此时相当于A后手在[i+1,j]的情况，
 * 当然A也可以先去arr[j],取完后剩余arr[i,j-1]，此时相当于A后手在[i,j-1]的情况，所以f(i,j)可表示为：
 * max{arr[i]+s(i+1,j),arr[j]+s(i,j-1)}。在分析s(i,j) B可先取arr[i]或arr[j] 取完后又相当于A先手的情况，
 * 只是这种情况下，B会留下最差解。所以s(i,j)可表示为：min{arr[i]+f(i+1,j),arr[j]+f(i,j-1)}。
 *
 *
 * VR->DP过程分析:
 * 1.可变参数为i,j并且i,j固定方法f,s返回值一定固定 所以无后效性
 * 2.有f,s两个函数所以要根据i和j的变化范围创建 两个表
 * 3.因为i不可能大于j所以两个二维表 左下部分都不用填充
 * 4.标出目标位置 i=0，j=arr.length-1
 * 5.当i=j时 f表上等于arr[i] ，s表上等于0，填充对角线上的点
 * 6.普遍位置上f(i，j)依赖s上i+1和j-1位置 s(i,j)依赖f上i+1和j-1位置 最后推出目标位置
 *
 */
public class ViolenceRecursiveDP_PokerGame {

    /**
     * 暴力递归方法 纸牌获得最大分数
     * @return
     */
    public static int winVR(int[] arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        //在先手和后手 选手中选出最大分数
        return Math.max(f(arr,0,arr.length-1),s(arr,0,arr.length-1));
    }

    /**
     * 先手情况下获得最大分数
     * @param arr 给定数组
     * @param i 初始开始拿的左范围
     * @param j 初始开始拿的右范围
     * @return
     */
    private static int f(int[] arr, int i, int j) {

        //如果i == j，即arr[i...j]上只有一张纸牌 当然会被先拿纸牌的人拿走，所以可以返回arr[i];
        if(i==j){
            return arr[i];
        }

        //拿了其中一个之后，当前玩家则成了后拿的那个人 因为玩家会做出最好的选择，所以会选择最好的方案
        return Math.max(arr[i]+s(arr,i+1,j),arr[j]+s(arr,i,j-1));
    }

    /**
     * 后手情况下获得最大分数
     * @param arr 给定数组
     * @param i 初始开始拿的左范围
     * @param j 初始开始拿的右范围
     * @return
     */
    private static int s(int[] arr, int i, int j) {
        //arr[i...j]上只有一张纸牌,作为后拿的人必然什么也得不到，所以返回0；
        if(i==j){
            return 0;
        }

        //因为对手会拿走最好的，所以当前玩家只能拿最差的
        return Math.min(f(arr,i+1,j),f(arr,i,j-1));
    }

    /**
     * 暴力递归->动态规划 纸牌获得最大分数
     * @return
     */
    public static int winDP(int[] arr){
        if(arr==null||arr.length==0){
            return 0;
        }
        //建立两张表
        int[][] f = new int[arr.length][arr.length];
        int[][] s = new int[arr.length][arr.length];
        for (int j = 0; j < arr.length; j++) {
            //f表对角线上的值
            f[j][j]=arr[j];
            //普遍位置上的点
            for(int i=j-1;i>=0;i--){
                f[i][j]=Math.max(arr[i]+s[i+1][j],arr[j]+s[i][j-1]);
                s[i][j]=Math.min(f[i+1][j],f[i][j-1]);
            }
        }
        //返回两张表上目标点的最大值
        return Math.max(f[0][arr.length-1],s[0][arr.length-1]);
    }

    public static void test(){
        int[] arr={1,2,100,4};
        System.out.println(winVR(arr));
        System.out.println(winDP(arr));
    }


}
