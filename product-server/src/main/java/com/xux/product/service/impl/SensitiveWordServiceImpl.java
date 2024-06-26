package com.xux.product.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.product.mapper.SensitiveWordMapper;
import com.xux.product.pojo.entity.SensitiveWord;
import com.xux.product.pojo.enums.SensitiveEnum;
import com.xux.product.service.SensitiveWordService;
import com.xux.product.util.Trie;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SensitiveWordServiceImpl extends ServiceImpl<SensitiveWordMapper, SensitiveWord>
        implements SensitiveWordService {

    private final Trie sensitiveTrie;

    @Override
    public SensitiveEnum add(String word) {
        // 判断是否存在
        LambdaQueryWrapper<SensitiveWord> wrapper = new LambdaQueryWrapper<SensitiveWord>().eq(SensitiveWord::getWord, word);
        if (this.exists(wrapper)) return SensitiveEnum.DUPLICATE;
        // 向ac自动机和数据库中添加数据
        sensitiveTrie.addWord(word);
        SensitiveWord sensitiveWord = new SensitiveWord();
        sensitiveWord.setWord(word);
        this.save(sensitiveWord);
        return SensitiveEnum.SUCCESS;
    }

    @Override
    public SensitiveEnum remove(Integer wordId) {
        // 判断是否存在
        SensitiveWord entity = this.getById(wordId);
        if (entity == null) return SensitiveEnum.NOT_FOUND;
        // 删除ac自动机和数据库中数据
        String word = entity.getWord();
        sensitiveTrie.removeWord(word);
        this.removeById(wordId);
        return SensitiveEnum.SUCCESS;
    }


    @Override
    public List<SensitiveWord> getPage(Integer pageNumber, Integer pageSize) {
        return this.getBaseMapper()
                .selectPage(new Page<>(pageNumber, pageSize, false), null)
                .getRecords();
    }

    @Override
    public List<String> filter(List<String> texts) {
        return texts.stream()
                .filter(text -> !sensitiveTrie.hasSensitive(text))
                .toList();
    }

    @Override
    public boolean hasSensitive(String text) {
        return sensitiveTrie.hasSensitive(text);
    }
}
