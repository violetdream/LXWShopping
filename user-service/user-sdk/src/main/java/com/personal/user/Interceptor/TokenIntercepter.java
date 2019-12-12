package com.personal.user.Interceptor;

import com.alibaba.fastjson.JSON;
import com.personal.ResultModule.ResponseData;
import com.personal.ResultModule.ResponseUtil;
import com.personal.tool.utils.CookieUtil;
import com.personal.user.anotation.Anoymous;
import com.personal.user.constants.SysRetCodeConstants;
import com.personal.user.datamodel.CheckAuthRequest;
import com.personal.user.datamodel.CheckAuthResponse;
import com.personal.user.service.IUserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 2019/11/18/0018
 * Create by 刘仙伟
 */
public class TokenIntercepter extends HandlerInterceptorAdapter {

    @Reference(timeout = 3000)
    IUserLoginService iUserLoginService;

    public static String ACCESS_TOKEN="access_token";

    public static String USER_INFO_KEY="userInfo";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(!(handler instanceof HandlerMethod)){
            return true;
        }
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Object bean=handlerMethod.getBean();
        if(isAnoymous(handlerMethod)){
            return true;
        }
        String token= CookieUtil.getCookieValue(request,ACCESS_TOKEN);
        if(StringUtils.isEmpty(token)){
            ResponseData responseData=new ResponseUtil().setErrorMsg("token已失效");
            response.setContentType("text/html;charset=UTF-8");
            response.getWriter().write(JSON.toJSON(responseData).toString());
            return false;
        }
        CheckAuthRequest checkAuthRequest=new CheckAuthRequest();
        checkAuthRequest.setToken(token);
        CheckAuthResponse checkAuthResponse=iUserLoginService.validToken(checkAuthRequest);
        if(checkAuthResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())){
            request.setAttribute(USER_INFO_KEY,checkAuthResponse.getUserInfo()); //保存token解析后的信息后续要用
            return super.preHandle(request, response, handler);
        }
        ResponseData responseData=new ResponseUtil().setErrorMsg(checkAuthResponse.getMsg());
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().write(JSON.toJSON(responseData).toString());
        return false;
    }

    private boolean isAnoymous(HandlerMethod handlerMethod){
        Object bean=handlerMethod.getBean();
        Class clazz=bean.getClass();
        if(clazz.getAnnotation(Anoymous.class)!=null){
            return true;
        }
        Method method=handlerMethod.getMethod();
        return method.getAnnotation(Anoymous.class)!=null;
    }
}
