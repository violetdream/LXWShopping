package com.personal.shopping.dto;

import com.personal.ResultModule.AbstractRequest;
import com.personal.shopping.constants.ShoppingRetCode;
import com.personal.tool.exception.ValidateException;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by mic on 2019/7/23.
 */
@Data
public class CheckAllItemRequest extends AbstractRequest {
    private Long userId;
    private String checked;

    @Override
    public void requestCheck() {
        if(userId==null|| StringUtils.isBlank(checked)){
            throw new ValidateException(ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getCode(),ShoppingRetCode.REQUISITE_PARAMETER_NOT_EXIST.getMessage());

        }
    }
}
