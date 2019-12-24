package com.personal.order.utils;

import com.personal.ResultModule.AbstractResponse;
import com.personal.order.constant.OrderRetCode;
import com.personal.tool.exception.ExceptionUtil;

public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response, Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(OrderRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(OrderRetCode.SYSTEM_ERROR.getMessage());
        }
    }
}
