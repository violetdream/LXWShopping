package com.personal.order.biz.callback;

import com.personal.order.biz.context.TransHandlerContext;

public interface TransCallback {

    void onDone(TransHandlerContext context);
}
