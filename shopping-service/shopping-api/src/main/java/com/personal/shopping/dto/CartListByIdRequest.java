package com.personal.shopping.dto;

import com.personal.ResultModule.AbstractRequest;
import com.personal.shopping.constants.ShoppingRetCode;
import com.personal.tool.exception.ValidateException;
import lombok.Data;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/23-18:59
 */
@Data
public class CartListByIdRequest extends AbstractRequest {
    private Long userId;
    @Override
    public void requestCheck() {
        if(userId==null||userId.intValue()==0){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
