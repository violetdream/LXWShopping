package com.personal.user.datamodel.persistence;

import com.personal.user.datamodel.entitys.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper{

    public User selectUserByName(String userName);

    public int insertUser(User user);
}