package com.bodhi.seniorcourse;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/8/30 15:11
 * desc :跳表结构
 *
 * 认识跳表：
 * 和BBS红黑树等平衡搜索二叉树实现的功能类似(找最大的/最小的K,找比K大/小的离他最近的key) CRUD代价也是logn,
 * 但是底层不是树结构实现的。跳表在原有的有序链表上面增加了多级索引，它采用随机技术决定链表中哪些节点应增
 * 加向前指针以及在该节点中应增加少个指针，相当于就是一种可以进行二分查找的有序链表。
 *
 */
public class SkipList_Structure {

    /**
     * 跳表的节点
     */
    public static class SkipListNode{
        //当前节点的值
        public Integer value;
        //不能层级的索引的下一个节点 nextNodes.size就是当前节点的层级数 nextNodes[0]当前节点第一层的下一个节点
        public ArrayList<SkipListNode> nextNodes;

        public SkipListNode(Integer value){
            this.value=value;
            nextNodes = new ArrayList<>();
        }
    }

    /**
     * 跳表结构(多级索引有序链表)
     */
    public  static class SkipList{
        //跳表的头节点，始终和最大层数的节点层数相同 不存数据默认值为系统最小值或空值
        private SkipListNode head;
        //跳表最大层数
        private int maxLevel;
        //跳表的节点数
        private int size;
        //每个节点是否增加层的概率边界
        private static final double PROBABILITY=0.5;

        //构造函数 初始各个成员变量的默认值
        public SkipList(){
            size=0;
            maxLevel=0;
            head = new SkipListNode(null);
            head.nextNodes.add(null);
        }


        /**
         * 向跳表中添加数据
         * @param newValue
         */
        public void add(Integer newValue){
            //不包含的情况下才添加数据
            if(!contains(newValue)){
                //添加数据更新跳表中的节点数
                size++;
                //随机当前节点分配的层数
                int level =0;
                while (Math.random()<PROBABILITY){
                    level++;
                }
                //如果新节点分配的层数超过跳表的层数 更新跳表头节点和层高
                while (level>maxLevel){
                    head.nextNodes.add(null);
                    maxLevel++;
                }
                //构建新节点
                SkipListNode newNode = new SkipListNode(newValue);
                //给新节点不同层数上 前后连接对应节点
                SkipListNode current = head;//从头节点向右开始
                int levelAll =maxLevel;//从最高层向下开始遍历
                do {
                    //找到levelAll层上小于newValue的最大节点
                    current = findNext(newValue,current,levelAll);
                    //从上倒下开始查找 当遍历到level时准备插入节点 并前后连接上
                    if(levelAll<=level){
                        //将新节点的第0层下个节点指向 current节点level层的下个节点,因为每次都是往0层插入
                        //所以会一层一层往上赶，最后回变成正确的对应的层级关系
                        newNode.nextNodes.add(0,current.nextNodes.get(level));
                        //将current节点的llevel层的下个节点设置层newNode
                        current.nextNodes.set(level,newNode);
                        level--;
                    }

                }while (levelAll-- >0);//maxLevel从上向下一直遍历到0层
            }
        }

        /**
         * 删除跳表中指定的值(有问题)
         * @param deleteValue
         */
        public void delete(Integer deleteValue){
            //跳表中存在才删除
            if (contains(deleteValue)) {
                //找到当前值对应的节点
                SkipListNode deleteNode = find(deleteValue);
                //删除节点跳表节点数减小
                size--;
                //从头节点和最高层开始遍历
                int level = maxLevel;
                SkipListNode current = head;
                do{
                    //找到小于要删除节点的最大节点
                    current = findNext(deleteNode.value, current, level);
                    //当要删除的节点下一个节点层级大于当前节点时 才将前后节点重连
                    if (deleteNode.nextNodes.size() > level) {
                        current.nextNodes.set(level, deleteNode.nextNodes.get(level));
                    }
                } while (level-- > 0);
            }
        }


        /**
         * 获得跳表的头节点
         * @return
         */
        public SkipListNode getHead(){
            return head;
        }

        /**
         * 返回跳表中最大的小于等于给定值e的节点
         * @param e
         * @return
         */
        private SkipListNode find(Integer e){
            return find(e, head, maxLevel);
        }

        /**
         * 返回跳表中给定 开始节点和层级 后的 最大的小于等于给定值e的节点
         * @param e
         * @param current
         * @param level
         * @return
         */
        private SkipListNode find(Integer e, SkipListNode current, int level) {
            do {
                current = findNext(e, current, level);
            } while (level-- > 0);
            return current;
        }

        /**
         * 返回在给定的开始节点的level层级中 右边最大的小于给定值e的 level层节点
         * @param e
         * @param current 当前节点值一定小于e
         * @param level 给定的层级
         * @return
         */
        private SkipListNode findNext(Integer e, SkipListNode current, int level) {
            //当前节点第level层的下一个节点
            SkipListNode next = current.nextNodes.get(level);
            while (next != null) {
                //当前节点第level层的下一个节点值
                Integer value = next.value;
                //如果在level层上当前节点的下一个节点值大于给定值则 当前节点就是在level层上要找的节点
                if (lessThan(e, value)) {
                    break;
                }
                //否则跳到本层上对应的下一个节点继续比较
                current = next;
                next = current.nextNodes.get(level);
            }
            return current;
        }

        public int size() {
            return size;
        }

        /**
         * 查找当前跳表是否包含指定数
         * @param value
         * @return
         */
        public boolean contains(Integer value){
            SkipListNode node = find(value);
            return node != null && node.value != null && equalTo(node.value, value);
        }

        /**
         * 比较两个整型的包装类 e是否小于b
         */
        private boolean lessThan(Integer e, Integer b) {
            return e.compareTo(b)<0;
        }

        /**
         * 比较两个整型的包装类是否相等
         * @return
         */
        private boolean equalTo(Integer a, Integer b){
            return a.compareTo(b)==0;
        }


        public Iterator<Integer> iterator() {
            return new SkipListIterator(this);
        }
    }

    public static class SkipListIterator implements Iterator<Integer>{
        SkipList list;
        SkipListNode current;

        public SkipListIterator(SkipList skipList){
            this.list=skipList;
            this.current=list.getHead();
        }


        @Override
        public boolean hasNext() {
            return current.nextNodes.get(0)!=null;
        }

        @Override
        public Integer next() {
            current= current.nextNodes.get(0);
            return current.value;
        }
    }

    public static void test(){
        SkipList skipList = new SkipList();
        skipList.add(10);
        skipList.add(2);
        skipList.add(16);
        skipList.add(58);

//        Iterator<Integer> iterator = skipList.iterator();
//        while (iterator.hasNext()){
//            System.out.print(iterator.next()+",");
//        }
//        System.out.println();

        skipList.delete(16);
        skipList.delete(100);

        Iterator<Integer> iterator = skipList.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next()+",");
        }
        System.out.println();

    }










}
