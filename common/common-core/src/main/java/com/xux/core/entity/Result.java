package com.xux.core.entity;

import lombok.Data;

/**
 * @author xux
 * @version 0.1
 * @since 2024/6/15 19:48
 */
@Data
public class Result {
    private final Integer code;
    private final String message;
    private final Object data;

    private Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static Result ok(){return new Result(200, "操作成功", null);}
    public static Result ok(String message){return new Result(200, message, null);}
    public static Result ok(String message, Object data){return new Result(200, message, data);}

    public static Result fail(){return new Result(400, "操作失败", null);}
    public static Result fail(String message){return new Result(400, message, null);}

    public static Result error(){return new Result(500, "系统错误,请联系管理员", null);}

    public static Result unAuthorized(String message){return new Result(401, message, null);}
}
