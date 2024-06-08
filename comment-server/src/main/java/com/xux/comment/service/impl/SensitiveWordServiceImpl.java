package com.xux.comment.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.comment.mapper.SensitiveWordMapper;
import com.xux.comment.pojo.entity.SensitiveWord;
import com.xux.comment.service.SensitiveWordService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SensitiveWordServiceImpl extends ServiceImpl<SensitiveWordMapper, SensitiveWord>
        implements SensitiveWordService {
    @Override
    public String replaceWith(String text, String target) {
        return null;
    }

    @Override
    public String replace(String text) {
        return null;
    }

    @Override
    public void addWord(String word) {

    }

    @Override
    public void deleteWord(String word) {

    }

    @Override
    public boolean exists(String word) {
        return false;
    }

    @Override
    @PostConstruct
    public void load() {

    }
}
