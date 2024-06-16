package com.xux.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.user.pojo.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/14 21:25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
