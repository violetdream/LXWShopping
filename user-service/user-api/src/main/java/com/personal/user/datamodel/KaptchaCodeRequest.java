package com.personal.user.datamodel;

import com.personal.ResultModule.AbstractRequest;
import com.personal.tool.exception.ValidateException;
import com.personal.user.constants.SysRetCodeConstants;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

/**
 * 2019/10/16/0016
 * Create by 刘仙伟
 */
@Data
public class KaptchaCodeRequest extends AbstractRequest {
    private String uuid;

    private String code;

    @Override
    public void requestCheck() {
        if(StringUtils.isBlank(uuid)||StringUtils.isBlank(code)){
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
