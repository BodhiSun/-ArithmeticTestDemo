package com.bodhi.seniorcourse;

import java.util.HashMap;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/29 10:39
 * desc :设计LFU缓存结构
 *
 * 【题目】
 * 设计LFU缓存算法 实现LFU中的set 和 get
 *
 * 【要求】
 * 1．set和get方法的时间复杂度为O(1)。
 * 2．根据get、set操作次数决定优先级。
 * 3．同样次数，最不经常访问的先出去。
 *
 *
 * 设计思路:两个双向链表
 * 首先是一个尾部大头部小的存储1次、2次...n次的大双向链表 每个节点代表key出现的对应次数，当Set和Get的时候
 * 先检查大双向链表是否存在对应的key 如果没有put就存在次数为1的大链表节点下面的小链表上 get就返回null,如果
 * 有，找到属于大链表上对应的那个节点，然后将小链表中对应的key从小小链表中分离出来，查看大链表节点的下一个
 * 节点是否是当前节点次数+1的关系，是的话就直接将key插到新的大链表节点上的小链表尾部上 不是就新建一个大链
 * 表节点，当前节点下面的小链表如果没有数据了 直接删除掉大链表上的当前节点，大链表节点上每个小链表是头进尾
 * 出 虽次数一样但是头部优先级高 尾部优先级低，所以当达到容量时 删大链表头部下面小链表的尾部节点即可
 *
 *
 *
 */
public class LfuCache_Structure {

    /**
     * 小链表节点
     */
    public static class Node{
        public  Integer key;
        public Integer value;
        public Integer times;
        public Node up;
        public Node down;

        public Node(Integer key, Integer value, Integer times) {
            this.key = key;
            this.value = value;
            this.times = times;
        }
    }

    public static class LFUCache{

        /**
         * 自定义大链表节点
         */
        public static class NodeList{
            //大链表节点下面小链表的头尾节点
            public Node head;
            public Node tail;

            //大链表节点本身的前后节点
            public NodeList last;
            public NodeList next;

            //初始化大链表节点 有小链表的数据才会创建大链表的节点
            public NodeList(Node node){
                head =node;
                tail=node;
            }

            /**
             * 往此大链表节点下面的小链表头部添加新节点
             * @param newHead
             */
            public void addNodeFromHead(Node newHead){
                newHead.down =head;
                head.up =newHead;
                head = newHead;
            }

            /**
             * 判断此大链表的节点下面的小链表是否为空
             * @return
             */
            public boolean isEmpty(){
                return head ==null;
            }

            /**
             * 当达到容量上限或者调用get/set时 把节点从小链表中分离
             */
            public void deleteNode(Node node){
                //小链表只有一个节点时
                if(head==tail){
                    head=null;
                    tail=null;
                }else{
                    if(node==head){
                        head = node.down;
                        head.up=null;
                    }else if(node==tail){
                        tail = node.up;
                        tail.down=null;
                    }else{
                        //在小链表中间的任意节点
                        node.up.down =node.down;
                        node.down.up=node.up;
                    }
                }
                node.up=null;
                node.down=null;
            }
        }

        //初始化给定的缓存容量大小
        private int capacity;
        //当前存的数量
        private int size;
        //存对应的key和k,v构成的节点Node作为value 通过key找Node
        private HashMap<Integer,Node> records;
        //通过小链表上的节点Node 找到对应的当前所在大链表上的节点
        private HashMap<Node,NodeList> heads;
        //当前构成的大链表中的头结点
        private NodeList headList;

        //初始化构造容量大小
        public LFUCache(int capacity){
            this.capacity=capacity;
            this.size=0;
            this.records = new HashMap<>();
            this.heads =new HashMap<>();
            headList=null;
        }

