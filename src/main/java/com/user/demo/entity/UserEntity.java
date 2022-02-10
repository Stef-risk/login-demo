package com.user.demo.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "`user_registration`")
public class UserEntity {
    /**
     * 自增ID
     */
    @Id
    @Column(name = "`id`")
    @GeneratedValue(generator = "JDBC")
    private Long id;

    /**
     * 用户名
     */
    @Column(name = "`user_name`")
    private String userName;

    /**
     * 用户电话号码
     */
    @Column(name = "`phone_number`")
    private String phoneNumber;

    /**
     * 密码
     */
    @Column(name = "`password`")
    private String password;

    /**
     * 创建时间
     */
    @Column(name = "`created`")
    private Date created;

    /**
     * 修改时间
     */
    @Column(name = "`updated`")
    private Date updated;

    /**
     * 注册验证码

     */
    @Column(name = "`verification_code`")
    private String verificationCode;

    /**
     * 用户账户是否有效

     */
    @Column(name = "`enabled`")
    private Boolean enabled;

    /**
     * 获取自增ID
     *
     * @return id - 自增ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置自增ID
     *
     * @param id 自增ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取用户名
     *
     * @return user_name - 用户名
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置用户名
     *
     * @param userName 用户名
     */
    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    /**
     * 获取用户电话号码
     *
     * @return phone_number - 用户电话号码
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * 设置用户电话号码
     *
     * @param phoneNumber 用户电话号码
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber == null ? null : phoneNumber.trim();
    }

    /**
     * 获取密码
     *
     * @return password - 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置密码
     *
     * @param password 密码
     */
    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    /**
     * 获取创建时间
     *
     * @return created - 创建时间
     */
    public Date getCreated() {
        return created;
    }

    /**
     * 设置创建时间
     *
     * @param created 创建时间
     */
    public void setCreated(Date created) {
        this.created = created;
    }

    /**
     * 获取修改时间
     *
     * @return updated - 修改时间
     */
    public Date getUpdated() {
        return updated;
    }

    /**
     * 设置修改时间
     *
     * @param updated 修改时间
     */
    public void setUpdated(Date updated) {
        this.updated = updated;
    }

    /**
     * 获取注册验证码

     *
     * @return verification_code - 注册验证码

     */
    public String getVerificationCode() {
        return verificationCode;
    }

    /**
     * 设置注册验证码

     *
     * @param verificationCode 注册验证码

     */
    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode == null ? null : verificationCode.trim();
    }

    /**
     * 获取用户账户是否有效

     *
     * @return enabled - 用户账户是否有效

     */
    public Boolean getEnabled() {
        return enabled;
    }

    /**
     * 设置用户账户是否有效

     *
     * @param enabled 用户账户是否有效

     */
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}