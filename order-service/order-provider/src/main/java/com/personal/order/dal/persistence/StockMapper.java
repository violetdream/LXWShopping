package com.personal.order.dal.persistence;

import com.personal.order.dal.entitys.Stock;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Description:
 * @Author： wz
 * @Date: 2019-09-16 00:09
 **/
@Mapper
public interface StockMapper {
     void updateStock(Stock stock);
     Stock selectStockForUpdate(Long itemId);
     Stock selectStock(Long itemId);
     List<Stock> findStocksForUpdate(List<Long> itemIds);
}