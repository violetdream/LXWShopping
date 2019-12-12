package com.personal.shopping.services;

import com.personal.shopping.IContentService;
import com.personal.shopping.constant.GlobalConstants;
import com.personal.shopping.constants.ShoppingRetCode;
import com.personal.shopping.converter.ContentConverter;
import com.personal.shopping.dal.entitys.PanelContent;
import com.personal.shopping.dal.persistence.PanelContentMapper;
import com.personal.shopping.dto.NavListResponse;
import com.personal.shopping.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Slf4j
@Service
public class ContentServiceImpl implements IContentService {

    @Autowired
    PanelContentMapper panelContentMapper;

    @Autowired
    ContentConverter contentConverter;

    @Override
    public NavListResponse queryNavList() {
        NavListResponse response=new NavListResponse();
        try {
            Map paramMap=new HashMap();
            paramMap.put("panelId", GlobalConstants.HEADER_PANEL_ID);
            List<PanelContent> pannelContents = panelContentMapper.selectPanelContents(paramMap);
            // TODO 添加缓存操作
            response.setPannelContentDtos(contentConverter.panelContents2Dto(pannelContents));
            response.setCode(ShoppingRetCode.SUCCESS.getCode());
            response.setMsg(ShoppingRetCode.SUCCESS.getMessage());
        }catch (Exception e){
            log.error("ContentServiceImpl.queryNavList Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }
}
