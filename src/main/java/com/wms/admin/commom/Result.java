package com.wms.admin.commom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

@Data
public class Result<T> {

    private T data;

    private String code;

    private String message;


    private Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }
    private Result(String code,String msg) {
        this.code = code;
        this.message = msg;
    }

    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result error(ResultCode resultCode) {
        return new Result(resultCode);
    }
    public static Result error(String code,String msg) {
        return new Result(code,msg);
    }
    public Result data(T data) {
        this.data = data;
        return this;
    }

}
