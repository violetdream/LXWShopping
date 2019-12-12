package com.personal.user.service;

import com.personal.user.datamodel.UserRegisterRequest;
import com.personal.user.datamodel.UserRegisterResponse;

/**
 * 2019/10/16/0016
 * Create by 刘仙伟
 */
public interface IUserRegisterService {

    public UserRegisterResponse register(UserRegisterRequest request);

}
