package com.xux.comment.controller;

import com.xux.comment.pojo.entity.SensitiveWord;
import com.xux.comment.pojo.enums.SensitiveEnum;
import com.xux.comment.service.SensitiveWordService;
import com.xux.common.entity.Result;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/10 17:15
 */
@RestController
@RequestMapping("sensitive")
@RequiredArgsConstructor
@Tag(name = "敏感词管理")
public class SensitiveWordController {
    private final SensitiveWordService service;

    @GetMapping
    @Operation(summary = "查询敏感词")
    public Result getWord(@RequestParam("pageNumber") Integer pageNumber,
                          @RequestParam("pageSize") Integer pageSize){
        List<SensitiveWord> data = service.getPage(pageNumber, pageSize);
        return Result.ok("操作成功", data);
    }

    @PostMapping
    @Operation(summary = "添加敏感词")
    public Result addWord(@RequestBody Map<String, String> word){
        SensitiveEnum state = service.add(word.get("word"));
        if (state == SensitiveEnum.SUCCESS) return Result.ok(state.getMessage());
        return Result.fail(state.getMessage());
    }

    @DeleteMapping("{id}")
    @Operation(summary = "删除敏感词")
    public Result removeWord(@PathVariable("id") Integer wordId){
        SensitiveEnum state = service.remove(wordId);
        if (state == SensitiveEnum.SUCCESS) return Result.ok(state.getMessage());
        return Result.fail(state.getMessage());
    }
}
