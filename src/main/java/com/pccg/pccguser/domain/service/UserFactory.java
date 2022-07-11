package com.pccg.pccguser.domain.service;

import com.pccg.pccguser.application.vo.UserVO;
import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.domain.entity.valueObject.Password;
import com.pccg.pccguser.domain.entity.valueObject.UserStatus;
import com.pccg.pccguser.infrastructure.persistence.entity.UserDO;
import org.springframework.beans.BeanUtils;

public class UserFactory {

    public static User makeUser(UserDO userDO) {
        User user = new User();
        BeanUtils.copyProperties(userDO, user);
        user.setPassword(new Password(userDO.getPassword()));
        user.setStatus(UserStatus.findByCode(userDO.getStatus()));
        user.setUserId(userDO.getId());
        return user;
    }

    public static UserVO makeVO(User user) {
        UserVO vo = new UserVO();
        vo.setUserName(user.getUserName());
        vo.setUserId(user.getUserId());
        vo.setEmail(user.getEmail());
        vo.setMobile(user.getMobile());
        vo.setRegisterTime(user.getCreateTime());
        vo.setStatus(user.getStatus());
        return vo;
    }
}
