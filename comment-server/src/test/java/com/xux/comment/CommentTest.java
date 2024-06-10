package com.xux.comment;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xux.comment.mapper.SensitiveWordMapper;
import com.xux.comment.pojo.entity.Comment;
import com.xux.comment.pojo.entity.SensitiveWord;
import com.xux.comment.service.CommentService;
import com.xux.comment.service.SensitiveWordService;
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
    @Autowired
    SensitiveWordService sensitiveWordService;
    @Test
    void test(){
        Comment comment = new Comment();
        comment.setRating(5);
        commentService.save(comment);
    }

    @Test
    void testService(){
        System.out.println(sensitiveWordService.getPage(2, 3));
    }
    @Test
    void testMapper(){
        System.out.println(mapper.selectPage(new Page<>(-2, 3), null));
    }

    @Test
    void testTrie(){
        String s = "测试1测试数据1and测试数据2to测试";
        System.out.println(trie.filter(s));
    }
}
