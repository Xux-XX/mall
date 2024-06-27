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
    /**
     * 新增秒杀场次
     * @param seckillArrangement 场次信息
     * @return 场次id
     */
    Integer addArrangement(SeckillArrangement seckillArrangement);

    /**
     * 删除指定场次
     * @param arrangementId 场次id
     */
    void removeArrangement(Integer arrangementId);

    /**
     * 更新数据
     * @param seckillArrangement 场次信息
     */
    void updateArrangement(SeckillArrangement seckillArrangement);

    /**
     * 获取开始时间大于等于指定值的秒杀场次
     * @param startTime 指定开始时间
     * @return List<SeckillArrangement>
     */
    List<SeckillArrangement> getByStarTime(LocalDateTime startTime);

    /**
     * 分页获取秒杀场次，按开始时间从近到远排序
     * @param pageNumber 页号
     * @param pageSize 页大小
     * @return List<SeckillArrangement>
     */
    List<SeckillArrangement> getOrderByBeginTime(Integer pageNumber, Integer pageSize);

    /**
     * 通过id获取秒杀场次
     * @param arrangementId 场次id
     * @return SeckillArrangement
     */
    SeckillArrangement getArrangementById(Integer arrangementId);
}
