package com.xux.seckill.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.seckill.mapper.ArrangementMapper;
import com.xux.seckill.pojo.entity.SeckillArrangement;
import com.xux.seckill.service.ArrangementService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
@Service
public class ArrangementServiceImpl extends ServiceImpl<ArrangementMapper, SeckillArrangement>
        implements ArrangementService {

    public List<SeckillArrangement> getArrangementList(LocalDateTime fromTime, LocalDateTime endTime){
        return this.lambdaQuery()
                .ge(SeckillArrangement::getStartTime, fromTime)
                .le(SeckillArrangement::getStartTime, endTime)
                .list();
    }
}
