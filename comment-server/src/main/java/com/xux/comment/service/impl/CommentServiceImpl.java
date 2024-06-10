package com.xux.comment.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.comment.feign.StoreFeign;
import com.xux.comment.mapper.CommentMapper;
import com.xux.comment.pojo.entity.Comment;
import com.xux.comment.pojo.enums.CommentEnum;
import com.xux.comment.service.CommentService;
import com.xux.common.context.UserContext;
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

    private final StoreFeign storeFeign;

    @Override
    public List<Comment> getByStoreId(Integer storeId, Integer pageNumber, Integer pageSize) {
        return this.lambdaQuery()
                .eq(Comment::getStoreId, storeId)
                .page(new Page<>(pageNumber, pageSize, false))
                .getRecords();
    }

    @Override
    public List<Comment> getByUserId(Integer userId, Integer pageNumber, Integer pageSize) {
        return this.lambdaQuery()
                .eq(Comment::getUserId, userId)
                .page(new Page<>(pageNumber, pageSize, false))
                .getRecords();
    }

    @Override
    public CommentEnum addComment(Comment comment) {
        boolean isExist = (boolean) storeFeign.exist(comment.getStoreId()).getData();
        if (!isExist) return CommentEnum.STORE_NOT_FOUND;
        comment.setUserId(UserContext.get().getUserId());
        this.save(comment);
        return CommentEnum.SUCCESS;
    }

    @Override
    public void removeComment(Integer commendId) {
        this.removeById(commendId);
    }

    @Override
    public void updateComment(Comment comment) {
        this.updateById(comment);
    }
}
