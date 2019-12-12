package com.personal.shopping.dto;

import com.personal.ResultModule.AbstractResponse;
import lombok.Data;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/24-16:27
 */

@Data
public class ProductDetailResponse extends AbstractResponse {
    private ProductDetailDto productDetailDto;
}
