package com.xux.comment.service;

import com.xux.comment.pojo.entity.SensitiveWord;
import com.xux.comment.pojo.enums.SensitiveEnum;

import java.util.List;
/**
 * @author xux
 * @version 0.1
 * @since 2024/6/9 13:54
 */
public interface SensitiveWordService {
    /**
     * 添加敏感词
     * @param word 敏感词
     * @return SUCCESS表示操作成功, DUPLICATE表示已存在
     */
    SensitiveEnum add(String word);

    /**
     * 通过Id删除敏感词
     * @param wordId 敏感词Id
     * @return SUCCESS: 操作成功, NOT_FOUND该敏感词不存在
     */
    SensitiveEnum remove(Integer wordId);

    /**
     * 分页查询
     *
     * @param pageNumber 页数
     * @param pageSize   页的大小
     * @return 分页查询的结果
     */
    List<SensitiveWord> getPage(Integer pageNumber, Integer pageSize);


}
