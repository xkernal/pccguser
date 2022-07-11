package com.pccg.pccguser.infrastructure.persistence.assembler;

import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.infrastructure.persistence.entity.UserDO;
import org.springframework.beans.BeanUtils;

public class UserAssembler {

    public static UserDO to(User user){
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(user, userDO);
        userDO.setId(user.getUserId());
        if(user.getStatus()!=null) {
            userDO.setStatus(user.getStatus().getCode());
        }
        if(user.getPassword()!=null) {
            userDO.setPassword(user.getPassword().getPassword());
        }
        return userDO;
    }
}
