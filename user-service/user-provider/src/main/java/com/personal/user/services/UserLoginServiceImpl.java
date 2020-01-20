package com.personal.user.services;

import com.alibaba.fastjson.JSON;
import com.personal.user.constants.SysRetCodeConstants;
import com.personal.user.datamodel.CheckAuthRequest;
import com.personal.user.datamodel.CheckAuthResponse;
import com.personal.user.datamodel.UserLoginRequest;
import com.personal.user.datamodel.UserLoginResponse;
import com.personal.user.datamodel.entitys.User;
import com.personal.user.datamodel.persistence.UserMapper;
import com.personal.user.service.IUserLoginService;
import com.personal.user.utils.ExceptionProcessorUtils;
import com.personal.user.utils.JwtTokenUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.DigestUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 2019/10/23/0023
 * Create by 刘仙伟
 */
@Slf4j
@Service
public class UserLoginServiceImpl implements IUserLoginService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public UserLoginResponse login(UserLoginRequest request) {
        log.info("Begin UserLoginServiceImpl.login: request:"+request);
        UserLoginResponse response=new UserLoginResponse();
        request.requestCheck();
        User user=userMapper.selectUserByName(request.getUserName());
        log.info("user="+user);
        if(user==null){
            response.setCode(SysRetCodeConstants.USERORPASSWORD_ERRROR.getCode());
            response.setMsg(SysRetCodeConstants.USERORPASSWORD_ERRROR.getMessage());
            return response;
        }
        //校验密码
        if(!DigestUtils.md5DigestAsHex(request.getPassword().getBytes()).equals(user.getPassword())) {
            response.setCode(SysRetCodeConstants.USERORPASSWORD_ERRROR.getCode());
            response.setMsg(SysRetCodeConstants.USERORPASSWORD_ERRROR.getMessage());
            log.error("login error : " + response);
            return response;
        }
        //校验正确，登录成功
        Map<String,Object> map=new HashMap<>();
        map.put("uid",user.getId());
        map.put("file",user.getFile());
        String token= JwtTokenUtils.createJWTToken(JSON.toJSONString(map));
        response.setToken(token);
        BeanUtils.copyProperties(user,response);
        response.setCode(SysRetCodeConstants.SUCCESS.getCode());
        response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        return response;
    }

    @Override
    public CheckAuthResponse validToken(CheckAuthRequest request) {
        log.info("Begin UserLoginServiceImpl.validToken: request:"+request);
        CheckAuthResponse response=new CheckAuthResponse();
        response.setCode(SysRetCodeConstants.SUCCESS.getCode());
        response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        try{
            request.requestCheck();
            String decodeMsg=JwtTokenUtils.validateJWTToken(request.getToken());
            if(StringUtils.isNotBlank(decodeMsg)){
                log.info("validate success");
                response.setUserInfo(decodeMsg);
                return response;
            }
            response.setCode(SysRetCodeConstants.TOKEN_VALID_FAILED.getCode());
            response.setMsg(SysRetCodeConstants.TOKEN_VALID_FAILED.getMessage());
        }catch (Exception e){
            log.error("UserLoginServiceImpl.validToken Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }
}
