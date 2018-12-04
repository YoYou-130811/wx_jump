package com.ball.wx.controller;

import com.ball.wx.bean.LoginBean;
import com.ball.wx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    @PostMapping(value = "/getOpenId$key")
    public LoginBean getOpenId$key(@RequestBody String code) throws Exception {
        return loginService.getOpenId$key(code);
    }

    @PostMapping(value = "/setUserInfo")
    public LoginBean setUserInfo(@RequestBody LoginBean loginBean) throws Exception {
        return loginService.setUserInfo(loginBean);
    }

    @GetMapping(value = "/getUserData/{userId}")
    public LoginBean getUserData(@PathVariable Integer userId) throws Exception {
        return loginService.getUserData(userId);
    }

    @DeleteMapping(value = "/deleteAllUsers")
    public void deleteAllUsers() throws Exception {
        loginService.deleteAllUsers();
    }

}
