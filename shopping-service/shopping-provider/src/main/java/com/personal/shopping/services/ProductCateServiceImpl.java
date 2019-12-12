package com.personal.shopping.services;

import com.alibaba.fastjson.JSON;
import com.personal.shopping.IProductCateService;
import com.personal.shopping.constant.GlobalConstants;
import com.personal.shopping.constants.ShoppingRetCode;
import com.personal.shopping.converter.ProductCateConverter;
import com.personal.shopping.dal.entitys.ItemCat;
import com.personal.shopping.dal.persistence.ItemCatMapper;
import com.personal.shopping.dto.AllProductCateRequest;
import com.personal.shopping.dto.AllProductCateResponse;
import com.personal.shopping.dto.ProductCateDto;
import com.personal.shopping.services.cache.CacheManager;
import com.personal.shopping.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by oahnus on 2019/8/8
 * 21:53.
 */
@Slf4j
@Service
public class ProductCateServiceImpl implements IProductCateService {
    @Autowired
    ItemCatMapper itemCatMapper;
    @Autowired
    CacheManager cacheManager;
    @Autowired
    ProductCateConverter productCateConverter;

    @Override
    public AllProductCateResponse getAllProductCate(AllProductCateRequest request) {
        AllProductCateResponse response = new AllProductCateResponse();
        response.setCode(ShoppingRetCode.SUCCESS.getCode());
        response.setMsg(ShoppingRetCode.SUCCESS.getMessage());

        try{
            String json=cacheManager.checkCache(GlobalConstants.PRODUCT_CATE_CACHE_KEY);
            if(StringUtils.isNotBlank(json)){
                List<ProductCateDto> productCateDtos = JSON.parseArray(json,ProductCateDto.class);
                response.setProductCateDtoList(productCateDtos);
                return response;
            }
//            Example itemCatExample = new Example(ItemCat.class);
//            ItemCatExample itemCatExample = new ItemCatExample();
//            ItemCatExample.Criteria criteria = itemCatExample.createCriteria();
//            String orderCol = "sort_order";
//            String orderDir = "asc";
            Map paramMap=new HashMap();
            paramMap.put("Sort","1");
          /*  if(request.getSort().equals("1")){
                orderCol="sort_order";
                orderDir="asc";
            }else if(request.getSort().equals("-1")){
                orderCol="sort_order";
                orderDir="desc";
            }*/
            if(request.getSort().equals("-1")){
                paramMap.put("Sort","-1");
            }
//            itemCatExample.selectItemCats(orderCol + " " + orderDir);

            List<ItemCat> itemCats = itemCatMapper.selectItemCats(paramMap);
            List<ProductCateDto> productCateDtoList = productCateConverter.items2Dto(itemCats);
            response.setProductCateDtoList(productCateDtoList);
            //设置缓存
            cacheManager.setCache(
                    genProductCateCacheKey(request),
                    JSON.toJSON(productCateDtoList).toString(),
                    GlobalConstants.PRODUCT_CATE_EXPIRE_TIME);
        }catch (Exception e){
            log.error("ProductCateServiceImpl.getAllProductCate Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    private String genProductCateCacheKey(AllProductCateRequest request) {
        return GlobalConstants.PRODUCT_CATE_CACHE_KEY;
    }
}
