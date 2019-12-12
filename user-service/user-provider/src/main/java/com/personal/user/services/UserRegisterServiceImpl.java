package com.personal.user.services;

import com.personal.tool.exception.ValidateException;
import com.personal.user.constants.SysRetCodeConstants;
import com.personal.user.datamodel.UserRegisterRequest;
import com.personal.user.datamodel.UserRegisterResponse;
import com.personal.user.datamodel.entitys.User;
import com.personal.user.datamodel.persistence.UserMapper;
import com.personal.user.service.IUserRegisterService;
import com.personal.user.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import java.util.Date;

/**
 * 2019/10/24/0024
 * Create by 刘仙伟
 */
@Slf4j
@Service
public class UserRegisterServiceImpl implements IUserRegisterService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserRegisterResponse register(UserRegisterRequest request) {
        log.info("Begin UserLoginServiceImpl.register: request:" + request);
        UserRegisterResponse response = new UserRegisterResponse();
        try{
//            validUserRegisterRequest(request);
            User user = new User();
            user.setUsername(request.getUserName());
            user.setPassword(DigestUtils.md5DigestAsHex(request.getUserPwd().getBytes()));
            user.setState(1);
            user.setCreated(new Date());
            user.setUpdated(new Date());
            if(userMapper.insertUser(user)!=1){
                response.setCode(SysRetCodeConstants.USER_REGISTER_FAILED.getCode());
                response.setMsg(SysRetCodeConstants.USER_REGISTER_FAILED.getMessage());
                return response;
            }
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        }catch (Exception e){
            log.error("UserLoginServiceImpl.register Occur Exception :",e);
            ExceptionProcessorUtils.wrapperHandlerException(response, e);
        }
        return response;
    }

    private void validUserRegisterRequest(UserRegisterRequest request){
        request.requestCheck();
        User user = userMapper.selectUserByName(request.getUserName());
        if (user!=null) {
            throw new ValidateException(SysRetCodeConstants.USERNAME_ALREADY_EXISTS.getCode(), SysRetCodeConstants.USERNAME_ALREADY_EXISTS.getMessage());
        }

    }
}
