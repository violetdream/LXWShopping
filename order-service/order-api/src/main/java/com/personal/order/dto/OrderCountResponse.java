package com.personal.order.dto;

import com.personal.ResultModule.AbstractResponse;
import lombok.Data;

@Data
public class OrderCountResponse extends AbstractResponse {

    private int count;
}
