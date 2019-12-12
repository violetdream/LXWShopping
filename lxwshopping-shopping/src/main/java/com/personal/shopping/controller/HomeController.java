package com.personal.shopping.controller;


import com.personal.ResultModule.ResponseData;
import com.personal.ResultModule.ResponseUtil;
import com.personal.shopping.IContentService;
import com.personal.shopping.IHomeService;
import com.personal.shopping.constants.ShoppingRetCode;
import com.personal.shopping.dto.HomePageResponse;
import com.personal.shopping.dto.NavListResponse;
import com.personal.user.anotation.Anoymous;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/23-17:06
 */
@RestController
@RequestMapping("/shopping")
public class HomeController {

    @Reference(timeout = 3000,check = false)
    IContentService contentService;

    @Reference(timeout = 3000,check = false)
    IHomeService iHomeService;

    @Anoymous
    @GetMapping("/navigation")
    public ResponseData navigation(){
        NavListResponse response=contentService.queryNavList();
        if(response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return new ResponseUtil().setData(response.getPannelContentDtos());
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }

    @Anoymous
    @GetMapping("/homepage")
    public ResponseData homepage(){
        HomePageResponse response=iHomeService.homepage();
        if(response.getCode().equals(ShoppingRetCode.SUCCESS.getCode())) {
            return new ResponseUtil().setData(response.getPanelContentItemDtos());
        }
        return new ResponseUtil().setErrorMsg(response.getMsg());
    }


}
