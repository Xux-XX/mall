package com.xux.seckill.service;

import com.xux.seckill.pojo.entity.SeckillArrangement;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/19 21:10
 */
public interface ArrangementService {
    List<SeckillArrangement> getByStarTime(LocalDateTime startTime);
}
