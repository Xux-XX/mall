package com.xux.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.order.pojo.entity.Order;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/23 17:11
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
