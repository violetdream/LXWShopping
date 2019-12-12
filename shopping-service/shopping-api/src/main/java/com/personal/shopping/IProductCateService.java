package com.personal.shopping;

import com.personal.shopping.dto.AllProductCateRequest;
import com.personal.shopping.dto.AllProductCateResponse;

/**
 * Created by oahnus on 2019/8/8
 * 21:38.
 */
public interface IProductCateService {
    /**
     * 获取所有产品分类
     * @param request
     * @return
     */
    AllProductCateResponse getAllProductCate(AllProductCateRequest request);
}
