package com.xux.product.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.product.mapper.CommentMapper;
import com.xux.product.pojo.entity.Comment;
import com.xux.product.pojo.enums.CommentEnum;
import com.xux.product.service.CommentService;
import com.xux.common.context.UserContext;
import com.xux.common.entity.UserInfo;
import com.xux.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * TODO: 权限验证
 */
@Service
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {

    @Override
    public List<Comment> getByStoreId(Integer storeId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public List<Comment> getByUserId(Integer userId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public List<Comment> getByCommentId(Integer commentId, Integer pageNumber, Integer pageSize) {
        return null;
    }

    @Override
    public CommentEnum addComment(Comment comment) {
        return null;
    }

    @Override
    public void removeComment(Integer commendId) {

    }

    @Override
    public boolean isCreator(Integer CommentId) {
        return false;
    }
}
