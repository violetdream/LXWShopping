package com.personal.shopping.dto;

import com.personal.ResultModule.AbstractResponse;
import lombok.Data;
import java.util.List;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/24-16:29
 */
@Data
public class AllProductResponse extends AbstractResponse {

    private List<ProductDto> productDtoList;

    private Long total;
}
