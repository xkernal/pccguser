package com.pccg.pccguser.infrastructure.persistence.entity;

import java.util.Date;

import lombok.Data;

@Data
public class UserDO {

    private long id;
    private String userName;
    private String password;
    private String email;
    private String mobile;
    private Date createTime;
    private Date modifyTime;
    private Integer status;
}
