package com.ezhiyang.approval.common;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author Caixiaowei
 * @ClassName Result.java
 * @Description TODO
 * @createTime 2020年06月18日 14:51:00
 */
@Data
@AllArgsConstructor
public class Result<T> {

    private int code;

    private String message;

    private T data;

    public Result(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Result sucess() {
        return new Result(0, "sucess");
    }

    public static Result sucess(String message) {
        return new Result(0, message);
    }

    public static Result sucessOfData(String message, Object data) {
        return new Result(0, message, data);
    }

    public static Result successOfData(Object data) {
        return new Result(0, "sucess", data);
    }

    public static Result error() {
        return new Result(1, "error");
    }

    public static Result error(String message) {
        return new Result(1, message);
    }
}
