package com.xux.common.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.xux.common.context.UserContext;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class MybatisConfig {
    private final String CREATE_TIME = "createTime";
    private final String CREATE_USER = "createUser";
    private final String UPDATE_TIME = "updateTime";
    private final String UPDATE_USER = "updateUser";

    /**
     * 自动填充元素配置
     */
    @Bean
    public MetaObjectHandler metaObjectHandler(){
        return new MetaObjectHandler() {

            @Override
            public void insertFill(MetaObject metaObject) {
                // 插入时自动填充公共元素
                this.strictInsertFill(metaObject, CREATE_TIME, LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, CREATE_USER, Integer.class, UserContext.get().getUserId());
                this.strictInsertFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
                this.strictInsertFill(metaObject, UPDATE_USER, Integer.class, UserContext.get().getUserId());
            }

            @Override
            public void updateFill(MetaObject metaObject) {
                this.strictUpdateFill(metaObject, UPDATE_TIME, LocalDateTime.class, LocalDateTime.now());
                this.strictUpdateFill(metaObject, UPDATE_USER, Integer.class, UserContext.get().getUserId());
            }
        };
    }

    /**
     * 分解插件配置
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
