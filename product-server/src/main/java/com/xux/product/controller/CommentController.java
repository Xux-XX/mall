package com.xux.product.controller;

import com.xux.commonWeb.annotation.PreAuthorization;
import com.xux.commonWeb.annotation.RequireLogin;
import com.xux.core.entity.Result;
import com.xux.product.pojo.entity.Comment;
import com.xux.product.pojo.enums.CommentEnum;
import com.xux.product.pojo.enums.CommentOrderBy;
import com.xux.product.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Max;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/11 21:16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/comment")
@Tag(name = "评论模块")
@Validated
public class CommentController {
    private final CommentService commentService;

    @GetMapping("store/{storeId}")
    @Operation(summary = "根据店铺id获取一级评论")
    public Result getCommentByStore(@PathVariable Integer storeId,
                                    @RequestParam Integer pageNumber,
                                    @RequestParam @Max(value = 30, message = "页大小最大为30") Integer pageSize,
                                    @RequestParam(name = "orderBy", defaultValue = "DEFAULT") CommentOrderBy orderBy){
        List<Comment> data = commentService.getByStoreId(storeId, pageNumber, pageSize, orderBy);
        return Result.ok("查询成功", data);
    }

    @GetMapping("user/{userId}")
    @Operation(summary = "根据用户名获取评论")
    public Result getCommentByUser(@PathVariable Integer userId,
                                   @RequestParam Integer pageNumber,
                                   @RequestParam @Max(value = 30, message = "页大小最大为30") Integer pageSize){
        List<Comment> data = commentService.getByUserId(userId, pageNumber, pageSize);
        return Result.ok("查询成功", data);
    }

    @GetMapping("children/{parentId}")
    @Operation(summary = "获取一个评论下的子评论")
    public Result getChildrenComment(@PathVariable Integer parentId,
                                     @RequestParam Integer pageNumber,
                                     @RequestParam @Max(value = 30, message = "页大小最大为30") Integer pageSize){
        List<Comment> childrenComments = commentService.getByParentId(parentId, pageNumber, pageSize);
        return Result.ok("查询成功", childrenComments);
    }

    @PostMapping
    @Operation(summary = "新增评论")
    @RequireLogin
    public Result addComment(@RequestBody Comment comment){
        CommentEnum status = commentService.addComment(comment);
        if (status == CommentEnum.SUCCESS) return Result.ok(status.getMessage());
        return Result.fail(status.getMessage());
    }

    @DeleteMapping
    @Operation(summary = "删除评论")
    @PreAuthorization("@commentService.isCreator(#commentId)")
    public Result removeComment(Integer commentId){
        commentService.removeComment(commentId);
        return Result.ok();
    }
}
