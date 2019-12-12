package com.personal.shopping.dal.persistence;

import com.personal.shopping.dal.entitys.Item;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemMapper{

    List<Item> selectItemFront(@Param("cid") Long cid,
                                 @Param("orderCol") String orderCol,@Param("orderDir") String orderDir,
                                 @Param("priceGt") Integer priceGt,@Param("priceLte") Integer priceLte);
    Item selectByPrimaryKey(Long id);
}