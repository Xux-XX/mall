package com.xux.seckill.service;

import com.xux.seckill.pojo.entity.SeckillArrangement;

import java.time.LocalDateTime;
import java.util.List;

public interface ArrangementService {
    List<SeckillArrangement> getArrangementList(LocalDateTime startTime, LocalDateTime endTime);
}
