package com.pccg.pccguser.application;

import com.pccg.pccguser.PccguserApplication;
import com.pccg.pccguser.domain.entity.User;
import com.pccg.pccguser.domain.entity.valueObject.Password;
import com.pccg.pccguser.domain.reposity.facade.UserRepository;
import com.pccg.pccguser.infrastructure.common.exception.PcgException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= PccguserApplication.class)
public class UserApplicationServiceTest {

    @Autowired
    private UserApplicationService userApplicationService;
    @Autowired
    private UserRepository userRepository;

    @Test
    @Transactional
    public void createTest() {
        User user = new User();
        user.setUserName("harry1");
        user.setPassword(Password.create("p1"));
        user.setEmail("harry@gmail.com");
        userApplicationService.registerUser(user);

        User query = userRepository.selectByUserName("harry1");
        assertNotNull(query);
    }

    @Test
    @Transactional
    public void createRepeatTest() {
        User user = new User();
        user.setUserName("harry1");
        user.setPassword(Password.create("p1"));
        user.setEmail("harry@gmail.com");
        userApplicationService.registerUser(user);
        assertThrows(PcgException.class, ()->userApplicationService.registerUser(user));
    }

    @Test
    @Transactional
    public void updateUserPasswordTest() {
        User user = new User();
        user.setUserName("harry1");
        user.setPassword(Password.create("p1"));
        user.setEmail("harry@gmail.com");
        userApplicationService.registerUser(user);

        user.setUserId(1);
        user.setPassword(Password.create("p2"));
        userApplicationService.updateUserPassword(user, "p1");

        User dbUser = userRepository.selectByUserName("harry1");
        assertEquals(Password.create("p2").getPassword(), dbUser.getPassword().getPassword());
    }
}