        /**
         * 向缓存中添加数据 有数据就修改value 并更改次数优先级
         * 没有数据就新建小链表节点 新建大链表节点 然后首尾连上
         * @param key
         * @param value
         */
        public void set(int key,int value){
            if(records.containsKey(key)){
                Node node = records.get(key);
                node.value=value;
                node.times++;
                //根据小链表节点找到对应的大链表节点
                NodeList curNodeList = heads.get(node);
                //将当前小链表对应节点移动到下一个大链表节点上
                move(node,curNodeList);
            }else{
                //如果缓存里没有对应数据 则新添加数据
                if(size==capacity){
                    //当前容量已经满了的时候先删除大链表上头结点上小链表末尾数据 才能继续添加
                    Node node=headList.tail;
                    headList.deleteNode(node);
                    //如果大链表头结点没有数据时删除头结点 并更新头结点
                    modifyHeadList(headList);

                    records.remove(node.key);
                    heads.remove(node);
                    size--;
                }

                //添加新数据
                Node node = new Node(key,value,1);
                //如果大链表为空 即从没有添加过数据 实例化头节点
                if(headList==null){
                    headList = new NodeList(node);
                }else{
                    //当大链表有数据时 并且头结点是次数为1的节点直接插入数据
                    if(headList.head.times.equals(node.times)){
                        headList.addNodeFromHead(node);
                    }else{
                        //头结点不是次数为1的节点 创建新的节点并更新头指针
                        NodeList newList = new NodeList(node);
                        newList.next = headList;
                        headList.last =newList;
                        headList = newList;
                    }
                }
                records.put(key,node);
                heads.put(node,headList);
                size++;
            }
        }


        /**
         * 查询缓存中key对应的value值 同时更新key的优先级
         * @param key
         * @return
         */
        public int get(int key){
            if(!records.containsKey(key)){
                //-1表示key不存在
                return -1;
            }
            Node node = records.get(key);
            //因为调用get操作 优先级要调高
            node.times++;
            NodeList nodeList = heads.get(node);
            move(node,nodeList);

            return node.value;


        }

        /**
         * 当前小链表删除节点后判断 如果大链表当前结点没有数据了则删除该结点 并重新连接大链表
         * 返回是否删除当前大链表的节点
         * @param nodeList
         */
        private boolean modifyHeadList(NodeList nodeList) {
            //删除节点后当前大链表节点上的小链表为空时
            if(nodeList.isEmpty()){
                //如果当前节点为大链表的头结点时
                if(headList==nodeList){
                    headList = nodeList.next;
                    if(headList!=null){
                        headList.last=null;
                    }
                }else{
                    nodeList.last.next = nodeList.next;
                    if(nodeList.next!=null){
                        nodeList.next.last = nodeList.last;
                    }
                }
                return true;
            }

            //不为空则不需要删除此节点 返回false
            return false;
        }

        /**
         * 将当前大链表上的小链表上对应节点 移动到下一个大链表节点上的小链表上
         * 并且当前大链表节点如果没有数据则删除此节点 下一个大链表节点没有则新建节点
         * @param node
         * @param oldNodeList
         */
        private void move(Node node, NodeList oldNodeList) {
            //想将此节点从原先的小链表上删除
            oldNodeList.deleteNode(node);
            //判断删除节点后小链表是否被删除
            NodeList preList=modifyHeadList(oldNodeList)?oldNodeList.last:oldNodeList;
            NodeList nextList = oldNodeList.next;
            //如果即将移动到的大链表节点不存在的话 新建大链表节点 并将大链表前后连接好
            if(nextList==null){
                NodeList newList = new NodeList(node);
                if(preList!=null){
                    preList.next=newList;
                }
                newList.last = preList;

                if(headList==null){
                    headList = newList;
                }
                heads.put(node,newList);
            }else{
                //即将移动到的节点不是大链表中最高的词频 则判断词频然后插入或新建
                if(nextList.head.times.equals(node.times)){
                    nextList.addNodeFromHead(node);
                    heads.put(node,nextList);
                }else{
                    //新建节点然后前后重连
                    NodeList newList = new NodeList(node);
                    if(preList!=null){
                        preList.next=newList;
                    }
                    newList.last=preList;
                    newList.next =nextList;
                    nextList.last=newList;

                    if(headList==nextList){
                        headList=newList;
                    }
                    heads.put(node,newList);
                }
            }
        }

    }

    public static void test(){
        LFUCache testCache = new LFUCache(2);
        testCache.set(1, 1);
        testCache.set(2, 2);
        System.out.println(testCache.get(1));
        testCache.set(3, 3);
        System.out.println(testCache.get(2));
        System.out.println(testCache.get(3));
        testCache.set(4, 4);
        System.out.println(testCache.get(1));
        System.out.println(testCache.get(3));
        System.out.println(testCache.get(4));
    }

}
