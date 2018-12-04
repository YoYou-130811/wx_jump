package com.ball.wx.controller;

import com.ball.wx.bean.LoginBean;
import com.ball.wx.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class LoginController {

    @Autowired
    LoginService loginService;

    /**
     * 根据前端发送的code获取openid和session_key
     * @param code
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/getOpenId$key")
    public LoginBean getOpenId$key(@RequestBody String code) throws Exception {
        return loginService.getOpenId$key(code);
    }

    /**
     * 判断信息是否完整，完整则添加用户信息
     * @param loginBean
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/setUserInfo")
    public LoginBean setUserInfo(@RequestBody LoginBean loginBean) throws Exception {
        return loginService.setUserInfo(loginBean);
    }

    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getUserData/{userId}")
    public LoginBean getUserData(@PathVariable Integer userId) throws Exception {
        return loginService.getUserData(userId);
    }

    /**
     * 删除所有用户信息（该接口不能对前端开放）
     * @throws Exception
     */
    @DeleteMapping(value = "/deleteAllUsers")
    public void deleteAllUsers() throws Exception {
        loginService.deleteAllUsers();
    }

}
