package com.xux.common.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.common.entity.LogEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xux
 * @version 0.1
 * @date 2024/6/9 20:00
 */
@Mapper
public interface LogMapper extends BaseMapper<LogEntity> {
}
