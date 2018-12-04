package com.ball.wx.service.Impl;

import com.ball.wx.CommonValue;
import com.ball.wx.bean.LoginBean;
import com.ball.wx.mapper.GameMapper;
import com.ball.wx.service.GameService;
import org.springframework.stereotype.Service;

@Service
public class GameServiceImpl implements GameService {

    private static final GameMapper gameMapper = CommonValue.sqlSession.getMapper(GameMapper.class);

    @Override
    public void setTopScore(LoginBean loginBean) throws Exception {
        if (loginBean.getTopScore() > getUserScore(loginBean.getUserId()).getTopScore()) {
            gameMapper.setTopScore(loginBean);
        }
    }

    @Override
    public LoginBean getUserScore(Integer userId) throws Exception {
        return gameMapper.getUserScore(userId);
    }

}
