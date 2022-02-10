package com.user.demo.controller;

import com.alibaba.fastjson.JSON;
import com.user.demo.entity.UserEntity;
import com.user.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 应用Controller
 *
 * @author Stefan
 * @date 2022/2/9
 */
@Slf4j
@Controller
@RequestMapping("/")
public class AppController {

    /**
     * 用户服务
     */
    @Autowired
    private UserService userService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping("/index")
    public String homePage() {
        return "index";
    }

    /**
     * 注册界面
     *
     * @param model
     * @return
     */
    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new UserEntity());
        return "sign_up_form";
    }

    /**
     * 处理注册请求
     *
     * @return
     */
    @RequestMapping("/process_register")
    public String processRegister(UserEntity userEntity, HttpServletRequest request) throws Exception {
        log.info("开始进行用户注册 User:{}", JSON.toJSONString(userEntity));
        //1.判空
        if (userEntity == null || userEntity.getPhoneNumber() == null || userEntity.getPassword() == null) {
            log.error("注册信息不全，拒绝注册 User:{}", JSON.toJSONString(userEntity));
            return null;
        }
        //2.对密码进行加密
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(userEntity.getPassword());
        userEntity.setPassword(encodedPassword);
        //3.保存用户信息
        userService.addUser(userEntity, getSiteUrl(request));
        return "register_success";
    }

    /**
     * 获取用户列表
     *
     * @param model
     * @return
     */
    @RequestMapping("/users")
    public String getListOfUsers(Model model, HttpServletRequest request) {
        List<UserEntity> userEntities = userService.getAllUsers();
        model.addAttribute("users", userEntities);
        String userName = request.getUserPrincipal().getName();
        log.info("{}", userName);
        return "users";
    }

    @RequestMapping("/verify")
    public String verifyUser(@Param("code") String code) {
        if (userService.verifyUser(code)) {
            return "verify_success";
        } else {
            return "verify_fail";
        }
    }

    /**
     * 从请求获取站点Url
     *
     * @param request
     * @return
     */
    private String getSiteUrl(HttpServletRequest request) {
        String siteUrl = request.getRequestURL().toString();
        log.info("siteUrl:{}", siteUrl);
        return siteUrl.replace(request.getServletPath(), "");
    }
}
