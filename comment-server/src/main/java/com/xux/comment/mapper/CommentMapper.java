package com.xux.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.comment.pojo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentMapper extends BaseMapper<Comment> {
}
