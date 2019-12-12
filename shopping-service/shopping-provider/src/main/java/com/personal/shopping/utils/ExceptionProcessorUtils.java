package com.personal.shopping.utils;

import com.personal.ResultModule.AbstractResponse;
import com.personal.shopping.constants.ShoppingRetCode;
import com.personal.tool.exception.ExceptionUtil;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/22-15:48
 */
public class ExceptionProcessorUtils {

    public static void wrapperHandlerException(AbstractResponse response, Exception e){
        try {
            ExceptionUtil.handlerException4biz(response,e);
        } catch (Exception ex) {
            response.setCode(ShoppingRetCode.SYSTEM_ERROR.getCode());
            response.setMsg(ShoppingRetCode.SYSTEM_ERROR.getMessage());
        }
    }
}
