package com.xux.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xux.product.pojo.entity.Comment;
import com.xux.product.pojo.enums.CommentEnum;
import com.xux.product.pojo.enums.CommentOrderBy;

import java.util.List;

public interface CommentService extends IService<Comment> {
    /**
     * 根据店铺id,查询评论
     * @param storeId 店铺id
     * @param pageNumber 页数
     * @param pageSize 页大小
     * @return List<Comment>
     */
    List<Comment> getByStoreId(Integer storeId, Integer pageNumber, Integer pageSize, CommentOrderBy orderBy);

    /**
     * 根据用户id, 查询评论, 按照时间从新到旧排序
     * @param userId 用户id
     * @param pageNumber 页数
     * @param pageSize 页大小
     * @return List<Comment>
     */
    List<Comment> getByUserId(Integer userId, Integer pageNumber, Integer pageSize);

    /**
     * 获取一个评论下的所有评论,按照时间从旧到新排序
     * @param commentId 评论id
     * @param pageNumber 页数
     * @param pageSize 页大小
     * @return List<Comment>
     */
    List<Comment> getByParentId(Integer commentId, Integer pageNumber, Integer pageSize);

    /**
     * 新增评论
     * @param comment 评论
     * @return SUCCESS: 操作成功
 *          <p>PRODUCT_NOT_FOUND: 添加评论的商品不存在
     *      <p>PARENT_NOT_FOUND: 上级评论不存在
     */
    CommentEnum addComment(Comment comment);

    /**
     * 根据评论id删除评论
     * @param commendId 评论id
     */
    void removeComment(Integer commendId);

    /**
     * @param CommentId 评论id
     * @return 当前登录用户是否为该评论的创建者
     */
    boolean isCreator(Integer CommentId);
}
