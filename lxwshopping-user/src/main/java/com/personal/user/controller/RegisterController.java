package com.personal.user.controller;

import com.personal.ResultModule.ResponseData;
import com.personal.ResultModule.ResponseUtil;
import com.personal.tool.utils.CookieUtil;
import com.personal.user.constants.SysRetCodeConstants;
import com.personal.user.datamodel.KaptchaCodeRequest;
import com.personal.user.datamodel.KaptchaCodeResponse;
import com.personal.user.datamodel.UserRegisterRequest;
import com.personal.user.datamodel.UserRegisterResponse;
import com.personal.user.service.IKaptchaCodeService;
import com.personal.user.service.IUserRegisterService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.config.support.Parameter;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;


/**
 * 2019/10/30/0030
 * Create by 刘仙伟
 */
@Slf4j
@RestController
@RequestMapping(value="/user")
public class RegisterController {
    @Reference(timeout = 3000,check=false)
    private IUserRegisterService userRegisterService;

    @Reference(timeout = 3000,check=false)
    private IKaptchaCodeService kaptchaCodeService;

    @PostMapping(value="/register")
    //TUDO 跟前端Axios发送的Content-Type默认是application/json;charset=utf-8有关
//    public ResponseData register(@RequestParam(value="userName") String userName, @RequestParam String userPwd, @RequestParam String captcha, HttpServletRequest request, HttpServletResponse response){
    public ResponseData register(@RequestBody Map<String,String> map, HttpServletRequest request, HttpServletResponse response){
        log.info("Request Content-Type : {}",request.getHeader("Content-Type"));
        KaptchaCodeRequest kaptchaCodeRequest=new KaptchaCodeRequest();
        String uuid=CookieUtil.getCookieValue(request,"kaptcha_uuid");
        kaptchaCodeRequest.setUuid(uuid);
        kaptchaCodeRequest.setCode(map.get("captcha"));
        KaptchaCodeResponse kaptchaCodeResponse = kaptchaCodeService.validateKaptchaCode(kaptchaCodeRequest);
        if (!kaptchaCodeResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            return new ResponseUtil<>().setErrorMsg(kaptchaCodeResponse.getMsg());
        }
        UserRegisterRequest registerRequest=new UserRegisterRequest();
        registerRequest.setUserName(map.get("userName"));
        registerRequest.setUserPwd(map.get("userPwd"));
        UserRegisterResponse userRegisterResponse=userRegisterService.register(registerRequest);
        if(!userRegisterResponse.getCode().equals(SysRetCodeConstants.SUCCESS)){
            return new ResponseUtil().setErrorMsg(userRegisterResponse.getMsg());
        }
        return new ResponseUtil().setData(userRegisterResponse);
    }
}
