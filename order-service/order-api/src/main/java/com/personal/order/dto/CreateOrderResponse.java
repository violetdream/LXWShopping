package com.personal.order.dto;

import com.personal.ResultModule.AbstractResponse;
import lombok.Data;

@Data
public class CreateOrderResponse extends AbstractResponse {

    private String orderId;
}
