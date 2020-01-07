package com.personal.shopping.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.personal.ResultModule.ResponseData;
import com.personal.ResultModule.ResponseUtil;
import com.personal.order.OrderCoreService;
import com.personal.order.OrderQueryService;
import com.personal.order.constant.OrderRetCode;
import com.personal.order.dto.*;
import com.personal.shopping.form.OrderDetail;
import com.personal.shopping.form.PageInfo;
import com.personal.shopping.form.PageResponse;
import com.personal.user.Interceptor.TokenIntercepter;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@RestController
@RequestMapping("/shopping")
public class OrderController {

    @Reference(timeout = 3000)
    private OrderCoreService orderCoreService;

    @Reference(timeout = 3000)
    private OrderQueryService orderQueryService;

    /**
     * 创建订单
     */
    @PostMapping("/order")
    public ResponseData order(@RequestBody CreateOrderRequest request, HttpServletRequest servletRequest){
        String userInfo=(String)servletRequest.getAttribute(TokenIntercepter.USER_INFO_KEY);
        JSONObject object= JSON.parseObject(userInfo);
        Long uid=Long.parseLong(object.get("uid").toString());
        request.setUserId(uid);
        request.setUniqueKey(UUID.randomUUID().toString());
        CreateOrderResponse response=orderCoreService.createOrder(request);
        if(response.getCode().equals(OrderRetCode.SUCCESS.getCode())){
            return new ResponseUtil<>().setData(response.getOrderId());
        }
        return new ResponseUtil<>().setErrorMsg(response.getMsg());
    }

    /**
     * 获取当前用户的所有订单
     * @return
     */
    @GetMapping("/order")
    public ResponseData orderByCurrentId(PageInfo pageInfo, HttpServletRequest servletRequest){
        OrderListRequest request = new OrderListRequest();
        request.setPage(pageInfo.getPage());
        request.setSize(pageInfo.getSize());
        request.setSort(pageInfo.getSort());
        String userInfo=(String)servletRequest.getAttribute(TokenIntercepter.USER_INFO_KEY);
        JSONObject object= JSON.parseObject(userInfo);
        Long uid=Long.parseLong(object.get("uid").toString());
        request.setUserId(uid);
        OrderListResponse listResponse = orderQueryService.orderList(request);
        if(listResponse.getCode().equals(OrderRetCode.SUCCESS.getCode())){
            PageResponse response = new PageResponse();
            response.setData(listResponse.getDetailInfoList());
            response.setTotal(listResponse.getTotal());
            return new ResponseUtil<>().setData(response);
        }
        return new ResponseUtil<>().setErrorMsg(listResponse.getMsg());
    }

    /**
     * 查询订单详情
     * @return
     */
    @GetMapping("/order/{id}")
    public ResponseData orderDetail(@PathVariable String id){
        OrderDetailRequest request=new OrderDetailRequest();
        request.setOrderId(id);
        OrderDetailResponse response=orderQueryService.orderDetail(request);
        if(response.getCode().equals(OrderRetCode.SUCCESS.getCode())){
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setOrderTotal(response.getPayment());
            orderDetail.setUserId(response.getUserId());
            orderDetail.setUserName(response.getBuyerNick());
            orderDetail.setGoodsList(response.getOrderItemDto());
            orderDetail.setTel(response.getOrderShippingDto().getReceiverPhone());
            orderDetail.setStreetName(response.getOrderShippingDto().getReceiverAddress());
            return new ResponseUtil<>().setData(orderDetail);
        }
        return new ResponseUtil<>().setErrorMsg(null);
    }

    /**
     * 取消订单
     * @return
     */
    @PutMapping("/order/{id}")
    public ResponseData orderCancel(@PathVariable String id){
        CancelOrderRequest request =new CancelOrderRequest ();
        request.setOrderId(id);
        return new ResponseUtil<>().setData(orderCoreService.cancelOrder(request));
    }

    /**
     * 删除订单
     * @param id
     * @return
     */
    @DeleteMapping("/order/{id}")
    public ResponseData orderDel(@PathVariable String id){
        DeleteOrderRequest deleteOrderRequest=new DeleteOrderRequest();
        deleteOrderRequest.setOrderId(id);
        return new ResponseUtil<>().setData(orderCoreService.deleteOrder(deleteOrderRequest));
    }

}
