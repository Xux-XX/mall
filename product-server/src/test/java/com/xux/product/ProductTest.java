package com.xux.product;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.pojo.entity.UserInfo;
import com.xux.product.mapper.CommentMapper;
import com.xux.product.mapper.ProductMapper;
import com.xux.product.pojo.entity.Comment;
import com.xux.product.pojo.entity.Product;
import com.xux.product.service.CommentService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/16 21:19
 */
@SpringBootTest
public class ProductTest extends ServiceImpl<ProductMapper, Product> {
    @Autowired
    private CommentMapper mapper;
    @Autowired
    private CommentService commentService;

    @BeforeAll
    public static void addUser(){
        UserContext.set(new UserInfo(0, 1));
    }
    @Test
    public void test(){

    }

    @Test
    void commentServiceTest(){
        List<Comment> comment = commentService.getByParentId(0, 1, 20);
        comment.stream().map(Comment::getContent).forEach(System.out::println);
    }


}
