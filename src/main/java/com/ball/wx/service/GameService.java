package com.ball.wx.service;

import com.ball.wx.bean.LoginBean;

public interface GameService {

    public void setTopScore(LoginBean loginBean) throws Exception;

    public LoginBean getUserScore(Integer userId) throws Exception;

}
