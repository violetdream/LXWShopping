package com.personal.order.biz.factory;

import com.personal.order.biz.TransOutboundInvoker;

public interface TransPipelineFactory<T> {

    TransOutboundInvoker build(T obj);
}
