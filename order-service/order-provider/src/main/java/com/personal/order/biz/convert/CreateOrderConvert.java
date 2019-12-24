package com.personal.order.biz.convert;

import com.personal.ResultModule.AbstractRequest;
import com.personal.ResultModule.AbstractResponse;
import com.personal.order.biz.context.CreateOrderContext;
import com.personal.order.biz.context.TransHandlerContext;
import com.personal.order.constant.OrderRetCode;
import com.personal.order.dto.CreateOrderRequest;
import com.personal.order.dto.CreateOrderResponse;

public class CreateOrderConvert implements TransConvert{


    public TransHandlerContext convertRequest2Ctx(AbstractRequest req, TransHandlerContext context) {
        CreateOrderRequest createOrderRequest=(CreateOrderRequest)req;
        CreateOrderContext createOrderContext=(CreateOrderContext) context;
        createOrderContext.setAddressId(createOrderRequest.getAddressId());
        createOrderContext.setCartProductDtoList(createOrderRequest.getCartProductDtoList());
        createOrderContext.setOrderTotal(createOrderRequest.getOrderTotal());
        createOrderContext.setStreetName(createOrderRequest.getStreetName());
        createOrderContext.setTel(createOrderRequest.getTel());
        createOrderContext.setUserId(createOrderRequest.getUserId());
        createOrderContext.setUserName(createOrderRequest.getUserName());
        createOrderContext.setUniqueKey(createOrderRequest.getUniqueKey());
        return createOrderContext;
    }


    public AbstractResponse convertCtx2Respond(TransHandlerContext ctx) {
        CreateOrderContext createOrderContext=(CreateOrderContext) ctx;
        CreateOrderResponse createOrderResponse=new CreateOrderResponse();
        createOrderResponse.setOrderId(createOrderContext.getOrderId());
        createOrderResponse.setCode(OrderRetCode.SUCCESS.getCode());
        createOrderResponse.setMsg(OrderRetCode.SUCCESS.getMessage());
        return createOrderResponse;
    }
}
