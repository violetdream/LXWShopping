package com.personal.user.controller;

import com.personal.ResultModule.ResponseData;
import com.personal.ResultModule.ResponseUtil;
import com.personal.tool.utils.CookieUtil;
import com.personal.user.constants.SysRetCodeConstants;
import com.personal.user.datamodel.KaptchaCodeRequest;
import com.personal.user.datamodel.KaptchaCodeResponse;
import com.personal.user.datamodel.UserLoginRequest;
import com.personal.user.datamodel.UserLoginResponse;
import com.personal.user.service.IKaptchaCodeService;
import com.personal.user.service.IUserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 2019/10/25/0025
 * Create by 刘仙伟
 */
@RestController
@RequestMapping(value="/user")
@Slf4j
public class LoginController {
    @Reference(timeout = 3000,check=false)
    private IUserLoginService iUserLoginService;

    @Reference(timeout = 3000,check=false)
    private IKaptchaCodeService iKaptchaCodeService;

    /**
     * 验证码开关
     */
    @Value("${captchaFlag:true}")
    private boolean captchaFlag;


    @PostMapping(value="/login")
    public ResponseData login(@RequestBody Map<String,String> map, HttpServletRequest request, HttpServletResponse response){
        UserLoginRequest loginRequest=new UserLoginRequest();
        loginRequest.setPassword(map.get("userPwd"));
        loginRequest.setUserName(map.get("userName"));
        String captcha=map.get("captcha");

        if (captchaFlag) {
            KaptchaCodeRequest kaptchaCodeRequest = new KaptchaCodeRequest();
            String uuid = CookieUtil.getCookieValue(request, "kaptcha_uuid");
            kaptchaCodeRequest.setCode(captcha);
            kaptchaCodeRequest.setUuid(uuid);
            KaptchaCodeResponse kaptchaCodeResponse = iKaptchaCodeService.validateKaptchaCode(kaptchaCodeRequest);
            if (!kaptchaCodeResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
                return new ResponseUtil<>().setErrorMsg(kaptchaCodeResponse.getMsg());
            }
        }
        UserLoginResponse userLoginResponse=iUserLoginService.login(loginRequest);
        log.info("Login Response : "+userLoginResponse);
        if(userLoginResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            Cookie cookie=CookieUtil.genCookie("access_token",userLoginResponse.getToken(),"/",24*60*60);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return new ResponseUtil().setData(userLoginResponse);
        }
        return new ResponseUtil().setErrorMsg(userLoginResponse.getMsg());
    }

}
