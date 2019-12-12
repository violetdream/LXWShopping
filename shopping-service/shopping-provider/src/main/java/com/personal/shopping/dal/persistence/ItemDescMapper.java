package com.personal.shopping.dal.persistence;


import com.personal.shopping.dal.entitys.ItemDesc;

public interface ItemDescMapper{

    ItemDesc selectByPrimaryKey(Long itemId);
}