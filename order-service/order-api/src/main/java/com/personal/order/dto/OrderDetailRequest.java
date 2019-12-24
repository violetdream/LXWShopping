package com.personal.order.dto;

import com.personal.ResultModule.AbstractRequest;
import com.personal.order.constant.OrderRetCode;
import com.personal.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

@Data
public class OrderDetailRequest extends AbstractRequest {
    private String orderId;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(orderId)){
            throw new ValidateException(OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());
        }
    }
}
