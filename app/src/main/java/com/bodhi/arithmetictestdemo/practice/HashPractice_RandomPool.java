package com.bodhi.arithmetictestdemo.practice;

import java.util.HashMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/23 15:56
 * desc :设计RandomPool随机池结构
 *
 * 【题目】 设计一种结构，在该结构中有如下三个功能： insert(key)：将某个key加入到该结构，做到不重复加入。
 *  delete(key)：将原本在结构中的某个key移除。 getRandom()： 等概率随机返回结构中的任何一个key。
 * 【要求】 Insert、delete和getRandom方法的时间复杂度都是 O(1)
 *
 * 思路：准备两张hash表 第一张表存k,index 第二张表存index，k
 * 添加操作先判断是否存在
 * 根据第二种表index随机数 实现等概率随机返回结构中的任何一个key
 * 删除操作不做特殊处理 会影响取随机数功能 ，所以删除操作要边删除边填充
 *
 */
public class HashPractice_RandomPool {

    /**
     * 自定义一个类
     */
    public static class RandomPool{
        public HashMap<String,Integer> map1;
        public HashMap<Integer,String> map2;
        public int size;

        public RandomPool(){
            map1 = new HashMap<>();
            map2 = new HashMap<>();
            size=0;
        }

        public void add(String str) {
            //做到不重复加入
            if (!map1.containsKey(str)) {
                map1.put(str, size);
                map2.put(size, str);
                size++;
            }
        }

        public String getRandom(){
            if(size==0){
                return null;
            }
            //根据第二个哈希表 等概率随机返回结构中的任何一个k
            int index = (int) (Math.random()*size);//0~size-1
            return map2.get(index);

        }

        public void delete(String str){
            if(map1.containsKey(str)){
                Integer deleteIndex = map1.get(str);

                //获取map2中最后位置的元素
                int lastIndex = size-1;
                String lastStr = map2.get(lastIndex);

                //相当于把传过来的str 在map1和map2中对应的元素都删除掉 并且用最后末尾的元素填充到对应的位置,为了getRandom不会取到空
                map1.put(lastStr,deleteIndex);
                map2.put(deleteIndex,lastStr);
                map1.remove(str);
                map2.remove(lastIndex);


            }
        }



    }


}
