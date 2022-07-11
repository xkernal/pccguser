package com.pccg.pccguser.infrastructure.common;

public enum ErrorCode {
    Normal(200, ""),
    IllegalArgument(2000, "illegal argument"),
    SysError(500, "server error");

    ErrorCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private int code;
    private String msg;

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
