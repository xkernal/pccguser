package com.pccg.pccguser.infrastructure.common.exception;

import com.pccg.pccguser.infrastructure.common.ErrorCode;

public class PcgException extends RuntimeException{
    private String msg;
    private int code = ErrorCode.SysError.getCode();

    public PcgException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public PcgException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }

    public PcgException(String msg, int code) {
        super(msg);
        this.msg = msg;
        this.code = code;
    }

    public PcgException(String msg, int code, Throwable e) {
        super(msg, e);
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
