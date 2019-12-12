package com.personal.user.service;

import com.personal.user.datamodel.KaptchaCodeRequest;
import com.personal.user.datamodel.KaptchaCodeResponse;

/**
 * 2019/10/16/0016
 * Create by 刘仙伟
 */
public interface IKaptchaCodeService {

    /**
     * 获取图形验证码
     * @param request
     * @return
     */
    public KaptchaCodeResponse getKaptchaCode(KaptchaCodeRequest request);

    /**
     * 验证图形验证码
     * @param request
     * @return
     */
    public KaptchaCodeResponse validateKaptchaCode(KaptchaCodeRequest request);
}
