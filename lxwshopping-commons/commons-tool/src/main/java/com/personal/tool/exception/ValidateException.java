package com.personal.tool.exception;

/**
 * 2019/10/11/0011
 * Create by 刘仙伟
 */
public class ValidateException extends BaseException {
    public ValidateException() {
        super();
    }

    public ValidateException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ValidateException(Throwable arg0) {
        super(arg0);
    }

    public ValidateException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ValidateException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public ValidateException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}
