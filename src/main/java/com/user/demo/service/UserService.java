package com.user.demo.service;

import com.user.demo.entity.UserEntity;

import java.util.List;

/**
 * 用户服务
 * @author Stefan
 * @date 2022/2/9
 */
public interface UserService {

    /**
     * 添加新用户
     * @param userEntity
     * @return
     */
    Boolean addUser(UserEntity userEntity);

    /**
     * 根据用户名获取用户
     * @param phoneNumber
     * @return
     */
    UserEntity getUserByPhoneNumber(String phoneNumber);

    /**
     * 获取所有用户列表
     * @return
     */
    List<UserEntity> getAllUsers();
}
