package com.user.demo.service.impl;

import com.user.demo.BaseTest;
import com.user.demo.entity.UserEntity;
import com.user.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertTrue;

class UserServiceImplTest extends BaseTest {

    @Autowired
    private UserService userService;

    @Test
    void addUser() throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("Stefan");
        userEntity.setPassword("elderflower7");
        userEntity.setPhoneNumber("999");
        userEntity.setCreated(new Date());
        assertTrue(userService.addUser(userEntity, null));
    }

}