package com.xux.comment;

import com.xux.comment.util.Trie;
import org.junit.jupiter.api.Test;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/9 14:24
 */
public class TestWithoutSpring {
    @Test
    void test(){
        Object temp = 1;
        boolean is = (boolean)temp;
        System.out.println(is);
    }

    @Test
    void trieTest(){
        Trie trie = new Trie(null);
        trie.addWord("测试数据1");
        trie.addWord("测试数据2");
        trie.addWord("测试");
        System.out.println(trie.filter("测试1测试数据1and测试数据2to测试"));
        trie.removeWord("测试");
        System.out.println(trie.filter("测试1测试数据1and测试数据2to测试"));
    }
}
