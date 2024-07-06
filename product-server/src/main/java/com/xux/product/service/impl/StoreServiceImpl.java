package com.xux.product.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.commonWeb.context.UserContext;
import com.xux.product.mapper.StoreMapper;
import com.xux.product.pojo.entity.Store;
import com.xux.product.service.StoreService;
import org.springframework.stereotype.Service;

/**
 * @author xux
 * @version 0.1
 * @since 2024/7/6 20:20
 */
@Service("storeService")
public class StoreServiceImpl extends ServiceImpl<StoreMapper, Store>
        implements StoreService {
    @Override
    public boolean isOwner(Integer storeId) {
        return this.lambdaQuery()
                .eq(Store::getStoreId, storeId)
                .eq(Store::getOwner, UserContext.get().getUserId())
                .exists();
    }
}
