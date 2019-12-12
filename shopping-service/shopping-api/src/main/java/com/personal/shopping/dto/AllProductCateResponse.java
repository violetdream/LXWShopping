package com.personal.shopping.dto;

import com.personal.ResultModule.AbstractResponse;
import lombok.Data;

import java.util.List;

/**
 * Created by oahnus on 2019/8/8
 * 21:46.
 */
@Data
public class AllProductCateResponse extends AbstractResponse {
    private List<ProductCateDto> productCateDtoList;
}
