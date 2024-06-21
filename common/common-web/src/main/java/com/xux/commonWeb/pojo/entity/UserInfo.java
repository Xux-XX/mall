package com.xux.commonWeb.pojo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/17 16:37
 */
@Data
@AllArgsConstructor
public class UserInfo {
    private Integer userId;
    private Integer roleStatus;
}
