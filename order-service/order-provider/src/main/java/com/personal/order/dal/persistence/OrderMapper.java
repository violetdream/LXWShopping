package com.personal.order.dal.persistence;

import com.personal.order.dal.entitys.Order;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;

@Mapper
public interface OrderMapper{
    Long countAll();
    Order selectByPrimaryKey(String orderId);
    List<Order> selectByUserId(Long userId);
    int updateByPrimaryKey(Order order);
    int deleteByPrimaryKey(String orderId);
    int insertOrder(Order order);
}