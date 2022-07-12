package com.pccg.pccguser.domain.entity;

import java.util.Date;

import com.pccg.pccguser.domain.entity.valueObject.Password;
import com.pccg.pccguser.domain.entity.valueObject.UserStatus;
import lombok.Data;

@Data
public class User {
    private long userId;
    private String userName;
    private Password password;
    private String email;
    private String mobile;
    private Date createTime;
    private Date modifyTime;
    private UserStatus status;

    public User create() {
        this.createTime = new Date();
        this.modifyTime = new Date();
        this.status = UserStatus.Enable;
        return this;
    }

    public User enable() {
        this.modifyTime = new Date();
        this.status = UserStatus.Enable;
        return this;
    }

    public User disable() {
        this.modifyTime = new Date();
        this.status = UserStatus.Disable;
        return this;
    }

    public boolean updateCheck(Password oldPassword) {
        return oldPassword.isSame(this.password);
    }
}
