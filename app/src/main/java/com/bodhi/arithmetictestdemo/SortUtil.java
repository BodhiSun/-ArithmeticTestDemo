package com.bodhi.arithmetictestdemo;

import android.util.Log;

import java.util.Arrays;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/12 15:34
 * desc :基于比较的排序
 *
 * 对数器的作用：
 * 1.验证一个方法a是否正确
 * 2.验证贪心策略是否正确
 * 对数器使用步骤：
 * 1.实现一个随机样本产生器
 * 2.实现一个绝对正确但是复杂度不好的方法b，也可以用系统提供的方法
 * 3.实现比对的方法 进行大样本测试比对
 * 4.如果有一个样本使得比对出错，打印样本并调试分析哪个方法出错
 * 5.当样本数量很多比对测试依然正确，可以确定要验证的方法已经正确
 * 应用：最好准备一个随机样本发生器的模板 二叉树、数组
 *
 *
 * 递归行为时间复杂度 master公式:
 * T(N) = a*T(N/b) + O(N^d)
 * 1) log(b,a) > d -> 复杂度为O(N^log(b,a))
 * 2) log(b,a) = d -> 复杂度为O(N^d * logN)
 * 3) log(b,a) < d -> 复杂度为O(N^d)
 *
 *
 *  工程中的综合排序算法：
 *  一.1.如果数组长度很短(长度小于60) 会优先选择插排
 *  二.1.如果数据类型是基础类型 优先选择快排
 *     2.如果是引用类型 优先选择归并排序
 *
 */
public class SortUtil {


