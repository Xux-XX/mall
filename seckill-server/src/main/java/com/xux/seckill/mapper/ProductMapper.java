package com.xux.seckill.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.seckill.pojo.entity.SeckillProduct;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper extends BaseMapper<SeckillProduct> {
}
