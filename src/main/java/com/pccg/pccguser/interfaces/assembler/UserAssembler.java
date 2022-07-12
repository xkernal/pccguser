package com.pccg.pccguser.interfaces.assembler;

import java.util.Date;

import com.pccg.pccguser.application.command.EditPasswordCmd;
import com.pccg.pccguser.application.command.EditUserCmd;
import com.pccg.pccguser.application.command.RegisterUserCmd;
import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.domain.entity.valueObject.Password;
import org.springframework.beans.BeanUtils;

public class UserAssembler {
    public static User to(RegisterUserCmd cmd) {
        User user = new User();
        BeanUtils.copyProperties(cmd, user);
        user.setPassword(Password.create(cmd.getPassword()));
        return user;
    }

    public static User to(EditUserCmd cmd) {
        User user = new User();
        BeanUtils.copyProperties(cmd, user);
        user.setModifyTime(new Date());
        return user;
    }

    public static User to(EditPasswordCmd cmd) {
        User user = new User();
        user.setUserId(cmd.getUserId());
        user.setPassword(Password.create(cmd.getNewPassword()));
        user.setModifyTime(new Date());
        return user;
    }
}
