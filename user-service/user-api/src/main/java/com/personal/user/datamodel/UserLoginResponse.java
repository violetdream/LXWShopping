package com.personal.user.datamodel;

import com.personal.ResultModule.AbstractResponse;
import lombok.Data;

/**
 * 2019/10/16/0016
 * Create by 刘仙伟
 */
@Data
public class UserLoginResponse extends AbstractResponse {
    private Long id;
    private String username;
    private String phone;
    private String email;
    private String sex;
    private String address;
    private String file;
    private String description;
    private Integer points;
    private Long balance;
    private int state;
    private String token;
}
