package com.personal.user.datamodel.entitys;

import lombok.Data;
import lombok.ToString;


@Data
@ToString
public class Address {

    private Long addressId;

    private Long userId;

    private String userName;

    private String tel;

    private String streetName;

    private Integer isDefault;
}