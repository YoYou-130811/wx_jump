package com.ball.wx.mapper;

import com.ball.wx.bean.LoginBean;
import org.apache.ibatis.annotations.Param;

public interface GameMapper {

    public void setTopScore(LoginBean loginBean) throws Exception;

    public LoginBean getUserScore(@Param(value = "userid") Integer userId) throws Exception;

}
