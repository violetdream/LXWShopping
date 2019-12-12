package com.personal.user.services;

import com.personal.user.constants.SysRetCodeConstants;
import com.personal.user.converter.MemberConverter;
import com.personal.user.datamodel.*;
import com.personal.user.datamodel.entitys.Member;
import com.personal.user.datamodel.persistence.MemberMapper;
import com.personal.user.service.IMemberService;
import com.personal.user.service.IUserLoginService;
import com.personal.user.utils.ExceptionProcessorUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Slf4j
@Service
public class MemberServiceImpl implements IMemberService {

    @Autowired
    MemberMapper memberMapper;

    @Autowired
    IUserLoginService userLoginService;

    @Autowired
    MemberConverter memberConverter;

    /**
     * 根据用户id查询用户会员信息
     * @param request
     * @return
     */
    @Override
    public QueryMemberResponse queryMemberById(QueryMemberRequest request) {
        QueryMemberResponse queryMemberResponse=new QueryMemberResponse();
        try{
            request.requestCheck();
            Member member=memberMapper.selectMemberByUserId(request.getUserId());
            if(member==null){
                queryMemberResponse.setCode(SysRetCodeConstants.DATA_NOT_EXIST.getCode());
                queryMemberResponse.setMsg(SysRetCodeConstants.DATA_NOT_EXIST.getMessage());
            }
            queryMemberResponse=memberConverter.member2Res(member);
            queryMemberResponse.setCode(SysRetCodeConstants.SUCCESS.getCode());
            queryMemberResponse.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
        }catch (Exception e){
            log.error("MemberServiceImpl.queryMemberById Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(queryMemberResponse,e);
        }
        return queryMemberResponse;
    }

    @Override
    public HeadImageResponse updateHeadImage(HeadImageRequest request) {
        HeadImageResponse response=new HeadImageResponse();
        //TODO
        return response;
    }

    @Override
    public UpdateMemberResponse updateMember(UpdateMemberRequest request) {
        UpdateMemberResponse response = new UpdateMemberResponse();
        try{
            request.requestCheck();
            CheckAuthRequest checkAuthRequest = new CheckAuthRequest();
            checkAuthRequest.setToken(request.getToken());
            CheckAuthResponse authResponse = userLoginService.validToken(checkAuthRequest);
            if (!authResponse.getCode().equals(SysRetCodeConstants.SUCCESS.getCode())) {
                response.setCode(authResponse.getCode());
                response.setMsg(authResponse.getMsg());
                return response;
            }
            Member member = memberConverter.updateReq2Member(request);
            int row = memberMapper.updateMemeberByCondition(member);
            response.setMsg(SysRetCodeConstants.SUCCESS.getMessage());
            response.setCode(SysRetCodeConstants.SUCCESS.getCode());
            log.info("MemberServiceImpl.updateMember effect row :"+row);
        }catch (Exception e){
            log.error("MemberServiceImpl.updateMember Occur Exception :"+e);
            ExceptionProcessorUtils.wrapperHandlerException(response,e);
        }
        return response;
    }
}
