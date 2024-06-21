package com.xux.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.seckill.pojo.entity.SeckillProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:19
 */
@Mapper
public interface ProductMapper extends BaseMapper<SeckillProduct> {
}
