package com.personal.order.biz.handler;

import com.personal.order.biz.context.TransHandlerContext;

public interface TransHandler {


    /**
     * 是否采用异步方式执行
     * @return
     */
    boolean isAsync();

    /**
     * 执行交易具体业务<br/>
     *
     * @param context
     * @return true则继续执行下一个Handler，否则结束Handler Chain的执行直接返回<br/>
     */
    boolean handle(TransHandlerContext context);


}
