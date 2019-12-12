package com.personal.shopping.dto;

import com.personal.ResultModule.AbstractRequest;
import com.personal.shopping.constants.ShoppingRetCode;
import com.personal.tool.exception.ValidateException;
import lombok.Data;

/**
 * Created by mic on 2019/7/23.
 */
@Data
public class DeleteCheckedItemRequest extends AbstractRequest {

    private Long userId;

    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());

        }
    }
}
