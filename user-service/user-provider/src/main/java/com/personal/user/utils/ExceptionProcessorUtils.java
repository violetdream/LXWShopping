package com.personal.user.utils;

import com.personal.ResultModule.AbstractResponse;
import com.personal.tool.exception.ExceptionUtil;
import com.personal.user.constants.SysRetCodeConstants;

/**
 * create-date: 2019/7/22-15:48
 */
public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response, Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(SysRetCodeConstants.SYSTEM_ERROR.getCode());
            response.setMsg(SysRetCodeConstants.SYSTEM_ERROR.getMessage());
        }
    }
}
