package com.bodhi.seniorcourse;

import android.print.PrinterId;

import java.util.HashMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/29 10:39
 * desc :设计LRU缓存结构
 *
 * 【题目】
 * 设计一种缓存结构，该结构在构造时确定大小，假设大小为K，并有两个功能：
 * set(key,value)：将记录(key,value)插入该结构。
 * get(key)：返回key对应的value值。
 * 【要求】
 * 1．set和get方法的时间复杂度为O(1)。
 * 2．某个key的set或get操作一旦发生，认为这个key的记录成了最经常使用的。
 * 3．当缓存的大小超过K时，移除最不经常使用的记录，即set或get最久远的。
 *
 *
 * 设计思路:哈希表+定制双向链表
 * 哈希表用HashMap 存对应的key和k,v构成的节点作为value 哈希表主要是用来查询是否存在某key以及修改value
 * 定制的双向链表尾进头出 尾部优先级最高头部最低 删除的时候可以先在双向链表通过头部指针把优先级最低的删除
 * 然后在根据key到哈希表里面找到对应的数据彻底删除，调用get/set时 根据哈希表key找到双向链表中对应节点，先
 * 把节点从环境分离，挂到最后，再重连其他节点 即将此key调整到最高优先级。
 *
 */
public class LruCache_Structure {

    /**
     * 双端链表的节点 同时也是哈希表的value类型
     * @param <K>
     * @param <V>
     */
    public static class Node<K,V> {
        public K key;
        public V value;
        public Node<K,V> last;
        public Node<K,V> next;

        public Node(K key,V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * 定制双端链表
     * @param <K>
     * @param <V>
     */
    public static class NodeDoubleLinkedList<K,V>{
        //头指针和尾指针
        private Node<K,V> head;
        private Node<K,V> tail;

        public NodeDoubleLinkedList(){
            this.head = null;
            this.tail = null;
        }

        /**
         * 双端链表加节点
         * @param newNode
         */
        public void addNode(Node<K,V> newNode){
            if (newNode == null) {
                return;
            }

            //如果头结点为空 说明此链表从没加入过数据 这是第一个数据 所以头尾指针都指向它
            if(this.head ==null){
                this.head  = newNode;
                this.tail = newNode;
            }else{
                //最新的节点添加到尾部 并将尾指针指向它
                this.tail.next =newNode;
                newNode.last=this.tail;
                this.tail =newNode;
            }
        }


        /**
         * 当发生get/set时 当前节点优先级应该变为最高 即将节点调整到尾部位置
         * @param node 默认node一定在此双端链表上
         */
        public void moveNodeToTail(Node<K,V> node){
            //如果此节点已经是在末尾最高优先级 则不用动
            if(this.tail==node){
                return ;
            }

            //如果此节点时头结点
            if(this.head==node){
                this.head = node.next;
                head.last = null;
            }else{
                //如果是中间的普遍节点
                node.last.next = node.next;
                node.next.last = node.last;
            }
            node.last = this.tail;
            node.next = null;
            this.tail.next = node;
            this.tail = node;
        }

        /**
         * 容量满了删除头部最不常用的节点 并返回该节点
         * @return
         */
        public Node<K,V> removeHead(){
            //当链表内没数据时直接返回空
            if(this.head ==null){
                return null;
            }

            Node<K,V> res = this.head;
            //当链表中只有一个节点时
            if(this.head==this.tail){
                this.head=null;
                this.tail=null;
            }else{
                this.head = res.next;
                res.next =null;
                this.head.last=null;
            }
            return res;
        }
    }

    public static class MyLruCache<K,V>{
        //准备哈希表
        private HashMap<K,Node<K,V>> kNodeHashMap;
        //准备自定义的双端链表
        private NodeDoubleLinkedList<K,V> nodeDoubleLinkedList;
        //缓存的容量大小
        private int capacity;

        //构造时确定初始化容量大小
        public MyLruCache(int capacity){
            if(capacity<1){
                throw new RuntimeException("the capacity should be more than 0");
            }

            this.kNodeHashMap = new HashMap<>();
            this.nodeDoubleLinkedList = new NodeDoubleLinkedList<>();
            this.capacity=capacity;
        }

        /**
         * get方法 查询缓存中key对应的value值 同时更新key的优先级
         * @param key
         * @return
         */
        public V get(K key){
            //如果缓存中有对应的数据就返回 并将对应的节点优先级调高
            if(this.kNodeHashMap.containsKey(key)){
                Node<K,V> res=this.kNodeHashMap.get(key);
                //对应的节点优先级调高
                this.nodeDoubleLinkedList.moveNodeToTail(res);
                return res.value;
            }
            return null;
        }

        /**
         * set方法 有数据就修改缓存中现有的数据 没有就新建数据 并更新节点的优先级
         * @param key
         * @param value
         */
        public void set(K key,V value){
            //如果有数据就更新缓存数据
            if(this.kNodeHashMap.containsKey(key)){
                Node<K, V> node = this.kNodeHashMap.get(key);
                node.value=value;
                this.nodeDoubleLinkedList.moveNodeToTail(node);
            }else{
                //没有数据就新建并加入数据
                Node<K,V> newNode = new Node<>(key,value);
                this.kNodeHashMap.put(key,newNode);
                this.nodeDoubleLinkedList.addNode(newNode);
                //加入数据后检查是否超过容量 超过移除头部节点
                if(this.kNodeHashMap.size()==this.capacity+1){
                    this.removeMostUnUsedCache();
                }
            }
        }

        /**
         * 移除链表头部节点 并根据链表节点删除哈希表中的数据 彻底删除数据
         */
        private void removeMostUnUsedCache() {
            //移除链表头结点
            Node<K, V> removeNode  = nodeDoubleLinkedList.removeHead();
            //根据链表返回的节点移除哈希表中的数据
            K removeKey =removeNode.key;
            this.kNodeHashMap.remove(removeKey);

        }
    }

    public static void test(){
        MyLruCache<String, Integer> testCache = new MyLruCache<String, Integer>(3);
        testCache.set("A", 1);
        testCache.set("B", 2);
        testCache.set("C", 3);
        System.out.println(testCache.get("B"));
        System.out.println(testCache.get("A"));
        testCache.set("D", 4);
        System.out.println(testCache.get("D"));
        System.out.println(testCache.get("C"));
    }



}
