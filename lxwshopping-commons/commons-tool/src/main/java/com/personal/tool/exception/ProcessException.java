package com.personal.tool.exception;

/**
 * 2019/10/11/0011
 * Create by 刘仙伟
 */
public class ProcessException extends BaseException{
    public ProcessException() {
        super();
    }

    public ProcessException(String errorCode) {
        super();
        this.errorCode = errorCode;
    }

    public ProcessException(Throwable arg0) {
        super(arg0);
    }

    public ProcessException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
    }

    public ProcessException(String errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public ProcessException(String errorCode, String message, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.message = message;
    }
}
