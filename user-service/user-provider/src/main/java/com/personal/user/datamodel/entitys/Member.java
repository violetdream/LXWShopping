package com.personal.user.datamodel.entitys;

import lombok.Data;
import lombok.ToString;
import java.util.Date;


@Data
@ToString
public class Member {

    private Long id;

    /**
     * 用户名
     */
    private String username;

    /**
     * 密码，加密存储
     */
    private String password;

    /**
     * 注册手机号
     */
    private String phone;

    /**
     * 注册邮箱
     */
    private String email;

    private Date created;

    private Date updated;

    private String sex;

    private String address;

    private Integer state;

    /**
     * 头像
     */
    private String file;

    private String description;

    /**
     * 积分
     */
    private Integer points;

    /**
     * 余额
     */
    private Double balance;


}