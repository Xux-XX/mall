package com.xux.common.entity;

import lombok.Data;
import org.springframework.lang.NonNull;

@Data
public class UserInfo {
    @NonNull
    private String jwt;
    @NonNull
    private Integer userId;
}
