package com.personal.ResultModule;

/**
 * 2019/10/11/0011
 * Create by 刘仙伟
 */
public class ResponseData<T> {
    private boolean success;

    private int code;//错误码

    private String message;//消息

    private T result;

    private long timeStamp=System.currentTimeMillis();

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
