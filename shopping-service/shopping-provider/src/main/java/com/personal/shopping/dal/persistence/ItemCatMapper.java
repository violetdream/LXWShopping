package com.personal.shopping.dal.persistence;

import com.personal.shopping.dal.entitys.ItemCat;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import java.util.Map;

@Mapper
public interface ItemCatMapper{
    List<ItemCat> selectItemCats(Map map);
}