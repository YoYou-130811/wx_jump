package com.ball.wx.service;

import com.ball.wx.bean.RankBean;

import java.util.List;

public interface RankService {

    public void insertData() throws Exception;

    public void deleteData() throws Exception;

    public List<RankBean> selectData() throws Exception;

}
