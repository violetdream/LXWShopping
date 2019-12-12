package com.personal.shopping.dto;

import com.personal.ResultModule.AbstractRequest;
import com.personal.shopping.constants.ShoppingRetCode;
import com.personal.tool.exception.ValidateException;
import lombok.Data;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/24-16:27
 */
@Data
public class ProductDetailRequest extends AbstractRequest {

    private Long id;

    @Override
    public void requestCheck() {
        if(id==null){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
