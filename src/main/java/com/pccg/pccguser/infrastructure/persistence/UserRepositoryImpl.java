package com.pccg.pccguser.infrastructure.persistence;

import java.util.List;
import java.util.stream.Collectors;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.domain.reposity.facade.UserRepository;
import com.pccg.pccguser.domain.service.UserFactory;
import com.pccg.pccguser.infrastructure.persistence.assembler.UserAssembler;
import com.pccg.pccguser.infrastructure.persistence.entity.UserDO;
import com.pccg.pccguser.infrastructure.persistence.mapper.UserMapper;
import com.pccg.pccguser.interfaces.api.MultiResponse;
import com.pccg.pccguser.interfaces.api.UserQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserMapper userMapper;

    @Override
    public long insert(User user) {
        UserDO userDO = UserAssembler.to(user);
        userMapper.insert(userDO);
        return userDO.getId();
    }

    @Override
    public void update(User user) {
        UserDO userDO = UserAssembler.to(user);
        userMapper.updateByPK(userDO);
    }

    public void batchUpdate(List<Long> ids, int status) {
        userMapper.batchUpdateByPK(ids, status);
    }

    @Override
    public User selectByUserName(String userName) {
        UserDO userDO = userMapper.selectByUserName(userName);
        if(userDO!=null){
           return UserFactory.makeUser(userDO);
        }
        return null;
    }

    @Override
    public User selectById(long id) {
        UserDO userDO = userMapper.selectById(id);
        if(userDO!=null) {
            return UserFactory.makeUser(userDO);
        }
        return null;
    }

    @Override
    public MultiResponse<User> selectByPage(UserQuery query) {
        PageHelper.startPage(query.getCurrentPage(), query.getPageSize());
        List<UserDO> userDOS = userMapper.selectByPage(query);
        PageInfo<UserDO> pageInfo = new PageInfo<UserDO>(userDOS);
        List<User> users = userDOS.stream().map(item->UserFactory.makeUser(item)).collect(Collectors.toList());
        return MultiResponse.of(users, pageInfo.getTotal());
    }
}
