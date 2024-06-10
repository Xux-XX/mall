package com.xux.comment;

import com.xux.comment.mapper.SensitiveWordMapper;
import com.xux.comment.pojo.entity.Comment;
import com.xux.comment.service.CommentService;
import com.xux.comment.util.Trie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTest {
    @Autowired
    CommentService commentService;
    @Autowired
    SensitiveWordMapper mapper;
    @Autowired
    Trie trie;
    @Test
    void test(){
        Comment comment = new Comment();
        comment.setRating(5);
        commentService.save(comment);
    }

    @Test
    void testMapper(){
        System.out.println(mapper.selectWordList());
    }

    @Test
    void testTrie(){
        String s = "测试1测试数据1and测试数据2to测试";
        System.out.println(trie.filter(s));
    }
}
