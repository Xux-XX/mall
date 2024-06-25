package com.xux.seckill.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.seckill.mapper.ArrangementMapper;
import com.xux.seckill.pojo.entity.SeckillArrangement;
import com.xux.seckill.service.ArrangementService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 22:20
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "seckill:arrangement")
public class ArrangementServiceImpl extends ServiceImpl<ArrangementMapper, SeckillArrangement>
        implements ArrangementService {
    @Override
    public Integer addArrangement(SeckillArrangement seckillArrangement) {
        this.save(seckillArrangement);
        return seckillArrangement.getSeckillId();
    }

    @Override
    @CacheEvict(key = "#arrangementId")
    public void removeArrangement(Integer arrangementId) {
        this.removeById(arrangementId);
    }

    @Override
    @CacheEvict(key = "#seckillArrangement.seckillId")
    public void updateArrangement(SeckillArrangement seckillArrangement) {
        this.lambdaUpdate()
                .eq(SeckillArrangement::getSeckillId, seckillArrangement.getSeckillId())
                .update(seckillArrangement);
    }

    @Override
    public List<SeckillArrangement> getByStarTime(LocalDateTime startTime) {
        return this.lambdaQuery()
                .ge(SeckillArrangement::getStartTime, startTime)
                .list();
    }

    @Override
    public List<SeckillArrangement> getOrderByBeginTime(Integer pageNumber, Integer pageSize) {
        return this.lambdaQuery()
                .ge(SeckillArrangement::getStartTime, LocalDateTime.now())
                .orderByAsc(SeckillArrangement::getStartTime)
                .page(new Page<>(pageNumber, pageSize))
                .getRecords();
    }

    @Override
    @Cacheable(key = "#arrangementId")
    public SeckillArrangement getArrangementById(Integer arrangementId) {
        return this.getById(arrangementId);
    }
}
