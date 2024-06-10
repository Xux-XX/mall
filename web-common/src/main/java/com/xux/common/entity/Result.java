package com.xux.common.entity;

import lombok.Data;

@Data
public class Result {
    private Integer code;
    private String message;
    private Object data;

    private Result(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
    public static Result ok(){
        return new Result(200, "操作成功", null);
    }
    public static Result ok(String message){
        return new Result(200, message, null);
    }
    public static Result ok(String message, Object data){
        return new Result(200, message, data);
    }
    public static Result fail(String message){
        return new Result(400, message, null);
    }
    public static Result fail(){
        return new Result(400, "操作失败", null);
    }

    public static Result error(){
        return new Result(500, "服务器异常,请联系管理员", null);
    }
}
