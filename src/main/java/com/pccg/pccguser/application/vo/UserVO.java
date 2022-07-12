package com.pccg.pccguser.application.vo;

import java.util.Date;

import com.pccg.pccguser.domain.entity.valueObject.UserStatus;
import lombok.Data;

@Data
public class UserVO {
    private long userId;
    private String userName;
    private String email;
    private String mobile;
    private Date registerTime;
    private UserStatus status;
}
