package com.xux.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.seckill.mapper.ArrangementMapper;
import com.xux.seckill.pojo.entity.SeckillArrangement;
import com.xux.seckill.service.ArrangementService;
import lombok.RequiredArgsConstructor;
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
public class ArrangementServiceImpl extends ServiceImpl<ArrangementMapper, SeckillArrangement>
        implements ArrangementService {
    @Override
    public List<SeckillArrangement> getByStarTime(LocalDateTime startTime) {
        return this.lambdaQuery()
                .ge(SeckillArrangement::getStartTime, startTime)
                .list();
    }
}
