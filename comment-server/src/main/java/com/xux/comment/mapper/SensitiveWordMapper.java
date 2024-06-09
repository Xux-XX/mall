package com.xux.comment.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.comment.pojo.entity.SensitiveWord;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SensitiveWordMapper extends BaseMapper<SensitiveWord> {

    @Select("select word from tb_sensitive_words ")
    List<String> selectWordList();
}
