package com.personal.user.datamodel;/**
 * Created by mic on 2019/7/30.
 */
import com.personal.ResultModule.AbstractRequest;
import com.personal.tool.exception.ValidateException;
import com.personal.user.constants.SysRetCodeConstants;
import lombok.Data;

/**
 * 腾讯课堂搜索【咕泡学院】
 * 官网：www.gupaoedu.com
 * 风骚的Mic 老师
 * create-date: 2019/7/30-下午11:49
 */
@Data
public class QueryMemberRequest extends AbstractRequest {

    private Long userId;

    @Override
    public void requestCheck() {
        if(userId==null){
            throw new ValidateException(
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getCode(),
                    SysRetCodeConstants.REQUEST_CHECK_FAILURE.getMessage());
        }
    }
}
