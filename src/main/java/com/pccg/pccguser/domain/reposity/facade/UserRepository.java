package com.pccg.pccguser.domain.reposity.facade;

import java.util.List;

import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.interfaces.api.MultiResponse;
import com.pccg.pccguser.interfaces.api.UserQuery;

public interface UserRepository {

    long insert(User user);

    void update(User user);

    void batchUpdate(List<Long> ids, int status);

    User selectByUserName(String userName);

    User selectById(long id);

    MultiResponse<User> selectByPage(UserQuery query);
}
