package com.ball.wx.controller;

import com.ball.wx.bean.LoginBean;
import com.ball.wx.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    GameService gameService;

    /**
     * 更新玩家最高分
     * @param loginBean
     * @throws Exception
     */
    @PutMapping(value = "/setTopScore")
    public void setTopScore(@RequestBody LoginBean loginBean) throws Exception {
        gameService.setTopScore(loginBean);
    }

    /**
     * 根据用户ID获取用户最高分（该接口不能对前端开放）
     * @param userId
     * @return
     * @throws Exception
     */
    @GetMapping(value = "/getUserScore/{userId}")
    public LoginBean getUserScore(@PathVariable Integer userId) throws Exception {
        return gameService.getUserScore(userId);
    }

}
