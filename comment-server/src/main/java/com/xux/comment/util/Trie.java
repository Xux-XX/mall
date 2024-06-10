package com.xux.comment.util;

import com.xux.comment.mapper.SensitiveWordMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * 使用AC自动机快速匹配敏感词
 * @author xux
 * @version 0.1
 * @since 2024/6/9 12:20
 */
@Component
public class Trie {

    /**
     * 将输入文本中的敏感词替换为目标字符串
     * @param text 待替换文本
     * @param target 替换的目标字符串
     * @return 替换后文本
     */
    public String replaceWith(String text, String target) {
        // 用List记录匹配到的敏感词的首位索引 Pair(begin, end)
        List<Pair<Integer, Integer>> indexList = new ArrayList<>();
        Node now = root;
        for(int i = 0; i < text.length(); i++){
            char ch = text.charAt(i);
            // 匹配失败则通过fail指针跳转到下一个状态
            if (!now.next.containsKey(ch)){
                now = now.fail;
                continue;
            }
            // 匹配成功,进入下一个状态
            now = now.next.get(ch);
            if (now.length != 0){
                // 匹配成功一个串后将now置为root避免交叉匹配
                indexList.add(Pair.of(i - now.length + 1, i));
                now = root;
            }
        }
        // 构造需要返回的字符串
        StringBuilder stringBuilder = new StringBuilder();
        int left = 0;
        for (Pair<Integer, Integer> pair : indexList) {
            String substring = text.substring(left, pair.getFirst());
            stringBuilder.append(substring);
            stringBuilder.append(target);
            left = pair.getSecond() + 1;
        }
        stringBuilder.append(text.substring(left));
        return stringBuilder.toString();
    }

    /**
     * 默认和谐方案,将敏感词替换为 * 符号
     * @param text 待替换文本
     * @return 替换后文本
     */
    public String filter(String text) {
        return replaceWith(text, "***");
    }

    /**
     * 添加敏感词, 并更新fail指针, 若该词已经存在则不做任何操作
     * @param word 敏感词
     */
    public synchronized void addWord(String word) {
        // trie数新增节点
        Node now = root;
        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            Node node = now.next.get(ch);
            if (node == null){
                // 如果是新建节点, 需要更新一下周围的fail指针
                node = new Node(ch);
                List<Node> nodeList = reverseList.computeIfAbsent(now, n -> new ArrayList<>());
                // 遍历fail指针为"新节点的父节点"的节点
                // 若子节点存在值为ch的节点,将这个节点的fail指针指向新节点
                for (Node node1 : nodeList) {
                    if (node1.next.containsKey(ch)){
                        Node child = node1.next.get(ch);
                        reverseList.get(child.fail).remove(child);
                        child.fail = node;
                        reverseList.computeIfAbsent(node, n -> new ArrayList<>()).add(child);
                    }
                }
                // 更新这个新建节点的fail指针
                node.fail = now.fail.next.getOrDefault(ch, root);
                now.next.put(ch, node);
            }
            now = node;
        }
        now.length = word.length();
    }


    /**
     * 删除敏感词, 并更新fail指针, 若该词不存在不做任何操作
     * @param word 需要删除的敏感词
     */
    public synchronized void removeWord(String word) {
        removeWordRecurse(word, 0, root);
    }

    /**
     * 使用递归的方式进行删除
     * @param word 需要删除的词
     * @param index 当前递归对应词中的索引
     * @param now 当前的节点
     */
    private void removeWordRecurse(String word, int index, Node now){
        if (index == word.length()){
            // 匹配成功, 将length标记置为0表示删除这个单词
            now.length = 0;
            return;
        }
        char ch = word.charAt(index);
        Node child = now.next.get(ch);
        if (child != null){
            removeWordRecurse(word, index + 1, child);
            if (child.next.isEmpty()){
                // 若子节点没有后继节点需要将子节点删除, 并更新fail指针
                now.next.remove(ch);
                List<Node> nodeList = reverseList.get(child);
                reverseList.remove(child);
                for (Node node : nodeList) {
                    reverseList.get(child.fail).add(node);
                    node.fail = child.fail;
                }
            }
        }
    }
//    private boolean removeWordRecurse(String word, int index, Node now){
//        if (now == null)return false;
//        if (index == word.length()){
//            // 成功匹配则将length标记置为0表示这个单词的删除
//            if (now.next.isEmpty()){
//                now.next = null;
//                now.fail = null;
//                return true;
//            }
//            now.length = 0;
//            return false;
//        }
//        char ch = word.charAt(index);
//        boolean isDelete = removeWordRecurse(word, index + 1, now.next.get(ch));
//        if (isDelete){
//            now.next.remove(ch);
//            if (now.next.isEmpty()){
//                now.next = null;
//                now.fail = null;
//                return true;
//            }
//        }
//        return false;
//    }


    /**
     * 判断word是否为已经载入的敏感词
     * @param word 待判断词
     * @return true存在, false不存在
     */
    public boolean exists(String word) {
        Node now = root;
        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            // 若不存在下一个状态则匹配失败
            if ((now = now.next.get(ch)) == null){
                return false;
            }
        }
        return now.length == 0;
    }


    /**
     * 载入敏感词库,并初始化fail指针
     */
    @PostConstruct
    public void load() {
        // 建立字典树
        for (String s : mapper.selectWordList()) {
            Node now = root;
            for (int i=0; i < s.length(); i++){
                char ch = s.charAt(i);
                now = now.next.computeIfAbsent(ch, Node::new);
            }
            now.length = s.length();
        }
        // 初始化fail指针
        buildFail();
    }

    /**
     * 建立fail指针
     */
    private void buildFail(){
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);
        while(!queue.isEmpty()){
            Node parent = queue.poll();
            // 更新reverseList
            parent.next.forEach((character, child) -> {
                // 父节点的fail存在有character节点时, 直接指向这个节点
                // 否则指向root节点
                child.fail = parent.fail.next.getOrDefault(character, root);
                reverseList.computeIfAbsent(child.fail, node -> new ArrayList<>()).add(child);
                queue.add(child);
            });
        }
    }


    /**
     * AC自动机节点
     * <p>value: 存储屏蔽词中的单位字符
     * <p>next: 存储下一个Node
     * <p>fail: 用于匹配失败时快速找到下一个匹配节点
     * <p>length: 表示这个字符串长度, 为0表示此位置不为字符串结束
     */
    private static class Node{
        char value;
        Map<Character, Node> next;
        Node fail;
        int length;
        public Node(char value) {
            this.value = value;
            next = new HashMap<>();
        }
    }

    /** 用于从数据库载入敏感词 */
    private final SensitiveWordMapper mapper;
    /** 字典树根节点, 不存储数据只存储子节点 */
    private final Node root;
    /** 记录fail指针为node的有哪些节点, 用于新增和删除操作 */
    private final Map<Node, List<Node>> reverseList;

    public Trie(SensitiveWordMapper mapper) {
        this.mapper = mapper;
        root = new Node(' ');
        root.fail = root;
        reverseList = new HashMap<>();
        reverseList.computeIfAbsent(root, node -> new ArrayList<>()).add(root);
    }
}
