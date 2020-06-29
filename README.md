# ArithmeticTestDemo

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

##### 3.练习题
1. KMP练习：一个字符串只能在后面添加字符变成一个大的字符串 要求添加最少的字符 使之大字符串变成包含两个原始串
2. KMP练习：有两棵树T1，T2,求T1中某一颗子树是否包含T2
3. 跳表结构
4. LRU缓存结构
5. LFU缓存结构
6. 搜索二叉树
7. 平衡搜索二叉树

4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
