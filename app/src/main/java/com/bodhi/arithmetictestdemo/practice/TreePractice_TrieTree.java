package com.bodhi.arithmetictestdemo.practice;

/**
 * @author : Sun
 * @version : 1.0
 * create time : 2019/7/24 17:18
 * desc :介绍前缀树
 *
 * 前缀树:给定一个字符串“abc” 给定一个头结点h，从头结点开始判断有没有路径a 有就复用 没有就从h处延伸出
 * 一个节点n1 h到n1的路径被标识为a，然后从n1判断有没有路径b 然后从n1处延伸出一个节点n2 n1到n2的路径被标
 * 识为b 然后延伸出n3 n2到n3的路径被标识为c，此时又来一个字符串“bce” 同理判断从头结点开始有没有路径b 没有
 * 所有从h处延伸一个新节点n4 h到n4的路径标示为b 然后依次延伸出 n5，n6 n4到n5标识为c，n5到n6标识为e 现在
 * 又来一个字符串“abd”，此时h处判断有路径a，在a处判断有路径b，在b处判断没有路径d，则在b处延伸出一个节点
 * n7，此时h除的路径一共有 a-b-c a-b-d b-c-e三条路径 这就是前缀树 增加的过程就是前缀树加法。
 *
 * 扩充1：要判断生成的前缀树中 是否包含以“ab”为开头的字符串 只要判断是否包含a-b的路径即可，但是如果要判断
 * 在此前缀树中是否加过ab 则不能确定，所以在每个加进来的字符串最后一个节点上增加一个标记位 标记第几次结尾
 * 则在判断前缀树中是否加过某个字符串 只要判断是否包含对应的路径 同时判断路径最后节点标记位是否大于0。
 *
 * 扩充2：假如给定一个字符串“ab” 要找到有多少个字符串是“ab”为前缀的 所以在每个节点上在加一个标记位 标
 * 记有多少个路径到达过此节点。
 *
 *
 *应用：在一个字符串数组中 要查某一个前缀，要查某个字符串是否在里面，要查以某个前缀开头的字符串一共有多少
 * 都可以用前缀树及其扩充数据项的方式实现 而且空间代价和时间代价极低
 *
 */
public class TreePractice_TrieTree {
    /**
     * 前缀节点
     */
    public static class TrieNode {
        public int path;//有多少个路径到达过此节点
        public int end;//有多少个路径以此节点结尾
        public TrieNode[] nexts;//路径

        public TrieNode() {
            path = 0;
            end = 0;
            //假如规定每个节点只有小写字母a-z 26种路
            nexts = new TrieNode[26];
        }
    }

    /**
     * 前缀树结构
     */
    public static class TrieTree{
        //前缀树的头结点
        private TrieNode root;

        public TrieTree(){
            root=new TrieNode();
        }

        /**
         * 加入字符串
         * @param word
         */
        public void insert(String word){
            if(word==null){
                return ;
            }
            char[] chars = word.toCharArray();
            TrieNode node = root;
            //用来标识是哪条路径
            int index = 0;
            for (int i = 0; i < chars.length; i++) {
                //如果char=a 则a-a=0 如果char=b 则b-a=1 ascii码地址97~122
                index = chars[i]-'a';
                //判断对应的路径是否存在 不存在新建路径
                if(node.nexts[index]==null){
                    node.nexts[index] = new TrieNode();
                }
                //node 来到对应路径的节点 把此节点上 路径到达过此节点标记+1
                node = node.nexts[index];
                node.path++;

            }
            //循环结束 到达路径的最末节点 把路径以此节点结尾标记+1；
            node.end++;
        }

        /**
         * 删除字符串
         * 大体逻辑怎么插的就怎么删 path-- end--
         * 当path--后等于0 代表此节点以及后面的节点都只有一条路径
         *
         * @param word
         */
        public void delete(String word) {
            //首先判断字符串是否存在前缀树中
            if(search(word)!=0){
                char[] chars = word.toCharArray();
                TrieNode node = root;
                int index =0;
                for (int i = 0; i < chars.length; i++) {
                    index = chars[i]-'a';
                    //某条路径上的某个节点 以及后面的节点都只被划过一次
                    if(--node.nexts[index].path==0){
                        //后面的节点直接全部砍掉
                        node.nexts[index]=null;
                        return;
                    }
                    node = node.nexts[index];
                }
                node.end--;
            }
        }

        /**
         * 查询某字符串在前缀树中插入过几次
         * @param word
         * @return
         */
        public int search(String word){
            if (word == null) {
                return 0;
            }

            char[] chars = word.toCharArray();
            TrieNode node = root;
            int index =0;
            for (int i = 0; i < chars.length; i++) {
                index=chars[i]-'a';
                //如果任何一个字符在路径上不存在 则此字符串一定没插入过返回为0
                if(node.nexts[index]==null){
                    return 0;
                }
                node=node.nexts[index];
            }
            //如果存在此字符串的路径 根据末尾节点的标记位直接返回
            return node.end;
        }

        /**
         * 查询在前缀树中以某字符串为前缀的一共有多少
         * @param pre
         * @return
         */
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            TrieNode node = root;
            int index = 0;
            for (int i = 0; i < chs.length; i++) {
                index = chs[i] - 'a';
                if (node.nexts[index] == null) {
                    return 0;
                }
                node = node.nexts[index];
            }
            return node.path;
        }
    }

    public static void test(){
        TrieTree trie = new TrieTree();
        System.out.println(trie.search("sun"));
        trie.insert("sun");
        System.out.println(trie.search("sun"));
        trie.delete("sun");
        System.out.println(trie.search("sun"));

        trie.insert("sun");
        trie.insert("sun");
        trie.delete("sun");
        System.out.println(trie.search("sun"));
        trie.delete("sun");
        System.out.println(trie.search("sun"));

        trie.insert("suna");
        trie.insert("sunac");
        trie.insert("sunab");
        trie.insert("sunad");
        trie.delete("suna");
        System.out.println(trie.search("suna"));
        System.out.println(trie.prefixNumber("sun"));
    }


}
