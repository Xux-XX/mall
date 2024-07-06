package com.xux.product.service.impl;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.commonWeb.context.UserContext;
import com.xux.commonWeb.pojo.entity.BaseEntity;
import com.xux.product.annotation.Sensitive;
import com.xux.product.mapper.CommentMapper;
import com.xux.product.pojo.entity.Comment;
import com.xux.product.pojo.enums.CommentEnum;
import com.xux.product.pojo.enums.CommentOrderBy;
import com.xux.product.service.CommentService;
import com.xux.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("commentService")
@RequiredArgsConstructor
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
        implements CommentService {
    private final ProductService productService;
    @Override
    @Sensitive
    public List<Comment> getByStoreId(Integer storeId, Integer pageNumber, Integer pageSize, CommentOrderBy orderBy) {
        LambdaQueryChainWrapper<Comment> query = this.lambdaQuery()
                .eq(Comment::getStoreId, storeId)
                .eq(Comment::getParentId, 0);
        // 根据参数选择不同的排序条件
        switch (orderBy){
            case RATING -> query.orderByDesc(Comment::getRating);
            case LIKE_COUNT -> query.orderByDesc(Comment::getLikeCount);
            case CREATE_TIME -> query.orderByDesc(BaseEntity::getCreateTime);
            case DEFAULT -> {}
        }
        return query
                .page(new Page<>(pageNumber, pageSize))
                .getRecords();
    }

    @Override
    @Sensitive
    public List<Comment> getByUserId(Integer userId, Integer pageNumber, Integer pageSize) {
        return this.lambdaQuery()
                .eq(BaseEntity::getCreateUser, userId)
                .page(new Page<>(pageNumber, pageSize))
                .getRecords();
    }

    @Override
    @Sensitive
    public List<Comment> getByParentId(Integer parentId, Integer pageNumber, Integer pageSize) {
        return this.lambdaQuery()
                .eq(Comment::getParentId, parentId)
                .page(new Page<>(pageNumber, pageSize))
                .getRecords();
    }

    @Override
    public CommentEnum addComment(Comment comment) {
        if (!productService.exists(comment.getProductId(), comment.getStoreId())) return CommentEnum.PRODUCT_NOT_FOUND;
        this.save(comment);
        return CommentEnum.SUCCESS;
    }

    @Override
    public void removeComment(Integer commendId) {
        this.removeById(commendId);
    }

    @Override
    public boolean isCreator(Integer commendId) {
        Integer userId = UserContext.get().getUserId();
        return this.lambdaQuery()
                .eq(Comment::getCommentId, commendId)
                .eq(BaseEntity::getCreateUser, userId)
                .exists();
    }
}
