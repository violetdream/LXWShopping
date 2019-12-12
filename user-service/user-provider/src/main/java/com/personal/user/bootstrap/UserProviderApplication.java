package com.personal.user.bootstrap;

import com.personal.user.datamodel.Image;
import com.personal.user.datamodel.KaptchaCodeRequest;
import com.personal.user.datamodel.KaptchaCodeResponse;
import com.personal.user.datamodel.UserLoginRequest;
import com.personal.user.datamodel.persistence.UserMapper;
import com.personal.user.services.KaptchaServiceImpl;
import com.personal.user.services.UserLoginServiceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

import java.util.HashMap;
import java.util.Map;

/**
 * 2019/10/16/0016
 * Create by 刘仙伟
 */
@SpringBootApplication
@ComponentScan(basePackages = {"com.personal.user.services","com.personal.user.converter"})
@MapperScan(basePackages="com.personal.user.datamodel")
public class UserProviderApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context=SpringApplication.run(UserProviderApplication.class,args);

//        KaptchaServiceImpl kaptchaService= (KaptchaServiceImpl) context.getBean("kaptchaServiceImpl");
//        Map<String,Object> map=new HashMap<String,Object>();
//        KaptchaCodeRequest request=new KaptchaCodeRequest();
//        for(int i=0;i<2000;i++){
//            KaptchaCodeResponse response=kaptchaService.getKaptchaCode(new KaptchaCodeRequest());
//            if(map.get(response.getImageCode())==null){
//                map.put(response.getImageCode(),response);
//            }else{
//                System.out.println("60s内有重复的token生成 redis里有两份一样的code，response = " + response);
//                request.setUuid(response.getUuid());
//                request.setCode(response.getCode());
//                KaptchaCodeResponse validateResponse=kaptchaService.validateKaptchaCode(request);
//                System.out.println("validateResponse = " + validateResponse);
//            }
//        }
//        for (String beanName: context.getBeanDefinitionNames()){
//            System.out.println("beanName = " + beanName+" "+context.getBean(beanName));
//        }
//
//        UserLoginServiceImpl userLoginService= (UserLoginServiceImpl) context.getBean("userLoginServiceImpl");
//        UserLoginRequest request=new UserLoginRequest();
//        request.setPassword("11111");
//        request.setUserName("test");
//        userLoginService.login(request);
    }
}
