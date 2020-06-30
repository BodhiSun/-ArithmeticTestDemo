# 算法和数据结构

#### 介绍
常用的算法、数据结构和常用的经典练习题

#### 一、常见的排序算法
##### [基于比较的排序](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/SortUtil.java)
1. 冒泡排序
2. 选择排序
3. 插入排序
4. 归并排序
5. 快速排序
6. 堆排序
##### [非基于比较的排序](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/SortUtil2.java)
1. 桶排序
2. 计数排序
3. 基数排序

##### 几种排序算法的比较
<img src="https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/res/mipmap-xhdpi/digest.png" width="750" />
&nbsp;口诀：选堆归基不变(时间复杂度最好最坏一样)、选堆快希不稳(稳定性不稳)

##### 工程中的综合排序算法：
1. 如果数组长度很短(长度小于60) 会优先选择插排
2. 当大于60如果数据类型是基础类型 优先选择快排
3. 当大于60如果是引用类型 优先选择归并排序

#### 二、递归行为时间复杂度计算 master公式:
&nbsp;&nbsp;&nbsp;&nbsp;T(N) = a*T(N/b) + O(N^d)
1. log(b,a) > d -> 复杂度为O(N^log(b,a))
2. log(b,a) = d -> 复杂度为O(N^d * logN)
3. log(b,a) < d -> 复杂度为O(N^d)

#### 三、验证算法是否正确除了在线的判题系统也可以通过对数器校验
##### 对数器作用：
1. 验证一个方法a是否正确
2. 验证贪心策略是否正确
##### 对数器使用步骤：
1. 实现一个随机样本产生器(二叉树、数组)
2. 实现一个绝对正确但是复杂度不好的方法b，也可以用系统提供的方法
3. 实现比对的方法 进行大样本测试比对
4. 如果有一个样本使得比对出错，打印样本并调试分析哪个方法出错
5. 当样本数量很多比对测试依然正确，可以确定要验证的方法已经正确


#### 四、经典算法、数据结构、练习题

##### 1.经典算法
1. [KMP算法](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/KMP_Arithmetic.java)：KMP算法-在0(n)时间复杂度内解决两个字符串str1,str2包含的问题 str2是str1中的子串返回在str1中str2开始的位置
2. [Manacher算法](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/Manacher_Arithmetic.java)：Manacher算法-在0(n)时间复杂度内处理字符串str中关于回文串的问题 即str中最长回文子串长度
3. [BFPRT算法](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/BFPRT_Arithmetic.java)：BFPRT算法-在一个无序数组中在0(n)时间复杂度内找到第K小的数或第K大的数

##### 2.数据结构
1. [滑动窗口结构](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/Window_Structure.java)
2. [单调栈结构](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/SingleStack_Structure.java)
3. [跳表结构](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/SkipList_Structure.java)
4. [LRU缓存结构](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/LruCache_Structure.java)
5. [LFU缓存结构](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/LfuCache_Structure.java)
6. [搜索二叉树](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/BinarySearchTree_Structure.java)
7. [平衡搜索二叉树](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/BalanceBinarySearchTree_Structure.java)
8. [布隆过滤器](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/BloomFilter.java)
9. [哈希表](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/HashPractice_HashFunctionHashTable.java)
10. [一致性哈希](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/HashPractice_SameHash.java)
11. [查集结构](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/HashPractice_UnionFind.java)
12. [前缀树](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_TrieTree.java)

##### 3.练习题
###### 1.堆栈练习
1. 用数组结构实现大小固定的队列和栈[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/HeapPractice_ArrayToStackAndQueue.java)
2. 实现一个特殊的栈，在实现栈的基本功能的基础上，再实现返 回栈中最小元素的操作。[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/HeapPractice_GetMinStack.java)
3. 如何仅用队列结构实现栈结构？如何仅用栈结构实现队列结构？[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/HeapPractice_StackAndQueueConvert.java)

###### 2.队列练习
1. 提供一个宠物类，猫类和狗类 要实现一个队列结构 单独取所有的猫或所有的狗，或按猫狗先后顺序依次取出[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/QueuePractice_DogCatQueue.java)

###### 3.矩阵练习
1. 在行列都排好序的矩阵中找数[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/MatrixPractice_FindNum.java)
2. 旋转正方形矩阵[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/MatrixPractice_RotateMatrix.java)
3. 转圈打印矩阵[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/MatrixPractice_RotatePrint.java)
4. “之”字形打印矩阵[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/MatrixPractice_ZigZagPrintMatrix.java)
5. 岛问题[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/MatrixPractice_Islands.java)

###### 4.链表练习
1. 复制含有随机指针节点的链表[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/LinkListPractice_CopyListWithRandom.java)
2. 两个单链表相交的一系列问题[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/LinkListPractice_FindFirstIntersectNode.java)
3. 判断一个链表是否为回文结构[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/LinkListPractice_isPalindrome.java)
4. 打印两个有序链表的公共部分[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/LinkListPractice_PrintCommonPart.java)
5. 反转单向和双向链表[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/LinkListPractice_ReverseList.java)
6. 将单向链表按某值划分成左边小、中间相等、右边大的形式[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/LinkListPractice_SmallerEqualBigger.java)
7. 环形单链表的约瑟夫问题(经典题目 难度很高 尽量理解)[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/LinkListPractice_JosephusProblem.java)

