package com.xux.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/15 19:48
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private Integer code;
    private String message;
    private Object data;

    public static Result ok(){return new Result(200, "操作成功", null);}
    public static Result ok(String message){return new Result(200, message, null);}
    public static Result ok(String message, Object data){return new Result(200, message, data);}

    public static Result fail(){return new Result(400, "操作失败", null);}
    public static Result fail(String message){return new Result(400, message, null);}

    public static Result error(){return new Result(500, "系统错误,请联系管理员", null);}

    public static Result unAuthorized(String message){return new Result(401, message, null);}
}
