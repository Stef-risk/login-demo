package com.user.demo.service;

import com.user.demo.entity.UserEntity;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * 用户服务
 * @author Stefan
 * @date 2022/2/9
 */
public interface UserService {

    /**
     * 添加新用户
     *
     * @param userEntity
     * @param siteUrl
     * @return
     */
    Boolean addUser(UserEntity userEntity, String siteUrl) throws Exception;

    /**
     * 验证用户
     *
     * @param verificationCode
     * @return
     */
    Boolean verifyUser(String verificationCode);

    /**
     * 根据用户名获取用户
     *
     * @param phoneNumber
     * @return
     */
    UserEntity getUserByPhoneNumber(String phoneNumber);

    /**
     * 获取所有用户列表
     *
     * @return
     */
    List<UserEntity> getAllUsers();

    /**
     * 根据验证码获取用户
     *
     * @param verificationCode
     * @return
     */
    UserEntity getUserByVerificationCode(String verificationCode);
}
