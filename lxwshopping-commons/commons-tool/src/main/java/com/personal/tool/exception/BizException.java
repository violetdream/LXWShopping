package com.personal.tool.exception;

import com.fasterxml.jackson.databind.ser.Serializers;

/**
 * 2019/10/11/0011
 * Create by 刘仙伟
 * 业务层异常
 */
public class BizException extends BaseException {
    public BizException(){
        super();
    }
    public BizException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public BizException(Throwable arg0) {
        super(arg0);
    }

    public BizException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public BizException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public BizException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}
