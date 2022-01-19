package com.wms.admin.exception;

import com.wms.admin.commom.ResultCode;

public class BusinessException extends RuntimeException{

    private String code;

    private String msg;

    public BusinessException(String code,String msg){
        super(msg);
        this.code = code;
    }


    public BusinessException(ResultCode resultCode){
        super(resultCode.getMsg());
        this.code = resultCode.getCode();
    }
}
