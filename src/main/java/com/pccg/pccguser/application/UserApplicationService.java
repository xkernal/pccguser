package com.pccg.pccguser.application;

import java.util.List;

import com.pccg.pccguser.application.vo.UserVO;
import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.domain.entity.valueObject.Password;
import com.pccg.pccguser.domain.service.UserDomainService;
import com.pccg.pccguser.interfaces.api.MultiResponse;
import com.pccg.pccguser.interfaces.api.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserApplicationService {

    @Autowired
    private UserDomainService userDomainService;

    @Transactional(rollbackFor = Exception.class)
    public void registerUser(User user) {
        userDomainService.add(user);
    }

    @Transactional(rollbackFor = Exception.class)
    public void disableUser(List<Long> userIds) {
        userDomainService.disableUser(userIds);
    }

    public MultiResponse<UserVO> selectByPage(UserQuery userQuery) {
        return userDomainService.selectByPage(userQuery);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateUserBasicInfo(User user) {
        userDomainService.updateUserBasic(user);
    }
    @Transactional(rollbackFor = Exception.class)
    public void updateUserPassword(User user, String oldPassword) {
        userDomainService.updateUserPassword(user, Password.create(oldPassword));
    }
}
