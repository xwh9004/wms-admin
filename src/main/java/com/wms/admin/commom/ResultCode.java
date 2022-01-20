package com.wms.admin.commom;

public enum ResultCode {
    SUCCESS("000000", "成功"),
    RESOURCE_EXISTS("000000", "%s已存在"),
    PERMISSION_TOKEN_EXPIRED("PERMISSION TOKEN EXPIRED", "TOKEN已过期，请重新验证"),
    PERMISSION_SIGNATURE_ERROR("PERMISSION_SIGNATURE_ERROR", "签名异常"),
    PERMISSION_TOKEN_INVALID("PERMISSION_TOKEN_INVALID", "TOKEN验证失败"),
    USER_NOT_LOGGED_IN("USER_NOT_LOGGED_IN", "用户未登入"),
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
