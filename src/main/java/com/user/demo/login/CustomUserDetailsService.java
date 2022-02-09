package com.user.demo.login;

import com.user.demo.entity.UserEntity;
import com.user.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Slf4j
public class CustomUserDetailsService implements UserDetailsService {

    /**
     * 用户服务
     */
    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {
        UserEntity userEntity = userService.getUserByPhoneNumber(phoneNumber);
        if (userEntity == null) {
            log.error("User {} not found.", phoneNumber);
            throw new UsernameNotFoundException("User not Found :" + phoneNumber);
        }
        return new CustomUserDetails(userEntity);
    }
}
