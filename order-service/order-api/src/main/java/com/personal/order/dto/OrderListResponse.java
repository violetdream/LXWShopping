package com.personal.order.dto;


import com.personal.ResultModule.AbstractResponse;
import lombok.Data;
import java.util.List;

@Data
public class OrderListResponse extends AbstractResponse {

    private List<OrderDetailInfo> detailInfoList;

    /**
     * 总记录数
     */
    private Long total;

}
