package com.personal.ResultModule;

import java.io.Serializable;

/**
 * 2019/10/11/0011
 * Create by 刘仙伟
 */
public abstract class AbstractRequest implements Serializable {

    private static final long serialVersionUID = -7161332201051487747L;

    public abstract void requestCheck();

    @Override
    public String toString() {
        return "AbstractRequest{}";
    }
}
