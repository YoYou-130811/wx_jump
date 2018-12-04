package com.ball.wx.service;

import com.ball.wx.bean.LoginBean;

public interface LoginService {

    public LoginBean getOpenId$key(String code) throws Exception;

    public LoginBean setUserInfo(LoginBean loginBean) throws Exception;

    public LoginBean getUserData(Integer userId) throws Exception;

    public void deleteAllUsers() throws Exception;

}
