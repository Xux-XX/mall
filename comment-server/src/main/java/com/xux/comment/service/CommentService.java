package com.xux.comment.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xux.comment.pojo.entity.Comment;
import com.xux.comment.pojo.enums.CommentEnum;

import java.util.List;

public interface CommentService extends IService<Comment> {
    /**
     * 根据店铺id,,查询经敏感词过滤后的评论
     * @param storeId 店铺id
     * @param pageNumber 页数
     * @param pageSize 页大小
     * @return List<Comment>
     */
    List<Comment> getByStoreId(Integer storeId, Integer pageNumber, Integer pageSize);

    /**
     * 根据用户id, 查询经敏感词过滤后的评论
     * @param userId 用户id
     * @param pageNumber 页数
     * @param pageSize 页大小
     * @return List<Comment>
     */
    List<Comment> getByUserId(Integer userId, Integer pageNumber, Integer pageSize);

    /**
     * 新增评论
     * @param comment 评论
     */
    CommentEnum addComment(Comment comment);

    /**
     * 根据评论id删除评论
     * @param commendId 评论id
     */
    void removeComment(Integer commendId);

    /**
     * 修改评论
     * @param comment 评论
     */
    void updateComment(Comment comment);
}
