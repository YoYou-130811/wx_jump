package com.ball.wx.mapper;

import com.ball.wx.bean.LoginBean;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {

    public Integer insertUser(LoginBean loginBean) throws Exception;

    public LoginBean selectUserByOpenId(@Param(value = "openid") String openid) throws Exception;

    public LoginBean selectUserByUserId(@Param(value = "userid") Integer userId) throws Exception;

    public void updateUser(LoginBean loginBean) throws Exception;

    public void deleteAllUsers() throws Exception;

}