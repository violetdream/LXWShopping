package com.personal.user.services;

import com.personal.user.constants.SysRetCodeConstants;
import com.personal.user.converter.AddressConverter;
import com.personal.user.datamodel.*;
import com.personal.user.datamodel.entitys.Address;
import com.personal.user.datamodel.persistence.AddressMapper;
import com.personal.user.service.IAddressService;
import com.personal.user.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/31-19:27
 */
@Slf4j
@Service
public class AddressServiceImpl implements IAddressService {

    @Autowired
    AddressMapper addressMapper;

    @Autowired
    AddressConverter converter;

    @Override
    public AddressListResponse addressList(AddressListRequest request) {
        //TODO 地址信息要做缓存处理
        AddressListResponse response=new AddressListResponse();
        try{
            request.requestCheck();
            Map paramMap=new HashMap();
            paramMap.put("userId",request.getUserId());
            List<Address> addresses=addressMapper.selectAddresss(paramMap);
            response.setAddressDtos(converter.address2List(addresses));
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        }catch (Exception e){
            log.error("AddressServiceImpl.addressList occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public AddressDetailResponse addressDetail(AddressDetailRequest request) {
        AddressDetailResponse response=new AddressDetailResponse();
        try{
            request.requestCheck();
            Address address=addressMapper.selectByPrimaryKey(request.getAddressId());
            response.setAddressDto(converter.address2AddressDto(address));
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        }catch (Exception e){
            log.error("AddressServiceImpl.addressDetail occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public AddAddressResponse createAddress(AddAddressRequest request) {
        log.error("AddressServiceImpl.createAddress request :"+request);
        AddAddressResponse response=new AddAddressResponse();
        try{
            request.requestCheck();
            checkAddressDefaultUnique(request.getIsDefault() != null && request.getIsDefault()==1,request.getUserId());
            Address address=converter.req2Address(request);
            int row=addressMapper.insertAddress(address);
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            log.info("AddressServiceImpl.createAddress effect row :"+row);
        }catch (Exception e){
            log.error("AddressServiceImpl.createAddress occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public UpdateAddressResponse updateAddress(UpdateAddressRequest request) {
        log.error("begin - AddressServiceImpl.updateAddress request :"+request);
        UpdateAddressResponse response=new UpdateAddressResponse();
        try{
            request.requestCheck();
            checkAddressDefaultUnique(request.getIsDefault()==1,request.getUserId());
            Address address=converter.req2Address(request);
            int row=addressMapper.updateAddressByCondition(address);
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            log.info("AddressServiceImpl.createAddress effect row :"+row);
        }catch (Exception e){
            log.error("AddressServiceImpl.updateAddress occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    @Override
    public DeleteAddressResponse deleteAddress(DeleteAddressRequest request) {
        log.error("begin - AddressServiceImpl.deleteAddress request :"+request);
        DeleteAddressResponse response=new DeleteAddressResponse();
        try{
            request.requestCheck();
            int row=addressMapper.deleteAddressByAddressId(request.getAddressId());
            if(row>0){
                response.setCode(SysRetCodeConstants.SUCCESS.getCode());
                response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            }else{
                response.setCode(SysRetCodeConstants.DATA_NOT_EXIST.getCode());
                response.setMsg(SysRetCodeConstants.DATA_NOT_EXIST.getMessage());
            }
            log.info("AddressServiceImpl.deleteAddress effect row :"+row);
        }catch (Exception e){
            log.error("AddressServiceImpl.deleteAddress occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }

    //地址只能有一个默认
    private void checkAddressDefaultUnique(boolean isDefault,Long userId){
        if(isDefault){
            Map paramMap=new HashMap();
            paramMap.put("userId",userId);
            List<Address> addresses=addressMapper.selectAddresss(paramMap);
            addresses.parallelStream().forEach(address->{
                if(address.getIsDefault()==1){
                    address.setIsDefault(1);
                    addressMapper.updateAddressByCondition(address);
                }
            });
        }
    }
}
