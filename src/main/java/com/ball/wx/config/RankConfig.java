package com.ball.wx.config;

import com.ball.wx.service.RankService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Slf4j
@Configuration
public class RankConfig {

    @Autowired
    RankService rankService;

    @Scheduled(fixedDelay = 1000 * 60 * 60 * 24)
    public void refresh() throws Exception {
        log.info("更新排行榜");
        rankService.deleteData();
        rankService.insertData();
    }

}
