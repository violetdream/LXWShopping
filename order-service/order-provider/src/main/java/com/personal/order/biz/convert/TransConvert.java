
package com.personal.order.biz.convert;

import com.personal.ResultModule.AbstractRequest;
import com.personal.ResultModule.AbstractResponse;
import com.personal.order.biz.context.TransHandlerContext;

public interface TransConvert {
    /**
     * 请求转上下文
     * 
     * @param req
     * @return
     */
    TransHandlerContext convertRequest2Ctx(AbstractRequest req, TransHandlerContext context);
    
    /**
     * 上下文转应答
     * 
     * @param ctx
     * @return
     */
    AbstractResponse convertCtx2Respond(TransHandlerContext ctx);
}
