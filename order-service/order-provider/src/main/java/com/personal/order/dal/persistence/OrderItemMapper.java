package com.personal.order.dal.persistence;


import com.personal.order.dal.entitys.OrderItem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface OrderItemMapper {
    List<OrderItem> queryByOrderId(@Param("orderId") String orderId);
    int updateStockStatus(@Param("status") Integer status, @Param("orderId") String orderId);
    OrderItem selectByPrimaryKey(String orderItemId);
    int deleteByOrderId(String orderId);
    int insertOrderItem(OrderItem orderItem);
}