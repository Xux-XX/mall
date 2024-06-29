package com.xux.product.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xux.commonWeb.pojo.dto.BuyProductDto;
import com.xux.product.pojo.entity.Product;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/12 21:32
 */
@Mapper
public interface ProductMapper extends BaseMapper<Product> {
    void increaseSalesBatch(List<BuyProductDto> buyProductDtoList);
}
