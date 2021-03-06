package com.personal.user.converter;


import com.personal.user.datamodel.AddAddressRequest;
import com.personal.user.datamodel.AddressDto;
import com.personal.user.datamodel.UpdateAddressRequest;
import com.personal.user.datamodel.entitys.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/31-19:33
 */
@Mapper(componentModel = "spring")
public interface AddressConverter {

    @Mappings({})
    AddressDto address2AddressDto(Address address);

    /*@Mappings({})
    AddressDto address2Res(Address address);*/

    List<AddressDto> address2List(List<Address> addresses);

    @Mappings({})
    Address req2Address(AddAddressRequest request);

    @Mappings({})
    Address req2Address(UpdateAddressRequest request);
}
