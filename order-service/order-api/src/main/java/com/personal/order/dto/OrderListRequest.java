package com.personal.order.dto;


import com.personal.ResultModule.AbstractRequest;
import com.personal.order.constant.OrderRetCode;
import com.personal.tool.exception.ValidateException;
import lombok.Data;

@Data
public class OrderListRequest extends AbstractRequest {

    private Long userId;
    private Integer page;
    private Integer size;
    private String sort;

    @Override
    public void requestCheck() {
        if(page == null || page < 1){
            page = 1;
        }
        if(size == null || size < 1){
            size = 5;
        }
        if(userId==null){
            throw new ValidateException(OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),OrderRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());

        }
    }
}
