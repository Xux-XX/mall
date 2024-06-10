package com.xux.comment.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xux.comment.mapper.SensitiveWordMapper;
import com.xux.comment.pojo.entity.SensitiveWord;
import com.xux.comment.pojo.enums.SensitiveEnum;
import com.xux.comment.service.SensitiveWordService;
import com.xux.comment.util.Trie;
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
        LambdaQueryWrapper<SensitiveWord> wrapper = new LambdaQueryWrapper<SensitiveWord>().eq(SensitiveWord::getWordId, wordId);
        if (!this.exists(wrapper)) return SensitiveEnum.NOT_FOUND;
        // 删除ac自动机和数据库中数据
        String word = this.getById(wordId).getWord();
        sensitiveTrie.removeWord(word);
        this.removeById(wordId);
        return SensitiveEnum.SUCCESS;
    }


    @Override
    public List<String> getPage(Integer pageNumber, Integer pageSize) {
        return this.getBaseMapper()
                .selectPage(new Page<>(pageNumber, pageSize, false), null)
                .getRecords()
                .stream()
                .map(SensitiveWord::getWord)
                .toList();
    }
}
