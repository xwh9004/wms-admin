package com.wms.admin.commom;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class Result<T> {

    private T data;

    private String code;

    private String message;


    private Result(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.message = resultCode.getMsg();
    }


    public static Result success() {
        return new Result(ResultCode.SUCCESS);
    }

    public static Result error(ResultCode resultCode) {
        return new Result(resultCode);
    }
    public Result data(T data) {
        this.data = data;
        return this;
    }


}
