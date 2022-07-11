package com.pccg.pccguser.domain.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.pccg.pccguser.application.vo.UserVO;
import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.domain.entity.valueObject.Password;
import com.pccg.pccguser.domain.entity.valueObject.UserStatus;
import com.pccg.pccguser.domain.event.UserCreateEvent;
import com.pccg.pccguser.domain.reposity.facade.UserRepository;
import com.pccg.pccguser.infrastructure.common.ErrorCode;
import com.pccg.pccguser.infrastructure.common.event.EventPublisher;
import com.pccg.pccguser.infrastructure.common.exception.PcgException;
import com.pccg.pccguser.interfaces.api.MultiResponse;
import com.pccg.pccguser.interfaces.api.UserQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserDomainService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventPublisher publisher;

    public void add(User user) {
        User existsUser = userRepository.selectByUserName(user.getUserName());
        if(existsUser!=null) {
            throw new PcgException("repeat userName", ErrorCode.IllegalArgument.getCode());
        }
        user.create();
        long userId = userRepository.insert(user);
        publisher.publish(UserCreateEvent.make(user.getUserName(), userId));
    }

    public void disableUser(List<Long> userIds) {
        if(userIds.size()==1) {
            long userId = userIds.get(0);
            User existsUser = userRepository.selectById(userId);
            if (existsUser == null) {
                throw new PcgException("user not exists", ErrorCode.IllegalArgument.getCode());
            }
            User user = new User();
            user.setUserId(userId);
            user.disable();
            userRepository.update(user);
        } else {
            userRepository.batchUpdate(userIds, UserStatus.Disable.getCode());
        }
    }

    public void updateUserBasic(User user) {
        User existsUser = userRepository.selectById(user.getUserId());
        if(existsUser==null) {
            throw new PcgException("user not exists", ErrorCode.IllegalArgument.getCode());
        }
        user.setModifyTime(new Date());
        userRepository.update(user);
    }

    public void updateUserPassword(User user, Password oldPassword) {
        User existsUser = userRepository.selectById(user.getUserId());
        if(existsUser==null) {
            throw new PcgException("user not exists", ErrorCode.IllegalArgument.getCode());
        }
        if(!existsUser.updateCheck(oldPassword)) {
            throw new PcgException("原密码错误", ErrorCode.IllegalArgument.getCode());
        }
        user.setModifyTime(new Date());
        userRepository.update(user);
    }

    public MultiResponse<UserVO> selectByPage(UserQuery userQuery) {
        MultiResponse<User> response = userRepository.selectByPage(userQuery);
        List<UserVO> userVOS = response.getData().stream().map(item->UserFactory.makeVO(item)).collect(Collectors.toList());
        return MultiResponse.of(userVOS, response.getTotal());
    }
}
