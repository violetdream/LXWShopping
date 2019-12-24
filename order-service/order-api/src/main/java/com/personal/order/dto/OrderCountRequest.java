package com.personal.order.dto;

import com.personal.ResultModule.AbstractRequest;
import com.personal.order.constant.OrderRetCode;
import com.personal.tool.exception.ValidateException;
import lombok.Data;

@Data
public class OrderCountRequest extends AbstractRequest {

    private Long userId;

    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
