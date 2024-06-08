package com.xux.comment;

import com.xux.comment.pojo.entity.Comment;
import com.xux.comment.service.CommentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CommentTest {
    @Autowired
    CommentService commentService;

    @Test
    void test(){
        Comment comment = new Comment();
        comment.setRating(5);
        commentService.save(comment);
    }
}
