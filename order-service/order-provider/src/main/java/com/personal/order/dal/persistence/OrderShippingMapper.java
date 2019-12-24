package com.personal.order.dal.persistence;

import com.personal.order.dal.entitys.OrderShipping;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderShippingMapper  {
    OrderShipping selectByPrimaryKey(String orderId);
    int deleteByPrimaryKey(String orderId);
    int insertOrderShipping(OrderShipping orderShipping);
}