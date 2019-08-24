package com.ball.wx.controller;

import com.ball.wx.service.RunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RunController {

    @Autowired
    private RunService runService;

    /**
     * 根据前端发送的数据解析s
     * @param data
     * @return
     * @throws Exception
     */
    @PostMapping(value = "/getRunData")
    public Object getRunData(@RequestBody Object data) throws Exception {
        return runService.getRunData(data);
    }

}
