package com.personal.shopping.form;

import com.personal.order.dto.OrderItemDto;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;


@Data
public class OrderDetail {
   private String userName;
    private BigDecimal orderTotal;
    private long userId;
    private List<OrderItemDto> goodsList;
    private String tel;
    private String streetName;
}
