package com.xux.commonWeb.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/***
 * @author xux
 * @version 0.1
 * @since 2024/6/9 19:06
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@TableName("tb_log")
public class LogEntity extends BaseEntity{
    @TableId(type = IdType.AUTO)
    private Integer logId;

    /** 描述 */
    private String description;

    /** 方法名称 */
    private String methodName;

    /** 方法参数(json格式) */
    private String param;

    /** 请求用户编号 */
    private Integer userId;

    /** 日志类型(INFO, ERROR) */
    private String logType;

    /** 返回结果 */
    private String result;

    /** 耗时 */
    private Long costTime;

    /** 请求IP */
    private String requestIp;

    /** 请求URI */
    private String requestURI;

    /** 异常详细  */
    private String exceptionDetail;
}
