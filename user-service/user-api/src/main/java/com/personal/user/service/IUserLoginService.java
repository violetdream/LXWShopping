package com.personal.user.service;

import com.personal.user.datamodel.CheckAuthRequest;
import com.personal.user.datamodel.CheckAuthResponse;
import com.personal.user.datamodel.UserLoginRequest;
import com.personal.user.datamodel.UserLoginResponse;

/**
 * 2019/10/16/0016
 * Create by 刘仙伟
 */
public interface IUserLoginService {

    /**
     *用户登录
     * @param request
     * @return
     */
    public UserLoginResponse login(UserLoginRequest request);

    /**
     * 校验过程
     * @param request
     * @return
     */
    CheckAuthResponse validToken(CheckAuthRequest request);
}
