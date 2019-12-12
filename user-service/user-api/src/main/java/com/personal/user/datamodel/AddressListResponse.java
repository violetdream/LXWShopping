package com.personal.user.datamodel;

import com.personal.ResultModule.AbstractResponse;
import lombok.Data;

import java.util.List;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/31-19:15
 */
@Data
public class AddressListResponse extends AbstractResponse {

    private List<AddressDto> addressDtos;
}
