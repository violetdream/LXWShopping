package com.personal.user.datamodel.persistence;


import com.personal.user.datamodel.entitys.Member;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper{
    Member selectMemberByUserId(Long userId);

    int updateMemeberByCondition(Member member);
}