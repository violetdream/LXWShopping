package com.personal.user.datamodel.persistence;


import com.personal.user.datamodel.entitys.Address;
import java.util.List;
import java.util.Map;

public interface AddressMapper{

    List<Address> selectAddresss(Map map);

    Address selectByPrimaryKey(Long addressId);

    int insertAddress(Address address);

    int updateAddressByCondition(Address address);

    int deleteAddressByCondition(Address address);

    int deleteAddressByAddressId(Long addressId);
}