###### 5.二叉树练习
1. 实现二叉树的先序、中序、后序遍历，包括递归方式和非递归方式[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_PreInPosTraversal.java)
2. 已知一棵完全二叉树，求其节点的个数[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_CompleteTreeNodeNumber.java)
3. 判断一棵二叉树是否是平衡二叉树[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_IsBalancedTree.java)
4. 判断一棵树是否是搜索二叉树、判断一棵树是否是完全二叉树[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_IsBSTAndCBT.java)
5. 直观的打印一颗二叉树(空间感还原的打印方式)[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_PrintBinaryTree.java)
6. 二叉树的序列化和反序列化[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_SerializeAndDeserialize.java)
7. 在二叉树中找到一个节点的后继节点[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_SuccessorNode.java)
8. 前缀树[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/TreePractice_TrieTree.java)
9. 最大搜索二叉子树的大小[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/TreePractice_LargestSubBS.java)
10. 返回二叉树中的最大值和最小值[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/TreePractice_MaxAndMinNum.java)
11. 二叉树上的最大距离[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/TreePractice_MaxDistance.java)
12. 公司参加晚会的最大活跃指数[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/TreePractice_MaxHappy.java)
13. 二叉树-Morris遍历[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/TreePractice_MorrisTraversal.java)

###### 6.哈希练习
1. 设计RandomPool随机池结构[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/HashPractice_RandomPool.java)

###### 7.贪心练习
1. 最小字典序[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/GreedyPractice_LowestLexicography.java)
2. 切金条用最小的费用 哈夫曼编码[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/GreedyPractice_GoldBarLessCost.java)
3. 实安排会议的场次最多[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/GreedyPractice_MoreMeetingArrange.java)
4. 项目达到最大收益IPO[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/GreedyPractice_ProjectMoreMoney.java)

###### 8.暴力递归
1. n!求n的阶乘 即1*2*3*...*n[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/ViolenceRecursive_Factorial.java)
2. 汉诺塔问题 总步数为2^n - 1[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/ViolenceRecursive_Hanoi.java)
3. 打印一个字符串的全部子序列，包括空字符串[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/ViolenceRecursive_PrintAllSubsquence.java)
4. 打印一个字符串的全部排列[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/ViolenceRecursive_PrintAllPermutation.java)
5. 母牛生小牛求牛的总数量[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/ViolenceRecursive_Cow.java)

###### 9.动态规划
1. 求二维数组最小路径[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/ViolenceRecursiveDP_MinPath.java)
2. 求数组中任意组合得到指定值[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/java/com/bodhi/arithmetictestdemo/practice/ViolenceRecursiveDP_AddToEqualAim.java)
3. 排成一条线的纸牌博弈问题[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/ViolenceRecursiveDP_PokerGame.java)
4. 字符串正则匹配问题(难度很高)[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/ViolenceRecursiveDP_RegularMatch.java)
5. 机器人多少个路径[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/ViolenceRecursiveDP_RobotWalk.java)
6. 换钱的方法数[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/ViolenceRecursiveDP_TransMoney.java)

###### 10.经典算法和数据结构练习
1. Kmp练习:一个字符串只能在后面添加字符变成一个大的字符串 要求添加最少的字符 使之大字符串变成包含两个原始串[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/KmpPractice_ShortestHaveTwice.java)
2. Kmp练习:有两棵树T1，T2,求T1中某一颗子树是否包含T2[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/KmpPractice_Tree1SubEqualTree2.java)
3. Manacher练习:给定一个字符串 只能向后面添加字符 要求添加最短的字符使之成为回文串[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/ManacherPractice_ShortestEnd.java)
4. Bfprt练习:在一个无序数组中找出前K小的数[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/BFPRT_Arithmetic.java)
5. 滑动窗口练习:最大值减去最小值小于或等于num的子数组数量[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/WindowPractice_MinEqualNumSubArr.java)
6. 滑动窗口练习:生成滑动窗口最大值数组[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/WindowPractice_SlidWinMaxNumArr.java)
7. 单调栈练习:求数组代表的环形山中能相互看见的山最多有多少对[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/SingleStackP_MaxPeakPairNum.java)
8. 单调栈练习:求数组代表的直方图中最大子矩形面积大小[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/SingleStackP_MaxRecAreaFromArr.java)
9. 单调栈练习:求二维数组最大子矩阵大小[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/SingleStackP_MaxSubMatrixNum.java)

###### 11.字符串数组练习
1. 大楼边际线问题[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/Practice_Building_Outline.java)
2. 给定一个字符串公式返回计算结果[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/Practice_FormulaCompute.java)
3. 给定一个数组arr（有0、正、负），和一个整数aim，求在arr中，累加和等于aim的最长子数组的长度[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/Practice_LongestSumSubArray.java)
4. 给定一个数组arr，注意全是正数；一个整数aim，求累加和等于aim的，最长子数组[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/Practice_LongestSumSubArray2.java)
5. 给定一个数组arr，值可正，可负，可0；一个整数aim，求累加和小于等于aim的，最长子数组[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/Practice_LongestSumSubArray3.java)
6. 数组最大异或和[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/Practice_MaxEORSum.java)
7. 最多异或和为0的子数组[Go](https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/seniorcourse/src/main/java/com/bodhi/seniorcourse/practice/Practice_MostEorNum.java)

