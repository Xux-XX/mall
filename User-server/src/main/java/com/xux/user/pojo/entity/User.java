package com.xux.user.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/11 20:54
 */
@Data
@TableName("tb_user")
public class User {
    @TableId
    private Integer userId;

    @NotBlank(message = "用户名不能为空")
    private String username;

    @NotBlank(message = "密码不能为空")
    private String password;
}
