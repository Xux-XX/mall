package com.xux.user.controller;

import com.xux.core.entity.Result;
import com.xux.user.pojo.entity.User;
import com.xux.user.pojo.enums.LoginStatus;
import com.xux.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/15 12:56
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "用户模块")
public class UserController {
    private final UserService userService;

    @PostMapping("login")
    @Operation(summary = "登录接口")
    public Result login(@RequestBody User user){
        LoginStatus status = userService.login(user.getUsername(), user.getPassword());
        if (status == LoginStatus.SUCCESS) return Result.ok(status.getMessage(), status.getData());
        return Result.fail(status.getMessage());
    }

    @DeleteMapping("logout")
    @Operation(summary = "退出登录")
    public Result logout(){
        userService.logout();
        return Result.ok();
    }
}
