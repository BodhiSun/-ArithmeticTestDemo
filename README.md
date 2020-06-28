# ArithmeticTestDemo

#### 介绍
常用的算法、数据结构和常用的经典练习题

#### 一、常见的排序算法
##### 基于比较的排序
1. 冒泡排序
2. 选择排序
3. 插入排序
4. 归并排序
5. 快速排序
6. 堆排序
##### 非基于比较的排序
1. 桶排序
2. 计数排序
3. 基数排序

##### 几种排序算法的比较
<img src="https://github.com/BodhiSun/ArithmeticTestDemo/blob/master/app/src/main/res/mipmap-xhdpi/digest.png" width="750" />
选堆归基不变(时间复杂度最好最坏一样)、选堆快希不稳(稳定性不稳)

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



#### 码云特技

1. 使用 Readme\_XXX.md 来支持不同的语言，例如 Readme\_en.md, Readme\_zh.md
2. 码云官方博客 [blog.gitee.com](https://blog.gitee.com)
3. 你可以 [https://gitee.com/explore](https://gitee.com/explore) 这个地址来了解码云上的优秀开源项目
4. [GVP](https://gitee.com/gvp) 全称是码云最有价值开源项目，是码云综合评定出的优秀开源项目
5. 码云官方提供的使用手册 [https://gitee.com/help](https://gitee.com/help)
6. 码云封面人物是一档用来展示码云会员风采的栏目 [https://gitee.com/gitee-stars/](https://gitee.com/gitee-stars/)
