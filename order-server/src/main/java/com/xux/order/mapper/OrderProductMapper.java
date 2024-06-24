package com.xux.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.order.pojo.entity.OrderProduct;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 23:11
 */
@Mapper
public interface OrderProductMapper extends BaseMapper<OrderProduct> {
}
