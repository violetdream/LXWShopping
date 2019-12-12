package com.personal.user.datamodel;

import com.personal.ResultModule.AbstractRequest;
import com.personal.tool.exception.ValidateException;
import com.personal.user.constants.SysRetCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 2019/10/15/0015
 * Create by 刘仙伟
 */
@Data
public class UserLoginRequest extends AbstractRequest {

    private String userName;
    private String password;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(userName)||StringUtils.isBlank(password)){
            throw new ValidateException(SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
