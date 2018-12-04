package com.ball.wx.controller;

import com.ball.wx.bean.RankBean;
import com.ball.wx.service.RankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class RankController {

    @Autowired
    RankService rankService;

    @GetMapping(value = "/getRankList")
    public List<RankBean> selectData() throws Exception {
        return rankService.selectData();
    }

}
