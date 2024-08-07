package com.xux.product.service;

import com.xux.commonWeb.pojo.dto.BuyProductDto;
import com.xux.product.exception.ProductNotFoundException;
import com.xux.product.pojo.dto.ProductUpdateDTO;
import com.xux.product.pojo.entity.Product;
import com.xux.product.pojo.enums.ProductOrderBy;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/12 21:33
 */
public interface ProductService {
    /**
     * 在指定商铺是否存在指定商品
     * @param productId 商品id
     * @param storeId 店铺id
     * @return boolean
     */
    boolean exists(Integer productId, Integer storeId);
    /**
     * 判断当前用户是否为该商品所在商铺拥有者
     * @param productId 商品id
     * @return boolean
     */
    boolean isOwner(Integer productId);
    /**
     * 添加商品
     * @param product 商品
     */
    void addProduct(Product product);

    /**
     * 通过id删除商品
     * @param productId 商品id
     * @throws ProductNotFoundException 商品id不存在或已经被删除时抛出
     */
    void remove(Integer productId);

    /**
     * 通过商品名字模糊搜索商品
     * @param productName 商品名字
     * @param pageNumber 页号
     * @param pageSize 页大小
     * @return List<Product>
     */
    List<Product> getByNameLike(String productName, Integer pageNumber, Integer pageSize, ProductOrderBy orderBy);

    /**
     * 通过id获取商品
     * @param productId 商品id
     * @return Product
     * @throws ProductNotFoundException 商品id不存在或被删除时抛出
     */
    Product getProductById(Integer productId);

    /**
     * 通过商品id批量查询,用于内部调用
     * @param ids 商品id集合
     * @return List<Product>
     */
    List<Product> getProductByIds(List<Integer> ids);

    /**
     * 通过店铺获取商品
     * @param storeId 店铺id
     * @param pageNumber 页号
     * @param pageSize 页大小
     * @param orderBy 排序规则
     * @return List<Product>
     */
    List<Product> getByStore(Integer storeId, Integer pageNumber, Integer pageSize, ProductOrderBy orderBy);

    /**
     * 获取已经缺货的商品
     * @param storeId 店铺id
     * @param pageNumber 页号
     * @param pageSize 页大小
     * @return List<Product>
     */
    List<Product> getOutOfStock(Integer storeId, Integer pageNumber, Integer pageSize);

    /**
     * 修改库存
     * @param increment 需要增加的库存(可以为负数)
     * @param productId 商品id
     * @throws ProductNotFoundException 商品id不存在或被删除时抛出
     */
    void increaseStock(Integer increment, Integer productId);

    /**
     * 修改商品信息
     * @param productUpdateDTO 包含商品id和需要更新的数据(名称、描述、价格、分类、库存、图片、状态)
     * @throws ProductNotFoundException 商品id不存在或被删除时抛出
     */
    void update(ProductUpdateDTO productUpdateDTO);

    /**
     * 批量增加商品的销量,用于内部调用
     * @param buyProductDtoList 包含商品id和购买的数量number
     */
    void increaseSaleBatch(List<BuyProductDto> buyProductDtoList);

    /**
     * 通过销量排行获取商品
     * @param rankL 最小排名(从1开始)
     * @param rankR 最大排名(从1开始)
     * @return List<Product>
     */
    List<Product> getBySaleRank(Integer rankL, Integer rankR);
}
