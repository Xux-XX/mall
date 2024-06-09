package com.xux.comment.util;

import com.xux.comment.mapper.SensitiveWordMapper;
import jakarta.annotation.PostConstruct;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple2;

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
     * 添加敏感词
     * @param word 敏感词
     */
    public void addWord(String word) {

    }


    /**
     * 删除敏感词
     * @param word 需要删除的敏感词
     */
    public void removeWord(String word) {

    }


    /**
     * 判断word是否为已经载入的敏感词
     * @param word 待判断词
     * @return true存在, false不存在
     */
    public boolean exists(String word) {
        return false;
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
            parent.next.forEach((character, child) -> {
                // 父节点的fail存在有character节点时, 直接指向这个节点
                // 否则指向root节点
                child.fail = parent.fail.next.getOrDefault(character, root);
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
    private class Node{
        char value;
        Map<Character, Node> next;
        Node fail;
        int length;
        public Node(char value) {
            this.value = value;
            next = new HashMap<>();
        }
    }

    /* 用于从数据库载入敏感词 */
    private final SensitiveWordMapper mapper;
    private final Node root;

    public Trie(SensitiveWordMapper mapper) {
        this.mapper = mapper;
        root = new Node(' ');
        root.fail = root;
    }
}
