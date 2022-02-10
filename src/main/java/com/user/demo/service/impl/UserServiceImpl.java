package com.user.demo.service.impl;

import com.user.demo.entity.UserEntity;
import com.user.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户服务实现类
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Boolean addUser(UserEntity userEntity) {
        if (userEntity == null) {
            log.error("添加用户失败,用户信息为空");
            return Boolean.FALSE;
        }
        userDao.insert(userEntity);
        return Boolean.TRUE;
    }

    @Override
    public UserEntity getUserByPhoneNumber(String phoneNumber) {
        if (StringUtils.isEmpty(phoneNumber)) {
            log.error("用户名为空");
            return null;
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setPhoneNumber(phoneNumber);
        return userDao.selectOne(userEntity);
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userDao.selectAll();
    }
}
