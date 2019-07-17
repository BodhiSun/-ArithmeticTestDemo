package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/17 15:18
 * desc : 用数组结构实现大小固定的队列和栈
 */
public class HeapPractice_ArrayToStackAndQueue {

    public static class ArrayStack{
        private Integer[] arr;//带实现的数组
        private Integer index;//栈中数据的个数 也是新添加的数即将填入的位置

        public ArrayStack(int initSize){
           if(initSize<0){
               throw new IllegalArgumentException("The init size is less than 0");
           }
           arr=new Integer[initSize];
            index=0;
        }

        //压入栈
        public void push(int obj){
            //说明栈中数据个数已经等于数组长度不能再添加新数据了
            if(index==arr.length){
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }
            //index 位置填上新数据 index++
            arr[index++]=obj;
        }

        //弹出栈
        public Integer pop(){
            //说明栈中已经没有数据了 不能再弹出新数据了
            if(index==0){
                new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            return arr[--index];
        }

        //查询栈顶数据
        public Integer peek(){
            if(index==0){
                return null;
            }
            return arr[index-1];
        }
    }

    public static class ArrayQueue{
        private Integer[] arr;
        private Integer size;//当前队列中数据的个数 增加size这个字段使取数据start和加数据end之间解耦 存取数据之和size做校验即可
        private Integer start;//每次取收据对应的位置
        private Integer end;//每次加数据对应的位置

        public ArrayQueue(int initSize){
            if(initSize<0){
                throw new IllegalArgumentException("The init size is less than 0");
            }
            arr = new Integer[initSize];
            size=0;
            start=0;
            end=0;
        }

        public Integer peek(){
            if(size==0){
                return null;
            }
            return arr[start];
        }

        public void push(int obj){
            if(size==arr.length){
                throw new ArrayIndexOutOfBoundsException("The queue is full");
            }

            size++;
            //将数据填充到end位置
            arr[end]=obj;
            //如果end已经到了最底的位置 end回到0位置 否则end移动到向下+1
            end=end==arr.length-1?0:end+1;
        }

        public Integer poll(){
            if(size==0){
                throw new ArrayIndexOutOfBoundsException("The queue is empty");
            }
            size--;
            int tmp =start;
            start =start==arr.length-1?0:start+1;
            return arr[tmp];
        }


    }
}
