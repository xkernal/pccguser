package com.pccg.pccguser.infrastructure.persistence;

import java.util.Arrays;

import com.pccg.pccguser.PccguserApplication;
import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.domain.entity.valueObject.Password;
import com.pccg.pccguser.domain.entity.valueObject.UserStatus;
import com.pccg.pccguser.domain.reposity.facade.UserRepository;
import com.pccg.pccguser.interfaces.api.MultiResponse;
import com.pccg.pccguser.interfaces.api.UserQuery;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= PccguserApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void before() {

    }

    @Test
    @Transactional
    public void testInsert() {
        insert("harry1");
        User user = userRepository.selectByUserName("harry1");
        assertNotNull(user);
    }

    private void insert(String userName) {
        User user = new User();
        user.setUserName(userName);
        user.setPassword(Password.create("p1"));
        user.setEmail("harry@gmail.com");
        user.create();
        userRepository.insert(user);
    }

    @Test
    @Transactional
    public void testUpdate() {
        testInsert();
        User user = new User();
        user.setUserId(1);
        user.setEmail("xkernal@163.com");
        user.disable();
        userRepository.update(user);
        User newUser = userRepository.selectById(1);
        assertEquals(UserStatus.Disable, newUser.getStatus());
        assertEquals("xkernal@163.com", user.getEmail());
    }
    @Test
    @Transactional
    public void testBatchUpdate(){
        for(int i=0; i<=5; i++) {
            insert("harry"+i);
        }
        userRepository.batchUpdate(Arrays.asList(1L, 2L), 0);
    }

    @Test
    @Transactional
    public void testSelect() {
        testInsert();
        User user = userRepository.selectByUserName("u1");
        assertNotNull(user);
    }

    @Test
    @Transactional
    public void testSelectByPage() {
        for(int i=0; i<=5; i++) {
            insert("harry"+i);
        }
        UserQuery query = new UserQuery();
        query.setCurrentPage(1);
        query.setPageSize(2);
        MultiResponse<User> response = userRepository.selectByPage(query);
        assertEquals(6, response.getTotal());
        assertEquals(2, response.getData().size());
    }
}
