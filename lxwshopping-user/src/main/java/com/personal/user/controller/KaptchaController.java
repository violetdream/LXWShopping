package com.personal.user.controller;

import com.personal.ResultModule.ResponseData;
import com.personal.ResultModule.ResponseUtil;
import com.personal.tool.utils.CookieUtil;
import com.personal.user.constants.SysRetCodeConstants;
import com.personal.user.datamodel.KaptchaCodeRequest;
import com.personal.user.datamodel.KaptchaCodeResponse;
import com.personal.user.service.IKaptchaCodeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 2019/10/30/0030
 * Create by 刘仙伟
 */
@Slf4j
@RestController
@RequestMapping(value="/user")
public class KaptchaController {

    @Reference(timeout = 3000,check=false)
    private IKaptchaCodeService kaptchaCodeService;

    @GetMapping(value="/kaptcha")
    public ResponseData genKaptcha(HttpServletRequest request, HttpServletResponse response){
        KaptchaCodeResponse kaptchaCodeResponse=kaptchaCodeService.getKaptchaCode(new KaptchaCodeRequest());
        log.info("生成验证码 ："+kaptchaCodeResponse);
        if (kaptchaCodeResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
            Cookie cookie=CookieUtil.genCookie("kaptcha_uuid",kaptchaCodeResponse.getUuid(),"/",60);
            cookie.setHttpOnly(true);
            response.addCookie(cookie);
            return  new ResponseUtil().setData(kaptchaCodeResponse.getImageCode());
        }
        return  new ResponseUtil().setData(kaptchaCodeResponse.getCode());
    }
}
