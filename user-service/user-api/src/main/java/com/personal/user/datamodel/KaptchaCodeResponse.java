package com.personal.user.datamodel;

import com.personal.ResultModule.AbstractResponse;
import lombok.Data;

/**
 * 2019/10/16/0016
 * Create by 刘仙伟
 */
@Data
public class KaptchaCodeResponse extends AbstractResponse {
    private String imageCode;

    private String uuid;
}
