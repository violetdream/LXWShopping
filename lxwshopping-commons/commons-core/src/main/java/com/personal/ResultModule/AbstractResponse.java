package com.personal.ResultModule;

import java.io.Serializable;

/**
 * 2019/10/11/0011
 * Create by 刘仙伟
 */
public abstract class AbstractResponse implements Serializable {
    private static final long serialVersionUID = 210621252042546651L;
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