    /**
     * 冒泡排序
     * 时间复杂度：(N-1)+(N-2)+..+1 等差数列 S(n)=a*n^2+b*n+1
     * 根据规则 不要低阶项，只要高阶项，忽略常数和系数 简化后为O(N^2)，
     * 额外空间复杂度：O(1)
     * 可以做到算法稳定性
     *
     * 流程：
     * 每次从0位置开始和后面的数依次比较 后面的数小就和前面的数交换 最后比较到末尾 找到最大的数排在末尾
     * 下一次从0位置比较到n-2位置 找到第二大的数放在n-2位置，以此类推。
     *
     *
     * @param arr 待排序数组
     */
    public static void bubbleSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = arr.length - 1; i > 0; i--) {
            for (int j = 0; j < i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr, j, j + 1);
                }
            }
        }
    }


    /**
     * 选择排序
     * 时间复杂度O(N^2)，额外空间复杂度O(1)
     * 不可以做到算法稳定性
     *
     * 流程：
     * 依次从0~N-1位置上找到一个最小的数 放到0位置
     * 然后在1~N-1位置上找到第二小的数 放到1位置 以此类推
     *
     *
     * @param arr 待排序数组
     */
    public static void selectSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }

        for (int i = 0; i < arr.length - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                minIndex = arr[j] < arr[minIndex] ? j : minIndex;
            }
            swap(arr, i, minIndex);
        }

    }

    /**
     * 插入排序
     * 时间复杂度 因为插入排序的常数操作和数据状况有关 存在最好情况最差情况和平均情况 如整个数组是升序的
     * 则为O(N) 整个数组是降序的则为0(N^2)，一个算法的时间复杂度当与数据状况有关时一律按最差的估计0(N^2)
     * 额外空间复杂度O(1)
     * 可以做到算法稳定性
     *
     * 流程：
     * 假如前面已经是排好序的数组 新插入进来的数和前面的数比较 如果比前面数小就和前面的数交换 否则不交换
     *
     * @param arr 待排序数组
     */
    public static void insertionSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0 && arr[j] > arr[j + 1]; j--) {
                swap(arr, j, j + 1);
            }
        }
    }
    public static void insertionSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        for (int i = 1; i < arr.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if ( arr[j + 1]<arr[j]) {
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
    }


    /**
     * 归并排序-分治思想
     * 时间复杂度O(N*logN)，额外空间复杂度O(N) (注：额外空间复杂度可以变成0(1) 归并排序内部缓存法 了解即可)
     * 可以做到算法稳定性
     * @param arr 待排序数组
     *
     * 流程：先左侧部分排好序 然后右侧部分排好序 最后整体用外排的方式在辅助数组排好序 然后拷贝回原数组
     *
     */
    public static void mergeSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        sortProcess(arr, 0, arr.length - 1);
    }

    /**
     * 归并排序
     *
     * @param arr 待排序数组
     * @param l   数组左边起始位置
     * @param r   数组右边终止位置
     */
    public static void sortProcess(Integer[] arr, int l, int r) {
        //子过程范围只有1个数 直接返回
        if (l == r) {
            return;
        }

        //这样防止求中间位置时越界 >>1右移一位相当于除2
        int mid = l + ((r - l) >> 1);
        sortProcess(arr, l, mid);
        sortProcess(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }

    /**
     * 归并排序
     * 最终的左右两边子序列合在一起排好序
     *
     * @param arr 待排序数组
     * @param L   数组左边起始位置
     * @param m   数组中间位置
     * @param R   数组右边终止位置
     *
     *  时间复杂度套进master公式的表示为：
     *   T(N)=2*T(N/2)+O(N^1)
     *   即a=2,b=2,d=1 满足log(b,a)=d
     *   所以归并排序时间复杂度为:0(N*LogN)
     */
    public static void merge(Integer[] arr, int L, int m, int R) {
        //生成一个辅助数组
        Integer[] help = new Integer[R - L + 1];
        //辅助数组角标起始位
        int i = 0;
        //左子序指针起始位
        int p1 = L;
        //右子序指针起始位
        int p2 = m + 1;

        //左右子序的指针都没到末尾
        while (p1 <= m && p2 <= R) {
            if(arr[p1]<arr[p2]){
                //如果左子序小 就把左子序数据拿出来填充到help，同时左子序的指针移到到下一个
                help[i]=arr[p1];
                p1++;
            }else{
                help[i]=arr[p2];
                p2++;
            }
            i++;
            //简化写法
//            help[i++]=arr[p1]<arr[p2]?arr[p1++]:arr[p2++];
        }
        //右子序指针先到末尾 把左子序直接拷贝到后面
        while (p1 <= m) {
            help[i++]=arr[p1++];
        }
        //左子序指针先到末尾 把右子序直接拷贝到后面
        while (p2 <= R) {
            help[i++]=arr[p2++];
        }

        //将排序好的help数组 拷贝回待排序数组arr
        for (int j = 0; j < help.length; j++) {
            arr[L+j]=help[j];
        }
    }

    /**
     * 快速排序
     * 随机快排时间复杂度是长期期望值O(N*logN)，额外空间复杂度长期期望值O(logN) 空间浪费在记录划分点上了
     * 不可以做到算法稳定性(注：可以做到算法稳定性“01 stable sort”了解即可)
     *
     * 流程：
     * 1.经典快排：把数组的最后位置数作为划分值 将整个数组分成小于等于区域和大于区域 然后小于等于区域放
     * 左边 大于区域放右边 ，然后继续将左(减去末尾划分点的数)右两部分 分别按各自的最后一个数 继续划分成
     * 两个区域以此递归 经典快排每次只搞定划分点的一个数
     *
     * 2.改进版-类似荷兰国旗的解法，将整个数组按最后一个数 划分成小于区域、等于区域、大于区域三部分 然后
     * 等于区域不动 将小于区域和大于区域继续按最后末尾数划分三部分 以此类推递归下去 改进版每次搞定等于区
     * 域一个区域的数 所以比经典快排快
     *
     * 3.改进版-随机快排 每次取数组中任意一个数 放到数组末尾作为划分值 使排序与样本顺序无关
     *
     *
     * @param arr 待排序数组
     */
    public static void quickSort(Integer[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        quickSort(arr, 0, arr.length - 1);
    }

    /**
     * 快速排序
     *
     * @param arr 待排序数组
     * @param L   数组左边起始位置
     * @param R   数组右边终止位置
     */
    public static void quickSort(Integer[] arr, int L, int R) {
        //数组左边角标不超过右边时，过滤partition出现没有等于区域的无效位置
        if (L < R) {
            //随机取数组中一个数作为因子放置数组末尾,这样算法与原始数据状况无关。加上这行由经典快排变为随机快排序
            swap(arr,L+(int)(Math.random()*(R-L+1)),R);
            //partition分块过程
            Integer[] p =partition(arr,L,R);
            //L~等于区域前一个数位置，即小于区域继续递归排序
            quickSort(arr,L,p[0]-1);
            //等于区域后一个数位置~R，即大于区域继续递归排序
            quickSort(arr,p[1]+1,R);
        }
    }

    /**
     * 注:经典快排是找到小于等于区域和大于区域，然后小于等于区域最后一个位置不动即找到的Num，两边区域的数继续递归，每次只排序一个数
     * 经典快速排序(改进后)partition过程是默认以最后一位(Num)做划分 小于区域放左边，大于区域放右边，返回中间等于区域
     * partition过程：当前待比较的数curr，小于Num区域下一个数less+1位置的数，给定的数Num
     *
     * @param arr
     * @param L
     * @param R
     * @return
     */
    public static Integer[] partition(Integer[] arr, int L, int R) {
        int less =L-1;//默认0~less已经放的是小于Num的数了，所以初始小于等于Num的区域还不存在，即L的前一个位置为起始位
        int more =R;//默认more~R已经放的是大于Num的数了，初始大于Num的区域包含了R位置的数，即R的位置为起始位
        while (L<more){
            //当前数小于Num，当前数和小于区域下一个数交换，小于区域+1，当前数+1
            if(arr[L]<arr[R]){
                swap(arr,++less,L++);
            }else if(arr[L]>arr[R]){
                //当前数大于Num，当前数和大于区域前一个数交换，大于区域-1，当前数不变继续比较
                swap(arr,--more,L);
            }else{
                //相等
                L++;
            }
        }
        //初始大于区域more包含了Num值，没参与比对，所以排序后交换到正确位置(初始包含Num可以省一个变量)
        swap(arr, more, R);
        return new Integer[] { less + 1, more };
    }


    /**
     * 堆排序
     * 时间复杂度O(N*logN) 建立大根堆时间复杂度为Log1+Log2+...+Log(n-1)=O(N)，额外空间复杂度O(1)
     * 不可以做到算法稳定性
     * 将数组在脑海中构建一个完全二叉树 如果一个节点角标是i 则它左子节点角标为2*i+1 右节点2*i+2 父节点(i-1)/2
     *
     * 流程：先将数组heapInsert构建成大根堆，然后将堆顶和最后一个数交换，再heapSize-1 ，再
     * 通过heapify将0~heapSize-1继续调整成大根堆 在将堆顶弹出依次下去。。。 得到从小到大的排序
     *
     *
     * @param arr
     */
    public static void heapSort(Integer[]arr){
        if (arr == null || arr.length < 2) {
            return;
        }

        //依次把i 位置上的数加进来 使0~i之间的数形成大根堆
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr,i);
        }

        int heapSize=arr.length;
        //然后将堆顶和最后一个数交换
        swap(arr,0,--heapSize);
        while (heapSize>0){
            //heapify继续调整变成大根堆
            heapify(arr,0,heapSize);
            //继续将堆顶和最后一个数交换
            swap(arr,0,--heapSize);
        }

    }

    /**
     * 堆排序
     * heapInsert 如果已经形成大根堆 新加入一个节点 如果当前节点比它的父节点大 则和父节点交换，然后继续
     * 和新的父节点比较。。重复下去 新城新的大根堆
     * @param arr 原始数组
     * @param i 加进来的数
     */
    private static void heapInsert(Integer[] arr, int i) {
        //兼顾边界0问题
        while(arr[i]>arr[(i-1)/2]){
            swap(arr,i,(i-1)/2);
            i = (i-1)/2;
        }
    }

    /**
     * 堆排序
     * heapify 如果当前堆中某一个节点i变小了,导致i应该往下边沉 即将当前节点和它左右两个孩子中最大的交换
     * 然后继续和新的左右两个孩子最大值比较 依次重复下去形成新的大根堆
     * @param arr 原始数组
     * @param i 变化的节点
     * @param heapSize 数组中从0开始到任何一位置heapSize-1 某一段是堆即堆的有效区域边界
     */
    private static void heapify(Integer[] arr, int i,int heapSize) {
       int left=i*2+1;
       //左孩子的下标没越界还在堆上
       while(left<heapSize){
            //取左右两个孩子中的大的下标 left+1为右孩子  left+1<heapSize右孩子不越界
           int largest = left+1<heapSize && arr[left+1]>arr[left]
                   ?left+1
                   :left;
           //左右两个孩子最大的那个和当前待比较中的最大的
           largest = arr[largest]>arr[i]?largest:i;

           //如果最大的是待比较的自己 则不用下沉
           if(largest==i){
               break;
           }

           //一个值变小 并且孩子中有比它大的数 交换位置继续向下比较
           swap(arr,largest,i);
           i = largest;
           left=i*2+1;
       }
    }

    public static void printArray(int[] arr) {
        if (arr == null) {
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    //不完全准确
    public static void swap2(Integer[] arr, int i, int j) {
        arr[i] = arr[i] ^ arr[j];
        arr[j] = arr[i] ^ arr[j];
        arr[i] = arr[i] ^ arr[j];
    }

    public static void swap(Integer[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


//========================================对数器校验=================================

    public static void logarithmicTest(){
        //对数器测试插入排序写的是否正确
        int testTime=50000;//大样本测试次数
        int size=10;//随机生成的数组最大长度
        int value=100;//数组内的值 -100~100
        boolean succeed=true;

        for (int i = 0; i < testTime; i++) {
            int[] arr1 = generateRandomArray(size, value);
            int[] arr2 = Arrays.copyOf(arr1,arr1.length);
            int[] arr3 = Arrays.copyOf(arr1,arr1.length);

            insertionSort(arr1);
            absolutelyRightMethod(arr2);
            boolean equals = Arrays.equals(arr1, arr2);
            if (!equals) {
                succeed=false;
                printArray(arr3);
                break;
            }
        }

        System.out.println(succeed?"Nice!":"Fucking fucked!");

//        int[] arr= new int[]{8,-54,20};
    }


    /**
     * 实现一个随机样本产生器
     * @param maxSize 数组最大长度
     * @param maxValue 数组内最大值
     * @return
     */
    public static int[] generateRandomArray(int maxSize,int maxValue){
        //生成一个随机长度的数组
        int[] arr=new int[(int) ((maxSize+1)*Math.random())];
        for (int i = 0; i < arr.length; i++) {
            //数组内每一个值也是随机的 即-maxValue 到 maxValue
            arr[i]=(int) ((maxValue + 1) * Math.random()) - (int) (maxValue * Math.random());
        }
        return arr;
    }

    /**
     * 绝对正确的排序方法
     * @param arr 待排序数组
     */
    public static void absolutelyRightMethod(int[] arr){
        Arrays.sort(arr);
    }


}
