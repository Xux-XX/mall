package com.xux.seckill.service;

import com.xux.seckill.pojo.entity.SeckillProduct;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:21
 */

public interface ProductService {
    /**
     * 查询指定场次的所有商品(后端使用)
     * @param seckillId 场次id
     * @return List<SeckillProduct>
     */
    List<SeckillProduct> getBySeckill(Integer seckillId);

    /**
     * 分页查询指定场次的所有商品(前端使用)
     * @param seckillId 场次id
     * @param pageNumber 页号
     * @param pageSize 页大小
     * @return List<SeckillProduct>
     */
    List<SeckillProduct> getPageBySeckill(Integer seckillId, Integer pageNumber, Integer pageSize);


    /**
     * 通过场次id和商品id查询商品信息
     * @param seckillId 场次id
     * @param productId 商品id
     * @return SeckillProduct
     */
    SeckillProduct getProduct(Integer seckillId, Integer productId);

    /**
     * 新增商品信息(在秒杀过程中不能操作)
     * @param seckillProduct 商品信息
     */
    void addProduct(SeckillProduct seckillProduct);

    /**
     * 批量新增商品信息(在秒杀过程中不能操作)
     * @param seckillProductList 商品信息集合
     */
    void addProductBatch(List<SeckillProduct> seckillProductList);

    /**
     * 根据场次id和商品id移除商品(在秒杀过程中不能操作)
     * @param seckillId 场次id
     * @param productId 商品id
     */
    void removeProduct(Integer seckillId, Integer productId);

    /**
     * 更新秒杀商品信息(在秒杀过程中不能更新)
     * @param seckillProduct 商品信息
     */
    void updateProduct(SeckillProduct seckillProduct);
}
