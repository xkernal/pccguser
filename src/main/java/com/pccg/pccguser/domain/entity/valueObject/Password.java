package com.pccg.pccguser.domain.entity.valueObject;

import org.springframework.util.DigestUtils;

public class Password {

    String password;

    public Password(String password) {
        this.password = password;
    }

    public static Password create(String source) {
         return new Password(DigestUtils.md5DigestAsHex(source.getBytes()));
    }

    public boolean isSame(Password password){
        return this.password.equals(password.getPassword());
    }

    public String getPassword() {
        return password;
    }
}
