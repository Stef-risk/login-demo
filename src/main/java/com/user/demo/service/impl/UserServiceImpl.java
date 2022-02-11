package com.user.demo.service.impl;

import ch.qos.logback.core.spi.LogbackLock;
import com.alibaba.fastjson.JSON;
import com.user.demo.dao.UserDao;
import com.user.demo.entity.UserEntity;
import com.user.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

/**
 * 用户服务实现类
 */
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {

    /**
     * 用户Dao
     */
    @Autowired
    private UserDao userDao;

    /**
     * 发送邮件服务
     */
    @Resource
    private JavaMailSender mailSender;

    /**
     * 邮件发送者
     */
    @Value("${spring.mail.username}")
    private String emailAddress;

    /**
     * TransactionManager
     */
    @Autowired
    private DataSourceTransactionManager dataSourceTransactionManager;

    @Autowired
    private TransactionDefinition transactionDefinition;

    @Autowired
    private TransactionTemplate transactionTemplate;

    @Transactional
    @Override
    public Boolean addUser(UserEntity userEntity, String siteUrl) throws Exception {
        log.info("开始新增用户 User:{}", JSON.toJSONString(userEntity));
        if (userEntity == null) {
            log.error("添加用户失败,用户信息为空");
            throw new Exception("user info empty");
        }
        if (isAccountAlreadyExist(userEntity)) {
            log.error("该用户已经存在 User:{}", JSON.toJSONString(userEntity));
            throw new Exception("user already exist");
        }
        //添加更新时间
        Date date = new Date();
        userEntity.setCreated(date);
        userEntity.setUpdated(date);
        //设置验证码和账户有效性
        String verificationCode = RandomStringUtils.random(64, true, false);
        userEntity.setVerificationCode(verificationCode);
        userEntity.setEnabled(Boolean.FALSE);
        //进行插入
        userDao.insert(userEntity);
        //发送验证码
        sendVerificationEmail(userEntity, siteUrl);
        return Boolean.TRUE;
    }

    @Override
    public Boolean verifyUser(String verificationCode) {
        log.info("开始验证新注册用户账号 verificationCode:{}", verificationCode);
        UserEntity userEntity = this.getUserByVerificationCode(verificationCode);
        if (userEntity == null || userEntity.getEnabled()) {
            return Boolean.FALSE;
        }
        log.info("根据验证码获取到用户 User:{}", JSON.toJSONString(userEntity));
        // 更新激活状态且将验证码置为空
        userEntity.setEnabled(Boolean.TRUE);
        userEntity.setVerificationCode(null);
        userDao.updateByPrimaryKey(userEntity);
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

    @Override
    public UserEntity getUserByVerificationCode(String verificationCode) {
        UserEntity userEntity = new UserEntity();
        userEntity.setVerificationCode(verificationCode);
        return userDao.selectOne(userEntity);
    }

    /**
     * 判断该账号是否已经注册过
     *
     * @param userEntity
     * @return
     */
    private Boolean isAccountAlreadyExist(UserEntity userEntity) {
        UserEntity example = new UserEntity();
        userEntity.setPhoneNumber(userEntity.getPhoneNumber());
        return userDao.selectOne(example) != null;
    }

    /**
     * 发送验证码
     *
     * @param userEntity
     * @param siteUrl
     */
    private void sendVerificationEmail(UserEntity userEntity, String siteUrl) throws MessagingException, UnsupportedEncodingException {
        String toAddress = userEntity.getPhoneNumber();
        String senderName = "A Site Test For Login and Email";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Your company name.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        // 设置发送者、接收者以及主题
        helper.setFrom(emailAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);
        // 设置内容
        content = content.replace("[[name]]", userEntity.getUserName());
        String verifyURL = siteUrl + "/verify?code=" + userEntity.getVerificationCode();
        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);
        // 发送
        mailSender.send(message);
    }

    private void transactionTest() {
        TransactionStatus transactionStatus= dataSourceTransactionManager.getTransaction(transactionDefinition);
        //do something
        dataSourceTransactionManager.commit(transactionStatus);
    }
}
