package com.personal.shopping.form;

import lombok.Data;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/29-下午10:23
 */
@Data
public class PageInfo {

    private Integer page;
    private Integer size;
    private String sort;
    private Integer priceGt;
    private Integer priceLte;
    private Long cid;
}
