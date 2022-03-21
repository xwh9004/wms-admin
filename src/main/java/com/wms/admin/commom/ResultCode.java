package com.wms.admin.commom;

public enum ResultCode {
    SUCCESS("000000", "成功"),
    RESOURCE_EXISTS("400001", "%s已存在"),
    RESOURCE_NOT_EXISTS("400002", "%s不存在"),
    RESOURCE_NOT_AVAILABLE("400003", "%s不可用"),
    PARAM_NOT_NULL("400044", "参数%s,不能为空"),
    PARAM_ERROR("400045", "参数错误,%s"),
    DATA_NO_MODIFY("400005", "%s,不可修改"),
    DATA_REPEAT("400006", "%s,不可重复"),
    STOCK_LACKED("400007", "%s,库存不足"),
    DATA_ERROR("400008", "%s,数据异常"),
    COMMON_ERROR("410000", "%s"),
    SYS_ERROR("999999", "系统异常，%s"),
    PERMISSION_TOKEN_EXPIRED("PERMISSION TOKEN EXPIRED", "TOKEN已过期，请重新验证"),
    PERMISSION_SIGNATURE_ERROR("PERMISSION_SIGNATURE_ERROR", "签名异常"),
    PERMISSION_TOKEN_INVALID("PERMISSION_TOKEN_INVALID", "TOKEN验证失败"),
    USER_NOT_LOGGED_IN("USER_NOT_LOGGED_IN", "用户未登入"),
    USER_PWD_ERROR("500001", "T用户名或密码错误"),
    ;

    private String code;
    private String msg;

    private ResultCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
