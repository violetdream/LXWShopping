package com.personal.order.biz.mock;

import com.personal.user.datamodel.*;
import com.personal.user.service.IMemberService;

/**
 * @author GP17513-成都-Rigel
 * @description 会员信息mock类
 * @date 2019/8/9 14:12
 **/
public class MockMemberService implements IMemberService {
    @Override
    public QueryMemberResponse queryMemberById(QueryMemberRequest request) {
        return new QueryMemberResponse();
    }

    @Override
    public HeadImageResponse updateHeadImage(HeadImageRequest request) {
        return new HeadImageResponse();
    }

    @Override
    public UpdateMemberResponse updateMember(UpdateMemberRequest request) {
        return new UpdateMemberResponse();
    }
}
