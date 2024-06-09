package com.xux.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.common.entity.LogEntity;
import com.xux.common.mapper.LogMapper;
import com.xux.common.service.LogService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/9 19:00
 */
@Service
public class LogServiceImpl extends ServiceImpl<LogMapper, LogEntity>
        implements LogService {

    @Async
    @Override
    public void saveAsync(LogEntity logEntity) {
        this.save(logEntity);
    }
}
